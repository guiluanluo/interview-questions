package Q5_02_Binary_to_String;

/*
 * Binary to string: given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print the binary
 * representation. if the number cannot be represented accurately in binary with at most 32 characters, print "ERROR".
 *
 * hint 143: to wrap your head around the problem, try thinking about how you'd do it for integers
 *
 * hint 167: in a number like .893(in base 10), what does each digit signify? what then does each digit in .10010
 * signify in base 2
 *
 * hint 173: a number such as .893(in base 10 indicates 8*10(-1)+9*10(-2)+3*10(-3)). translate this system into base 2
 *
 * hint 269: how would you get the first digit in .893? if you multiplied by 10, you'd shift the values over to get
 * 8.93. what happens if you multiply by 2?
 *
 * hint 297: think about what happens for values that can't be represented accurately in binary.
 *
 * Solution: Note - when otherwise ambiguous, we'll use the subscripts x2 and x10 to indicate whether x is in base 2 or
 * base 10.
 *
 * first, let's start off by asking ourselves what a non-integer number in binary looks like. by analogy to a decimal
 * number, then binary number 0.101 would look like: 0.101 = 1*(1/2) + 0*(1/4) + 1*(1/8)
 *
 * to print the decimal part, we can multiply by 2 and check if 2n is greater than or equal to 1. this is essentially
 * "shifting" the fractional sum. that is:
 *       r  = 2(base10)n
 *          = 2(base10) * 0.101(base2)
 *          = 1*(1/1)+ 1*(1/2) + 0*(1/4)
 *          = 1.01(base2)
 * if r >1, then we know that n had a 1 right after the decimal point. by doing this continuously, we can check every
 * digital
 */
public class Question {

  public static String printBinary(double num) {
    if (num >= 1 || num <= 0) {
      return "ERROR";
    }

    StringBuilder binary = new StringBuilder();
    binary.append(".");

    while (num > 0) {
      /* Setting a limit on length: 32 characters */
      if (binary.length() > 32) {
        return "ERROR";
      }

      double r = num * 2;
      if (r >= 1) {
        binary.append(1);
        num = r - 1;
      } else {
        binary.append(0);
        num = r;
      }
    }
    return binary.toString();
  }

  public static String printBinary2(double num) {
    if (num >= 1 || num <= 0) {
      return "ERROR";
    }

    StringBuilder binary = new StringBuilder();
    double frac = 0.5;
    binary.append(".");
    while (num > 0) {
      /* Setting a limit on length: 32 characters */
      if (binary.length() >= 32) {
        return "ERROR";
      }
      if (num >= frac) {
        binary.append(1);
        num -= frac;
      } else {
        binary.append(0);
      }
      frac /= 2;
    }
    return binary.toString();
  }

  public static void main(String[] args) {
    String bs = printBinary(.125);
    System.out.println(".125: printBinary: " + bs + "\n");

    for (int i = 0; i < 1000; i++) {
      double num = i / 1000.0;
      String binary = printBinary(num);
      String binary2 = printBinary2(num);
      if (!binary.equals("ERROR") || !binary2.equals("ERROR")) {
        System.out.println(num + " : " + binary + " " + binary2);
      }
    }
  }
}
