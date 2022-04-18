import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class AgarwalGame extends AbstractGame{


//Dimensions of game window
   private static final int DEFAULT_WIDTH = 900;
   private static final int DEFAULT_HEIGHT = 600; 

//Starting Player coordinates
   private static final int STARTING_PLAYER_X = 0;
   private static final int STARTING_PLAYER_Y = 100;

//Score needed to win the game
   private static final int SCORE_TO_WIN = 500;

//Maximum that the game speed can be increased to
//(a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
   private static final int MAX_GAME_SPEED = 300;
//Interval that the speed changes when pressing speed up/down keys
   private static final int SPEED_CHANGE = 20;   
// Default that the game speed can be increased to.
   private static final int DEFAULT_GAME_SPEED = 100;


   private static final String BACKGROUND_FILE = "assets/background.gif";  
   private static final String INTRO_SPLASH_FILE = "assets/spash1.gif";  
   private static final String WINNER_IMAGE = "assets/winner.gif";  
   private static final String LOSER_IMAGE = "assets/lose.gif";

//Key pressed to advance past the splash screen
   public static final int ADVANCE_SPLASH_KEY = KeyEvent.VK_ENTER;

//Interval that Entities get spawned in the game window
//ie: once every how many ticks does the game attempt to spawn new Entities
   private static final int SPAWN_INTERVAL = 45;
//Maximum Entities that can be spawned on a single call to spawnEntities
   private static final int MAX_SPAWNS = 3;   
//A Random object for all your random number generation needs!
   public static final Random rand = new Random();
//Player's current score
   private int score;
// Player's  new speed.
   private int newSpeed = 5;



//Stores a reference to game's Player object for quick reference
//(This Player will also be in the displayList)
   private Player player;
   private Avoid avoid;
   private RareGet rare;
   private Get get;

  


   public AgarwalGame(){
      this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
   }
  
   public AgarwalGame(int gameWidth, int gameHeight){
      super(gameWidth, gameHeight);
   }


//Performs all of the initialization operations that need to be done before the game starts
   protected void preGame(){
      this.setBackgroundImage(BACKGROUND_FILE);
      this.setBackgroundColor(Color.black);
      this.setSplashImage(INTRO_SPLASH_FILE);
      player = new Player(STARTING_PLAYER_X, STARTING_PLAYER_Y);
      displayList.add(player); 
      score = 0;
   }

//Called on each game tick
   protected void updateGame(){
      this.setBackgroundImage(BACKGROUND_FILE);
    //scroll all scrollable Entities on the game board
      scrollEntities();   
    //Spawn new entities only at a certain interval
      if (ticksElapsed % SPAWN_INTERVAL == 0)            
         spawnEntities();
   
    //updates the title on the title bar of the game window
      updateTitle();
   }


    
 //updates the title bar of the game window 
   private void updateTitle(){
      super.setTitleText("HP: " + player.getHP() +" Score:  " + this.score);
   }


//Scroll all scrollable entities per their respective scroll speeds
   protected void scrollEntities(){
    //iterating through the arraylist of entities
      for (int i = 0; i < displayList.size(); i++){ 
         if(displayList.get(i) instanceof Scrollable){
            ((Scrollable) displayList.get(i)).scroll();
            if((displayList.get(i) instanceof Consumable) && (player.isCollidingWith(displayList.get(i)))){
               handleCollision((Consumable)displayList.get(i));
            }
         }
         for (int j = 0; j < displayList.size(); j++){ 
            if((displayList.get(j) instanceof Bullet)){
               Entity ent = checkCollision(displayList.get(j));
               if(ent != null)
                  bulletHandleCollision(ent,(Bullet)displayList.get(j));   
            }     
         }
      }
   }
   


//Spawn new Entities on the right edge of the game board
   protected void spawnEntities(){
   //creating a random position on y axis where the entities are being spawned.
      int  no_spawned = rand.nextInt(MAX_SPAWNS)+1;
   //creating a new array list to store the entity being spawned randomly
      ArrayList<Entity> store = new ArrayList<Entity>();
      for(int i = 0; i < no_spawned; i++){
        //randomising the frequency in which the entities are being spawned
         int entity_type = rand.nextInt(0,5)+1;
         if(entity_type == 1|| entity_type == 2 || entity_type == 3){
            avoid = new Avoid(super.getWindowWidth(),1);
            int y = rand.nextInt(super.getWindowHeight() - avoid.getHeight());
            avoid.setY(y);
            for (int j = 0; j < store.size(); j++){
               if(store.get(j).isCollidingWith(avoid)){
                  y = rand.nextInt(super.getWindowHeight()-avoid.getHeight());
                  avoid.setY(y);
                  j = -1;
               }
            }
            displayList.add(avoid); 
            store.add(avoid);
         }
         if(entity_type == 3|| entity_type == 4){
            get = new Get(super.getWindowWidth(),1);
            int y2 = rand.nextInt(super.getWindowHeight()-get.getHeight());
            get.setY(y2);
            for (int j = 0; j < store.size(); j++){
               if(store.get(j).isCollidingWith(get)){
                  y2 = rand.nextInt(super.getWindowHeight()-get.getHeight());
                  get.setY(y2);
                  j = -1;
               }
            }
            displayList.add(get); 
            store.add(get);
         }
         if(entity_type == 5){
            rare = new RareGet(super.getWindowWidth(),1);
            int y1 = rand.nextInt(super.getWindowHeight()-rare.getHeight());
            rare.setY(y1);
            for (int j = 0; j < store.size(); j++){
               if(store.get(j).isCollidingWith(rare)){
                  y1 = rand.nextInt(super.getWindowHeight() - rare.getHeight());
                  rare.setY(y1);
                  j = -1;
               }
            }
            displayList.add(rare); 
            store.add(rare);
         }
      }
      bulletList = new ArrayList<Bullet>();
      for(int i = 0; i< displayList.size();i++){
         if(displayList.get(i) instanceof Bullet)
            bulletList.add((Bullet)displayList.get(i));
      }   
   }



   protected void bulletHandleCollision(Entity collidedWith, Bullet bullet){
      if ((Entity) collidedWith instanceof Consumable) {
      // if((Consumable) collidedWith instanceof Get){
      
      // }
         displayList.remove(collidedWith);
         displayList.remove(bullet);
         bulletList.remove(bullet);
      }
   }    


//Called whenever it has been determined that the Player collided with a consumable
   protected void handleCollision(Consumable collidedWith){
    //Player's  hp score
      int hp = 0;
      if ((Consumable) collidedWith instanceof Get){
         score += Get.GET_POINT_VALUE;
         displayList.remove(collidedWith);
      }
      if ((Consumable) collidedWith instanceof RareGet){
         score += Get.GET_POINT_VALUE;
         hp = player.getHP() + rare.getDamageValue();  
         player.setHP(hp);
         displayList.remove(collidedWith); 
      }
      if ((Consumable) collidedWith instanceof Avoid){
         hp = player.getHP() + avoid.getDamageValue(); 
         player.setHP(hp);
         displayList.remove(collidedWith);
      }
   }



//Called once the game is over, performs any end-of-game operations 
    //And also checks to see whether the player won or lost the game.
   protected void postGame(){
      this.setBackgroundImage(BACKGROUND_FILE);
      if(this.score >= SCORE_TO_WIN && player.getHP() > 0) {
         this.setSplashImage(WINNER_IMAGE);
         super.setTitleText("GAME OVER! You Won!");
         return;
      } 
      this.setSplashImage(LOSER_IMAGE); 
      super.setTitleText("GAME OVER! You Lose!");    
   }


    //Determines if the game is over or not
    //Game can be over due to either a win or lose state
   protected boolean isGameOver(){
      if (player.getHP() <= 0 || score >= SCORE_TO_WIN){
         return true;
      }
      else {
         return false;
      }
   }



    //Reacts to a single key press on the keyboard
    //Override's AbstractGame's handleKey
   protected void handleKey(int key){
      newSpeed += super.getGameSpeed();
        //first, call AbstractGame's handleKey to deal with any of the 
        //fundamental key press operations
      super.handleKey(key);
      setDebugText("Key Pressed!: " + KeyEvent.getKeyText(key));
        //if a splash screen is up, only react to the advance splash key
        
      if (getSplashImage() != null){
         if (key == ADVANCE_SPLASH_KEY)
            super.setSplashImage(null);
         return;
      }
      if(key == KEY_PAUSE_GAME) { 
         pauseGameOnSplashScreen = true;
         isPaused = !isPaused;
         ticksElapsed = 1;
      }
      if(!isPaused){
         if((key == SPEED_UP_KEY) && (super.getGameSpeed() > 0 && super.getGameSpeed() <= MAX_GAME_SPEED)){
            super.setGameSpeed(super.getGameSpeed()+SPEED_CHANGE);
         }
         else if((key == SPEED_DOWN_KEY) && (super.getGameSpeed() > 0)){
            super.setGameSpeed(super.getGameSpeed()-SPEED_CHANGE);  
         }
         if(key == KEY_QUIT_GAME){
            System.exit(0);
         }
         if((key == UP_KEY) && (player.getY() >= 0)){
            player.setY(player.getY() - player.getMovementSpeed());
         }
         else if((key == DOWN_KEY) && (player.getDown() <= super.getWindowHeight())){
            player.setY(player.getY() + player.getMovementSpeed());
         }
         else if((key == LEFT_KEY) &&  (player.getX() >= 0)){
            player.setX(player.getX() - player.getMovementSpeed());
         }
         else if((key == RIGHT_KEY) && (player.getRight() <= super.getWindowWidth())){
            player.setX(player.getX() + player.getMovementSpeed());
         
         }
         else if(key == KEY_BULLET_GAME){
            Bullet bullet = new Bullet(player.getX(), player.getY()+1);
            displayList.add(bullet);
            bulletList.add(bullet);
         
         }
      }   
   }



}
