import java.util.Scanner;

public class NameOfTheOldest {

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      String length = "";
      int red = 0;
      while(true){
         String word = scanner.nextLine();
         String [] array = word.split(",");
         int blue = Integer.valueOf(array[1]);
         if(blue > red){
            red = blue;
            length =array[0];  
         }
      }
      //System.out.println("Name of Oldest:" + length);
   
   }
}