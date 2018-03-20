package Q2_08_Loop_Detection;

import java.util.HashSet;
import java.util.Set;

import CtCILibrary.LinkedListNode;

/*
 * Loop Detection: given a circular linked list, implement an algorithm that returns the node at the beginning of the
 * loop.
 *
 * Definition: circular linked list: a (corrupt) linked list in which a node's next pointer points to an earlier node,
 * so as to make a loop in the linked list.
 *
 * Example: input: A->B->C->D->E->C [ the same C as earlier]
 * output: C
 *
 * hint 50: there are really two parts to this problem. first, detect if the linked list has a loop. second, figure out
 * where the loop starts
 *
 * hint 69: to identify if there's a cycle, try the "runner" approach described on page 93. have one pointer move faster
 * that the other
 *
 * hint 83: you can use two pointers, one moving twice as fast as the other. if there is a cycle, then two pointers will
 * collide. they will land at the same location at the same time. where do they land? why there?
 *
 * hint 90: if you haven't identified the pattern of where the two pointers start, try this: use the linked list
 * 1->2->3->4->5->6->7->8->9->?, where the ? links to another node. try making the ? the first node(that is, the 9
 * points to the 1 such that the entire linked list is a loop). then make the ? the node 2. then the node 3. then the
 * node 4. what is the pattern? can you explain why this happens?
 *
 * Solution1: use a hash table to store the node while iterate through the linked list. it takes O(N) time, O(N) space
 *
 * Solution2: part 1 - detect if linked list has a loop: an easy way to detect if a linked list has a loop is through
 * the FastRunner/SlowRunner approach. FastRunner move two steps at a time, while SlowRunner moves one step. Much like
 * two cars racing around a track at different steps, they must eventually meet!!!
 *
 * part 2 - when do they collide? let's assume that the linked list has a "non-looped" part of size k. if we apply our
 * algorithm from part 1, when will FastRunner and SlowRunner collide? We know that for every p steps that SlowRunner
 * takes, FastRunner has taken 2p steps. therefore, when SlowRunner enters the looped portion after k steps, FastRunner
 * has taken 2k steps total and must be 2k-k steps, or k steps, into the looped portion!! since k might be much larger
 * than the loop length, we should actually write this as mod(k, LOOP_SIZE) step, which we will denote as k.
 *
 * at each sub-sequence step, FastRunner and SlowRunner get either one step farther away or one step closer, depending
 * on your perspective. that is, because we are in a circle, when A moves q steps away from B, it is also moving q
 * closer to B. So now we know the following facts:
 * 1: SlowRunner is 0 steps into the loop; FastRunner is k steps into the loop
 * 2: SlowRunner is k steps behind the FastRunner; FastRunner is (LOOP_SIZE-k) steps behind SlowRunner
 * 3: FastRunner catch up to SlowRunner at a rate of 1 step per unit of time.
 * so, when do they meet? then they meet after (LOOP_SIZE-k) steps,. at this point, they will be k steps before the head
 * of the loop. Let's call this point CollisionSpot.
 *
 *  speed of FastRunner/SlowRunner = 2 : (((LOOP_SIZE-k)+x)/t) / (x/t) = 2 ==> (LOOP_SIZE-k)+x = 2x ==> x = LOOP_SIZE-k
 *
 * part 3 - how do you find the start of the loop? we now know that CollisionSpot is k nodes before the star of the loop.
 * Because k= mod(k, LOOP_SIZE) (or k = k + M*LOOP_SIZE, for any integer M), it is also correct to say that it is k node
  * from the loop start. For example, if node N is 2 nodes into a 5 node loop, it also correct to say that it is 7, 12,
  * or even 397 nodes into the loop.
  *
  *
 */
public class Question {

  /**
   * this algorithm takes O(N), and O(N) space
   */
  public static LinkedListNode FindBeginning_lucy(LinkedListNode head) {
    Set<LinkedListNode> nodeSet = new HashSet<>();
    LinkedListNode current = head;

    while (current != null) {
      LinkedListNode next = current.next;
      current.next = null;
      if (!nodeSet.contains(current)) {
        nodeSet.add(current);
      } else {
        return current;
      }
      current = next;
    }
    return null;
  }


  public static LinkedListNode FindBeginning(LinkedListNode head) {
    LinkedListNode slow = head;
    LinkedListNode fast = head;

    // Find meeting point
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        break;
      }
    }

    // Error check - there is no meeting point, and therefore no loop
    if (fast == null || fast.next == null) {
      return null;
    }

    /**
     * Move slow to Head. Keep fast at Meeting Point. Each are k steps from the Loop Start.
     * If they move at the same pace, they must meet at Loop Start.
     */
    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    // Both now point to the start of the loop.
    return fast;
  }

  public static void main(String[] args) {
    int list_length = 100;
    int k = 10;

    // Create linked list
    LinkedListNode[] nodes = new LinkedListNode[list_length];
    for (int i = 0; i < list_length; i++) {
      nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
    }

    // Create loop;
    nodes[list_length - 1].next = nodes[list_length - k];

    LinkedListNode loop = FindBeginning(nodes[0]);
    if (loop == null) {
      System.out.println("No Cycle.");
    } else {
      System.out.println(loop.data);
    }

    System.out.println("===call FindBeginning_lucy() ====");
    LinkedListNode loop1 = FindBeginning_lucy(nodes[0]);
    if (loop1 == null) {
      System.out.println("No Cycle.");
    } else {
      System.out.println(loop1.data);
    }
  }

}
