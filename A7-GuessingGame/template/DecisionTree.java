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
     * Returns the child specified by the given boolean.
     * 
     * @param isLeft True to get left child, false to get right
     * @return Left or right child
     */
    public DecisionTree getChild(boolean isLeft) {
        if (isLeft) {
            return this.getLeft();
        }
        return this.getRight();
    }

    /**
     * Returns the child corresponding to a yes/no choice represented by the given char.
     * 
     * @param choice 'Y' for yes, 'N' for no
     * @return Left child if yes, right child if no
     * @throws IllegalArgumentException If choice is an invalid character
     */
    public DecisionTree getChild(char choice) {
        return getChild(charToBool(choice));
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
     * Sets the child specified by the boolean to be a leaf storing the given data.
     * 
     * @param data Data for new child
     * @param isLeft True to set left child, false to set right
     */
    public void setLeafChild(String data, boolean isLeft) {
        if (isLeft) {
            this.setLeft(new DecisionTree(data));
        } else {
            this.setRight(new DecisionTree(data));
        }
    }

    /**
     * Sets the child corresponding to the given char to be a leaf storing the given data.
     * 
     * @param data Data for new child
     * @param choice 'Y' to set left child, 'N' to set right
     * @throws IllegalArgumentException If choice is an invalid character
     */
    public void setLeafChild(String data, char choice) {
        this.setLeafChild(data, charToBool(choice));
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
        // recursive step: move to the correct child, then call recursively to solve the rest
        return this.getChild(path.charAt(0)).followPath(path.substring(1));
    }

    /**
     * Returns the boolean value associated with a given character 'Y' or 'N'.
     * 
     * @param choice 'Y' or 'N'
     * @return True for 'Y', false for 'N'
     * @throws IllegalArgumentException If character is invalid
     */
    private static boolean charToBool(char choice) {
        if (choice == 'Y') {
            return true;
        } else if (choice == 'N') {
            return false;
        } else {
            throw new IllegalArgumentException("Path contains invalid characters.");
        }
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
            nodes.add(this); // initial node queue
            ArrayDeque<String> paths = new ArrayDeque<String>();
            paths.add(""); // initial path queue
            while (!nodes.isEmpty()) { // breadth-first algorithm
                DecisionTree node = nodes.remove();
                String path = paths.remove();
                if (node.isBranch()) { // add children and their paths, if they exist
                    nodes.add(node.getLeft());
                    nodes.add(node.getRight());
                    paths.add(path + "Y");
                    paths.add(path + "N");
                }
                out.println(path + " " + node.getData());
            }
            out.close();
        } catch (Exception e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }
    }

    /**
     * Sets the tree's contents according to data from the given file name.
     * 
     * @param fileName Name of file to read from
     */
    public void populateFromFile(String fileName) {
        try {
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                int spaceIndex = line.indexOf(' ');
                this.setPathEndPt(line.substring(0, spaceIndex), line.substring(spaceIndex + 1));
            }
            file.close();
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.err.println("Cannot locate file.");
            } else {
                System.err.println("Malformed file content.");
            }
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
    private void setPathEndPt(String path, String data) {
        if (path.isEmpty()) {
            this.setData(data);
        } else {
            DecisionTree parent = this.followPath(path.substring(0, path.length() - 1));
            parent.setLeafChild(data, path.charAt(path.length() - 1));
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
