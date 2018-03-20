package Q10_08_Find_Duplicates;

import CtCILibrary.AssortedMethods;

/**
 * Find duplicate: you have an array with all the numbers from 1 to N, where N is at most 32,000. The array may have
 * duplicate entries and you do not know what N is. with only 4 kilobytes of memory available, how would you print all
 * duplicate elements in the array?
 *
 * hint 289: can you use a bit vector?
 *
 * hint 315: consider implementing your own bit vector class. it's a good exercise and an important part of this
 * problem.
 *
 * Solution: we have a kilobytes of memory which means we can address up to 8*4*(2 power of 10)bits[1KB =2 powerOf 10]
 * note that 8*4*(2 power of 10)bits is greater than 32000. we can create a bit vector with 32000 bits, where each bit
 * represents one integer.
 *
 * using this bit vector, we can then iterate through the array, flagging each element v by set bit v to 1. when we come
 * cross a duplicate element, we print it.
 */
public class Question {

  public static void checkDuplicates(int[] array) {

    BitSet bs = new BitSet(32000);
    //  System.out.println("**bs size:" + bs.getBitSetSize());

    for (int i = 0; i < array.length; i++) {
      int num = array[i];
      int num0 = num - 1; // bitset starts at 0, numbers start at 1
      if (bs.get(num0)) {
        System.out.println(num);
      } else {
        bs.set(num0);
      }
    }

    System.out.println("**bs size:" + bs.getBitSetSize());
  }

  public static void main(String[] args) {
    int[] array = AssortedMethods.randomArray(30, 1, 70);
    System.out.println(AssortedMethods.arrayToString(array));
    checkDuplicates(array);
  }

}
