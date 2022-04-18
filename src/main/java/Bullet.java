import java.util.ArrayList;

public class Bullet extends Entity implements Scrollable,BulletPower{
    
   //Location of image file to be drawn for an Avoid
   private static final String BULLET_IMAGE_FILE ="assets/trident.png";
   //Dimensions of the Avoid    
   private static final int BULLET_WIDTH= 30;
   private static final int BULLET_HEIGHT = 30;
   //Speed that the avoid moves each time the game scrolls
   private static final int BULLET_SCROLL_SPEED = 5;
 


  
   public Bullet(){
      this(0, 0);        
   }
   
   public Bullet(int x, int y){
      super(x, y, BULLET_WIDTH, BULLET_HEIGHT, BULLET_IMAGE_FILE);  
   }
   
   public int getScrollSpeed(){
      return BULLET_SCROLL_SPEED;
   }
   
   //Move the avoid left by the scroll speed
   public void scroll(){
      setX(getX() + BULLET_SCROLL_SPEED);
   }

   public int getBulletDamage(){
      return 0;
   }
}