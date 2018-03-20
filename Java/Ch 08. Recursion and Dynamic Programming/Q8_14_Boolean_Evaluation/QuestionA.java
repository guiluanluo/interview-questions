package Q8_14_Boolean_Evaluation;

/**
 * Boolean evaluation: given a boolean expression consisting of the symbols 0(false), 1(true), &(AND), |(OR), and
 * ^(XOR), and a desired boolean result value result, implement a funciton to count the number of ways of parenthesizing
 * the expression such that it evaluates or result. the expression should be fully parenthesized (e.g., (0)^(1)) but not
 * extraneously(e.g., (((0))^(1))).
 *
 * Example: countEval("1^0|0|1", false) -> 2    countEval("0&0&0&1^1|0", true) -> 10
 *
 * hint 148: can we just try all possibilities? what would this look like?
 *
 * hint 168: we can think about each possibility as each place where we can put parentheses. this means around each
 * operator, such that the expresssion is split at the operator. what is the base case?
 *
 * hint 197: the base case is when we have a single value, 1 or 0
 *
 * hint 305: if your code looks really lengthy, with a lots of its' ( for each possible operator, "target" boolean
 * result, and left/right side), think about the relationship between the different parts. try to simplify your code. it
 * should not need a ton of complicated if-statements. for example, consider expressions of the form >LEFT>OR<RIGHT>
 * versus <LEFT>AND<RIGHT>. both may need to know the number of ways that the <LEFT> evaluates to true. see what code
 * you can resue.
 *
 * hint 327: look at your recursion. do you have repeated calls anywhere? can you memoize it?
 */
public class QuestionA {

  public static int count = 0;

  public static boolean stringToBool(String c) {
    return c.equals("1") ? true : false;
  }

  public static int countEval(String s, boolean result) {
    count++;
    if (s.length() == 0) {
      return 0;
    }
    if (s.length() == 1) {
      return stringToBool(s) == result ? 1 : 0;
    }

    int ways = 0;

    for (int i = 1; i < s.length(); i += 2) {
      char c = s.charAt(i);
      String left = s.substring(0, i);
      String right = s.substring(i + 1, s.length());
      int leftTrue = countEval(left, true);
      int leftFalse = countEval(left, false);
      int rightTrue = countEval(right, true);
      int rightFalse = countEval(right, false);
      int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

      int totalTrue = 0;
      if (c == '^') { // required: one true and one false
        totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
      } else if (c == '&') { // required: both true
        totalTrue = leftTrue * rightTrue;
      } else if (c == '|') { // required: anything but both false
        totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
      }

      int subWays = result ? totalTrue : total - totalTrue;
      ways += subWays;
    }

    return ways;
  }

  public static void main(String[] args) {
    String expression = "0^0|1&1^1|0|1";
    boolean result = true;

    System.out.println(countEval(expression, result));
    System.out.println(count);
  }

}
