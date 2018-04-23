package Q16_15_Master_Mind;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Master mind: the game of master mind is played as follows: the computer has four slots, and each slot will contain a
 * ball that is red(R), yellow(Y), green(G) or blue(B). for example, the computer might have RGGB (slot #1 is red, slot
 * #2 and #3 are green, slot #4 is blue)
 *
 * you, the user, are trying to guess tht solution. you might, for example, guess YRGB. when you guess the correct color
 * for the correct slot, you get a "hit". if you guess a color that exists but is in the wrong slot, you get a
 * "pseudo-hit". note that a slot that is a hit can never count as a pseudo-hit.
 *
 * for example, if the actually solution is RGBY and you guess GGRR, you have one hit and one pseudo-hit. write a method
 * that, given a guess and a solution, return the number of hits and pseudo-hits.
 *
 * hint 638: try first creating an array with the frequency that each item occurs
 *
 * hint 729: for ease and clarity in implementation, you might want to use other methods and classes
 *
 * Solution: this problem straightforward, but it's surprisingly easy to make little mistakes. you should check your
 * code extremely thoroughly, on a variety of test cases.
 *
 * we will implement this code by first creating a frequency array which store how many time each character occurs in
 * solution, excluding times when the slot is a "hit". then, we iterate through guest to count the number of
 * preudo-hits.
 */
public class Question {

  public static class Result {

    public int hits;
    public int pseudoHits;

    public Result(int h, int p) {
      hits = h;
      pseudoHits = p;
    }

    public Result() {
    }

    public String toString() {
      return "(" + hits + ", " + pseudoHits + ")";
    }
  }

  public static Result estimate_lucy(String guess, String solution) {
    char[] guessArray = guess.toCharArray();
    char[] solutionArray = solution.toCharArray();

    int hits = 0;
    int pseudoHits = 0;

    //count hit number
    Set<Character> hitCharacters = new HashSet<>();
    for (int i = 0; i < guessArray.length; i++) {
      if (guessArray[i] == solutionArray[i]) {
        hits += 1;
        hitCharacters.add(guessArray[i]);
      }
    }

    //count pseudo-hit number
    Set<Character> solutionUniqueCharSet = new HashSet<>();
    for (Character sch : solutionArray) {
      solutionUniqueCharSet.add(sch);
    }

    for (int i = 0; i < guessArray.length; i++) {
      if (!hitCharacters.contains(guessArray[i])) {
        if (solutionUniqueCharSet.contains(guessArray[i])) {
          pseudoHits += 1;
        }
      }
    }

    return new Result(hits, pseudoHits);
  }


  public static int code(char c) {
    switch (c) {
      case 'B':
        return 0;
      case 'G':
        return 1;
      case 'R':
        return 2;
      case 'Y':
        return 3;
      default:
        return -1;
    }
  }

  public static int MAX_COLORS = 4;

  public static Result estimate(String guess, String solution) {
    if (guess.length() != solution.length()) {
      return null;
    }
    Result res = new Result();
    int[] frequencies = new int[MAX_COLORS];

		/* Compute hits and built frequency table */
    for (int i = 0; i < guess.length(); i++) {
      if (guess.charAt(i) == solution.charAt(i)) {
        res.hits++;
      } else {
        /* Only increment the frequency table (which will be used for pseudo-hits) if
         * it's not a hit. If it's a hit, the slot has already been "used." */
        int code = code(solution.charAt(i));
        if (code >= 0) {
          frequencies[code]++;
        }
      }
    }

		/* Compute pseudo-hits */
    for (int i = 0; i < guess.length(); i++) {
      int code = code(guess.charAt(i));
      if (code >= 0 && frequencies[code] > 0 && guess.charAt(i) != solution.charAt(i)) {
        res.pseudoHits++;
        frequencies[code]--;
      }
    }
    return res;
  }

  /************************** TEST CODE **********************************/

  public static char letterFromCode(int k) {
    switch (k) {
      case 0:
        return 'B';
      case 1:
        return 'G';
      case 2:
        return 'R';
      case 3:
        return 'Y';
      default:
        return '0';
    }
  }

  public static Result estimateBad(String g, String s) {
    char[] guess = g.toCharArray();
    char[] solution = s.toCharArray();
    int hits = 0;
    for (int i = 0; i < guess.length; i++) {
      if (guess[i] == solution[i]) {
        hits++;
        solution[i] = '0';
        guess[i] = '0';
      }
    }

    int pseudohits = 0;

    for (int i = 0; i < guess.length; i++) {
      if (guess[i] != '0') {
        for (int j = 0; j < solution.length; j++) {
          if (solution[j] != '0') {
            if (solution[j] == guess[i]) {
              pseudohits++;
              solution[j] = '0';
              break;
            }
          }
        }
      }
    }

    return new Result(hits, pseudohits);
  }

  public static String randomString() {
    int length = 4;
    char[] str = new char[length];
    Random generator = new Random();

    for (int i = 0; i < length; i++) {
      int v = generator.nextInt(4);
      char c = letterFromCode(v);
      str[i] = c;
    }

    return String.valueOf(str);
  }

  public static boolean test(String guess, String solution) {
    Result res1 = estimate(guess, solution);
    Result res2 = estimateBad(guess, solution);
    Result res3 = estimate_lucy(guess, solution);

    System.out.println(
        "**guess:" + guess + ", solution:" + solution + ", res1:" + res1 + ", res2:" + res2 + ", res3:" + res3);

    if (res1.hits == res2.hits && res1.pseudoHits == res2.pseudoHits) {
      return true;
    } else {
      System.out.println("FAIL: (" + guess + ", " + solution + "): " + res1.toString() + " | " + res2.toString());
      return false;
    }
  }

  public static boolean testRandom() {
    String guess = randomString();
    String solution = randomString();
    return test(guess, solution);
  }

  public static boolean test(int count) {
    for (int i = 0; i < count; i++) {
      if (!testRandom()) {
        return true;
      }
    }
    return false;
  }

  /********************** END TEST CODE ************************/


  public static void main(String[] args) {
    test(1000);
  }
}
