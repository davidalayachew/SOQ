
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClientServerExample
{

   public static void main(String[] args)
   {
   
      System.out.println("===========STARTING SYNCHRONOUS COMMUNICATION============");
      synchronousCommunication();
      System.out.println("===========FINISHED SYNCHRONOUS COMMUNICATION============");
   
      System.out.println("===========STARTING ASYNCHRONOUS COMMUNICATION============");
      asynchronousCommunication();
      System.out.println("===========FINISHED ASYNCHRONOUS COMMUNICATION============");
   
   }
   
   public static void synchronousCommunication()
   {
   
      Server server = new Server();
      Client client = new Client();
   
      String response = "";
   
      response = client.sendMessage("Good morning Server!", server).join();
      
      System.out.println("Client received the following response from the server -- {" + response + "}");
      
      response = client.sendMessage("Good evening Server!", server).join();
   
      System.out.println("Client received the following response from the server -- {" + response + "}");
   
   }
   
   public static void asynchronousCommunication()
   {
   
      Server server = new Server();
      Client client = new Client();
   
      List<CompletableFuture<String>> responses = new ArrayList<>();
   
      responses.add(client.sendMessage("Good morning Server!", server));
      responses.add(client.sendMessage("Good evening Server!", server));
   
      for (CompletableFuture<String> eachResponse : responses)
      {
      
         System.out.println("Client received the following response from the server -- {" + eachResponse.join() + "}");
      
      }
   
   
   }
   
   static class Server
   {
   
      CompletableFuture<String> respond(final String input)
      {
      
         System.out.println("Server received the following message -- {" + input + "}");
         
         return 
            CompletableFuture.supplyAsync(
               () -> 
               {
               
                  try
                  {
                  
                  //sleep for 2 seconds, to represent arbitrary delay in receiver processing
                     Thread.sleep(2000);
                  
                     return input.contains("morning") ? "Good morning to you too!" : "Good evening to you too!";
                  
                  }
                  
                  catch (Exception e)
                  {
                  
                     throw new IllegalStateException("What happened?", e);
                  
                  }
               
               });
      
      }
   
   }
   
   static class Client
   {
   
      CompletableFuture<String> sendMessage(String message, Server server)
      {
      
         System.out.println("Client is about to send the following message to the server -- {" + message + "}");
      
         return server.respond(message);
         
      }
   
   }

}