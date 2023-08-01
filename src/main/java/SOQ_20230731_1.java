
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SOQ_20230731_1
{

   public static void main(String[] args)
   {
   
      new SOQ_20230731_1();
   
   }
   
   public SOQ_20230731_1()
   {
   
      final JFrame frame = new JFrame();
      frame.setSize(300, 100);
      frame.setLocation(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setBackground(Color.BLACK);
      
      final JPanel panel = new JPanel();
      
      panel.setBackground(Color.BLACK);
      panel.setMaximumSize(new java.awt.Dimension(100, 40));
      panel.add(createContent());
      
      frame.add(panel);
      
      frame.setVisible(true);
   
   }
   
   private JPanel createContent()
   {
   
      final JPanel panel = new JPanel();
      panel.setBackground(Color.BLACK);
      panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
   
      final JTextField textField = new JTextField();
      textField.setBackground(Color.BLACK);
      textField.setForeground(Color.WHITE);
      textField.setText("Enter your license key");
      textField.setBorder(BorderFactory.createEmptyBorder());
      textField.addMouseListener(
         new MouseAdapter()
         {
         
            public void mouseClicked(MouseEvent event)
            {
            
               textField.selectAll();
            
            }
         
         });
      
      final JButton button = new JButton();
      button.setBackground(Color.YELLOW);
      button.setForeground(Color.WHITE);
      button.setText("Connect");
      
      panel.add(textField);
      panel.add(button);
      
      return panel;
      
   }

}
