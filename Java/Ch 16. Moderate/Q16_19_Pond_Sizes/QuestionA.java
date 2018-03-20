package Q16_19_Pond_Sizes;

import java.util.ArrayList;

/*
 * Pond sizes: you have an integer matrix representing a plot of land, where the value at that location represents the
 * height above see level. a value of zero indicates water. a pond is a region of water connected vertically,
 * horizontally, or diagonally. the size of the pond is the total number of connected water cells. write a method to
 * compute the sizes of all ponds in the matrix.
 *
 * example: input:
 * 0 2 1 0
 * 0 1 0 1
 * 1 1 0 1
 * 0 1 0 1
 * output: 2,4,1 (in any order)
 *
 * hint 673: if you were given the row and column of a water cell, how can you find all connected spaces?
 *
 * hint 686: try recursion to count the number of water cells
 *
 * hint 705: how can you make sure that you are not revisiting the same cells? think about how breadth-first search
 * or depth-first search on a graph works.
 *
 * hint 722: you should have an algorithm that's O(n*N) on an NxN matrix. if your algorithm isn't consider if you have
 * miscomputed the runtime or if your algorithm is suboptimal.
 */
public class QuestionA {

  public static ArrayList<Integer> computePondSizes(int[][] land) {
    ArrayList<Integer> pondSizes = new ArrayList<Integer>();
    for (int r = 0; r < land.length; r++) {
      for (int c = 0; c < land[r].length; c++) {
        if (land[r][c] == 0) {
          int size = computeSize(land, r, c);
          pondSizes.add(size);
        }
      }
    }
    return pondSizes;
  }

  public static int computeSize(int[][] land, int row, int col) {
    /* If out of bounds or already visited. */
    if (row < 0 || col < 0 || row >= land.length || col >= land[row].length || land[row][col] != 0) {
      return 0;
    }
    int size = 1;
    land[row][col] = -1;
    for (int dr = -1; dr <= 1; dr++) {
      for (int dc = -1; dc <= 1; dc++) {
        size += computeSize(land, row + dr, col + dc);
      }
    }
    return size;
  }

  public static void main(String[] args) {
    int[][] land = {{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}};
    ArrayList<Integer> sizes = computePondSizes(land);
    for (int sz : sizes) {
      System.out.println(sz);
    }
  }
}
