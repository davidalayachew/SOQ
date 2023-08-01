import java.util.Scanner;

//object class
@SuppressWarnings({"this-escape"})
public class ShowRecipes {

   public static void main (String [] args){
      final ShowRecipes first = new ShowRecipes();
      System.out.println("FIRST");
      first.input();
      first.compareRecipe();
      first.WantToCook();
        
      first.Selection();
        
   }
    
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

   public void input() { // user inputs ingredient counts
   
        //Asks user for ingredients on-hand
      System.out.println("Please enter how many of each ingredient you have:");
   
      System.out.print(ingredientsNames[0]);
      int apples = kbd.nextInt();
      setApples(apples);
   
      System.out.print(ingredientsNames[1]);
      int cherries = kbd.nextInt();
      setCherries(cherries);
   
      System.out.print(ingredientsNames[2]);
      int carrots = kbd.nextInt();
      setCarrots(carrots);
   
      System.out.print(ingredientsNames[3]);
      int flour = kbd.nextInt();
      setFlour(flour);
   
      System.out.print(ingredientsNames[4]);
      int sugar = kbd.nextInt();
      setSugar(sugar);
   }//end user ingredient input

   public void IngredientCount() { //Lists ingredient input counts
      System.out.println("According to your input, you have:");
   
      System.out.println("Ingredient\tValue");
      for(int counter=0;counter<ingredients.length;counter++) {
         System.out.println(ingredientsNames[counter] + "\t\t" + ingredients[counter]);
      }//end for loop
   }//end ingredient listing
    
    
   public void Selection(){ //Can access every method in class except CookAgain
      System.out.println("What would you like to do now? Enter a number to select. \n1. Cook something \n2. See ingredients \n3. See available recipes \n4. Modify ingredient amounts");
        
        //1. Cook something -- WantToCook()
        //2. See ingredient list -- IngredientCount()
        //3. See available recipes -- compareRecipe()
        //4. Modify total ingredient amounts -- input()
        //maybe  put this in ShowRecipes, create object for each to put in own classes
        
      int selection = kbd.nextInt();
        
      switch (selection) {
         case 1:
            WantToCook();
            break;
         case 2: 
            IngredientCount();
            break;
         case 3: 
            compareRecipe();
            break;
         case 4:
            input();
            break;
         default:
            System.out.println("Invalid");
            return;
      }}
    
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

   public void compareRecipe() {
   
      System.out.println("According to the input, you can make:");
   
        //Apples
      if (ingredients[0] >= 3) {
         System.out.println(RecipeNamesA[0]); //Apple Jam
      }
      if (ingredients[0] >= 2) {
         System.out.println(RecipeNamesA[1]); //Apple Jelly
         System.out.println(RecipeNamesA[2]); //Apple Smoothie
      }
   
      if (ingredients[0] >= 1 && ingredients[3] >= 1){
         System.out.println(RecipeNamesA[3]); //Apple Tart
      }
   
      if (ingredients[0] >= 2 && ingredients[4] >= 2 && ingredients[3] >= 1){
         System.out.println(RecipeNamesA[4]); //Apple Pie
      }
      if (ingredients[0] == 0) {
         System.out.println("No apple recipes");
      }
   
        //Cherries
      if (ingredients[1] >= 3) {
         System.out.println(RecipeNamesCh[0]); //Cherry Jam
      }
      if (ingredients[1] >= 2) {
         System.out.println(RecipeNamesCh[1]); //Cherry Jelly
      }
   
      if (ingredients[1] >= 2 && ingredients[3] >= 3 && ingredients[4] >= 2) {
         System.out.println(RecipeNamesCh[2]); //Cherry Pie
      }
      if (ingredients[1] >= 1 && ingredients[3] <= 1 && ingredients[4] >= 1) {
         System.out.println(RecipeNamesCh[3]); //Cherry Tart
      }
      if (ingredients[1] == 0) {
         System.out.println("No cherry recipes");
      }
   
        //Carrots
      if (ingredients[2] >= 2) {
         System.out.println(RecipeNamesCa[0]); //Carrot juice 
      }
      if (ingredients[2] >= 2 && ingredients[3] >= 1) {
         System.out.println(RecipeNamesCa[2]); //Carrot potage
      }
   
      if (ingredients[2] >= 1 && ingredients[3] >= 1 && ingredients[4] >= 1){
         System.out.println(RecipeNamesCa[1]); //Carrot cake
      }   
      if (ingredients[2] == 0) {
         System.out.println("No carrot recipes");
      }
        
   }

