package Q16_18_Pattern_Matcher;

/**
 * Pattern matching: you are given two strings, pattern and value. the pattern string consists of just the letters a and
 * b, describing a pattern within a string. for example, the string "catcatgocatgo" matches the pattern aabab (where
 * "cat" is a and "go" is b). it also matches patterns like a, ab, and b. write a method to determine if value matches
 * pattern.
 *
 * hints 630: start with a brute force approach. can you try all possibilities for a and b?
 *
 * hint 642: observe that one of the substrings, either a or b, must start at the beginning of the string. that cuts
 * down the number of possibilities.
 *
 * hint 652: don't forget to handle the possibility that the first character in the pattern is b.
 *
 * hint 662: be careful with how you analyze the runtime. if you iterate through O(n*n) substrings and each one does an
 * O(n) string comparison, then the total runtime is O(n*n*n)
 *
 * hint 684: suppose you decide on a specific value for the "a" part of a pattern. how many possibilities are there for
 * b?
 *
 * hint 717: since the value of a determines the value of b (and vice versa) and either a or b must start at the
 * beginning of the value, you should have only O(n) possibilities for how to split up the pattern.
 *
 * hint 726: you should be able to have an O(n*n) algorithm
 */
public class QuestionA {

  public static boolean doesMatch(String pattern, String value) {
    if (pattern.length() == 0) {
      return value.length() == 0;
    }

    int size = value.length();
    for (int mainSize = 0; mainSize < size; mainSize++) {
      String main = value.substring(0, mainSize);

      for (int altStart = mainSize; altStart <= size; altStart++) {
        for (int altEnd = altStart; altEnd <= size; altEnd++) {

          String alt = value.substring(altStart, altEnd);

          String cand = buildFromPattern(pattern, main, alt);
          if (cand.equals(value)) {
            System.out.println(main + ", " + alt);
            return true;
          }
        }
      }
    }
    return false;
  }

  public static String buildFromPattern(String pattern, String main, String alt) {
    StringBuffer sb = new StringBuffer();
    char first = pattern.charAt(0);
    for (char c : pattern.toCharArray()) {
      if (c == first) {
        sb.append(main);
      } else {
        sb.append(alt);
      }
    }
    return sb.toString();
  }


  public static void main(String[] args) {

    System.out.println("index:" + "ababb".indexOf("a", 1));

    String[][] tests = {{"ababb", "backbatbackbatbat"}, {"abab", "backsbatbackbats"}, {"aba", "backsbatbacksbat"}};
    for (String[] test : tests) {
      String pattern = test[0];
      String value = test[1];
      System.out.println(pattern + ", " + value + ": " + doesMatch(pattern, value));
    }

  }

}
