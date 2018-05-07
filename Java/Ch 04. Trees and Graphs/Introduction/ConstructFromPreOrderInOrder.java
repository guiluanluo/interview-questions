package Introduction;

/**
 * https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 */
public class ConstructFromPreOrderInOrder {

  /* A binary tree node has data, pointer to left child
and a pointer to right child */
  static class Node {

    char data;
    Node left, right;

    Node(char item) {
      data = item;
      left = right = null;
    }
  }

  static class BinaryTree {

    Node root;
    static int preIndex = 0;

    /**
     * Recursive function to construct binary of size len from
     * Inorder traversal in[] and Preorder traversal pre[].
     * Initial values of inStart and inEnd should be 0 and len -1.
     * The function doesn't do any error checking for cases where
     * inorder and preorder do not form a tree
     *
     * Note: The first element in pre[] is always rootIdx, search it in in[] to find left and right subtrees
     */
    public Node buildTree(char in[], char pre[], int inStart, int inEnd) {
      if (inStart > inEnd) {
        return null;
      }

      /* Pick current node from Preorder traversal using preIndex and increment preIndex */
      Node tNode = new Node(pre[preIndex++]);

		  /* If this node has no children then return */
      if (inStart == inEnd) {
        return tNode;
      }

		  /* Else find the index of this node in Inorder traversal */
      int inIndex = search(in, inStart, inEnd, tNode.data);

		/* Using index in Inorder traversal, construct left and right subtress */
      tNode.left = buildTree(in, pre, inStart, inIndex - 1);
      tNode.right = buildTree(in, pre, inIndex + 1, inEnd);

      return tNode;
    }

    /**
     * Function to find index of value in arr[start...end]
     * The function assumes that value is present in in[]
     */
    private int search(char arr[], int strt, int end, char value) {
      int i;
      for (i = strt; i <= end; i++) {
        if (arr[i] == value) {
          return i;
        }
      }
      return i;
    }

    /**
     * This funtcion is here just to test buildTree()
     */
    public void printInorder(Node node) {
      if (node == null) {
        return;
      }

		/* first recur on left child */
      printInorder(node.left);

		/* then print the data of node */
      System.out.print(node.data + " ");

		/* now recur on right child */
      printInorder(node.right);
    }
  }

  // driver program to test above functions
  public static void main(String args[]) {
    BinaryTree tree = new BinaryTree();
    char in[] = new char[]{'D', 'B', 'E', 'A', 'F', 'C'};
    char pre[] = new char[]{'A', 'B', 'D', 'E', 'C', 'F'};
    int len = in.length;
    Node root = tree.buildTree(in, pre, 0, len - 1);

    // building the tree by printing inorder traversal
    System.out.println("Inorder traversal of constructed tree is : ");
    tree.printInorder(root);
  }
}