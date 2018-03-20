package Q10_01_Sorted_Merge;

import CtCILibrary.AssortedMethods;

/**
 * Sorted merge: you are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. write
 * a method to merge B into A in sorted order.
 *
 * hint 332: try moving from the end of the array to the beginning
 *
 * Solution: since we already know that A has enough buffer at the end, we won't need to allocate additional space. our
 * logic should involves simply comparing elements of A and B and inserting them in order, until we have exhuasted all
 * elements in A and B. we move largest elements to the back
 */

public class Question {

  public static void merge(int[] a, int[] b, int lastA, int lastB) {

    int indexMerged = lastB + lastA - 1; /* Index of last location of merged array */
    int indexA = lastA - 1; /* Index of last element in array b */
    int indexB = lastB - 1; /* Index of last element in array a */

		/* Merge a and b, starting from the last element in each */
    while (indexB >= 0) {
      if (indexA >= 0 && a[indexA] > b[indexB]) { /* end of a is bigger than end of b */
        a[indexMerged] = a[indexA]; // copy element
        indexA--;
      } else {
        a[indexMerged] = b[indexB]; // copy element
        indexB--;
      }
      indexMerged--; // move indices
    }
  }

  public static void main(String[] args) {
    int[] a = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
    int[] b = {1, 4, 7, 6, 7, 7};
    merge(a, b, 8, 6);
    System.out.println(AssortedMethods.arrayToString(a));
  }

}
