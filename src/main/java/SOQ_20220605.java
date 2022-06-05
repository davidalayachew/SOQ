import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class SOQ_20220605
{

   enum MyEnum1 { A, B, C, ; }
   enum MyEnum2 { A, B, C, ; }
   enum MyEnum3 { A, B, C, ; }
   
   public static void main(String[] args)
   {
   
      method1(List.of(MyEnum1.class, MyEnum2.class, MyEnum3.class));
   
   }
   
   public static void method1(List<Class<? extends Enum<?>>> clazzez)
   {
   
      Class<? extends Enum<?>> clazz = clazzez.get(0);
      
      // EnumSet.allOf(clazz);
   // 
      // clazzez.stream().forEach(eclass -> EnumSet.allOf(eclass));
   
   }

}