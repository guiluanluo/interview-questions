package Q16_11_Diving_Board;

import java.util.HashSet;

/**
 * Memoization solution: as in many recursive algorithms(especially those with exponential runtimes), we can optimize
 * this through memorization(a form of dynamic programming).
 *
 * observe that some of the recursive calls will be essentially equivalent. for example, picking plank 1 and then plank
 * 2 is e  equivalent to picking plank 2 and then plank 1.
 *
 * therefore, if we've seen this(total, plank count) pair before then we stop this recursive path. we can do this using
 * a HashSet with a key of(total, plank count).
 *
 * Note: many candidates will make a mistake here, rather than stopping only when they've seen(total, planl count),
 * they'll stop whenever they've seen just total before. this is incorrect. seeing two planks of length 1 is not the
 * same as one plank of length 2, because there are different numbers of planks remaining. in memoization problems, ber
 * bery carefule about what you choose for your key.
 */
public class QuestionB {

  public static int counter = 0;

  public static HashSet<Integer> allLengths(int k, int shorter, int longer) {
    HashSet<Integer> lengths = new HashSet<Integer>();
    HashSet<String> visited = new HashSet<String>();
    getAllLengths(k, 0, shorter, longer, lengths, visited);
    return lengths;
  }

  public static void getAllLengths(int k, int total, int shorter, int longer, HashSet<Integer> lengths,
      HashSet<String> visited) {
    counter++;
    if (k == 0) {
      lengths.add(total);
      return;
    }

    String key = k + " " + total;
    if (visited.contains(key)) {
      return;
    }

    getAllLengths(k - 1, total + shorter, shorter, longer, lengths, visited);
    getAllLengths(k - 1, total + longer, shorter, longer, lengths, visited);
    visited.add(key);
  }

  public static void main(String[] args) {
    HashSet<Integer> lengths = allLengths(5, 1, 3);
    System.out.println(lengths.toString());
    System.out.println(counter);
  }

}
