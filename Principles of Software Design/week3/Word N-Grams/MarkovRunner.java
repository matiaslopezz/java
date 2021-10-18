
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        int seed = 139;
        int size = 120;
        runModel(markovWord, st, size, seed); 
    } 
    
    public void runMarkovTwo() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovTwo = new MarkovWordTwo(); 
        int seed = 832;
        int size = 120;
        runModel(markovTwo, st, size, seed); 
    } 
    
    public void testing(){
        MarkovWordOne markov = new MarkovWordOne();
        String st = "this is just a test yes this is a simple test";
        markov.setTraining(st);
        int seed = 42;
        int size= 50;
        runModel(markov, st, size, seed);    
    }
    
    public void testing2(){
        MarkovWordTwo markov = new MarkovWordTwo();
        String st = "this is just a test yes this is a simple test";
        markov.setTraining(st);
        int seed = 42;
        int size= 50;
        runModel(markov, st, size, seed);    
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
