package io.github.davidalayachew;

import java.util.Arrays;

/** Solution. https://stackoverflow.com/questions/71407575/it-there-better-way-to-cast-int-to-enum-in-java */
public class SOQ_20220406_0138
{

   /**
    * 
    * Main method.
    * 
    * @param   args  commandline arguments, should they be needed.
    * 
    */
   public static void main(String[] args)
   {
   
      interface Type
      {
      
         int value();
         
         static <E extends Enum<E> & Type> E fromValue(Class<E> clazz, final int value)
         {
         
            return
               Arrays.stream(clazz.getEnumConstants())
                  .parallel()
                  .filter(each -> each.value() == value)
                  .findAny()
                  .orElseThrow()
                  ;
         
         }
      
      }
      
      
      enum Enum1 implements Type
      {
      
         A(1),
         B(5),
         C(10),
         ;
         
         private final int value;
         
         Enum1(int value)
         {
         
            this.value = value;
         
         }
         
         public int value()
         {
         
            return this.value;
         
         }
      
      }
      
      enum Enum2 implements Type
      {
      
         D(1),
         E(20),
         F(30),
         ;
      
         private final int value;
         
         Enum2(int value)
         {
         
            this.value = value;
         
         }
         
         public int value()
         {
         
            return this.value;
         
         }
      
      }
   
   }

}
