package google;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/min-cut-square/0
 *
 * Given a paper of size A x B. Task is to cut the paper into squares of any size. Find the minimum number of squares
 * that can be cut from the paper.
 *
 * Input: The first line of input contains an integer T denoting the number of test cases. Then T test cases follow.
 * Each test case contains two integer A and B denoting the two size of the paper.
 *
 * Output: Print the minimum number of squares that can be cut from the paper.
 *
 * Constraints: 1<=T<=10^5 1<=A<=10^5 1<=B<=10^5
 *
 * Example: Input:
 * 2
 * 13 29
 * 30 35
 * Output:
 * 9
 * 5
 */
public class MinCutSquare {

  public static int cutSquare(int n, int m, int[][] memo) {
    if (m == n) {
      return 1;
    }

    if (memo[n][m] != 0) {
      return memo[n][m];
    }

    int verticalCutMin = Integer.MAX_VALUE;
    int horizontalCutMin = Integer.MAX_VALUE;
    for (int i = 1; i <= n / 2; i++) {
      verticalCutMin = Math.min(cutSquare(i, m, memo) + cutSquare(n - i, m, memo), verticalCutMin);
    }
    for (int i = 1; i <= m / 2; i++) {
      horizontalCutMin = Math.min(cutSquare(n, i, memo) + cutSquare(n, m - i, memo), horizontalCutMin);
    }
    memo[n][m] = Math.min(verticalCutMin, horizontalCutMin);
    return memo[n][m];
  }

  public static int cutSquare(int n, int m, Map<String, Integer> memoMap) {
    if (m == n) {
      return 1;
    }

    String key = n + "_" + m;
    if (memoMap.containsKey(key)) {
      return memoMap.get(key);
    }

    int verticalCutMin = Integer.MAX_VALUE;
    int horizontalCutMin = Integer.MAX_VALUE;
    for (int i = 1; i <= n / 2; i++) {
      verticalCutMin = Math.min(cutSquare(i, m, memoMap) + cutSquare(n - i, m, memoMap), verticalCutMin);
    }
    for (int i = 1; i <= m / 2; i++) {
      horizontalCutMin = Math.min(cutSquare(n, i, memoMap) + cutSquare(n, m - i, memoMap), horizontalCutMin);
    }

    int minCut = Math.min(verticalCutMin, horizontalCutMin);
    memoMap.put(key, minCut);
    return minCut;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      String[] input = scanner.nextLine().trim().split(" ");
      int a = Integer.parseInt(input[0]);
      int b = Integer.parseInt(input[1]);
      Map<String, Integer> memoMap = new HashMap<>();
      System.out.println("cutSquare() with HashMap: " + cutSquare(a, b, memoMap));

      int[][] memo = new int[a + 1][b + 1];
      System.out.println("cutSquare() with a matrix (faster!)" + cutSquare(a, b, memo));
      count++;
    }
  }
}
