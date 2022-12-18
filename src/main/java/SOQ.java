import java.util.*;
import java.util.stream.*;

public class SOQ
{

   public static void main(String[] args)
   {
   
      new SOQ();
   
   }

   public SOQ()
   {
   
      this.soq_20221217_better();
   
   }
   
   private void soq_20221217_better() // https://stackoverflow.com/questions/74838895/java8-flatmap-mapstring-liststring-to-mapstring-integer-where-the-int
   {
   
      //https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summingInt-java.util.function.ToIntFunction-
      final Map<String, List<String>> map = new HashMap<>();
      
      map.put("abc", List.of("a", "b", "c"));
      map.put("defg", List.of("d", "e", "f", "g"));
      map.put("hijkl", List.of("h", "i", "j", "k", "l"));
      
      final 
         Map<String, Integer> 
         //var 
         counts = 
            map
               .entrySet()
               .stream()
               .collect(Collectors.toMap(Map.Entry::getKey, (each) -> each.getValue().size()))
               ;
         
      counts.entrySet().forEach(System.out::println);
   
   }
   
   private void soq_20221217() // https://stackoverflow.com/questions/74838895/java8-flatmap-mapstring-liststring-to-mapstring-integer-where-the-int
   {
   
      //https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summingInt-java.util.function.ToIntFunction-
      final Map<String, List<String>> map = new HashMap<>();
      
      map.put("abc", List.of("a", "b", "c"));
      map.put("defg", List.of("d", "e", "f", "g"));
      map.put("hijkl", List.of("h", "i", "j", "k", "l"));
      
      final 
         Map<String, Integer> 
         //var 
         counts = 
            map
               .entrySet()
               .stream()
               .flatMap(each -> Stream.of(Map.entry(each.getKey(), each.getValue().size())))
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
               ;
         
      counts.entrySet().forEach(System.out::println);
   
   }
   
   private void soq_20221209()
   {
   
   //https://stackoverflow.com/questions/74749257/how-to-convert-a-hashset-of-strings-to-hashmap-of-sub-strings-using-java-streams
      HashSet<String> hs = new LinkedHashSet<>(Arrays.asList(
         "('300','3000025863',9,'NB','B')",
         "('300','25863',10,'NB','B')",
         "('300','3000025863',9,'NB','XXXXXXXX')",  //Duplicated row, latest value
         "('300','3000025863',8,'NB','B')"));
   
      HashMap<String, String> hm = new LinkedHashMap<>(); //HasMap key = composite key of first the 3 elements
      int numKeys = 3;   //The number of elements in the composite key. Has to be variable
   //Is there a better way for convert HashSet to HashMap?
      hs.forEach( 
         s -> {
            String sKey = "";
            String[] aux = s.split(",",numKeys+1);
            for (int i=0; i<numKeys; i++){
               sKey = sKey+aux[i]+",";
            }
            hm.put(sKey,aux[numKeys]);
         });
   
      HashSet<String> hsResult = new HashSet<>();  //HashSet without duplicates
      hm.forEach( 
         (key, value) -> {
            hsResult.add(key+value);
         });
      System.out.println("-----------Result-----------");
      for (String s:hsResult) {
         System.out.println(s);
      }
   
   }

}