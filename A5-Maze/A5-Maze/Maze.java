import java.util.Scanner;

/**
 * Stores information about the layout of a maze.
 * 
 * @author Greta Wang
 * @version Spring 2026
 */
public class Maze implements DisplayableMaze {

  private MazeContents[][] mazeGrid;
  private int height;
  private int width;
  private MazeLocation start;
  private MazeLocation finish;

  /**
   * Constructor encodes data from the given file into a maze.
   * 
   * @param fname Name of file to read
   * @throws IllegalArgumentException If file is malformed
   */
  public Maze(String fname) {
    this.setDimensions(fname);
    Scanner file = SolveMaze.readMaze(fname);
    int i = 0; // row index i
    while (file.hasNextLine()) {
      String line = file.nextLine();
      for (int j = 0; j < line.length(); j++) { // column index j
        char token = line.charAt(j);
        if (token == 'S') { // start
          this.initStart(i, j);
        } else if (token == 'F') { // finish
          this.initFinish(i, j);
        } else {
          this.mazeGrid[i][j] = charToContents(token); // walls and open spaces
        }
      }
      i++;
    }
    file.close();
    if (this.start == null) {
      throw new IllegalArgumentException("Missing start point.");
    }
    if (this.finish == null) {
      throw new IllegalArgumentException("Missing end point.");
    }
  }

