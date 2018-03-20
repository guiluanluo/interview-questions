package Q17_06_Count_of_2s;

/**
 * Count of 2s: write a method to count the number of 2s between 0 and n.
 *
 * Brute force solution is easy to understand, but Improve solution is not!!
 *
 * Brute force solution: remember that interviewers want to see how you are approaching a problem. offering a brute
 * force solution is a great way to start.
 *
 * Improve solution: rather than looking at the problem by range of number, we can look at the problem digit by digit.
 * picture a sequence of numbers
 */
public class Question {


  public static int count2sInRange(int number) {
    int count = 0;
    int len = String.valueOf(number).length();
    for (int digit = 0; digit < len; digit++) {
      count += count2sInRangeAtDigit(number, digit);
    }
    System.out.println("\n number:" + number + ", len:" + len + ", count:" + count);
    return count;
  }

  //count the number of '2' digital between 0 to
  public static int count2sInRangeAtDigit(int number, int d) {

    int powerOf10 = (int) Math.pow(10, d);
    int nextPowerOf10 = powerOf10 * 10;
    int right = number % powerOf10;

    int roundDown = number - number % nextPowerOf10;
    int roundUp = roundDown + nextPowerOf10;

    int digit = (number / powerOf10) % 10;
    if (digit < 2) { // if the digit in spot digit is
      return roundDown / 10;
    } else if (digit == 2) {
      return roundDown / 10 + right + 1;
    } else {
      return roundUp / 10;
    }
  }

  /* Alternate, messier, solution */
  public static int count2sR(int n) {
    // Example: n = 513

    // Base case
    if (n == 0) {
      return 0;
    }

    // Split apart 513 into 5 * 100 + 13. [Power = 100; First = 5; Remainder = 13]
    int power = 1;
    while (10 * power < n) {
      power *= 10;
    }
    int first = n / power;
    int remainder = n % power;

    System.out.println("**n:" + n + ", power:" + power + ", first:" + first + ", remainder:" + remainder);

    // Counts 2s from first digit
    int nTwosFirst = 0;
    if (first > 2) {
      nTwosFirst += power;
    } else if (first == 2) {
      nTwosFirst += remainder + 1;
    }

    // Count 2s from all other digits
    int nTwosOther = first * count2sR(power - 1) + count2sR(remainder);

    return nTwosFirst + nTwosOther;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 101; i++) {
      int v1 = count2sR(i);
      System.out.println("Between 0 and " + i + ": " + v1 );
    }

//    for (int i = 0; i < 1000; i++) {
//      int v1 = count2sR(i);
//      int v2 = count2sInRange(i);
//      System.out.println("Between 0 and " + i + ": " + v1 + " " + v2);
//    }
  }

}