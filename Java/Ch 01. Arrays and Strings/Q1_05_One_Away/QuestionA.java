package Q1_05_One_Away;

/*
 * One Away: there are three types of edits that can be performed on strings: insert a character, remove a character, or
 * replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.
 *
 * Example:
 * pale, ple -> true      one removal away
 * pales, pale -> true    one removal away
 * pale, ppale -> true    one insertion away
 * pale, bale -> true     one replacement away
 * pale, bake -> false
 *
 * hints 23: start with the easy thing. can you check each of the conditions separately?
 * hints 97: what is relationship between the "insert character" option and the "remove character" option? do these need
 * to be two separate check?
 * hint 130: can you do all three checks in a single pass?
 *
 * removal is just the inverse of insertion
 *
 *
 */
public class QuestionA {

  public static boolean oneEditReplace_lucy(String s1, String s2) {
    int len1 = s1.length();
    int len2 = s2.length();

    char[] s1Array = s1.toCharArray();
    char[] s2Array = s2.toCharArray();

    if (len1 == len2) {
      //replace
      int diffCount = 0;
      for (int i = 0; i < len1; i++) {
        if (s1Array[i] != s2Array[i]) {
          diffCount++;
        }
      }
      return diffCount == 1;

    } else if (len1 < len2) {
      //insert or remove
      int diffCount = 0;
      int index1 = 0, index2 = 0;
      while (index1 < len1 && index2 < len2) {
        if (s1Array[index1] != s2Array[index2]) {
          diffCount++;
          index2++;
        } else {
          index1++;
          index2++;
        }
      }
      return diffCount == 1;
    }
    return false;
  }

  public static boolean oneEditReplace(String s1, String s2) {
    boolean foundDifference = false;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (foundDifference) {
          return false;
        }

        foundDifference = true;
      }
    }
    return true;
  }

  /* Check if you can insert a character into s1 to make s2. */
  public static boolean oneEditInsert(String s1, String s2) {
    int index1 = 0;
    int index2 = 0;
    while (index2 < s2.length() && index1 < s1.length()) {
      if (s1.charAt(index1) != s2.charAt(index2)) {
        if (index1 != index2) {
          return false;
        }
        index2++;
      } else {
        index1++;
        index2++;
      }
    }
    return true;
  }

  public static boolean oneEditAway(String first, String second) {
    if (first.length() == second.length()) {
      return oneEditReplace(first, second);
    } else if (first.length() + 1 == second.length()) {
      return oneEditInsert(first, second);
    } else if (first.length() - 1 == second.length()) {
      return oneEditInsert(second, first);
    }
    return false;
  }

  public static void main(String[] args) {
    String a = "pse";
    String b = "pale";
    boolean isOneEdit = oneEditAway(a, b);
    System.out.println(a + ", " + b + ": " + isOneEdit);

    System.out.println("lucy:" + a + ", " + b + ": " + oneEditReplace_lucy(a, b));
  }

}
