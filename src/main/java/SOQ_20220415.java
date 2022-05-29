import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SOQ_20220415
{

   static class AnotherObject
   {
      
      public int useSomeAmount(Object o, int amount)
      {
         
         return new Random().nextInt();
         
      }
      
   }
   
   public static void main(String[] args)
   {
   
      final List<Object> objects = List.of(1, 2, 3, 4, 5, 6);
      final List<AnotherObject> otherObjects = List.of(new AnotherObject());
      
      attempt2(objects, otherObjects);
      
      System.out.println("Running");
   
   }
   
   public static void attempt1()
   {
   // 
      // AtomicInteger amount = new AtomicInteger(1000);
   //    
      // objects.forEach(
         // (o) -> {
            // otherObjects.forEach(
               // (a) -> {
               //    //amount = amount - thisMethodDoesntMatter(o,amount);
               // });
         // });
   // 
   }
   
   public static void attempt2(List<Object> objects, List<AnotherObject> otherObjects)
   {
   
      int amount = 0;
   
      objects.forEach(
         (o) -> {
            otherObjects.stream()
               //.map(a -> a.useSomeAmount(o, amount))
               ;
         });
   
      for (Object o : objects) {
         for (AnotherObject a : otherObjects) {
            amount = amount - a.useSomeAmount(o,amount);
         }
      }
   
   
   
   }
   
}
