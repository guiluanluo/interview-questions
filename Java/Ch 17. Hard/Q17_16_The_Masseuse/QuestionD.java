package Q17_16_The_Masseuse;

/**
 * we only need to know the best values from i+1 and i+2. therefore, we can use two integers.
 * this give us the most optimal time and space possible: O(n) time and O(1) space
 */
public class QuestionD {

  public static int maxMinutes(int[] massages) {
    int oneAway = 0;
    int twoAway = 0;
    for (int i = massages.length - 1; i >= 0; i--) {
      int bestWith = massages[i] + twoAway;
      int bestWithout = oneAway;
      int current = Math.max(bestWith, bestWithout);
      twoAway = oneAway;
      oneAway = current;
    }
    return oneAway;
  }

  public static void main(String[] args) {
    int[] massages = {2 * 15, 1 * 15, 4 * 15, 5 * 15, 3 * 15, 1 * 15, 1 * 15, 3 * 15};
    System.out.println(maxMinutes(massages));
  }

}
