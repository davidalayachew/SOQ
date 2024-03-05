
package codeReview._20240203;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

/** NOT THREAD SAFE. */
public final class WindowsQueue<E>
{

   private final Map<WindowTurn, WindowState<E>> queues = new EnumMap<>(WindowTurn.class);

   private ArrayList<E> window1, window2;
   private WindowStatus windowStatus1, windowStatus2;
   private WindowTurn turn = WindowTurn.WINDOW1;

   public WindowsQueue(final E[] elements)
   {
   
      for (final WindowTurn windowTurn : WindowTurn.values())
      {
      
         queues.put(windowTurn, new WindowState<>());
      
      }
   
      for (final E elem : elements)
      {
      
         this.insert(elem);
      
      }
   
   }

   private void setWindowStatus1(WindowStatus status)
   {
   
      windowStatus1 = status;
   
   }

   private void setWindowStatus2(WindowStatus status)
   {
   
      windowStatus2 = status;
   
   }

   public WindowTurn getTurn()
   {
   
      return turn;
   
   }

   private void setTurn(WindowTurn turn)
   {
   
      this.turn = turn;
   
   }

   private Stream<WindowState<E>> values()
   {
   
      return
         this
            .queues
            .values()
            .stream()
            ;
   
   }

   public void closeQueue()
   {
   
      if (pendingElementsInQueue() > 0)
      {
      
         throw new IllegalStateException("Elements are pending");
      
      }
   
      this
         .values()
         .forEach(windowState -> windowState.windowStatus(WindowStatus.CLOSED))
         ;
   
   }

   public boolean isWindow1Open() {
      return windowStatus1 == WindowStatus.OPEN;
   }

   public boolean isWindow2Open() {
      return windowStatus2 == WindowStatus.OPEN;
   }

   public void closeWindow() {
      if (pendingElementsInWindow1() <= pendingElementsInWindow2()) {
         transfer(window1, window2, pendingElementsInWindow1());
         setWindowStatus1(WindowStatus.CLOSED);
         setTurn(WindowTurn.WINDOW2);
      } else {
         transfer(window2, window1, pendingElementsInWindow2());
         setWindowStatus2(WindowStatus.CLOSED);
         setTurn(WindowTurn.WINDOW1);
      }
   }

   public void openWindow() {
      if (windowStatus1 == WindowStatus.CLOSED) {
         setWindowStatus1(WindowStatus.OPEN);
         return;
      }
      if (windowStatus2 == WindowStatus.CLOSED)
         setWindowStatus2(WindowStatus.OPEN);
   }

   public boolean isInQueue(final E elem)
   {
   
      return
         this
            .queues
            .values()
            .stream()
            .map(WindowState::windowElements)
            .anyMatch(list -> list.contains(elem))
            ;
   
   }

   public int pendingElementsInQueue()
   {
   
      return
         this
            .values()
            .map(WindowState::windowElements)
            .mapToInt(List::size)
            .sum()
            ;
   
   }

   public int pendingElementsInWindow1() {
      return window1.size();
   }

   public int pendingElementsInWindow2() {
      return window2.size();
   }

   public void insert(final E elem)
   {
   
      Objects.requireNonNull(elem, "Cannot insert a null element!");
   
      IS_QUEUE_CLOSED:
      if (isQueueClosed())
      {
      
         throw new IllegalStateException("Both windows are closed");
      
      }
   
      IS_ELEMENT_IN_QUEUE:
      if (isInQueue(elem))
      {
      
         throw new IllegalStateException("Duplicate element");
      
      }
   
      final boolean areAllWindowsOpen =
         this
            .queues
            .values()
            .stream()
            .map(WindowState::windowStatus)
            .allMatch(WindowStatus.OPEN::equals)
            ;
   
      final List<E> windowElements;
   
      if (areAllWindowsOpen)
      {
      
         windowElements =
            this
               .values()
               .map(WindowState::windowElements)
               .min(Comparator.comparingInt(List::size))
               .orElseThrow()
               ;
      
      }
      
      else
      {
      
         windowElements =
            this
               .values()
               .filter(windowState -> WindowStatus.OPEN.equals(windowState.windowStatus()))
               .map(WindowState::windowElements)
               .findFirst()
               .orElseThrow()
               ;
      
      }
   
      windowElements.add(elem);
   
   }

   public boolean isQueueClosed() {
      return
         this
            .queues
            .values()
            .stream()
            .map(WindowState::windowStatus)
            .noneMatch(WindowStatus.OPEN::equals)
            ;
   }

   private void validateQueueEmpty() {
      if (pendingElementsInQueue() == 0)
         throw new IllegalStateException("Queue is empty");
   }

   private void validateWindowsClosed() {
      if (isQueueClosed())
         throw new IllegalStateException("Both windows are closed");
   }

   public E elementToServe()
   {
   
      validateQueueEmpty();
      validateWindowsClosed();
   
      final WindowTurn currentTurn = this.getTurn();
   
      final WindowState<E> windowState =
         this
            .queues
            .get(currentTurn)
            ;
   
      return
         windowState
            .windowElements()
            .getFirst()
            ;
   
   }

   public void serve()
   {
   
      validateQueueEmpty();
      validateWindowsClosed();
   
      final WindowTurn currentTurn = this.getTurn();
   
      final WindowState<E> windowState =
         this
            .queues
            .get(currentTurn)
            ;
   
      windowState
         .windowElements()
         .removeFirst()
         ;
   
      final Optional<WindowTurn> nextTurn =
         this
            .queues
            .entrySet()
            .stream()
            .filter(eachEntry -> WindowStatus.OPEN.equals(eachEntry.getValue().windowStatus()))
            .map(Map.Entry::getKey)
            .findAny()
            ;
   
      nextTurn
         .ifPresent(this::setTurn)
         ;
   
   }

   public void balance()
   {
   
      final boolean anyWindowsClosed =
         this
            .values()
            .map(WindowState::windowStatus)
            .filter(WindowStatus.CLOSED::equals)
            .findAny()
            .isPresent()
            ;
   
      if (anyWindowsClosed)
      {
      
         throw new IllegalStateException("Cannot balance");
      
      }
      
      final OptionalDouble potentialAvg = 
         this
            .values()
            .map(WindowState::windowElements)
            .mapToInt(List::size)
            .average()
            ;
   
      final int avg = (int) Math.rint(potentialAvg.orElseThrow());
   
   //    final var idk =
   //       this
   //          .values()
   //          .map(WindowState::windowElements)
   //          .mapToInt(List::size)
   //          ;
   
      int diff = pendingElementsInWindow1() - pendingElementsInWindow2();
      if (diff > 1) {
         transfer(window1, window2, diff / 2);
      } else if (diff < -1) {
         transfer(window2, window1, diff / 2);
      }
   }

   private void transfer(ArrayList<E> source, ArrayList<E> destination, int amount) {
      while (amount > 0) {
         destination.add(source.get(source.size() - amount));
         source.remove(source.size() - amount);
         amount--;
      }
   }
}