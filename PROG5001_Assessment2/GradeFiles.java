
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
        
        //Display a menu for user to interact
        while (true) {
            System.out.println("\nMenu: ");
            System.out.println("1. List students with total marks lower than the threshold");
            System.out.println("2. List 5 students who scored the highest and the lowest total marks");
            System.out.println("3. Exit");
            System.out.println("Choose the option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
        //Perform a switch case to let the user choose the operation.
            switch (choice) {
                case 1:
                    listStudentBelowThreshold(scanner);
                    break;
                case 2:
                    TopAndBelowStudents();
                    break;
                case 3:
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("No such option! Try Again.");
            }
        }
        
    }
    
    private static void listStudentBelowThreshold(Scanner scanner) {
        System.out.println("Enter the threshold value: ");
        double threshold = scanner.nextDouble();
        List<studentDetails> filterStudents = new ArrayList<>();
        
        for (studentDetails students : students){
            if (students.displayTotal() < threshold) {
                filterStudents.add(students);
            }
        }
        System.out.println("Students that scored less marks than " +threshold+ ":");
        for (studentDetails students : filterStudents) {
            System.out.println(students);
        }
    }
    
    private static void TopAndBelowStudents() {
        List<studentDetails> sortedStudents = new ArrayList<>(students);
        sortByMarks(sortedStudents);
        
        System.out.println("5 highest marks scorers : ");
        for (int i= 0; i <5 && i < sortedStudents.size(); i++) {        //Start from first placed student and increment reaching the fifth highest scoring student.
            System.out.println(sortedStudents.get(i));
        }
        
        System.out.println("5 lowest marks scorers : ");
        for (int i= sortedStudents.size()-1; i >= sortedStudents.size() -5 && i >=0; i--) {     //Start from last placed student and decrement reaching the fifth lowest scoring student.
            System.out.println(sortedStudents.get(i));
        }        
    }
    
    private static void sortByMarks(List<studentDetails> studentList) {
        int n = studentList.size();
        for (int i =0; i < n; i++){
            for (int j =0; j < n-i-1; j++) {
                if (studentList.get(j).displayTotal() < studentList.get(j+1).displayTotal()){
                   studentDetails temp = studentList.get(j);
                   studentList.set(j, studentList.get(j+1));
                   studentList.set(j+1, temp);
                }
                
            }
        }
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

