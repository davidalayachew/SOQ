public class Driver
{

   private ShowRecipesNew sr;
   
   public Driver(ShowRecipesNew parameter)
   {
   
      sr = parameter;
   
   }

   public static void main (String [] args){
      final ShowRecipesNew first = new ShowRecipesNew();
      System.out.println("FIRST");
      
      final UserPrompt prompt = new UserPrompt();
      
      final Driver driver = new Driver(first);
      driver.input(prompt);
      first.compareRecipe(prompt);
      driver.WantToCook(prompt);
   
      driver.selection(prompt);
        
   }
      
   public void selection(UserPrompt prompt){ //Can access every method in class except CookAgain
     
     //1. Cook something -- WantToCook()
     //2. See ingredient list -- IngredientCount()
     //3. See available recipes -- compareRecipe()
     //4. Modify total ingredient amounts -- input()
     //maybe  put this in ShowRecipes, create object for each to put in own classes
     
      int userChoice = 
         prompt.askForNumber(
            "What would you like to do now? Enter a number to select. \n1. Cook something \n2. See ingredients \n3. See available recipes \n4. Modify ingredient amounts"
         );
     
      switch (userChoice) {
         case 1:
            WantToCook(prompt);
            break;
         case 2: 
            sr.IngredientCount(prompt);
            break;
         case 3: 
            sr.compareRecipe(prompt);
            break;
         case 4:
            input(prompt);
            break;
         default:
            System.out.println("Invalid");
            return;
      }
      
   }

   public void WantToCook(UserPrompt prompt) { //Enter recipe number - deducts ingredients
     
      int recipeNum = prompt.askForNumber("What would you like to cook? Please enter the recipe number.");
      
      if (recipeNum >= Recipe.values().length || recipeNum < 0)
      {
      
         prompt.print("invalid entry");
      
      }
      
      else
      {
      
         final Recipe recipeToMake = Recipe.values()[recipeNum];
      
         if (recipeToMake.canMakeRecipeWith(sr))
         {
         
            prompt.print(recipeToMake.cookRecipeWith(sr));
         
         }
         
         else
         {
         
            prompt.print("not enough ingredients");
         
         }
      
      }
      
      prompt.print("You have cooked a thing! Good job!");
      CookAgain(prompt); //cook again? prompt
   } //end WantToCook method

   public void input(UserPrompt prompt) { // user inputs ingredient counts
   
     //Asks user for ingredients on-hand
      prompt.print("Please enter how many of each ingredient you have:");
      
      for (Ingredient each : Ingredient.values())
      {
      
         int count = prompt.askForNumber(each.name());
      
         sr.setIngredientCount(each, count);
      
      }
   
   }//end user ingredient input

   public void CookAgain(UserPrompt prompt) {   
   
      int answer = prompt.askForNumber("Would you like to cook again? 1 = yes, 2 = no - ");
   
      switch (answer) {
      
         case 1: //which means yes
            if (Recipe.canCookAnything(sr)) {
               prompt.print("You've used some ingredients. Let's see what you have now.");
               sr.IngredientCount(prompt);
               sr.compareRecipe(prompt);
               WantToCook(prompt);
               break;
            }
            else {
               prompt.print("You don't have enough ingredients left to cook anything.");
               break;
            }
         case 2: //which means no
            prompt.print("Ok, done cooking. Enjoy!");
            break;
         default:
            prompt.print("Invalid answer please try again!");
      
      }// End answer switch
   
   }
   
}
