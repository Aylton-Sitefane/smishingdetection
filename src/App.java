import java.util.*;

public class App {
    public ArrayList<String> answer(String mensagem) {
        String[] palavrasChave = {"Aquele", "Faz", "a", "transferência", "Pode", "transferir", "sim", "não", "valor", "mandar", "neste", "este", "sai", "nome", "vem"};
        String[] carteiras = {"mpesa", "emola"};
        
        Set<String> conjuntoPalavrasChave = new HashSet<>(Arrays.asList(palavrasChave));
        Set<String> conjuntoCarteiras = new HashSet<>(Arrays.asList(carteiras));
        ArrayList<String> finalResult = new ArrayList<>();
        
        // Separar a mensagem em palavras
        String[] palavras = mensagem.split("\\s+|,\\s*|\\.\\s*");
        
        StringBuilder novaString = new StringBuilder();
        
        boolean carteiraEncontrada = false;
        for (String palavra : palavras) {
            if (conjuntoPalavrasChave.contains(palavra)) {
                if (palavra.equals("sim") || palavra.equals("não")) {
                    novaString.append(palavra).append(",");
                } else {
                    novaString.append(palavra).append("_");
                }
            } else if (conjuntoCarteiras.contains(palavra)) {
                novaString.append(palavra).append(",");
                carteiraEncontrada = true;
            } else {
                novaString.append(palavra).append(", ");
            }
        }
        
        // Corrigir a formatação final
        String resultado = novaString.toString().trim();
        if (resultado.endsWith("_")) {
            resultado = resultado.substring(0, resultado.length() - 1); 
        } else if (resultado.endsWith(", ")) {
            resultado = resultado.substring(0, resultado.length() - 2); 
        }
        
        // Adicionar cada segmento ao ArrayList finalResult
        String[] segmentos = resultado.split(",\\s*");
        Collections.addAll(finalResult, segmentos);
        
        return finalResult;
    }

    public static void main(String[] args) {
        // App app = new App();
        // ArrayList<String> resultado = app.answer("Faz a transferência com emola sim nenhum não");
        
        // // Exibir o resultado
        // System.out.println(resultado);

            try {
                J48SpamClassifier classifier = new J48SpamClassifier("./data/vodacom_spam.arff");
    
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
