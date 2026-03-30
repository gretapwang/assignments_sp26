import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Subclass of BinaryTree representing a set of yes/no questions leading to answers.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class DecisionTree extends BinaryTree<String> {

    /**
     * Constructor for a leaf.
     * 
     * @param data Data to store in leaf
     */
    public DecisionTree(String data) {
        super(data);
    }

    /**
     * Constructor for a branch.
     * 
     * @param data Data to store in root
     * @param left Left child
     * @param right Right child
     */
    public DecisionTree(String data, DecisionTree left, DecisionTree right) {
        super(data, left, right);
    }

    /**
     * Accessor for left child.
     * 
     * @return Left child
     */
    public DecisionTree getLeft() {
        return (DecisionTree) super.getLeft();
    }

    /**
     * Accessor for right child.
     * 
     * @return Right child
     */
    public DecisionTree getRight() {
        return (DecisionTree) super.getRight();
    }

    /**
     * Returns the child corresponding to a yes/no choice represented by the given boolean.
     * 
     * @param isYes True to answer yes, false for no
     * @return Left child if yes, right child if no
     */
    public DecisionTree makeChoice(boolean isYes) {
        if (isYes) {
            return this.getLeft();
        } else {
            return this.getRight();
        }
    }

    /**
     * Returns the child corresponding to a yes/no choice represented by the given char.
     * 
     * @param choice Char 'Y' to answer yes, 'N' for no
     * @return Left child if yes, right child if no
     * @throws IllegalArgumentException If char is invalid
     */
    public DecisionTree makeChoice(char choice) {
        if (choice != 'Y' && choice != 'N') {
            throw new IllegalArgumentException("Invalid character.");
        }
        return makeChoice(choice == 'Y');
    }

    /**
     * Manipulator for left child.
     * 
     * @param left Node to set as left
     * @throws UnsupportedOperationException If left is not a DecisionTree
     */
    public void setLeft(BinaryTree<String> left) {
        if (left instanceof DecisionTree) {
            super.setLeft(left);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Manipulator for right child.
     * 
     * @param right Node to set as right
     * @throws UnsupportedOperationException If right is not a DecisionTree
     */
    public void setRight(BinaryTree<String> right) {
        if (right instanceof DecisionTree) {
            super.setRight(right);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Sets children as leaves storing the given data.
     * 
     * @param lData Data for left child
     * @param rData Data for right child
     */
    public void setLeafChildren(String lData, String rData) {
        this.setLeft(new DecisionTree(lData));
        this.setRight(new DecisionTree(rData));
    }

    /**
     * Takes in a String specifying a sequence of yes/no choices and returns the resulting node.
     * 
     * @param path String of 'Y'/'N' representing choices
     * @return Node reached by path
     * @throws NullPointerException If path is not traceable on the tree
     * @throws IllegalArgumentException If path contains invalid characters
     */
    public DecisionTree followPath(String path) {
        if (path.isEmpty()) { // base case
            return this;
        } 
        return this.makeChoice(path.charAt(0)).followPath(path.substring(1));
    }

    /**
     * Writes the tree's contents to the given file name in breadth-first order.
     * 
     * @param fileName Name of file to write to
     */
    public void writeToFile(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            ArrayDeque<DecisionTree> nodes = new ArrayDeque<DecisionTree>();
            nodes.add(this);
            ArrayDeque<String> paths = new ArrayDeque<String>();
            paths.add("");
            while (!nodes.isEmpty()) {
                out.println(nextFileLine(nodes, paths));
            }
            out.close();
        } catch (Exception e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }
    }

    /**
     * Given a queue of tree nodes and a queue of paths, advances both queues one step in the 
     * breadth-first traversal algorithm. 
     * Returns the data corresponding to that step, formatted to write to a file.
     * 
     * @param nodes Queue of DecisionTree nodes
     * @param paths Queue of Strings representing tree paths
     * @return Next line of data to write to file
     * @throws NoSuchElementException If either queue is empty
     */
    private static String nextFileLine(ArrayDeque<DecisionTree> nodes, ArrayDeque<String> paths) {
        DecisionTree node = nodes.remove();
        String path = paths.remove();
        if (node.isBranch()) {
            nodes.add(node.getLeft());
            nodes.add(node.getRight());
            paths.add(path + "Y");
            paths.add(path + "N");
        }
        return path + " " + node.getData();
    }

    /**
     * Sets the tree's contents according to data from the given file name.
     * 
     * @param fileName Name of file to read from
     */
    public void populateFromFile(String fileName) {
        Scanner file;
        try {
            file = new Scanner(new File(fileName));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                int spaceIndex = line.indexOf(' ');
                this.setLeafOnPath(line.substring(0, spaceIndex), line.substring(spaceIndex + 1));
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);  
        } catch (Exception e) {
            System.err.println("Malformed file content.");
            System.exit(-1); 
        }
    }

    /**
     * Sets the destination of the given path to be a leaf storing the given data.
     * 
     * @param path Path String describing node to set
     * @param data Data for new node
     * @throws NullPointerException If parent of the desired node does not exist
     * @throws IllegalArgumentException If path contains invalid characters
     */
    private void setLeafOnPath(String path, String data) {
        if (path.isEmpty()) {
            this.setData(data);
        } else {
            DecisionTree parent = this.followPath(path.substring(0, path.length() - 1));
            char lastChar = path.charAt(path.length() - 1);
            if (lastChar == 'Y') {
                parent.setLeft(new DecisionTree(data));
            } else if (lastChar == 'N') {
                parent.setRight(new DecisionTree(data));
            } else {
                throw new IllegalArgumentException("Path contains invalid characters.");
            }
        }
    }

    /**
     * Tests DecisionTree behavior with an animal guessing tree.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        DecisionTree crocodile = new DecisionTree("Crocodile");
        DecisionTree mosquito = new DecisionTree("Mosquito");
        DecisionTree reptileQuestion = new DecisionTree("Is it a reptile?", crocodile, mosquito);
        DecisionTree horse = new DecisionTree("Horse");
        DecisionTree dog = new DecisionTree("Dog");
        DecisionTree hoovesQuestion = new DecisionTree("Does it have hooves?", horse, dog);
        DecisionTree tree = new DecisionTree("Is it a mammal?", hoovesQuestion, reptileQuestion);

        System.out.println("Yes mammal, no hooves: " + tree.getLeft().getRight().getData());
        System.out.println("No mammal, no reptile: " + tree.followPath("NN").getData());

        tree.writeToFile("AnimalTree.txt");
    }
}