import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class J48SmishingClassifier {

    private J48 classifier;
    private Instances datasetStructure;

    public J48SmishingClassifier(String arffFilePath) throws Exception {

        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(arffFilePath));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);
        classifier = new J48();
        classifier.setConfidenceFactor(0.25f); 
        classifier.setMinNumObj(2);            
        classifier.buildClassifier(data);

        classifier = new J48();
        classifier.buildClassifier(data);

        datasetStructure = new Instances(data, 0);
        datasetStructure.setClassIndex(datasetStructure.numAttributes() - 1);

        System.out.println("Árvore de decisão:");
        System.out.println(classifier.toString());
    }

    public String classifyMessage(String message) throws Exception {
        String formattedMessage = replaceSpacesWithCommas(message);

        DenseInstance instance = new DenseInstance(datasetStructure.numAttributes());
        instance.setDataset(datasetStructure);

        String[] messageParts = formattedMessage.split(","); 
        boolean isStructured = false;

        if (messageParts.length == datasetStructure.numAttributes() - 1) {
            isStructured = true;
            for (int i = 0; i < messageParts.length; i++) {
                String value = messageParts[i].trim();
                Attribute attribute = datasetStructure.attribute(i);

                if (attribute.isNominal()) {
                    int index = attribute.indexOfValue(value);
                    if (index < 0) {
                        List<String> values = new ArrayList<>();
                        for (int j = 0; j < attribute.numValues(); j++) {
                            values.add(attribute.value(j));
                        }
                        throw new IllegalArgumentException("Valor não definido para o atributo: " + value
                            + ". Valores esperados: " + values);
                    }
                    instance.setValue(attribute, value);
                } else {
                    instance.setValue(attribute, value);
                }
            }
        }

        double labelIndex = -1;
        double[] distribution = null;
        if (isStructured) {
            labelIndex = classifier.classifyInstance(instance);
            distribution = classifier.distributionForInstance(instance);
        }

        if (isStructured) {
            String label = datasetStructure.classAttribute().value((int) labelIndex);
            StringBuilder result = new StringBuilder("Classe: " + label + "\nProbabilidades: ");
            for (int i = 0; i < distribution.length; i++) {
                result.append(datasetStructure.classAttribute().value(i)).append(": ")
                      .append(String.format("%.2f", distribution[i] * 100)).append("% ");
            }
            return result.toString();
        } else {
            return "Mensagem não estruturada para o modelo.";
        }
    }

    private String replaceSpacesWithCommas(String message) {
        return message.replaceAll("\\s+", ",");
    }

    public static void main(String[] args) {
        try {
            J48SmishingClassifier classifier = new J48SmishingClassifier("vodacom_spam.arff");

            String structuredMessage = "Pode_transferir_via mpesa sim nenhum sim"; 
            String unstructuredMessage = "Pode transferir via mpesa sai nome de sim"; 

            String result1 = classifier.classifyMessage(structuredMessage);
            System.out.println("A mensagem estruturada é: " + result1);

            String result2 = classifier.classifyMessage(unstructuredMessage);
            System.out.println("A mensagem não estruturada é: " + result2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
