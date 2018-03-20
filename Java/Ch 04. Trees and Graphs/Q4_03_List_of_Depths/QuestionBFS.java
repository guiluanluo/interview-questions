package Q4_03_List_of_Depths;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;

/**
 * List of depths: given a binary tree, design and algorithm which creates a linked list of all the nodes at each depth
 * (e.g, if you have a tree with depth D, you will have D linked lists)
 *
 * hint 107: try modifying a graph search algorithm to track the depth from the root.
 *
 * hint 123: a hash table or array that maps from level number to nodes at that level might also be useful
 *
 * hint 135: you should be able to come up with an algorithm involving both depth-first-search and breathe-first-search
 */
public class QuestionBFS {

  public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {

    ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();

		/* "Visit" the root */
    LinkedList<TreeNode> current = new LinkedList<TreeNode>();
    if (root != null) {
      current.add(root);
    }

    while (current.size() > 0) {
      result.add(current); // Add previous level

      LinkedList<TreeNode> parents = current; // Go to next level
      current = new LinkedList<TreeNode>();

      for (TreeNode parent : parents) {
        /* Visit the children */
        if (parent.left != null) {
          current.add(parent.left);
        }
        if (parent.right != null) {
          current.add(parent.right);
        }
      }
    }

    return result;
  }

  public static void printResult(ArrayList<LinkedList<TreeNode>> result) {
    int depth = 0;
    for (LinkedList<TreeNode> entry : result) {
      Iterator<TreeNode> i = entry.listIterator();
      System.out.print("Link list at depth " + depth + ":");

      while (i.hasNext()) {
        System.out.print(" " + ((TreeNode) i.next()).data);
      }
      System.out.println();

      depth++;
    }
  }


  public static void main(String[] args) {
    int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
    root.print();

    ArrayList<LinkedList<TreeNode>> list = createLevelLinkedList(root);
    printResult(list);
  }

}
