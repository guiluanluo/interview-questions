package Q2_03_Delete_Middle_Node;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;

/*
 * Delete Middle Node: implement an algorithm to delete a node in the middle (i.e.., any node but the first and last
 * node, not necessarily the exact middle) of a singly linked list, given only access to that node.
 *
 * Example:
 * input: the node c from the linked list a->b->c->d->e->f
 * result: nothing is returned, but the new linked list looks like a->b->d->e->f
 *
 * hint 72: picture the list 1->5->9->12. removing 9 would make it look like 1->5->12. you only have access to the 9 node.
  * can you make it look like the correct answer?
 */
public class Question {



  public static boolean deleteNode(LinkedListNode n) {

    if (n == null || n.next == null) {
      return false; // Failure
    }

    LinkedListNode next = n.next;
    n.data = next.data;
    n.next = next.next;

    return true;
  }

  public static void main(String[] args) {
    LinkedListNode head = AssortedMethods.randomLinkedList(10, 0, 10);
    System.out.println(head.printForward());
    deleteNode(head.next.next.next.next); // delete node 4
    System.out.println(head.printForward());
  }
}
