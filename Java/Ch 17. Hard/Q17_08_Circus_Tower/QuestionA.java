package Q17_08_Circus_Tower;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Circus tower: a circus is designing a tower routine consisting of people standing at one another's shoulders. for
 * practical and aesthetic reasons, each person must be both shorter and lighter than the person below him/her. given
 * the heights and weights of each person in the circus, write a method to compute the largest possible number of people
 * in such a tower.
 *
 * Solution: when we cut out all the "fluff" to this problem, we can understand that the problem is really the
 * following: "we have a list of pairs of items, find the longest sequence such that both the first and second item are
 * in not decreasing order"
 *
 * one thing we might first try is sorting the items on an attribute. this is useful actually, but won't get us all the
 * way there.
 *
 * Solution 1: recursive: one approach is to essentially try all possibilities. after sorting by height, we iterate
 * through the array. at each element, we branch into tow choices: add this element to the subsequence(if it's valid) or
 * do not.
 */
public class QuestionA {

  public static ArrayList<HtWt> longestIncreasingSeq(ArrayList<HtWt> items) {
    System.out.println("original    items:" + items.toString());
    Collections.sort(items);
    System.out.println("after sort, items:" + items.toString());
    return bestSeqAtIndex(items, new ArrayList<HtWt>(), 0);
  }

  private static ArrayList<HtWt> bestSeqAtIndex(ArrayList<HtWt> array, ArrayList<HtWt> sequence, int index) {
    if (index >= array.size()) {
      return sequence;
    }

    HtWt value = array.get(index);

    ArrayList<HtWt> bestWith = null;
    if (canAppend(sequence, value)) {
      ArrayList<HtWt> sequenceWith = (ArrayList<HtWt>) sequence.clone();
      sequenceWith.add(value);
      bestWith = bestSeqAtIndex(array, sequenceWith, index + 1);
    }

    ArrayList<HtWt> bestWithout = bestSeqAtIndex(array, sequence, index + 1);

    return max(bestWith, bestWithout);
  }

  // Returns longer sequence
  private static ArrayList<HtWt> max(ArrayList<HtWt> seq1, ArrayList<HtWt> seq2) {
    if (seq1 == null) {
      return seq2;
    } else if (seq2 == null) {
      return seq1;
    }
    return seq1.size() > seq2.size() ? seq1 : seq2;
  }

  private static boolean canAppend(ArrayList<HtWt> solution, HtWt value) {
    if (solution == null) {
      return false;
    }
    if (solution.size() == 0) {
      return true;
    }
    HtWt last = solution.get(solution.size() - 1);
    return last.isBefore(value);
  }


  public static ArrayList<HtWt> initialize() {
    ArrayList<HtWt> items = new ArrayList<HtWt>();

    HtWt item = new HtWt(65, 60);
    items.add(item);

    item = new HtWt(70, 150);
    items.add(item);

    item = new HtWt(56, 90);
    items.add(item);

    item = new HtWt(75, 190);
    items.add(item);

    item = new HtWt(60, 95);
    items.add(item);

    item = new HtWt(68, 110);
    items.add(item);

    item = new HtWt(35, 65);
    items.add(item);

    item = new HtWt(40, 60);
    items.add(item);

    item = new HtWt(45, 63);
    items.add(item);

    return items;
  }

  public static void main(String[] args) {
    ArrayList<HtWt> items = initialize();
    ArrayList<HtWt> solution = longestIncreasingSeq(items);
    System.out.println("solution:" + solution.toString());
  }

}
