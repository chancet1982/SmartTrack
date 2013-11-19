package Utilities;

import java.security.MessageDigest;
public class Encryption {

     public String encrypt(String stringToEncrypt) {
         String encryptedString = null;
         try {
             MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
             messageDigest.update(stringToEncrypt.getBytes());
             encryptedString = new String(messageDigest.digest());
         } catch (Exception e) {

         }
         return encryptedString;
    }

    public String decrypt(String stringToDecrypt) {
        String encryptedString = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(stringToDecrypt.getBytes());
            encryptedString = new String(messageDigest.digest());
        } catch (Exception e) {

        }
        return encryptedString;
    }
}
