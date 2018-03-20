package Q16_01_Number_Swapper;

/**
 * //TODO: understand +,-, *, / using bit shifting , 02/20/2018, LL
 * Number swapper: write a function tos wap a number in place (that is, without temporary variables)
 *
 * hint 491: try picturing the two numbers, a and b, on a number line.
 *
 * hint 715: let diff be the difference between a and b. can you use diff in some way? then can you get rid of this
 * temporary variable?
 *
 * hint 736: you could also try using XOR
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
    System.out.println(a + ":" + Integer.toBinaryString(a) + ", " + b + ":" + Integer.toBinaryString(b));

    a = a ^ b;
    System.out.println(a + "^" + b + ": " + a + ": " + Integer.toBinaryString(a));

    b = a ^ b;
    System.out.println(a + "^" + b + ": " + b + ": " + Integer.toBinaryString(b));

    a = a ^ b;
    System.out.println(a + "^" + b + ": " + a + ": " + Integer.toBinaryString(a));

    System.out.println("swap_opt(), a: " + a + ", b: " + b);

  }

  public static void main(String[] args) {
    int a = 14;
    int b = 5;
    System.out.println("a: " + a + ", b: " + b);

    swap(a, b);
    swap_opt(a, b);
  }
}
