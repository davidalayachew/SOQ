import java.util.function.Consumer;

public class SOQ_20220528
{

   public static class Example
   {
   
      private Short a;
      private Integer b;
      private Long c;
   
      public Example() {}
   
      public Short getA() {
         return this.a;}
      public Integer getB() {
         return this.b;}
      public Long getC() {
         return this.c;}
   
      public void setA(Short a) {this.a = a;}
      public void setB(Integer b) {this.b = b;}
      public void setC(Long c) {this.c = c;}
      
      public String toString() { 
         return this.a + " " + this.b + " " + this.c; }
   
   }
   
   public static void main(String[] args)
   {
   
      final Example instanceName = new Example();
      
      final Short tuv = 1;
   
      set(instanceName::setA, Short.valueOf(tuv));
      set(instanceName::setB, Integer.valueOf(2));
      set(instanceName::setC, Long.valueOf(3));
   
   }
   
   public static <T> void set(Consumer<T> setter, T value)
   {
   
      setter.accept(value);
   
   }

}
