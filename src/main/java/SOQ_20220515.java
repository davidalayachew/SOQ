import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SOQ_20220515
{

   public class Game extends JFrame {
      private static final int width = 8;
      private static final int height = 8;
   
      private static Piece clicked;
   
      private static Piece[][] fields = new Piece[width][height];
   
      private JPanel main = new JPanel();
   
      public void init(JPanel g) {
         for (int y = 0; y < fields.length; y++) {
            for (int x = 0;  x < fields[y].length; x++) {
            
               if (y == 1) fields[y][x] = new Pawn(x, y, true); //2nd or 7th row is filled with pawns
               else if (y == 6) fields[y][x] = new Pawn(x, y, false);
               else {
                  fields[y][x] = new Empty(x,y,true);
               }
            
               fields[y][x].addActionListener(
                  e -> {
                     var p = (Piece) e.getSource();
                     System.out.println(p.getX() + p.getY());
                  });
            
               g.add(fields[y][x]);
            }
         }
      }
   
      public Game() {
         main.setBackground(Color.blue.darker());
         main.setLayout(new GridLayout(8,8));
         this.setSize(800,800);
      
         init(main);
         this.add(main);
      
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         this.setVisible(true);
      }
   
   }

   public abstract class Piece extends JButton {
      private int x;
      private int y;
      private final boolean isWhite;
   
      public Piece(int x, int y, boolean isWhite) {
         this.x = x;
         this.y = y;
         this.isWhite = isWhite;
      }
   
      public int getX() {
         return x;
      }
   
      public int getY() {
         return y;
      }
   
      public boolean isWhite() {
         return isWhite;
      }
   
      public boolean canMoveTo(int toX, int toY) {
         return true;
      }
   }
   
   public class Pawn extends Piece{
      public Pawn(int x, int y, boolean isWhite) {
         super(x, y, isWhite);
         this.setText(isWhite ? "Pawn" : "pawn");
      }
   }
   
   public class Empty extends Piece
   {
   
      public Empty(int x, int y, boolean isWhite)
      {
      
         super(x, y, isWhite);
         this.setText(isWhite ? "EMPTY" : "empty");
      
      }
   
   }
   
   public static void main(String[] args) {
      new SOQ_20220515();  
   }
   
   public SOQ_20220515()
   {
   
      var g = new Game();
   
   }
}