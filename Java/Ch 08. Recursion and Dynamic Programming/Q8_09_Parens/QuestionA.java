package Q8_09_Parens;

import java.util.HashSet;
import java.util.Set;

/*
 * Parens: implement an algorithm to print all valid(e.g., properly opened and closed) combinations of n pairs of
 * parentheses.
 *
 * Example: input: 3    Output: ((())), (()()), ()(()), ()(()), ()()()
 *
 * hint 138: try the base case and build approach
 *
 * hint 174: suppose we had all valid ways of writing two pairs of parentheses? how could we use this to ge all valid
 * ways of writing three pairs?
 *
 * hint 187: we could try generating the solution for three pairs by taking the list of two pairs of parentheses and
 * adding a third pair. we'd have to add the third paren before, around, and after. that is: ()<SOLUTION>,
 * (</SOLUTION>), <SOLUTION>(). will this work?
 *
 * hint 209: the problem with the solution suggested by the earlier hint is that it might have duplicate values. we
 * could eliminate this by using a hash table
 *
 * hint 243: alternatively, we could think about doing this by moving through the string and adding left and right
 * parens at each step. will this eliminate duplicate? how do we know if we can add a left or right paren?
 *
 * hint 265: adding a left or right paren at each step will eliminate duplicates. each substring will be unique at each
 * step. therefore, the total string will be unique.
 *
 * hint 295: we can ensure that this string is balid by counting the number of left and right parens. it is always valid
 * to add a left paren, up until the total number of pairs of parens. we can add a right paren as long as count(left
 * parens) <= count(right parens).
 *
 * Solution A: our first though here might be to apply a recursive approach where we build the solution for f(n) by
 * adding pairs of parentheses to f(n-1). that is certainly a good instinct.
 *
 * let's consider the solution for
 *    n = 2: (()), ()()
 *    n = 3: ((())), (()()), ()(()), ()(()), ()()()
 * we can do this by inserting a pair of parentheses inside every existing pair of parentheses, as well as one at
 * the beginning of the string. any other places that we could insert parentheses, such as at the end of the string,
 * would reduce to the earlier cases
 *  (()) -> (()())    inserted pair after 1st left paren
 *       -> ((()))    inserted pair after 2nd left paren
 *       -> ()(())    inserted pair at beginning of string (duplicate)
 *  ()() -> (())()    inserted pair after 1st left paren
 *       -> ()(())    inserted pair after 2nd left paren  (duplicate)
 *       -> ()()()    inserted pair at beginning of string
 * but wait - we have some duplicate pairs listed. the string ()(()) is listed twice. if we are going to apply this
 * approach, then we will need to check for duplicate values before adding a string to our list!!
 *
 * this works, but it's not very efficient. we waste a lot of time coming up with the duplicate strings!!
 */
public class QuestionA {

  public static String insertInside(String str, int leftIndex) {
    String left = str.substring(0, leftIndex + 1);
    String right = str.substring(leftIndex + 1, str.length());
    return left + "()" + right;
  }

  public static Set<String> generateParens(int remaining) {
    Set<String> set = new HashSet<String>();
    if (remaining == 0) {
      set.add("");
    } else {

      Set<String> prev = generateParens(remaining - 1);
      for (String str : prev) {
        for (int i = 0; i < str.length(); i++) {
          if (str.charAt(i) == '(') {
            String s = insertInside(str, i);
            /* Add s to set if it is not already in there.
             * Note: HashSet automatically checks for duplicates before adding, so an explicit check is not necessary.
             */
            set.add(s);
          }
        }
        set.add("()" + str);
      }
    }

    return set;
  }

  public static void main(String[] args) {
    Set<String> list = generateParens(4);
    for (String s : list) {
      System.out.println(s);
    }
    System.out.println(list.size());
  }

}
