import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource resource = new FileResource();
        myWords.clear();
        myFreqs.clear();
        
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int currentValue = 0;
        int index = 0;
        
        for (int k = 0; k < myFreqs.size(); k++) {
            if (myFreqs.get(k) > currentValue) {
                currentValue = myFreqs.get(k);
                index = k;
            }            
        }
        return index;
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        
        for (int k = 0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k)+"\t"+myWords.get(k)); 
        }
        
        int indexOfMax = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " +
        myWords.get(indexOfMax)+" "+myFreqs.get(indexOfMax));
    }   
    
    
}
