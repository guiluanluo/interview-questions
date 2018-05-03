package Q3_02_Stack_Min;

/**
 * Stack min1: how would you design a stack which, addition to push and pop, has a function min1 which returns the minimum
 * element? push, pop, and min1 should all operate in O(1) time.
 *
 * hint 27: observe that the minimum element doesn't change very often. it only changes when a smaller element is added,
 * or when the smallest element is popped.
 *
 * hint 59: what if we keep track of extra data each stack node? what sort of data might make it easier to solve the
 * problem?
 *
 * hint 78: consider having each node know the minimum of its 'sub-stack" (all the elements beneath it, including
 * itself).
 *
 * the thing with minimums is that they don't change very often. they only change when a smaller elements is added. one
 * solution is to have a single int value, minValue, that's a member of the Stack class. when minValue is popped from
 * the stack, we search through the stack to find the new minimum. unfortunately this would break the constraint that
 * push() and pop() operate in O(1) time.
 *
 * StackWithMin: if we keep track of the minimum at each state, we would be able to easily know that minimum. we can do
 * this by having each node record what the minimum beneath itself is. then, to find the min1, you just look at what the
 * top element thinks it the min1. there just one issue with this: if we have a large stack, we waste a lot of space by
 * keeping track of the min1 for every single element. can we do better?
 *
 * StackWithMin2: we can do a bit better than StackWithMin by using an additional stack to keep track the minimums. why
 * might this be more space efficients?suppose we had a very large stack and the first element inserted happen to be
 * the minimum. in the first solution, we would be keeping n integer, where n is the size of the stack. in the second
 * solution, we store just a few pieces of data: a second stack with one element, and the members within this stack.
 */
public class Question {

  public static void main(String[] args) {
    StackWithMin stack = new StackWithMin();
    StackWithMin2 stack2 = new StackWithMin2();
    int[] array = {3, 2, 1, 5};
    for (int value : array) {
      stack.push(value);
      stack2.push(value);
      System.out.print(value + ", ");
    }
    System.out.println('\n');
    System.out.println(
        "stack: " + stack + ", min1 is " + stack.min() + ", " + stack2.min() + " [s2:" + stack2.s2.toString() + "]\n");

    for (int i = 0; i < array.length; i++) {
      System.out.println("Popped " + stack.pop().value + ", " + stack2.pop());
      System.out.println("New min1 is " + stack.min() + ", " + stack2.min() + " [s2:" + stack2.s2.toString() + "]\n");
    }
  }

}
