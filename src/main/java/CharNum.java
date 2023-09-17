import java.util.List;
import java.util.Objects;

public record CharNum(char c, int num)
{

   public static void main(final String[] args)
   {
   
      triangle("xyzabc");
   
   }

   public static void triangle(final String letters)
   {
   
      Objects.requireNonNull(letters);
   
      final List<Integer> characters =
         letters
            .chars()
            .boxed()
            .toList()
            ;
   
      final int MAX_SIZE = characters.size();
      
      for (int row = 0; row < MAX_SIZE; row++)
      {
      
         for (int column = 0; column < MAX_SIZE; column++)
         {
         
            final String character = Character.toString(characters.get(column));
         
            final int digits = MAX_SIZE - row - column;
            
            if (digits > 0)
            {
            
               System.out.print(character.repeat(digits));
               System.out.print(" ".repeat(MAX_SIZE - digits + 1));
            
            }
            
            else
            {
            
               System.out.print(" ".repeat(MAX_SIZE + 1));
            
            }
         
         }
         
         System.out.println();
      
      }
   
   }

}