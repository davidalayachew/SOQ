import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class idk
{

   public static void main(final String[] args)
   {
   
      record Attribute(String attr) {}
   
      final List<Attribute> myList =
         List
            .of
            (
               new Attribute("hello"),
               new Attribute("goodbye"),
               new Attribute("oh no")
            )
            ;
   
      final Map<String, List<String>> result =
         myList
            .stream()
            .map(Attribute::attr)
            .filter(attr -> "hello".equals(attr) || "goodbye".equals(attr))
            .collect(Collectors.groupingBy(Function.identity()))
            ;
      
      System.out.println(result.get("hello"));   //[hello]
      System.out.println(result.get("goodbye")); //[goodbye]
   
   
   }

}