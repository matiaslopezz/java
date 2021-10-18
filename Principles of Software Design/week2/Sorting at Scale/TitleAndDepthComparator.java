
/**
 * Write a description of class TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
       
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        
        double depth1 = q1.getDepth();
        double depth2 = q2.getDepth();
        
        int comparingTitles = title1.compareTo(title2);
        
        if(comparingTitles < 0){
            return -1;
        }
        if(comparingTitles > 0){
            return 1;
        }
        if(comparingTitles == 0){
            if(depth1 < depth2){
                return -1;
            }
            if(depth1 > depth2){
                return 1;
            }
        }
        return 0;
    }
}
