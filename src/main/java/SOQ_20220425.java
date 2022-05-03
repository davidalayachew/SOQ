import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SOQ_20220425
{

   public static void main(String[] args)
   {
   
      final JFrame frame = new JFrame();
   
      frame.setTitle("Button Group Example");
      frame.setSize(500, 500);
      frame.setLocation(500, 200);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      final JPanel panel = new JPanel();
      
      final AbstractButton button1 = new JCheckBox("Button 1");
      final AbstractButton button2 = new JCheckBox("Button 2");
      final AbstractButton button3 = new JCheckBox("Button 3");
      
      final AbstractButton check = new JButton("CHECK");
      check.addActionListener(event -> printButtonString(button1, button2, button3));
      
      panel.add(button1);
      panel.add(button2);
      panel.add(button3);
      
      panel.add(check);
      
      frame.add(panel);
      
      frame.setVisible(true);
   
   }
   
   private static void printButtonString(AbstractButton... buttons)
   {
   
      String output = "";
   
      for (AbstractButton button : buttons)
      {
         
         if (button.isSelected())
         {
            
            output += button.getText() + ", ";
            
         }
         
      }
      
      output = output.length() == 0 ? "" : output.substring(0, output.length() - 2);
      
      System.out.println(output);
   
   }

}
