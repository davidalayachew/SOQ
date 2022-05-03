import java.util.Collection;
import java.util.List;

public class SOQ_20220429
{

   class AgentA
   {
   
      public final boolean condition1;
      public final boolean condition2;
      
      /** Potentially other fields. */
      
      public AgentA(boolean condition1, boolean condition2)
      {
      
         this.condition1 = condition1;
         this.condition2 = condition2;
      
      }
      
      public boolean bothTrue()
      {
      
         return condition1 && condition2;
      
      }
      
      public String toString()
      {
      
         return
            this.getClass().getName() 
               + "{ condition1 = " + this.condition1
               + ", condition2 = " + this.condition2 + "}";
      
      }
      
      /** Potentially other methods. */
   
   }
   
   class AgentB extends AgentA { 
      public AgentB(boolean c1, boolean c2) { super(c1, c2); }
   }
   
   class AgentC extends AgentA { 
      public AgentC(boolean c1, boolean c2) { super(c1, c2); }
   }
   
   class AgentD extends AgentA { 
      public AgentD(boolean c1, boolean c2) { super(c1, c2); }
   }
   
   public SOQ_20220429()
   {
   
      final List<AgentB> popB = List.of(new AgentB(false, false), new AgentB(true, true));
      final List<AgentC> popC = List.of(new AgentC(false, false), new AgentC(true, true));
      final List<AgentD> popD = List.of(new AgentD(false, false), new AgentD(true, true));
      
      System.out.println(findFirst(popB));
      System.out.println(findFirst(popC));
      System.out.println(findFirst(popD));
   
   }
   
   public <T extends AgentA> T findFirst(Collection<T> popT)
   {
   
      for (T each : popT)
      {
      
         if (each.bothTrue())
         {
         
            return each;
         
         }
      
      }
   
      throw new IllegalStateException("Couldn't find one!");
   
   }
   
   public static void main(String[] args)
   {
   
      new SOQ_20220429();
   
   }

}