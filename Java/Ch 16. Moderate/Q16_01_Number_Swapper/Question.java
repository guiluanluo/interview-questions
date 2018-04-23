package Q16_01_Number_Swapper;

/**
 * //TODO: understand +,-, *, / using bit shifting , 02/20/2018, LL Number swapper: write a function to swap a number in
 * place (that is, without temporary variables)
 *
 * hint 491: try picturing the two numbers, a and b, on a number line.
 *
 * hint 715: let diff be the difference between a and b. can you use diff in some way? then can you get rid of this
 * temporary variable?
 *
 * hint 736: you could also try using XOR
 *
 * It is the Bitwise xor operator in java which results 1 for different value of bit (ie 1 ^ 0 = 1) and 0 for same value
 * of bit (ie 0 ^ 0 = 0) when a number is written in binary form
 */
public class Question {

  public static void swap(int a, int b) {
    // Example for a = 9, b = 4
    a = a - b; // a = 9 - 4 = 5
    b = a + b; // b = 5 + 4 = 9
    a = b - a; // a = 9 - 5

    System.out.println("swap(), a: " + a + ", b: " + b);
  }

  public static void swap_opt(int a, int b) {
    System.out.println("\n swap_opt(" + a + ", " + b + ")");
    System.out.println(a + ":" + Integer.toBinaryString(a) + ", " + b + ":" + Integer.toBinaryString(b));

    //14:1110, 5:0101 ==>11: 1011
    a = a ^ b;
    System.out.println(a + "^" + b + ": " + a + ": " + Integer.toBinaryString(a));

    // 11: 1011 , 14:1110 ==> 5: 0101
    b = a ^ b;
    System.out.println(a + "^" + b + ": " + b + ": " + Integer.toBinaryString(b));

    //11: 1011, 5: 0101 ==> 14:1110
    a = a ^ b;
    System.out.println(a + "^" + b + ": " + a + ": " + Integer.toBinaryString(a));

    System.out.println("swap_opt(), a: " + a + ", b: " + b);

  }

  public static void main(String[] args) {
    int a = 14;
    int b = 5;
    System.out.println("a: " + a + ", b: " + b);

    swap(a, b);

    a = 14;
    b = 5;
    swap_opt(a, b);
  }
}
