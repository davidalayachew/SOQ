import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class ReworkedRecipeEngine
{

   public static void main(String[] args)
   {
   
      System.out.println("Hello World");
   
   }
   
   public enum Ingredient
   {
   
      APPLE,
      CHERRY,
      CARROT,
      FLOUR,
      SUGAR,
      ;
   
   }
   
   public enum Recipe
   {
   
   
   
   }
   
   public class IngredientsBag
   {
   
      private final Map<Ingredient, Integer> bag = new EnumMap<>(Ingredient.class);
   
   }

}
