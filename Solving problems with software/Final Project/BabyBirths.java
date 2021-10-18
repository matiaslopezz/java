import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
   //Lesson video code
    public void printNames(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser(false);
       
       for (CSVRecord rec : parser){
           int numBorn = Integer.parseInt(rec.get(2));
           String name = rec.get(0);
           String gender = rec.get(1);
           if (numBorn <= 100){
               System.out.println("Name: " + name + " Gender: " + gender + 
               " Num Born: " + numBorn);
            }
       }
   }
   
   //Point1 - totalBirths
   public void totalBirths (FileResource fr){
       int totalBirths = 0;
       int totalNames = 0;
       
       int totalGirlsBirth = 0;
       int totalGirlsNames = 0;
       
       int totalBoysBirth = 0;
       int totalBoysNames = 0;
       
       CSVParser parser = fr.getCSVParser(false);
       for (CSVRecord rec : parser){
           int numBorn = Integer.parseInt(rec.get(2));
           String name = rec.get(0);
           String gender = rec.get(1);
           
           totalBirths += numBorn;
           totalNames += 1;
           
           if (gender.equals("M")){
               totalBoysNames += 1;
               totalBoysBirth += numBorn;
           } else {
                totalGirlsNames += 1;
                totalGirlsBirth += numBorn;
           }
       }
       System.out.println("Total births: " + totalBirths);
       System.out.println("Total girls births: " + totalGirlsBirth);
       System.out.println("Total boys births: " + totalBoysBirth);
       System.out.println(" ");
       System.out.println("Total names in file: " + totalNames);
       System.out.println("Total girls names: " + totalGirlsNames);
       System.out.println("Total boys births: " + totalBoysNames);
   }
   
   public void testTotalBirths () {
       FileResource fr = new FileResource("./us_babynames_test/example-small.csv");
       totalBirths(fr);
   }
   
   //Point2 - getRank
   public int getRank(int year, String name, String gender){
       int rank = 0;
       
       FileResource fr = new FileResource("./testing/yob"+year+"short.csv");
       CSVParser parser = fr.getCSVParser(false);
       for (CSVRecord rec : parser){
           String dataName = rec.get(0);
           String dataGender = rec.get(1);
           
           if(dataGender.equals(gender)){
               rank += 1;
               
               if(dataName.equals(name)){
                   return rank;
               }
           } 
       }
       return -1;
   }
   
   public void testGetRank (){
       int rank = getRank(2012, "Jacob", "M");
       System.out.println("Rank of Jacob on 2012 was: " + rank);
       System.out.println(" ");
       
       rank = getRank(2012, "Ava", "F");
       System.out.println("Rank of Ava on 2012 was: " + rank);
       System.out.println(" ");
       
       rank = getRank(2013, "Isabella", "F");
       System.out.println("Rank of Isabella on 2013 was: " + rank);
       System.out.println(" ");
       
       rank = getRank(2012, "Mason", "F");
       System.out.println("Rank of Mason on 2012 was: " + rank);
       System.out.println(" ");
   }
   
   
   //Part3 - getName   
   public String getName (int year, int rank, String gender) {
       String name = "";
       int count = 0;
       
       FileResource fr = new FileResource("./testing/yob"+year+"short.csv");
       CSVParser parser = fr.getCSVParser(false);
       for (CSVRecord rec : parser){
           String dataGender = rec.get(1);
           
           if(dataGender.equals(gender)){
               count += 1;
               name = rec.get(0);
               
               if(count == rank){
                   return name;
               }            
           }                  
       }
       return "NO NAME";
   }
   
   public void testGetName () {
       String name = getName(2012, 6, "F");
       System.out.println("Name of the 6'female birth in 2012 is: " + name);
       System.out.println(" ");
   }
   
   //Part4 - changingNames   
   public void whatIsNameInYear (String name, int year, int newYear, String gender){
       int rank = getRank(year, name, gender);
       String newName = "";
       int count = 0;
       
       FileResource fr = new FileResource("./testing/yob" + newYear + "short.csv");
       CSVParser parser = fr.getCSVParser(false);
       for (CSVRecord rec : parser){
           String dataGender = rec.get(1);
           
           if(dataGender.equals(gender)){
               count += 1;
               
               if(count == rank){
                   newName = rec.get(0);                   
               }
           }           
       }
       System.out.println(name + " born in " + year + " would be " + newName + 
       " if he/she was born in " + newYear);       
   }      
   
   public void testWhatIsNameInYear() {
       whatIsNameInYear("Mason", 2012, 2014, "M");
   }
   
   //Part5 - yearHighRank
   
   public int yearOfHighestRank (String name, String gender) {
       int year = 0;
       
       DirectoryResource dr = new DirectoryResource();
       
       return year;
   }
}
