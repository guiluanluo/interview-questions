package Q2_05_Sum_Lists;


import CtCILibrary.LinkedListNode;

/*
 * Sum List: you have two numbers represented by a linked list, where each node contains a single digit. the digital are
 * stored in reverse order, such that the 1's digit is at the head of the list. write a function that adds the two
 * numbers and returns the sum as a linked list.
 *
 * Example:
 * input: (7->1->6) + (5->9->2). that is 617 +295
 * output: 2->1->9. that is 912
 *
 * Follow Up: suppose the digits are sorted in forward order. repeat the above problem.
 * Example:
 * input: (6->1->7) + (2->9->5). that is, 617 + 295.
 * output: 9->1->2. that is, 912
 *
 * hint 7: of course you could convert the linked lists to integers, compute the sum, and then convert it back to a new
 * linked list. if you did this in an interview, your interviewer would likely accept the answer, and then see if you
 * could do this without converting it to a number and back.
 *
 * hint 30: try recursion. suppose you have two lists, A = 1->5->9 (representing 951) and B = 2->3->6->7 (representing
 * 7632), and a function that operates on the remainder of the lists (5->9 and 3->6->7). could you use this to create
 * the sum method? what is the relationship between sum(1->5->9, 2->3->->6->7) and sum(5->9, 3->6->7)?
 *
 * hint 71: make sure you have considered linked lists that are not the same length.
 *
 * hint 95: does your algorithm work on linked list like 9->7->8 and 6->8->5? double check that.
 *
 * hint 109: for the follow-up questions: this issue is that when the linked lists aren't the same length, the head of
 * one linked list might represent the 1000's place while the other represents the 10's place. what if you make them the
 * same length? is there a way to modify the linked list to do that, without changing the value it represents?
 */
public class QuestionA {

  private static LinkedListNode addLists_lucy(LinkedListNode l1, LinkedListNode l2) {
    if (l1 == null && l2 == null) {
      return null;
    }

    LinkedListNode sumNodeHeader = null;
    LinkedListNode sumNode = null;

    int carrier = 0;
    while (l1 != null && l2 != null) {
      int value = l1.data + l2.data + (carrier == 1 ? 1 : 0);
      carrier = value >= 10 ? 1 : 0;
      value = value % 10;

      LinkedListNode node = new LinkedListNode(value, null, null);
      if (sumNodeHeader == null) {
        sumNodeHeader = node;
        sumNode = sumNodeHeader;
      } else {
        sumNode.next = node;
        sumNode = node;
      }

      l1 = l1.next;
      l2 = l2.next;
    }

    if (l1 != null || l2 != null) {
      LinkedListNode remainderNode = l1 != null ? l1 : l2;
      LinkedListNode l1r = remainderNode;
      while (l1r != null) {
        int data1 = l1r.data + (carrier == 1 ? 1 : 0);
        carrier = data1 > 10 ? 1 : 0;
        l1r.data = l1r.data % 10;
        l1r = l1r.next;
      }
      sumNode.next = remainderNode;
    }

    if (carrier == 1) {
      sumNode.next = new LinkedListNode(1, null, null);
    }

    return sumNodeHeader;

  }

  private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {

    if (l1 == null && l2 == null && carry == 0) {
      return null;
    }

    LinkedListNode result = new LinkedListNode();
    int value = carry;
    if (l1 != null) {
      value += l1.data;
    }
    if (l2 != null) {
      value += l2.data;
    }
    result.data = value % 10;
    if (l1 != null || l2 != null) {
      LinkedListNode more = addLists(l1 == null ? null : l1.next,
          l2 == null ? null : l2.next,
          value >= 10 ? 1 : 0);
      result.setNext(more);
    }
    return result;
  }

  public static int linkedListToInt(LinkedListNode node) {
    int value = 0;
    if (node.next != null) {
      value = 10 * linkedListToInt(node.next);
    }
    return value + node.data;
  }

  public static void main(String[] args) {
    LinkedListNode lA1 = new LinkedListNode(9, null, null);
    LinkedListNode lA2 = new LinkedListNode(9, null, lA1);
    LinkedListNode lA3 = new LinkedListNode(9, null, lA2);

    LinkedListNode lB1 = new LinkedListNode(1, null, null);
    LinkedListNode lB2 = new LinkedListNode(0, null, lB1);
    LinkedListNode lB3 = new LinkedListNode(0, null, lB2);

    LinkedListNode list3 = addLists(lA1, lB1, 0);

    System.out.println("  " + lA1.printForward());
    System.out.println("+ " + lB1.printForward());
    System.out.println("= " + list3.printForward());

    int l1 = linkedListToInt(lA1);
    int l2 = linkedListToInt(lB1);
    int l3 = linkedListToInt(list3);

    System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
    System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));

    System.out.print("\n====Call addLists_lucy() ====\n");

    LinkedListNode list33 = addLists_lucy(lA1, lB1);

    System.out.println("  " + lA1.printForward());
    System.out.println("+ " + lB1.printForward());
    System.out.println("= " + list33.printForward());

    int l11 = linkedListToInt(lA1);
    int l12 = linkedListToInt(lB1);
    int l13 = linkedListToInt(list33);

    System.out.print(l11 + " + " + l12 + " = " + l13 + "\n");
    System.out.print(l11 + " + " + l12 + " = " + (l11 + l12));


  }
}
