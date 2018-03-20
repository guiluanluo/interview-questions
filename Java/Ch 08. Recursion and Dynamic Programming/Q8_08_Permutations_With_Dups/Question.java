package Q8_08_Permutations_With_Dups;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Permutations with Dups: write a method to compute all permutations of a string whose characters are not necessarily
 * unique. the list of permutations should not have duplicates.
 *
 * hint 161: you can handle this by just checking to see if there are duplicates before printing them(or adding them to
 * a list). you can do this with a hash table. in what case might this be okay? in what case might it not be a very good
 * solution?
 *
 * hint 190: if you haven't solved 8.7 yet, do that one first
 *
 * hint 222: try getting the count of each character. for example, ABCAAC has 3 As, 2 Cs, 1 B.
 *
 * hint 255: to get all permutations with 3 As, 2 Cs, 1 B, you need to fist pick a starting character: A, B, or C. if
 * it's an A, then you need all permutations with 2 As, 2 Cs, and 1 B.
 *
 * Solution: this is very similar to the previous problem, except that now we could potentially have duplicate
 * characters in the words.
 *
 * one simple way of handling this problem is to do the same work to check if a permutation has been created before and
 * then, if not, add it to the list. a simple hash table will do the trick her. this solution will take O(n!) time in
 * the worst case(and, in fact, in all cases).
 *
 * consider a string with all duplicate characters, like aaaaaaaaaaaaa. it takes long time to compute it but there is
 * only 1 permutation. ideally we would like to only create the unique permutations, rather than creating every
 * permutation and then ruling out the duplicate.
 *
 * we could start with computing the count of each letter for string "aabbbbc" would be: a->2, b->4, c->1. let's imagine
 * generating a permutation of this string. the first choice we make is whether to use an a,b,or c as the first
 * character. after that, we have a subproblem to solve: find all permutations of the remaining characters, adn append
 * those to the already picked "prefix".
 * P(a->2, b->4, c->1) =
 *    P(a->1, b->4, c->1)
 *    P(a->2, b->3, c->1)
 *    P(a->2, b->4, c->0)
 */
public class Question {

  public static HashMap<Character, Integer> buildFreqTable(String s) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for (char c : s.toCharArray()) {
      if (!map.containsKey(c)) {
        map.put(c, 0);
      }
      map.put(c, map.get(c) + 1);
    }
    return map;
  }

  public static void printPerms(HashMap<Character, Integer> map, String prefix, int remaining,
      ArrayList<String> result) {

    if (remaining == 0) {
      result.add(prefix);
      return;
    }

    for (Character c : map.keySet()) {
      int count = map.get(c);
      if (count > 0) {
        map.put(c, count - 1);

        printPerms(map, prefix + c, remaining - 1, result);
        map.put(c, count);
      }
    }
  }

  public static ArrayList<String> printPerms(String s) {
    ArrayList<String> result = new ArrayList<String>();
    HashMap<Character, Integer> map = buildFreqTable(s);
    printPerms(map, "", s.length(), result);
    return result;
  }

  public static void main(String[] args) {
    String s = "aabbccc";
    ArrayList<String> result = printPerms(s);
    System.out.println("Count: " + result.size());
    for (String r : result) {
      System.out.println(r);
    }
  }

}
