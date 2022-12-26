public class NonAttackingQueens {
   private int[][] board;
   private int solutionCount = 0;
   private boolean solutionFound = false;

   public NonAttackingQueens() {
      board = new int[8][8];
   }

   public boolean canPlace(int x, int y) {
        // Check if a queen is already placed at position (x, y)
      if (board[x][y] == 1) {
         return false;
      }
   
        // Check horizontal positions
      for (int i = 0; i < 8; i++) {
         if (board[x][i] == 1) {
            return false;
         }
      }
   
        // Check vertical positions
      for (int i = 0; i < 8; i++) {
         if (board[i][y] == 1) {
            return false;
         }
      }
   
        // Check diagonal positions
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            if (board[i][j] == 1 && (Math.abs(i - x) == Math.abs(j - y))) {
               return false;
            }
         }
      }
   
      return true;
   }
   
   public static void main(String[] args)
   {
   
      new NonAttackingQueens().solve();
   
   }

   public void solve() {
    // Check if the solutionCount has reached 92
      if (solutionCount == 92) {
         return;
      }
   
    // Check if all 8 queens have been placed
      int queensPlaced = 0;
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            if (board[i][j] == 1) {
               queensPlaced++;
            }
         }
      }
      if (queensPlaced == 8) {
        // All positions have been checked, so we have found a solution
         solutionCount++;
         System.out.println("Solution " + solutionCount + ":");
         print();
         return;
      }
   
    // Try to place a queen at each position on the board
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            if (canPlace(i, j)) {
                // Place a queen at position (i, j) and try to solve the rest of the board
               board[i][j] = 1;
               solve();
                // Backtrack: remove the queen from position (i, j) and try the next position
               board[i][j] = 0;
            }
         }
      }
   }

   public void print() {
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            if (board[i][j] == 1) {
               System.out.print(" X");
            } else {
               System.out.print(" O");
            }
         }
         System.out.println();
      }
      System.out.println("---------------");
   }
}