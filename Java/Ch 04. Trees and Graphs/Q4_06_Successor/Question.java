package Q4_06_Successor;

import CtCILibrary.TreeNode;

/**
 * Successor: write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search
 * tree. you may assume that each node has a link to its parent.
 *
 * hint 79: think about how an in-order traversal works and try to "reverse engineer" it
 *
 * hint 91: here's one step of logic: the successor of a specific node is the leftmost node of the right subtree. what
 * if there is no right subtree, though?
 *
 * Solution: in-order traversal traverses the left subtree, then the current node, then the right subtree. to approach
 * this problem, we need to think very, very carefully about what happens.
 *
 * let's suppose we have a hypothetical node, we know that the order goes left subtree, then current node, then right
 * subtree. so, the next node we visit should be on the right side.
 *
 * but which node on the right subtree? it should be the first node we'd visit if we were doing an in-order traversal of
 * that subtree. this means that it should be the LEFTMOST node on the right subtree. easy enough!!!
 *
 * but what if the node doesn't have a right subtree? this is where it gets a bit trickier. if a node N doesn't have a
 * right subtree, then we are done traversing N's subtree. we need to pick up where we left off with N's parent, which
 * we'll call Q.
 *
 * if N was to the left of Q, then the next node we should traverse should be Q(again, since left -> current ->right).
 *
 * if N were to the right of Q, then we have fully traversed Q's subtree as well. we need to traverse upwards from Q
 * until we find a node X that we have not fully traversed. how do we know that we have not fully traversed a node X? we
 * know we have hit this case "when we move from a left node to its parent". the left node is fully traversed, but its
 * parent is not.
 */
public class Question {

  public static TreeNode inorderSucc(TreeNode n) {
    if (n == null) {
      return null;
    }

    // Found right children -> return left most node of right subtree
    if (n.parent == null || n.right != null) {
      return leftMostChild(n.right);

    } else {

      TreeNode q = n;
      TreeNode x = q.parent;

      // Go up until we�re on left instead of right
      while (x != null && x.left != q) {
        q = x;
        x = x.parent;
      }
      return x;
    }
  }

  public static TreeNode leftMostChild(TreeNode n) {
    if (n == null) {
      return null;
    }

    while (n.left != null) {
      n = n.left;
    }
    return n;
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TreeNode root = TreeNode.createMinimalBST(array);
    root.print();

    for (int i = 0; i < array.length; i++) {
      TreeNode node = root.find(array[i]);
      TreeNode next = inorderSucc(node);
      if (next != null) {
        System.out.println(node.data + "->" + next.data);
      } else {
        System.out.println(node.data + "->" + null);
      }
    }
  }

}
