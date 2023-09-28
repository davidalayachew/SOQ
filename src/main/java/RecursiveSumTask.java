import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RecursiveSumTask extends RecursiveAction {

   private int[] input;
   private int start;
   private int end;
   private static final int THRESHOLD = 1000;
   private int result;

   public RecursiveSumTask(int[] input, int start, int end) {
      this.input = input;
      this.start = start;
      this.end = end;
   }

   @Override
   protected void compute() {
      if ((this.end - this.start) / 2 > THRESHOLD) {
         List<RecursiveSumTask> subTasks = createSubtasks();
         ForkJoinTask.invokeAll(subTasks);
         subTasks.forEach((n) -> this.result += n.getResult());
      } else {
         process();
      }
   }

   private List<RecursiveSumTask> createSubtasks() {
      List<RecursiveSumTask> subtasks = new ArrayList<RecursiveSumTask>();
      int mid = (end + start) / 2;
      subtasks.add(new RecursiveSumTask(input, this.start, mid));
      subtasks.add(new RecursiveSumTask(input, mid, this.end));
   
      return subtasks;
   }

   private void process() {
      for (int i = start; i < end; i++) {
         this.result += input[i];
      }
   }

   public int getResult() {
      return this.result;
   }
}