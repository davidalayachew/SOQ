import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SOQ_20220514
{

   public static class Student {
      private String lName;
      private int idNumber;
      private int age;
   
      public Student() {
         lName = "";
         idNumber = 0;
         age = 0;
      }
      public Student(String l, int i, int a) {
         lName = l;
         idNumber = i;
         age = a;
      }
   
      public void setName(String last) {
         lName = last;
      }
   
      public String getName() {
         return lName;
      }
   
      public void setIdNum(int num) {
         idNumber = num;
      }
   
      public int getIdNum() {
         return idNumber;
      }
   
      public void setAge(int a) {
         age = a;
      }
   
      public int getAge() {
         return age;
      }
   }

   public static void main(String[] args) throws IOException {
      String file = "studentData.txt";
      Scanner reader = new Scanner(file);
   
      ArrayList<Student> users = readFile(file);
      Student s = new Student();
      users.add(s);
   
   
      Scanner input = new Scanner(System.in);
    //user enters idNumber to display age
      System.out.println("Enter ID Number"); //exception handling to be added
      int idNum = input.nextInt();
   
   //this part is where I'm stuck 
      for(int i = 0; i < users.size(); i++){
         if(idNum == users.get(i).getIdNum()){
            final Student temp = users.get(i);
            System.out.println("Student with an ID of " + temp.getIdNum() + " has an age of " + temp.getAge());
         }
      }
   
   }
//METHOD FOR READING FILE
   public static ArrayList<Student> readFile(String file)throws IOException{
      Scanner reader = new Scanner(new File(file));
   
      ArrayList<Student> list = new ArrayList<Student>();//creates ArrayList with student object
   
      reader.nextLine();//skips first line
      while(reader.hasNext()){//splits all text in the file into words
         String lName = reader.next();
         int idNum = reader.nextInt();
         int age = reader.nextInt();
      
         Student users = new Student(lName ,idNum, age); 
         list.add(users);
      }
    
      return list;
   
   }//end method

}
