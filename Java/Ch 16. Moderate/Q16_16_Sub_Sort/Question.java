package Q16_16_Sub_Sort;

/**
 * Sub sort: given an array of integers, write a method to find indices m and n such that if you sorted elements m
 * through n, the entire array would be sorted. minimize n-m (that is, find the smallest such sequence).
 *
 * example: input: 1,2,4,7,10,11,7,12,6,7,16,18,19    output: (3,9)
 *
 * hint 481: would it help to know the longest sorted sequences at the beginning and end?
 *
 * hint 552: we can think about the array as divided into three subarrays: LEFT, MIDDLE, RIGHT, LEFT and RIGHT are both
 * sorted. the MIDDEL elements are in an arbitrary order. we need to expand MIDDLE until we could sort those elements
 * and then have the entire array sorted.
 *
 * hint 666: consider the three subarrays: LEFT, MIDDLE, RIGHT,. focus on just this question: can you sort middle such
 * that the entire array becomes sorted? how would you check this?
 *
 * hint 707: in order to able to sort MIDDLE and have the whole array become sorted, you need MAX(LEFT) <=MIN(MIDDLE and
 * RIGHT) and MAX(LEFT and MIDDLE)<= MIN(RIGHT).
 *
 * hint 734: can you expand the middle until the earlier condition is met?
 *
 * hint 745: you should be able to solve this in O(N) time.
 *
 * Solution: before we begin, let's make sure we understand what our answer will look like. if we just looking for just
 * two indices, this indicates that some middle section of the array will be sorted, with the start and end of the array
 * already being in order.
 */
public class Question {

  public static int findEndOfLeftSubsequence(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i] < array[i - 1]) {
        return i - 1;
      }
    }
    return array.length - 1;
  }

  public static int findStartOfRightSubsequence(int[] array) {
    for (int i = array.length - 2; i >= 0; i--) {
      if (array[i] > array[i + 1]) {
        return i + 1;
      }
    }
    return 0;
  }

  public static int shrinkLeft(int[] array, int min_index, int start) {
    int comp = array[min_index];
    for (int i = start - 1; i >= 0; i--) {
      if (array[i] <= comp) {
        return i + 1;
      }
    }
    return 0;
  }

  public static int shrinkRight(int[] array, int max_index, int start) {
    int comp = array[max_index];
    for (int i = start; i < array.length; i++) {
      if (array[i] >= comp) {
        return i - 1;
      }
    }
    return array.length - 1;
  }

  public static void findUnsortedSequence(int[] array) {
    // find left sub-sequence
    int end_left = findEndOfLeftSubsequence(array);
    if (end_left >= array.length - 1) {
      System.out.println("The array is already sorted.");
      return;
    }

    // find right sub-sequence
    int start_right = findStartOfRightSubsequence(array);

    //find the max & min indices of middle unsorted sub-sequence
    int max_index = end_left; // max of left side
    int min_index = start_right; // min of right side

    for (int i = end_left + 1; i < start_right; i++) {
      if (array[i] < array[min_index]) {
        min_index = i;
      }
      if (array[i] > array[max_index]) {
        max_index = i;
      }
    }

    // slide left until less than array[min_index]
    int left_index = shrinkLeft(array, min_index, end_left);

    // slide right until greater than array[max_index]
    int right_index = shrinkRight(array, max_index, start_right);

    if (validate(array, left_index, right_index)) {
      System.out.println("TRUE: " + left_index + " " + right_index);
    } else {
      System.out.println("FALSE: " + left_index + " " + right_index);
    }
  }

  /* Validate that sorting between these indices will sort the array. Note that this is not a complete
   * validation, as it does not check if these are the best possible indices.
   */
  public static boolean validate(int[] array, int left_index, int right_index) {
    int[] middle = new int[right_index - left_index + 1];
    for (int i = left_index; i <= right_index; i++) {
      middle[i - left_index] = array[i];
    }

    java.util.Arrays.sort(middle);
    for (int i = left_index; i <= right_index; i++) {
      array[i] = middle[i - left_index];
    }

    for (int i = 1; i < array.length; i++) {
      if (array[i - 1] > array[i]) {
        return false;
      }
    }
    return true;
  }


  public static int[] findUnsortedSequence_ll(int[] array) {
    int[] result = new int[2];

    int size = array.length;
    int leftIdx = 0;
    int rightIdx = size - 1;

    for (int i = 1; i < size - 1; i++) {
      if (array[i] > array[i - 1]) {
        leftIdx = i;
        break;
      }
    }

    for (int j = size - 1; j >= 0; j--) {
      if (array[j] > array[j - 1]) {
        rightIdx = j;
        break;
      }
    }
    result[0] = leftIdx;
    result[1] = rightIdx;

    System.out.println(leftIdx + " " + rightIdx);

    return result;
  }


  public static void main(String[] args) {
    int[] array = {1, 9, 4, 3, 5};
    findUnsortedSequence(array);
    findUnsortedSequence_ll(array);
  }

}
