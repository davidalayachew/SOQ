
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuickAndDirtyExample
{

   public static void main(final String[] args)
   {
   
      SwingUtilities
         .invokeLater
         (
            () ->
            {
            
               final JFrame frame = new JFrame();
               frame.setTitle("Quiz");
               frame.setLocationByPlatform(true);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
               MENU_BAR:
               {
               
                  final JMenuBar menuBar = new JMenuBar();
               
                  final JMenu fileMenu = new JMenu("File Menu");
               
                  final JMenuItem file = new JMenuItem("File Menu Item");
               
                  fileMenu.add(file);
               
                  menuBar.add(fileMenu);
               
                  frame.setJMenuBar(menuBar);
               
               }
            
               final JPanel mainPanel = new JPanel();
               mainPanel.setLayout(new BorderLayout());
            
               SUBMIT_BUTTON:
               {
               
                  final JPanel subPanel = new JPanel();
                  subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.LINE_AXIS));
               
                  final JButton submitButton = new JButton();
                  submitButton.setText("Submit");
               
                  subPanel.add(Box.createHorizontalGlue());
                  subPanel.add(submitButton);
               
                  mainPanel.add(subPanel, BorderLayout.SOUTH);
               
               }
            
               MAIN_BODY:
               {
               
                  final JPanel questionSectionPanel = new JPanel();
                  questionSectionPanel.setLayout(new BorderLayout());
               
                  final JLabel theQuestion = new JLabel();
                  theQuestion.setText("What is your favorite color?");
               
                  questionSectionPanel.add(theQuestion, BorderLayout.NORTH);
               
                  final ButtonGroup theAnswersButtonGroup = new ButtonGroup();
               
                  final List<String> theAnswers = List.of("Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet");
               
                  final JPanel answerSectionPanel = new JPanel();
                  answerSectionPanel.setLayout(new GridLayout(0, 1));
               
                  questionSectionPanel.add(answerSectionPanel, BorderLayout.WEST);
               
                  for (final String eachAnswer : theAnswers)
                  {
                  
                     final JRadioButton eachAnswerButton = new JRadioButton(eachAnswer);
                  
                     theAnswersButtonGroup.add(eachAnswerButton);
                     answerSectionPanel.add(eachAnswerButton);
                  
                  }
               
                  final JScrollPane scrollPane = new JScrollPane(questionSectionPanel);
               
                  mainPanel.add(scrollPane, BorderLayout.CENTER);
               
               }
            
               frame.add(mainPanel);
            
               frame.pack();
               frame.setVisible(true);
            
            }
         )
         ;
   
   }

}
