package Q5_06_Conversion;

import CtCILibrary.AssortedMethods;

/**
 * Conversion: write a function to determine the number of bits you would need to flip to convert integer A to integer
 * B.
 *
 * Example:
 * Input: 29 (or: 1 1101) , 15 (or: 0 1111)    Output: 2
 *
 * hint 336: how would you figure out how many bits are different between two number?
 *
 * hint 369: think about what an XOR indicates. if you do a XOR b, where does the result have 1s? where does it have
 * 0s?
 *
 * Solution: this seemingly complex problem is actually rather straightforward. to approach this, ask yourself how you
 * would figure out which bits on two numbers are different. simple: with an XOR
 */
public class QuestionA {

  public static int bitSwapRequired(int a, int b) {
    int count = 0;
    for (int c = a ^ b; c != 0; c = c >>> 1) {
      count += c & 1;
    }
    return count;
  }

  public static void main(String[] args) {
    int a = -23432;
    int b = 512132;
    System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
    System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    System.out.println("Required number of bits: " + bitSwapRequired(a, b));
  }
}
