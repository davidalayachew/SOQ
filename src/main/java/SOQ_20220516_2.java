public class SOQ_20220516_2
{

   public static void main(String[] args)
   {
   
      final String bestThing = "The best thing about a boolean is even if you are wrong you are only off by a bit";
   
      String[] wordArray = bestThing.split(" ");
      int index = 0;
      for( int i = 0 ; i < wordArray.length; i++){
         if (wordArray[i].equals("only")) {
            index = i;
         
         }
      
      }
      System.out.println(index);
   
   }

}