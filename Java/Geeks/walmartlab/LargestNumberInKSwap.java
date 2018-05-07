package walmartlab;

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

  private static String max = "0";

  public static void findMaximumNumWithKSwap(char[] str, int k) {
    if (k == 0) {
      return;
    }

    int n = str.length;

    // consider every digit
    for (int i = 0; i < n - 1; i++) {
      // and compare it with all digits after it
      for (int j = i + 1; j < n; j++) {
        // if digit at position i is less than digit at position j, swap it and check for maximum
        // number so far and recurse for remaining swaps
        if (Character.getNumericValue(str[i]) < Character.getNumericValue(str[j])) {
          // swap str[i] with str[j]
          str = swap(str, i, j);

          // If current num is more than maximum so far
          if (String.valueOf(str).compareTo(max) > 0) {
            max = String.valueOf(str);
          }

          // recurse of the other k - 1 swaps
          findMaximumNumWithKSwap(str, k - 1);

          // back track
          str = swap(str, i, j);
        }
      }
    }
  }

  private static char[] swap(char[] arr, int i, int j) {
    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
    return arr;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      int k = Integer.parseInt(scanner.nextLine().trim());
      String str = scanner.nextLine().trim();

      findMaximumNumWithKSwap(str.toCharArray(), k);
      System.out.println(max);
      count++;
    }
  }
}
