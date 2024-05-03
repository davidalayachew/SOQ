import java.awt.event.*;
 
public 
   class 
      InputHandler 
   implements 
      KeyListener,
      MouseListener, 
      MouseMotionListener 
{

   public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, tabPressed, fPressed; 
   public int mouseX, mouseY;
 
   @Override public void keyTyped(KeyEvent e) {
   }
 
   @Override public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_W) {
         upPressed = true;
      //System.out.println("Up");
      }
   
      if (key == KeyEvent.VK_A) {
         leftPressed = true;
      //System.out.println("Left");
      }
   
      if (key == KeyEvent.VK_S) {
         downPressed = true;
      //System.out.println("Down");
      }
   
      if (key == KeyEvent.VK_D) {
         rightPressed = true;
      //System.out.println("Right");
      }
   
      if (key == KeyEvent.VK_SPACE) {
         spacePressed = true;
      }
   
      if (key == KeyEvent.VK_TAB) {
         tabPressed = true;
      }
      if (key == KeyEvent.VK_F) {
         fPressed = true;
      }
   
   }
 
   @Override
   public void keyReleased(KeyEvent e) {
      int key = e.getKeyCode();
   
      if (key == KeyEvent.VK_W) {
         upPressed = false;
      }
   
      if (key == KeyEvent.VK_A) {
         leftPressed = false;
      }
   
      if (key == KeyEvent.VK_S) {
         downPressed = false;
      }
   
      if (key == KeyEvent.VK_D) {
         rightPressed = false;
      }
   
      if (key == KeyEvent.VK_SPACE) {
         spacePressed = false;
      }
   
      if (key == KeyEvent.VK_TAB) {
         tabPressed = false;
      }
      if (key == KeyEvent.VK_F) {
         fPressed = false;
      }
   
   }
 
   public void mouseClicked(MouseEvent e) {
   
   }
 
 
   public void mousePressed(MouseEvent e) {
   
   }
 
 
   public void mouseReleased(MouseEvent e) {
   
   }
 
 
   public void mouseEntered(MouseEvent e) {
   
   }
 
 
   public void mouseExited(MouseEvent e) {
   
   }
 
   public void mouseMoved(MouseEvent e) {
      mouseX = e.getX();
      mouseY = e.getY();
   }
 
   public void mouseDragged(MouseEvent e) {
   
   }
}