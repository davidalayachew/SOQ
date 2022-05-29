import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SOQ_20220519 {
   public static void main(String[] args) {
      new SOQ_20220519();
   }

   public SOQ_20220519() {
      EventQueue.invokeLater(
         new Runnable() {
            @Override
            public void run() {
               try {
                  JFrame frame = new JFrame();
                  frame.add(new MainPane());
                  frame.pack();
                  frame.setLocationRelativeTo(null);
                  frame.setVisible(true);
               } catch (IOException ex) {
                  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
         });
   }

   public class MainPane extends JPanel {
   
      private BufferedImage car;
      private BufferedImage road;
   
      private int horizontalPosition = 106;
   
      public MainPane() throws IOException {
         car = ImageIO.read(getClass().getResource("/images/Car.png"));
         road = ImageIO.read(getClass().getResource("/images/Road.png"));
      
         JButton left = new JButton("<");
         JButton right = new JButton(">");
      
         JPanel actionPane = new JPanel();
         actionPane.setOpaque(false);
         actionPane.add(left);
         actionPane.add(right);
      
         setLayout(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.weighty = 1;
         gbc.anchor = GridBagConstraints.SOUTH;
         add(actionPane, gbc);
      
         left.setFocusPainted(false);
         right.setFocusPainted(false);
      
         left.addActionListener(
            new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int halfCarWidth = (car.getWidth() / 2);
                  horizontalPosition -= halfCarWidth;
                  if (horizontalPosition - halfCarWidth < 0) {
                     horizontalPosition = halfCarWidth;
                  }
                  repaint();
               }
            });
         right.addActionListener(
            new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int halfCarWidth = (car.getWidth() / 2);
                  horizontalPosition += halfCarWidth;
                  if (horizontalPosition + halfCarWidth > getWidth()) {
                     horizontalPosition = getWidth() - halfCarWidth;
                  }
                  repaint();
               }
            });
      }
   
      @Override
      public Dimension getPreferredSize() {
         return new Dimension(213, 216);
      }
   
      @Override
      protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g.create();
         drawRoadSurface(g2d);
         g2d.dispose();
         g2d = (Graphics2D) g.create();
         drawCar(g2d);
         g2d.dispose();
      }
   
      protected void drawCar(Graphics2D g2d) {
         g2d = (Graphics2D) g2d.create();
         int x = horizontalPosition - (car.getWidth() / 2);
         int y = (getHeight() - car.getHeight()) / 2;
         g2d.translate(x, y);
         g2d.drawImage(car, 0, 0, this);
         g2d.dispose();
      }
   
      protected void drawRoadSurface(Graphics2D g2d) {
         int x = (getWidth() - road.getWidth()) / 2;
         int y = (getHeight() - road.getHeight()) / 2;
         g2d.drawImage(road, x, y, this);
      }
   
   }
}