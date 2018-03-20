package Q5_07_Pairwise_Swap;

import CtCILibrary.AssortedMethods;

/**
 * Pairwise swap: write a program to swap odd and even bits in an integer with as few instruction as possible (e.g., bit
 * 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and son on)
 *
 * hint 145: swapping each pair means moving the even bits to the left and the odd bits to the right. can you break this
 * problem into parts?
 *
 * hint 248: can you create a number that represents just the even bits? then can you shift the even bits over by one?
 *
 * hint 328: the value 1010 in binary is 10 in decimal or 0xA in hex. what will a sequence of 101010... be in hex? that
 * is, how do you represent an alternating sequence of 1s and 0s with 1s in the odd places? how do you do this for the
 * reverse (1s in the even spots)?
 *
 * hint 355: try masks 0xaaaa aaaa and 0x5555 5555 to select the even and odd bits. then try shifting the even and odd
 * bits around to create the right number.
 *
 * Integer has 32 bits, the mask of the even bits: 1010 1010 1010 1010 1010 1010 1010 1010 = aaaa aaaa the odd bits:
 * 0101 0101 0101 0101 0101 0101 0101 0101 = 5555 5555
 *
 * we could mask all odd bits with aaaa aaaa, then shift them right by 1 to put them in even spots; mask all even bits
 * with 5555 5555 and then shift them left by 1 to put them in odd spots; finally OR both of them
 */
public class Question {

  public static int swapOddEvenBits(int x) {
    int even2odd = (x & 0xaaaaaaaa) >>> 1;
    int odd2even = (x & 0x55555555) << 1;
    return even2odd | odd2even;
//    return (((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1));
  }

  public static void main(String[] args) {
    int a = 234321;
    System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
    int b = swapOddEvenBits(a);
    System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
  }

}
