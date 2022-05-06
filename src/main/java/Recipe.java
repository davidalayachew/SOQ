import java.util.EnumMap;
import java.util.Map;

public enum Recipe
{

   APPLE_JAM(
      Map.of(
         Ingredient.APPLE, 3
      )
   ),
   
   APPLE_JELLY(
      Map.of(
         Ingredient.APPLE, 2
      )
   ),
   
   APPLE_SMOOTHIE(
      Map.of(
         Ingredient.APPLE, 2
      )
   ),
   
   APPLE_TART(
      Map.of(
         Ingredient.APPLE, 1,
         Ingredient.FLOUR, 1
      )
   ),
   
   APPLE_PIE(
      Map.of(
         Ingredient.APPLE, 2,
         Ingredient.FLOUR, 1,
         Ingredient.SUGAR, 2
      )
   ),
   
   CHERRY_JAM(
      Map.of(
         Ingredient.CHERRY, 3
      )
   ),
   
   CHERRY_JELLY(
      Map.of(
         Ingredient.CHERRY, 2
      )
   ),
   
   CHERRY_PIE(
      Map.of(
         Ingredient.CHERRY, 2,
         Ingredient.FLOUR, 3,
         Ingredient.SUGAR, 2
      )
   ),
   
   CHERRY_TART(
      Map.of(
         Ingredient.CHERRY, 1,
         Ingredient.FLOUR, 1,
         Ingredient.SUGAR, 1
      )
   ),
   
   CARROT_JUICE(
      Map.of(
         Ingredient.CARROT, 2
      )
   ),
   
   CARROT_CAKE(
      Map.of(
         Ingredient.CARROT, 1,
         Ingredient.FLOUR, 1,
         Ingredient.SUGAR, 1
      )
   ),
   
   CARROT_POTAGE(
      Map.of(
         Ingredient.CARROT, 2,
         Ingredient.FLOUR, 1
      )
   ),
   ;
   
   private final EnumMap<Ingredient, Integer> requiredIngredients;
   
   Recipe(Map<Ingredient, Integer> parameter)
   {
   
      requiredIngredients = new EnumMap<>(parameter);
   
   }
   
   public boolean canMakeRecipeWith(ShowRecipesNew sr)
   {
   
      for (Map.Entry<Ingredient, Integer> each : requiredIngredients.entrySet())
      {
      
         Ingredient ingredient = each.getKey();
         int howMuchWeHave = sr.getIngredientCount(ingredient);
         int howMuchWeNeed = requiredIngredients.get(ingredient);
      
         if (howMuchWeHave < howMuchWeNeed)
         {
         
            return false;
         
         }
      
      }
      
      return true;
   
   }

   public String cookRecipeWith(ShowRecipesNew sr)
   {
   
      for (Map.Entry<Ingredient, Integer> each : requiredIngredients.entrySet())
      {
      
         Ingredient ingredient = each.getKey();
         int currentNum = sr.getIngredientCount(ingredient);
         int requiredAmount = requiredIngredients.get(ingredient);
      
         sr.setIngredientCount(ingredient, currentNum - requiredAmount);
      
      }
      
      return name();
   
   }
   
   public static boolean canCookAnything(ShowRecipesNew sr)
   {
   
      for (Recipe each : Recipe.values())
      {
      
         if (each.canMakeRecipeWith(sr))
         {
         
            return true;
         
         }
      
      }
      
      return false;
      
   }

}