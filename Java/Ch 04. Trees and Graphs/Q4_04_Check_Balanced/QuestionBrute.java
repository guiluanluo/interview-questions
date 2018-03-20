package Q4_04_Check_Balanced;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

/**
 * Check balanced: implement a function to check if a binary tree is balance. for the purposes of this question, a
 * balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more that
 * one.
 *
 * hint 21: think about the definition of a balanced tree. can you check that condition for a single node? can you check
 * it for every node?
 *
 * hint 33: if you've developed a brute force solution, be careful about its runtime. if you are computing the height of
 * the subtrees for each node, you could have a pretty inefficient algorithm.
 *
 * hint 49: what if you could modify the binary tree node class to allow a node to store the height of its subtree?
 *
 * hint 105: you don't need to modify the binary tree class to store the height of the subtree. can you recursive
 * function compute the height of each subtree while also checking if a node is balanced? try having the function return
 * multiple values
 *
 * hint 124: actually, you can just have a single checkHeight() function that does both the height computation and the
 * balance check. an integer return value can be used to indicate both.
 */
public class QuestionBrute {

  public static int getHeight(TreeNode root) {
    if (root == null) {
      return -1;
    }
    return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
  }

  public static boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }

    int heightDiff = getHeight(root.left) - getHeight(root.right);
    if (Math.abs(heightDiff) > 1) {
      return false;
    } else {
      return isBalanced(root.left) && isBalanced(root.right);
    }
  }

  public static void main(String[] args) {
    // Create balanced tree
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TreeNode root = TreeNode.createMinimalBST(array);
    System.out.println("Root? " + root.data);
    System.out.println("Is balanced? " + isBalanced(root));

    // Could be balanced, actually, but it's very unlikely...
    TreeNode unbalanced = new TreeNode(10);
    for (int i = 0; i < 10; i++) {
      unbalanced.insertInOrder(AssortedMethods.randomIntInRange(0, 100));
    }
    System.out.println("Root? " + unbalanced.data);
    System.out.println("Is balanced? " + isBalanced(unbalanced));
  }

}
