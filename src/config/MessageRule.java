package config;

import java.util.HashMap;
import java.util.Map;


public class MessageRule {

    public static Map<String, Double> preprocessSMS(String sms) {
        Map<String, Double> attributes = new HashMap<>();

        String[] keywords = {"Pode_transferir_via", "Faz_a_transferência_com", "Pode_mandar_com", "Aquele_valor", "Usa_este_número", "Pode_mandar_neste_numero"};
        
        for (String keyword : keywords) {
            if (sms.contains(keyword)) {
                attributes.put(keyword, 1.0);
            } else {
                attributes.put(keyword, 0.0);
            }
        }

        return attributes;
    }
    
    
    
}
