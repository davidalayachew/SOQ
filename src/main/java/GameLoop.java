import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
 
 
public class GameLoop extends JPanel {
 
   InputHandler inputHandler = new InputHandler();//Implements input handler class
   Player player = new Player();//Implements player class
   final int screenWidth = 700;
   final int screenHeight = 500;
 
   int playerX = player.xPos; //Initial player position
   int playerY = player.yPos; //Initial player position
 
   int playerSpeed = player.speed;
 
   int playerSizeH = 25;
   int playerSizeW = 25;
 
   int mouseX;
   int mouseY;
 
   int maxEnemyAmount = 10;
 
   int maxBigEnemyAmount = 3;
 
   boolean projectileFired = false;
 
   int score = 0;
 
   int buffX = -100;
 
   int buffY = -100;
 
   boolean buffPickedUp = false;
 
   boolean isGameOver = false;
 
   private Timer gameOverTimer;
 
   private Timer gameTimer;
 
   public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
 
   public ArrayList<Enemy> enemyList = new ArrayList<>();
 
   public ArrayList<Integer> buffList = new ArrayList<>();
 
   public ArrayList<BigEnemy> bigEnemyList = new ArrayList<>();
 
   public JButton restartButton;
 
   public void drawRestartButton() {
      restartButton = new JButton("Restart");//Code taken from: https://stackoverflow.com/questions/27676684/add-restart-button-when-game-is-over
      restartButton.setBounds(300, screenHeight / 2, 100, 50);
      add(restartButton);
      restartButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //isGameOver = false;
               restartGame();
            }
         });
      restartButton.setVisible(true);
   }
 
