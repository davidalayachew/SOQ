import java.util.List;
import java.util.function.Predicate;

public class SOQ_20220501
{

   public static void main(String[] args)
   {
   
      record TypeA(int a) {}
   
      record TypeB(boolean b) {}
      
      final List<TypeA> as = List.of(new TypeA(0), new TypeA(1), new TypeA(2), new TypeA(3), new TypeA(4));
      
      final List<TypeB> bs = List.of(new TypeB(true), new TypeB(false));
      
      var whateverA = filter(as, typeA -> typeA.a() % 2 == 1);
      
      System.out.println(whateverA);
   
      var whateverB = filter(bs, typeB -> typeB.b());
      
      System.out.println(whateverB);
   
   }

   public static <T, V> List<T> filter(List<T> typeAList, Predicate<T> predicate)
   {
   
      return
         typeAList.stream()
            .filter(predicate)
            .toList()
            ;
   
   }

}
