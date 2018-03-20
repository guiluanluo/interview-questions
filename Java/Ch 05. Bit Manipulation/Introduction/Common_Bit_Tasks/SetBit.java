package Introduction.Common_Bit_Tasks;


public class SetBit {

  /**
   * this method shifts 1 over by i bits, create a value that looks like 0001 0000. by performing an OR with num, the
   * value at bit i will change. all other bits of the mask are zero and will not affect num.
   */
  public static int setBit(int num, int i) {
    return num | (1 << i);
  }

  public static void main(String[] args) {
    int num = 10;
    int i = 2;

    System.out.println(num + ": " + Integer.toBinaryString(num));
    System.out.println("(1 << 2): " + Integer.toBinaryString((1 << i)));
    System.out.println(num + " | (1 << 2):" + Integer.toBinaryString((num | (1 << i))));

    System.out.println("setBit " + i + " result: " + Integer.toBinaryString(setBit(num, i)));
  }

}
