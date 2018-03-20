package Q8_08_Permutations_With_Dups;

import java.util.HashSet;
import java.util.Set;

/**
 * Lucy Solution: Alternatively, instead of the permutations back up the stack, we can push the prefix down the stack.
 * when we get to the bottom(base case), prefix holds a full permutation. we use Set to eliminate duplicate
 * permutation!!!
 */
public class QuestionB {

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

  public static void main(String[] args) {
    Set<String> list = getPerms("aabbccc");
    System.out.println("There are " + list.size() + " permutations.");
    for (String s : list) {
      System.out.println(s);
    }
  }
}
