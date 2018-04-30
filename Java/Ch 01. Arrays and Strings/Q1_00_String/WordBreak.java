package Q1_00_String;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/word-break/0
 *
 * Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated
 * sequence of dictionary words. See following examples for more details.
 *
 * Consider the following dictionary { i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}
 *
 * Input:  ilike Output: Yes The string can be segmented as "i like".
 *
 * Input:  ilikesamsung Output: Yes The string can be segmented as "i like samsung" or "i like sam sung".
 *
 * Input: First line is integer T denoting number of test cases. 1<=T<=100. Every test case has 3 lines. First line is N
 * number of words in dictionary. 1<=N<=12. Second line contains N strings denoting the words of dictionary. Length of
 * each word is less than 15. Third line contains a string S of length less than 1000.
 *
 * Output: Print 1 is possible to break words, else print 0.
 *
 * Example: Input: 2 12 i like sam sung samsung mobile ice cream icecream man go mango ilike 12 i like sam sung samsung
 * mobile ice cream icecream man go mango idontlike Output: 1 0
 *
 * Note: the same question as Q17_13_ReSpace
 */
public class WordBreak {

  public static int isWorkBreak(List<String> dictionary, String s) {
    List<String> results = new ArrayList<>();

    char[] sentence = s.toCharArray();
    String prefix = "";
    String previous = "";
    boolean found = false;
    int foundLength = 0;

    for (int i = 0; i < sentence.length; i++) {
      prefix = prefix + sentence[i];
      if (dictionary.contains(prefix)) {
        found = true;
        previous = prefix;
      } else {
        if (found) {
          results.add(previous);
          foundLength += previous.length();
          i = i - 1;
          prefix = "";
          previous = "";
          found = false;
        }
      }
    }

    //handle the last word
    if (found) {
      results.add(previous);
      foundLength += previous.length();
    }

    StringBuilder build = new StringBuilder();
    for (String s1 : results) {
      build.append(s1).append(" ");
    }
    System.out.println("results:" + build.toString());

    return foundLength == s.length() ? 1 : 0;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int i = 0;
    while (i < testCount) {
      int numWords = Integer.parseInt(scanner.nextLine().trim());
      String[] words = scanner.nextLine().trim().split(" ");

      List<String> wordList = new ArrayList<>();
      for (int j = 0; j < numWords; j++) {
        wordList.add(words[j]);
      }
      String input = scanner.nextLine().trim();
      int result = isWorkBreak(wordList, input);
      System.out.println(result);

      i++;
    }
  }
}