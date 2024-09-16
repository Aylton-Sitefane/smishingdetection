import model.J48SmishingClassifier;
import model.OneRClassifier;

public class App {
    
    public static void main(String[] args) {
    
            try {
                J48SmishingClassifier classifier = new J48SmishingClassifier("./data/vodacom_spam.arff");
    
                String structuredMessage = "Pode_transferir_via mpesa sim nenhum sim"; 
                String unstructuredMessage = "Pode transferir via mpesa sai nome de sim"; 
    
                String result1 = classifier.classifyMessage(structuredMessage);
                System.out.println("A mensagem estruturada é: " + result1);
    
                String result2 = classifier.classifyMessage(unstructuredMessage);
                System.out.println("A mensagem não estruturada é: " + result2);

                OneRClassifier oneR = new OneRClassifier();
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
}
