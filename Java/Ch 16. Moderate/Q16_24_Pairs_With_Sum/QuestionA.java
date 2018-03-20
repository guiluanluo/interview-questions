package Q16_24_Pairs_With_Sum;

import java.util.ArrayList;

/**
 * Pairs with sum: design an algorithm to find all pairs of integers within an array which sum to a specified value
 *
 * hint 547:start with a brute force solution. what is the runtime? what is the best conceivable runtime for this
 * problem?
 *
 * hint 596: can we make this faster with a hash table
 *
 * hint 643: what if the array are sorted?
 *
 * hint 672: if we sorted the array, we could do repeated binary searches for the complement of a number. what if,
 * instead, the array is given to use sorted? could we then solve the problem in O(N) time and O(1) space?
 */
public class QuestionA {

  public static ArrayList<Pair> printPairSums(int[] array, int sum) {
    ArrayList<Pair> result = new ArrayList<Pair>();
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if (array[i] + array[j] == sum) {
          result.add(new Pair(array[i], array[j]));
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] test = {9, 3, 6, 5, 5, 7, -1, 13, 14, -2, 12, 0};
    ArrayList<Pair> pairs = printPairSums(test, 12);
    for (Pair p : pairs) {
      System.out.println(p.toString());
    }
  }
}
