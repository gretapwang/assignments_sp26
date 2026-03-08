import java.io.*;
import java.util.Scanner;

/**
 * Solves mazes recursively with graphic display.
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
    if (current.equals(maze.getFinish())) { // success case
      maze.setContents(current, MazeContents.PATH);
      return true;
    }
    if (!maze.isExplorable(current.getRow(), current.getCol())) { // failure case
      return false;
    }
    // recursive step:
    maze.setContents(current, MazeContents.VISITED);
    if ((solve(maze, current.neighbor(MazeDirection.NORTH)) || solve(maze, current.neighbor(MazeDirection.SOUTH)))
        || (solve(maze, current.neighbor(MazeDirection.EAST)) || solve(maze, current.neighbor(MazeDirection.WEST)))) {
      maze.setContents(current, MazeContents.PATH);
      return true;
    }
    maze.setContents(current, MazeContents.DEAD_END);
    return false;
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
      if (args.length <= 0) {
        maze = new Maze("maze1");
      }
      else {
        maze = new Maze(args[0]);
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
