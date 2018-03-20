package Q10_09_Sorted_Matrix_Search;

import CtCILibrary.AssortedMethods;

/**
 * Sort matrix search: given an MxN matrix in which each row and each column is sorted in ascending order, write a
 * method to find an element
 *
 * hint 193: start with a naive solution(but hopefully not too naive, you should be able to use the fact that the matrix
 * is sorted)
 *
 * hint 211: we can do a binary search in each row. how long will this take? how can we do better?
 *
 * hint 229: if your are considering a particular column, is there a way to wuickly eliminate it(int some cases at
 * lease)?
 *
 * hint 251: since each column is sorted, you know that the value can't be in the column if it's smaller than the min
 * value in this column. what else does this tell you?
 *
 * hint 266: if the value x is smaller than the start of the column, then it also can't be in any columns to the right.
 *
 * hint 279: think about the previous hint in the context of rows
 *
 * hint 288: what would happen if we tried to keep track of this using an array? what are the pros and cons of this?
 *
 * hint 291: can we use the previous hints to move up, down, left, and right around the rows and columns?
 *
 * hint 303: another way to think about this is that if you drew a rectangle around a cell extending to the bottom,
 * right coordinate of the matrix, the cell would be gibber than all the items in this square.
 *
 * hint 317: a cell will be larger than all the items below it and to the right. it will be smaller than all cells above
 * it and to the left. if we wanted to eliminate the most elements first, which element should we compare the value x
 * to?
 *
 * hint 330: if we compare x to the center element in the matrix, we can eliminate roughly one quarter of the elements
 * in the matrix
 *
 * Solution A: native solution - we can do binary search on EVERY ROW to find the element. this algorithm will be O(M
 * log(N)) since there are M rows and it takes O(log(N)) time to search each one. this is a good approach to mention to
 * your interviewer before you proceed with generating a getter algorithm
 */
public class QuestionA {

  public static boolean findElement(int[][] matrix, int elem) {
    int row = 0;
    int col = matrix[0].length - 1;
    while (row < matrix.length && col >= 0) {
      if (matrix[row][col] == elem) {
        return true;
      } else if (matrix[row][col] > elem) {
        col--;
      } else {
        row++;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int M = 10;
    int N = 5;
    int[][] matrix = new int[M][N];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        matrix[i][j] = 10 * i + j;
      }
    }

    AssortedMethods.printMatrix(matrix);

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < M; j++) {
        int v = 10 * i + j;
        System.out.println(v + ": " + findElement(matrix, v));
      }
    }

  }

}
