package Q8_09_Parens;

import java.util.ArrayList;

/*
 * we could avoid this duplicate string issue by building the string from scratch. under this approach, we add left and
 * right parens, as long as our expression stays valid.
 *
 * on each recursive call, we have the index for particular character in the string. we need select either a left or
 * right paren. when can we use a left paren, and when can we use a right paren?
 *
 * 1) left paren: as long as we haven't used up all the left parentheses, we can always insert a left paren.
 * 2) right paren: we can insert a right paren as long as it won't lead to a syntax error. when will we get a syntax
 * error? we will get a syntax error if there are more right parentheses than left.
 *
 * so we simply keep track of the number of left and right parentheses allowed. if there are left parens remaining,
 * we'll insert a left paren and recurse. if there are more right parens remaining than left (i.e., if there are more
 * left parens in use than right parens), then we'll insert a right paren and recurse.
 */
public class QuestionB {

  public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {

    if (leftRem < 0 || rightRem < leftRem) {
      return; // invalid state
    }

    if (leftRem == 0 && rightRem == 0) { /* all out of left and right parentheses */
      list.add(String.copyValueOf(str));

    } else {
      str[index] = '('; // Add left and recurse
      addParen(list, leftRem - 1, rightRem, str, index + 1);

      str[index] = ')'; // Add right and recurse
      addParen(list, leftRem, rightRem - 1, str, index + 1);
    }
  }

  public static ArrayList<String> generateParens(int count) {
    char[] str = new char[count * 2];
    ArrayList<String> list = new ArrayList<String>();
    addParen(list, count, count, str, 0);
    return list;
  }

  public static void main(String[] args) {
    ArrayList<String> list = generateParens(6);
    for (String s : list) {
      System.out.println(s);
    }
    System.out.println(list.size());
  }

}
