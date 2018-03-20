package Q2_04_Partition;

import CtCILibrary.LinkedListNode;

/*
 * Partition: write code to partition a linked list around a value x, such that all nodes less than x come before all
 * nodes greater than or equal to x. if x is contained within the list, the values of x only need to be after the
 * elements less than x(see below). the partition element x can appear anywhere in the "right partition"; it does not
 * need to appear between the left and right partitions.
 *
 * Example:
 * input:  3->5->-8->5->10->2->1 [partition =5]
 * output: 3->1-->2->10->5->5->8
 *
 * Hint 3: there are many solutions to this problem, most of which are equally optimal in runtime. some have shorter,
 * cleaner code than others. can you brainstorm different solutions?
 *
 * Hint 24: consider that the elements don't have to stay in the same relative order. we only need to ensure that
 * elements less than the pivot must be before elements greater than the pivot. does that help you come up with
 * more solutions?
 *
 * Solution A: if this were an array, we would need to be careful about how we shifted elements. array shifts are very
 * expensive. however, in a linked list, the situation is much easier. rather than shifting and swapping elements,
 * we can actually create two different linked lists: one for elements less than x, another for elements >= x, then
 * merge two linked list
 */
public class Question {

  public static LinkedListNode partition_lucy0(LinkedListNode node, int x) {
    LinkedListNode smaller = null;
    LinkedListNode larger = null;

    LinkedListNode current = node;

    while (current != null) {
//      System.out.println("data:" + current.data);
      LinkedListNode cloneNode = new LinkedListNode(current.data, null, null);
      if (current.data < x) {
        if (smaller == null) {
          smaller = cloneNode;
        } else {
          smaller.prev = cloneNode;
          cloneNode.next = smaller;
          smaller = cloneNode;
        }
      } else {
        if (larger == null) {
          larger = cloneNode;
        } else {
          larger.prev = cloneNode;
          cloneNode.next = larger;
          larger = cloneNode;
        }
      }

      current = current.next;
    }

    if (smaller == null) {
      return larger;
    }

    if (smaller != null && larger != null) {
      LinkedListNode runner = smaller;
      while (runner.next != null) {
        runner = runner.next;
      }
      runner.next = larger;
      larger.prev = runner;
      return smaller;
    }

    return null;
  }

  public static LinkedListNode partition_lucy(LinkedListNode node, int x) {
    LinkedListNode beforePivotHeader = null;
    LinkedListNode beforePivot = null;
    LinkedListNode afterPivotHeader = null;
    LinkedListNode afterPivot = null;

		/* Partition list */
    while (node != null) {
      LinkedListNode next = node.next;
      node.next = null;

      if (node.data < x) {
        if (beforePivotHeader == null) {
          beforePivotHeader = node;
          beforePivot = beforePivotHeader;
        } else {
          beforePivot.next = node;
          beforePivot = node;
        }
      } else {
        if (afterPivotHeader == null) {
          afterPivotHeader = node;
          afterPivot = afterPivotHeader;
        } else {
          afterPivot.next = node;
          afterPivot = node;
        }
      }

      node = next;
    }

    System.out.println("**beforePivotHeader:" + beforePivotHeader.printForward());
    System.out.println("**afterPivotHeader:" + afterPivotHeader.printForward());

		/* Merge before list and after list */
    if (beforePivotHeader == null) {
      return afterPivotHeader;
    }

    beforePivot.next = afterPivotHeader;
    return beforePivotHeader;
  }


  public static LinkedListNode partition(LinkedListNode node, int x) {
    LinkedListNode beforeStart = null;  //header
    LinkedListNode beforeEnd = null;
    LinkedListNode afterStart = null; //header
    LinkedListNode afterEnd = null;

		/* Partition list */
    while (node != null) {
      LinkedListNode next = node.next;
      node.next = null;

      if (node.data < x) {
        if (beforeStart == null) {
          beforeStart = node;
          beforeEnd = beforeStart;
        } else {
          beforeEnd.next = node;
          beforeEnd = node;
        }
      } else {
        if (afterStart == null) {
          afterStart = node;
          afterEnd = afterStart;
        } else {
          afterEnd.next = node;
          afterEnd = node;
        }
      }

      node = next;
    }

    System.out.println("**beforeStart:" + beforeStart.printForward());
    System.out.println("**afterStart:" + afterStart.printForward());

		/* Merge before list and after list */
    if (beforeStart == null) {
      return afterStart;
    }

    beforeEnd.next = afterStart;
    return beforeStart;
  }

  public static void main(String[] args) {
    /* Create linked list */
    int[] vals = {33, 9, 2, 3, 10, 10389, 838, 874578, 5};
    LinkedListNode head = new LinkedListNode(vals[0], null, null);
    LinkedListNode current = head;
    for (int i = 1; i < vals.length; i++) {
      current = new LinkedListNode(vals[i], null, current);
    }
    System.out.println(head.printForward());

    System.out.println("==partition by 3 ====");
    /* Partition */
    LinkedListNode h = partition(head, 3);
    /* Print Result */
    System.out.println(h.printForward());

    System.out.println("==partition_lucy by 10 ====");
    /* Partition */
    LinkedListNode h1 = partition_lucy(head, 10);
    /* Print Result */
    System.out.println(h1.printForward());



    /* Create linked list */
    int[] vals2 = {33, 9, 2, 3, 10, 10389, 838, 874578, 5};
    LinkedListNode head2 = new LinkedListNode(vals[0], null, null);
    LinkedListNode current2 = head2;
    for (int i = 1; i < vals2.length; i++) {
      current2 = new LinkedListNode(vals2[i], null, current2);
    }
    System.out.println("==partition_lucy0 by 10 ====");
    /* Partition */
    System.out.println("original list:" + head2.printForward());
    LinkedListNode h2 = partition_lucy0(head2, 10);
    /* Print Result */
    System.out.println(h2.printForward());

  }

}
