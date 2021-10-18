import edu.duke.*;

public class WordPlay {
    public boolean isVowel (char ch){
        String vowels = "aeiou";
        String capitalVowels = vowels.toUpperCase();
        
        if (vowels.indexOf(ch) != -1 || capitalVowels.indexOf(ch) != -1){
            return true;
        } else {
            return false;
        }
    }
    
    public void testIsVowel (){
        char ch = 'e';
        boolean result = isVowel(ch);
        System.out.println(ch + " is vowel? " + result);
    }
    
    
    public String replaceVowels (String phrase, char ch){
        StringBuilder string = new StringBuilder(phrase);
                
        for (int i=0; i < string.length(); i++){
            char currentchar = string.charAt(i);
            
            if (isVowel(currentchar)){
                string.setCharAt(i, ch);
            }            
        }
        return string.toString();
    }
    
    public void testReplaceVowels (){
        String phrase = "Hello World";
        char ch = '#';
        String result = replaceVowels(phrase,ch);
        System.out.println(phrase + " ---> " + result);
    }
    
    
    public String emphasize (String phrase, char ch){
        StringBuilder string = new StringBuilder(phrase);
        
        for (int i=0; i < string.length(); i++){
            char currentChar = string.charAt(i);
            char chUpper = Character.toUpperCase(ch);  
            char chLower = Character.toLowerCase(ch);
            
            if (i%2 != 0 && (currentChar == chLower || currentChar == chUpper)){
                string.setCharAt(i,'+');
            } else if(i%2 == 0 && (currentChar == chLower || currentChar == chUpper)) {
                string.setCharAt(i,'*');
            }            
        }
        
        return string.toString();
    }
    
    public void testEmphasize(){
        String phrase = "dna ctgaaactga";
        char ch = 'A';
        String result = emphasize(phrase,ch);
        System.out.println(phrase + " ---> " + result);  
        System.out.println("");
        
        phrase = "Mary Bella Abracadabra";
        ch = 'a';
        result = emphasize(phrase,ch);
        System.out.println(phrase + " ---> " + result); 
    
    }
}
