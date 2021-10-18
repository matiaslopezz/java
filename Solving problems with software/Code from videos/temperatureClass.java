import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class temperatureClass {

    public CSVRecord hottestHourInFile (CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (largestSoFar == null){
                largestSoFar = currentRow;
            } else {
                String string_currentTemp = currentRow.get("TemperatureF");
                double currentTemp = Double.parseDouble(string_currentTemp);
                
                String string_largestTemp = largestSoFar.get("TemperatureF");                
                double largestTemp = Double.parseDouble(string_largestTemp);
                
                if (currentTemp > largestTemp){
                    largestSoFar = currentRow;
                }
            }            
        }
        return largestSoFar;
    }
    
    public void testHottestInDay() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord largest = hottestHourInFile(parser);
        System.out.println("Hottest temperature was " + 
        largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }
}
