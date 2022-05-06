import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public interface SOQ_20220505_1
{

   public final class FlatLeaf extends Leaf {
      public FlatLeaf(int length) {
         super(length);
      }
   }
   
   public final class NeedleLeaf extends Leaf {
      public NeedleLeaf(int length) {
         super(length);
      }
   }

   public sealed class Leaf{
      private int length;
   
      public Leaf(int length) {
         this.length = length;
      }
      
      public void grow() {
         this.length++;
      }
   }
   
   public class Branch<L extends Leaf> {
      private int length;
      private List<Branch<L>> subBranches;
      private List<L> leaves;
      private Supplier<L> leafGenerator;
   
      public Branch(int length, List<Branch<L>> subBranches, List<L> leaves, Supplier<L> leafGenerator) {
         this.length = length;
         this.subBranches = subBranches;
         this.leaves = leaves;
         this.leafGenerator = leafGenerator;
      }
   
      public void grow() {
         this.length++;
         this.subBranches.forEach(Branch::grow);
         this.subBranches.add(new Branch<>(1, new ArrayList<>(), new ArrayList<>(), this.leafGenerator));
         this.leaves.add(leafGenerator.get());
         this.leaves.forEach(Leaf::grow);
      }
   }
   
   public class Trunk<L extends Leaf> {
      private int height;
      private int width;
      private Set<Branch<L>> branches;
      private Supplier<L> leafGenerator;
   
      public Trunk(int height, int width, Set<Branch<L>> branches, Supplier<L> leafGenerator) {
         this.height = height;
         this.width = width;
         this.branches = branches;
         this.leafGenerator = leafGenerator;
      }
      
      public Trunk<L> with(Supplier<L> leafGenerator) {
         return new Trunk<>(this.height, this.width, this.branches, leafGenerator);
      }
   
      public void grow() {
         this.width++;
         this.height += 15;
         this.branches.forEach(Branch::grow);
         this.branches.add(
            new Branch<L>(
               1, 
               new ArrayList<>(), 
               new ArrayList<>(),
               this.leafGenerator));
      }
   }

   public sealed class Tree<L extends Leaf> permits DeciduousTree, ConiferTree
   {
      private int id;
      private String name;
      private int age;
      private Trunk<L> trunk;
      
      public Tree(int id, String name, int age, Trunk<L> trunk, Supplier<L> leafGenerator) {
         this.id = id;
         this.name = name;
         this.age = age;
         this.trunk = trunk.with(leafGenerator);
      }
      
      public static <L extends Leaf> Tree<L> generate(int id, String name, Supplier<L> leafGenerator) {
         return new Tree<L>(id, name, 0, new Trunk<L>(3, 1, new HashSet<>(), leafGenerator), leafGenerator);
      }
      
      public void grow() {
         this.age++;
         this.trunk.grow();
      }
      
   }
   
   public final class DeciduousTree<L extends FlatLeaf> extends Tree<L> {
      public DeciduousTree(int id, String name, int age, Trunk<L> trunk, Supplier<L> leafGenerator) {
         super(id, name, age, trunk, leafGenerator);
      }
   }

   public final class ConiferTree<L extends NeedleLeaf> extends Tree<L> {
      public ConiferTree(int id, String name, int age, Trunk<L> trunk, Supplier<L> leafGenerator) {
         super(id, name, age, trunk, leafGenerator);
      }
   }

   public static void main(String[] args)
   {
   
      Tree<FlatLeaf> tree = Tree.generate(1, "Deciduous", (() -> new FlatLeaf(1)));
   
   }

}
