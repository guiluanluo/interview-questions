package Q8_01_Triple_Step;

/*
 * Triple step: a child is running up a staircase with n steps and can hop either 1, step 2, or 3 steps at a time.
 * implement a method to count how many possible ways tht child can run up the stairs.
 *
 * hint 152: approach this from the top-down. what is the very last hop the child made?
 * hint 178: if we knew the number of paths to each of the steps before step 100, could we compute the number of steps
 * to 100?
 *
 * hint 217:we can compute the number of steps to 100 by the number of steps to 99,98, and 97. this corresponds to the
 * child hopping 1,2,or 3 steps at the end. do we add those or multiple them? that is: is it f(100) = f(99)+f(98)+f(97)
 * or f(100) = f(99)*f(98)*f(97)?
 *
 * hint 237: we multiply the values when it's "we do this then this.", we add them when it's "we do this or this."
 * hint 262: what is the runtime of this method? think carefully. can you optimize it?
 * hint 359: try memoization as a way to optimize an inefficient recursive program.
 */

public class QuestionA {

  public static int countWays(int n) {
    if (n < 0) {
      return 0;
    } else if (n == 0) {
      return 1;
    } else {
      return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
    }
  }

  public static void main(String[] args) {
    int n = 20;
    int ways = countWays(n);
    System.out.println("countWays(" + n + "): " + ways);
  }

}
