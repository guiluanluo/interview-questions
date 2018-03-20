package Q17_05_Letters_and_Numbers;

import java.util.HashMap;

/*
 * Option Solution is Good: what we're trying to do is find a subarray where the count of letters equals the count of
 * number. what if we just started from the beginning, counting the number of letters and numbers?
 *      a a a a 1 1 a 1 1 a a 1 a a 1
 * #a   1 2 3 4 4 4 5 5 5 6 7 7 8 9 9
 * #1   0 0 0 0 1 2 2 3 4 4 4 5 5 5 5
 *
 * certainly, whenever the number of letters equals the numbers of numbers, we can say that from index 0 to that index
 * is an "equal" subarray. that will only tell us equal subarrays that start at index 0. how can we identify all equal
 * subarrays? let's picute this: suppose we inserted an equal subarray(like a11a1a) after an array link a1aaa1.
 * how would that impact the counts?
 *      a 1 a a a 1   a 1 1 a 1 a
 * #a   1 1 2 3 4 4   5 5 5 6 6 7
 * #1   0 1 1 1 1 2   2 3 4 4 5 5
 * study the numbers before the subarray(4,2) and the end(7,5). the values are not the same, but the difference are the same:
 * 4-2=7-5. this makes sense. since they've added the same number of #a, #1, they should maintain the sam difference.
 *
 * == observe that when the difference is the same, the subarray start one after the initial matching index and continues
 * through the final matching index.
 *
 * let's update the earlier array with the difference:
 *      a a a a 1 1 a 1 1 a a 1 a a 1
 * #a   1 2 3 4 4 4 5 5 5 6 7 7 8 9 9
 * #1   0 0 0 0 1 2 2 3 4 4 4 5 5 5 5
 * -    1 2 3 4 3 2 3 2 1 2 3 2 3 4 4
 *
 * wherever we return the same difference, then we know we have found an equal subarray. to find the biggest subarray,
 * we just have to find the two indices farthest apart with the same value.
 *
 * to do so, we use a hash table to store the first time we see a particular difference. then, each time we see the same
 * difference, we see if this subarray(from first occurrence of this index to current index) is bigger then the current max.
 * if so, we update the max.
 */
public class QuestionB {

  public static char[] findLongestSubarray(char[] array) {
    /* Compute deltas between count of numbers and count of letters. */
    int[] deltas = computeDeltaArray(array);

		/* Find pair in deltas with matching values and largest span. */
    int[] match = findLongestMatch(deltas);

		/* Return the subarray. Note that it starts one *after* the
     * initial occurence of this delta. */
    return extract(array, match[0] + 1, match[1]);
  }

  /**
   * Compute the difference between the number of letters and
   * numbers between the beginning of the array and each index.
   */
  public static int[] computeDeltaArray(char[] array) {
    int[] deltas = new int[array.length];
    int delta = 0;
    for (int i = 0; i < array.length; i++) {
      if (Character.isLetter(array[i])) {
        delta++;
      } else if (Character.isDigit(array[i])) {
        delta--;
      }
      deltas[i] = delta;
    }
    return deltas;
  }

  /**
   * Find the matching pair of values in the deltas array with the
   * largest difference in indices.
   */
  public static int[] findLongestMatch(int[] deltas) {

    //keyed by the deltas difference, value is the index
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    map.put(0, -1);
    int[] max = new int[2];

    for (int i = 0; i < deltas.length; i++) {
      if (!map.containsKey(deltas[i])) {
        map.put(deltas[i], i);
      } else {
        int matchIndex = map.get(deltas[i]);
        int distance = i - matchIndex;
        int longest = max[1] - max[0];
        if (distance > longest) {
          max[1] = i;
          max[0] = matchIndex;
        }
      }
    }
    return max;
  }

  public static char[] extract(char[] array, int start, int end) {
    if (start > end) {
      return null;
    }
    char[] subarray = new char[end - start + 1];
    for (int i = start; i <= end; i++) {
      subarray[i - start] = array[i];
    }
    return subarray;
  }


  public static boolean isEqual(char[] array, int start, int end) {
    int counter = 0;
    for (int i = start; i < end; i++) {
      if (Character.isLetter(array[i])) {
        counter++;
      } else if (Character.isDigit(array[i])) {
        counter--;
      }
    }
    return counter == 0;
  }

  public static void main(String[] args) {
    char b = '1';
    char a = 'a';
    char[] array = {a, b, a, b, a, b, b, b, b, b, a, a, a, a, a, b, a, b, a, b, b, a, a, a, a, a, a, a};
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
    char[] max = findLongestSubarray(array);
    if (max == null) {
      System.out.println("No equal subarray");
    } else {
      System.out.println(max.length);
      for (int i = 0; i < max.length; i++) {
        System.out.print(max[i] + " ");
      }

      System.out.println("\nIs Valid? " + isEqual(max, 0, max.length));
    }
  }

}
