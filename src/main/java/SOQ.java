import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
import javax.swing.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.imageio.metadata.IIOMetadataNode;

public class SOQ
{

   public static void main(String[] args)
   {
   
      new SOQ();
   
   }

   public SOQ()
   {
   
      new SOQ_20230108();
   
   }
   
   public class SOQ_20230108
   {
   
      public static void main(String[] args)
      {
      
         System.out.println(getParentTypeList(EstimateFuelType.class));
         System.out.println(getParentTypeList(EstimateTransmissionType.class));
      
      }
   
      public interface ParentInterface<E extends Enum<E>> // pick a better name
      {
      
         String getName();
         String getValue();
         String getCode();
         
         public static <E extends Enum<E> & ParentInterface<E>> List<E> values(final Class<E> clazz)
         {
         
            return Arrays.asList(clazz.getEnumConstants());
         
         }
      
      }
   
      public enum EstimateFuelType implements ParentInterface<EstimateFuelType>
      {
      
         GASOLINE("가솔린", "A001", "001"),
         DIESEL("디젤", "A002", "002"),
         LPG_GENERAL("LPG(일반인 구입)", "A003", "003"),
         LPG_ALL("LPG", "A004", "004"),
         HYBRID("가솔린+전기", "A005", "005"),
         ELECTRONIC("전기", "A009", "009"),
         ETC("기타", "A999", "999");
      
         final String name;
         final String value;
         final String code;
      
         EstimateFuelType(String name, String value, String code) 
         {
            
            this.name = name;
            this.value = value;
            this.code = code;
         
         }
      
         public String getName() 
         {
            
            return name;
         
         }
      
         public String getValue() 
         {
            
            return value;
         
         }
      
         public String getCode() 
         {
            
            return code;
         
         }
      
      }
   
      public enum EstimateTransmissionType implements ParentInterface<EstimateTransmissionType>
      {
      
         AUTO("오토(A/T)", "A001", "001"),
         MANUAL("수동(M/T)", "A002", "002"),
         ETC("기타", "A999", "999");
      
         final String name;
         final String value;
         final String code;
      
         EstimateTransmissionType(String name, String value, String code) 
         {
            
            this.name = name;
            this.value = value;
            this.code = code;
         
         }
      
         public String getName() 
         {
            
            return name;
         
         }
      
         public String getValue() 
         {
            
            return value;
         
         }
      
         public String getCode() 
         {
            
            return code;
         
         }
      
      }
   
      public static <E extends Enum<E> & ParentInterface<E>> List<Map<String, Object>> getParentTypeList(Class<E> clazz) {
      
         final List<Map<String, Object>> typeList = new ArrayList<>();
      
         for(final E type : ParentInterface.values(clazz)) {
            final Map<String, Object> typeMap = new HashMap<>();
            typeMap.put("code", type.getCode());
            typeMap.put("value", type.getValue());
            typeMap.put("name", type.getName());
            typeList.add(typeMap);
         }
      
         return typeList;
      
      }
   
      public static <E extends Enum<E> & ParentInterface<E>> List<Map<String, Object>> getGenericTypeList(final Class<E> clazz) {
      
         List<Map<String, Object>> fuelList = new ArrayList<>();
      
         for(E fuelType : ParentInterface.values(clazz)) {
            Map<String, Object> transmission = new HashMap<>();
            transmission.put("code", fuelType.getCode());
            transmission.put("value", fuelType.getValue());
            transmission.put("name", fuelType.getName());
            fuelList.add(transmission);
         }
      
         return fuelList;
      
      }
   
   }
   
   public class SOQ_20230102_1
   {
   
      public SOQ_20230102_1()
      {
      
         SwingUtilities.invokeLater(TestGui::new);
      
      }
   
      static class EntryListPanelTest extends JPanel {
         public EntryListPanelTest(String entryText){
            String[] entryTextSplit = entryText.split(":");
            setLayout(new BorderLayout());
            JButton button = new JButton(entryTextSplit[0]);
            JLabel label = new JLabel(entryTextSplit[1]);
            label.setVisible(false);
         
            add(button, BorderLayout.NORTH);
            add(label, BorderLayout.CENTER);
         
            button.addActionListener(e -> label.setVisible(!label.isVisible()));
         }
      }
   
