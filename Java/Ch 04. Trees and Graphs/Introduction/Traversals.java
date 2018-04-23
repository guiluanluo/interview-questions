package Introduction;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import CtCILibrary.BTreePrinter;
import CtCILibrary.TreeNode;

/**
 * In-Order Traversal: means to "visit"(often print) the left branch, then the current node, and finally, the right
 * branch. when performed on a binary search tree, it visits the nodes in ascending order (hence the name "in-order")
 *
 * Pre-Order Traversal: means to "visit"(often print) the current node BEFORE its child nodes (hence the name
 * "pre-order"). the root node is always the first node visited!!
 *
 * Post-Order Traversal: means to "visit"(often print) the current node AFTER its child node (hence the name
 * "post-order"). the root node is always the last node visited!!
 */
public class Traversals {

  public static void visit(TreeNode node) {
    if (node != null) {
      System.out.println(node.data);
    }
  }

  public static void inOrderTraversal(TreeNode node) {
    if (node != null) {
      inOrderTraversal(node.left);
      visit(node);
      inOrderTraversal(node.right);
    }
  }

  public static void preOrderTraversal(TreeNode node) {
    if (node != null) {
      visit(node);
      preOrderTraversal(node.left);
      preOrderTraversal(node.right);
    }
  }

  public static void postOrderTraversal(TreeNode node) {
    if (node != null) {
      postOrderTraversal(node.left);
      postOrderTraversal(node.right);
      visit(node);
    }
  }

  //we visit a node A and then iterate through each of A's children.
  //Note: pre-order and other forms of tree traversal are a form of DFS
  public static void depthFirstSearch(TreeNode node) {
    if (node == null) {
      return;
    }

    visit(node);
    depthFirstSearch(node.left);
    depthFirstSearch(node.right);
  }

  public static void breathFirstSearch(TreeNode node) {
    Queue queue = new LinkedList();
    queue.add(node);

    while (queue.peek() != null) {
      TreeNode currentNode = (TreeNode) queue.poll();
      visit(currentNode);

      if (currentNode.left != null) {
        queue.add(currentNode.left);
      }
      if (currentNode.right != null) {
        queue.add(currentNode.right);
      }
    }
  }

  public static void breathFirstSearch_recursive(TreeNode root) {
    if (root != null) {
      printLevelOrder(Arrays.asList(root));
    }
  }

  private static void printLevelOrder(List<TreeNode> level) {
    List<TreeNode> nextLevel = new LinkedList<>();
    for (TreeNode node : level) {
      visit(node);
      if (node.left != null) {
        nextLevel.add(node.left);
      }
      if (node.right != null) {
        nextLevel.add(node.right);
      }
    }
    if (!nextLevel.isEmpty()) {
      printLevelOrder(nextLevel);
    }
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    // We needed this code for other files, so check out the code in the library
    TreeNode root = TreeNode.createMinimalBST(array);
    BTreePrinter.printNode(root);

    System.out.println("==== preOrderTraversal ====");
    preOrderTraversal(root);

    System.out.println("==== inOrderTraversal ====");
    inOrderTraversal(root);

    System.out.println("==== postOrderTraversal ====");
    postOrderTraversal(root);

    System.out.println("==== depthFirstSearch ====");
    depthFirstSearch(root);

    System.out.println("==== breathFirstSearch ====");
    breathFirstSearch(root);

    System.out.println("==== breathFirstSearch_recursive ====");
    breathFirstSearch_recursive(root);
  }
}
