package Q2_01_Remove_Dups;

import CtCILibrary.LinkedListNode;

/**
 *
 */
public class ListCommonFunctions_lucy {

  public static LinkedListNode reverse(LinkedListNode node) {
    LinkedListNode head = null;

    while (node != null) {
      LinkedListNode next = node.next;
      LinkedListNode clone = new LinkedListNode(node.data);
      node.next = head;
      head = node;

      node = next;
    }
    return head;
  }

  public static LinkedListNode add2Tail(LinkedListNode node, int data) {
    LinkedListNode newNode = new LinkedListNode(data);

    LinkedListNode mover = node;
    while (mover != null) {
      if (mover.next == null) {
        mover.next = newNode;
        break;
      }
      mover = mover.next;
    }
    return node;
  }

  public static LinkedListNode add2Head(LinkedListNode node, int data) {
    LinkedListNode newNode = new LinkedListNode(data);
    newNode.next = node;
    return newNode;
  }


  public static void main(String[] args) {
    LinkedListNode lA1 = new LinkedListNode(3, null, null);
    LinkedListNode lA2 = new LinkedListNode(6, null, lA1);
    LinkedListNode lA3 = new LinkedListNode(9, null, lA2);

    System.out.println("lA1:  " + lA1.printForward());

    LinkedListNode reversedLA1 = reverse(lA1);
    System.out.println("reversedLA1:  " + reversedLA1.printForward());

    lA1 = new LinkedListNode(3, null, null);
    lA2 = new LinkedListNode(6, null, lA1);
    lA3 = new LinkedListNode(9, null, lA2);
    System.out.println("\nlA1:  " + lA1.printForward());
    LinkedListNode add2Tail = add2Tail(lA1, 12);
    System.out.println("add2Tail:  " + add2Tail.printForward());

    lA1 = new LinkedListNode(3, null, null);
    lA2 = new LinkedListNode(6, null, lA1);
    lA3 = new LinkedListNode(9, null, lA2);
    System.out.println("\nlA1:  " + lA1.printForward());
    LinkedListNode add2head = add2Head(lA1, 10);
    System.out.println("add2head:  " + add2head.printForward());


  }
}
