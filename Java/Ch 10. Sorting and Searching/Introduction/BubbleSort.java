package Introduction;

import java.util.stream.IntStream;

/*
 * Bubble Sort | runtime O(n*n) average and worst case, memory: O(1)
 *
 * This is one of the most straightforward sorting algorithms; the core idea is to keep swapping adjacent elements of
 * an array if they are in an incorrect order until the collection is sorted.
 *
 * in bubble sort, we start at the beginning of the array and swap the first two elements if the first is greater than
 * the second. then , we got the next pair, and so on, continuously making sweeps of the array until it is sorted. in
 * doing so, the smaller items slowly "bubble"up to the beginning of the list.
 *
 * As mentioned earlier, to sort an array, we iterate through it while comparing adjacent elements, and swapping them
 * if necessary. For an array of size n, we perform n-1 such iterations.
 *
 * Let’s take up an example to understand the methodology. We’d like to sort the array in the ascending order:
 *   4    2    1    6    3    5
 * We start the first iteration by comparing 4 and 2; they are definitely not in the proper order. Swapping would result in:
 *   [2    4]    1    6    3    5
 * Now, repeating the same for 4 and 1:
 *   2    [1    4]    6    3    5
 * We keep doing it until the end:
 *  2    1    [4    6]    3    5
 *  2    1    4    [3    6]    5
 *  2    1    4    3    [5    6]
 *
 * As we can see, at the end of the first iteration, we got the last element at its rightful place. Now, all we need to
 * do is repeat the same procedure in further iterations. Except, we exclude the elements which are already sorted.
 *
 * In the second iteration, we’ll iterate through entire array except for the last element. Similarly, for 3rd iteration,
 * we omit last 2 elements. In general, for k-th iteration, we iterate till index n-k (excluded).
 * At the end of n-1 iterations, we’ll get the sorted array.
 *
 * Now that in you understand the technique, let’s dive into the implementation.
 */
public class BubbleSort {

  // A function to implement bubble sort with recursive
  public static void bubbleSort(int arr[], int n) {
    // Base case
    if (n == 1) {
      return;
    }

    // One pass of bubble sort. After this pass, the largest element is moved (or bubbled) to end.
    for (int i = 0; i < n - 1; i++) {
      if (arr[i] > arr[i + 1]) {
        // swap arr[i], arr[i+1]
        int temp = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = temp;
      }
    }

    // Largest element is fixed, recur for remaining array
    bubbleSort(arr, n - 1);
  }

  public static void bubbleSort(int arr[]) {
    int n = arr.length;
    int temp = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 1; j < (n - i); j++) {
        if (arr[j - 1] > arr[j]) {
          // swap elements
          temp = arr[j - 1];
          arr[j - 1] = arr[j];
          arr[j] = temp;
        }
      }
    }
  }

  public static void bubbleSort1(int[] arr) {
    int n = arr.length;
    IntStream.range(0, n - 1)
        .flatMap(i -> IntStream.range(i + 1, n - i))
        .forEach(j -> {
          if (arr[j - 1] > arr[j]) {
            int temp = arr[j];
            arr[j] = arr[j - 1];
            arr[j - 1] = temp;
          }
        });
  }


  public static String array2String(int arr[]) {
    StringBuilder builder = new StringBuilder();

    int n = arr.length;
    for (int i = 0; i < n; ++i) {
      builder.append(arr[i] + " ");
    }
    return builder.toString();
  }

  // Driver method to test above
  public static void main(String args[]) {
    int[] arr = {64, 34, 25, 12, 22, 11, 90};
    System.out.println("Array Before Bubble Sort: " + array2String(arr));
    bubbleSort(arr);
    System.out.println("Array After Bubble Sort: " + array2String(arr));

    int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
    bubbleSort1(arr1);
    System.out.println("Array After Bubble Sort(java8): " + array2String(arr));

    int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
    bubbleSort(arr2, arr2.length);
    System.out.println("Array After Bubble Sort(recursive): " + array2String(arr));
  }
}


