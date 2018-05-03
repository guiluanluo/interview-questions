package walmartlab;

import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/count-all-possible-paths-from-top-left-to-bottom-right/0
 *
 * The task is to count all the possible paths from top left to bottom right of a mXn matrix with the constraints that
 * from each cell you can either move only to right or down.
 */
public class CountPath {

  public static int dp(int r, int c) {
    int[][] matrix = new int[r][c];
    for (int i = 0; i < r; i++) {
      matrix[i][0] = 1;
    }

    for (int i = 0; i < c; i++) {
      matrix[0][i] = 1;
    }

    for (int i = 1; i < r; i++) {
      for (int j = 1; j < c; j++) {
        matrix[i][j] = (matrix[i - 1][j] + matrix[i][j - 1]) % ((int) Math.pow(10, 9) + 7);
      }
    }
    printMatrix(matrix);
    return matrix[r - 1][c - 1];
  }


  private static void printMatrix(int[][] matrix) {
    int r = matrix.length;
    int c = matrix[0].length;
    for (int i = 0; i < r; i++) {
      String temp = "";
      for (int j = 0; j < c; j++) {
        temp += (matrix[i][j] + " ");
      }
      System.out.println(temp);
    }
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
      System.out.println("dp:" + dp(row, column));

      count++;
    }
  }
}
