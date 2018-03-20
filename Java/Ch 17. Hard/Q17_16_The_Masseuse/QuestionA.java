package Q17_16_The_Masseuse;

/**
 * TODO: what is this question?? -LL
 *
 * The masseuse: a popular masseuse receives a sequence of back-to-back appointment requests and is debating which ones
 * to accept. she needs a 15-minute break between appoints and therefore she cannot accept any adjacent requests. given
 * a sequence of back-to-back appointment requests (all multiple of 15 mins, none overlap, and none can be moved), find
 * the optimal (highest total booked minutes) set the masseuse can honor. return the number of minutes.
 *
 * example: input {30,15,60,75,45,15,15,45}   output: 180 mins {30,60,45,45}
 *
 * Solution D is the best: we only need to know the best values from i+1 and i+2. therefore, we can use two integers.
 * this give us the most optimal time and space possible: O(n) time and O(1) space
 */
public class QuestionA {

  public static int maxMinutes(int[] massages) {
    return maxMinutes(massages, 0);
  }

  public static int maxMinutes(int[] massages, int index) {
    if (index >= massages.length) { // Out of bounds
      return 0;
    }

		/* Best with this reservation. */
    int bestWith = massages[index] + maxMinutes(massages, index + 2);

		/* Best without this reservation. */
    int bestWithout = maxMinutes(massages, index + 1);

    System.out.println("**bestWith:" + bestWith + ", bestWithout:" + bestWithout);
    /* Return best of this subarray, starting from index. */
    return Math.max(bestWith, bestWithout);
  }

  public static void main(String[] args) {
    int[] massages = {30, 15, 60, 75, 45, 15, 15, 45};
    System.out.println(maxMinutes(massages));
  }

}
