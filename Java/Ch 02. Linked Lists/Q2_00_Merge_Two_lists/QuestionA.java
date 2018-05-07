package Q2_00_Merge_Two_lists;

import CtCILibrary.LinkedListNode;

/**
 * https://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
 *
 * Write a SortedMerge() function that takes two lists, each of which is sorted in increasing order, and merges the two
 * together into one list which is in increasing order. SortedMerge() should return the new list. The new list should be
 * made by splicing together the nodes of the first two lists.
 *
 * For example if the first linked list a is 5->10->15 and the other linked list b is 2->3->20, then SortedMerge()
 * should return a pointer to the head node of the merged list 2->3->5->10->15->20.
 *
 * There are many cases to deal with: either ‘a’ or ‘b’ may be empty, during processing either ‘a’ or ‘b’ may run out
 * first, and finally there’s the problem of starting the result list empty, and building it up while going through ‘a’
 * and ‘b’.
 */
public class QuestionA {

  public static LinkedListNode merge(LinkedListNode node1, LinkedListNode node2) {
    LinkedListNode dumpHead = new LinkedListNode(-1);
    LinkedListNode current = dumpHead;
    while (node1 != null && node1 != null) {
      LinkedListNode copy;
      if (node1.data < node2.data) {
        copy = new LinkedListNode(node1.data);
        current.next = copy;
        current = copy;
        node1 = node1.next;
      } else {
        copy = new LinkedListNode(node2.data);
        current.next = copy;
        current = copy;
        node2 = node2.next;
      }
    }

    if (node1 != null) {
      current.next = node1;
    } else if (node2 != null) {
      current.next = node2;
    }
    return dumpHead.next;
  }


  public static void main(String[] args) {
    int[] values1 = {5, 10, 15};
    int[] values2 = {2, 3, 11, 20};
    LinkedListNode l1 = buildList(values1);
    LinkedListNode l2 = buildList(values2);

    LinkedListNode l3 = merge(l1, l2);

    System.out.println("l1: " + l1.printForward());
    System.out.println("l2: " + l2.printForward());
    System.out.println("l3: " + l3.printForward());
  }

  private static LinkedListNode buildList(int[] values) {
    LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
    LinkedListNode head = first;
    LinkedListNode second = first;

    for (int i = 0; i < values.length; i++) {
      second = new LinkedListNode(values[i], null, null);
      first.setNext(second);
      second.setPrevious(first);
      first = second;
    }
    return head.next;
  }
}
