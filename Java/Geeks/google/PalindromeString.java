package google;

import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/save-ironman/0
 *
 * Jarvis is weak in computing palindromes for Alphanumeric characters.
 * While Ironman is busy fighting Thanos, he needs to activate sonic punch but Jarvis is stuck in computing palindromes.
 * You are given a string containing the alphanumeric character. Find whether the string is palindrome or not.
 * If you are unable to solve it then it may result in the death of Iron Man.
 *
 * Input:
 * The first line of the input contains t, the number of test cases. Each line of the test case contains string 'S'.
 *
 * Output:
 * Each new line of the output contains "YES" if the string is palindrome and "NO" if the string is not a palindrome.
 *
 * Constraints:
 * 1<=t<=100
 * 1<=|S|<=100000
 * Note: Consider alphabets and numbers only for palindrome check. Ignore symbols and whitespaces.
 *
 * Example:
 * Input:
 * 2
 * I am :IronnorI Ma, i
 * Ab?/Ba
 *
 * Output:
 * YES
 * YES
 */
public class PalindromeString {

  private static boolean isLetterOrDigit(char c) {
    return (c >= 'a' && c <= 'z') ||
        (c >= 'A' && c <= 'Z') ||
        (c >= '0' && c <= '9');
  }

  private static String filter(String str) {
    StringBuilder build = new StringBuilder();
    char[] arr = str.toCharArray();
    for (char c : arr) {
      if (isLetterOrDigit(c)) {
        build.append(c);
      }
    }
    return build.toString();
  }

  public static String isPalindromeStr(String str) {
    if (str == null || str.length() < 2) {
      return "NO";
    }

    String filtedStr = filter(str);
    char[] arr = filtedStr.toCharArray();
    int startIdx = 0;
    int endIdx = arr.length - 1;
    while (startIdx < endIdx) {
      if (arr[startIdx] != arr[endIdx]) {
        return "NO";
      }
      startIdx++;
      endIdx--;
    }
    return "YES";
  }


  private static boolean isLetterOrDigit_1(char c) {
    return Character.isLetter(c) || Character.isDigit(c);
  }

  public static String isPalindromeStr_1(String str) {
    if (str == null || str.length() < 2) {
      return "NO";
    }

    char[] arr = str.toCharArray();
    int startIdx = 0;
    int endIdx = arr.length - 1;
    while (startIdx < endIdx) {
      while (startIdx < endIdx && !isLetterOrDigit_1(arr[startIdx])) {
        startIdx++;
      }
      while (endIdx > startIdx && !isLetterOrDigit_1(arr[endIdx])) {
        endIdx--;
      }
      if (arr[startIdx] != arr[endIdx]) {
        return "NO";
      }

      startIdx++;
      endIdx--;
    }
    return "YES";
  }


  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      String str = scanner.nextLine().trim();
      String result = isPalindromeStr(str.toUpperCase());
      System.out.println("isPalindromeStr(): " + result);
      System.out.println("isPalindromeStr_1(): " + isPalindromeStr_1(str.toUpperCase()));
      count++;
    }
  }
}