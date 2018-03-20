package Introduction.Common_Bit_Tasks;


public class GetBit {

  /**
   * this method shifts 1 over by i bits, create a value that looks like 0001 0000. by performing an AND with num, we
   * clear all bits other than the bit at bit i. finally, w compare that to 0. if that new value is not zero, then bit i
   * must have a 1. otherwise, bit i is 0.
   */
  public static boolean getBit(int num, int i) {
    return ((num & (1 << i)) != 0);
  }

  public static void main(String[] args) {
    int num = 10;
    int i = 2;

    System.out.println(num + ": " + Integer.toBinaryString(num));
    System.out.println("(1 << 3): " + Integer.toBinaryString((1 << i)));
    System.out.println(num + " & (1 << 3):" + Integer.toBinaryString((num & (1 << i))));

    boolean flag = getBit(num, i);
    System.out.println("flag:" + flag);

  }

}
