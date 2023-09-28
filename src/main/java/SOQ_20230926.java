
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class SOQ_20230926
{

   public static void main(String[] args) throws Exception
   {
   
      record Item(int index) {}
   
      final Stream<CompletableFuture<List<Item>>> pointA =
         create
         (
            List
               .of
               (
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(1), new Item(2), new Item(3)),
                  List.of(new Item(4), new Item(5), new Item(6)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9)),
                  List.of(new Item(7), new Item(8), new Item(9))
               )
         )
         ;
   
      final Stream<CompletableFuture<Item>> pointZ = convert(pointA);
   
      final Item item =
         pointZ
            .map(CompletableFuture::join)
            .filter(eachItem -> eachItem.index() == 4)
            .findFirst()
            .orElseThrow()
            ;
   
      System.out.println(item);
   
   }

   private static <T> Stream<CompletableFuture<T>> convert(final Stream<CompletableFuture<List<T>>> pointA)
   {
   
      return
         pointA
            .parallel()
            .map
            (
               eachFuture ->
                  eachFuture
                     .thenApply
                     (
                        batch ->
                           batch
                              .stream()
                              .map(eachElement -> CompletableFuture.supplyAsync(() -> eachElement))
                              .toList()
                     )
            )
            .map(CompletableFuture::join)
            .flatMap(List::stream)
            ;
   
   }

   private static <T> Stream<CompletableFuture<List<T>>> create(final List<List<T>> lists)
   {
   
      return
         lists
            .stream()
            .map
            (
               eachList ->
                  CompletableFuture
                     .supplyAsync
                     (
                        () ->
                        {
                        
                           System.out.println("COMPUTATION WAS PERFORMED");
                        
                           return eachList;
                        
                        }
                     )
         
            )
            ;
   
   }

}