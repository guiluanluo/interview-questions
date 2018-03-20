package Q10_08_Find_Duplicates;

import java.util.BitSet;

import CtCILibrary.AssortedMethods;

public class BitSetDemo {

  public static void checkDuplicates(int[] array) {

    BitSet bs = new BitSet(32000);

    for (int i = 0; i < array.length; i++) {
      int num = array[i];
      int num0 = num - 1; // bitset starts at 0, numbers start at 1
      if (bs.get(num0)) {
        System.out.println(num);
      } else {
        bs.set(num0);
      }
    }

    System.out.println("**bs size:" + bs.size());
  }

  public static void main(String args[]) {
    int[] array = AssortedMethods.randomArray(30, 1, 70);
    System.out.println(AssortedMethods.arrayToString(array));
    checkDuplicates(array);

    System.out.println("==== DEMO BitSet ====");
    BitSet bits1 = new BitSet(16);
    BitSet bits2 = new BitSet(16);

    // set some bits
    for (int i = 0; i < 16; i++) {
      if ((i % 2) == 0) {
        bits1.set(i);
      }

      if ((i % 5) != 0) {
        bits2.set(i);
      }
    }

    System.out.println("Initial pattern in bits1: ");
    System.out.println(bits1 + ", size:" + bits1.size());

    System.out.println("\nInitial pattern in bits2: ");
    System.out.println(bits2 + ", size:" + bits2.size());

    // AND bits
    bits2.and(bits1);
    System.out.println("\nbits2 AND bits1: ");
    System.out.println(bits2);

    // OR bits
    bits2.or(bits1);
    System.out.println("\nbits2 OR bits1: ");
    System.out.println(bits2);

    // XOR bits
    bits2.xor(bits1);
    System.out.println("\nbits2 XOR bits1: ");
    System.out.println(bits2);
  }
}
