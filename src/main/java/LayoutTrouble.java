import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class LayoutTrouble
{

   public static void main(String[] args)
   {
   
      gridLayoutWithMoreElementsThanPlanned(1, 3, 300, 400);
      gridLayoutWithMoreElementsThanPlanned(1, 0, 600, 400);
   
   }

   private static void gridLayoutWithMoreElementsThanPlanned(final int rows, final int columns, final int x, final int y)
   {
   
      final JFrame frame = new JFrame();
      final JPanel panel = new JPanel();
   
      panel.setLayout(new GridLayout(rows, columns));
      
      panel.add(new JButton("1"));
      panel.add(new JButton("2"));
      panel.add(new JButton("3"));
      panel.add(new JButton("4"));
   
      frame.add(panel);
      
      frame.setTitle("4 buttons in a " + rows + " by " + columns);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(300, 300);
      frame.setLocation(x, y);
      frame.setVisible(true);
   
   }
   
}