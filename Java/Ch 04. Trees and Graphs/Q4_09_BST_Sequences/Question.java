package Q4_09_BST_Sequences;

import java.util.ArrayList;
import java.util.LinkedList;

import CtCILibrary.TreeNode;

/*
 * TODO: will review it later, Jan 20, 2018 , LL
 * BST sequence: a binary search tree was created by traversing through an array from left to right and inserting each
 * element. given a binary search tree with distinct elements, print all possible arrays that could have led to this
 * tree.
 *
 * Example:
 * input:
 *     2
 *    / \
 *   1   3
 * output: {2,1,3}, {2,3,1}
 *
 * hint 39: what is the very first value that must be in each array?
 *
 * hint 48: the root is the very first value that must be in every array. what can you say about the order of the values
 * in the left subtree as compared toe the vlaues in the rights subtree? do the left subtree value need to be inserted
 * before the right subtree?
 *
 * hint 66: the relationship between the left subtree values and the right subtree value is, essentially, anything. the
 * left subtree values could be inserted before the right subtree, or the reverse (right values before left),
 * or any other ordering.
 *
 * hint 82: break this down into subproblems. user recursive. if you had all possible sequences for the left subtree
 * and the right subtree, how could you create all possible sequences for the entire tree?
 *
 * Soluton: it's very usdful to kick off this question with a good example.
 *
 */
public class Question {

  public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
      ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
    /* One list is empty. Add the remainder to [a cloned] prefix and
     * store result. */
    if (first.size() == 0 || second.size() == 0) {
      LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
      result.addAll(first);
      result.addAll(second);
      results.add(result);
      return;
    }

		/* Recurse with head of first added to the prefix. Removing the
     * head will damage first, so we’ll need to put it back where we
		 * found it afterwards. */
    int headFirst = first.removeFirst();
    prefix.addLast(headFirst);
    weaveLists(first, second, results, prefix);
    prefix.removeLast();
    first.addFirst(headFirst);

		/* Do the same thing with second, damaging and then restoring
     * the list.*/
    int headSecond = second.removeFirst();
    prefix.addLast(headSecond);
    weaveLists(first, second, results, prefix);
    prefix.removeLast();
    second.addFirst(headSecond);
  }

  public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
    ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

    if (node == null) {
      result.add(new LinkedList<Integer>());
      return result;
    }

    LinkedList<Integer> prefix = new LinkedList<Integer>();
    prefix.add(node.data);

		/* Recurse on left and right subtrees. */
    ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
    ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

		/* Weave together each list from the left and right sides. */
    for (LinkedList<Integer> left : leftSeq) {
      for (LinkedList<Integer> right : rightSeq) {
        ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
        weaveLists(left, right, weaved, prefix);
        result.addAll(weaved);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    TreeNode node = new TreeNode(100);
    int[] array = {100, 50, 20, 75, 150, 120, 170};
    for (int a : array) {
      node.insertInOrder(a);
    }
    node.print();

    ArrayList<LinkedList<Integer>> allSeq = allSequences(node);
    for (LinkedList<Integer> list : allSeq) {
      System.out.println(list);
    }
    System.out.println(allSeq.size());
  }

}