  /**
   * Sets maze dimensions to match the given file.
   * Creates empty 2D backing array.
   * 
   * @param fname Name of file to read
   * @throws IllegalArgumentException If file is empty or lines vary in width
   */
  private void setDimensions(String fname) {
    Scanner file = SolveMaze.readMaze(fname);
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
    file.close();
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Maze must have positive height and width");
    }
    this.height = height;
    this.width = width;
    this.mazeGrid = new MazeContents[height][width];
  }

  /**
   * Sets the given coordinates as the start point, or throws exception if start already exists.
   * 
   * @param i Row
   * @param j Column
   * @throws IllegalArgumentException If maze already has a start
   * @throws ArrayIndexOutOfBoundsException If coordinates are out of bounds
   */
  private void initStart(int i, int j) {
    if (this.start != null) {
      throw new IllegalArgumentException("Maze cannot have multiple start points.");
    }
    this.mazeGrid[i][j] = MazeContents.OPEN;
    this.start = new MazeLocation(i, j);
  }

  /**
   * Sets the given coordinates as the end point, or throws exception if end already exists.
   * 
   * @param i Row
   * @param j Column
   * @throws IllegalArgumentException If maze already has an end
   * @throws ArrayIndexOutOfBoundsException If coordinates are out of bounds
   */
  private void initFinish(int i, int j) {
    if (this.finish != null) {
      throw new IllegalArgumentException("Maze cannot have multiple end points.");
    }
    this.mazeGrid[i][j] = MazeContents.OPEN;
    this.finish = new MazeLocation(i, j);
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
   * Getter for height.
   * 
   * @return Height of the maze
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Getter for width.
   * 
   * @return Width of the maze
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the contents at a given row and column.
   * 
   * @param i Row
   * @param j Column
   * @return Contents at row i, column j
   * @throws ArrayIndexOutOfBoundsException If indices out of bounds
   */
  public MazeContents getContents(int i, int j) {
    return this.mazeGrid[i][j];
  }

  /**
   * Updates contents at a given MazeLocation.
   * 
   * @param location Location to update
   * @param contents New contents
   * @throws ArrayIndexOutOfBoundsException If location out of bounds
   */
  public void setContents(MazeLocation location, MazeContents contents) {
    this.mazeGrid[location.getRow()][location.getCol()] = contents;
  }

  /**
   * Returns whether the given coordinates are an explorable location.
   * 
   * @param i Row
   * @param j Column
   * @return True if row i, column j is an open path not yet explored
   */
  public Boolean isExplorable(int i, int j) {
    MazeContents value;
    try {
      value = this.getContents(i, j);
    } catch (ArrayIndexOutOfBoundsException e) {
      return false; // return false if out of bounds
    }
    return value.isExplorable;
  }

  /**
   * Getter for start.
   * 
   * @return Starting location
   */
  public MazeLocation getStart() {
    return this.start;
  }

  /**
   * Getter for finish.
   * 
   * @return Finish location
   */
  public MazeLocation getFinish() {
    return this.finish;
  }

    /** This DemoMaze method will allow you to generate a simple maze
     * to test your code on as you develop it. Ultimately, you need
     * to accept maze files as command line inputs or standard input.
     * You will need to implement the DisplayableMaze interface before you
     * can run the initDemoMaze method.
     * * @author Tianah Gooden
     * * @version October 17th 2023
     */
    public void initDemoMaze(){ //String fileName, 
        this.height = 10;
        this.width = 8;
        this.mazeGrid = new MazeContents[height][width];
        this.start = new MazeLocation(1,1);
        this.finish = new MazeLocation(8,6);

        this.mazeGrid[0][0] = MazeContents.WALL; this.mazeGrid[0][1] = MazeContents.WALL; this.mazeGrid[0][2] = MazeContents.WALL; this.mazeGrid[0][3] = MazeContents.WALL; this.mazeGrid[0][4] = MazeContents.WALL; this.mazeGrid[0][5] = MazeContents.WALL; this.mazeGrid[0][6] = MazeContents.WALL; this.mazeGrid[0][7] = MazeContents.WALL;
        this.mazeGrid[1][0] = MazeContents.WALL; this.mazeGrid[1][1] = MazeContents.OPEN; this.mazeGrid[1][2] = MazeContents.OPEN; this.mazeGrid[1][3] = MazeContents.OPEN; this.mazeGrid[1][4] = MazeContents.OPEN; this.mazeGrid[1][5] = MazeContents.OPEN; this.mazeGrid[1][6] = MazeContents.WALL; this.mazeGrid[1][7] = MazeContents.WALL;
        this.mazeGrid[2][0] = MazeContents.WALL; this.mazeGrid[2][1] = MazeContents.WALL; this.mazeGrid[2][2] = MazeContents.OPEN; this.mazeGrid[2][3] = MazeContents.WALL; this.mazeGrid[2][4] = MazeContents.WALL; this.mazeGrid[2][5] = MazeContents.OPEN; this.mazeGrid[2][6] = MazeContents.WALL; this.mazeGrid[2][7] = MazeContents.WALL;
        this.mazeGrid[3][0] = MazeContents.WALL; this.mazeGrid[3][1] = MazeContents.OPEN; this.mazeGrid[3][2] = MazeContents.WALL; this.mazeGrid[3][3] = MazeContents.OPEN; this.mazeGrid[3][4] = MazeContents.OPEN; this.mazeGrid[3][5] = MazeContents.OPEN; this.mazeGrid[3][6] = MazeContents.WALL; this.mazeGrid[3][7] = MazeContents.WALL;
        this.mazeGrid[4][0] = MazeContents.WALL; this.mazeGrid[4][1] = MazeContents.OPEN; this.mazeGrid[4][2] = MazeContents.OPEN; this.mazeGrid[4][3] = MazeContents.OPEN; this.mazeGrid[4][4] = MazeContents.WALL; this.mazeGrid[4][5] = MazeContents.WALL; this.mazeGrid[4][6] = MazeContents.OPEN; this.mazeGrid[4][7] = MazeContents.WALL;
        this.mazeGrid[5][0] = MazeContents.WALL; this.mazeGrid[5][1] = MazeContents.OPEN; this.mazeGrid[5][2] = MazeContents.WALL; this.mazeGrid[5][3] = MazeContents.OPEN; this.mazeGrid[5][4] = MazeContents.OPEN; this.mazeGrid[5][5] = MazeContents.WALL; this.mazeGrid[5][6] = MazeContents.WALL; this.mazeGrid[5][7] = MazeContents.WALL;
        this.mazeGrid[6][0] = MazeContents.WALL; this.mazeGrid[6][1] = MazeContents.OPEN; this.mazeGrid[6][2] = MazeContents.WALL; this.mazeGrid[6][3] = MazeContents.WALL; this.mazeGrid[6][4] = MazeContents.OPEN; this.mazeGrid[6][5] = MazeContents.OPEN; this.mazeGrid[6][6] = MazeContents.OPEN; this.mazeGrid[6][7] = MazeContents.WALL;
        this.mazeGrid[7][0] = MazeContents.WALL; this.mazeGrid[7][1] = MazeContents.OPEN; this.mazeGrid[7][2] = MazeContents.WALL; this.mazeGrid[7][3] = MazeContents.OPEN; this.mazeGrid[7][4] = MazeContents.OPEN; this.mazeGrid[7][5] = MazeContents.WALL; this.mazeGrid[7][6] = MazeContents.OPEN; this.mazeGrid[7][7] = MazeContents.WALL;
        this.mazeGrid[8][0] = MazeContents.WALL; this.mazeGrid[8][1] = MazeContents.OPEN; this.mazeGrid[8][2] = MazeContents.OPEN; this.mazeGrid[8][3] = MazeContents.WALL; this.mazeGrid[8][4] = MazeContents.OPEN; this.mazeGrid[8][5] = MazeContents.WALL; this.mazeGrid[8][6] = MazeContents.OPEN; this.mazeGrid[8][7] = MazeContents.WALL;
        this.mazeGrid[9][0] = MazeContents.WALL; this.mazeGrid[9][1] = MazeContents.WALL; this.mazeGrid[9][2] = MazeContents.WALL; this.mazeGrid[9][3] = MazeContents.WALL; this.mazeGrid[9][4] = MazeContents.WALL; this.mazeGrid[9][5] = MazeContents.WALL; this.mazeGrid[9][6] = MazeContents.WALL; this.mazeGrid[9][7] = MazeContents.WALL;
  }
}
