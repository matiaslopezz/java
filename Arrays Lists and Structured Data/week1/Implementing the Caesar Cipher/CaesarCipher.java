import edu.duke.*;

public class CaesarCipher {
    public String encrypt (String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet;
        //Considering lower case letters
        String alphabetLower = alphabet.toLowerCase();
        String shiftedAlphabetLower = shiftedAlphabet.toLowerCase();
        
        for (int i=0; i < encrypted.length(); i++){
            char currentChar = encrypted.charAt(i);
            
            int idxUpper = alphabet.indexOf(currentChar);
            int idxLower = alphabetLower.indexOf(currentChar);
            
            if (idxUpper != -1 ){
                char newChar = shiftedAlphabet.charAt(idxUpper);
                encrypted.setCharAt(i, newChar);
            }
            
            if (idxLower != -1){
                char newChar = shiftedAlphabetLower.charAt(idxLower);
                encrypted.setCharAt(i, newChar);
            }
        }
        
        return encrypted.toString();
    }
    
    public void testEncrypt(){
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        String result = encrypt(input, key);
        System.out.println(input + " ---> " + result);
        System.out.println("");
        
        input = "First Legion";
        key = 23;
        result = encrypt(input, key);
        System.out.println(input + " ---> " + result);
        System.out.println("");
        
        input = "First Legion";
        key = 17;
        result = encrypt(input, key);
        System.out.println(input + " ---> " + result);
        System.out.println("");
    }
    
    public void testCaesar (){
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        //If we want the original message back:
        String decrypted = encrypt(encrypted, 23-key);
        System.out.println(decrypted);
    }
    
    public String encryptTwoKeys (String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
               
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = alphabet.toLowerCase();
        
        for (int i=0; i < encrypted.length(); i++){
            char currentChar = encrypted.charAt(i);
            
            int idxUpper = alphabet.indexOf(currentChar);
            int idxLower = alphabetLower.indexOf(currentChar);
            
            if (i%2 == 0){   
                String shiftedAlphabet = alphabet.substring(key1) + alphabet;
                String shiftedAlphabetLower = shiftedAlphabet.toLowerCase();
                
                if (idxUpper != -1 ){
                    char newChar = shiftedAlphabet.charAt(idxUpper);
                    encrypted.setCharAt(i, newChar);
                }
            
                if (idxLower != -1){
                    char newChar = shiftedAlphabetLower.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }
                
            } else if (i%2 != 0) {
                String shiftedAlphabet = alphabet.substring(key2) + alphabet;
                String shiftedAlphabetLower = shiftedAlphabet.toLowerCase();
                
                if (idxUpper != -1 ){
                    char newChar = shiftedAlphabet.charAt(idxUpper);
                    encrypted.setCharAt(i, newChar);
                }            
                if (idxLower != -1){
                    char newChar = shiftedAlphabetLower.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);                
                }    
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys(){
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        String result = encryptTwoKeys(input, key1, key2);
        System.out.println(input + " ---> " + result);
        System.out.println("");
    }
}
