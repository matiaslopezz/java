import edu.duke.*;

public class WordLenghts {
           
    public int[] countWordLengths (FileResource resource, int[] count){
        int[] numberOfWords = new int[31];
        String[] listOfWords = new String[100];
        int index = 0;
        
        for(String word : resource.words()){
            String firstElement = word.substring(0);
            int firstIndex = word.indexOf(firstElement);
            int lastIndex = word.length() - 1;
            int wordLength = 0;
                        
            if(!Character.isLetter(word.charAt(firstIndex))){                              
                wordLength = word.length() - 1;
            } else {
                wordLength = word.length();
            }
            
            if(!Character.isLetter(word.charAt(lastIndex))){
                wordLength = word.length() - 1;
            } else{
                wordLength = word.length();
            }
            
            listOfWords[index] = word;            
            index += 1;
            
            for (int k=0; k<listOfWords.length; k++) {
               if (wordLength == k) {
                   numberOfWords[k] += 1;
               } 
               
               if(wordLength >= 30){
                   numberOfWords[30] += 1;
               }
            }            
        }
        
        for(int i=0; i<numberOfWords.length; i++){
            System.out.println(numberOfWords[i] + " words of length " + i);            
        }        
        return numberOfWords;
    }
    
    public int indexOfMax (int[] values) {
        int currentValue = 0;
        int currentIndex = 0;
        int maxValue = 0;
                
        for(int k=0; k<values.length; k++){
            if(values[k]>currentValue){
                currentValue = values[k];          
                currentIndex = k;
            }            
            maxValue = currentValue;            
        }
        System.out.println("maxValue: " + maxValue + " at index " + currentIndex);
        return currentIndex;
    }
    
    public void testCountWordLength() {
        FileResource resource = new FileResource();
        int[] count = new int[31];
        int[] result = countWordLengths(resource, count);
        int maxIndex = indexOfMax(result);
        System.out.println("Index of max length: " + maxIndex);        
    }   
    
}
