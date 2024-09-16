package config;

import java.util.*;

public class SMSPreprocessor {

    public static ArrayList<String> SmsProcessor(String mensagem) {
        String[] palavrasChave = {"Aquele", "Faz", "a", "transferência", "Pode", "transferir", "sim", "não", "valor", "mandar", "neste", "este", "sai", "nome", "vem"};
        String[] carteiras = {"mpesa", "emola"};
        
        Set<String> conjuntoPalavrasChave = new HashSet<>(Arrays.asList(palavrasChave));
        Set<String> conjuntoCarteiras = new HashSet<>(Arrays.asList(carteiras));
        ArrayList<String> finalResult = new ArrayList<>();
        
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

        String resultado = novaString.toString().trim();
        if (resultado.endsWith("_")) {
            resultado = resultado.substring(0, resultado.length() - 1); 
        } else if (resultado.endsWith(", ")) {
            resultado = resultado.substring(0, resultado.length() - 2); 
        }

        String[] segmentos = resultado.split(",\\s*");
        Collections.addAll(finalResult, segmentos);
        
        return finalResult;
    }
    
    public static Map<String, Double> preprocessSMS(String sms) {
        ArrayList<String> processedWords = SmsProcessor(sms);
        Map<String, Double> attributes = new HashMap<>();

        for (String word : processedWords) {
            attributes.put(word, attributes.getOrDefault(word, 0.0) + 1.0); 
        }
        return attributes;
    }
}
