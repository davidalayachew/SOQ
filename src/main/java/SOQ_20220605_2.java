import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SOQ_20220605_2
{

   private sealed interface Value
   {
   
      public static List<Value> asList(Object... uncheckedValues)
      {
      
         Objects.requireNonNull(uncheckedValues);
         
         final List<Value> values = new ArrayList<>();
         
         for (var each : uncheckedValues)
         {
         
            //values.add(
               //switch(each)
               //{
               
                  //case null         -> throw new NullPointerException("No nulls allowed");
                  //case Integer i    -> new Argument(i);
                  //case String s     -> Operation.valueOf(s.toUpperCase());
                  //case Argument a   -> a;
                  //case Operation o  -> o;
                  //default           -> throw new IllegalArgumentException("Unexpected type! each = " + each);
               
               //}
            //);
         
         }
         
         return values;
      
      }
   
   }
   
   private enum Operation implements Value
   {
      
      SOME_OP,
      ANOTHER_OP,
      DIFFERENT_OP,
      RANDOM_OP,
      ;
      
   }
   
   private record Argument(int num) implements Value {}

   public static void main(String[] args)
   {
   
      final List<Value> opsAndArgs =
         Value.asList
         (
            "SOME_OP",
            42,
            "ANOTHER_OP",
            99,
            3,
            "DIFFERENT_OP",
            "RANDOM_OP",
            14
         );

         final int result = calculateResult(opsAndArgs);
         
         System.out.println(result);

   }
   
   private static int calculateResult(List<Value> opsAndArgs)
   {
   
         int i = 0;

         //while (i < opsAndArgs.size())
         //{
         
            //if (opsAndArgs.get(i) instanceof Operation operation)
            //{
            
               //i = 
                  //switch (operation)
                  //{
               
                     //case SOME_OP -> 
                     //{
                     
                        //handleSomeOp(
                              //opsAndArgs.get(i + 1)
                           //);
      
                        //yield i + 2;
                     
                     //}
                     
                     //case ANOTHER_OP ->
                     //{
                     
                        //handleAnotherOp(
                              //opsAndArgs.get(i + 1),
                              //opsAndArgs.get(i + 2)
                           //);
      
                        //yield i + 3;
                     
                     //}
                  
                     //case DIFFERENT_OP ->
                     //{
                     
                        //handleDifferentOp();
      
                        //yield i + 1;
                     
                     //}
                     
                     //case RANDOM_OP ->
                     //{
                        
                        //yield i + 4;
                        //throw new UnsupportedOperationException("This has not been implemented yet.");
                     
                     //}
                  
                  //};
               
            //}
         
         //}
         
         return i;
   
   }
   
   private static void handleSomeOp(Value value)
   {
   
      //do something
   
   }

   private static void handleAnotherOp(Value value1, Value value2)
   {
   
      //do something
   
   }

   private static void handleDifferentOp()
   {
   
      //do something
   
   }

}
