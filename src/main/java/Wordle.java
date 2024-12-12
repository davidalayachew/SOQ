import java.util.ArrayList;
import java.util.Scanner;

public class Wordle {
   private final String wordToGuess;
   private ArrayList<Character> availableLetters = new ArrayList<>();
   private String playerInput;
   private int tries;
   private Scanner input;

   public static void main(String[] args) {
      Wordle wordle = new Wordle("BIKES");
      wordle.init();
   }

   public String getWordToGuess() {
      return wordToGuess;
   }

   public ArrayList<Character> getAvailableLetters() {
      return availableLetters;
   }

   public void setAvailableLetters(ArrayList<Character> availableLetters) {
      this.availableLetters = availableLetters;
   }

   public String getPlayerInput() {
      return playerInput;
   }

   public void setPlayerInput(String playerInput) {
      this.playerInput = playerInput;
   }

   public int getTries() {
      return tries;
   }

   public void setTries(int tries) {
      this.tries = tries;
   }

   public Scanner getInput() {
      return input;
   }

   public void setInput(Scanner input) {
      this.input = input;
   }

   public Wordle(String wordToGuess){
      this.wordToGuess = wordToGuess.toUpperCase();
      for (char letter = 'A'; letter <= 'Z'; letter++) {
         this.availableLetters.add(letter);
      }
      this.input = new Scanner(System.in);
      this.tries = 5;
   }



   public void init() {
      boolean win = false;
      while (!win){
         insertPlayerInput();
         win = checkWord();
      }
   }


   public boolean checkWord(){
      if(this.wordToGuess.equalsIgnoreCase(playerInput)){
         System.out.println("GG !");
         return true;
      } else if (getTries() == 0) {
         System.out.println("Your number of tries is finished");
         System.out.println("The correct answer was : " + getWordToGuess());
         return false;
      
      } else{
         System.out.println("Try again !");
         setTries(getTries()-1);
         System.out.println("Remaining Tries : " + getTries());
         removeOtherLetters();
         checkRightPositons();
         return false;
      }
   
   }

   public void checkRightPositons(){
      for (int i = 0; i < getWordToGuess().length(); i++) {
      
         if(getPlayerInput().charAt(i) == getWordToGuess().charAt(i)){
            System.out.println("The letter " + getPlayerInput().charAt(i) + " is in the right place");
         }
      }
   }

   public void removeOtherLetters(){
      for (int i = 0; i < getPlayerInput().length(); i++) {
         char inputLetter = getPlayerInput().charAt(i);
      
      
         if (!getWordToGuess().contains(String.valueOf(inputLetter))) {
         
            getAvailableLetters().remove(Character.valueOf(inputLetter));
         }
      }
   }

   public void insertPlayerInput(){
      do {
         System.out.println("The available letters are : ");
         for (int i = 0; i < getAvailableLetters().size(); i++) {
            System.out.print(getAvailableLetters().get(i) + " ");
         }
         System.out.println("\nGuess the word");
         setPlayerInput(getInput().nextLine().toUpperCase());
         if(getPlayerInput().length() != 5){
            System.out.println("Your input should contain five letters only !");
         }
      }while(getPlayerInput().length() != 5);
   }
}
