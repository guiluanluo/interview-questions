package Q1_00_String;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/combine-the-strings/0
 *
 * Given N strings consisting of ‘R’ and ‘B’. Two strings can be only combined if last character of first string and
 * first character of second string are same. Print the maximum length possible by combining strings.
 *
 * Input: The first line of input contains an integer T denoting the number of test cases. Then T test cases follow.
 * Each test case consists of two lines. First line of each test case contains an Integer N denoting number of strings
 * and the second line contains N space separated strings of equal length.
 *
 * Output: For each test case, in a new line print the maximum length possible. If no strings can combine then print
 * "0".
 *
 * Constraints: 1<=T<=100, 2<=N<=1000, 1<=|String length|<=1000
 *
 * Example: Input: 2 3 RBR BBR RRR     2 RRR BBB
 *
 * Output: 9 0
 */
public class CombineStrings {

  public static int findMaxCombineString(String[] arr, int n) {
    int l = arr[0].length();
    int rr = 0;
    int bb = 0;
    int br = 0;
    int rb = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i].charAt(0) == 'R' && arr[i].charAt(l - 1) == 'R') {
        rr++;
      } else if (arr[i].charAt(0) == 'B' && arr[i].charAt(l - 1) == 'B') {
        bb++;
      } else if (arr[i].charAt(0) == 'B' && arr[i].charAt(l - 1) == 'R') {
        br++;
      } else if (arr[i].charAt(0) == 'R' && arr[i].charAt(l - 1) == 'B') {
        rb++;
      }
    }

    int m = Math.min(br, rb);
    if (br + rb == 0) {
      m = Math.max(bb, rr) == 1 ? 0 : Math.max(bb, rr) * l;
    } else {
      if (br == rb) {
        m = (2 * m + (bb + rr)) * l;
      } else {
        m = (br == 0 || rb == 0) && (bb + rr == 0) ? 0 : ((2 * m + 1) + (bb + rr)) * l;
      }
    }
    return m;
  }


  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      int n = Integer.parseInt(scanner.nextLine().trim());
      String[] arrItems = scanner.nextLine().split(" ");
      int result = findMaxCombineString(arrItems, n);
      System.out.println(result);
      count++;
    }
  }
}
