package Q8_02_Robot_in_a_Grid;

import java.util.ArrayList;

import CtCILibrary.AssortedMethods;


/**
 * Robot in a gird: image a robot sitting on the upper left corner of grid with r rows and c columns. the robot can only
 * move in two direction s, right and down, but certain cells are "off limit" such that the robot cannot step on them.
 * design an algorithm to find a path for the robot from the top left to the bottom right
 *
 * hint 331: for the robot to reach the last cell, it must find a path to the second-to-last cell. for it to find a path
 * to the second-to-last cells, it must find a path to the third-to-last cells.
 *
 * hint 388: think again about the efficiency of your algorithm. can you optimize it?
 *
 * Solution: if we picture this grid, the only way to move to spot (r,c) is by moving to one of the adjacent spots:
 * (r-1, c) or (r, c-1). so , we need to find a path to either (r-1, c) or (r, c-1).
 *
 * how do we find a path to those spots?to find a path to (r-1, c) or (r, c-1), we need to move to one of its adjacent
 * cells. so, we need to find a path to a spot adjacent to (r-1, c), which are coordinates (r-2, c) and (r-1, c-1), or a
 * spot adjacent to (r, c-1), which are spots (r-1, c-1) and (f, c-2). observe that we list the point(r-1, c-1) twice:
 * we'll discuss that issue later. so then, to find a path from the origin, we just work backwards like this: start from
 * the last cell, we try to find a path to each of its adjacent cells.
 */
public class QuestionA {

  public static ArrayList<Point> getPath(boolean[][] maze) {
    if (maze == null || maze.length == 0) {
      return null;
    }
    ArrayList<Point> path = new ArrayList<Point>();
    if (getPath(maze, maze.length - 1, maze[0].length - 1, path)) {
      return path;
    }
    return null;
  }

  public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {

    // If out of bounds or not available, return.
    if (col < 0 || row < 0 || !maze[row][col]) {
      return false;
    }

    boolean isAtOrigin = (row == 0) && (col == 0);

    //If there 's a path from the start to my current location, add my location.
    if (isAtOrigin || getPath(maze, row, col - 1, path) || getPath(maze, row - 1, col, path)) {
      Point p = new Point(row, col);
      path.add(p);
      return true;
    }

    return false;
  }

  public static void main(String[] args) {
    int size = 5;
    boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 70);

    AssortedMethods.printMatrix(maze);

    ArrayList<Point> path = getPath(maze);
    if (path != null) {
      System.out.println(path.toString());
    } else {
      System.out.println("No path found.");
    }
  }

}
