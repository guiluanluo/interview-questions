package Q4_05_Validate_BST;

import CtCILibrary.TreeNode;

/**
 * Validate BST: implement a function to check if a binary tree is a binary search tree. BST is a binary tree in which
 * every node fits a specific ordering property - all left decendents <= n < right decendents.
 *
 * hint 35: if you traversed the tree using an in-order traversal and the elements were truly in the right order, does
 * this indicate that the tree is actually in order? what happens for duplicate elements? if duplicate elements are
 * allowed, they must be on a specific side (usually left).
 *
 * hint 57: to be a binary search tree, it is not sufficient that the left.vale <= current.value < right.value for each
 * node. every node on the left must less that the current node, which must be less that all the nodes on the right.
 *
 * hint 86: if every node on the left must be less that or equal to the current node, then this is really the same thing
 * as saying that the biggest node on the left must be less than or equal to the current node.
 *
 * hint 113: rather that validating the current node's value against leftTree.max and rightTree.min, can we flip around
 * the logic? validate the left tree's nodes to ensure that they are smaller than current.value
 *
 * hint 128: think about the checkBST function as a recursive function that ensures each node is within an
 * allowable(min1, max) range. at first, thi range is infinite. when we traverse to the left, the min1 is negative
 * infinity and the max is root.value. can you implement this recursive function and properly adjust these ranges as you
 * traverse the tree?
 *
 * Solution: we can implement this in two ways: the first leverages the in-order traversal, and the second builds off
 * the property that left <= current < right.
 *
 * in-order traversal with tracking the last element we was and compare it as we go
 */
public class Question {

  public static Integer last_printed = null;

  public static boolean checkBST(TreeNode node) {
    return checkBST(node, true);
  }

  // Allow "equal" value only for left child. This validates the BST property.
  public static boolean checkBST(TreeNode n, boolean isLeft) {
    if (n == null) {
      return true;
    }

    /**
     *Check / recurse left
     */
    if (!checkBST(n.left, true)) {
      return false;
    }

    /**
     * Check current
     */
    if (last_printed != null) {

      if (isLeft) {
        // left child "is allowed" be equal to parent.
        if (n.data < last_printed) {
          return false;
        }

      } else {
        // Right child "is not allowed" be equal to parent.
        if (n.data <= last_printed) {
          return false;
        }
      }
    }
    last_printed = n.data;

    /**
     *Check / recurse right
     */
    if (!checkBST(n.right, false)) {
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    int[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
    TreeNode node = TreeNode.createMinimalBST(array);
    //node.left.data = 5;
    //node.left.right.data = 3;
    System.out.println(checkBST(node));

    test();
  }

  public static void test() {
    TreeNode node;
    boolean condition;
    System.out.println("test cases for equals condition.");

		/* Expect true: for left child: node.data <= last_printed.
   2
  / \
 /   \
 2   3
      \
      4
		*/
    int[] array2 = {1, 2, 3, 4};
    node = TreeNode.createMinimalBST(array2);
    node.left.data = 2;
    node.print();
    last_printed = null;
    condition = checkBST(node);
    System.out.println("should be true: " + condition);

		/* Expect false: for right child: node.data <= last_printed.
   2
  / \
 /   \
 1   2
      \
      4
		 */
    int[] array3 = {1, 2, 3, 4};
    node = TreeNode.createMinimalBST(array3);
    node.right.data = 2;
    node.print();
    last_printed = null;
    condition = checkBST(node);
    System.out.println("should be false: " + condition);
  }
}