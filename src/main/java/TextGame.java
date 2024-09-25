import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TextGame {

   JFrame window; //Frame which will be the window frame
   Container con; //A base which will contain everything we want to display
   JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceBottomPanel, playerPanel, namePanel, inventoryPanel, leftPanel, rightPanel;//Panels for texts, buttons and title etc.
   JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName, nameLabel, emptyLabel, itemLabel;//A label that displays short strings and icons used for our title screen text
   JTextArea maintextArea, biggerText, descriptionArea; //a multi line area which will display lots of text
   JTextField nameField; //Used for a single line of text
   Font titleFont = new Font("Times New Roman", Font.PLAIN, 90); //setting up our title font
   JButton startButton, choice1, choice2, choice3, choice4, continueButton, nextButton; //Creating start button
   Font normalFont = new Font("Times New Roman", Font.PLAIN, 28); //setting up our normal texts fonts
   Font biggerFont = new Font("Times New Roman", Font.PLAIN, 40);
   int playerHP;
   String weapon, position, playerName;

   ArrayList<String> inventory; //list of the inventory, added game items gets added here
   TitleScreenHandler tsHandler = new TitleScreenHandler(); //Declaring the action listener for use
   ChoiceHandler choiceHandler = new ChoiceHandler();

//Here is the command we use to display text in the game, we have 2 JTextAreas with 2 fonts so this how we select between them
   public void displayText(String text, boolean useBiggerFont) {
      if (useBiggerFont) {
         biggerText.setText(text);
         biggerText.setVisible(true);
         maintextArea.setVisible(false);
      } else {
         maintextArea.setText(text);
         maintextArea.setVisible(true);
         biggerText.setVisible(false);
      }
   
      con.validate();
      con.repaint();
   }

   public static void main(String[] args) {
      new TextGame();
   }

   public TextGame() {
   // Initialize JFrame
      window = new JFrame();
      window.setSize(800, 600);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.getContentPane().setBackground(Color.BLACK);
      window.setLayout(null);
      window.setVisible(true);
      con = window.getContentPane();
   
   // Initialize title panel and label
      titleNamePanel = new JPanel();
      titleNamePanel.setBounds(100, 100, 600, 150);
      titleNamePanel.setBackground(Color.black);
   
      titleNameLabel = new JLabel("POTATOLAND");
      titleNameLabel.setForeground(Color.white);
      titleNameLabel.setFont(titleFont);
   
   // Initialize button panel and button
      startButtonPanel = new JPanel();
      startButtonPanel.setBounds(300, 400, 200, 100);
      startButtonPanel.setBackground(Color.black); //sets background color for the panel
      startButton = new JButton("START");
      startButton.setBackground(Color.black); //sets background color for the button
      startButton.setForeground(Color.white); //sets text color for the button
      startButton.setFont(normalFont); //using the font we created in the main class textgame
      startButton.setFocusPainted(false); // remove the focus border/outline on the button
      startButton.addActionListener(tsHandler); //added to the start button the declared action listener which will go to the public class TitleHandler action listener, calling it
   // Add components to panels and container
      titleNamePanel.add(titleNameLabel);
      startButtonPanel.add(startButton);
   
   // Initiliazing Inventory
      inventory = new ArrayList<>();
   
      con.add(titleNamePanel);
      con.add(startButtonPanel);
   
   // Force components to repaint and validate layout
      window.validate();
      window.repaint();
   }
//Creating games creen area
   public void createGameScreen() {
   // Hide title panels
      if (titleNamePanel != null) titleNamePanel.setVisible(false);
      if (startButtonPanel != null) startButtonPanel.setVisible(false);
   
   // Initialize the main text panel if not already initialized
      if (mainTextPanel == null) {
         mainTextPanel = new JPanel();
         mainTextPanel.setBounds(100, 100, 600, 250);
         mainTextPanel.setBackground(Color.black);
         con.add(mainTextPanel);
      
         maintextArea = new JTextArea();
         maintextArea.setBounds(100, 100, 600, 250);
         maintextArea.setBackground(Color.black);
         maintextArea.setForeground(Color.white);
         maintextArea.setFont(normalFont);
         maintextArea.setLineWrap(true);
         mainTextPanel.add(maintextArea);
      
         biggerText = new JTextArea();
         biggerText.setBounds(100, 100, 600, 250);
         biggerText.setBackground(Color.black);
         biggerText.setForeground(Color.white);
         biggerText.setFont(biggerFont);
         biggerText.setLineWrap(true);
         biggerText.setVisible(false);
         mainTextPanel.add(biggerText);
      }
   
   // Initialize choice buttons and panel
      if (choiceBottomPanel == null) {
         choiceBottomPanel = new JPanel();
         choiceBottomPanel.setBounds(250, 350, 300, 150);
         choiceBottomPanel.setBackground(Color.black);
         choiceBottomPanel.setLayout(new GridLayout(4, 1));
      
         choice1 = new JButton("Choice 1");
         choice1.setBackground(Color.black);
         choice1.setForeground(Color.white);
         choice1.setFont(normalFont);
         choice1.setFocusPainted(false);
         choice1.addActionListener(choiceHandler);
         choice1.setActionCommand("c1");
         choiceBottomPanel.add(choice1);
      
         choice2 = new JButton("Choice 2");
         choice2.setBackground(Color.black);
         choice2.setForeground(Color.white);
         choice2.setFont(normalFont);
         choice2.setFocusPainted(false);
         choice2.addActionListener(choiceHandler);
         choice2.setActionCommand("c2");
         choiceBottomPanel.add(choice2);
      
         choice3 = new JButton("Choice 3");
         choice3.setBackground(Color.black);
         choice3.setForeground(Color.white);
         choice3.setFont(normalFont);
         choice3.setFocusPainted(false);
         choice3.addActionListener(choiceHandler);
         choice3.setActionCommand("c3");
         choiceBottomPanel.add(choice3);
      
         choice4 = new JButton("Choice 4");
         choice4.setBackground(Color.black);
         choice4.setForeground(Color.white);
         choice4.setFont(normalFont);
         choice4.setFocusPainted(false);
         choice4.addActionListener(choiceHandler);
         choice4.setActionCommand("c4");
         choiceBottomPanel.add(choice4);
      
         con.add(choiceBottomPanel);
      }
   
   // Initialize player panel if not already initialized
      if (playerPanel == null) {
         playerPanel = new JPanel();
         playerPanel.setBounds(100, 15, 600, 50);
         playerPanel.setBackground(Color.black);
         playerPanel.setLayout(new GridLayout(1, 4));
         con.add(playerPanel);
      
         hpLabel = new JLabel("HP:");
         hpLabel.setFont(normalFont);
         hpLabel.setForeground(Color.white);
         playerPanel.add(hpLabel);
      
         hpLabelNumber = new JLabel();
         hpLabelNumber.setFont(normalFont);
         hpLabelNumber.setForeground(Color.white);
         playerPanel.add(hpLabelNumber);
      
         weaponLabel = new JLabel("Weapon: ");
         weaponLabel.setFont(normalFont);
         weaponLabel.setForeground(Color.white);
         playerPanel.add(weaponLabel);
      
         weaponLabelName = new JLabel();
         weaponLabelName.setFont(normalFont);
         weaponLabelName.setForeground(Color.white);
         playerPanel.add(weaponLabelName);
      }
   
   // Set up player stats
      playerSetup();
   }

   public void playerSetup() {
      playerHP = 15;
      weapon = "Stick";
      weaponLabelName.setText(weapon);
      hpLabelNumber.setText("" +playerHP);
   }

// Player Name Input
   public void askPlayerName() {
   // Hide previous panels
      if (titleNamePanel != null) titleNamePanel.setVisible(false);
      if (startButtonPanel != null) startButtonPanel.setVisible(false);
   
   // Panel for entering player's name
      namePanel = new JPanel();
      namePanel.setBounds(100, 150, 600, 250);
      namePanel.setBackground(Color.black);
      namePanel.setLayout(new GridLayout(4, 1, 10, 10));
   
      nameLabel = new JLabel("Enter your name:");
      nameLabel.setForeground(Color.white);
      nameLabel.setFont(biggerFont);
      namePanel.add(nameLabel); //Adds name label to the name panel
      nameField = new JTextField();
      nameField.setFont(biggerFont);
      nameField.setForeground(Color.white);
      nameField.setBackground(Color.black);
      namePanel.add(nameField); //Adds name field to the name panel
   //adding continue button
      continueButton = new JButton("Continue");
      continueButton.setBackground(Color.black);
      continueButton.setForeground(Color.white);
      continueButton.setFont(normalFont);
      continueButton.setFocusPainted(false);
      continueButton.addActionListener(
         new ActionListener() {
            @Override
            
            //Make sure this doesn't interfere with the other methods
            public void actionPerformed(ActionEvent e) {
               playerName = nameField.getText();
               introStory(); // Proceed to the introStory after entering name
            }
         });
   
      namePanel.add(continueButton); //Final thing we add in panel
      con.add(namePanel); //Finally add the panel to container
      window.validate();
      window.repaint();
   }

   public void introStory() {
   //error checking
      System.out.println("Intro story method called");
   
   //Set the position to the intro story
      position = "introStory";
   
   //Clear previous content so you can display text without anything extra in the way
      con.removeAll(); //removes all components from the container
      con.revalidate(); //Makes sure to lay components again after changes (Important to call this after removing all)
      con.repaint(); //Requesting that container components needs to be redrawn to ensure changes are visually updated.
   //Initialize the text panel
      mainTextPanel = new JPanel();
      mainTextPanel.setBounds(100, 100, 600, 250);
      mainTextPanel.setBackground(Color.black);
      con.add(mainTextPanel);
   
   //Initialize text area
      maintextArea = new JTextArea();
      maintextArea.setBounds(100, 100, 600, 250);
      maintextArea.setBackground(Color.black);
      maintextArea.setForeground(Color.white);
      maintextArea.setFont(normalFont);
      maintextArea.setLineWrap(true);
      mainTextPanel.add(maintextArea); //Adds text area to the text panel above
   //Initialize biggerText Area
      biggerText = new JTextArea();
      biggerText.setBounds(100, 100, 600, 250);
      biggerText.setBackground(Color.black);
      biggerText.setForeground(Color.white);
      biggerText.setFont(biggerFont);
      biggerText.setLineWrap(true);
      biggerText.setVisible(false); // Initially hidden
      mainTextPanel.add(biggerText); // Add to the text panel
   //Initialize next button
      nextButton = new JButton("Next");
      nextButton.setBackground(Color.black);
      nextButton.setForeground(Color.white);
      nextButton.setFont(normalFont);
      nextButton.setFocusPainted(false);
      nextButton.setBounds(350, 400, 100, 50); //ensure the button has valid bounds
      con.add(nextButton);
   
      System.out.println("Next Button added"); //error checking
      window.validate();
      window.repaint();
   
   //Start the story with the first part
      showIntroPart1();
   
      window.validate();
      window.repaint();
   }

   private void showIntroPart1() {
      displayText("The year is 2028. The day you move\nto Japan has finally come.", true);
   
   //Goes through all Action Listeners currently attached to next button, then removes them. Ensures that button will only perform new action.
      for (ActionListener al : nextButton.getActionListeners()) {
         nextButton.removeActionListener(al);
      }
   
      nextButton.addActionListener(
         new ActionListener() {
            @Override
            
            
            public void actionPerformed(ActionEvent e) {
               showIntroPart2();
            }
         });
   }

   private void showIntroPart2() {
      displayText("You arrive early at the airport,\nexcited and nervous at the same time.",true);
   
   // Remove any existing action listeners
      for (ActionListener al : nextButton.getActionListeners()) {
         nextButton.removeActionListener(al);
      }
   
      nextButton.addActionListener(
         new ActionListener() {
            @Override
            
            
            public void actionPerformed(ActionEvent e) {
               showIntroPart3();
            }
         });
   }

   private void showIntroPart3() {
      displayText(playerName + ": Hey, I gotta go to the \nbathroom real quick, wait me here.", true);
   
   // Remove any existing action listeners
      for (ActionListener al : nextButton.getActionListeners()) {
         nextButton.removeActionListener(al);
      }
   
      nextButton.addActionListener(
         new ActionListener() {
            @Override
            
            
            public void actionPerformed(ActionEvent e) {
               showIntroPart4();
            }
         });
   }

   private void showIntroPart4() {
      displayText("As you enter the bathroom stall, you\nstart feeling dizzy and collapse!", true);
   
   // Remove any existing action listeners
      for (ActionListener al : nextButton.getActionListeners()) {
         nextButton.removeActionListener(al);
      }
   
      nextButton.addActionListener(
         new ActionListener() {
            @Override
            
            
            public void actionPerformed(ActionEvent e) {
               showIntroPart5();
            }
         });
   }

   private void showIntroPart5() {
      displayText("Time passes....", true);
   
   // Remove any existing action listeners
      for (ActionListener al : nextButton.getActionListeners()) {
         nextButton.removeActionListener(al);
      }
   
      nextButton.addActionListener(
         new ActionListener() {
            @Override
            
            
            public void actionPerformed(ActionEvent e) {
               startingPoint();
            }
         });
   }

   public void startingPoint() {
      nextButton.setVisible(false); //hides the next button
   //ensure game screen elements are visible
      if (choiceBottomPanel == null) {
         createGameScreen();
      }
   
      choiceBottomPanel.setVisible(true);
      position = "startingPoint";
      displayText(playerName + ": Ugh... My head... Where am I?\n\nWhat do you want to do?", false);
      choice1.setText("Exit the bathroom");
      choice2.setText("Check pockets");
   
   //hides the other buttons that are not needed
      choice3.setVisible(false);
      choice4.setVisible(false);
   }

   public void exitBathroom() {
      displayText("You exit the bathroom and you find\nyourself in unfamiliar place...", true);
   
   //Hiding panels from not overlapping and for a more clean look
      choiceBottomPanel.setVisible(false);
      playerPanel.setVisible(false);
   
   //Press next button to move to wizard house
      nextButton.setVisible(true);
      nextButton.addActionListener(
         new ActionListener() {
            @Override
            
            
            public void actionPerformed(ActionEvent e) {
               wizardHouse(); //go to wizardHouse method
            }
         });
   }

   public void wizardHouse() {
   
   }

   public void checkPockets() {
   // Hide panels that are not needed
      if (choiceBottomPanel != null) choiceBottomPanel.setVisible(false);
      if (playerPanel != null) playerPanel.setVisible(false);
      if (maintextArea != null) maintextArea.setVisible(false);
   
   // Clear any previous inventory display
      if (inventoryPanel != null) {
         con.remove(inventoryPanel);
      }
   
   // Create new inventory panel
      inventoryPanel = new JPanel();
      inventoryPanel.setBounds(100, 200, 600, 250);
      inventoryPanel.setBackground(Color.white);
      inventoryPanel.setLayout(new GridLayout(1, 2)); // Split into two halves
      inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2)); // White border
      con.add(inventoryPanel);
   
   // Left panel for inventory items
      leftPanel = new JPanel();
      leftPanel.setBackground(Color.black);
      leftPanel.setLayout(new GridLayout(inventory.size(), 1)); // One item per row
      inventoryPanel.add(leftPanel);
   
   // Right panel for item description
      rightPanel = new JPanel();
      rightPanel.setBackground(Color.black);
      rightPanel.setLayout(new BorderLayout());
      inventoryPanel.add(rightPanel);
   
   // Create description area
      descriptionArea = new JTextArea();
      descriptionArea.setFont(normalFont);
      descriptionArea.setForeground(Color.white);
      descriptionArea.setBackground(Color.black);
      descriptionArea.setLineWrap(true);
      descriptionArea.setWrapStyleWord(true);
      descriptionArea.setEditable(false);
      rightPanel.add(descriptionArea, BorderLayout.CENTER);
   
   // Add inventory items to left panel
      if (inventory.isEmpty()) {
         JLabel emptyLabel = new JLabel("No items in inventory");
         emptyLabel.setForeground(Color.white);
         leftPanel.add(emptyLabel);
      } else {
         for (String item : inventory) {
            JLabel itemLabel = new JLabel(item);
            itemLabel.setForeground(Color.white);
            itemLabel.setFont(normalFont);
         
         // Add mouse listener to update description on click
            itemLabel.addMouseListener(
               new java.awt.event.MouseAdapter() {
                  @Override
                  
                  
                  public void mouseClicked(java.awt.event.MouseEvent e) {
                     displayItemDescription(item);
                  }
               });
         
            leftPanel.add(itemLabel);
         }
      }
      inventoryPanel.setVisible(true);
      con.validate();
      con.repaint();
   }

   private void displayItemDescription(String item) {
   // This method updates the description area based on the selected item
   // For example, you can use a switch case or if statements to set different descriptions
      switch (item) {
         case "weapon":
            descriptionArea.setText("A basic wooden stick. Useful for basic defense.");
            break;
      // Add cases for other items here
         default:
            descriptionArea.setText("Description not available.");
            break;
      }
   }

//creating button action called TitleScreenHandler, when button click, the code within this will be executed
   public class TitleScreenHandler implements ActionListener {
   
      public void actionPerformed(ActionEvent event) {
         askPlayerName();
      }
   }
//Handles multiple choices
   public class ChoiceHandler implements ActionListener {
   
      public void actionPerformed(ActionEvent event) {
      
      //determines which button was clicked since actionlistener is attached to multiple buttons
         String yourChoice = event.getActionCommand();
      
         switch (yourChoice) {
            case "c1":
               exitBathroom();
               break;
            case "c2":
               checkPockets();
               break;
         }
      }
   }
}