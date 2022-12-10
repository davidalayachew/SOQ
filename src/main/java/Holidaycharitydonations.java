import java.util.Scanner; //needed for input

public class Holidaycharitydonations {
   // static double[] cashDonations = new double[6];
// 
   // static double[] lbsFood = new double[6]; 
// 
   // static String[] siteName = new String[6]; 
   // static String bestSiteCash = " "; 
// 
   // static String bestSiteFood = " "; 
// 
   // static double totalCash = 0; 
// 
   // static double totalFood = 0; 
// 
   // static double maxFood = 0; 
   // static double maxCash = 0; 
// 
   // public static void main(String[] args) {
   // 
      // Scanner input = new Scanner(System.in); 
      // String runAgain = "yes"; //use runAgain
   // 
      // do {
      // 
         // getDonations();
         // processDonations();
         // displayDonations();
      // 
      // 
         // System.out.print("Enter yes if you want to run again: ");
         // runAgain = input.next();
         // input.nextLine(); 
         // System.out.print("\n\n\n");
      // } while (runAgain.equalsIgnoreCase("yes"));
   // 
   // } // end of main
// 
   // public static void getDonations() {
      // Scanner input = new Scanner(System.in); 
   // 
      // for (int i = 0; i < 6; i++) {
         // System.out.println("Enter site " + (i + 1) + " name: ");
         // siteName[i] = input.next();
         // input.nextLine();//fixes skipping issue
      // 
         // System.out.println("Enter cash donation(USD) for" + siteName[i] + ":   ");
         // cashDonations[i] = input.nextDouble();
         // while (cashDonations[i] < 1 )
         // {
            // System.out.println("SORRY - must be greater than 1 USD");
            // System.out.print("Enter cash donation(USD) for " + siteName[i] + ": ");
            // cashDonations[i] = input.nextDouble();
         // }
         // System.out.println("Enter food donation(lbs.) for " + siteName[i] + ": ");
         // lbsFood[i] = input.nextDouble();
      // 
         // while (lbsFood[i] < 1)
         // {
            // System.out.println("SORRY - must be greater than 1lb");
            // System.out.println("Enter food donation(lbs.) for " + siteName[i] + ": ");
            // lbsFood[i] = input.nextDouble();
         // }
      // 
      // }
   // 
   // }
   // public static void displayDonations() {
   // 
      // System.out.println("\n\n\n");
      // System.out.println("\tHOLIDAY DONATION LOCATIONS REPORT\t");
      // System.out.println("\t---------------------------------\t");
      // System.out.println("\n\n\n");
   // 
   // 
      // for (int i = 0; i < 6; i++)
      // {
         // System.out.print("Site:" + siteName[i]); 
         // System.out.print("Individual Cash Donations: $" + cashDonations[i]);
         // System.out.print("Individual Food Donations:" + lbsFood[i]+ "lbs" );
         // System.out.print("\n");
      // 
      // }//End of for loop
   // 
      // System.out.print("\tHoliday Donation Totals Report\t");
      // System.out.println("\t----------------------------\t");
   // 
      // System.out.println("Total Cash Donations are $" + totalCash );
      // System.out.println("Total Food Donations are " + totalFood + "lbs" );
      // System.out.println("\n");
      // System.out.println("\tHoliday Donation Best Site Report\t");
      // System.out.println("\t---------------------------------\t");  
   // 
      // System.out.println("\n");
      // System.out.println("Best location site for cash donation:" + bestSiteCash );
      // System.out.println("Max cash collected was:" + maxCash );
   // 
      // System.out.println("\n");
      // System.out.println("Best location site for food donation:" + bestSiteFood );
      // System.out.println("Max food collected was:" + maxFood );
   // }// end of displayDonations()
// 
// 
// 
   // public static void processDonations() {
      // totalCash = 0;
      // totalFood = 0;
      // maxCash = cashDonations[0];
      // maxFood = lbsFood[0];
      // for (int i = 1; i < 6; i++)
      // {
         // if (cashDonations[i] > maxCash)
         // {
            // maxCash = cashDonations[i];
            // bestSiteCash = siteName[i];
         // } 
         // if (lbsFood[i] > maxFood)
         // {
            // maxFood = lbsFood[i];
            // bestSiteFood = siteName[i];
         // } 
      // }//End of for loop
   // 
   // 
      // for (int i = 0; i < 6; i++)
      // 
         // totalCash = totalCash + cashDonations[i];
      // totalFood = totalFood + lbsFood[i];
   // }
// 
}//end of processDonations

