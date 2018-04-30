package Q4_00_Matrix;

import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/count-all-possible-paths-from-top-left-to-bottom-right/0
 *
 * The task is to count all the possible paths from top left to bottom right of a mXn matrix with the constraints that
 * from each cell you can either move only to right or down.
 */
public class CountPath {

  public static int findPossiblePath(int[][] matrix) {
    int row = matrix.length;
    int column = matrix[0].length;
    return findPath(matrix, row, column);
  }

  public static int findPath(int[][] matrix, int row, int column) {
    if (row < 0 || column < 0) {
      return 0;
    }

    if (row == 0 && column == 0) {
      return 1;
    }

    return findPath(matrix, row, column - 1) + findPath(matrix, row - 1, column);
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      String[] arrItems = scanner.nextLine().split(" ");
      int row = Integer.parseInt(arrItems[0].trim());
      int column = Integer.parseInt(arrItems[1].trim());

      int[][] matrix = new int[row][column];
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < column; j++) {
          matrix[i][j] = 0;
        }
      }
      int result = findPossiblePath(matrix);
      System.out.println(result);

      count++;
    }
  }
}
