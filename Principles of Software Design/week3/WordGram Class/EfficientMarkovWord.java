
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    
    private String[] myText;
    private Random myRandom;
    private Integer myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myMap = new HashMap<WordGram, ArrayList<String>>();
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
        myMap = buildMap();
        printHashMapInfo();
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
    
    public HashMap<WordGram, ArrayList<String>> buildMap(){
        myMap.clear();
        /*
        for (int i = 0; i < myText.length-myOrder+1; i++){
            WordGram kGram = new WordGram(myText, i, myOrder);
            String next = myText[i+myOrder];
            System.out.println("Next " + next + " " + i);
            ArrayList<String> follows = new ArrayList<String>();
            if(i+myOrder >= myText.length){ 
                System.out.print("inside if");
                myMap.put(kGram, follows);
                System.out.println("current map " + myMap);
            } else {
                if(myMap.containsKey(kGram)){
                    myMap.get(kGram).add(next);
                } else {
                    follows = new ArrayList<String>();
                    follows.add(next);
                    myMap.put(kGram, follows);;
                }
            }
        }
        */
       ArrayList<String> list = new ArrayList<String>();
       for (int i = 0 ; i < myText.length-myOrder+1; i++) {
           WordGram kGram = new WordGram(myText, i, myOrder);
           if(myMap.containsKey(kGram)){
               list = myMap.get(kGram);
               if (i+myOrder <= myText.length){
                   list.add(myText[i+myOrder]);
                }
           } else {
               list = new ArrayList<String>();
               if (i+myOrder < myText.length){
                   list.add(myText[i+myOrder]);
                   myMap.put(kGram,list);
                }
            }
        }
        return myMap;
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> res = myMap.get(key);
        return res;
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
    
    public void printHashMapInfo(){
        int max = 0;
        System.out.println("Map" + myMap);
        System.out.println("Total keys: " + myMap.size());
        
        for(WordGram wg : myMap.keySet()){
            int tempMax = myMap.get(wg).size();
            if (tempMax > max){
                max = tempMax;
                System.out.println("Max size: " + max);
            }
            if (myMap.get(wg).size() == max){
                System.out.println(wg);
            }            
        }
        
    }
    
}
