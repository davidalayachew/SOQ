public class Kvarteret {

   public static enum Drink
   {
   
      HANSA(74),
      GRANS(103),
      VESTKYST(110),
      //GT(Ingredient.GIN.price + Ingredient.TONIC.price + Ingredient.MINT.price)
      GT(price("GIN", "TONIC", "MINT")),
      BACARDI_SPECIAL((price("GIN")/2) + price("ROM", "GRENADINE", "LIME")),
      ;
   
      public final int price;
      
      Drink(int price)
      {
      
         this.price = price;
      
      }
      
      private static int price(String...ingredients)
      {
      
         int total = 0;
      
         for (String each : ingredients)
         {
         
            total += Ingredient.valueOf(each.toUpperCase()).price;
         
         }
      
         return total;
      
      }
   
   }
   
   public static enum Ingredient
   {
   
      ROM(65),
      GRENADINE(10),
      LIME(10),
      MINT(10),
      TONIC(20),
      GIN(85)
      ;
      
      public final int price;
      
      Ingredient(int price)
      {
      
         this.price = price;
      
      }
   
   }

   public int calculatePrice(Drink drink, boolean student, int amount)
   {
   
      if (amount > 2 && (drink == Drink.GT || drink == Drink.BACARDI_SPECIAL)) {
         throw new RuntimeException("Too many drinks, max 2.");
      }
   
      return
         switch (drink)
         {
         
            case HANSA:
            case GRANS:
            case VESTKYST:
               if (student)
               {
               
                  yield drink.price*9/10;
               
               }
            case GT:
            case BACARDI_SPECIAL:
               yield drink.price * amount;
         
         };
   
   }

}