
/**
 * Write a description of class TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        
        String lastWord1 = title1.substring(title1.lastIndexOf(",")+1);
        String lastWord2 = title2.substring(title2.lastIndexOf(",")+1);
        
        double mag1 = q1.getMagnitude();
        double mag2 = q2.getMagnitude();
        
        int comparingWords = lastWord1.compareTo(lastWord2);
        
        if(comparingWords < 0){
            return -1;
        }
        if(comparingWords > 0){
            return 1;
        }
        if(comparingWords == 0){
            if(mag1 < mag2){
                return -1;
            }
            if(mag1 > mag2){
                return 1;
            }
        }
        return 0;
    }
}
