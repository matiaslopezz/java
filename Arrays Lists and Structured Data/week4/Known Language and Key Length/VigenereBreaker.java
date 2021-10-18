import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    CaesarCracker cc = new CaesarCracker();
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder(message);
        String slicedString = "";
        for (int i=whichSlice; i<sb.length(); i+=totalSlices) {
            char c = sb.charAt(i);
            slicedString = slicedString + c;
        }
        return slicedString.toString();
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        for(int i = 0; i<key.length; i++){
            String slicedString = sliceString(encrypted, i, klength);
            int k = cc.getKey(slicedString);
            key[i] = k;
            System.out.println(k);
        }        
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource("messages/secretmessage1.txt");
        String message = fr.asString();
        int[] key = tryKeyLength(message,4,'e');
               
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(message);
        
        System.out.println(decrypted);       
                
        
    }
    
}
