import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterCounts;
    
    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        characterCounts = new ArrayList<Integer>();
    }
    
    public void update(String person) {        
        int index = characterNames.indexOf(person);
        if (!characterNames.contains(person)){
            characterNames.add(person);
            characterCounts.add(1);
        } else {
            int value = characterCounts.get(index);
            characterCounts.set(index, value + 1);
        }
    }
    
    public void findAllCharacters() {
        FileResource resource = new FileResource();
        characterNames.clear();
        characterCounts.clear();
        
        for (String line : resource.lines()) {
            String dot = ".";
            int indexOfDot = line.indexOf(dot);
            if (line.contains(dot)) {
                String name = line.substring(0,indexOfDot);
                //System.out.println(name);
                update(name);
            }
        }        
    }
    
    public void charactersWithNumParts(int num1, int num2) {        
        for (int k=0; k<characterCounts.size(); k++) {
            int freqs = characterCounts.get(k);
            String name = characterNames.get(k);
            if (freqs >= num1 && freqs <= num2) {
                System.out.println(name+"\t"+freqs);
            }
        }        
    }    
    
    public void tester () {
        findAllCharacters();
        for (int k = 0; k < characterNames.size(); k++) {
            if (characterCounts.get(k) >= 2) {                
                System.out.println(characterNames.get(k)+"\t"+characterCounts.get(k));
            }
        }
        
        int num1 = 10;
        int num2 = 15;        
        System.out.println("Character between 2 and 5 appearecences: ");
        charactersWithNumParts(num1, num2);        
    }
}
