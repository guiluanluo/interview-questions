package Introduction;

/*
 * How does recursive work?
 *
 * factorial(5)
 *   factorial(4)
 *     factorial(3)
 *        factorial(2)
 *           factorial(1)
 *              return 1
 *        return 2*1 = 2
 *     return 3*2 = 6
 *   return 4*6 = 24
 * return 5*24 = 120
 *
 * factorial(3) -> factorial(2) -> factorial(1), Which evaluates as
 *
 * factorial(1) = 1                                        = 1
 * factorial(2) = 2 * factoral(1)                          = 2 * 1
 * factorial(3) = 3 * (2 * factorial(2) * (factorial(1))   = 3 * 2 * 1
 */
public class Recursive {

  public static int factorial(int x) {
    System.out.println("factorial(" + x + ")");
    if (x == 1) {
      System.out.println("factorial(" + x + "), value:" + 1);
      return 1;
    } else {
      int value = x * factorial(x - 1);
      System.out.println("factorial(" + x + "), value:" + value);
      return value;
    }
  }

  public static void main(String[] args) {
    // Try routine
    factorial(3);
  }

}
