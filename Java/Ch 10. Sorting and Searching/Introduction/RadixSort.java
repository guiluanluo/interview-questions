package Introduction;

import java.util.Arrays;

/**
 * Radix Sort | runtime: O(kn)
 *
 * Radix sort is a sorting algorithm for integers(and some other data types) that takes advantage of the fact that
 * integers have a finite number of bits. in radix sort, we iterate through each digit of the number, group numbers by
 * each digit. for example, if have an array of integers, we might first sort by the first digit, so that the 0s are
 * grouped together. then, we sort each of these groupings by the next digit. we repeat this process sorting by each
 * subsequent digit, until finally the whole array is sorted.
 */
public class RadixSort {

  // A utility function to get maximum value in arr[]
  public static int getMax(int arr[], int n) {
    int mx = arr[0];
    for (int i = 1; i < n; i++) {
      if (arr[i] > mx) {
        mx = arr[i];
      }
    }
    return mx;
  }

  // A function to do counting sort of arr[] according to the digit represented by exp.
  public static void countSort(int arr[], int n, int exp) {
    int output[] = new int[n]; // output array
    int i;
    int count[] = new int[10];
    Arrays.fill(count, 0);

    System.out.println("n:" + n + ", exp:" + exp);

    // Store count of occurrences in count[]
    for (i = 0; i < n; i++) {
      count[(arr[i] / exp) % 10]++;
      System.out.println("count[(" + arr[i] + " /" + exp + ") % 10]:" + count[(arr[i] / exp) % 10]);
    }

    // Change count[i] so that count[i] now contains actual position of this digit in output[]
    for (i = 1; i < 10; i++) {
      count[i] += count[i - 1];
      System.out.println("count[" + i + "]:" + count[i]);
    }

    // Build the output array
    for (i = n - 1; i >= 0; i--) {
      output[count[(arr[i] / exp) % 10] - 1] = arr[i];
      count[(arr[i] / exp) % 10]--;
    }

    // Copy the output array to arr[], so that arr[] now contains sorted numbers according to current digit
    for (i = 0; i < n; i++) {
      arr[i] = output[i];
    }
  }

  // The main function to that sorts arr[] of size n using Radix Sort
  public static void radixsort(int arr[], int n) {
    // Find the maximum number to know number of digits
    int m = getMax(arr, n);
    System.out.println("m:" + m + ", array length n:" + n);

    // Do counting sort for every digit. Note that instead of passing digit number, exp is passed.
    // exp is 10^i where i is current digit number
    for (int exp = 1; m / exp > 0; exp *= 10) {
      System.out.println("");
      countSort(arr, n, exp);
    }
  }

  // A utility function to print an array
  public static void print(int arr[], int n) {
    for (int i = 0; i < n; i++) {
      System.out.print(arr[i] + " ");
    }
  }


  /*Driver function to check for above function*/
  public static void main(String[] args) {
    int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
    int n = arr.length;
    radixsort(arr, n);
    print(arr, n);
  }
}
