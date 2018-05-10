package Q4_10_Check_Subtree;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

/**
 * Check subtree: T1 and T2 are two very large binary trees, with T1 much bigger than T2. create an algorithm to
 * determine if T2 is a subtree of T1. A tree T2 is a subtree of T1 if there exist a node N in T1 such that the subtree
 * of N is identical to T2. that is, if you cut off the tree at node N, the two trees would be identical.
 *
 * hint 4: if T2 is a subtree of T1, how will its in-order traversal compare to T1's? what about its pre-order and
 * post-order traversal?
 *
 * hint 11: the in-order traversals won't tell us much. after all, every binary search tree with the same
 * values(regardless of structure) will have the same in-order traversal. this is what in-order traversal means:
 * contents are in-order. (and if it won't work in the specific case of a binary search tree, then it certainly won't
 * work for a general binary tree).the pre-order traversal, however, is much more indicative.
 *
 * hint 18: you may have concluded that if T2. preorderTraversal() is a substring of T1.preorderTraversal, then T2 is a
 * subtree of T1. this is almost true, exception that the trees could have duplicate values. suppose T1 and T2 have all
 * duplicate values but different structures. the pre-order traversal will look the same even though T2 is a not a
 * subtree of T1. how can you handle situation like this?
 *
 * hint 31: although the problem seems likes it stems from duplicate values, it's really deeper that that. the issue is
 * that the pre-order traversal is the same only because there are null nodes that we skipped over(because the are
 * null). consider inserting a placeholder value into the pre-order traversal string whenever you reach a null node.
 * register the null node as a "real" node so that you can distinguish between the different structures.
 *
 * hint 37: alternatively, we can handle this problem recursively. given a specific node with T1, can we check to see if
 * its subtree matches T2?
 */
public class QuestionA {

  public static boolean containsTree(TreeNode t1, TreeNode t2) {
    StringBuilder string1 = new StringBuilder();
    StringBuilder string2 = new StringBuilder();

    getOrderString(t1, string1);
    getOrderString(t2, string2);

    return string1.indexOf(string2.toString()) != -1;
  }

  public static void getOrderString(TreeNode node, StringBuilder sb) {
    if (node == null) {
      sb.append("X");             // Add null indicator
      return;
    }
    sb.append(node.data);           // Add root
    getOrderString(node.left, sb);  // Add left
    getOrderString(node.right, sb); // Add right
  }

  public static void main(String[] args) {
    // t2 is a subtree of t1
    int[] array1 = {1, 2, 1, 3, 1, 1, 5};
    int[] array2 = {2, 3, 1};

    TreeNode t1 = AssortedMethods.createTreeFromArray(array1);
    TreeNode t2 = AssortedMethods.createTreeFromArray(array2);

    if (containsTree(t1, t2)) {
      System.out.println("t2 is a subtree of t1");
    } else {
      System.out.println("t2 is not a subtree of t1");
    }

    // t4 is not a subtree of t3
    int[] array3 = {1, 2, 3};
    TreeNode t3 = AssortedMethods.createTreeFromArray(array1);
    TreeNode t4 = AssortedMethods.createTreeFromArray(array3);

    if (containsTree(t3, t4)) {
      System.out.println("t4 is a subtree of t3");
    } else {
      System.out.println("t4 is not a subtree of t3");
    }
  }

}
