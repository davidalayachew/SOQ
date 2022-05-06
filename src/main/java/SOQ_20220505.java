import java.util.concurrent.*;

public class SOQ_20220505 {

   static ExecutorService customExecutorService = Executors.newSingleThreadExecutor();

   private static void runTasks(int i) {
      System.out.printf("-- input: %s --%n", i);
   
      CompletableFuture<Void> signal_1 = CompletableFuture.supplyAsync(
         () -> {
            try {
               TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            throw new RuntimeException("Oh noes!");
         }, customExecutorService);
   
      CompletableFuture<Integer> signal_2 = CompletableFuture.supplyAsync(() -> 16 / i);
   
      CompletableFuture.allOf(signal_1, signal_2)
                .thenApplyAsync(
         justVoid -> {
            final int num = signal_2.join();
            System.out.println(num);
            return num;
         })
                .whenComplete(
         (result, exception) -> {
            if (exception != null) {
               System.out.println("exception occurs");
               System.err.println(exception);
            } else {
               System.out.println("no exception, got result: " + result);
            }
         })
                .thenApplyAsync(input -> input * 3)
                .thenAccept(System.out::println);
   
   }

   public static void main(String[] args) {
      runTasks(0);
      customExecutorService.shutdown();
   }

}