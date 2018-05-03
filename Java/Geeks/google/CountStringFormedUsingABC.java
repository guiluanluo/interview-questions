package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * https://practice.geeksforgeeks.org/problems/count-of-strings-that-can-be-formed-using-a-b-and-c-under-given-constraints/0
 *
 * Given a length n, count the number of strings of length n that can be made using ‘a’, ‘b’ and ‘c’ with at-most one
 * ‘b’ and two ‘c’s allowed.
 *
 * Input: The first line of input contains an integer T denoting the number of test cases. Then T test cases follow. The
 * first line of each test case contains an integer N denoting the length of the string.
 *
 * Output: the count of the strings that can be formed under the given constraint.
 *
 * Constraints: 1<= T <=100, 1<= N <=1000
 *
 * Example: Input: 2   1   3    Output: 3 19
 *
 * Note: this is compute computation with duplicate character problem, but it takes too long to really generate all
 * strings. but using key = n + ":" + bCount + ":" + cCount, calculate the number from base case
 */
public class CountStringFormedUsingABC {

  /**
   * This is the best solution
   */
  private static HashMap<String, Integer> dp;

  public static int solve(int n, int bCount, int cCount) {
    if (bCount < 0 || cCount < 0) {
      return 0;
    }
    if (n == 0) {
      return 1;
    }
    if (bCount == 0 && cCount == 0) {
      return 1;
    }

    String key = n + ":" + bCount + ":" + cCount;
    if (dp.containsKey(key)) {
      return dp.get(key);
    }

    int result = solve(n - 1, bCount, cCount);   //a+1b+2c
    result += solve(n - 1, bCount, cCount - 1); //a+1b+1c
    result += solve(n - 1, bCount - 1, cCount); //a+2c
    dp.put(key, result);
    return result;
  }

  /**
   * the following solution generate all possible permutation strings.
   */
  public static void getPerms(String prefix, String remainder, Set<String> result) {
    if (remainder.length() == 0) {
      result.add(prefix);
    }

    int len = remainder.length();
    for (int i = 0; i < len; i++) {
      String before = remainder.substring(0, i);
      String after = remainder.substring(i + 1, len);
      char c = remainder.charAt(i);
      getPerms(prefix + c, before + after, result);
    }
  }

  public static Set<String> getPerms(String str) {
    Set<String> result = new HashSet<>();
    getPerms("", str, result);
    return result;
  }

  public static int calculatePerms(int n) {
    if (n < 1) {
      return 0;
    }
    if (n == 1) {
      return 3;
    }

    Set<String> result = new HashSet<>();
    String aStr = concatStr('a', n);
    String a1bStr = "b" + concatStr('a', n - 1);
    String a1cStr = "c" + concatStr('a', n - 1);
    String a1b1cStr = "bc" + concatStr('a', n - 2);
    String a2cStr = "cc" + concatStr('a', n - 2);
    String a1b2cStr = "bcc" + concatStr('a', n - 3);

    List<String> input = new ArrayList<>();
    input.add(aStr);
    input.add(a1bStr);
    input.add(a1cStr);
    input.add(a1b1cStr);
    input.add(a2cStr);
    input.add(a1b2cStr);

    for (String str : input) {
      if (str.length() > 0) {
        result.addAll(getPerms(str));
      }
    }
    return result.size();
  }

  private static String concatStr(char c, int n) {
    StringBuilder build = new StringBuilder();
    for (int i = 0; i < n; i++) {
      build.append(c);
    }
    return build.toString();
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      dp = new HashMap<String, Integer>();
      int n = Integer.parseInt(scanner.nextLine().trim());
      System.out.println("calculatePerms(n): " + calculatePerms(n));
      System.out.println("solve(n): " + solve(n, 1, 2));
      count++;
    }
  }
}
