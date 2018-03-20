package Q16_05_Factorial_Zeros;

/**
 * we can make it a little more efficient by directly counting the factors of 5. using this approach, we would fist
 * count the number of multiples of 5 between 1 and n (which is n/5), then the number of multiples of 25(n/25), then
 * 125, and so on.
 */
public class QuestionB {

  public static int countFactZeros(int num) {
    int count = 0;
    if (num < 0) {
      System.out.println("Factorial is not defined for negative numbers");
      return 0;
    }

    for (int i = 5; num / i > 0; i *= 5) {
      count += num / i;
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
      System.out.println(i + "! (or " + factorial(i) + ") has " + countFactZeros(i) + " zeros");
    }
  }
}