   private String RecipeNamesA[] = {"1. Apple Jam", "2. Apple Jelly", "3. Apple Smoothie", "4. Apple Tart", "5. Apple Pie"}; //Apple recipes
   private String RecipeNamesCh[] = {"6. Cherry Jam", "7. Cherry Jelly", "8. Cherry Pie", "9. Cherry Tart"}; //Cherry recipes
   private String RecipeNamesCa[] = {"10. Carrot Juice", "11. Carrot Cake","12. Carrot Potage"}; //Carrot recipes

   public void WantToCook() { //Enter recipe number - deducts ingredients
        
      System.out.println("What would you like to cook? Please enter the recipe number.");
      int recipeNum = kbd.nextInt();
   
      switch(recipeNum) {
      
         case 1: //Apple jam
            setApples(getApples()-3);
            System.out.println(RecipeNamesA[0]);
            break;
         case 2: //Apple jelly
            System.out.println(RecipeNamesA[1]);
            setApples(getApples()-2);
            break;
         case 3://Apple Smoothie
            System.out.println(RecipeNamesA[2]);
            setApples(getApples()-2);
            break;
         case 4: //Apple Tart
            System.out.println(RecipeNamesA[3]);
            setApples(getApples()-1);
            setFlour(getFlour()-1);
            setSugar(getSugar()-1);
            break;
         case 5: //Apple Pie
            System.out.println(RecipeNamesA[4]);
            setApples(getApples()-2);
            setFlour(getFlour()-3);
            setSugar(getSugar()-2);
            break;
         case 6: //Cherry Jam
            System.out.println(RecipeNamesCh[0]);
            setCherries(getCherries()-3);
            break;
         case 7: //Cherry Jelly
            System.out.println(RecipeNamesCh[1]);
            setCherries(getCherries()-2);
            break;
         case 8: //Cherry Pie
            System.out.println(RecipeNamesCh[2]);
            setCherries(getCherries()-2);
            setFlour(getFlour()-3);
            setSugar(getSugar()-2);
            break;
         case 9: //Cherry Tart
            System.out.println(RecipeNamesCh[3]);
            setCherries(getCherries()-1);
            setFlour(getFlour()-1);
            setSugar(getSugar()-1);
            break;
            
         case 10: //Carrot juice 
            System.out.println(RecipeNamesCa[0]);
            setCarrots(getCarrots()-2);
            break;
         case 11: //Carrot cake
            System.out.println(RecipeNamesCa[1]);
            setCarrots(getCarrots()-1);
            setFlour(getFlour()-1);
            setSugar(getSugar()-1);
            break;
      
         case 12: //Carrot potage
            System.out.println((RecipeNamesCa[2]));
            setCarrots(getCarrots()-2);
            setFlour(getFlour()-1); 
            break;
            
         default:
            System.out.println("Invalid entry.");
      }//End recipeNum switch
        
      System.out.println("You have cooked a thing! Good job!");
      CookAgain(); //cook again? prompt
   } //end WantToCook method

   public void CookAgain() {   
    
      System.out.println("Would you like to cook again? Please type yes or no");
      kbd.nextLine();
      String answer = kbd.nextLine();
   
      switch (answer) {
      
         case "yes":
            if (ingredients[0] >= 1 || ingredients[1] >= 1 || ingredients[2] >= 1) { //no apples, cherries, or carrots
               System.out.println("You've used some ingredients. Let's see what you have now.");
               IngredientCount();
               compareRecipe();
               WantToCook();
               break;
            }
            else {
               System.out.println("You don't have enough ingredients left to cook anything.");
               break;
            }
         case "no":
            System.out.println("Ok, done cooking. Enjoy!");
            break;
         default:
            System.out.println("Invalid answer please try again!");
      
      }// End answer switch
    
      kbd.close(); // close scanner
   }
}