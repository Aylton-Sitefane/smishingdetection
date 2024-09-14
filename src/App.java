import config.MessageRule;
import data.DatasetSmish;
import model.ClassificadorJ48;

public class App {
    public static void main(String[] args) throws Exception {
        String caminho = "/caminho/para/dataset";
        try {
            ClassificadorJ48 classifier= new ClassificadorJ48();
            DatasetSmish datasetSmish = new DatasetSmish(caminho);
            classifier.trainModel(datasetSmish.getDataInstance());

            MessageRule messageRule = new MessageRule();

            String mensagem = "Envia o valor para esse numero";
            String resultTrain = messageRule.isSmisging(datasetSmish.getDataInstance(), mensagem, classifier.getTree());
            System.out.println("O resultado da classicicacao> "+ resultTrain);

        } catch (Exception e) {
            System.out.println("Ocorreu alguma execssao durante o treinamento "+e);
        }
    }
}
