package Q8_07_Permutations_Without_Dups;

import java.util.ArrayList;

/**
 * Permutations without Dups: write a method to compute all permutations of a string of unique characters.
 *
 * hint 150: approach 1: suppose you had all permutations of abc. how can you use that to get all permutations of abcd?
 *
 * hint 185: approach 1: the permutations of abc represent all ways of ordering abc. now, we want to create all
 * orderings of abcd. take a specific ordering of abcd, such as bdca. this bcda sting represents an ordering of abc,
 * too: remove the d and you get bca. given the string bca, can you create all the "related" orderings that include d,
 * too?
 *
 * hint 200: approach 1: given a string such as bcd, you can create all permutations of abcd that have {a,b,c} in order
 * bca by inserting d into each possible location: dbca,bdca,bcda,bcad. given all permutations of abc, can you then
 * create all permutations of abcd?
 *
 * hint 267: approach 1: you can create all permutations of abcd by computing all permutations of abc and then inserting
 * d into each possible location within those.
 *
 * hint 278:approach 2: if you have all permutations of two-character sub-strings, could you generate all permutations
 * of three-character sub-strings?
 *
 * hint 309: approach 2: to generate a permutation of abcd, you need to pick an initial character. it can be a,b,c,d.
 * you can then permute the remaining characters. how can you use this approach to generate all permutations of the full
 * string?
 *
 * hint 335: approach 2: to generate all permutations of abcd, pick each character(a,b,c,d) as a starting character.
 * permute the remaining characters and prepend the starting character. how do you permute the remaining characters?
 * with a recursive process that follows the same logic.
 *
 * hint 356: approach 2: you can implement this approach by having the recursive function pass back the list of the
 * strings, and then you prepend the starting character to it. or, you can push down a prefix to the recursive calls.
 *
 * Solution A: building from permutations of first n-1 characters
 */
public class QuestionA {

  public static ArrayList<String> getPerms(String str) {
    if (str == null) {
      return null;
    }

    ArrayList<String> permutations = new ArrayList<String>();
    if (str.length() == 0) { // base case
      permutations.add("");
      return permutations;
    }

    char first = str.charAt(0); // get the first character
    String remainder = str.substring(1); // remove the first character

    ArrayList<String> words = getPerms(remainder); // get all permutation of remainder string

    for (String word : words) {                  // insert the first character into each possible location within those
      for (int j = 0; j <= word.length(); j++) {
        String s = insertCharAt(word, first, j);
        permutations.add(s);
      }
    }
    return permutations;
  }

  public static String insertCharAt(String word, char c, int i) {
    String start = word.substring(0, i);
    String end = word.substring(i);
    return start + c + end;
  }

  public static void main(String[] args) {
    ArrayList<String> list = getPerms("abcde");
    System.out.println("There are " + list.size() + " permutations.");
    for (String s : list) {
      System.out.println(s);
    }
  }

}
