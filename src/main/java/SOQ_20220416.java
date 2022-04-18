import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SOQ_20220416
{

   public static void main(String[] args)
   {
   
      JFrame frame = new JFrame("Title");
      
      frame.setSize(500, 500);
      frame.setLocation(200, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      frame.add(createPanelAndList());
      
      frame.setVisible(true);
   
   }
   
   public static JPanel createPanelAndList()
   {
   
      enum Fruit
      {
      
         APPLE,
         BANANA,
         CHERRY,
         ;
      
      }
   
      JPanel panel = new JPanel();
      
      JList<Fruit> list = new JList<>(Fruit.values());
      
      panel.add(list);
      
      MouseListener mouseListener = 
         new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               if (SwingUtilities.isRightMouseButton(e)) {
                  int index = list.locationToIndex(e.getPoint());
                  System.out.println("Right clicked on Item " + index);
               }
            }
         };
   
      list.addMouseListener(mouseListener);
      
      return panel;
   
   }

}