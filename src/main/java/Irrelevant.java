import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class Irrelevant
{

   public static void main(String[] args) throws Exception
   {
   
      final Color o = new Color(255, 0, 0, 255); //opaque red
      final Color t = new Color(0, 255, 0, 0);   //transparent green -- clear
   
      final Color[][] pixels = //red edges with (ideally) a clear center
         {
            {o, t, t, t, o},
            {o, t, t, t, o},
            {o, t, t, t, o},
            {o, t, t, t, o},
            {o, t, t, t, o}
         };
   
      BufferedImage image = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_ARGB);
   
      for (int y = 0; y < pixels.length; y++)
      {
         for (int x = 0; x < pixels[y].length; x++)
         {
            image.setRGB(x, y, pixels[y][x].getRGB());
         }
      }
   
      ImageIO.write(image, "gif", new File("abctest.gif"));
      ImageIO.write(image, "png", new File("abctest.png"));
   
   }

}