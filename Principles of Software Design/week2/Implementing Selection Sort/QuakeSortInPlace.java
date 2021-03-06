
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for(int i=from+1; i < quakeData.size(); i++){
            QuakeEntry quake = quakeData.get(i);
            QuakeEntry quakeMax = quakeData.get(maxIdx);
            if(quake.getDepth() > quakeMax.getDepth()){
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByDepth(ArrayList<QuakeEntry> in) {
        for (int i=0; i < 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(maxIdx,qi);
            in.set(i,qmax);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData,
    int numSorted) {
        //for (int j=0; j<numSorted; j++) {
            for (int i=0; i < quakeData.size()-1; i++){
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qNext = quakeData.get(i+1);
                
                if(qi.getMagnitude() > qNext.getMagnitude()){
                    quakeData.set(i,qNext);
                    quakeData.set(i+1,qi);
                }
            }
            //System.out.println("Printing quakes after pass" + " " + j);
            for (QuakeEntry qe: quakeData) { 
                System.out.println(qe);
            }            
        //}    
        
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for(int i=0; i<in.size()-1;i++){
            System.out.println("Printing quakes after pass" + " " + i);
            onePassBubbleSort(in,i);            
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i=0; i < quakes.size()-1; i++){
            QuakeEntry qi = quakes.get(i);
            QuakeEntry qNext = quakes.get(i+1);
                
            if(qi.getMagnitude() > qNext.getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck
    (ArrayList<QuakeEntry> in){
        int passes = 0;
        for(int i = 0; i < in.size()-1; i++) {
            System.out.println("Sorted? " + checkInSortedOrder(in));
            if(!checkInSortedOrder(in)){                
                System.out.println("Printing quakes after pass" + " " + i);
                onePassBubbleSort(in,i);
                passes++;                
            }
        }
        System.out.println("Passes needed to sort = " + passes);
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int passes = 0;
        for (int i=0; i< in.size(); i++) {
            if(!checkInSortedOrder(in)){
                int minIdx = getSmallestMagnitude(in,i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i,qmin);
                in.set(minIdx,qi);
                passes++;
            }             
        }
        System.out.println("Passes needed to sort = " + passes);
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");
        //sortByMagnitude(list);
        //sortByDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println("Earthquakes in sorted order:");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
