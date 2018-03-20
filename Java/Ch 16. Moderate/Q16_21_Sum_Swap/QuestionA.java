package Q16_21_Sum_Swap;

/**
 * Sum swap: given two arrays of integers, find a pair of values (one value from each array) that you can swap to give
 * the two arrays the same sum.
 *
 * example: input {4,1,2,1,1,2} and {3,6,3,3}     output: {1,3}
 *
 * hint 544: do some math here or play around with some examples. what does this pair need to look like? what can ou say
 * about their values?
 *
 * hint 556: when you move a value a from array A to array B, then A's sum decreases by a and B's sum increases by a.
 * what happens when you swap two values? what would be need to sap two values and get the same sum?
 *
 * hint 563: if you swap two values, a and b, then the sum of A becomes sumA-a+b and the sum of B becomes sumB-b+a.
 * these sums need to be equal.
 *
 * hint 570: you are looking for values a and b where sumA-a+b=sumB-b+a. do the math to work out what this means for a
 * and b's value.
 *
 * hint 582: if we do the math, we are looking for a pair of values such that a-b =(sumA-sumB)/2. the problem then
 * reduces to looking for a pair of values with a particular difference.
 *
 * hint 591: a brute force solution is to just lok through all pairs of values to find one with the right difference.
 * this will probably look like an outer loop through A with an inner loop through B. for each value, compute the
 * difference and compare it to what we are looking for. can we be more specific here, though? given a value in A and a
 * target differnce, do we know the exact value of the element within B we are looking for?
 *
 * hint 601: what the brute force really does is look for a value with B wich equals a-target. how can you more quickly
 * find this element? what approaches help us quickly find out if an element exists within an array?
 *
 * hint 605: we can use a hash table here. we can also try sorting. both help us locate elements more quickly
 *
 * hint 634: what if the sum of A is 11 and the sum of B is 8? can there be a pair with the right difference? check that
 * your solution handles this situation appropriately.
 */
public class QuestionA {

  public static int sum(int[] array) {
    int s = 0;
    for (int a : array) {
      s += a;
    }
    return s;
  }

  public static int[] findSwapValues(int[] array1, int[] array2) {
    int sum1 = sum(array1);
    int sum2 = sum(array2);

    for (int one : array1) {
      for (int two : array2) {
        int newSum1 = sum1 - one + two;
        int newSum2 = sum2 - two + one;
        if (newSum1 == newSum2) {
          int[] values = {one, two};
          return values;
        }
      }
    }

    return null;
  }

  public static void main(String[] args) {
    int[] array1 = {1, 1, 1, 2, 2, 4};
    int[] array2 = {3, 3, 3, 6};
    int[] swaps = findSwapValues(array1, array2);
    if (swaps == null) {
      System.out.println("null");
    } else {
      System.out.println(swaps[0] + " " + swaps[1]);
    }
  }

}
