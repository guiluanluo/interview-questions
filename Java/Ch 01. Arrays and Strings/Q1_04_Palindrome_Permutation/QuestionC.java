package Q1_04_Palindrome_Permutation;

/**
 * actually we don't need to know the counts. we just need to know the count is even or odd. think about flipping a
 * light on/off(that is initially off). if the ligh winds up in the OFF state, we don't know how many time we flipped
 * it, but we do know it was an even count.
 *
 * given this, we can use a single integer(as a bit vector). when we see a letter, we map it to an integer between 0 and
 * 26(assuming an english alphabet). then we toggle the bit at that value. at the end of the iteration, we check that at
 * most one bit in the integer is set to 1.
 *
 * we can easily check that no bits in the integer are 1: just compare the integer to 0. there is actually a very
 * elegant way to check that an integer has exactly one bit set to 1.
 *
 * picture an integer link 0001 0000. we could of cause shift the integer repeatedly to check that there is only a
 * single 1. alternatively, if we subtract 1 from the number, we will get 0000 1111. what's notable about this is that
 * there is no overlap between the number (as opposed to say 0010 1000, which, when we subtract 1 from, we get 0010
 * 0111) so, we can check to see that a number has exactly one 1 because if we subtract 1 from it and then AND it with
 * the new number, we should get 0
 *
 * 0001 0000 -1 = 0000 1111           0001 0000 & 0000 1111 = 0
 */
public class QuestionC {

  /* Toggle the ith bit in the integer. */
  public static int toggle(int bitVector, int index) {
    if (index < 0) {
      return bitVector;
    }

    int mask = 1 << index;
    System.out.println("** bitVector:" + bitVector + ", index:" + index + ",  mask(1 << index):" + mask
        + ", (bitVector & mask):" + (bitVector & mask));

    if ((bitVector & mask) == 0) { //off
      bitVector |= mask; //on
    } else {
      bitVector &= ~mask; // off
    }

    System.out.println("**** return bitVector:" + bitVector + "\n");
    return bitVector;
  }

  /* Create bit vector for string. For each letter with value i, toggle the ith bit. */
  public static int createBitVector(String phrase) {
    int bitVector = 0;
    for (char c : phrase.toCharArray()) {
      int x = Common.getCharNumber(c);
      bitVector = toggle(bitVector, x);
    }
    return bitVector;
  }

  /* Check that exactly one bit is set by subtracting one from the
   * integer and ANDing it with the original integer. */
  public static boolean checkExactlyOneBitSet(int bitVector) {
    return (bitVector & (bitVector - 1)) == 0;
  }

  public static boolean isPermutationOfPalindrome(String phrase) {
    int bitVector = createBitVector(phrase);
    return bitVector == 0 || checkExactlyOneBitSet(bitVector);
  }

  public static void main(String[] args) {
    String pali = "cadac";// "Rats live on no evil star";
    System.out.println(isPermutationOfPalindrome(pali));
  }

}
