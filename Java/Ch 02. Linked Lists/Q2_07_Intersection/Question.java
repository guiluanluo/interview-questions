package Q2_07_Intersection;

import CtCILibrary.AssortedMethods;
import CtCILibrary.LinkedListNode;

/**
 * Intersection: given two (single) linked lists, determine if the two lists intersect. return the intersection node.
 * note that the intersection is defined based on reference, not value. that is, if the kth node of the first linked
 * list is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.
 *
 * hint 20: you can do this in O(A+B) time and O(1) additional space. that is, you do not need a hash table(although you
 * could do it with one).
 *
 * hint 45: examples will help you. draw a picture of intersecting linked lists and two equivalent linked lists (by
 * value) that do not intersect.
 *
 * hint 55: focus first on just identifying if there's an intersection.
 *
 * hint 65: observe that two intersecting linked lists will always have the same last node. once they intersect, all the
 * nodes after that will be equal.
 *
 * hint 76: you can determine if two linked lists intersect by traversing to the end of each and comparing their tails.
 *
 * hint 93: now, you need ot find where the linked lists intersect. suppose the linked list were the same length. how
 * could you do this?
 *
 * hint 111: if the two linked list were the same length, you could traverse forward in each until you found an element
 * in common. now , how do you adjust this for lists of different lengths?
 *
 * hint 120: try using the difference between the lengths of the two linked lists.
 *
 * hint 129: if you move a pointer in the longer linked list forward by the difference in lengths, you can then apply a
 * similar approach to the scenario when the linked lists are equal.
 *
 * Comments
 *
 * 1) determining if there is an intersection: one approach would be to use a hash table and just throw all the linked
 * lists nodes into there. we would need ot be careful to reference the lined lists by there memory location, not by
 * their value. there is an easier way thought. observe that two intersecting linked lists will always have the same
 * last node!! therefore, we can just traverse to the ed of each linked list and compare the last node.
 *
 * 2) finding the intersecting node: one through is that we could traverse backwards through each linked. when the
 * linked lists "split", that's the intersection. of course, you cannot really traverse backwards through a singly
 * linked list.
 *
 * if the linked lists are the same length, you could just traverse through them at the same time. when they collide,
 * that's your intersection. when they are not the same length, we would like to just "chop off" - or ignore - those
 * nodes.
 */
public class Question {

  public static class Result {

    public LinkedListNode tail;
    public int size;

    public Result(LinkedListNode tail, int size) {
      this.tail = tail;
      this.size = size;
    }
  }

  public static Result getTailAndSize(LinkedListNode list) {
    if (list == null) {
      return null;
    }

    int size = 1;
    LinkedListNode current = list;
    while (current.next != null) {
      size++;
      current = current.next;
    }
    return new Result(current, size);
  }

  public static LinkedListNode getKthNode(LinkedListNode head, int k) {
    LinkedListNode current = head;
    while (k > 0 && current != null) {
      current = current.next;
      k--;
    }
    return current;
  }

  public static LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
    if (list1 == null || list2 == null) {
      return null;
    }

		/* Get tail and sizes. */
    Result result1 = getTailAndSize(list1);
    Result result2 = getTailAndSize(list2);

		/* If different tail nodes, then there's no intersection. */
    if (result1.tail != result2.tail) {
      return null;
    }

		/* Set pointers to the start of each linked list. */
    LinkedListNode shorter = result1.size < result2.size ? list1 : list2;
    LinkedListNode longer = result1.size < result2.size ? list2 : list1;

		/* Advance the pointer for the longer linked list by the difference in lengths. */
    longer = getKthNode(longer, Math.abs(result1.size - result2.size));

		/* Move both pointers until you have a collision. */
    while (shorter != longer) {
      shorter = shorter.next;
      longer = longer.next;
    }

		/* Return either one. */
    return longer;
  }

  public static void main(String[] args) {
    /* Create linked list */
    int[] vals = {-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8};
    LinkedListNode list1 = AssortedMethods.createLinkedListFromArray(vals);

    int[] vals2 = {12, 14, 15};
    LinkedListNode list2 = AssortedMethods.createLinkedListFromArray(vals2);

    list2.next.next = list1.next.next.next.next;

    System.out.println(list1.printForward());
    System.out.println(list2.printForward());

    LinkedListNode intersection = findIntersection(list1, list2);

    System.out.println(intersection.printForward());
  }

}
