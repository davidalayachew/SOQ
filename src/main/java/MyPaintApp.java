import java.util.Vector;
import javax.swing.*;

import java.awt.*;

import java.awt.event.*;


public class MyPaintApp extends JFrame {
   Vector<Point> list = new Vector<>();
   Vector<Vector<Point>> list1 = new Vector<>();

   class MyPanel extends JPanel {
      public MyPanel() {
      
         final MouseAdapter mouseAdapter =
            new MouseAdapter() {
               public void mouseDragged(MouseEvent event) {
                  repaint();
                  list.add(event.getPoint());
               }
               public void mouseMoved(MouseEvent event) {
                  repaint();
               }
               public void mouseReleased(MouseEvent event) {
                  if (list.isEmpty()){
                     return;
                  }
               
                  list1.add(list);
                  list = new Vector<>();
                  repaint();
               }
            }
            ;
      
         this.addMouseMotionListener(mouseAdapter);
         this.addMouseListener(mouseAdapter);
      }
   
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         for (final Vector<Point> listA : list1) {
            for (int i = 0; i < listA.size() - 1; i++) {
               Point p1 = listA.get(i);
               Point p2 = listA.get(i + 1);
               g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
         }
      }
   }

   @SuppressWarnings("this-escape")
   public MyPaintApp() {
      setSize(600, 450);
      setLocation(400, 0);
      setTitle("My Paint");
      add(new MyPanel());
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public static void main(String[] args)
   {
   
      new MyPaintApp();
   
   }

}
