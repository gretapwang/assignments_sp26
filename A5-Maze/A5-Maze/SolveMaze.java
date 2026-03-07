import java.io.*;
import java.util.Scanner;

/**
 * Solves mazes recursively with visual display.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
class SolveMaze {

  /**
   * Returns a Scanner that reads from the specified file.
   * 
   * @param fname Name of file to read from
   * @return Scanner
   */
  public static Scanner readMaze(String fname){
    Scanner file = null;
    try {
      file = new Scanner(new File(fname));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);  
    }
    return file;
  }

  /**
   * Returns whether the given maze can be solved from the given location.
   * Computes solutions recursively.
   * 
   * @param maze Maze to solve
   * @param current Starting location
   * @return True if solvable, false otherwise
   */
  public static boolean solve(Maze maze, MazeLocation current) {
    try { Thread.sleep(30); } catch (InterruptedException e) {} // REMOVE!!!!
    if (current.equals(maze.getFinish())) { // success case
      maze.setContents(current, MazeContents.PATH);
      return true;
    }
    if (!maze.isExplorable(current.getRow(), current.getCol())) { // failure case
      return false;
    }
    // recursive step:
    maze.setContents(current, MazeContents.VISITED);
    if (solve(maze, current.neighbor(MazeDirection.NORTH)) || solve(maze, current.neighbor(MazeDirection.SOUTH))
        || solve(maze, current.neighbor(MazeDirection.EAST)) || solve(maze, current.neighbor(MazeDirection.WEST))) {
      maze.setContents(current, MazeContents.PATH);
      return true;
    }
    maze.setContents(current, MazeContents.DEAD_END);
    return false;
  }

  /**
   * Returns a Maze with the layout specified in the given file.
   * 
   * @param fname Name of file to encode into maze
   * @return Maze object
   * @throws IllegalArgumentException If maze file is malformed
   */
  public static Maze fileToMaze(String fname) {
    Maze maze = makeEmptyMaze(fname);
    Scanner file = readMaze(fname);
    int sCount = 0; // count occurrences of 'S'
    int fCount = 0; // count occurrences of 'F'
    int i = 0; // row index i
    while (file.hasNextLine()) {
      String line = file.nextLine();
      for (int j = 0; j < line.length(); j++) { // column index j
        char token = line.charAt(j);
        MazeLocation location = new MazeLocation(i, j);
        if (token == 'S') { // set start
          maze.setStart(location);
          sCount++;
        } else if (token == 'F') { // set finish
          maze.setFinish(location);
          fCount++;
        } else {
          maze.setContents(location, charToContents(token)); // walls and open spaces
        }
      }
      i++;
    }
    if (sCount != 1) {
      throw new IllegalArgumentException("Maze should have exactly one start point.");
    }
    if (fCount != 1) {
      throw new IllegalArgumentException("Maze should have exactly one end point.");
    }
    return maze;
  }

  /**
   * Returns a Maze with appropriate dimensions to encode the given file, but no contents set.
   * 
   * @param fname Name of file to encode into maze
   * @return Empty maze of correct dimensions
   * @throws IllegalArgumentException If file is empty, or rows have varying width
   */
  private static Maze makeEmptyMaze(String fname) {
    Scanner file = readMaze(fname);
    int height = 0;
    int width = -1;
    while (file.hasNextLine()) {
      String line = file.nextLine();
      height++;
      if (width == -1) {
        width = line.length();
      } else if (width != line.length()) {
        throw new IllegalArgumentException("All rows in maze must have equal width.");
      }
    }
    return new Maze(height, width);
  }

  /**
   * Returns the MazeContents associated with the given character ('#', '.', or ' ').
   * 
   * @param character Character from file
   * @return Contents to encode in maze
   * @throws IllegalArgumentException If given an unsupported character
   */
  private static MazeContents charToContents(char character) {
    if (character == '#') {
      return MazeContents.WALL;
    } else if (character == '.' || character == ' ') {
      return MazeContents.OPEN;
    } else {
      throw new IllegalArgumentException("File contains invalid characters.");
    }
  }
  
  /**
   * Displays and solves a maze from a file specified in the command line.
   * Defaults to maze1 if no file name provided.
   * 
   * @param args Command line arguments specifying file name
   */
  public static void main(String[] args) {
    Maze maze = null;
    try {
      if(args.length <= 0) {
        maze = fileToMaze("maze1");
      }
      else {
        maze = fileToMaze(args[0]);
      }
    } catch (Exception e) { // print error message if file malformed
      System.err.println(e.getLocalizedMessage());
      System.exit(-1);
    }
    MazeViewer viewer = new MazeViewer(maze); // display maze
    if (solve(maze, maze.getStart())) { // solve and print result
      System.out.println("Maze solved!");
    } else {
      System.out.println("No solution found.");
    }
  }
}
