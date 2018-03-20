package Q17_09_Kth_Multiple;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Optimal algorithm: to generate a new element Ai, we are searching through a linked list where each element looks like
 * one of: 1) 3*previous element; 2) 5*previous element; 3) 7*previous element; where is there unnecessary work that we
 * might be able to optimize out?
 *
 * please read page 551: let's walk through this with an example to make it really clear.
 * 1) initialize: Q3=3   Q5=5 Q7=7
 * 2) remove min=3. insert 3*3 into Q3, 5*3 into Q5, 7*3 into Q7:
 *      Q3=3*3   Q5=5,5*3   Q7=7,7*3
 * 3) remove min=5. 3*5 is dup, since we already did 5*3. insert 5*5 into Q5, 7*5 into Q7:
 *      Q3=3*3   Q5=5*3,5*5   Q7=7,7*3,7*5
 * 4) remove min=7. 3*7 and 5*7 are dup, since we already did 7*3 and 7*5. insert 7*7 into Q7:
 *      Q3=3*3   Q5=5*3,5*5   Q7=7*3,7*5,7*7
 * 5) remove min=9. insert 3*9 into Q3, 5*9 into Q5, 7*9 into Q7:
 *      Q3=3*9   Q5=5*3,5*5,5*9  Q7=7*3,7*5,7*7,7*9
 * 6) remove min=15. 3*15 is a dup, since we already did 5*9. insert 5*15 into Q5, 7*15 into Q7
 *      Q3=3*9   Q5=5*5,5*9,5*15   Q7=7*3,7*5,7*7,7*9,7*15
 * 7) remove min=21. 3*21 and 5*21 are dup, since we already did 7*9 and 7*15. insert 7*21 into Q7
 *      Q3=3*9   Q5=5*5,5*9,5*15   Q7=7*5,7*7,7*9,7*15,7*21
 *
 * our pseudocode for this problem is as follows:
 * 1) initialize array and queues Q3,Q5,Q7
 * 2) insert 1 into array
 * 3) insert 1*3, 1*5, 1*7 into Q3,Q5,Q7
 * 4) let x be the minimum element in Q3,Q5,Q7. apply x to magic:
 * 5) if x was found in:
 *      Q3 -> append x*3, x*5, x*7 to Q3,Q5,Q7. remove x from Q3
 *      Q5 -> append x*5, x*7 to Q5,Q7. remove x from Q5
 *      Q7 -> append x*7 to Q7. remove x from Q7
 * 6) repeat steps 4-6 until we've found k elements.
 *
 * when you get this question, do you best to solve it -- even through it's really difficult. you can start with a brute
 * force approach(challenging, but not quite as tricky), and then you can start trying to optimize it. or try to find a
 * pattern in the numbers.
 *
 * chances are that your interviewer will help you along when you get stuck. whatever you do, don't give up! think out
 * loud, wonder out lond, and explain your though process. your interviewer will probably jump in to guide you.
 *
 * remember, perfection on this problem is not expected. your performance is evaluated in comparison to other candidates.
 * everyone struggles on tricky problem.
 */
public class QuestionC {

  public static int getKthMagicNumber(int k) {
    if (k < 0) {
      return 0;
    }

    int val = 0;
    Queue<Integer> queue3 = new LinkedList<Integer>();
    Queue<Integer> queue5 = new LinkedList<Integer>();
    Queue<Integer> queue7 = new LinkedList<Integer>();
    queue3.add(1);

    for (int i = 0; i <= k; i++) { // Include 0th iteration through kth iteration
      int v3 = queue3.size() > 0 ? queue3.peek() : Integer.MAX_VALUE;
      int v5 = queue5.size() > 0 ? queue5.peek() : Integer.MAX_VALUE;
      int v7 = queue7.size() > 0 ? queue7.peek() : Integer.MAX_VALUE;

      val = Math.min(v3, Math.min(v5, v7));
      if (val == v3) { //enqueue into queue 3,5,7
        queue3.remove();
        queue3.add(3 * val);
        queue5.add(5 * val);
        queue7.add(7 * val);
      } else if (val == v5) { //enqueue into queue  5,7
        queue5.remove();
        queue5.add(5 * val);
        queue7.add(7 * val);
      } else if (val == v7) { //enqueue into Q7
        queue7.remove();
        queue7.add(7 * val);
      }
    }
    return val;
  }

  public static void printQueue(Queue<Integer> q, int x) {
    System.out.print(x + ": ");
    for (Integer a : q) {
      System.out.print(a / x + ", ");
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    for (int i = 0; i < 14; i++) {
      System.out.println(i + " : " + getKthMagicNumber(i));
    }
  }
}
