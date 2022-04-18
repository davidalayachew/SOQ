public class SOQ_20220415_1
{

   public static void main(String[] args)
   {
   
      Server server = new Server();
      Client client = new Client();
      
      client.sendMessage("Hello Server", server);
   
   }
   
   static class Server
   {
   
      String respond(String input)
      {
      
         String output = "";
         
         System.out.println("Server received the following message -- {" + input + "}");
         
         //do something
         
         return output;
      
      }
   
   }
   
   static class Client
   {
   
      void sendMessage(String message, Server server)
      {
      
         System.out.println("Client is about to send the following message to the server -- {" + message + "}");
      
         String response = server.respond(message);
         
         System.out.println("Client received the following response from the server -- {" + response + "}");
      
         //maybe do stuff with the response
      
      }
   
   }

}