package Q16_17_Contiguous_Sequence;

/**
 * Contiguous sequence: you are given an array of integers (both positive and negative). find the contiguous sequence
 * with the largest sum. return the sum.
 *
 * example: input: 2,-8,3,-2,4,-10    output: 5 (i.e., {3,-2,4})
 *
 * hint 530: picture the array as alternating sequences of positive and negative numbers. observe that we would never
 * include just part of a positive sequence or part of a negative sequence.
 *
 * hint 566: start from the beginning of the array. as that sub-sequence gets larger, it stays as the best sub-sequence.
 * once it becomes negative, though, it's useless.
 *
 * hint 593: if we tracked the running sum, we should reset it as soon as the sub-sequence becomes negative. we would
 * never add a negative sequence to the beginning or end of another sub-sequence.
 *
 * hint 613: you can solve this in O(N) time and O(1) space
 */
public class Question {

  public static int getMaxSum(int[] a) {

    int maxSum = 0;
    int runningSum = 0;

    for (int i = 0; i < a.length; i++) {
      runningSum += a[i];

      if (maxSum < runningSum) {
        maxSum = runningSum;
      } else if (runningSum < 0) {
        runningSum = 0;
      }
    }
    return maxSum;
  }

  public static void main(String[] args) {
    int[] a = {2, -8, 3, -2, 4, -10};
    System.out.println(getMaxSum(a));

    int[] b = {2, -1, 3, -2, 4, -10};
    System.out.println(getMaxSum(b));
  }
}
