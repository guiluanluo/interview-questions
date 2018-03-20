package Q2_06_Palindrome;

import CtCILibrary.LinkedListNode;

/**
 * TODO: need to study recursive first, and come back to study - LL
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
 */
public class QuestionC {

  public static class Result {

    public LinkedListNode node;
    public boolean result;

    public Result(LinkedListNode n, boolean res) {
      node = n;
      result = res;
    }
  }

  public static Result isPalindromeRecurse(LinkedListNode head, int length) {

    if (head == null || length <= 0) { // Even number of nodes
      return new Result(head, true);
    } else if (length == 1) { // Odd number of nodes
      return new Result(head.next, true);
    }

		/* Recurse on sublist. */
    Result res = isPalindromeRecurse(head.next, length - 2);

		/* If child calls are not a palindrome, pass back up a failure. */
    if (!res.result || res.node == null) {
      return res;
    }
		
		/* Check if matches corresponding node on other side. */
    res.result = (head.data == res.node.data);
		
		/* Return corresponding node. */
    res.node = res.node.next;

    return res;
  }

  public static int lengthOfList(LinkedListNode n) {
    int size = 0;
    while (n != null) {
      size++;
      n = n.next;
    }
    return size;
  }

  public static boolean isPalindrome(LinkedListNode head) {
    int length = lengthOfList(head);
    Result p = isPalindromeRecurse(head, length);
    return p.result;
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
    //nodes[length - 2].data = 9; // Uncomment to ruin palindrome

    LinkedListNode head = nodes[0];
    System.out.println(head.printForward());
    System.out.println(isPalindrome(head));
  }

}
