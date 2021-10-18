
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        String st = "this is a test yes this is a test.";
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows);
        System.out.println("Follows size: " + follows.size());
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        
        ArrayList<String> follows = markov.getFollows("th");
        System.out.println(follows);
        System.out.println("Follows size: " + follows.size());
    }
}
