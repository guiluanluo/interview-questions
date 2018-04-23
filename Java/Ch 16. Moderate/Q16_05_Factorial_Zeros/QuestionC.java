package Q16_05_Factorial_Zeros;

/**
 * we can make it a little more efficient by directly counting the factors of 5. using this approach, we would fist
 * count the number of multiples of 5 between 1 and n (which is n/5), then the number of multiples of 25(n/25), then
 * 125, and so on.
 */
public class QuestionC {

  public static int countFactZeros(long num) {
    int count = 0;
    if (num < 0) {
      System.out.println("Factorial is not defined for negative numbers");
      return 0;
    }

    double temp = (double) num / 10;
    int intTemp = (int) temp;
    double subtract = intTemp - temp;
    while (subtract == 0) {
      count++;
      num = num / 10;
      temp = (double) num / 10;
      intTemp = (int) temp;
      subtract = intTemp - temp;
    }

    return count;
  }

  public static int factorial(int num) {
    if (num == 1) {
      return 1;
    } else if (num > 1) {
      return num * factorial(num - 1);
    } else {
      return -1; // Error
    }
  }

  public static void main(String[] args) {
    for (int i = 1; i < 12; i++) {
      long value = factorial(i);
      System.out.println(i + "! (or " + value + ") has " + countFactZeros(value) + " zeros");
    }
  }
}
