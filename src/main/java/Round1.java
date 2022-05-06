import java.util.Scanner;

public interface Round1
{


   public static void main (String [] args){
      final ShowRecipes first = new ShowRecipes();
      System.out.println("FIRST");
      
      final UserPrompt prompt = new UserPrompt();
      
      final Driver driver = new Driver(first);
      driver.input(prompt);
      first.compareRecipe(prompt);
      driver.WantToCook(prompt);
   
      driver.selection(prompt);
        
   }
      
   public class UserPrompt
   {
   
      final Scanner kbd = new Scanner(System.in);
   
      public int askForNumber(String prompt)
      {
      
         //Print your prompt to the command line -- I added a little extra at the end for simple reading
         System.out.print(prompt + " - ");
         
         return kbd.nextInt();
         
      }
      
      public void print(String message)
      {
      
         System.out.println(message);
      
      }
   
   }
   
   public class Driver
   {
   
      ShowRecipes sr;
      
      public Driver(ShowRecipes parameter)
      {
      
         sr = parameter;
      
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
         
         switch(recipeNum) {
         
            case 1: //Apple jam
               sr.setApples(sr.getApples()-3);
               prompt.print(sr.RecipeNamesA[0]);
               break;
            case 2: //Apple jelly
               prompt.print(sr.RecipeNamesA[1]);
               sr.setApples(sr.getApples()-2);
               break;
            case 3://Apple Smoothie
               prompt.print(sr.RecipeNamesA[2]);
               sr.setApples(sr.getApples()-2);
               break;
            case 4: //Apple Tart
               prompt.print(sr.RecipeNamesA[3]);
               sr.setApples(sr.getApples()-1);
               sr.setFlour(sr.getFlour()-1);
               sr.setSugar(sr.getSugar()-1);
               break;
            case 5: //Apple Pie
               prompt.print(sr.RecipeNamesA[4]);
               sr.setApples(sr.getApples()-2);
               sr.setFlour(sr.getFlour()-3);
               sr.setSugar(sr.getSugar()-2);
               break;
            case 6: //Cherry Jam
               prompt.print(sr.RecipeNamesCh[0]);
               sr.setCherries(sr.getCherries()-3);
               break;
            case 7: //Cherry Jelly
               prompt.print(sr.RecipeNamesCh[1]);
               sr.setCherries(sr.getCherries()-2);
               break;
            case 8: //Cherry Pie
               prompt.print(sr.RecipeNamesCh[2]);
               sr.setCherries(sr.getCherries()-2);
               sr.setFlour(sr.getFlour()-3);
               sr.setSugar(sr.getSugar()-2);
               break;
            case 9: //Cherry Tart
               prompt.print(sr.RecipeNamesCh[3]);
               sr.setCherries(sr.getCherries()-1);
               sr.setFlour(sr.getFlour()-1);
               sr.setSugar(sr.getSugar()-1);
               break;
            
            case 10: //Carrot juice 
               prompt.print(sr.RecipeNamesCa[0]);
               sr.setCarrots(sr.getCarrots()-2);
               break;
            case 11: //Carrot cake
               prompt.print(sr.RecipeNamesCa[1]);
               sr.setCarrots(sr.getCarrots()-1);
               sr.setFlour(sr.getFlour()-1);
               sr.setSugar(sr.getSugar()-1);
               break;
         
            case 12: //Carrot potage
               prompt.print((sr.RecipeNamesCa[2]));
               sr.setCarrots(sr.getCarrots()-2);
               sr.setFlour(sr.getFlour()-1); 
               break;
            
            default:
               prompt.print("Invalid entry.");
         }//End recipeNum switch
        
         prompt.print("You have cooked a thing! Good job!");
         CookAgain(prompt); //cook again? prompt
      } //end WantToCook method
   
      public void input(UserPrompt prompt) { // user inputs ingredient counts
      
        //Asks user for ingredients on-hand
         prompt.print("Please enter how many of each ingredient you have:");
      
         sr.setApples(prompt.askForNumber(sr.ingredientsNames[0]));
      
         sr.setCherries(prompt.askForNumber(sr.ingredientsNames[1]));
      
         sr.setCarrots(prompt.askForNumber(sr.ingredientsNames[2]));
      
         sr.setFlour(prompt.askForNumber(sr.ingredientsNames[3]));
      
         sr.setSugar(prompt.askForNumber(sr.ingredientsNames[4]));
      }//end user ingredient input
   
