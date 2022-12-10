import java.util.*;

public class SOQ
{

   public static void main(String[] args)
   {
   
      new SOQ();
   
   }

   public SOQ()
   {
   
      this.soq_20221209();
   
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