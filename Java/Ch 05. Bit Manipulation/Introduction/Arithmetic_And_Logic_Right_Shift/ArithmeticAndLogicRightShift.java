package Introduction.Arithmetic_And_Logic_Right_Shift;


public class ArithmeticAndLogicRightShift {

  public static int repeatedArithmeticRightShift(int x, int count) {
    for (int i = 0; i < count; i++) {
      x >>= 1; // arithmetic shift by 1
    }
    return x;
  }


  public static int repeatedLogicalRightShift(int x, int count) {
    for (int i = 0; i < count; i++) {
      x >>>= 1; // logical shift by 1
    }
    return x;
  }

  public static void main(String[] args) {
    int x = 8;
    int count = 2;

    int arithmeticShiftResult = repeatedArithmeticRightShift(x, count);
    int logicalShiftResult = repeatedLogicalRightShift(x, count);

    System.out.println(x + ": " + Integer.toBinaryString(x));

    System.out.println("repeatedArithmeticRightShift(x:" + x + ", count:" + count + "): " +
        Integer.toBinaryString(arithmeticShiftResult));

    System.out.println("repeatedLogicalRightShift(x:" + x + ", count:" + count + "): " +
        Integer.toBinaryString(logicalShiftResult));
  }
}
