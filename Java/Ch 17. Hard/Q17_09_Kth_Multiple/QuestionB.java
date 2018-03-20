package Q17_09_Kth_Multiple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * we know Ak can be expressed as (3,5 or 7) *(some values in {A1,....,Ak-1)}. we also know Ak is by definition, the
 * next number in the list. therefore, Ak will be the smallest "new" number (a number that it's already in
 * {A1,....,Ak-1)}) that can be formed by multiplying each value in the list by 3,5 or 7.
 *
 * how would we find Ak? well, we could actually multiple each number in the list by 3,5 and 7 and find the smallest
 * element that has not yet been added to our list. this solution is O(k*k). not bad, but we could do better!
 *
 * rather that Ak trying to "pull" from a previous element in the list(by multiplying all of them by 3,5,7), we can
 * think about each previous value in the list and :pushing" out the three subsequence values in the list. that is, each
 * number Ai, will eventually be used later in the list in the following forms: 3*Ai, 5*Ai, 7*Ai. we can use this
 * thought to plan in advance. each time we add a number Ai to the list, we hold on to the values 3Ai,5Ai and 7Ai in
 * some sort of temporary list. to generate Ai+1, we search through this temporary list to find the smallest value.
 */

public class QuestionB {

  public static int getKthMagicNumber(int k) {
    if (k < 0) {
      return 0;
    }

    int val = 1;
    Queue<Integer> q = new LinkedList<Integer>();
    addProducts(q, 1);

    for (int i = 0; i < k; i++) { // Start at 1 since we've already done one iteration
      val = removeMin(q);
      addProducts(q, val);
    }
    return val;
  }


  public static int removeMin(Queue<Integer> q) {
    int min = q.peek();

    for (Integer v : q) {
      if (min > v) {
        min = v;
      }
    }

    while (q.contains(min)) {
      q.remove(min);
    }

    return min;
  }

  public static void addProducts(Queue<Integer> q, int v) {
    q.add(v * 3);
    q.add(v * 5);
    q.add(v * 7);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 14; i++) {
      System.out.println("getKthMagicNumber(" + i + "): " + getKthMagicNumber(i));
    }
  }
}
