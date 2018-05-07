package Q2_00_Reverse_List;

import CtCILibrary.LinkedListNode;

/**
 * https://www.geeksforgeeks.org/reverse-a-linked-list/
 *
 * Given pointer to the head node of a linked list, the task is to reverse the linked list. We need to reverse the list
 * by changing links between nodes.
 *
 * Examples:
 *
 * Input : Head of following linked list 1->2->3->4->NULL Output : Linked list should be changed to, 4->3->2->1->NULL
 *
 * Input : Head of following linked list 1->2->3->4->5->NULL Output : Linked list should be changed to,
 * 5->4->3->2->1->NULL
 *
 * Input : NULL Output : NULL
 *
 * Input  : 1->NULL Output : 1->NULL
 */
public class QuestionA {

  public static LinkedListNode reverseSingleLinkedList(LinkedListNode node) {

    LinkedListNode prev = null;
    LinkedListNode current = node;
    LinkedListNode next = null;

    while (current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    node = prev;
    return node;
  }


  public static void main(String[] args) {
    LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
    LinkedListNode head = first;
    LinkedListNode second = first;
    int[] values = {1, 3, 2, 3, 5};
    for (int i = 0; i < values.length; i++) {
      second = new LinkedListNode(values[i], null, null);
      first.setNext(second);
      //second.setPrevious(first);
      first = second;
    }
    System.out.println("head list: " + head.printForward());

    LinkedListNode reverse = reverseSingleLinkedList(head);
    System.out.println("After call reverse():" + reverse.printForward());
  }

}
