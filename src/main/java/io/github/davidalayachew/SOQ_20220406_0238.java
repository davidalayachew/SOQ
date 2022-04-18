package io.github.davidalayachew;

import java.util.Arrays;
import java.io.File;

/** Regex. https://stackoverflow.com/questions/71762001 */
public class SOQ_20220406_0238
{

   /**
    * 
    * Splits a String pathname by file separator.
    * 
    * @param   path  the pathname, as a String.
    * @return        the broken up pathname
    * 
    */
   public static String[] splitByPathString(final String path)
   {
   
      return path.replace("/in", "/").split(File.separator);
   
   }
   
   /**
    * 
    * Main method.
    * 
    * @param   args  commandline arguments, should they be needed.
    * 
    */
   public static void main(String[] args)
   {
   
      //splitByPathString();
   
   }

}