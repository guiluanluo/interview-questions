package Q8_05_Recursive_Multiply;

/**
 * Recursive multiple: write a recursive function to multiple two positive integers without using the * operator. you
 * can use addition, subtraction, and bit shifting, but you should minimize the number of those operations.
 *
 * hint 166: think about multiplying 8 by 9 as counting the number of cells in a matrix with width 8 and height 9.
 *
 * hint 203: if you want to count the cells in an 8x9 matrix, you could count the cells in a 4x9 matrix and then double
 * it.
 *
 * hint 227: think about how you might handle this for odd numbers
 *
 * hint 234: if there's duplicated work across different recursive calls, can you cache it?
 *
 * hint 246: if you are doing 9*7(both odd numbers), then you could do 4*7 and 5*7
 *
 * hint 280: alternatively, if you are doing 9*7, you could do 4*7, double that, and then add 7.
 *
 * Solution 1: **IMPORTANT** this is a good approach for a lots of interview questions. it is oftern useful to think
 * about what it really means to do something, evern when it's pretty obvious.
 *
 * we can think about 8x7 as doing "adding 7 eight times", we can also think about it as the nuber of squares in an 8x7
 * grid. how would we count the number of squares in this grid? we could count half the squares and then double it(by
 * adding this count to itself). to count half the squares, we repeat the same process. of course, this "doubling" only
 * works if the number is in fact even. when it's not even, we need to do the counting/summing from scratch.
 */
public class QuestionA {

  public static int counter = 0;

  public static int sum(int x, int y) {
    counter++;
    return x + y;
  }

  public static int minProductHelper(int smaller, int bigger) {
    System.out.println("minProductHelper(smaller=" + smaller + ", bigger=" + bigger + ")");

    if (smaller == 0) { // 0 x bigger = 0
      return 0;
    } else if (smaller == 1) { // 1 x bigger = bigger
      return bigger;
    }

		/* Compute half. If uneven, compute other half. If even, double it. */
    int s = smaller >> 1; // Divide by 2
    int side1 = minProductHelper(s, bigger);

    int side2 = side1;
    if (smaller % 2 == 1) {
      counter++;
      side2 = minProductHelper(smaller - s, bigger);
    }

    counter++;
    return side1 + side2;

  }

  public static int minProduct(int a, int b) {
    int bigger = a < b ? b : a;
    int smaller = a < b ? a : b;
    return minProductHelper(smaller, bigger);
  }

  public static void main(String[] args) {
    int a = 8;
    int b = 7;
    int product = a * b;
    int minProduct = minProduct(a, b);

    if (product == minProduct) {
      System.out.println("Success: " + a + " * " + b + " = " + product);
    } else {
      System.out.println("Failure: " + a + " * " + b + " = " + product + " instead of " + minProduct);
    }
    System.out.println("Adds: " + counter);
  }

}
