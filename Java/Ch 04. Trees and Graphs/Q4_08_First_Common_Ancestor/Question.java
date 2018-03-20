package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;

/**
 * First common ancestor: design an algorithm and write code to find the first common ancestor of two nodes in a binary
 * tree. avoid storing additional nodes in a data structure. Note: this is not necessarily a binary search tree.
 *
 * Lucy note: 1) a binary tree is a tree in which each node has up to two children. 2) a binary search tree is a binary
 * tree in which every node fits a specific order property: all left descendents <= n < all right descendents
 *
 * hint 10: if each node has a link to its parent, we could leverage the approach from question 2.7 on page 95. however,
 * our interviewer might not let make this assumption.
 *
 * hint 16: the first common ancestor is the deepest node such that P and Q are both decendants. think about how you
 * might identity thi node.
 *
 * hint 28: how would you figure out if P is a decentant of a node N?
 *
 * hint 36: start with the root. can you identify if root is the first common ancestor? if it is no, can you identify
 * which side of root the first common ancestor is on?
 *
 * hint 46: try a recursive approach. check if P and Q are decendants of the left subtree and the right subtree. if they
 * are decendants of different substrees, then the current node is the first common ancestor. if they are descendant s
 * of the same subtree, then that subtree holds the first common ancestor. now, how do you implement this effciently?
 *
 * hint 70: in the more naive algorithm, we had one method that indicated if X is a decendent of N, and another method
 * that would recurse to find the first common ancestor. this is repeatedly searching the samee elements ina subtree. we
 * should merge this into one firstCommonAncestor function. what return values would give us the information we need?
 *
 * hint 80: the firstCommonAncestor() could return the first common ancestor (if P and Q are both contained in the
 * tree), P if P is in the tree and not Q. Q if Q is in the tree and not P, and null otherwise.
 *
 * hint 96: careful! does your algorithm handle the case where on only one node exists? what will happen? you might need
 * to tweak the return values a bit
 */
public class Question {

  static int TWO_NODES_FOUND = 2;
  static int ONE_NODE_FOUND = 1;
  static int NO_NODES_FOUND = 0;

  // Checks how many 'special' nodes are located under this root
  public static int covers(TreeNode root, TreeNode p, TreeNode q) {
    int ret = NO_NODES_FOUND;
    if (root == null) {
      return ret;
    }
    if (root == p || root == q) {
      ret += 1;
    }
    ret += covers(root.left, p, q);
    if (ret == TWO_NODES_FOUND) // Found p and q
    {
      return ret;
    }
    return ret + covers(root.right, p, q);
  }

  public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (q == p && (root.left == q || root.right == q)) {
      return root;
    }
    int nodesFromLeft = covers(root.left, p, q); // Check left side
    if (nodesFromLeft == TWO_NODES_FOUND) {
      if (root.left == p || root.left == q) {
        return root.left;
      } else {
        return commonAncestor(root.left, p, q);
      }
    } else if (nodesFromLeft == ONE_NODE_FOUND) {
      if (root == p) {
        return p;
      } else if (root == q) {
        return q;
      }
    }

    int nodesFromRight = covers(root.right, p, q); // Check right side
    if (nodesFromRight == TWO_NODES_FOUND) {
      if (root.right == p || root.right == q) {
        return root.right;
      } else {
        return commonAncestor(root.right, p, q);
      }
    } else if (nodesFromRight == ONE_NODE_FOUND) {
      if (root == p) {
        return p;
      } else if (root == q) {
        return q;
      }
    }
    if (nodesFromLeft == ONE_NODE_FOUND &&
        nodesFromRight == ONE_NODE_FOUND) {
      return root;
    } else {
      return null;
    }
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TreeNode root = TreeNode.createMinimalBST(array);
    TreeNode n3 = root.find(1);
    TreeNode n7 = root.find(7);
    TreeNode ancestor = commonAncestor(root, n3, n7);
    System.out.println(ancestor.data);
  }

}
