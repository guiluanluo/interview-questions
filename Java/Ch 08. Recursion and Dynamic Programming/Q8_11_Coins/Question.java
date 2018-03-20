package Q8_11_Coins;

/*
 * Coins: given an infinite number of quarters(25 cents), dimes(10 cents), nickels(5 cents), and pennies(1 cent), write
 * code to calculate the number of ways of representing n cents.
 *
 * hint 300: try breaking it down into subproblems. if you wer making change, what is the first choice you would make?
 *
 * hint 324: if you were making change, the first choice you might make is how many quarters you need to use.
 *
 * hint 343: once you've decided to use two quarters to make change for 98 cents, you now need ot figure out how many
 * ways to make change for 48 cents using nickels, dimes, and pennies.
 *
 * hint 394: try using memoization.
 *
 * Solution: let's say n=100, we want to compute the number of ways making change for 100 cents. what is the
 * relationship between this problem and its sub-problem?
 *
 * we know that making change for 100 cents will involve either 0,1,2,3,or 4 quarters. so
 *  makeChange(100) = makeChange(100 using 0 quarter) +
 *                    makeChange(100 using 1 quarter) +
 *                    makeChange(100 using 2 quarter) +
 *                    makeChange(100 using 3 quarter) +
 *                    makeChange(100 using 4 quarter)
 *  makeChange(100) = makeChange(100 using 0 quarter) +
 *                    makeChange(75 using 1 quarter) +
 *                    makeChange(50 using 2 quarter) +
 *                    makeChange(25 using 3 quarter) +
 *                    1
 *
 * note that the final statement from above, makeChange(100 using 4 quarters), equal 1. we call this "fully reduced"
 * now what? we have used up all our quarters, so now we can start apply our next  bigest denomination: dimes
 *  makeChange(100 using 0 quarter) = makeChange(100 using 0 quarters, 0 dimes) +
 *                                    makeChange(100 using 0 quarters, 1 dimes) +
 *                                    makeChange(100 using 0 quarters, 2 dimes) +
 *                                    .....
 *                                    makeChange(100 using 0 quarters, 10 dimes)
 *
 *  makeChange(75 using 0 quarter) =  makeChange(75 using 0 quarters, 0 dimes) +
 *                                    makeChange(75 using 0 quarters, 1 dimes) +
 *                                    makeChange(75 using 0 quarters, 2 dimes) +
 *                                    .....
 *                                    makeChange(75 using 0 quarters, 7 dimes)
 *
 *  makeChange(50 using 0 quarter) =  makeChange(50 using 0 quarters, 0 dimes) +
 *                                    makeChange(50 using 0 quarters, 1 dimes) +
 *                                    makeChange(50 using 0 quarters, 2 dimes) +
 *                                    .....
 *                                    makeChange(50 using 0 quarters, 5 dimes)
 *
 *  makeChange(25 using 0 quarter) =  makeChange(25 using 0 quarters, 0 dimes) +
 *                                    makeChange(25 using 0 quarters, 1 dimes) +
 *                                    makeChange(25 using 0 quarters, 2 dimes) +
 *
 * each one of these, in turn, expands out once we start applying nickels. we end up with a tree-like recursive structure
 * where each call expands out to four or more call.
 *
 * the base case of our recursion is fully reduced statement. for example, makeChange(50 using 0 quarter, 5 dimes)
 * is fully reduced to 1, since 5 dimes equal to 50 cents.
 *
 */
public class Question {

  public static int makeChange(int amount, int[] denoms, int index) {
    if (index >= denoms.length - 1) {
      return 1; // one denom remaining -> one way to do it
    }

    int denomAmount = denoms[index];
    int ways = 0;
    for (int i = 0; i * denomAmount <= amount; i++) {
      int amountRemaining = amount - i * denomAmount;
      ways += makeChange(amountRemaining, denoms, index + 1); // go to next denom
    }

    System.out.println("makeChange(amount:" + amount + ",denoms:" + denoms + ",index:" + index + ") return " + ways);
    return ways;
  }

  public static int makeChange(int amount, int[] denoms) {
    return makeChange(amount, denoms, 0);
  }

  public static void main(String[] args) {
    int[] denoms = {25, 10, 5, 1};
    int ways = makeChange(50, denoms);
    System.out.println(ways);
  }

}