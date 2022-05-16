public class SOQ_20220516
{

   public static void main(String[] args)
   {
   
      int cnt = 0;
      int sum = 0;
   
      for(int i = 1; i <= 1000; i++)
      {
         if(cnt >= 5)
         {
            break;
         }
      
         if ((i%3 == 0) && (i%5 == 0))
         {
         
            System.out.println(i);
            sum += i;
            cnt++;
         
         }
      }
   
      System.out.println(sum);
      System.out.println(cnt);
   
   }

}