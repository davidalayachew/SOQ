public class CR_20220523
{

   enum Size
   {
   
      TALL,
      GRANDE,
      VENTI,
      TRENTA,
      ;
   
   }

   enum Creamer implements Priceable {
      NONE, 
      MILK, 
      HALF_N_HALF,
      ;
   }

   interface Priceable {
      
   }

}
