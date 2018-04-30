package Q2_01_Remove_Dups;

import java.util.HashSet;

import CtCILibrary.LinkedListNode;

/**
 * Remove Dups: write code to remove duplicates from an unsorted linked list
 *
 * FOLLOW UP: how would you solve this problem if a temporary buffer is not allowed?
 *
 * hint 9: have you tried a hash table? you should be able to do this in a single pass of the linked list.
 *
 * hint 40: without extra space, you will need O(N*N) time. try using two pointers, where the second one searches ahead
 * of the first one.
 *
 * Solution A takes O(N) time, with O(N) space.
 */
public class QuestionA {

  public static void deleteDups_lucy(LinkedListNode n) {

    HashSet<Integer> set = new HashSet<Integer>();
    LinkedListNode previous = null;

    while (n != null) {
      if (!set.contains(n.data)) {
        set.add(n.data);
        previous = n;
      } else {
        previous.next = n.next;
      }
      n = n.next;
    }
  }

  public static void deleteDups(LinkedListNode n) {

    HashSet<Integer> set = new HashSet<Integer>();
    LinkedListNode previous = null;

    while (n != null) {
      if (set.contains(n.data)) {
        previous.next = n.next;
      } else {
        set.add(n.data);
        previous = n;
      }

      n = n.next;
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
    System.out.println("head list: " +head.printForward());

    deleteDups(head);
    System.out.println("After call deleteDups():" + head.printForward());
  }
}
