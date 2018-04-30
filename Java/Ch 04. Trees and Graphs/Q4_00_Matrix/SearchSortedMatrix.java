package Q4_00_Matrix;

/**
 * Thttps://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix
 *
 * Given an n x n matrix and a number x, find position of x in the matrix if it is present in it. Else print “Not
 * Found”. In the given matrix, every row and column is sorted in increasing order. The designed algorithm should have
 * linear time complexity.
 */
public class SearchSortedMatrix {

  /**
   * Searches the element x in mat[][]. If the element is found, then prints its position
   * and returns true, otherwise prints "not found" and returns false
   */
  private static void search(int[][] mat, int x) {
    int rowSize = mat.length;

    int leftIdx = 0;
    int rightIdx = rowSize - 1;  //set indexes for top right element

    while (leftIdx < rowSize && rightIdx >= 0) {
      if (mat[leftIdx][rightIdx] == x) {
        System.out.print(x + " Found at " + leftIdx + " " + rightIdx);
        return;
      }

      if (mat[leftIdx][rightIdx] > x) {
        rightIdx--;
      } else {
        leftIdx++;
      }
    }

    System.out.print("n Element not found");
    return;  // if ( leftIdx==n || rightIdx== -1 )
  }

  public static void main(String[] args) {
    int mat[][] = {
        {10, 20, 30, 40},
        {15, 25, 35, 45},
        {27, 29, 37, 48},
        {32, 33, 39, 50}};

    search(mat, 29);
  }

}
