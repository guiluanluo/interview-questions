package Q2_01_Remove_Dups;

import CtCILibrary.LinkedListNode;

/**
 * Lucy: what is advantage of solution C???? it is not easy to understand..
 */
public class QuestionC {

  public static void deleteDups(LinkedListNode head) {
    if (head == null) {
      return;
    }

    LinkedListNode previous = head;
    LinkedListNode current = previous.next;

    while (current != null) {
      System.out.println("current.data:" + current.data);

      // Look backwards for dups, and remove any that you see.
      LinkedListNode runner = head;
      while (runner != current) {
        if (runner.data == current.data) {
          System.out.println("  **found the same data: " + runner.data);
          LinkedListNode tmp = current.next;
          previous.next = tmp;
          current = tmp;
          /* We know we can't have more than one dup preceding
           * our element since it would have been removed earlier.
           */
          break;
        }
        runner = runner.next;
      }

			/* If runner == current, then we didn't find any duplicate 
       * elements in the previous for loop.  We then need to
			 * increment current.  
			 * If runner != current, then we must have hit the �break� 
			 * condition, in which case we found a dup and current has
			 * already been incremented.*/
      if (runner == current) {
        previous = current;
        current = current.next;
      }
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
    System.out.println(head.printForward());
  }
}
