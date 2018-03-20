package Q2_02_Return_Kth_To_Last;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;

/**
 * Return Kth to last(lucy: last -> first): implement an algorithm to find the kth to last element of a singly linked
 * list.
 *
 * hint 8: what if you knew the linked list size? what is the difference between finding the Kth-to-last element and
 * finding the Xth element?
 *
 * hint 25: if you don't know the linked list size, can you compute it? how does this impact the runtime?
 *
 * hint 41: try implementing it recursively. if you could find the (K-1)th to last element, can you find the kth
 * element?
 *
 * hint 67: you might find it useful to return multiple values. some languages don't directly support this, but there
 * are workarounds in essentially any language. what are some of those workarounds?
 *
 * hint 126: can you do it iteratively? imagine if you had two pointers pointing to adjacent nodes and they were moving
 * at the same speed through the linked list. when one hits the end of the linked list, where will the other be?
 *
 * Solution: we wll approach this problem both recursively and non-recursively. remember that recursive solutions are
 * often cleaner but less optimal. for example, in this problem, the recursive implementation is about half the length
 * of the iterative solution but also takes O(N) space, where n is the number of elements in the linked list
 *
 * note that for this solution, we have defined k such that passing int k=1 would return the last element, k=2 would
 * return tot he second to last element,and so on. it is equally acceptable to define k such that k=0 would return the
 * last element.
 *
 * Solution A: don't return the element. one way to do this is to change the problem to simply printing the kth to last
 * element. then, we can pass back the value of the count simply through return values. of course, this is only valid
 * solution if the interviewer says it is valid!!
 */
public class QuestionA {

  public static LinkedListNode printKthToLast_lucy(LinkedListNode head, int k) {
    LinkedListNode runner1 = head;
    LinkedListNode runner2 = head;

    int counter = 0;
    while (counter < k) {
      if (runner2.next != null) {
        runner2 = runner2.next;
        counter += 1;
      } else {
        break;
      }
    }

    while (runner2 != null) {
      if (runner2.next != null) {
        runner1 = runner1.next;
        runner2 = runner2.next;
      } else {
        break;
      }
    }

    return runner1;

  }

  public static int printKthToLast(LinkedListNode head, int k) {
    if (head == null) {
      return 0;
    }

    int index = printKthToLast(head.next, k) + 1;
    if (index == k) {
      System.out.println(k + "th to last node is " + head.data);
    }
    return index;
  }

  public static void main(String[] args) {
    int[] array = {0, 1, 2, 3, 4, 5, 6};
    LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
    System.out.println(head.printForward());

    System.out.println("called printKthToLast()...");
    for (int i = 0; i <= array.length + 1; i++) {
      printKthToLast(head, i);
    }

    System.out.println("called printKthToLast_lucy()...");
    for (int i = 0; i <= array.length; i++) {
      System.out.println(i + "th to last node is " + printKthToLast_lucy(head, i).data);
    }
  }
}
