package Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;

/**
 * Solution: with links to parents (better worst-case time)
 *
 * similar to Question B approach, we could trace P's path upwards and check if any of the node cover Q. the first node
 * that covers Q(we already know that every node on this path will cover P) must be the first common ancestor.
 *
 * to implement this, we can just traverse upwards from P, storing the parent and the sibling node in a variable. (the
 * sibling node is always a child of parent and refers to the newly uncovered subtree). at each iteration, sibling gets
 * set to the old parents's sibling node and parent gets set to parent.
 *
 * this algorithm takes O(t) time, where t is the size of subtree for the first common ancestor. in worst case, this
 * will be O(N), where N is the number of nodes in the tree. we can derive this runtime by noticing that each node in
 * that subtree is search once.
 */
public class QuestionC {

  public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    if (!covers(root, p) || !covers(root, q)) {
      return null;
    } else if (covers(p, q)) {
      return p;
    } else if (covers(q, p)) {
      return q;
    }

    TreeNode sibling = getSibling(p);
    TreeNode parent = p.parent;
    while (!covers(sibling, q)) {
      sibling = getSibling(parent);
      parent = parent.parent;
    }
    return parent;
  }

  public static boolean covers(TreeNode root, TreeNode p) {
    if (root == null) {
      return false;
    }

    if (root == p) {
      return true;
    }

    return covers(root.left, p) || covers(root.right, p);
  }

  public static TreeNode getSibling(TreeNode node) {
    if (node == null || node.parent == null) {
      return null;
    }

    TreeNode parent = node.parent;
    return parent.left == node ? parent.right : parent.left;
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TreeNode root = TreeNode.createMinimalBST(array);
    root.print();

    TreeNode n3 = root.find(1);
    TreeNode n7 = root.find(7);
    TreeNode ancestor = commonAncestor(root, n3, n7);
    System.out.println(ancestor.data);
  }

}
