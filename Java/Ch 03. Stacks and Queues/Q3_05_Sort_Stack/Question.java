package Q3_05_Sort_Stack;

import java.util.Stack;

import CtCILibrary.AssortedMethods;

/**
 * Sort stack: write a program to sort a stack such that the smallest items are on the top. you can use an additional
 * temporary stack, but you may not copy the elements into any other data structure (such as an array). the stack
 * supports the following operations: push, pop, peek, and isEmpty
 *
 * hint 15: one way of sorting an array is to iterate through the array and insert each element into a new array in
 * sorted order. can you do this with a stack?
 *
 * hint 32: imagine your secondary stack is sorted. can you insert element into it in sorted order? you might need some
 * extra storage. what could you use for extra storage?
 *
 * hint 43: keep the secondary stack in sorted order, with the biggest elements on the top. use the primary stack for
 * additional storage.
 *
 * this algorithm is O(N*N) time and O(N) space. if we were allowed to use unlimited stacks, we could implement a
 * modified quicksort() or mergsort().
 */
public class Question {

  public static Stack<Integer> mergesort(Stack<Integer> inStack) {

    if (inStack.size() <= 1) {
      return inStack;
    }

    Stack<Integer> left = new Stack<Integer>();
    Stack<Integer> right = new Stack<Integer>();
    int count = 0;
    while (inStack.size() != 0) {
      count++;
      if (count % 2 == 0) {
        left.push(inStack.pop());
      } else {
        right.push(inStack.pop());
      }
    }

    left = mergesort(left);
    right = mergesort(right);

    while (left.size() > 0 || right.size() > 0) {
      if (left.size() == 0) {
        inStack.push(right.pop());
      } else if (right.size() == 0) {
        inStack.push(left.pop());
      } else if (right.peek().compareTo(left.peek()) <= 0) {
        inStack.push(left.pop());
      } else {
        inStack.push(right.pop());
      }
    }

    Stack<Integer> reverseStack = new Stack<Integer>();
    while (inStack.size() > 0) {
      reverseStack.push(inStack.pop());
    }

    return reverseStack;
  }

  public static void sort(Stack<Integer> s) {
    Stack<Integer> r = new Stack<Integer>();

    while (!s.isEmpty()) {

      /* Insert each element in s in sorted order into r. */
      int tmp = s.pop();

      System.out.println("\ntmp: " + tmp + ", s: " + s.toString() + ", r: " + r.toString());

      while (!r.isEmpty() && r.peek() > tmp) {
        System.out.println("** s.push: " + r.peek());
        s.push(r.pop());
      }

      System.out.println("**** r.push: " + tmp);
      r.push(tmp);
    }

		/* Copy the elements back. */
    while (!r.isEmpty()) {
      s.push(r.pop());
    }
  }

  public static void main(String[] args) {
    Stack<Integer> s = new Stack<Integer>();
    for (int i = 0; i < 10; i++) {
      int r = AssortedMethods.randomIntInRange(0, 1000);
      s.push(r);
    }
    System.out.println("s: " + s.toString());

    sort(s);

    System.out.println("sorted s: " + s.toString());

  }
}
