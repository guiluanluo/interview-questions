package Introduction;

/**
 * https://www.geeksforgeeks.org/print-postorder-from-given-inorder-and-preorder-traversals/
 * Given Inorder and Preorder traversals of a binary tree, print Postorder traversal.
 *
 * Example:
 * Input: Inorder traversal in[] = {4, 2, 5, 1, 3, 6}
 * Preorder traversal pre[] = {1, 2, 4, 5, 3, 6}
 *
 * Output: Postorder traversal is {4, 5, 2, 6, 3, 1}
 *
 * A naive method is to first construct the tree, then use simple recursive method to print postorder traversal of the
 * constructed tree.
 *
 * We can print postorder traversal without constructing the tree. The idea is, root is always the first item in
 * preorder traversal and it must be the last item in postorder traversal. We first recursively print left subtree, then
 * recursively print right subtree. Finally, print root. To find boundaries of left and right subtrees in pre[] and
 * in[], we search root in in[], all elements before root in in[] are elements of left subtree and all elements after
 * root are elements of right subtree. In pre[], all elements after index of root in in[] are elements of right subtree.
 * And elements before index (including the element at index and excluding the first element) are elements of left
 * subtree.
 *
 * Input: Inorder traversal in[] = {4, 2, 5, 1, 3, 6}
 * Preorder traversal pre[] = {1, 2, 4, 5, 3, 6}
 *
 * 1. The pre[0] is the root of the tree --> 1
 * 2. The in[] represents the tree as: { { left-sub-tree }; root; { right-sub-tree } }
 * So the index of the root at in[] is the length of the left sub-tree --> 3
 * 3. The (in[].length - rootIndex - 1) is the length of the right sub-tree --> 2
 * 4. The pre[] represents the tree as: { root ; { left-sub-tree }; { right-sub-tree } }
 * Since we know the lengths of the sub-trees from steps #2 and #3, we can proceed recursively as follows:
 *
 * leftIn[] = in[ 0 .. (rootIndex-1) ] = { 4, 2, 5 }
 * leftPre[] = pre[ 1 .. (rootIndex-2) ] = { 2, 4, 5 }
 *
 * rightIn[] = in[ (rootIndex+1) .. (length-1) ] = { 3, 6 }
 * rightPre[] = pre[ (rootIndex+1) .. (length-1) ] = { 3, 6 }
 *
 * Recursion ends when the lengths of the in[] and pre[] become equal to 1.
 */
public class PrintPostOrderFromPreOrderInOrder {

  // Prints postorder traversal from given inorder and preorder traversals
  public void printPostOrder(int in[], int pre[]) {
    printPostOrder(in, pre, 0, in.length - 1, 0, pre.length - 1);
  }

  public void printPostOrder(int in[], int pre[], int inStart, int inEnd, int preStart, int preEnd) {
    // The first element in pre[] is always root, search it in in[] to find left and right subtrees
    int root = search(in, inStart, inEnd, pre[preStart]);

    // If left subtree is not empty, print left subtree
    if (root != 0 && root > inStart - 1) {
//      printPostOrder(in, pre + 1, root);
      printPostOrder(in, pre, inStart, root, preStart + 1, root);
    }

    // If right subtree is not empty, print right subtree ????
    if (root != (inEnd - inStart) && root < inEnd-2) {
//    if (root != n-1) {
//      printPostOrder(in + root + 1, pre + root + 1, n - root - 1);
      printPostOrder(in, pre, root + 1, inEnd, root + 1, preEnd);
    }

    // Print root
    System.out.println(pre[preStart] + " ");
  }

  private // A utility function to search x in arr[] of size n
  int search(int arr[], int inStart, int inEnd, int x) {
    for (int i = inStart; i <=inEnd; i++) {
      if (arr[i] == x) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String args[]) {
//    int preorder[] = {10, 5, 3, 21, 20, 18, 9, 16};
//    int inorder[] = {3, 5, 21, 10, 18, 9, 20, 16};

    int preorder[] = {4, 2, 5, 1, 3, 6};
    int inorder[] = {1, 2, 4, 5, 3, 6};

    PrintPostOrderFromPreOrderInOrder ppp = new PrintPostOrderFromPreOrderInOrder();
    ppp.printPostOrder(preorder, inorder);

    //Postorder traversal is {4, 5, 2, 6, 3, 1}
  }
}