//Method to start the game loop
   public void startGame() {
      Timer gameTimer = new Timer(12, 
         new ActionListener() {//Sets screen to refresh at ~60FPS
            @Override
            public void actionPerformed(ActionEvent e) {//Game loop
               update();
               checkPlayerBuffCollision();
               checkPlayerBorderCollision();
               checkProjectileEnemyCollision();
               checkPlayerEnemyCollision();
               updateProjectiles();
               updateEnemies();
               updateBigEnemy();
               checkIfPlayerDead();
               repaint();
            }
         });
      gameTimer.start();
   
      Timer enemyTimer = new Timer(3000, 
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               spawnEnemies();
            }
         });
      enemyTimer.start();
   
      Timer buffTimer = new Timer(5000, 
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               spawnBuff();
            }
         });
      buffTimer.start();
   
      Timer bigEnemyTimer = new Timer(10000, 
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               spawnBigEnemy();
            }
         });
      bigEnemyTimer.start();
   }
 
   public void update() {
      if (inputHandler.upPressed) {
         System.out.println("Up");
         playerY -= playerSpeed;
      }
      if (inputHandler.downPressed) {
         System.out.println("Down");
         playerY += playerSpeed;
      }
      if (inputHandler.leftPressed) {
         System.out.println("Left");
         playerX -= playerSpeed;
      }
      if (inputHandler.rightPressed) {
         System.out.println("Right");
         playerX += playerSpeed;
      }
      if (!inputHandler.spacePressed) {
         projectileFired = false;
      }
      if (inputHandler.spacePressed && !projectileFired) {
         projectileFired = true;//Flag to make sure user can't continuously fire projectiles
         Projectile newProjectile = new Projectile(playerX + playerSizeW / 2, playerY + playerSizeH / 2, mouseX, mouseY);//Spawns projectile from the middle of the player
         projectileList.add(newProjectile);//Adds the projectile to a list
      }
   }
 
   public void checkPlayerBorderCollision() {
      if (playerY > screenHeight) {
         playerY = screenHeight - 50;
      } else if (playerY > 436) {
         playerY = 436;
      } else if (playerY < 0) {
         playerY = 0;
      }
   
      if (playerX > screenWidth) {
         playerX = screenWidth;
      } else if (playerX > 659) {
         playerX = 659;
      } else if (playerX < 0) {
         playerX = 0;
      }
   }
 
   public void checkProjectileEnemyCollision() {
      for (int i = 0; i < projectileList.size(); i++) {
         Projectile projectile = projectileList.get(i);
         for (int x = 0; x < enemyList.size(); x++) {
            Enemy enemy = enemyList.get(x);
            if (projectile.enemyHit(enemy)) {
               enemyList.remove(x);//Kills enemy
               score++;
               projectileList.remove(i);
               i--;
            }
         }
      }
   }
 
   public void checkPlayerEnemyCollision() {
      Rectangle playerEdges = new Rectangle(playerX, playerY, playerSizeW, playerSizeH);
      for (int i = 0; i < enemyList.size(); i++) {
         Enemy enemy = enemyList.get(i);
         Rectangle enemyEdges = new Rectangle(enemy.getEnemyXPos(), enemy.getEnemyYPos(), enemy.getEnemySizeW(), enemy.getEnemySizeH());
         if (playerEdges.intersects(enemyEdges)) {
            player.subtractHP(enemy.getEnemyDmg());
         }
         for (int x = 0; x < bigEnemyList.size(); x++) {
            BigEnemy bigEnemy = bigEnemyList.get(x);
            Rectangle bigEnemyEdges = new Rectangle(bigEnemy.getEnemyXPos(), bigEnemy.getEnemyYPos(), 50, 50);
            if (playerEdges.intersects(bigEnemyEdges)) {
               player.subtractHP(bigEnemy.getEnemyDmg());
            }
         }
      }
   }
 
   public void checkPlayerBuffCollision() {
      Rectangle playerEdges = new Rectangle(playerX, playerY, playerSizeW, playerSizeH);
      Rectangle buffEdges = new Rectangle(buffX, buffY, 5, 5);
      if (playerEdges.intersects(buffEdges) && player.getHP() <= 100 && !buffPickedUp) {
         int tempHP = player.getHP() + 10;
         if (tempHP > 100) {
            player.setHP(100);
         } else {
            player.addHP(10);
         }
         buffPickedUp = true;
         buffList.clear();
         buffX = -100;
         buffY = -100;
      }
   }
 
   public void checkIfPlayerDead() {
      if (player.getHP() == 0) {
         gameOver();
      }
   }
 
   public void restartGame() {
      isGameOver = false;
      score = 0;
      player.setHP(100);
      playerSpeed = player.getSpeed();
      playerX = player.getXPos();
      playerY = player.getYPos();
      enemyList.clear();
      bigEnemyList.clear();
      projectileList.clear();
      buffList.clear();
      remove(restartButton);
   }
 
   public void gameOver() {
      isGameOver = true;
      drawRestartButton();
   }
 
   public void updateProjectiles() {
      for (int i = 0; i < projectileList.size(); i++) {
         Projectile projectile = projectileList.get(i);
         projectile.updatePosition();//Update position
      //Check if projectile hits the edge and if it does, remove from list
         if (projectile.getProjectileXPos() < 0 || projectile.getProjectileXPos() > screenWidth || projectile.getProjectileYPos() < 0 || projectile.getProjectileYPos() > screenHeight) {
            projectileList.remove(i);
            i--;
         }
      }
   }
 
   public void spawnEnemies() {
      if (enemyList.size() < maxEnemyAmount) {
         int enemyX = (int) (Math.random() * screenWidth);
         int enemyY = (int) (Math.random() * screenHeight);
         Enemy newEnemy = new Enemy(enemyX, enemyY, 1);
         enemyList.add(newEnemy);
      }
   }
 
   public void updateEnemies() {
      for (int i = 0; i < enemyList.size(); i++) {
         Enemy enemy = enemyList.get(i);
         enemy.updatePosition(playerX, playerY);//Update position
      }
   }
 
   public void spawnBigEnemy() {
      if (bigEnemyList.size() < maxBigEnemyAmount) {
         int bigEnemyX = (int) (Math.random() * screenWidth);
         int bigEnemyY = (int) (Math.random() * screenHeight);
         BigEnemy newBigEnemy = new BigEnemy(bigEnemyX, bigEnemyY, 5);
         bigEnemyList.add(newBigEnemy);
      }
   }
 
   public void updateBigEnemy() {
      for (int i = 0; i < bigEnemyList.size(); i++) {
         BigEnemy bigEnemy = bigEnemyList.get(i);
         bigEnemy.updatePosition(playerX, playerY);
      }
   }
   public void spawnBuff () {
      buffX = (int) (Math.random() * screenWidth);
      buffY = (int) (Math.random() * screenHeight);
      buffPickedUp = false;
      buffList.add(1);
   }
 
 
 
   @Override
   protected void paintComponent (Graphics g) {
   //System.out.println("paintComponent called");
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
   
      if (!isGameOver) {
         setBackground(Color.black);//Sets background colour to black
         g.setColor(Color.black);
         g.fillRect(0, 0, screenWidth, screenHeight);
      
         g.setColor(Color.white);//Sets colour to white
         g.fillRect(playerX, playerY, playerSizeW, playerSizeH);//Draws player on screen
      
      
         g.setColor(Color.blue);//Set colour to blue
         for (Projectile projectile : projectileList) {//For every projectile in the list, place projectile
            g.fillRect(projectile.getProjectileXPos(), projectile.getProjectileYPos(), projectile.getProjectileWidth(), projectile.getProjectileHeight());
         }
      
         g.setColor(Color.green);
         for (Enemy enemy : enemyList) {
            g.fillRect(enemy.getEnemyXPos(), enemy.getEnemyYPos(), enemy.getEnemySizeW(), enemy.getEnemySizeH());
         }
      
         g.setColor(Color.red);
         for (BigEnemy bigEnemy : bigEnemyList) {
            g.fillRect(bigEnemy.getEnemyXPos(), bigEnemy.getEnemyYPos(), 50, 50);
         }
      
         g.setColor(Color.white);
         Font font = new Font("Comic Sans", Font.BOLD, 16);
         g.setFont(font);
      
         g.drawString("Score: " + score, 10, 20);
         g.drawString("Health: ", 10, 45);
      
         g.setColor(Color.red);
         g.drawString(String.valueOf(player.getHP()), 70, 45);
      
         g.setColor(Color.yellow);
         g.fillRect(buffX, buffY, 5, 5);
      
         mouseX = inputHandler.mouseX;
         mouseY = inputHandler.mouseY;
      } else {
         g.setColor(Color.black);
         g.fillRect(0, 0, screenWidth, screenHeight);
         g.setColor(Color.white);
         Font gameOverFont = new Font("Arial", Font.BOLD, 50);
         g.setFont(gameOverFont);
         g.drawString("Game Over", 210, 80);
      }
   
   }
 
}
