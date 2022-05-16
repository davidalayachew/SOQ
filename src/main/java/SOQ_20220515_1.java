import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class SOQ_20220515_1
{

   public static void main(String[] args) {
      JFrame details = new JFrame();
      Task task = new Task();
   
   
      JOptionPane.showMessageDialog(null, "Welcome to MyWorld");
      String option = JOptionPane.showInputDialog(details,"Choose one of the following: "
         + "1. Add tasks \n"
         + "2. Show report \n"
         + "3. Quit \n"
         );
        
      int x;
      x= Integer.parseInt(option);
      if(x==1){
         String task1 = JOptionPane.showInputDialog(details,"Number of tasks:"); 
         Integer.parseInt(task1);
         String desc = JOptionPane.showInputDialog(null, "Task Description:");
         while(!task.checkTaskDescription(desc)){
            JOptionPane.showInputDialog(details,"Enter description");
         }
      }
      else if(x==2)
      {
         JOptionPane.showMessageDialog(details,"Coming Soon");      
      }
      else if(x==3){
         System.exit(0);
      }     
      
   }

   public static class Task {
      boolean checkTaskDescription(String taskDescription){
         boolean length= false;
         if(taskDescription.length() == 10) {
            length= true;  
         }else{ 
            length= false;
         }
         return false;
      }
   }
}