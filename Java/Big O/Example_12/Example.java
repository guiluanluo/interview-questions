package Example_12;

/**
 * count all permutation of a string, for example "123", "132", "213", "231", "312", "321"
 */

public class Example {

  public static void permutation(String str) {
    permutation(str, "");
  }

  public static void permutation(String str, String prefix) {
    if (str.length() == 0) {
      System.out.println("prefix:" + prefix + "\n");
    } else {
      for (int i = 0; i < str.length(); i++) {
        String rem = str.substring(0, i) + str.substring(i + 1);
        System.out.println("rem:" + rem + ", prefix:" + prefix + ", str.charAt(" + i + "):" + str.charAt(i));
        permutation(rem, prefix + str.charAt(i));
      }
    }
  }


  public static void main(String[] args) {
    permutation("123");
  }


}
