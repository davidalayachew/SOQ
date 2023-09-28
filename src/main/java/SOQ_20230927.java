import java.util.Random;
import java.util.concurrent.*;

public class SOQ_20230927 {
   public static void main(String[] args) throws Exception {
      final int SIZE = 1_000_000_000;
      System.out.println("SIZE = " + SIZE);
      int[] input = generateInput(SIZE);
      par(input);
      seq(input);
   }

   private static void seq(int[] input) {
      long startTime = System.currentTimeMillis();
   
      long result = 0;
      for (int num : input) {
         result += num;
      }
      
      System.out.println("Result: " + result);
      System.out.println("Sequential time: " + Long.toString(System.currentTimeMillis() - startTime));
   }

   private static void par(int[] input) {
   
      int cores = Runtime.getRuntime().availableProcessors();
      System.out.println("Number of cores on this machine: " + Integer.toString(cores));
      
      System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
      ForkJoinPool forkJoinPool = new ForkJoinPool(8);
   
      long startTime = System.currentTimeMillis();
      RecursiveSumTask task = new RecursiveSumTask(input, 0, input.length);
      forkJoinPool.execute(task);
      forkJoinPool.invoke(task);
   
      System.out.println("Result: " + task.getResult());
      System.out.println("Parallel time: " + Long.toString(System.currentTimeMillis() - startTime));
   
   }

   public static int[] generateInput(int size) {
      Random random = new Random();
      int[] array = new int[size];
      for (int i = 0; i < size; i++) {
         array[i] = random.nextInt(5);
      }
      return array;
   }
   
}
