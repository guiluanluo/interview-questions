package Q5_01_Insertion;

import CtCILibrary.AssortedMethods;

/**
 * Insertion: you are given two 32-bit number, N and M, and two bit position, i and j. write a method to insert M into N
 * such that M starts at bit j and ends at bit i. you can assume that the bits j through i have enough space to fit all
 * of M. that is, if M=10011, you can assume that there are at lease 5 bits between j and i. you would not, for example,
 * have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.
 *
 * Example:
 *
 * input N = 100 0000 0000,  M = 10011, i =2, j=6     Output: N = 100 0100 1100
 *
 * hint 137: break this into parts. focus first on clearing the appropriate bits.
 *
 * hint 169: to clear this bits, create a "bit mask" that looks lik a series of 1s, then 0s, the 1s
 *
 * hint 215: it's easy to create a bit mask of 0s at the beginning or end. but how do you create a bit mask with a bunch
 * of zero in the middle? do it the easy way: create a bit mask for the left side and then another one for the right
 * side. then you can merge those.
 *
 * Solution: this problem can be approached in three key step
 * 1) clear bits j through i in N
 * 2) shift M so that it lines up with bits j through i
 * 3) merge M and N
 */
public class Question {

  public static int updateBits(int n, int m, int i, int j) {
    System.out.println("** updateBits(), n=" + Integer.toBinaryString(n) + ", m=" + Integer.toBinaryString(m) +
        ", i=" + i + ", j=" + j);

    // Validation
    if (i >= 32 || j < i) {
      return 0;
    }

		/* Create a mask to clear bits i through j in n
    /* EXAMPLE: i = 2, j = 4. Result should be 11100011.
		 * (Using 8 bits for this example.  This is obviously not actually 8 bits.)
		 */
    int allOnes = ~0; // allOnes = 11111111

    int left = allOnes << (j + 1); // 1s through position j, then 0s. left = 11100000
    int right = ((1 << i) - 1); // 1s after position i.  right = 00000011
    int mask = left | right; // All 1s, except for 0s between i and j. mask = 11100011

    System.out.println("allOnes:" + Integer.toBinaryString(allOnes));
    System.out.println("left:" + Integer.toBinaryString(left));
    System.out.println("right:" + Integer.toBinaryString(right)
        + ", (1 << " + i + "):" + Integer.toBinaryString((1 << i)));
    System.out.println("mask:" + Integer.toBinaryString(mask));

		/* Clear i through j, then put m in there */
    int n_cleared = n & mask; // Clear bits j through i.
    int m_shifted = m << i; // Move m into correct position.

    System.out.println("n_cleared:" + Integer.toBinaryString(n_cleared));
    System.out.println("m_shifted:" + Integer.toBinaryString(m_shifted));

		/* OR them, and we're done! */
    return n_cleared | m_shifted;
  }

  public static void main(String[] args) {
    int a = 103217;
    System.out.println(AssortedMethods.toFullBinaryString(a));
    int b = 13;
    System.out.println(AssortedMethods.toFullBinaryString(b));
    int c = updateBits(a, b, 4, 12);
    System.out.println(AssortedMethods.toFullBinaryString(c));
  }

}
