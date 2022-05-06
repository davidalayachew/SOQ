public enum Ingredient
{

   APPLE(1),
   CHERRY(1),
   CARROT(1),
   FLOUR(0),
   SUGAR(0),
   ;
   
   private final int defaultValue;
   
   Ingredient(int input)
   {
   
      defaultValue = input;
   
   }
   
   public int defaultValue()
   {
   
      return defaultValue;
   
   }

}
