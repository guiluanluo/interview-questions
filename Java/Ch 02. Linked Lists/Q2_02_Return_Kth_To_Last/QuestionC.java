package Q2_02_Return_Kth_To_Last;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;

/**
 * we described earlier that the issues was that we could not simultaneously return a counter and an index. if we wrap
 * the counter value with simple class( or even a single element array), we can mimic passing by reference.
 */
public class QuestionC {

  public static class Index {

    public int value = 0;
  }

  public static LinkedListNode kthToLast(LinkedListNode head, int k) {
    Index idx = new Index();
    return kthToLast(head, k, idx);
  }

  public static LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
    if (head == null) {
      return null;
    }

    LinkedListNode node = kthToLast(head.next, k, idx);
    idx.value = idx.value + 1;
    if (idx.value == k) {
      return head;
    }
    return node;
  }

  public static void main(String[] args) {
    int[] array = {0, 1, 2, 3, 4, 5, 6};
    LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
    System.out.println(head.printForward());

    for (int i = 0; i <= array.length + 1; i++) {
      LinkedListNode node = kthToLast(head, i);
      String nodeValue = node == null ? "null" : "" + node.data;
      System.out.println(i + ": " + nodeValue);
    }

    System.out.println("===Lucy print from 1 to array.length ===");
    for (int i = 1; i <= array.length; i++) {
      LinkedListNode node = kthToLast(head, i);
      String nodeValue = node == null ? "null" : "" + node.data;
      System.out.println(i + ": " + nodeValue);
    }
  }
}
