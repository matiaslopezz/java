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
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> wordSet = new HashSet<String>();
        
        for(String line : fr.lines()){
            String lowerLine = line.toLowerCase();
            wordSet.add(lowerLine);
        }
        return wordSet;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String[] words = message.split("\\W");
        for (String word : words){
            if (dictionary.contains(word.toLowerCase())){
                count += 1  ;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int[] keyLength = new int[100];        
        int maxCount = 0;
        String decrypted = "";
        
        for (int i = 1; i < 101; i++){   
            int[] key = tryKeyLength(encrypted,i,'e');
            VigenereCipher vc = new VigenereCipher(key);
            
            String currentDecrypted = vc.decrypt(encrypted);
            int currentCount = countWords(currentDecrypted, dictionary);
            
            
            if (currentCount > maxCount) {
                maxCount = currentCount;
                decrypted = currentDecrypted;
                System.out.println(i);
            }       
        }
        System.out.println(maxCount);
        
        return decrypted;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        FileResource dictionary = new FileResource("dictionaries/English.txt");
        HashSet<String> dictionaryWords = readDictionary(dictionary);               
        
        String decrypted = breakForLanguage(message, dictionaryWords);       
        System.out.println(decrypted);              
        
    }
    
}
