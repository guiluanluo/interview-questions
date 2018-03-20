package Q17_12_BiNode;

import java.util.HashSet;
import java.util.Set;

/**
 * BiNode: consider a simple data structure called BiNode, which has pointers to two other nodes. the data structure
 * BiNode could be used to represent both a binary tree(where node1 is the left node and node2 is the right node) or a
 * doubly linked list(where node1 is the previous node and node2 is the next node). implement a method to convert a
 * binary search tree(implemented with BiNode) into a double linked list. the values should be keep in order and
 * operation should be performed in place(that is, on the original data structure).
 *
 * Solution: this seemingly complex problem can be implement quite elegantly using recursive. you will need to
 * understand recursive very well to solve it
 *
 * Solution A: additional data structure: the first and easier approach is to create a new data structure called
 * NodePair which hold just the head and tail of a linked list. the convert method can then return something of type
 * NodePair
 *
 * Solution B: retrieving the tail: instead of returning the head and tail of the linked list with NodePair, we can
 * return just the header, and then we can use the head to find the tail of the linked list. this algorithm takes O(n*n)
 * runtime(Solution B is close to lucy's idea !!)
 *
 * Solution C: building a circular linked list: build off the second solution, this approach requires returning the head
 * & tail of linked list with BiNode. we can do this by returning each list as the head of a circular linked list. to
 * get the tail, then, we simply call head.node1. this approach takes O(n) runtime, since each node is only touched an
 * average onf once (or, more accurately O(1) times).
 */
public class QuestionA {

  private static class NodePair {

    BiNode head;
    BiNode tail;

    public NodePair(BiNode head, BiNode tail) {
      this.head = head;
      this.tail = tail;
    }
  }

  public static NodePair convert(BiNode root) {
    if (root == null) {
      return null;
    }

    NodePair part1 = convert(root.node1);
    NodePair part2 = convert(root.node2);

    if (part1 != null) {
      concat(part1.tail, root);
    }

    if (part2 != null) {
      concat(root, part2.head);
    }

    return new NodePair(part1 == null ? root : part1.head, part2 == null ? root : part2.tail);
  }

  public static void concat(BiNode x, BiNode y) {
    x.node2 = y;
    y.node1 = x;
  }

  public static void printLinkedListTree(BiNode root) {
    for (BiNode node = root; node != null; node = node.node2) {
      if (node.node2 != null && node.node2.node1 != node) {
        System.out.print("inconsistent node: " + node);
      }
      System.out.print(node.data + "->");
    }
    System.out.println();
  }

  public static BiNode createTree() {
    BiNode[] nodes = new BiNode[7];
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new BiNode(i);
    }
    nodes[4].node1 = nodes[2];
    nodes[4].node2 = nodes[5];
    nodes[2].node1 = nodes[1];
    nodes[2].node2 = nodes[3];
    nodes[5].node2 = nodes[6];
    nodes[1].node1 = nodes[0];
    return nodes[4];
  }

  public static void printAsTree(BiNode root, String spaces) {
    if (root == null) {
      System.out.println(spaces + "- null");
      return;
    }
    System.out.println(spaces + "- " + root.data);
    printAsTree(root.node1, spaces + "   ");
    printAsTree(root.node2, spaces + "   ");
  }

  public static void main(String[] args) {
    BiNode root = createTree();
    printAsTree(root, "");
    NodePair n = convert(root);
    printLinkedListTree(n.head);

    System.out.println("\n called convert_lucy()... root:" + root.data);
    BiNode header = convert_lucy(root);
    BiNode runner = header;
    while (runner != null) {
      System.out.print(runner.data + "->");
      runner = runner.node2;
    }
  }


  public static BiNode convert_lucy(BiNode root) {
    BiNode header = new BiNode(Integer.MIN_VALUE);
    BiNode tail = header;
    Set<BiNode> visited = new HashSet<>();
    convert_inOrderTraversal(root, tail, visited);
    return header.node2;
  }

  public static void convert_inOrderTraversal(BiNode node, BiNode tail, Set<BiNode> visited) {
    if (node == null) {
      return;
    }

    if (node.node1 != null && !visited.contains(node.node1)) {
      convert_inOrderTraversal(node.node1, tail, visited);
    }

    if (!visited.contains(node)) {
      visited.add(node);
      System.out.println(node.data + " ");
      BiNode currentNode = new BiNode(node.data);
      currentNode.node1 = tail;
      tail.node2 = currentNode;
      tail = currentNode;
    }

    if (node.node2 != null && !visited.contains(node.node2)) {
      convert_inOrderTraversal(node.node2, tail, visited);
    }
  }


}


