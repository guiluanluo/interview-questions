package walmartlab;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/largest-number-in-k-swaps/0
 *
 * Given a number K and string S of digits denoting a positive integer, build the largest number possible by performing
 * swap operations on the digits of S at most K times.
 *
 * Input The first line of input contains an integer T denoting the number of test cases. Then T test cases follow. The
 * first line of each test case contains a positive integer K. The second line of each test case contains a string of
 * digits denoting a positive integer.
 *
 *
 * Output Print out the largest number possible.
 *
 *
 * Constraints 1 <= T <= 100, 0 < S <= 30, 0 < K <= 10
 *
 * Examples
 *
 * Input
 * 3
 * 4
 * 1234567
 * 3
 * 3435335
 * 2
 * 1034
 *
 * Output 7654321 5543333 4301
 */
public class LargestNumberInKSwap {

  public static String swaps(String str, int k) {
    PriorityQueue<Character> minHeap = new PriorityQueue(k, new Comparator<Character>() {
      @Override
      public int compare(Character o1, Character o2) {
        return o1 >= o2 ? 1 : -1;
      }
    });

    char[] arr = str.toCharArray();
    for (char c : arr) {
      if (minHeap.size() == k) {
        if (c > minHeap.peek()) {
          minHeap.poll();
          minHeap.add(c);
        }
      } else {
        minHeap.add(c);
      }
    }

    StringBuilder build = new StringBuilder();
    while (!minHeap.isEmpty()) {
      build.append(minHeap.poll());
    }

    return build.toString();
  }

  public static String swaps_2(String str, int k) {
    char[] arr = str.toCharArray();
    Arrays.sort(arr);

    int size = arr.length;

    StringBuilder build = new StringBuilder();
    for (int i = 0; i < k; i++) {
      build.append(arr[size - 1 - i]);
    }

    String kstr = build.toString();
    int count = 0;
    for (int j = 0; j < str.length() && count < size - k; j++) {
      if (!kstr.contains(str.charAt(j) + "")) {
        build.append(str.charAt(j));
        count++;
      }
    }
    return build.toString();
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      int k = Integer.parseInt(scanner.nextLine().trim());
      String str = scanner.nextLine().trim();
//      System.out.println("result:" + swaps(str, k));
      System.out.println("result2:" + swaps_2(str, k));
      count++;
    }
  }
}
