package walmartlab;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/k-largest-elements/0
 *
 * Given an array, print k largest elements from the array.  The output elements should be printed in decreasing order.
 *
 * Input:
 *
 * The first line of input contains an integer T denoting the number of test cases.
 * The first line of each test case is N and k, N is the size of array and K is the largest elements to be returned.
 * The second line of each test case contains N input C[i].
 *
 * Output:
 *
 * Print the k largest element in descending order.
 *
 * Constraints:
 *
 * 1 ≤ T ≤ 100
 * 1 ≤ N ≤ 100
 * K ≤ N
 * 1 ≤ C[i] ≤ 1000
 *
 * Example:
 *
 * Input:
 * 2
 * 5 2
 * 12 5 787 1 23
 * 7 3
 * 1 23 12 9 30 2 50
 *
 * Output:
 * 787 23
 * 50 30 23
 */
public class KLargestElements {

  public static void printKLargestElements(int[] arr, int k) {
    PriorityQueue<Integer> minheap = new PriorityQueue<>(k, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });

    for (int a : arr) {
      if (minheap.size() == k) {
        if (minheap.peek() < a) {
          minheap.poll();
          minheap.add(a);
        }
      } else {
        minheap.add(a);
      }
    }

    int[] result = new int[k];
    int lastIdx = k - 1;
    while (!minheap.isEmpty()) {
      result[lastIdx--] = minheap.poll();
    }

    StringBuilder build = new StringBuilder();
    for (int r : result) {
      build.append(r).append(" ");
    }
    System.out.println(build.toString());
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      String[] input1 = scanner.nextLine().trim().split(" ");
      int n = Integer.parseInt(input1[0]);
      int k = Integer.parseInt(input1[1]);

      String[] input2 = scanner.nextLine().trim().split(" ");
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = Integer.parseInt(input2[i]);
      }

      printKLargestElements(arr, k);
      count++;
    }
  }
}

