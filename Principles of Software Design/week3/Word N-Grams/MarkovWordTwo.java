
/**
 * Write a description of class MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        sb.append(key1);
        sb.append(" ");
        String key2 = myText[index+1];
        sb.append(key2);
        sb.append(" ");        
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            //System.out.println("Key1: " + key1);
            //System.out.println("Key2: " + key2);
            //System.out.println("Follows: " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size()); 
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target1, 
    String target2, int start){
        
        for (int i = start; i < words.length-2; i++){
            if (words[i].equals(target1) && words[i+1].equals(target2)){
                return i;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText,key1,key2,pos);
            if (start == -1){
                break;
            }
            if (start + key1.length() + key2.length() >= myText.length-2) {
                break;
            }
            String next = myText[start+2];
            follows.add(next);
            pos = start + 2;
        }
        return follows;
    }
    
    public void testIndexOf(){
        String st = "this is just a test yes this is a simple test";
        setTraining(st);
        String target1 = "this";
        String target2 = "is";
        int start = 0;
        int idx = indexOf(myText, target1, target2, start);
        System.out.println("Index of " + target1 + " " + idx);
        
        target1 = "just";
        target2 = "a";
        start = 3;
        idx = indexOf(myText, target1, target2, start);
        System.out.println("Index of " + target1 + " " + idx);        
        
    }   
}