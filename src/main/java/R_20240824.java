
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public final class R_20240824
{

   public static void main(final String[] args)
   {
   
      final List<String> list = scanByPrefix("b");
      
      list.forEach(System.out::println);
   
   }

   public static List<String> scanByPrefix(final String prefix) {
      final List<String> result;
      final Map<String, String> fields =
            Map
               .ofEntries
               (
                  Map.entry("b", "c"),
                  Map.entry("bb", "cc"),
                  Map.entry("bc", "ca")
               )
               ;
   
      final Map<String, String> someMap =
            fields
               .entrySet()
               .stream()
               .filter(entry -> entry.getKey().startsWith(prefix)) // Filter fields by prefix
               .collect
               (
                  Collectors
                     .toMap
                     (
                        Map.Entry::getKey, 
                        Map.Entry::getValue,
                        (oldValue, newValue) -> {throw new IllegalArgumentException("no merge!");},
                        TreeMap::new
                     )
               )
               ;
      
      result =
         someMap
            .entrySet()
            .stream()               
            .map(entry -> entry.getKey() + "(" + entry.getValue() + ")") // Format as "Field(Value)"
            .collect(Collectors.toList()) // Collect the result into a list
            ;
      return result;
   }

}