package Q1_02_Check_Permutation;

/**
 * this implementation use 1)An ASCII character in 8-bit ASCII encoding is 8 bits (1 byte), though it can fit in 7 bits:
 * int letter[] = new int[128] to count the number of char occurs
 */

public class QuestionB {

  public static boolean permutation(String s, String t) {
    if (s.length() != t.length()) {
      return false; // Permutations must be same length
    }

    int[] letters = new int[128]; // Assumption: ASCII
    for (int i = 0; i < s.length(); i++) {
      letters[s.charAt(i)]++;
    }

    for (int i = 0; i < t.length(); i++) {
      letters[t.charAt(i)]--;

      if (letters[t.charAt(i)] < 0) {
        return false;
      }
    }

    return true; // letters array has no negative values, and therefore no positive values either
  }

  public static void main(String[] args) {
    String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
    for (String[] pair : pairs) {
      String word1 = pair[0];
      String word2 = pair[1];
      boolean anagram = permutation(word1, word2);
      System.out.println(word1 + ", " + word2 + ": " + anagram);
    }
  }
}
