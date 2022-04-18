package io.github.davidalayachew;

import java.util.Arrays;
import java.util.List;

/** Enums. https://stackoverflow.com/questions/71517372 */
public class SOQ_20220406_0201
{

   /**
    * 
    * Main method.
    * 
    * @param   args  commandline arguments, should they be needed.
    * 
    */
   public static void main(String[] args)
   {
   
      enum Faction
      {
      
         F1,
         F2,
         F3,
         ALL,
         ;
      
      }
      
      enum Skill
      {
      
         SKILL_1(Faction.F1, "arbitraryArgs"),
         SKILL_2(Faction.F2, "arbitraryArgs"),
         SKILL_3(Faction.F3, "arbitraryArgs"),
         SKILL_ALL_CAN_USE(Faction.ALL, "arbitraryArgs"),
         ;
         
         private final Faction faction;
         private final String arbitraryArgs;
         
         Skill(Faction faction, String arbitraryArgs)
         {
         
            this.faction = faction;
            this.arbitraryArgs = arbitraryArgs;
         
         }
         
         public static List<Skill> fetchAllSkillsForFaction(final Faction faction)
         {
         
            return
               Arrays.stream(values())
                  .parallel()
                  .filter(each -> each.faction == faction)
                  .toList()
                  ;
         
         }
      
      }
      
      System.out.println(Skill.fetchAllSkillsForFaction(Faction.F1));
   
   }

}
