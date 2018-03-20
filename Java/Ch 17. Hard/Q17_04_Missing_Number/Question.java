package Q17_04_Missing_Number;

import java.util.ArrayList;
import java.util.Random;

/*
 * Missing number: an array A contains all the integers from 0 to n, except for one number which is missing. in this
 * problem, we can not access an entire integer in A with a single operation. the element of A are represented in
 * binary, and the only operation we can use to access then is "fetch the jth bit of A[i]", which takes constant time.
 * write code to find the missing integer. can you do it in O(n) time?
 *
 * hint 609: how long would it take you to figure out the least significant bit of the missing number?
 *
 * hint 658: to fin the least significant bit of the missing number, note that you know how many 0s and 1s to expect.
 * for example, if you see three 0s and three 1s in the least significant bit, then the missing number's least
 * significant bit must be a 1. think about it: in any sequence of 0s and 1s, you'd get a 0, then a 1, and so on
 *
 * hint 682: once you've identified that the least significant bit is a 0(or a 1), you can rule out all the numbers
 * without 0s as the least significant bit. how is this problem different from the earlier part?
 *
 * Solution: you may have seen a very similar sounding problem: given a list of numbers from 0 to n, with exactly one
 * number removed, find the missing number. this problem can be resolved by simply adding the list of numbers and
 * comparing it to the actual sum of 0 through n, which is n(n+1)/2, the difference will be the missing number!
 *
 * we could solve this by computing the value fo each number, based on its binary representation, and calculating the
 * sum. the runtime of this solution is n*length(n), when length is the number of bits in n. note that
 * length(n)=log2(n), so the runtime is actually O(n long(n)). not quite good enough!
 *
 * we can actually use a similar approach, but leverage the bit values more directly. picture a list of binary numbers
 * (the ---- indicates the value that was removed):
 * 00000 00001 00010 ----     00100 00101 00110 00111     01000 01001 01010 01011     01100 01101
 *
 * removing the number above creates an imbalance of 1s and 0s in the least significant bit, which we'll call LSB.
 * in a list of numbers from 0 to n, we would expect there to be the same number of 0s as 1s (if n is odd),
 * or additional 0 if n is even. that is:
 *    if n%2 = 1, then count(0s) =count(1s)
 *    if n%2 = 0, then count(0s) = 1+ count(1s)
 * note that this means that count(0s) is always greater than or equal to count(1s)!
 *
 * when we remove a value v from the list, we'll know immediately if v is even or odd just by looking at
 * the least significant bits of all the other values in the list.
 *  if count(0s)<= count(1s), then v is even.
 *  if count(0s) > count(1s), then v is odd.
 *
 * we can now remove all evens and focus on the odds, or remove all the odds and focus on the evens. okay, but how do we
 * figure the next bit in v is? if v were contained in our (now smaller) list, then we should expect to find the following
 * (where count2 indicates the number of 0a and 1s in the second least significant bit):
 *  if count2(0s)<= count2(1s), then LSB2(v) = 0
 *  if count2(0s) > count2(1s), then LSB2(v) = 1
 * we can repeat this process for each bit. one each iteration, we count the number of 0s and 1s in bit i to check
 * if LSB1(v) is 0 or 1. then, we discard the numbers where LSB1(x)!=LSB1(v). that is, if v is even, we discard
 * the odd numbers, and so on.
 *
 *  please keep read page 534....
 */
public class Question {

  /* Create a random array of numbers from 0 to n, but skip 'missing' */
  public static ArrayList<BitInteger> initialize(int n, int missing) {

    BitInteger.INTEGER_SIZE = Integer.toBinaryString(n).length();
    ArrayList<BitInteger> array = new ArrayList<BitInteger>();

    for (int i = 1; i <= n; i++) {
      array.add(new BitInteger(i == missing ? 0 : i));
    }

    // Shuffle the array once. TODO: why need to shuffle the array here!! 02/25/2018, LL comment out!!
//    for (int i = 0; i < n; i++) {
//      int rand = i + (int) (Math.random() * (n - i));
//      array.get(i).swapValues(array.get(rand));
//    }

    return array;
  }


  public static int findMissing(ArrayList<BitInteger> array) {
    return findMissing(array, BitInteger.INTEGER_SIZE - 1);
  }

  private static int findMissing(ArrayList<BitInteger> input, int column) {
    if (column < 0) { // Base case and error condition
      return 0;
    }

    ArrayList<BitInteger> oneBits = new ArrayList<BitInteger>(input.size() / 2);
    ArrayList<BitInteger> zeroBits = new ArrayList<BitInteger>(input.size() / 2);

    for (BitInteger t : input) {
      if (t.fetch(column) == 0) {
        zeroBits.add(t);
      } else {
        oneBits.add(t);
      }
    }

    /**
     * we recursive calculate the other bits of v. then, we insert either 0 or 1, depending on whether or not
     *  count1(0s) <= count1(1s).
     */
    if (zeroBits.size() <= oneBits.size()) { // missing number is even
      int v = findMissing(zeroBits, column - 1);
      System.out.print("0");
      return (v << 1) | 0;
    } else {                                // missing number is odd
      int v = findMissing(oneBits, column - 1);
      System.out.print("1");
      return (v << 1) | 1;
    }
  }

  public static void main(String[] args) {
    Random rand = new Random();
    int n = rand.nextInt(300000) + 1;
    int missing = rand.nextInt(n + 1);
//    ArrayList<BitInteger> array = initialize(n, missing);
//    System.out.println("The array contains all numbers but one from 0 to " + n + ", except for " + missing);
//
//    int result = findMissing(array);
//    if (result != missing) {
//      System.out.println("Error in the algorithm!\n" + "The missing number is "
//          + missing + ", but the algorithm reported " + result);
//    } else {
//      System.out.println(" The missing number is " + result);
//    }

    System.out.println("\n");
    n = 10;
    missing = 5;
    ArrayList<BitInteger> array2 = initialize(n, missing);
    System.out.println("The array contains all numbers but one from 0 to " + n + ", except for " + missing
        + ", BitInteger.INTEGER_SIZE: " + BitInteger.INTEGER_SIZE);

    int result2 = findMissing(array2);
    if (result2 != missing) {
      System.out.println("Error in the algorithm!\n" + "The missing number is "
          + missing + ", but the algorithm reported " + result2);
    } else {
      System.out.println(" The missing number is " + result2);
    }
  }
}
