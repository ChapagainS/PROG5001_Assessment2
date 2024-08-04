
/**
 * PROG5001_Assessment2
 *Fundamental of Programming
 * @author (Subash Chapagain)
 * @version (7th July, 2024)
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GradeFiles
{
    private static List<studentDetails> students = new ArrayList<studentDetails>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);           //Taking the file input from the user.
        System.out.println("Enter the file name of the Student marks sheet: ");
        String fileName = scanner.nextLine();               
        readSheetData(fileName);
        displayMarks();
    }
    
    public static void readSheetData(String fileName){
        try(Scanner fileScanner = new Scanner (new File(fileName))){
            fileScanner.nextLine();                         //Ignore the first line of data on the sheet.
            fileScanner.nextLine();                         //Ignore the second line of data on the sheet.
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] data = line.split(",");            //Giving instruction that the different fields on the sheet are separated by a comma ",".
                if (data.length >=6) {
                    String name = data[1] +" "+data[0];     //Giving the array value of the data.
                    int studentId = Integer.parseInt(data[2]);
                    double a1=0, a2=0, a3=0;                //Initial value set
                    try{
                        a1 = Double.parseDouble (data[3]);
                        a2 = Double.parseDouble (data[4]);
                        a3 = Double.parseDouble (data[5]);
                    } catch (NumberFormatException e) {     
                        System.err.println();               //Default error message if the format of the data doesn't match.
                    }
                    students.add(new studentDetails(name, studentId, a1, a2, a3));
                }
            }
        }
            catch (FileNotFoundException e) {
                System.out.println("File is not located in same directory!" +fileName);         //Run this error message if the file in not found in the location.
                System.exit(1);
            }
    }
    public static void displayMarks(){
        for(studentDetails students : students) {
            System.out.println(students);
        }
    }
}

class studentDetails{
    private String name;
    private int studentId;
    private double a1, a2, a3;
    
    public studentDetails (String name, int studentId, double a1, double a2, double a3) {
        this.name = name;
        this.studentId = studentId;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }
    
    public double displayTotal(){
        return this.a1+ this.a2+ this.a3;               //Get the total accumulated marks;
    }
    
    @Override
    public String toString(){
        return String.format("%-50s %-15d %.1f %.1f %.1f %.1f", this.name, this.studentId, this.a1, this.a2, this.a3, displayTotal());
    }
}
