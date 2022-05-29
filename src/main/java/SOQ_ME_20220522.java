import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SOQ_ME_20220522
{

   public static void main(String[] args)
   {
   
      final List<String> list = List.of("apple", "banana", "coconut");
   
      var result = 
         list.stream()
            .collect(
               Collectors.toMap(
                  String::length,
                  Function.identity()
               )
            )
         ;
   
      System.out.println(result);
   
   }

}
