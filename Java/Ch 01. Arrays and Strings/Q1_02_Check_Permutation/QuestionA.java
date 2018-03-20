package Q1_02_Check_Permutation;

/*
 * Check permutation: give two strings, write a method to decide if one is a permutation of the other
 *
 * Solution hint 1: describe what is means for two string to be permutations of each other. now look at that definition
 * you provided. can you check the strings against that definition?
 *
 * hint 84: there is one solution that is O(N log N) time. another solutions uses some space, but is O(N) time.
 * hint 122: could a hash table be useful?
 * hint 131: two strings that are permutations should have the same characters, but in different orders. can you make
 * the orders the same?
 *
 * Solution A: sort two strings first , and then compare them.  sorting takes O(n log(n)) performance
 */

public class QuestionA {

  public static String sort(String s) {
    char[] content = s.toCharArray();

    //Java uses Dual-Pivot Quicksort, this algorithm offers O(n log(n)) performance
    java.util.Arrays.sort(content);
    return new String(content);
  }

  public static boolean permutation(String s, String t) {
    return sort(s).equals(sort(t));
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
