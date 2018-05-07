package Q2_00_Delete_Element;

import CtCILibrary.LinkedListNode;

/**
 * Delete a element x from a single linked list
 *
 * input: 1->3->2->3->3     output: 1->2
 */
public class QuestionA {

  public static LinkedListNode deleteElement(LinkedListNode head, int v) {
    if (head == null) {
      return null;
    }
    if (head.data == v) {
      return head.next;
    }

    LinkedListNode prev = head;
    LinkedListNode current = head.next;
    while (current != null) {
      if (current.data == v) {
        prev.next = current.next;
      } else {
        prev = current;
      }
      current = current.next;
    }
    return head;
  }

  public static void deleteOneNode(LinkedListNode head, int key) {
    // Store head node
    LinkedListNode prev = null;
    LinkedListNode temp = head;

    // If head node itself holds the key to be deleted
    if (temp != null && temp.data == key) {
      head = temp.next; // Changed head
      return;
    }

    // Search for the key to be deleted, keep track of the
    // previous node as we need to change temp.next
    while (temp != null && temp.data != key) {
      prev = temp;
      temp = temp.next;
    }

    // If key was not present in linked list
    if (temp == null) {
      return;
    }

    // Unlink the node from linked list
    prev.next = temp.next;
  }


  public static void main(String[] args) {
    LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
    LinkedListNode head = first;
    LinkedListNode second = first;

    int[] values = {1, 3, 2, 3, 3};

    for (int i = 0; i < values.length; i++) {
      second = new LinkedListNode(values[i], null, null);
      first.setNext(second);
      second.setPrevious(first);
      first = second;
    }
    System.out.println("head list: " + head.printForward());

    deleteElement(head, 3);
    System.out.println("After call deleteDups():" + head.printForward());
  }

}
