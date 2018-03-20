package Q4_02_Minimal_Tree;

import CtCILibrary.TreeNode;

/**
 * Minimal tree: given a sorted (increasing order) array with unique integer elements, write an algorithm to create a
 * binary search tree with minimal height
 *
 * hint 19: a minimal binary tree has about the same number of nodes on the left of each node as on the right. let's
 * focus on just the root for now. how would you ensure that about the same number of nodes are on the left of the root
 * as on the right?
 *
 * hint 73: you could implement this by finding the "ideal" next element to add and repeatedly calling insertValue().
 * this will be a bit inefficient, as you would have to repeatedly traverse the tree. try recursion instead. can you
 * divide this problem into a sub-problem?
 *
 * hint 116: image we had a createMinimalTree() method that returns a minimal tree for a given array (but for some
 * strange reason doesn't operate on the root of the tree). could you use this to operate on the root of tree? could you
 * write the base case for the function? great! then that's basically the entire function
 *
 * Solution: to create a tree of minimal height, we need to match the number of nodes in the left subtree to the number
 * of the nodes in the right subtree as much as possible. this mean the root should be the middle of the array.
 */

public class Question {


  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    // We needed this code for other files, so check out the code in the library
    TreeNode root = TreeNode.createMinimalBST(array);
    System.out.println("Root? " + root.data);
    System.out.println("Created BST? " + root.isBST());
    System.out.println("Height: " + root.height());
    root.print();

  }

}
