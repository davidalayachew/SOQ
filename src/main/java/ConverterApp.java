import java.util.Scanner;

public class ConverterApp
{
   public static void main(String[] args){
      poundsToKilos(10);
      kilosToPounds(10);
      milesToKilometres(10);
      kilometresToMiles(10);
      metresToYards(10);
      
   }
   /**method to convert from pounds to kilos and display the information */
   public static void poundsToKilos(double pounds){
      System.out.println(pounds + " pounds is equal to " + (pounds * 0.453592) + " kg.");
   }
   public static void kilosToPounds(double kilos){
      System.out.println(kilos + " kilograms is equal to " + (kilos * 2.205) + " lbs.");
   }
   public static void milesToKilometres(double miles){
      System.out.println(miles + " miles is equal to " + (miles * 1.60934) + " km.");
   }
   public static void kilometresToMiles(double kilometres){
      System.out.println(kilometres + " kilomtres is equal to " + (kilometres * 0.621371) + " mi.");
   }
   public static void metresToYards(double metres){
      System.out.println(metres + " metres is equal to " + (metres * 1.09361) + " yds.");
   }   
}