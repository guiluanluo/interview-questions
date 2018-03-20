package Q8_07_Permutations_Without_Dups;

import java.util.ArrayList;

/**
 * Solution B: building from permutations of all n-1 character subStrings
 *
 * Base case: single-character strings - P(a1) = a1
 * case : two-character strings - P(a1,a2)=a1a2, a2a1; P(a2,a3)=a2a3,a3a2; P(a1,a3)=a1a3,a3a1
 * case : three-character strings - P(a1a2a3) = {a1+P(a2,a3)} + {a2+P(a1,a3)} + {a3+P(a1,a2)}
 */
public class QuestionB {

  public static ArrayList<String> getPerms(String remainder) {
    int len = remainder.length();
    ArrayList<String> result = new ArrayList<String>();

		/* Base case. */
    if (len == 0) {
      result.add(""); // Be sure to return empty string!
      return result;
    }

    for (int i = 0; i < len; i++) {
      /* Remove char i and find permutations of remaining characters.*/
      String before = remainder.substring(0, i);
      String after = remainder.substring(i + 1, len);
      ArrayList<String> partials = getPerms(before + after);

			/* Prepend char i to each permutation.*/
      for (String s : partials) {
        result.add(remainder.charAt(i) + s);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    ArrayList<String> list = getPerms("abc");
    System.out.println("There are " + list.size() + " permutations.");
    for (String s : list) {
      System.out.println(s);
    }
  }

}
