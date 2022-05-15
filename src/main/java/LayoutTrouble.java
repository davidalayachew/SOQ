import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class LayoutTrouble
{

   private static final int MAX = 10;

   public static void main(String[] args)
   {
   
      gridLayoutWithMoreElementsThanPlanned(1, 3, 300, 400);
      gridLayoutWithMoreElementsThanPlanned(1, 0, 800, 400);
   
   }

   private static void gridLayoutWithMoreElementsThanPlanned(final int rows, final int columns, final int x, final int y)
   {
   
      final JFrame frame = new JFrame();
      final JPanel panel = new JPanel();
   
      panel.setLayout(new GridLayout(rows, columns));
      
      for (int i = 1; i < MAX; i++)
      {
      
         panel.add(new JButton("" + i));
      
      }
   
      frame.add(panel);
      
      frame.setTitle("4 buttons in a " + rows + " by " + columns);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500, 300);
      frame.setLocation(x, y);
      frame.setVisible(true);
   
   }
   
}