import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SOQ_20220516_1
{

   public static void main(String[] args)
   {
   
      Pattern reg = Pattern.compile("\\*");
   
      var result = reg.splitAsStream("A*B*C*D*E*F*G*A*B*C*D*E*F*A*B*C*D*E*F*G*H")
         .filter(role -> role.matches("A"))
         .map(String::trim)
         .toList();
   
      System.out.println(result);
   
   }

}