      public void CookAgain(UserPrompt prompt) {   
      
         int answer = prompt.askForNumber("Would you like to cook again? 1 = yes, 2 = no - ");
      
         switch (answer) {
         
            case 1: //which means yes
               if (sr.ingredients[0] >= 1 || sr.ingredients[1] >= 1 || sr.ingredients[2] >= 1) { //no apples, cherries, or carrots
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

//object class
   public class ShowRecipes {
   
    
      Scanner kbd = new Scanner(System.in);
   
    //ingredient names array
      public String ingredientsNames[] = {"Apples", "Cherry", "Carrot", "Flour ", "Sugar "};
   
    //ingredients array - setters will set
      public int[] ingredients = new int[5];
   
    //Basic constructor
      public ShowRecipes() {
         ingredients[0] = 1; //apple
         ingredients[1] = 1; //cherry
         ingredients[2] = 1;//carrot
         ingredients[3] = 0;//flour
         ingredients[4] = 0;//sugar
      }
   
    //overloaded constructor that covers each field
      public ShowRecipes(int apples, int cherries, int carrots, int flour, int sugar) {
         System.out.println("overloaded");
         setApples(apples);
         setCherries(cherries);
         setCarrots(carrots);
         setFlour(flour);
         setSugar(sugar);
      }
   
      public void IngredientCount(UserPrompt prompt) { //Lists ingredient input counts
         prompt.print("According to your input, you have:");
      
         prompt.print("Ingredient\tValue");
         for(int counter=0;counter<ingredients.length;counter++) {
            prompt.print(ingredientsNames[counter] + "\t\t" + ingredients[counter]);
         }//end for loop
      }//end ingredient listing
    
    
    //Setters - set from input()
    //Apple setter
      public void setApples(int apples){
         ingredients[0] = apples;
      }
    //Cherry setter
      public void setCherries(int cherries){
         ingredients[1] = cherries;
      }
    //Carrot setter
      public void setCarrots(int carrots){
         ingredients[2]= carrots;
      }
    //Flour setter
      public void setFlour(int flour){
         ingredients[3] = flour;
      }
    //Sugar setter
      public void setSugar(int sugar){
         ingredients[4] = sugar;
      }
    
    //Getters
    //Apple getter
      public int getApples(){
         return ingredients[0];
      }
    //Cherry getter
      public int getCherries(){
         return ingredients[1];
      }
    //Carrot getter
      public int getCarrots(){
         return ingredients[2];
      }
    //Flour getter
      public int getFlour(){
         return ingredients[3];
      }
    //Sugar getter
      public int getSugar(){
         return ingredients[4];
      }
   
      public void compareRecipe(UserPrompt prompt) {
      
         prompt.print("According to the input, you can make:");
      
        //Apples
         if (ingredients[0] >= 3) {
            prompt.print(RecipeNamesA[0]); //Apple Jam
         }
         if (ingredients[0] >= 2) {
            prompt.print(RecipeNamesA[1]); //Apple Jelly
            prompt.print(RecipeNamesA[2]); //Apple Smoothie
         }
      
         if (ingredients[0] >= 1 && ingredients[3] >= 1){
            prompt.print(RecipeNamesA[3]); //Apple Tart
         }
      
         if (ingredients[0] >= 2 && ingredients[4] >= 2 && ingredients[3] >= 1){
            prompt.print(RecipeNamesA[4]); //Apple Pie
         }
         if (ingredients[0] == 0) {
            prompt.print("No apple recipes");
         }
      
        //Cherries
         if (ingredients[1] >= 3) {
            prompt.print(RecipeNamesCh[0]); //Cherry Jam
         }
         if (ingredients[1] >= 2) {
            prompt.print(RecipeNamesCh[1]); //Cherry Jelly
         }
      
         if (ingredients[1] >= 2 && ingredients[3] >= 3 && ingredients[4] >= 2) {
            prompt.print(RecipeNamesCh[2]); //Cherry Pie
         }
         if (ingredients[1] >= 1 && ingredients[3] <= 1 && ingredients[4] >= 1) {
            prompt.print(RecipeNamesCh[3]); //Cherry Tart
         }
         if (ingredients[1] == 0) {
            prompt.print("No cherry recipes");
         }
      
        //Carrots
         if (ingredients[2] >= 2) {
            prompt.print(RecipeNamesCa[0]); //Carrot juice 
         }
         if (ingredients[2] >= 2 && ingredients[3] >= 1) {
            prompt.print(RecipeNamesCa[2]); //Carrot potage
         }
      
         if (ingredients[2] >= 1 && ingredients[3] >= 1 && ingredients[4] >= 1){
            prompt.print(RecipeNamesCa[1]); //Carrot cake
         }   
         if (ingredients[2] == 0) {
            prompt.print("No carrot recipes");
         }
        
      }
   
      private String RecipeNamesA[] = {"1. Apple Jam", "2. Apple Jelly", "3. Apple Smoothie", "4. Apple Tart", "5. Apple Pie"}; //Apple recipes
      private String RecipeNamesCh[] = {"6. Cherry Jam", "7. Cherry Jelly", "8. Cherry Pie", "9. Cherry Tart"}; //Cherry recipes
      private String RecipeNamesCa[] = {"10. Carrot Juice", "11. Carrot Cake","12. Carrot Potage"}; //Carrot recipes
   
   }

}