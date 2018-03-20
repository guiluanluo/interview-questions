package Q16_20_T9;

import java.util.ArrayList;
import java.util.HashSet;

import CtCILibrary.AssortedMethods;

/*
 * T9: on old cell phones, users typed on a numeric keypad and the phone would provide a list of words that matched
 * these numbers. each digit mapped to a set of 0-4 letters. implement an algorithm to return a list of matching words,
 * given a sequence of digits. you are provided a list of valid words(provided in whatever data structure you'd like).
 * the mapping is shown in the diagram below:
 * ------------------
 *  1     2     3
 *        abc   def
 *------------------
 *  4     5     6
 *  ghi   jkl   mno
 *------------------
 *  7     8     9
 *  pqrs  tuv   wxyz
 *------------------
 *        0
 *------------------
 *
 * example: input 8733    output: tree, used
 *
 * hint 470: consider recursion
 *
 * hint 486: can you recursively try all possibilities?
 *
 * hint 653: in the real world, we should know that some prefixes/substrings won't work. for example, consider
 * the number 33835676368. although 3383 does correspond to fftf, there are no words that start with fftf. there a way
 * we can shot-circuit in cases like this?
 *
 * hint 702: a trie might help us short-circuit. what if you sorted the whole list of words in the trie?
 *
 * hint 725: we are probably going to run this algorithm many times. if we did more preprocessing, is there a way
 * we could optimize this?
 *
 * hint 743: with preprocessing, we can actually get the looup time down to O(1)
 *
 * Lucy: solution C is the best!
 */
public class QuestionA {

  public static char[][] t9Letters = {
      null,          // 0
      null,          // 1
      {'a', 'b', 'c'},    // 2
      {'d', 'e', 'f'},    // 3
      {'g', 'h', 'i'},    // 4
      {'j', 'k', 'l'},    // 5
      {'m', 'n', 'o'},    // 6
      {'p', 'q', 'r', 's'},  // 7
      {'t', 'u', 'v'},    // 8
      {'w', 'x', 'y', 'z'}  // 9
  };

  public static char[] getT9Chars(char digit) {
    if (!Character.isDigit(digit)) {
      return null;
    }
    int dig = Character.getNumericValue(digit) - Character.getNumericValue('0');
    return t9Letters[dig];
  }

  public static void getValidWords(String number, int index, String prefix, HashSet<String> wordSet,
      ArrayList<String> results) {
    /* If it's a complete word, print it. */
    if (index == number.length()) {
      if (wordSet.contains(prefix)) {
        results.add(prefix);
      }
      return;
    }

		/* Get characters that match this digit */
    char digit = number.charAt(index);
    char[] letters = getT9Chars(digit);

		/* Go through all remaining options. */
    if (letters != null) {
      for (char letter : letters) {
        getValidWords(number, index + 1, prefix + letter, wordSet, results);
      }
    }
  }

  public static ArrayList<String> getValidT9Words(String number, HashSet<String> wordList) {
    ArrayList<String> results = new ArrayList<String>();
    getValidWords(number, 0, "", wordList, results);
    return results;
  }

  public static void main(String[] args) {
    ArrayList<String> words = getValidT9Words("33835676368", AssortedMethods.getWordListAsHashSet());
    for (String w : words) {
      System.out.println(w);
    }
  }

}
