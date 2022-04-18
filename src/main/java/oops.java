class Kunoichi {
   String rank;
   int BobSize;

   public void printRecord(){
      System.out.println(this.rank + " " + this.BobSize);
      
   }

   Kunoichi(Kunoichi Hinata){
      this.rank = Hinata.rank;
      this.BobSize = Hinata.BobSize;
   }
   
   Kunoichi(String rank, int){
      this.rank = Hinata.rank;
      this.BobSize = Hinata.BobSize;
   }
   
}



public class oops {

   public static void main (String [] args){
   
      Kunoichi Tsunade = new Kunoichi("Jonin", 106);
   
   
      Kunoichi Hinata = new Kunoichi(Tsunade);
      Hinata.printRecord();
   }
}