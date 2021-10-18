import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for(int k=0; k<message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);

            if(dex != -1){
                counts[dex] += 1;
            }
         }
        return counts;
    } 
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k<vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }    
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 1;
        
        if(maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);        
    }
    
    public void testDecrypt(){
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println("The message is: " + message);
                
        CaesarCipher cc = new CaesarCipher();
        int key = 15;
        String encrypted = cc.encrypt(message, key);
        System.out.println("The encrypted message is: " + encrypted);
               
        String decrypted = decrypt(encrypted);
        System.out.println("The decrypted message is: " + decrypted);
        System.out.println("");
    }    
    
    public String halfOfString(String message, int start){
        StringBuilder halfString = new StringBuilder();
        
        for(int i=0; i<halfString.length(); i++){
            char currentChar = halfString.charAt(i);
            
            int idx = message.indexOf(currentChar);
            if(start%2 == 0){
                char newChar = message.charAt(idx);
                halfString.setCharAt(i,newChar);
            } else if (start%2 != 0){
                char newChar = message.charAt(idx+1);
                halfString.setCharAt(i,newChar);
            }
        }
        return halfString.toString();
    }
    
    public void testHalfOfString(){    
        String message = "Qbkm Zgis";
        int start = 0;
        int start2 = 1;
        String result = halfOfString(message, start);
        System.out.println("Half string starting with: " + start +
        " --> " + result);
        result = halfOfString(message, start2);
        System.out.println("Half string starting with: " + start2 +
        " --> " + result);
    }
}
