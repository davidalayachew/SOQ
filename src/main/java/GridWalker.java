import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GridWalker {
   public static void main(String[] args) {
      new GridWalker();
   }

   public GridWalker() {
      EventQueue.invokeLater(
         new Runnable() {
            @Override
            public void run() {
               JFrame frame = new JFrame();
               frame.add(new SwingCraft(new DefaultWorld(new DefaultChunkFactory())));
               frame.pack();
               frame.setLocationRelativeTo(null);
               frame.setVisible(true);
            }
         });
   }

   public class SwingCraft extends JPanel {
      protected enum Direction {
         UP, DOWN, LEFT, RIGHT
      }
   
      private Matrix<ChunkPane> chunkMatrix = new HashMapMatrix<ChunkPane>();
   
      private World world;
      private Point playerLocation = new Point(0, 0);
   
      @SuppressWarnings("this-escape")
      public SwingCraft(World world) {
         setLayout(new BorderLayout());
         this.world = world;
         setChunk(playerLocation);
         InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
         ActionMap actionMap = getActionMap();
      
         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), Direction.LEFT);
         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), Direction.RIGHT);
         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), Direction.UP);
         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), Direction.DOWN);
      
         actionMap.put(Direction.LEFT, new MoveAction(-1, 0));
         actionMap.put(Direction.RIGHT, new MoveAction(1, 0));
         actionMap.put(Direction.UP, new MoveAction(0, -1));
         actionMap.put(Direction.DOWN, new MoveAction(0, 1));
      }
   
      public void setChunk(Point location) {
         ChunkPane pane = chunkMatrix.get(location.x, location.y);
         if (pane == null) {
            Chunk chunk = world.getChunkAt(location.x, location.y);
            pane = new ChunkPane(chunk);
            chunkMatrix.put(pane, location.x, location.y);
         }
      
         removeAll();
         add(pane);
      
         revalidate();
         repaint();
      }
   
      protected void moveBy(int xDelta, int yDelta) {
         int targetX = playerLocation.x + xDelta;
         int targetY = playerLocation.y + yDelta;
      
         playerLocation.x = targetX;
         playerLocation.y = targetY;
      
         setChunk(playerLocation);
      }
   
      protected class MoveAction extends AbstractAction {
         private int xDelta, yDelta;
      
         public MoveAction(int xDelta, int yDelta) {
            this.xDelta = xDelta;
            this.yDelta = yDelta;
         }
      
         @Override
         public void actionPerformed(ActionEvent e) {
            moveBy(xDelta, yDelta);
         }
      }
   }

   public class ChunkPane extends JPanel {
      private Chunk chunk;
   
      @SuppressWarnings("this-escape")
      public ChunkPane(Chunk chunk) {
         this.chunk = chunk;
      
         this.
            setBackground
            (
               switch (chunk.biome())
               {
               
                  case  FOREST   -> Color.GREEN;
                  case  PLAINS   -> Color.YELLOW;
               
               }
            )
            ;
      
         setLayout(new GridBagLayout());
         Point location = chunk.location();
         JLabel label = new JLabel(location.x + "x" + location.y);
         add(label);
      }
   
      @Override
      public Dimension getPreferredSize() {
         return new Dimension(200, 200);
      }
   }

   // This basically acts a multi dimensional matrix of values, keyed by the
   // x/y coordinates.
   public interface Matrix<Type> {
      public Type get(int x, int y);
      public void put(Type value, int x, int y);
   }

   public class HashMapMatrix<Type> implements Matrix<Type> {
      private Map<Integer, Map<Integer, Type>> values = new HashMap<>(8);
   
      @Override
      public Type get(int x, int y) {
         Map<Integer, Type> rows = values.get(y);
         if (rows == null) {
            return null;
         }
         return rows.get(x);
      }
   
      @Override
      public void put(Type value, int x, int y) {
         Map<Integer, Type> rows = values.get(y);
         if (rows == null) {
            rows = new HashMap<Integer, Type>(8);
            values.put(y, rows);
         }
         rows.put(x, value);
      }
   }

   public enum Biome {
      FOREST, PLAINS
   }

   public interface Chunk {
      public Biome biome();
      public Point location();
   }

   public interface World {
      public Chunk getChunkAt(int x, int y);
   }

   public interface ChunkFactory {
      public Chunk createChunk(Point worldLocation);
   }

   public class DefaultChunkFactory implements ChunkFactory {
   
      private List<Biome> listOfBiomes = new ArrayList<>(Arrays.asList(Biome.values()));
   
      protected List<Biome> getListOfBiomes() {
         return listOfBiomes;
      }
   
      @Override
      public Chunk createChunk(Point worldLocation) {
         List<Biome> biomes = new ArrayList<>(getListOfBiomes());
         Collections.shuffle(biomes);
         Biome biome = biomes.get(0);
         return new DefaultChunk(biome, worldLocation);
      }
   }

   public record DefaultChunk(Biome biome, Point location) implements Chunk {
   
      public DefaultChunk {
         location = new Point(location);
      }
   
   }

   public class DefaultWorld implements World {
   
      private Matrix<Chunk> chunkMatrix = new HashMapMatrix<>();
      private ChunkFactory chunkFactory;
   
      public DefaultWorld(ChunkFactory chunkFactory) {
         this.chunkFactory = chunkFactory;
      }
   
      public ChunkFactory getChunkFactory() {
         return chunkFactory;
      }
   
      protected Matrix<Chunk> getChunks() {
         return chunkMatrix;
      }
   
      @Override
      public Chunk getChunkAt(int x, int y) {
         Chunk chunk = getChunks().get(x, y);
         if (chunk == null) {
            chunk = getChunkFactory().createChunk(new Point(x, y));
            getChunks().put(chunk, x, y);
         }
         return chunk;
      }
   }
}