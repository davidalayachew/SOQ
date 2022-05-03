public class CardObject {

   public static final int LENGTH = 19; // aka amount of cards  KEEP THIS UPDATED!
   public static final int BEDROCK = 2;

   String name;
   String baseType;
   int attack;
   int health;
   String[] tags;
   String description;

   CardObject(int card) {
      transform(card);
   }

   public void transform(int card) { //  init("" new String[]{}); break; copypasta
      switch (card) { // I will need to make eventually that stuff is replaced (WOB = when on board, OF = on faint, SOT = start of turn, EOT = end of turn)
         case -1 -> {}
         case  0 -> init("Zombie",         "mob", 2, 6, new String[]{"undead"}, "WOB: can Wield tools and weapons"); 
         case  1 -> init("Lava Bucket",  "spell", 0, 0, new String[]{}, "Give an enemy Burning 3 for 4 turns"); 
         case  2 -> init("Bedrock",      "block", 1, 3, new String[]{}, "Immune. Unbuffable. WOB: make allies on either side Immune"); 
         case  3 -> init("Creeper",        "mob", 6, 1, new String[]{}, "Thorns 6. Recoil."); 
         case  4 -> init("Spider",         "mob", 2, 5, new String[]{}, "WOB: can Combine with Skeleton to Transform into Spider Jockey"); 
         case  5 -> init("Skeleton",       "mob", 3, 6, new String[]{}, "OF: Gain Arrow"); 
         case  6 -> init("Spider Jockey",  "mob", 2, 5, new String[]{}, "Double Attack. OF: Summon Skeleton"); 
         case  7 -> init("Baby Zombie",    "mob", 3, 8, new String[]{}, "Fire Resistance."); 
         case  8 -> init("The Sun",      "spell", 0, 0, new String[]{}, "Give all enemy undead mobs burning for 6 turns"); 
         case  9 -> init("Lava Bucket",  "spell", 0, 0, new String[]{}, "Give an enemy burning 3 for 4 turns"); 
         case 10 -> init("Water Bucket",  "item", 0, 6, new String[]{}, "SOT: remove burning from allies"); 
         case 11 -> init("Dispenser",    "block", 0, 6, new String[]{}, "WOB: projectiles cost 1 or are free not sure"); 
         case 12 -> init("Arrow",        "spell", 0, 0, new String[]{}, "deal 2 damage"); 
         case 13 -> init("Air Block",    "block", 0, 1, new String[]{"unobtainable"}, ""); 
         case 14 -> init("Air",          "spell", 0, 0, new String[]{}, "Summon Air for the enemy"); 
         case 15 -> init("Bow",           "item", 0, 5, new String[]{}, "WOB: Arrows deal 2x damage. OB: gain 2 arrows"); 
         case 16 -> init("Crossbow",      "item", 0, 5, new String[]{}, "WOB: Arrows deal 3x damage once per turn. OB: gain 2 arrows"); 
         case 17 -> init("Dropper",      "block", 0, 6, new String[]{"powerable"}, "SOT: optionally play a random card in your hand at a discount"); 
         case 18 -> init("Bamboo Shoot", "block", 0, 4, new String[]{}, "EOT: Summon Bamboo"); 
         case 19 -> init("Bamboo",       "block", 0, 3, new String[]{"unobtainable"}, "EOT: for every bamboo shoot Summon a 0/2 Bamboo"); 
         case 20 -> {}
         case 21 -> {}
         case 22 -> {}
         case 23 -> {}
         case 24 -> {}
         case 25 -> {}
         case 26 -> {}
      
      }
   }

   private void init(String n, String bt, int a, int h, String[] t, String d) {
      this.name = n;
      this.baseType = bt;
      this.attack = a;
      this.health = h;
      this.tags = t;
      this.description = d;
   }
}