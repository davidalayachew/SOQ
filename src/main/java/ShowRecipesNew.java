import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

//object class
public class ShowRecipesNew {

 //ingredients array - setters will set
   private EnumMap<Ingredient, Integer> ingredients = new EnumMap<>(Ingredient.class);

 //Basic constructor
   public ShowRecipesNew() {
      for (Ingredient each : Ingredient.values())
      {
         ingredients.put(each, each.defaultValue());
      }
   }

 //overloaded constructor that covers each field
   public ShowRecipesNew(int apples, int cherries, int carrots, int flour, int sugar) {
      this();
      System.out.println("overloaded");
      setIngredientCount(Ingredient.APPLE, apples);
      setIngredientCount(Ingredient.CHERRY, cherries);
      setIngredientCount(Ingredient.CARROT, carrots);
      setIngredientCount(Ingredient.FLOUR, flour);
      setIngredientCount(Ingredient.SUGAR, sugar);
   }


   public void IngredientCount(UserPrompt prompt) { //Lists ingredient input counts
      prompt.print("According to your input, you have:");
   
      prompt.print("Ingredient\tValue");
      for(Map.Entry<Ingredient, Integer> each : ingredients.entrySet()) {
         prompt.print(each.getKey() + "\t\t" + each.getValue());
      }//end for loop
   }//end ingredient listing
 
   public void setIngredientCount(Ingredient ingredient, int count)
   {
   
      ingredients.put(ingredient, count);
   
   }
 
   public int getIngredientCount(Ingredient ingredient)
   {
   
      return ingredients.get(ingredient);
   
   }
 
   public void compareRecipe(UserPrompt prompt) {
   
      prompt.print("According to the input, you can make:");
      
      for (Ingredient each : Ingredient.values())
      {
      
         Integer count = getIngredientCount(each);
      
         if (count == null || count == 0)
         {
         
            prompt.print("No " + each.name().toLowerCase() + " recipes");
         
         }
      
      }
      
      for (Recipe each : Recipe.values())
      {
      
         if (each.canMakeRecipeWith(this))
         {
         
            prompt.print(each.ordinal() + " " + each.name());
         
         }
      
      }
   
   }

}
