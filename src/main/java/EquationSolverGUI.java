import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class EquationSolverGUI extends JFrame {

   public static final int WIDTH = 500;
   public static final int HEIGHT = 300;

   public EquationSolverGUI() {
      
      super("Equation Solver");
      setSize(WIDTH, HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      JPanel panel = new JPanel(new GridLayout(0, 1));
      JPanel inputPanel = new JPanel();
      JPanel outputPanel = new JPanel();
      JPanel buttonPanel = new JPanel();
   
   
      JTextField input = new JTextField(20);
      inputPanel.add(input);
   
      JTextField output = new JTextField(20);
      outputPanel.add(output);
   
      JButton solve = new JButton("Solve");
      buttonPanel.add(solve);
   
      panel.add(inputPanel);
      panel.add(outputPanel);
      panel.add(buttonPanel);
      
      add(panel);
   
   }

   public static void main(String[] args) {
      EquationSolverGUI equationSolver = new EquationSolverGUI();
      equationSolver.setVisible(true);
   }
   
   
}