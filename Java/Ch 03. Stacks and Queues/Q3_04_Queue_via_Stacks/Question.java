package Q3_04_Queue_via_Stacks;

import java.util.LinkedList;
import java.util.Queue;

import CtCILibrary.AssortedMethods;

/**
 * Queue via Stack: implement a MyQueue class which implement a queue using two stacks.
 *
 * hint 98: the major difference between a queue and a stack is the order of elements. a queue removes the oldest item
 * and a stack removes the newest item. how could you remove the oldest item from a stack if you only had access to the
 * newest item?
 *
 * hint 114: we can remove the oldest item from a stack by repeatedly removing he newest item (inserting those into the
 * temporary stack) until we get down to one element. then, after we've retrieved the newest item, putting all the
 * elements back. the issue with this is that doing several pops in a row will require O(N) work each time. can we
 * optimize for scenarios where we might do several pops in a row?
 */
public class Question {

  public static void main(String[] args) {
    MyQueue<Integer> my_queue = new MyQueue<Integer>();

    // Let's test our code against a "real" queue
    Queue<Integer> test_queue = new LinkedList<Integer>();

    for (int i = 0; i < 100; i++) {
      int choice = AssortedMethods.randomIntInRange(0, 10);
      if (choice <= 5) { // enqueue
        int element = AssortedMethods.randomIntInRange(1, 10);
        test_queue.add(element);
        my_queue.add(element);
        System.out.println("Enqueued " + element);
      } else if (test_queue.size() > 0) {
        int top1 = test_queue.remove();
        int top2 = my_queue.remove();
        if (top1 != top2) { // Check for error
          System.out.println("******* FAILURE - DIFFERENT TOPS: " + top1 + ", " + top2);
        }
        System.out.println("Dequeued " + top1);
      }

      if (test_queue.size() == my_queue.size()) {
        if (test_queue.size() > 0 && test_queue.peek() != my_queue.peek()) {
          System.out
              .println("******* FAILURE - DIFFERENT TOPS: " + test_queue.peek() + ", " + my_queue.peek() + " ******");
        }
      } else {
        System.out.println("******* FAILURE - DIFFERENT SIZES ******");
      }
    }
  }
}
