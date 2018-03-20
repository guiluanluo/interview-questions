package Q2_04_Partition;

import CtCILibrary.LinkedListNode;

/**
 * if we don't care about making the elements of the list "stable" ( where there's no obligation to, since the
 * interviewer hasn't specified that), then we can instead rearrange the elements by growing the list at the head and
 * trial.
 *
 * solution B, C are the same
 */
public class QuestionB {

  public static LinkedListNode partition(LinkedListNode node, int x) {
    LinkedListNode beforeStart = null;
    LinkedListNode afterStart = null;

		/* Partition list */
    while (node != null) {
      LinkedListNode next = node.next;

      if (node.data < x) {
        /* Insert node into start of before list */
        node.next = beforeStart;
        beforeStart = node;
      } else {
        /* Insert node into front of after list */
        node.next = afterStart;
        afterStart = node;
      }

      node = next;
    }

    System.out.println("**beforeStart:" + beforeStart.printForward());
    System.out.println("**afterStart:" + afterStart.printForward());

		/* Merge before list and after list */
    if (beforeStart == null) {
      return afterStart;
    }

    LinkedListNode head = beforeStart;
    while (beforeStart.next != null) {
      beforeStart = beforeStart.next;
    }

    beforeStart.next = afterStart;
    return head;
  }

  public static void main(String[] args) {
    int length = 20;
    LinkedListNode[] nodes = new LinkedListNode[length];
    for (int i = 0; i < length; i++) {
      nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
    }

    for (int i = 0; i < length; i++) {
      if (i < length - 1) {
        nodes[i].setNext(nodes[i + 1]);
      }
      if (i > 0) {
        nodes[i].setPrevious(nodes[i - 1]);
      }
    }

    LinkedListNode head = nodes[0];
    System.out.println(head.printForward());

    LinkedListNode h = partition(head, 7);
    System.out.println(h.printForward());
  }

}
