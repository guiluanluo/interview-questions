package Q17_09_Kth_Multiple;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Kth multiple: design an algorithm to find the kth number such that the only prime factors are 3,5,and 7. note that
 * 3,5,and 7 do not have to be factors, but it should not have any other prime factors.
 *
 * for example the first several multiples would be (in order) 1,3,5,7,9,15,21.
 *
 * Solution: let's understand what the problem is asking for. it is asking for the kth smallest number that is in the
 * form (3 powerOf a)*(5 powerOf b)*(7 powerOf c). let's start with a brute force way of finding this.
 *
 * Brute Force: we know that the biggest this kth number could be (3 powerOf k)*(5 powerOf k)*(7 powerOf k). so the
 * 'stupid' way of doing this is to compute (3 powerOf a)*(5 powerOf b)*(7 powerOf c) for all values of a,b,c between 0
 * and k. we can throw them all into a list, sort the list, and then pick the kth smallest value.
 *
 * the runtime of allPossibleKFactors(k) is O(k*k*k), then we sorted the (k*k*k) result in O(k*k*k long(k*k*k)) time
 * ==>O(k*k*k long(k)). there are a number of optimizations you could make to this(and better ways of handling the
 * integer over-flow), but honestly this algorithm is fairly slow.
 */
public class QuestionA {

  public static int getKthMagicNumber(int k) {
    ArrayList<Integer> possibilities = allPossibleKFactors(k);
    Collections.sort(possibilities);
    return possibilities.get(k);
  }

  public static ArrayList<Integer> allPossibleKFactors(int k) {
    ArrayList<Integer> values = new ArrayList<Integer>();
    for (int a = 0; a <= k; a++) { // 3
      int powA = (int) Math.pow(3, a);

      for (int b = 0; b <= k; b++) { // 5
        int powB = (int) Math.pow(5, b);

        for (int c = 0; c <= k; c++) { // 7
          int powC = (int) Math.pow(7, c);

          int value = powA * powB * powC;
          if (value < 0 || powA == Integer.MAX_VALUE || powB == Integer.MAX_VALUE || powC == Integer.MAX_VALUE) {
            value = Integer.MAX_VALUE;
          }
          values.add(value);
        }
      }
    }
    return values;
  }


  public static void main(String[] args) {
    for (int i = 0; i < 50; i++) {
      System.out.println(i + " : " + getKthMagicNumber(i));
    }
  }
}
