package Q17_03_Random_Set;

import CtCILibrary.AssortedMethods;

/**
 * Random Set: write a method to randomly generate a set of m integers from an array of size. each element must have
 * equal probability of being chosen.
 *
 * hint 493: if you haven't already, solve 17.2 on page 186
 *
 * hint 595: try this recursive. suppose you had an algorithm to get a subset of size m from n-1 element. could you
 * develop an algorithm to get a subset of size m from n element?
 *
 * Solution: like the prior problem (17.2) which was similar, we can look at this problem recursively using Base Case
 * and Build approach.
 *
 * suppose we have an algorithm that can pull a random set of m elements form an array of size n-1. how can we use this
 * algorithm to pull a random set of m elements from an array of size n?
 *
 * we can fist pull a random set of size m from the fist n-1 elements. then, we just need to decide if array[n] should
 * be inserted into our subset (which would require pulling out a random element from it). an easy way to do thi is to
 * pick a random number k from 0 through n. if k<m, then insert array[n] into subset[k]. this will both "fairly"(ie.
 * with proportional probability) insert array[n] into the subset and "fairly" remove a random element from the subset.
 */
public class Question {

  /* Random number between lower and higher, inclusive */
  public static int rand(int lower, int higher) {
    return lower + (int) (Math.random() * (higher - lower + 1));
  }

  /**
   * solution 1: pick M elements from original array, using only elements 0 through i (inclusive).
   */
  public static int[] pickMRecursively(int[] original, int m, int i) {
    if (i + 1 < m) { // Not enough elements
      return null;
    }

    if (i + 1 == m) { // Base case -- copy first m elements into array
      int[] set = new int[m];
      for (int k = 0; k < m; k++) {
        set[k] = original[k];
      }
      return set;

    } else {
      int[] set = pickMRecursively(original, m, i - 1);
      int k = rand(0, i);
      if (k < m) {
        set[k] = original[i];
      }
      return set;
    }
  }

  /**
   * solution 2: pick M elements from original array.
   */
  public static int[] pickMIteratively(int[] original, int m) {
    int[] subset = new int[m];

		/* Fill in subset array with first part of original array */
    for (int i = 0; i < m; i++) {
      subset[i] = original[i];
    }

		/* Go through rest of original array. */
    for (int i = m; i < original.length; i++) {
      int k = rand(0, i);
      if (k < m) {
        subset[k] = original[i];
      }
    }

    return subset;
  }

  public static void main(String[] args) {
    System.out.println("call pickMIteratively()...");
    int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println(AssortedMethods.arrayToString(cards));
    int[] set = pickMIteratively(cards, 4);
    System.out.println(AssortedMethods.arrayToString(set));

    System.out.println("call pickMRecursively()...");
    int[] cards1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println(AssortedMethods.arrayToString(cards1));
    int[] set1 = pickMRecursively(cards1, 4, cards1.length - 1);
    System.out.println(AssortedMethods.arrayToString(set1));

  }

}
