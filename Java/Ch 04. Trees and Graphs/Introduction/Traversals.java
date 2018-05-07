package Introduction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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

  public static void inOrderTraversal_iterative(TreeNode root) {
    if (root == null) {
      return;
    }

    StringBuilder build = new StringBuilder();

    //keep the nodes in the path that are waiting to be visited
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode node = root;

    //first node to be visited will be the left one
    while (node != null) {
      stack.push(node);
      node = node.left;
    }

    // traverse the tree
    while (!stack.isEmpty()) {
      // visit the top node
      node = stack.pop();
      build.append(node.data).append(" ");

      if (node.right != null) {
        node = node.right;
        // the next node to be visited is the leftmost
        while (node != null) {
          stack.push(node);
          node = node.left;
        }
      }
    }
    System.out.println(build.toString());
  }


  public static void preOrderTraversal(TreeNode node) {
    if (node != null) {
      visit(node);
      preOrderTraversal(node.left);
      preOrderTraversal(node.right);
    }
  }

  public static void preOrderTraversal_iterative(TreeNode root) {
    if (root == null) {
      return;
    }

    StringBuilder build = new StringBuilder();

    // Create an empty stack and push root to it
    Stack<TreeNode> nodeStack = new Stack<TreeNode>();
    nodeStack.push(root);

        /* Pop all items one by one. Do following for every popped item
         a) print it
         b) push its right child
         c) push its left child
         Note that right child is pushed first so that left is processed first
         */
    while (!nodeStack.empty()) {

      // Pop the top item from stack and print it
      TreeNode mynode = nodeStack.peek();
      build.append(mynode.data).append(" ");
      nodeStack.pop();

      // Push right and left children of the popped node to stack
      if (mynode.right != null) {
        nodeStack.push(mynode.right);
      }
      if (mynode.left != null) {
        nodeStack.push(mynode.left);
      }
    }
    System.out.println(build.toString());
  }

  public static void postOrderTraversal(TreeNode node) {
    if (node != null) {
      postOrderTraversal(node.left);
      postOrderTraversal(node.right);
      visit(node);
    }
  }

  public static void postOrderTraversal_iterative(TreeNode root) {
    if (root == null) {
      return;
    }

    StringBuilder build = new StringBuilder();

    // Create two stacks
    Stack<TreeNode> s1 = new Stack<>();
    Stack<TreeNode> s2 = new Stack<>();

    // push root to first stack
    s1.push(root);

    // Run while first stack is not empty
    while (!s1.isEmpty()) {
      // Pop an item from s1 and push it to s2
      TreeNode temp = s1.pop();
      s2.push(temp);

      // Push left and right children of, removed item to s1
      if (temp.left != null) {
        s1.push(temp.left);
      }
      if (temp.right != null) {
        s1.push(temp.right);
      }
    }

    // Print all elements of second stack
    while (!s2.isEmpty()) {
      TreeNode temp = s2.pop();
      build.append(temp.data).append(" ");
    }
    System.out.println(build.toString());
  }

  /**
   * we visit a node A and then iterate through each of A's children.
   * Note: pre-order and other forms of tree traversal are a form of DFS
   */
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

  /**
   * Top view of a binary tree is the set of nodes visible when the tree is viewed from the top
   */
  public static void topView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    topViewLeft(root.left, result);
    result.add(root.data);
    topViewRight(root.right, result);

    StringBuilder build = new StringBuilder();
    for (Integer data : result) {
      build.append(data).append(" ");
    }
    System.out.println(build.toString());
  }

  private static void topViewLeft(TreeNode node, List<Integer> result) {
    if (node == null) {
      return;
    }
    result.add(0, node.data);
    topViewLeft(node.left, result);
  }

  private static void topViewRight(TreeNode node, List<Integer> result) {
    if (node == null) {
      return;
    }
    result.add(node.data);
    topViewRight(node.right, result);
  }


  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    // We needed this code for other files, so check out the code in the library
    TreeNode root = TreeNode.createMinimalBST(array);
    BTreePrinter.printNode(root);

    System.out.println("==== preOrderTraversal ====");
    preOrderTraversal(root);
    System.out.println("==== preOrderTraversal_iterative ====");
    preOrderTraversal_iterative(root);

    System.out.println("==== inOrderTraversal ====");
    inOrderTraversal(root);
    System.out.println("==== inOrderTraversal_iterative ====");
    inOrderTraversal_iterative(root);

    System.out.println("==== postOrderTraversal ====");
    postOrderTraversal(root);
    System.out.println("==== postOrderTraversal_iterative ====");
    postOrderTraversal_iterative(root);

    System.out.println("==== depthFirstSearch ====");
    depthFirstSearch(root);

    System.out.println("==== breathFirstSearch ====");
    breathFirstSearch(root);

    System.out.println("==== breathFirstSearch_recursive ====");
    breathFirstSearch_recursive(root);

    System.out.println("==== topView ====");
    topView(root);
  }
}
