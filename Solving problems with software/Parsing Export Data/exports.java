import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of class exports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class exports {
    
    public void countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser){
            String countryName = record.get("Country");
            String countryExports = record.get("Exports");
            String countryValues = record.get("Value (dollars)");
            if (countryName.contains(country)){
                System.out.print(countryName + ": ");
                System.out.print(countryExports + ": ");
                System.out.println(countryValues);
            }
        }
    }
    
    public void listExportersTwo(CSVParser parser, String exportItem1,
                                 String exportItem2){
       for (CSVRecord record : parser){
           String countryName = record.get("Country");
           String countryExports = record.get("Exports");
           
           if (countryExports.contains(exportItem1) && 
               countryExports.contains(exportItem2)){
               System.out.println(countryName);
           }
                
       }
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for (CSVRecord record : parser){
            String countryName = record.get("Country");
            String countryExports = record.get("Exports");
            if (countryExports.contains(exportItem)){
                counter += 1;
                System.out.println(countryName);
                
            }
        }
        System.out.println("The number of countries that export " +
                exportItem + " is: " + counter);
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String countryName = record.get("Country");
            String countryValues = record.get("Value (dollars)");
            if (countryValues.length() > amount.length()){
                System.out.print(countryName + " ");
                System.out.println(countryValues);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //Calling countryInfor
        System.out.println("- countryInfo method -");
        countryInfo(parser, "Nauru");
        
        System.out.println(" ");
        
        //Calling listExportersTwo
        parser = fr.getCSVParser();
        System.out.println("- listExportersTwo method -");
        listExportersTwo(parser, "fish", "nuts");
        
        System.out.println(" ");
        
        //Calling numberOfExporters
        parser = fr.getCSVParser();
        System.out.println("- numberOfExporters method -");
        numberOfExporters(parser, "sugar");
        
        //System.out.println(" ");
        
        //Calling bigExporters
        //parser = fr.getCSVParser();
        //System.out.println("- bigExporters method -");
        //bigExporters(parser, "$999,999,999,999");
    }
}
