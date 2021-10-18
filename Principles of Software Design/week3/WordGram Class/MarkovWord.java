
/**
 * Write a description of class MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private Integer myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            System.out.println("Key: " + kGram);
            System.out.println("Follows: " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        for (int i = start; i < words.length; i++){
            if(i >= words.length-myOrder){
                break;
            }            
            WordGram currWG = new WordGram(words,i,target.length());
            if(currWG.equals(target)){
                return i;
            }           
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText,kGram,pos);
            if (start == -1){
                break;
            }
            if (start + kGram.length() >= myText.length-myOrder){
                break;
            }
            String next = myText[start+myOrder];
            follows.add(next);
            pos = start + myOrder;
        }
        return follows;
    }
    
    public void testIndexOf(){
        String source = "this is a test this is a test";
    	String[] words = source.split("\\s+");
    	
    	for(int index = 0; index <= words.length - 4; index += 1) {
            WordGram wg = new WordGram(words,index,4);
            System.out.println(index+"\t"+wg.length()+"\t"+wg);
            int idx = indexOf(words, wg, 0);
            System.out.println("Index of: " + wg + " - " + idx);
        }
    }
    
}
