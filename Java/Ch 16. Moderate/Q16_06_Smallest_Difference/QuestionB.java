package Q16_06_Smallest_Difference;

import java.util.Arrays;

/**
 * we sort two arrays first, then find the minimum difference by iterating through the array.
 */
public class QuestionB {

  public static int findSmallestDifference(int[] array1, int[] array2) {
    Arrays.sort(array1);
    Arrays.sort(array2);

    int index1 = 0;
    int index2 = 0;
    int difference = Integer.MAX_VALUE;

    while (index1 < array1.length && index2 < array2.length) {
      if (Math.abs(array1[index1] - array2[index2]) < difference) {
        difference = Math.abs(array1[index1] - array2[index2]);
        if (difference == 0) {
          return difference;
        }
      }

      if (array1[index1] < array2[index2]) {
        index1++;
      } else {
        index2++;
      }
    }
    return difference;
  }

  public static void main(String[] args) {
    int[] array1 = {1, 3, 15, 11, 2};
    int[] array2 = {23, 127, 234, 19, 8};
    int difference = findSmallestDifference(array1, array2);
    System.out.println(difference);
  }

}
