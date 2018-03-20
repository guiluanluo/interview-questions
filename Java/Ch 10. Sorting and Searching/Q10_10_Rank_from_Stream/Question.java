package Q10_10_Rank_from_Stream;

import CtCILibrary.AssortedMethods;

/*
 * Rank from stream: image you are reading in a stream of integers. periodically, you wish to be able to look up the
 * rank of a number x(the number of values less than or equal to x). implement the data structures and algorithms to
 * support these operations. that is , implement the method track(int x), which is call when each number is generated,
 * and the method getRankOfNumber(int x), which returns the number of values less than or equal to x( not including x
 * itself)
 *
 * Example:  stream(in order of appearance): 5,1,4,4,5,9,7,13,3
 *  getRankOfNumber(1)=0;
 *  getRankOfNumber(3)=1;
 *  getRankOfNumber(4)=3
 *
 * hint 301: the problem with using an array is that it will be slow to insert a number. what other data structures
 * could we use?
 * hint 376: would it work well to use a binary search tree?
 * hint 392: consider a binary search tree where each node stores some additional data.
 */

/**
 * Solution: a relatively easy way to implement this would be to have an array that holds all elements in sorted order.
 * when a new element comes in, we would need ot shift the other elements to make room. implementing getRankOdNumber()
 * would be quite efficient, through. we would simple perform a binary search for n, and return the index.
 *
 * however, this is very inefficient for inserting elements(that is, the track(int x) function). we need a data
 * structure which is good at keeping relative ordering, as well as updating when we insert new elements, a binary
 * search tree can do just that.
 *
 * instead of inserting elements into an array, we insert elements into a binary search tree. the method track(int x)
 * will run in O(log n) time, where n is the size of tree(provided, of course, that the tree is balanced).
 *
 * to find the rank of a number, we could do an in-order traversal, keeping a counter as we traverse. the goal is that,
 * by the time we find x, counter will equal the number of elements less than x. as long as we are moving left during
 * searching for x, the counter won't change. why? because all the values we are skipping on right side are greater than
 * x. after all, the very smallest element(with rank of 1) is the leftmost node.
 *
 * when we move to the right though, we skip over a bunch of elements on the left. all of these elements are less than
 * x, so we'll need to increment counter by the number of elements in the left subtree.
 *
 * rather than counting the size of the left subtree(which would be inefficient), we can track this information as we
 * add new elements to the tree.
 */
public class Question {

  private static RankNode root = null;

  public static void track(int number) {
    if (root == null) {
      root = new RankNode(number);
    } else {
      root.insert(number);
    }
  }

  public static int getRankOfNumber(int number) {
    return root.getRank(number);
  }

  public static void main(String[] args) {
    int size = 100;
    int[] list = AssortedMethods.randomArray(size, -100, 100);
    for (int i = 0; i < list.length; i++) {
      track(list[i]);
    }

    int[] tracker = new int[size];
    for (int i = 0; i < list.length; i++) {
      int v = list[i];
      int rank1 = root.getRank(list[i]);
      tracker[rank1] = v;
    }

    for (int i = 0; i < tracker.length - 1; i++) {
      if (tracker[i] != 0 && tracker[i + 1] != 0) {
        if (tracker[i] > tracker[i + 1]) {
          System.out.println("ERROR at " + i);
        }
      }
    }

    System.out.println("Array: " + AssortedMethods.arrayToString(list));
    System.out.println("Ranks: " + AssortedMethods.arrayToString(tracker));
  }

}
