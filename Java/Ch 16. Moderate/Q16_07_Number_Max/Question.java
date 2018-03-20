package Q16_07_Number_Max;

/**
 * Number max: write a method that finds the maximum of two numbers. you should not use if-else or any other comparison
 * operator
 *
 * hint 472: let k be 1 if a>b and 0 otherwise. if you were given k, could you return the max (without a comparison or
 * if-else logic)?
 *
 * hint 512: if k were 1 when a>b and 0 otherwise, then you could return a*k + b*(not k). but how do you create k?
 *
 * hint 706: when a>b, then a-b>0. can you get the sign bit of a-b?
 *
 * hint 727: have you considered how to handle integer overflow in a-b?
 *
 * Solution: a common way of implementing a max() function is to look at the sign of (a-b). in this case, we can not use
 * a comparison operator on this, but we can use multiplication. let k equal to the sign of (a-b) such that if a-b>0,
 * then k is 1, else k-0. let q be the inverse of k. the issue of this solution is when a-b overflows. suppose, for
 * example, a is INT_MAX-2, b is -15. in this case, (a-b) will be greater that INT_MAX and will overflow, resulting in a
 * negative value.
 *
 * when does (a-b) overflow? it will overflow only when a is positive and b is negative, or the other way around. it may
 * be difficult to specially detect the overflow condition, but we can detect when a and b have different signs. Note
 * that if a and b have different signs, then we want k to equal sign(a).
 *
 * the logic looks like: if a and b have different signs ->let k = sign(a), else let k = sign(a-b)
 */
public class Question {

  /* Flips a 1 to a 0 and a 0 to a 1 */
  public static int flip(int bit) {
    return 1 ^ bit;
  }

  /**
   * Returns 1 if a is positive, and 0 if a is negative.
   * Note: If a number starts with 0x, it means the rest of the digits are interpreted as hex
   */
  public static int sign(int a) {
    return flip((a >> 31) & 0x1);
  }

  public static int getMaxNaive(int a, int b) {
    int k = sign(a - b);
    int q = flip(k);
    System.out.println("getMaxNaive(" + a + ", " + b + "), k:" + k + ", q:" + q);
    return a * k + b * q;
  }

  public static int getMax(int a, int b) {
    int c = a - b;

    int sa = sign(a); // if a >= 0, then 1 else 0
    int sb = sign(b); // if b >= 0, then 1 else 0
    int sc = sign(c); // depends on whether or not a - b overflows

		/* We want to define a value k which is 1 if a > b and 0 if a < b. 
     * (if a = b, it doesn't matter what value k is) */

    int use_sign_of_a = sa ^ sb; // If a and b have different signs, then k = sign(a)
    int use_sign_of_c = flip(sa ^ sb); // If a and b have the same sign, then k = sign(a - b)

		/* We can't use a comparison operator, but we can multiply values by 1 or 0 */
    int k = use_sign_of_a * sa + use_sign_of_c * sc;
    int q = flip(k); // opposite of k

    return a * k + b * q;
  }

  public static void main(String[] args) {
    int a = 26;
    int b = -15;

    System.out.println("max_naive(" + a + ", " + b + ") = " + getMaxNaive(a, b));
    System.out.println("max(" + a + ", " + b + ") = " + getMax(a, b));

    a = -15;
    b = 2147483647;

    System.out.println("max_naive(" + a + ", " + b + ") = " + getMaxNaive(a, b));
    System.out.println("max(" + a + ", " + b + ") = " + getMax(a, b));
  }

}
