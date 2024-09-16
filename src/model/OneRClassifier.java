package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import config.SMSPreprocessor;
import weka.classifiers.rules.OneR;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class OneRClassifier {

    public OneRClassifier() throws Exception{
        DataSource source = new DataSource("./data/vodacom_spam.arff");
        Instances dataset = source.getDataSet();

        if (dataset.classIndex() == -1) {
            dataset.setClassIndex(dataset.numAttributes() - 1);
        }

        OneR oneR = new OneR();
        oneR.buildClassifier(dataset);

        String sms = "Pode_transferir_via mpesa sim nenhum sim"; 
        Map<String, Double> attributes = SMSPreprocessor.preprocessSMS(sms);

        ArrayList<Attribute> attributesList = new ArrayList<>();
        for (int i = 0; i < dataset.numAttributes() - 1; i++) { 
            attributesList.add(dataset.attribute(i));
        }

        List<String> classValues = new ArrayList<>();
        classValues.add("spam");
        classValues.add("nao_spam");
        Attribute classAttribute = new Attribute("classe", classValues);
        attributesList.add(classAttribute);

        Instances testSet = new Instances("TestSet", attributesList, 1);
        testSet.setClassIndex(testSet.numAttributes() - 1);

        double[] values = new double[testSet.numAttributes()];
        for (int i = 0; i < dataset.numAttributes() - 1; i++) { 
            Attribute attr = dataset.attribute(i);
            values[i] = attributes.getOrDefault(attr.name(), 0.0);
        }

        Instance testInstance = new DenseInstance(1.0, values);
        testSet.add(testInstance);

        double label = oneR.classifyInstance(testSet.instance(0));

        System.out.println(testSet.toSummaryString());
        
        System.out.println("A SMS é classificada como: " + testSet.classAttribute().value((int) label));
    }

    
}


