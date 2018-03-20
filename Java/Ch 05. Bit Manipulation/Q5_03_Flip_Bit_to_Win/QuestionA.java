package Q5_03_Flip_Bit_to_Win;

/**
 * Flit bit to win: you have an integer and you can flip exactly one bit from a 0 to 1. write code to find the length of
 * the longest sequence of 1s you could create.
 *
 * Example
 *
 * Input: 1775 ( or: 110 1110 1111)     output: 8
 *
 * hint 159: start with a brute force solution. can you try all possibilities?
 *
 * hint 226: flipping a 0 to 1 can merge two sequence of 1s- but only if the two sequences are separated by only one 0
 *
 * hint 314: each sequence can be lengthened by merging it with an adjacent sequence (if any) or just flipping the
 * immediate neighboring zero. you just need to find the best choice.
 *
 * hint 352: try to do it in linear time, a single pass, and O(1) space.
 */
public class QuestionA {

  public static int SEQUENCE_LENGTH = 32;

  public static boolean getBit(int num, int i) {
    return ((num & (1 << i)) != 0);
  }

  public static int longestSequence(int n) {
    int maxSeq = 0;

    for (int i = 0; i < SEQUENCE_LENGTH; i++) {
      maxSeq = Math.max(maxSeq, longestSequenceOf1s(n, i));
    }

    return maxSeq;
  }

  public static int longestSequenceOf1s(int n, int indexToIgnore) {
    int max = 0;
    int counter = 0;
    for (int i = 0; i < SEQUENCE_LENGTH; i++) {
      if (i == indexToIgnore || getBit(n, i)) {
        counter++;
        max = Math.max(counter, max);
      } else {
        counter = 0;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int original_number = Integer.MAX_VALUE;
    int new_number = longestSequence(original_number);

    System.out.println(Integer.toBinaryString(original_number));
    System.out.println(new_number);
  }

}
