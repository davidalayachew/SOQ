public abstract class MyService
{
   
   public abstract <T> T get(String key, Class<T> responseType);

   // public <T> T get(String key, String objType)
   // {
   //    
      // return
         // switch(objType)
         // {
         // 
         //    case "Customer" -> get(key, String.class.getClass());
         //    case "Product" -> get(key, Integer.class);
         // 
         // };
   //    
   // }

}