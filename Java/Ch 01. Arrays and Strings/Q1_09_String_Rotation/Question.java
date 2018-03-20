package Q1_09_String_Rotation;

/**
 * String Rotation: assume you have a method isSubstring() which check if one word is a substring of another. given two
 * strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call isSubstring(eg, "waterbotttle"
 * is a rotation of "erbotallewat").
 *
 * Hint 34: if a string is a rotation of another, then it's a rotation at a particular point. for example, a rotation of
 * waterbottle at character 3 mean cutting waterbootle at character 3 and putting the right half(erbottle before the
 * left half (wat).
 *
 * Hint 88: we are essentially asking if there's a way of splitting the first string into two parts, x and y, such that
 * the first string is xy and the second string is yx. for example, x=wat and y=erbottle. the first string is
 * xy=waterbottle. the second string is yx=erbottlewat.
 *
 * Hint 104: thing about the earlier hint. then think about what happens when you concatenate erbottlewat to itself. you
 * get erbottlewatterbottlewat.
 */
public class Question {

  public static boolean isSubstring(String big, String small) {
    if (big.indexOf(small) >= 0) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isRotation(String s1, String s2) {
    int len = s1.length();

    /* check that s1 and s2 are equal length and not empty */
    if (len == s2.length() && len > 0) {
      /* concatenate s1 and s1 within new buffer */
      String s1s1 = s1 + s1;
      return isSubstring(s1s1, s2);
    }
    return false;
  }

  public static void main(String[] args) {
    String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
    for (String[] pair : pairs) {
      String word1 = pair[0];
      String word2 = pair[1];
      boolean is_rotation = isRotation(word1, word2);
      System.out.println(word1 + ", " + word2 + ": " + is_rotation);
    }
  }

}
