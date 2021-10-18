
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;    
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int charNumber) {        
        myRandom = new Random();
        N = charNumber;
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        map = buildMap();
        
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public HashMap<String,ArrayList<String>> buildMap(){
        map.clear();
        for (int i = 0; i < myText.length()-N+1; i++){
            String key = myText.substring(i, i+N);
            ArrayList<String> follows = new ArrayList<String>();
            if (i+N >= myText.length()){
                map.put(key,follows);
            } else {
                if (map.containsKey(key)){
                    map.get(key).add(myText.substring(i+N, i+N+1));
                } else {
                    follows.add(myText.substring(i+N, i+N+1));
                    map.put(key,follows);
                }
            }
        }
        return map;
    }
    
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> res = map.get(key);
        return res;
    }
    
    
    public String toString() {
        return "EfficientMarkovModel of order " + N;
    }
    
    public void printHashMapInfo() {
        HashMap<String, ArrayList<String>> map = buildMap();
        int max = 0;
        
        //System.out.println(map);
        System.out.println("Total keys: " + map.size());
        
        for(String s : map.keySet()){
            int tempMax = map.get(s).size();
            if (tempMax > max){
                max = tempMax;
                System.out.println("Max size: " + max);
            }
            if (map.get(s).size() == max){
                System.out.println(s);
            }            
        }
    }
}
