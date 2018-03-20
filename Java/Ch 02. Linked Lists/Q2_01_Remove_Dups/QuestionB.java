package Q2_01_Remove_Dups;

import CtCILibrary.LinkedListNode;

/**
 * Follow up: no buffer allowed
 *
 * if we don't have a buffer, we can iterate with two pointers: current which iterates through the linked list, and
 * runner which checks all subsequent nodes for duplicates.
 *
 * Solution B takes O(N*N) time, no additional space O(1).
 */
public class QuestionB {

  public static void deleteDups_lucy(LinkedListNode head) {
    LinkedListNode current = head;
    LinkedListNode runner = current;

    while (current != null) {
      while (runner.next != null) {
        if (runner.next.data == current.data) {
          runner.next = runner.next.next;
        } else {
          runner = runner.next;
        }
      }
      current = current.next;
    }
  }


  public static void deleteDups(LinkedListNode head) {
    LinkedListNode current = head;

    while (current != null) {
      /* Remove all future nodes that have the same value */
      LinkedListNode runner = current;
      while (runner.next != null) {
        if (runner.next.data == current.data) {
          runner.next = runner.next.next;
        } else {
          runner = runner.next;
        }
      }
      current = current.next;
    }
  }

  public static void main(String[] args) {
    LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
    LinkedListNode head = first;
    LinkedListNode second = first;
    for (int i = 1; i < 8; i++) {
      second = new LinkedListNode(i % 2, null, null);
      first.setNext(second);
      second.setPrevious(first);
      first = second;
    }
    System.out.println(head.printForward());
    deleteDups(head);
  }
}
