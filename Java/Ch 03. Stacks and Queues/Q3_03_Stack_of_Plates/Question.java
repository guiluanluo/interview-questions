package Q3_03_Stack_of_Plates;

/**
 * Stack of plates: imagine a (literal) stack of plates. if the stack gets too high, it might topple. therefore, in real
 * life, we would likely start a new stack when the previous stack exceeds some threshold. implement a data structure
 * SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the
 * previous one exceeds capacity.SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack
 * (that is pop() should return the same values as it would if there were just a single stack)
 *
 * TODO: will revisit it after review the recursive questions
 * Follow Up: implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
 *
 * hint 64: you will need to keep track of the size of each substack. when one stack is full, you may need to create a
 * new stack.
 *
 * hint 81: popping an element at a specific substack will mean that some stacks are not at full capacity. is this an
 * issue? there is no right answer, but you should think about hoe to handle this
 */
public class Question {

  public static void main(String[] args) {
    int capacity_per_substack = 5;
    SetOfStacks set = new SetOfStacks(capacity_per_substack);
    for (int i = 0; i < 34; i++) {
      set.push(i);
    }
    for (int i = 0; i < 35; i++) {
      System.out.println("Popped " + set.pop());
    }
  }
}
