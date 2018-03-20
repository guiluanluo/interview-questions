package Introduction;

import CtCILibrary.AssortedMethods;

/**
 * IMPORTANT: merge sort, quick sort, and bucket sort are the most commonly used in interviews!!!
 *
 * Merge sort | runtime: O(n log n) average and worst case. memory: depends
 *
 * merge sort divides the array in half, sorts each of those halves, and then merges them back together. each of those
 * halves has the same sorting algorithm applied to it. eventually, you are merging just two single-element arrays. it
 * is the "merge" part that does all the heavy lifting.
 *
 * the merge method operates by copying all the elements from the target array segment into a helper array, keeping
 * track of where the start of the left and right halves should be(helperLeft, helperRight). we then iterate through
 * helper, copying the smaller element from each half into the array. at the end, we copy any remaining elements into
 * the target array.
 *
 * you may notice that only the remaining elements from the left half of the helper array are copied into the target
 * array. why not the right hal? the right half doesn't need to be copied because it's already there.
 *
 * consider, for example, an array link [1,4,5 || 2,8,9] (the "||" indicate the partition point). prior to merging the
 * two halves, both the helper array and the target array segment will end with [8,9]. once we copy over four
 * elements(1,4,5,2) into the target array, the [8,9] will still be in place in both arrays. there's no need to copy
 * them over.
 *
 * The space complexity of merge sort is O(n) due to the auxiliary space used to merge parts of the array
 *
 * https://www.geeksforgeeks.org/merge-sort/
 */
public class MergeSort {

  public static void mergesort(int[] array) {
    int[] helper = new int[array.length];
    mergesort(array, helper, 0, array.length - 1);
  }

  public static void mergesort(int[] array, int[] helper, int low, int high) {
    if (low < high) {
      int middle = (low + high) / 2;
      mergesort(array, helper, low, middle); // Sort left half
      mergesort(array, helper, middle + 1, high); // Sort right half
      merge(array, helper, low, middle, high); // Merge them
    }
  }

  public static void merge(int[] array, int[] helper, int low, int middle, int high) {

    //Copy both halves into a helper array
    for (int i = low; i <= high; i++) {
      helper[i] = array[i];
    }

    int helperLeft = low;
    int helperRight = middle + 1;
    int current = low;

		/* Iterate through helper array. Compare the left and right
     * half, copying back the smaller element from the two halves
		 * into the original array.
		 */
    while (helperLeft <= middle && helperRight <= high) {

      if (helper[helperLeft] <= helper[helperRight]) {
        array[current] = helper[helperLeft];
        helperLeft++;
      } else { // If right element is smaller than left element
        array[current] = helper[helperRight];
        helperRight++;
      }
      current++;
    }

    /**
     * Copy the rest of the left side of the array into the target array.
     *
     * Note: only the remaining elements from the left half of the helper are copied into
     * the target array. why not the right half? the right half doesn't need to be copied because it's already there.
     */
    int remaining = middle - helperLeft;
    for (int i = 0; i <= remaining; i++) {
      array[current + i] = helper[helperLeft + i];
    }
  }

  public static void main(String[] args) {
    int size = 20;
    int[] array = AssortedMethods.randomArray(size, 0, size - 1);
    int[] validate = new int[size];
    AssortedMethods.printIntArray(array);
    for (int i = 0; i < size; i++) {
      validate[array[i]]++;
    }
    mergesort(array);
    for (int i = 0; i < size; i++) {
      validate[array[i]]--;
    }
    AssortedMethods.printIntArray(array);
    for (int i = 0; i < size; i++) {
      if (validate[i] != 0 || (i < (size - 1) && array[i] > array[i + 1])) {
        System.out.println("ERROR");
      }
    }
  }

}
