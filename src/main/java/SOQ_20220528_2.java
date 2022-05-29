import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SOQ_20220528_2
{

   public static void main(String[] args)
   {
   
      final List<String[]> list = new ArrayList<>();
   
      Scanner scanner = new Scanner(System.in);
         
      while (scanner.hasNextLine()) {
            
         String[] arr = scanner.nextLine().split(",");
            
         list.add(arr);
            
      }
   
      String[][] ar = list.toArray(String[][]::new);
      System.out.println(Arrays.deepToString(ar[0]));
   
   }

}