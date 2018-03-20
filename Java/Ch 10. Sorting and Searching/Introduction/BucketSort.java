package Introduction;

import java.util.Arrays;

/*
 * IMPORTANT: merge sort, quick sort, and bucket sort are the most commonly used in interviews!!!
 *
 * https://www.geeksforgeeks.org/bucket-sort-2/
 *
 * Bucket sort is mainly useful when input is uniformly distributed over a range. For example, consider the following
 * problem: "Sort a large set of floating point numbers which are in range from 0.0 to 1.0 and are uniformly distributed
 * across the range." How do we sort the numbers efficiently?
 *
 * A simple way is to apply a comparison based sorting algorithm. The lower bound for Comparison based sorting algorithm
 * (Merge Sort, Heap Sort, Quick-Sort .. etc) is Ω(n Log n), i.e., they cannot do better than nLogn. Can we sort the
 * array in linear time? Counting sort can not be applied here as we use keys as index in counting sort. Here keys are
 * floating point numbers. The idea is to use bucket sort. Following is bucket algorithm.
 *
 * bucketSort(arr[], n)
 *  1) Create n empty buckets (Or lists).
 *  2) Do following for every array element arr[i].
 *      .......a) Insert arr[i] into bucket[n*array[i]]
 *  3) Sort individual buckets using insertion sort.
 *  4) Concatenate all sorted buckets.
 *
 *  Time Complexity: If we assume that insertion in a bucket takes O(1) time then steps 1 and 2 of the above algorithm
 *  clearly take O(n) time. The O(1) is easily possible if we use a linked list to represent a bucket
 *  (In the following code, C++ vector is used for simplicity). Step 4 also takes O(n) time as there will be n items
 *  in all buckets. The main step to analyze is step 3. This step also takes O(n) time on average if all numbers are
 *  uniformly distributed (please refer CLRS book for more details)
 */
public class BucketSort {

  public static void sort(int[] arr, int maxVal) {

    int[] bucket = new int[maxVal + 1];

    for (int i = 0; i < bucket.length; i++) {
      bucket[i] = 0;
    }

    for (int i = 0; i < arr.length; i++) {
      bucket[arr[i]]++;
    }

    printBucket(bucket);

    int outPos = 0;
    for (int i = 0; i < bucket.length; i++) {
      for (int j = 0; j < bucket[i]; j++) {
        arr[outPos++] = i;
      }
    }
  }

  public static void printBucket(int[] bucket) {
    StringBuilder builder0 = new StringBuilder();
    for (int i = 0; i < bucket.length; i++) {
      builder0.append("i=" + i + ",bucket=" + bucket[i] + "  ");
    }
    System.out.println(builder0.toString());

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < bucket.length; i++) {
      for (int j = 0; j < bucket[i]; j++) {
        builder.append("j=" + j + ",i=" + i + "  ");
      }
    }
    System.out.println(builder.toString());
  }


  public static int[] sort1(int[] sequence, int maxValue) {
    // Bucket Sort
    int[] Bucket = new int[maxValue + 1];
    int[] sorted_sequence = new int[sequence.length];

    for (int i = 0; i < sequence.length; i++) {
      Bucket[sequence[i]]++;
    }

    int outPos = 0;
    for (int i = 0; i < Bucket.length; i++) {
      for (int j = 0; j < Bucket[i]; j++) {
        sorted_sequence[outPos++] = i;
      }
    }

    return sorted_sequence;
  }

  static void printSequence(int[] sorted_sequence) {
    for (int i = 0; i < sorted_sequence.length; i++) {
      System.out.print(sorted_sequence[i] + " ");
    }
  }

  static int maxValue(int[] sequence) {
    int maxValue = 0;
    for (int i = 0; i < sequence.length; i++) {
      if (sequence[i] > maxValue) {
        maxValue = sequence[i];
      }
    }
    return maxValue;
  }

  public static void main(String[] args) {
    int maxVal = 5;
    int[] data = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};

    System.out.println("Before: " + Arrays.toString(data));
    sort(data, maxVal);
    System.out.println("After:  " + Arrays.toString(data));
  }
}

