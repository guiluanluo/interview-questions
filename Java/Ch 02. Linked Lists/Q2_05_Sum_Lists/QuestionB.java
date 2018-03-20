package Q2_05_Sum_Lists;

import CtCILibrary.LinkedListNode;

/**
 * Part B is conceptually the same(recurse, carry the excess), but has some additional complicates when is comes to
 * implementations:
 *
 * 1: one list may be shorter that the other, and we cannot handle this "on the fly". for example, suppose we were
 * adding (1->2->3->4) and (5->6->7). we need to know that the 5 should be "matched" with the 2, not the 1. we can
 * accomplish this by comparing the lengths of the lists in the beginning and padding the shorter list with zeros.
 *
 * 2: in the first part, successive results were added to the tail ( passed forward).this mean that the recursive call
 * would be passed the carry, and would return the result (which is then appended to the tail). in this case, however,
 * results are added to the head (passed backward). the recursive call must return the result, as before, as well as the
 * carry. this is no terribly challenging to implement, but it is more cumbersome. we can solve this issue by creating a
 * wrapper calls call PartialSum.
 *
 * Note: how we have pulled insertBefore(), padList(0) and length() (not listed) into their own method. this makes the
 * code cleaner and easier to read - a wise thing to do in your interview!!
 */

public class QuestionB {

  private static int length(LinkedListNode l) {
    if (l == null) {
      return 0;
    } else {
      return 1 + length(l.next);
    }
  }

  /**
   * in the first part, successive results were added to the tail ( passed forward).this mean that the recursive call
   * would be passed the carry, and would return the result (which is then appended to the tail). in this case, however,
   * results are added to the head (passed backward). the recursive call must return the result, as before, as well as
   * the carry. this is no terribly challenging to implement, but it is more cumbersome. we can solve this issue by
   * creating a wrapper calls call PartialSum.
   */
  private static PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
    if (l1 == null && l2 == null) { // reach tail
      PartialSum sum = new PartialSum();
      return sum;
    }

    PartialSum sum = addListsHelper(l1.next, l2.next);

    //result is added to the header
    int val = sum.carry + l1.data + l2.data;
    LinkedListNode full_result = insertBefore(sum.sum, val % 10);
    sum.sum = full_result;
    sum.carry = val / 10;

    return sum;
  }

  private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2) {
    int len1 = length(l1);
    int len2 = length(l2);

    if (len1 < len2) {
      l1 = padList(l1, len2 - len1);
    } else {
      l2 = padList(l2, len1 - len2);
    }

    PartialSum sum = addListsHelper(l1, l2);

    if (sum.carry == 0) {
      return sum.sum;
    } else {
      LinkedListNode result = insertBefore(sum.sum, sum.carry);
      return result;
    }
  }

  private static LinkedListNode padList(LinkedListNode l, int padding) {
    LinkedListNode head = l;
    for (int i = 0; i < padding; i++) {
      head = insertBefore(head, 0);
    }
    return head;
  }

  private static LinkedListNode insertBefore(LinkedListNode list, int data) {
    LinkedListNode node = new LinkedListNode(data);
    if (list != null) {
      node.next = list;
    }
    return node;
  }

  public static int linkedListToInt(LinkedListNode node) {
    int value = 0;
    while (node != null) {
      value = value * 10 + node.data;
      node = node.next;
    }
    return value;
  }

  public static void main(String[] args) {
    LinkedListNode lA1 = new LinkedListNode(3, null, null);
    LinkedListNode lA2 = new LinkedListNode(1, null, lA1);

    LinkedListNode lB1 = new LinkedListNode(5, null, null);
    LinkedListNode lB2 = new LinkedListNode(9, null, lB1);
    LinkedListNode lB3 = new LinkedListNode(1, null, lB2);

    LinkedListNode list3 = addLists(lA1, lB1);

    System.out.println("  " + lA1.printForward());
    System.out.println("+ " + lB1.printForward());
    System.out.println("= " + list3.printForward());

    int l1 = linkedListToInt(lA1);
    int l2 = linkedListToInt(lB1);
    int l3 = linkedListToInt(list3);

    System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
    System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));
  }
}
