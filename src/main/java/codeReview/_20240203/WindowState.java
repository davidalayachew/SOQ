
package codeReview._20240203;

import java.util.ArrayList;
import java.util.List;

public final class WindowState<E>
{

   private final List<E> windowElements = new ArrayList<>();
   private WindowStatus windowStatus = WindowStatus.OPEN;

   public List<E> windowElements()
   {
   
      return this.windowElements;
   
   }

   public WindowStatus windowStatus()
   {
   
      return this.windowStatus;
   
   }
   
   public void windowStatus(final WindowStatus windowStatus)
   {
   
      this.windowStatus = windowStatus;
   
   }

}
