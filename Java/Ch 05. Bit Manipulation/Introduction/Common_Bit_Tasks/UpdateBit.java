package Introduction.Common_Bit_Tasks;


public class UpdateBit {

  /**
   * to set the ith bit to a value v, we first clear the bit at position i by using a mask that looks like 1110 1111.
   * then, we shift the intended value, v, left by i bits. this will create a number with bit i equal to v and all other
   * bits equal to 0. finally, we OR these two numbers, updating the ith bit if v is 1 and leaving it as 0 otherwise.
   */
  public static int updateBit(int num, int i, boolean bitIs1) {
    int value = bitIs1 ? 1 : 0;
    int mask = ~(1 << i);
    return (num & mask) | (value << i);
  }

  public static void main(String[] args) {
    int num = 8;
    int i = 2;

    System.out.println(num + ": " + Integer.toBinaryString(num));
    System.out.println("~(1 << 2): " + Integer.toBinaryString(~(1 << i)));
    System.out.println(num + " &  (~(1 << 2)): " + Integer.toBinaryString(~(1 << i)));
    System.out.println(num + "<<" + i + ": " + Integer.toBinaryString(num << i));
    System.out.println("(8 & (~(1<<2))) | (8<<2):" + Integer.toBinaryString(updateBit(num, i, true)));

    System.out.println("updateBit " + i + " result: " + Integer.toBinaryString(updateBit(num, i, true)));
  }

}
