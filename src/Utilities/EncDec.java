package Utilities;
import org.apache.commons.codec.binary.Base64;

public class EncDec {

    //Result is a byteArray which is "Hashed"
     public byte[]  encode(String stringToEncode) {
        byte [] encoded = null;
        encoded = Base64.encodeBase64(stringToEncode.getBytes());
        return encoded;
    }

    public String decode(byte[] byteArrayToDecode) {
        byte [] decoded = null;
        decoded = Base64.decodeBase64(byteArrayToDecode);
        String decodedString = new String(decoded);
        return decodedString;
    }
}
