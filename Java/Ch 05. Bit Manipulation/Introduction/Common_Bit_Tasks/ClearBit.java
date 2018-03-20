package Introduction.Common_Bit_Tasks;


public class ClearBit {

  /**
   * this method operates in almost the reverse of setBit(). fist, we create a number like 1110 1111 by creating the
   * reverse of it (0001 0000) and negating it. then, we perform an AND with num. this will clear the ith bit and leave
   * the remainder unchanged.
   */
  public static int clearBit(int num, int i) {
    int mask = ~(1 << i);
    return num & mask;
  }

  /**
   * the most significant bit(MSB) is sometimes referred to as the left-most bit due to the convention in positional
   * notation of writing more significant digits further to the left
   *
   * the least significant bit (LSB) is sometimes referred to as the right-most bit, due to the convention in
   * positional notation of writing less significant digits further to the right
   *
   * to clear all bits from the most significant bit through i (inclusive), we create a mask with a 1 at the ith bit (1
   * << i). then, we subtract 1 from it, giving us a sequence of 0s followed by i 1s. we then AND our number with this
   * mask to leave just the last i bits.
   */
  public static int cleanBitsMSBthroughI(int num, int i) {
    int mask = (1 << i) - 1;
    return num & mask;
  }

  /**
   * to clear all bits from i through 0 (inclusive), we take a sequence of all 1s (which is -1) and shift it left by i +
   * 1 bit. this gives us a sequence of 1s (in the most significant bits) followed by i 0 bits.
   */
  public static int cleanBitsIthrough0(int num, int i) {
    int mask = (-1 << (i + 1));
    return num & mask;
  }

  public static void main(String[] args) {

    int num = 15;
    int i = 2;

    System.out.println("1: " + Integer.toBinaryString(1));
    System.out.println("-1: " + Integer.toBinaryString(-1));
    System.out.println("-2: " + Integer.toBinaryString(-2));
    System.out.println("-3: " + Integer.toBinaryString(-3));
    System.out.println("mask = (-1 << (" + i + " + 1)): " + Integer.toBinaryString((-1 << (i + 1))));
    System.out.println("\n");

    System.out.println(num + ": " + Integer.toBinaryString(num));
    System.out.println("(1 << 2): " + Integer.toBinaryString((1 << i)));
    System.out.println("~(1 << 2): " + Integer.toBinaryString(~(1 << i)));
    System.out.println(num + " &  ~(1 << 2):" + Integer.toBinaryString((num & (~(1 << i)))));

    System.out.println(num + ": " + Integer.toBinaryString(num) + ", clearBit " + i + ", result: " + Integer
        .toBinaryString(clearBit(num, i)));

    System.out.println("\n");
    System.out.println("(1 << 2): " + Integer.toBinaryString((1 << i)));
    System.out.println("(1 << 2) - 1: " + Integer.toBinaryString((1 << i) - 1));
    System.out.println(num + ": " + Integer.toBinaryString(num) + ", cleanBitsMSBthroughI " + i + " result: "
        + Integer.toBinaryString(cleanBitsMSBthroughI(num, i)));

    System.out.println("\n");
    System.out.println("(1 << 2): " + Integer.toBinaryString((1 << i)));
    System.out.println("(-1 << (2 + 1)): " + Integer.toBinaryString((-1 << (i + 1))));
    System.out.println(num + ": " + Integer.toBinaryString(num) + ", cleanBitsIthrough0 " + i + " result: "
        + Integer.toBinaryString(cleanBitsIthrough0(num, i)));
  }

}
