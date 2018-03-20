package Q3_01_Three_in_One;

import CtCILibrary.AssortedMethods;

/**
 * Three in One: describe how you could use a single array to implement three stacks
 *
 * hint 2: a stack is simply a data structure in which the most recently added elements are removed first. can you
 * simulate a single stack using an array? remember that there are many possible solution, and there trade-offs of each
 *
 * hint 12: we could simulate three stacks in an array by just allocating the first third of the array to the first
 * stack, the second third to the second stack, and the final third to the third stack. one might actually be much
 * bigger than the others, though. can we be more flexible with the divisions?
 *
 * hint 38: if you want to allow for flexible divisions, yu can shift stacks around. can you ensure that all available
 * capacity is used?
 *
 * hint 58: try thinking about the array as circular, such that the end of the array 'wraps around' to the start of the
 * array.
 *
 * Solution A uses Fixed Multiple Stack: divide the array in three equal parts and allow the individual stack to grow in
 * that limited space.
 */
public class QuestionA {

  public static void printStacks(FixedMultiStack stacks) {
    System.out.println(AssortedMethods.arrayToString(stacks.getValues()));
  }

  public static void main(String[] args) throws Exception {
    FixedMultiStack stacks = new FixedMultiStack(4);
    printStacks(stacks);
    stacks.push(0, 10);
    printStacks(stacks);
    stacks.push(1, 20);
    printStacks(stacks);
    stacks.push(2, 30);
    printStacks(stacks);

    stacks.push(1, 21);
    printStacks(stacks);
    stacks.push(0, 11);
    printStacks(stacks);
    stacks.push(0, 12);
    printStacks(stacks);

    stacks.pop(0);
    printStacks(stacks);

    stacks.push(2, 31);
    printStacks(stacks);

    stacks.push(0, 13);
    printStacks(stacks);
    stacks.push(1, 22);
    printStacks(stacks);

    stacks.push(2, 31);
    printStacks(stacks);
    stacks.push(2, 32);
    printStacks(stacks);
  }
}