      static class ListPanelTest extends JPanel {
         String[] entryTexts = {"dropdown1:content1", "dropdown2:content2", "dropdown3:content3"};
         ArrayList<EntryListPanelTest> entryPanels;
      
         public ListPanelTest(){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            entryPanels = new ArrayList<>();
            for (String entryText: entryTexts) {
               EntryListPanelTest entryListPanel = new EntryListPanelTest(entryText);
               entryPanels.add(entryListPanel);
               add(entryListPanel);
            }
         
            addMouseWheelListener(
               e -> {
                  for (EntryListPanelTest entryPanel : entryPanels) {
                     entryPanel.setLocation(entryPanel.getX(), entryPanel.getY() - e.getWheelRotation() * 20);
                  }
               });
         }
      }
   
      public static class TestGui extends JFrame {
      
         public TestGui() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            add(new ListPanelTest());
            pack();
            setVisible(true);
         }
      }
   
   }

   public static class SOQ_20230102
   {
   
      static interface MyEnum<E extends Enum<? extends E> & MyEnum<E, T>, T>
      {
      
         public static <E extends Enum<? extends E> & MyEnum<E, T>, T> E valueOfAttribute(Class<? extends E> enumClass, T attributeValue)
         {
         
            for (final E enumConstant : enumClass.getEnumConstants())
            {
            
               if (Objects.equals(enumConstant.attribute(), attributeValue))
               {
               
                  return enumConstant;
               
               }
            
            }
         
            throw new IllegalArgumentException("no enum constant, from " + enumClass + ", for " + attributeValue);
         
         }
      
         T attribute();
      
      }
   
      static interface YourEnum<E extends Enum<E> & MyEnum<E, String>> extends MyEnum<E, String>
      {
      
         @Override
         default String attribute()
         {
         
            return ((Enum<?>) this).name();
         
         }
      
      }
   
      static enum HisEnum implements YourEnum<HisEnum>
      {
      
         HIS,
         ;
      
      }
   
      static enum HerEnum implements YourEnum<HerEnum>
      {
      
         HER,
         ;
      
      }
   
      static <E extends Enum<E> & MyEnum<E, T>, T> E valueOfAttribute(T attributeValue, List<Class<? extends E>> enumClasses)
      {
      
         return
            enumClasses
               .stream()
               .map
               (
                  t ->
                  {
                  
                     try
                     {
                     
                        return MyEnum.valueOfAttribute(t, attributeValue); // won't compile
                     
                     }
                     
                     catch (IllegalArgumentException iae)
                     {
                     
                        return null;
                     
                     }
                  
                  }
               )
               .filter(Objects::nonNull)
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("no enum constant, from " + enumClasses + " for " + attributeValue));
      
      }
   
      public SOQ_20230102()
      {
      
         //System.out.println(valueOfAttribute("ABC", List.of(HerEnum.class, HisEnum.class)));
      
      }
   
   }

   public class SOQ_20221226_3
   {
   
      public SOQ_20221226_3()
      {
      
      
      
      }
   
      public void obviousSolution()
      {
      
         final CompletableFuture<Boolean> f1 = CompletableFuture.supplyAsync(() -> myMethod(1));
         final CompletableFuture<Boolean> f2 = CompletableFuture.supplyAsync(() -> myMethod(3));
         final CompletableFuture<Boolean> f3 = CompletableFuture.supplyAsync(() -> myMethod(5));
         final CompletableFuture<Boolean> f4 = CompletableFuture.supplyAsync(() -> myMethod(6));
      
         final var listOfResponses = List.of(f1, f2, f3, f4);
      
         final CompletableFuture<Object> result =
            CompletableFuture
               .allOf
               (
                  f1,
                  f2,
                  f3,
                  f4
               )
               .thenApply(__ -> listOfResponses.stream().filter(CompletableFuture::join).toList());
      
      
      
      }
   
      public static boolean myMethod(final int input)
      {
      
         return input % 2 == 0;
      
      }
   
   }

   public class SOQ_20221226_2
   {
   
      public SOQ_20221226_2()
      {
      
         System.out.println(someMathFunction(643));
      
      }
   
      public static boolean someMathFunction(final int num)
      {
      
         int a = 0;
      
         incrementCheck:
         {
         
            final int i1 = (num / 100) % 10;
            final int i2 = (num / 10)  % 10;
            final int i3 = num % 10;
         
            if ( i1 - i2 == i2 - i3 + 1 )
            {
            
               a++;
            
            }
         
         }
      
         forLoop:
         {
         
            for (int i = 1; Math.pow(10, i + 2) < num; i++)
            {
            
               final double i1;
               final double i2;
               final double i3;
            
               anotherCheck:
               {
               
                  final int index = i;
                  final IntToDoubleFunction func = scale -> (num / Math.pow(10, index + scale)) % 10;
               
                  i1 = func.applyAsDouble(2);
                  i2 = func.applyAsDouble(1);
                  i3 = func.applyAsDouble(0);
               
               }
            
               if (i1 - i2 == i2 - i3 + 1)
               {
               
                  a++;
               
               }
            
            }
         
         }
      
         returnBlock:
         {
         
            if ( (Math.pow(10, a+1) < num) && (Math.pow(10, a+2) == num) )
            {
            
               return true;
            
            }
            
            else
            {
            
               return false;
            
            }
         
         }
      
      }
   
   }

   public class SOQ_20221226_1
   {
   
      public SOQ_20221226_1()
      {
      
      
         new FunctionsAndGenerics();
      
      
      }
   
      @FunctionalInterface
      public interface SetNodeFunction<T>
      {
      
         Function<NodeList, Set<T>> apply(Function<Node, T> mapTo);
      
      
      }
   
      public class FunctionsAndGenerics
      {
      
         public FunctionsAndGenerics()
         {
         
            final SetNodeFunction<String> function = (mapTo) -> (nl ->
                  IntStream
                     .range(0, nl.getLength())
                     .mapToObj(i -> mapTo.apply(nl.item(i)))
                     .collect(Collectors.toUnmodifiableSet()))
               ;
         
            final IIOMetadataNode second = new IIOMetadataNode();
            second.setAttribute("abc", "xyz");
         
            final IIOMetadataNode nl = new IIOMetadataNode();
            nl.setAttribute("abc", "valuesomething");
            nl.appendChild(second);
         
            final Function<Node, String> mapTo = node -> String.valueOf(node.getAttributes().getLength());
         
            final Function<NodeList, Set<String>> finalFunction = function.apply(mapTo);
         
         
         
            final Set<String> output = finalFunction.apply(nl);
         
            System.out.println(output);
         
         }
      
      }
   
   }

   public class SOQ_20221226
   {
   
      public SOQ_20221226()
      {
      
      
         new FunctionsAndGenerics((mapTo) -> (nl ->
                  IntStream
                     .range(0, nl.getLength())
                     .mapToObj(i -> mapTo.apply(nl.item(i)))
                     .collect(Collectors.toUnmodifiableSet()))
            );
      
      
      }
   
      @FunctionalInterface
      public interface SetNodeFunction<T>
      {
      
         Function<NodeList, Set<T>> apply(Function<Node, T> mapTo);
      
      
      }
   
      public class FunctionsAndGenerics
      {
      
         public FunctionsAndGenerics(SetNodeFunction<String> function)
         {
         
            final IIOMetadataNode second = new IIOMetadataNode();
            second.setAttribute("abc", "xyz");
         
            final IIOMetadataNode nl = new IIOMetadataNode();
            nl.setAttribute("abc", "valuesomething");
            nl.appendChild(second);
         
            final Function<Node, String> mapTo = node -> String.valueOf(node.getAttributes().getLength());
         
            final Function<NodeList, Set<String>> finalFunction = function.apply(mapTo);
         
         
         
            final Set<String> output = finalFunction.apply(nl);
         
            System.out.println(output);
         
         }
      
      }
   
   }

   public class Visualization extends JPanel implements Scrollable, MouseWheelListener
   {
      private double zoomFactor = 1;
      private double prevZoomFactor = 1;
      private boolean zoomer;
      private double xOffset = 0;
      private double yOffset = 0;
   
      public Visualization()
      {
         addMouseWheelListener(this);
         Font currentFont = getFont();
         Font newFont = currentFont.deriveFont(currentFont.getSize() * 15F);
         setFont(newFont);
         setBackground(Color.WHITE);
      }
   
      @Override
      protected void paintComponent(Graphics g)
      {
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g.create();
      
         if(zoomer)
         {
            AffineTransform at = new AffineTransform();
         
            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();
         
            double zoomDiv = zoomFactor / prevZoomFactor;
         
            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;
         
            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2d.transform(at);
            zoomer = false;
         }
      
         g2d.drawString("1234567890", 500, 200);
      
         g2d.dispose();
      }
   
      @Override
      public void mouseWheelMoved(MouseWheelEvent e)
      {
         zoomer = true;
      
         if (e.getWheelRotation() < 0)
         {
            zoomFactor *= 1.1;
            repaint();
         }
      
         if (e.getWheelRotation() > 0)
         {
            zoomFactor /= 1.1;
            repaint();
         }
      }
   
      @Override
        public Dimension getPreferredSize()
      {
         return new Dimension(2000, 800);
      }
   
      @Override
        public Dimension getPreferredScrollableViewportSize()
      {
         return new Dimension(1550, 800);
      }
   
      @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
      {
         return 32;
      }
   
      @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
      {
         return 32;
      }
   
      @Override
        public boolean getScrollableTracksViewportWidth()
      {
         return false;
      }
   
      @Override
        public boolean getScrollableTracksViewportHeight()
      {
         return false;
      }
   }

   public class SomeTest implements Runnable
   {
   
      public SomeTest()
      {
      
      
      
      }
   
      @Override
      public void run()
      {
         JFrame f = new JFrame("Example");
         Visualization v = new Visualization();
         JScrollPane sp = new JScrollPane(v);
      
         MouseAdapter mouseAdapter =
            new MouseAdapter()
            {
               private Point origin;
            
               @Override
               public void mousePressed(MouseEvent e)
               {
                  origin = new Point(e.getPoint());
               }
            
               @Override
               public void mouseDragged(MouseEvent e)
               {
                  JViewport vp = null;
               
                  if (origin != null)
                  {
                     vp = sp.getViewport();
                  }
               
                  if (vp != null)
                  {
                     int deltaX = origin.x - e.getX();
                     int deltaY = origin.y - e.getY();
                  
                     Rectangle view = vp.getViewRect();
                     view.x += deltaX;
                     view.y += deltaY;
                  
                     v.scrollRectToVisible(view);
                  }
               }
            };
      
         sp.getViewport().addMouseListener(mouseAdapter);
         sp.getViewport().addMouseMotionListener(mouseAdapter);
      
         f.add(sp);
         f.setContentPane(sp);
         f.pack();
         f.setLocationRelativeTo(null);
         f.setVisible(true);
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
   }

   public class IA2 extends JFrame implements ActionListener{
   
   
   
      JButton total = new JButton();
      JButton back = new JButton();
   
      JFrame frame1 = new JFrame();
      JFrame frame2 = new JFrame();
   
   
      IA2(){
      
      
         frame1.setTitle("Lab equipement");
         frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame1.setLocationByPlatform(true);
         frame1.setVisible(true);
         frame1.setResizable(false);
         frame1.getContentPane().setBackground(new Color(5, 25, 50));
      
         frame2.setTitle("Total Equipement");
         frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame2.setLocationByPlatform(true);
         //frame2.setVisible(true);
         frame2.setResizable(false);
         frame2.getContentPane().setBackground(new Color(5, 25, 50));
      
         total = new JButton();
         frame1.add(total);
         total.addActionListener(this);
         total.setText("Total Equipement");
         total.setFocusable(false);
      
         back = new JButton();
         frame2.add(back);
         back.addActionListener(this);
         back.setText("Back");
         back.setFocusable(false);
      
         frame1.pack();
      
      }
   
      @Override
      public void actionPerformed(ActionEvent e) {
      
         if(e.getSource()==total) {
         
            frame1.dispose();
            frame2.setVisible(true);
            frame2.pack();
         
         }
      
      
         if(e.getSource()==back) {
         
            frame2.dispose();
            frame1.setVisible(true);
            frame1.pack();
         }
      }
   
   }

   private class SOQ_20221222_1
   {
   
      public SOQ_20221222_1()
      {
      
         Long a = 100L;
         Long b = 100L;
      
         Long c = 101L;
         Long d = 101L;
      
         Long e = 102L;
         Long f = 102L;
      
         Long g = 126L;
         Long h = 126L;
      
         Long i = 127L;
         Long j = 127L;
      
         Long k = 128L;
         Long l = 128L;
      
         Long m = 129L;
         Long n = 129L;
      
         System.out.println(a == b);
         System.out.println(c == d);
      
         System.out.println(e == f);
         System.out.println(g == h);
      
         System.out.println(i == j);
         System.out.println(k == l);
      
         System.out.println(m == n);
      
      }
   
   }

   private class SOQ_20221222
   {
   
      public static class AllocationContext {
         Iterator<AllocationCandidateSet> currentPlacementZoneAllocationCandidatesProvider;
         AllocationCandidateSet currentCandidateSet;
      
         AllocationCandidateSet firstSuccessfulCandidateSet;
      
         int iteration = 0;
      }
   
      public static class AllocationCandidateSet {
      
      }
   
      public void start()
      {
      
         List<AllocationCandidateSet> candidates = new ArrayList<>();
         IntStream.range(0, 5000).forEach(__ -> candidates.add(new AllocationCandidateSet()));
         AllocationContext ctx = new AllocationContext();
         ctx.currentPlacementZoneAllocationCandidatesProvider = candidates.iterator();
      
         SOQ_20221222 main = new SOQ_20221222();
         main.attemptAllocation(ctx).join();
      
         System.out.println(ctx.firstSuccessfulCandidateSet);
         System.out.println(ctx.iteration);
      
      }
   
      private CompletableFuture<AllocationContext> attemptAllocation(AllocationContext context) {
         return CompletableFuture.completedFuture(context)
            .thenCompose(this::getNextCandidateSet)
            .thenCompose(this::runAffinityFilters)
            .thenCompose(
            ctx -> {
               if (ctx.firstSuccessfulCandidateSet == null
                        && context.currentPlacementZoneAllocationCandidatesProvider.hasNext()) {
                  return attemptAllocation(ctx);
               } else {
                  return CompletableFuture.completedFuture(ctx);
               }
            });
      }
   
      private CompletableFuture<AllocationContext> getNextCandidateSet(AllocationContext ctx) {
      // For the sake of simplicity, I omitted most of the logic inside this method.
         ctx.currentCandidateSet = ctx.currentPlacementZoneAllocationCandidatesProvider.next();
         ctx.iteration++;
         return CompletableFuture.completedFuture(ctx);
      }
   
      private CompletableFuture<AllocationContext> runAffinityFilters(AllocationContext ctx) {
      // This is a long running async operation doing DB calls
         return CompletableFuture.completedFuture(ctx)
            .thenCompose(
            context -> {
            
               if (context.iteration == 1875)
               {
               
                  System.out.println("");
               
               }
                // Do some DB calls and run business logic and evaluate if the current AllocationCandidateSet is successful
               boolean success = context.iteration == 1876;
               return CompletableFuture.completedFuture(success);
            })
            .thenAccept(
            success -> {
               if (success) {
                  ctx.firstSuccessfulCandidateSet = ctx.currentCandidateSet;
               }
            })
            .thenApply(__ -> ctx);
      }
   
   }

   private class SOQ_20221220
   {
   //
      // public class Main
      // {
      //
         // public Main()
         // {
         //
            // WindowPanel winP = new WindowPanel();
            // winP.myWindowPanel();
         //
            // CharSelection charSelection = new CharSelection();
            // charSelection.myCharSelection();
         //
         // }
      //
      //
      // }
   //
      // public class GamePanel extends JPanel implements  Runnable{
      //
      // //Window settings variables
      //    //KeyHandler KH = new KeyHandler();
         // final int originalTileSize = 16; //16x16px
         // final int scale = 3;
         // final int tileSize = originalTileSize * scale; //48x48px
         // final int maxScreenCol = 16; //Tiles in a column
         // final int maxScreenRow = 12; //Tiles in a row
         // final int maxScreenWidth = tileSize * maxScreenCol;
         // final int maxScreenHeight = tileSize * maxScreenRow;
      //
      //
         // Thread gameThread;
      //
         // Player player = new Player(this, KH);
         // int framesPS = 60;
      //
      //
      // //Player pos
         // int playerX = 100;
         // int playerY = 100;
         // int playerSpeed = 4;
         // public GamePanel(){
         //
            // this.setVisible(false);
         //
         // //Window Settings with variable implementation
            // this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
            // this.setBackground(Color.white);
            // this.setDoubleBuffered(true);
            // this.addKeyListener(KH);
            // this.setFocusable(true);
         //
         // //////////7
            // JButton exit = new JButton();
            // JButton moreSize = new JButton();
            // JButton lessSize = new JButton();
         //
            // exit.setSize(30, 30);
            // moreSize.setSize(30, 30);
            // lessSize.setSize(30, 30);
         //
         //
         // //exit.setVisible(true);
            // exit.setBackground(Color.BLUE);
            // this.add(exit);
         // ///////
         // //optionSel = "Archer";
         //
            // JButton butW = new JButton("^");
            // butW.setSize(500, 500);
            // butW.setBackground(Color.BLUE);
            // this.add(butW);
            // butW.setVisible(true);
            // butW.revalidate();
            // butW.repaint();
         //
            // this.setVisible(true);
            // this.repaint();
            // this.revalidate();
         //
         //
         //
         // }
      //
      //
         // public void startGameThread(){
            // gameThread = new Thread(this);
            // gameThread.start();
         //
         //
         // }
      //
         // public void update(){
         // //System.out.println(playerY);
         //
         // //System.out.println(upP);
            // if(KH.upPressed){
               // playerY -= playerSpeed;
            // }
            // else if(KH.downPressed == true){
               // playerY += playerSpeed;
            // }
            // else if(KH.leftPressed == true){
               // playerX -= playerSpeed;
            // }
            // else if(KH.rightPressed){
               // playerX += playerSpeed;
            // }
         //
         // }
      //
         // public void run(){
         //
            // double drawInterval = 1000000000/framesPS;
            // double delta = 0;
            // long lastTime = System.nanoTime();
            // long currentTime;
            // long timer = 0;
            // int drawCount = 0;
         //
            // while(gameThread != null){
            //
               // currentTime = System.nanoTime();
               // delta += (currentTime - lastTime)/drawInterval;
               // timer += (currentTime-lastTime);
               // lastTime = currentTime;
               // if (delta >= 1){
                  // update();
                  // repaint();
                  // delta--;
                  // drawCount++;
               // }
               // if(timer >= 1000000000){
                  // System.out.println("System FPS= "+drawCount);
                  // drawCount = 0;
                  // timer = 0;
               // }
            //
            //
            //
            //
            // }
         // }
      //
      //
         // public void paintComponent(Graphics g){
            // super.paintComponent(g);
         //
            // Graphics2D g2 = (Graphics2D)g;
         // //System.out.println("painting");
         //
            // g2.setColor(Color.GREEN);
            // g2.fillRect(playerX, playerY, tileSize, tileSize);
         //
         //
         //
         //
         //
            // g2.dispose();
         //
         // }
      //
      //
      //
      // }
   //
      // public class WindowPanel {
      //
         // public boolean startThread;
      //
         // public void myWindowPanel(){
         //
            // JFrame window = new JFrame();
         // //Window Creation
         //
            // window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // window.setResizable(false);
            // window.setTitle("My Game");
            // window.setLocation(0, 0);//No le indica un lugar especifico donde salir, por lo que sale en el medio
         //
         //
         //
         //
         //
         //
            // GamePanel gamePanel = new GamePanel();
            // window.add(gamePanel);
         //
         // ///
            // JButton butW = new JButton("^");
            // butW.setSize(100, 100);
            // butW.setBackground(Color.BLUE);
            // gamePanel.add(butW);
            // butW.setVisible(true);
         //
            // butW.revalidate();
            // butW.repaint();
         //
         // ///
         //
         //
            // window.pack();
         //
         //
         //
         //
         //
            // window.setLocationRelativeTo(null);
            // window.setVisible(true);
            // gamePanel.startGameThread();
         //
         //
         //
         //
         // }
      //
      //
      // }
   //
   }

   private class SOQ_20221219_3
   {
   
      public static final int MAX = 1_000_000;
   
      public SOQ_20221219_3()
      {
      
         final int correctAnswer = new Random().nextInt(MAX);
         int chancesLeft = 15;
      
         int upperBound = MAX;
         int lowerBound = 1;
      
         while (chancesLeft-- > 0)
         {
         
            String playerResponse = "";
         
            while (playerResponse == null || !playerResponse.trim().matches("[0-9]+"))
            {
            
               playerResponse = JOptionPane.showInputDialog("Enter a number between " + lowerBound + " and " + upperBound);
            
            }
         
            final int choice = Integer.parseInt(playerResponse);
         
            final int midPoint = (upperBound - lowerBound) / 2;
         
         }
      
      }
   
   }

   private class SOQ_20221219_2
   {
   
      private enum Tool { HAMMER, NAIL, DRILL, SCREW, SAW, WOOD, METAL, ; }
   
      private enum Project
      {
      
         WORK_BENCH(Tool.HAMMER, Tool.NAIL, Tool.SAW, Tool.WOOD),
         STOP_SIGN(Tool.DRILL, Tool.SCREW, Tool.METAL),
         SHED(Tool.HAMMER, Tool.NAIL, Tool.DRILL, Tool.SCREW, Tool.SAW, Tool.WOOD, Tool.METAL),
         TOOL_HANGER(Tool.HAMMER, Tool.NAIL, Tool.WOOD),
         ;
      
         public final Set<Tool> requiredTools;
      
         Project(Tool... requiredTools)
         {
         
            this.requiredTools = Set.of(requiredTools);
         
         }
      
         public boolean canMakeWithGivenTools(Set<Tool> givenTools)
         {
         
            Objects.requireNonNull(givenTools);
         
            return givenTools.containsAll(this.requiredTools);
         
         }
      
      }
   
      public SOQ_20221219_2()
      {
      
         final Set<Tool> tools = EnumSet.of(Tool.HAMMER, Tool.NAIL, Tool.SAW, Tool.WOOD);
      
         for (final Project eachProject : Project.values())
         {
         
            if (eachProject.canMakeWithGivenTools(tools))
            {
            
               System.out.println(eachProject + " can be made with " + tools);
            
            }
         
         }
      
      }
   
   }

   private class SOQ_20221219_1
   {
      private static final String NUMBER_ONE = "number.one";
      private static final String NUMBER_TWO = "number.two";
      private static final String NUMBER_THREE = "number.three";
      private static final String NUMBER_FOUR = "number.four";
      private static final String NUMBER_FIVE = "number.five";
   
      // ... more error names
   
      public void handler(RuntimeException exception, String customerNo, String code) {
         if (is(NUMBER_ONE, exception)) {
            throw new RuntimeException();
         } else if (is(NUMBER_TWO, exception)) {
            throw new RuntimeException();
         } else if (is(NUMBER_THREE, exception)) {
            throw new RuntimeException();
         } else if (is(NUMBER_FOUR, exception)) {
            throw new RuntimeException();
         } else if (is(NUMBER_FIVE, exception)) {
            throw new RuntimeException();
         }
      }
   
      private boolean is(String errorCode, RuntimeException exception) {
         return Objects.equals(exception, errorCode);
      }
      public SOQ_20221219_1()
      {
      
      
      
      }
   
   }

   private void soq_20221219() // https://stackoverflow.com/questions/15671620/small-straight-method
   {
   
      final Consumer<List<Integer>> isSmallStraight =
         parameter ->
         {
         
            final List<Integer> list = new ArrayList<>(parameter);
         
            Collections.sort(list);
         
            int consecutiveCounter = 0;
         
            mainLoop:
            for (int index = 0; index < list.size() - 1; index++)
            {
            
               final int first = list.get(index);
               final int second = list.get(index + 1);
            
               if (Objects.equals(first, second))
               {
               
                  continue mainLoop;
               
               }
               
               else if (second - first == 1)
               {
               
                  consecutiveCounter++;
               
                  if (consecutiveCounter >= 3)
                  {
                  
                     System.out.println(parameter + "\tIt is a small straight");
                     break mainLoop;
                  
                  }
               
               }
               
               else
               {
               
                  consecutiveCounter = 0;
               
               }
            
            }
         
            if (consecutiveCounter < 3)
            {
            
               System.out.println(parameter + "\tIt is NOT a small straight");
            
            }
         
         };
   
      isSmallStraight.accept(List.of(1, 2, 4, 3, 6));
      isSmallStraight.accept(List.of(1, 2, 4, 3, 3));
      isSmallStraight.accept(List.of(1, 1, 4, 3, 3));
      isSmallStraight.accept(List.of(1, 3, 4, 3, 3));
      isSmallStraight.accept(List.of(1, 5, 4, 3, 3));
      isSmallStraight.accept(List.of(1, 2, 4, 5, 3));
      isSmallStraight.accept(List.of(1, 2, 4, 4, 3));
      isSmallStraight.accept(List.of(1, 2, 4, 2, 3));
   
   }

   private void soq_20221217_better() // https://stackoverflow.com/questions/74838895/java8-flatmap-mapstring-liststring-to-mapstring-integer-where-the-int
   {
   
      //https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summingInt-java.util.function.ToIntFunction-
      final Map<String, List<String>> map = new HashMap<>();
   
      map.put("abc", List.of("a", "b", "c"));
      map.put("defg", List.of("d", "e", "f", "g"));
      map.put("hijkl", List.of("h", "i", "j", "k", "l"));
   
      final
         Map<String, Integer>
         //var
         counts =
            map
               .entrySet()
               .stream()
               .collect(Collectors.toMap(Map.Entry::getKey, (each) -> each.getValue().size()))
               ;
   
      counts.entrySet().forEach(System.out::println);
   
   }

   private void soq_20221217() // https://stackoverflow.com/questions/74838895/java8-flatmap-mapstring-liststring-to-mapstring-integer-where-the-int
   {
   
      //https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summingInt-java.util.function.ToIntFunction-
      final Map<String, List<String>> map = new HashMap<>();
   
      map.put("abc", List.of("a", "b", "c"));
      map.put("defg", List.of("d", "e", "f", "g"));
      map.put("hijkl", List.of("h", "i", "j", "k", "l"));
   
      final
         Map<String, Integer>
         //var
         counts =
            map
               .entrySet()
               .stream()
               .flatMap(each -> Stream.of(Map.entry(each.getKey(), each.getValue().size())))
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
               ;
   
      counts.entrySet().forEach(System.out::println);
   
   }

   private void soq_20221209()
   {
   
   //https://stackoverflow.com/questions/74749257/how-to-convert-a-hashset-of-strings-to-hashmap-of-sub-strings-using-java-streams
      HashSet<String> hs = new LinkedHashSet<>(Arrays.asList(
         "('300','3000025863',9,'NB','B')",
         "('300','25863',10,'NB','B')",
         "('300','3000025863',9,'NB','XXXXXXXX')",  //Duplicated row, latest value
         "('300','3000025863',8,'NB','B')"));
   
      HashMap<String, String> hm = new LinkedHashMap<>(); //HasMap key = composite key of first the 3 elements
      int numKeys = 3;   //The number of elements in the composite key. Has to be variable
   //Is there a better way for convert HashSet to HashMap?
      hs.forEach(
         s -> {
            String sKey = "";
            String[] aux = s.split(",",numKeys+1);
            for (int i=0; i<numKeys; i++){
               sKey = sKey+aux[i]+",";
            }
            hm.put(sKey,aux[numKeys]);
         });
   
      HashSet<String> hsResult = new HashSet<>();  //HashSet without duplicates
      hm.forEach(
         (key, value) -> {
            hsResult.add(key+value);
         });
      System.out.println("-----------Result-----------");
      for (String s:hsResult) {
         System.out.println(s);
      }
   
   }

}