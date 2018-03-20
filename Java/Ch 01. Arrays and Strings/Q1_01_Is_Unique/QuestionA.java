package Q1_01_Is_Unique;

/*
 * Is Unique: implement an algorithm to determine if a string has all unique characters. what if you can not use
 * additional data structures?
 *
 * solution hint 44: try a hash table;
 * hint 117: could a bit vector be useful?
 * hint 132: can you solve it in O(N logN) time? what might solve like that?
 *
 * Note: should ask interviewer if the string is an ASCII string or Unicode string.
 *
 * 8 bits : It depends what is the character and what encoding it is in: An ASCII character in 8-bit ASCII encoding is 8
 * bits (1 byte), though it can fit in 7 bits. An ISO-8895-1 character in ISO-8859-1 encoding is 8 bits (1 byte). A
 * Unicode character in UTF-8 encoding is between 8 bits (1 byte) and 32 bits (4 bytes).
 *
 * the time complexity for this code is O(n), the space complexity is O(1). Solution A uses data structure "array",
 * Solution B doesn't use a variable: int checker
 */
public class QuestionA {

  public static boolean isUniqueChars(String str) {
    //TODO: the length of input str could >128, don't need this check -LL
    if (str.length() > 128) {
      return false;
    }

    //2 power 7 is 128
    boolean[] char_set = new boolean[128];
    for (int i = 0; i < str.length(); i++) {
      int val = str.charAt(i);
      if (char_set[val]) {
        return false;
      }
      char_set[val] = true;
    }
    return true;
  }

  public static void char2Int_lucy(char[] charArray) {
    for (int i = 0; i < charArray.length; i++) {
      int charValue = charArray[i];
      System.out.println(charArray[i] + ", " + charValue);
    }
  }

  public static void main(String[] args) {
    char2Int_lucy("0123".toCharArray());
    char2Int_lucy("abcdz".toCharArray());
    char2Int_lucy("ABCDZ".toCharArray());

    String[] words = {"abcde", "hello", "apple", "kite", "padle"};
    for (String word : words) {
      System.out.println(word + ": " + isUniqueChars(word) + "\n");
    }

    String word_65 = "abcde111112222233333444445555566666777778888899999000001111122222";
    String[] word_130 = {word_65 + word_65};
    for (String word : word_130) {
      System.out.println(word + ": " + isUniqueChars(word) + "\n");
    }
  }

}
