package Big_O;

import java.util.ArrayList;
import java.util.List;

/**
 * permutation of one string only!
 */
public class Ex_16 {

  public static List<String> permutation(String str) {
    List<String> results = new ArrayList<>();
    permutation(str, "", results);
    return results;
  }

  public static void permutation(String str, String prefix, List<String> results) {
    if (str.length() == 0) {
      System.out.println(prefix);
      results.add(prefix);
    } else {
      for (int i = 0; i < str.length(); i++) {
        String rem = str.substring(0, i) + str.substring(i + 1);
        permutation(rem, prefix + str.charAt(i), results);
      }
    }
  }

  public static void main(String[] args) {

    List<String> results = permutation("abc");
    System.out.println("results:" + results);
  }
}
