import java.util.Scanner;

public class UserPrompt
{

   private final Scanner kbd = new Scanner(System.in);

   public int askForNumber(String prompt)
   {
   
      //Print your prompt to the command line -- I added a little extra at the end for simple reading
      System.out.print(prompt + " - ");
      
      return kbd.nextInt();
      
   }
   
   public void print(String message)
   {
   
      System.out.println(message);
   
   }

}
