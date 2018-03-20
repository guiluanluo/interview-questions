package Q2_06_Palindrome;

import CtCILibrary.LinkedListNode;

/**
 * Palindrome: implement a function to check if a linked list is a palindrome
 *
 * A palindrome is a word or phrase that is the same forwards and backwards. such as "madam" or "nurses run"
 *
 * hint 5: a palindrome is something which is the same when written forwards and backwards. what if you reversed the
 * linked list?
 *
 * hint 13: try using a stack
 *
 * hint 29: assume you have the length of the linked list. can you implement this recursively?
 *
 * hint 61: in the recursive approach (we have the length of the list), the middle is the base case:
 * isPermutation(middle) is true. the node x to the immediate left of the middle: what can that node do to check if
 * x->middle->y forms a palindrome? now suppose that checks out. what about the previous node a? if x->middle->y is a
 * palindrome, how can it check that a->x->middle->y->b is a palindrome?
 *
 * hint 101: go back to previous hint. remember: there are ways to return multiple values. you can do this with a new
 * class.
 *
 * Solution A: reverse and compare:  reverse the linked list adn compare the reversed list to the original list. if they
 * are the same, the lists are identical
 *
 * observe that we have modularized this code into reverse() and isEqual() functions
 */
public class QuestionA {

  public static boolean isPalindrome(LinkedListNode head) {
    LinkedListNode reversed = reverseAndClone(head);
    return isEqual(head, reversed);
  }

  public static LinkedListNode reverseAndClone(LinkedListNode node) {
    LinkedListNode head = null;
    while (node != null) {
      LinkedListNode n = new LinkedListNode(node.data); // Clone
      n.next = head;
      head = n;

      node = node.next;
    }
    return head;
  }

  public static boolean isEqual(LinkedListNode one, LinkedListNode two) {
    while (one != null && two != null) {
      if (one.data != two.data) {
        return false;
      }

      one = one.next;
      two = two.next;
    }
    return one == null && two == null;
  }

  public static void main(String[] args) {
    int length = 9;
    LinkedListNode[] nodes = new LinkedListNode[length];
    for (int i = 0; i < length; i++) {
      nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
    }

    for (int i = 0; i < length; i++) {
      if (i < length - 1) {
        nodes[i].setNext(nodes[i + 1]);
      }
      if (i > 0) {
        nodes[i].setPrevious(nodes[i - 1]);
      }
    }
    // nodes[length - 2].data = 9; // Uncomment to ruin palindrome

    LinkedListNode head = nodes[0];
    System.out.println(head.printForward());
    System.out.println(isPalindrome(head));
  }

}
