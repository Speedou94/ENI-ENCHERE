package fr.eni.encheres.groupe_2.dal.security;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class SecretKeyProvider {
   private static ResourceBundle rb;

   static {
       try {
           rb = ResourceBundle.getBundle("secret_key");
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
   public static String getSecretKey(int key){
       String secretKey = "";
       try {
           secretKey = rb.getString(String.valueOf(key));
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       return secretKey;
   }
   public static List<Integer> getTotalSecretKey(){
       List<Integer> totalSecretKey = new ArrayList<>();
       int cpt=0;
       try {
           Enumeration<String> enumeration = rb.getKeys();
           while (enumeration.hasMoreElements()){
               enumeration.nextElement();
               cpt++;
               totalSecretKey.add(cpt);
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       return totalSecretKey;
   }
}
