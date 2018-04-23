package Q16_05_Factorial_Zeros;

/**
 * Factorial zeros: write an algorithm which computes the number of trailing zero in n factorial
 *
 * hint 584: how does a zero get into the result of n!? what does it mean?
 *
 * hint 710: each zero in n! corresponds to n being divisible by a factor of 10. what does that mean?
 *
 * hint 728: each factor of 10 in n! means n! divisible by 5 and 2.
 *
 * hint 732: can you count the number of factors of 5 and 2? do ou need to count both?
 *
 * hint 744: have you considered that 25 actually accounts for two factors of 5?
 *
 * Solution: a simple approach is to compute the factorial, and then count the number of tailing zeros by continuously
 * dividing by 10. the problem with this though is that the bounds of an int would be exceeded very quickly.
 *
 * consider a factorial like 6! = 1*2*3*4*5*6; a tailing zero is created with multiple of 10, and multiple of 10 are
 * created with pair of 5-multiples and 2-multiples.
 * 5! = 120, 6!=720, 10!=3628800
 */
public class QuestionA {

  public static int factorsOf5(int i) {
    int count = 0;
    while (i % 5 == 0) {
      count++;
      i /= 5;
    }
    return count;
  }

  public static int countFactZeros(int num) {
    int count = 0;
    for (int i = 2; i <= num; i++) {
      count += factorsOf5(i);
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
