import java.util.Scanner;

public class SOQ_20220514_1
{

   public static void main(String[] args)
   {
   
      final Scanner in = new Scanner(System.in);
   
      System.out.println("Take Another Order? (Y/N)");
      String answer = in.nextLine();
      
      System.out.println("Take Another Order? (Y/N)");
      answer = in.nextLine();
      
      if(answer.equalsIgnoreCase("y"))
      {
         
         System.out.println("HERE");
         //b.getClass(); 
      
      }
      
      else
      {
      
         System.out.println("Thanks for ordering!");
      
      }
   
   }

}