package Q10_07_Missing_Int;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Follow up: what if we have only 10 MB memory?
 *
 * it's possible to find a missing integer with two passes of the data set. we can divide up the integer into blocks of
 * some size( we'll discuss how to decide on a size later). let's assume that we divide up the integers into blocks of
 * 1000. so, block 0 represent s the numbers o through 999, block 1 represent number 1000 -1999, and so on.
 *
 * since all the values are distinct, we know how many values we should find in each block, so we search through the
 * file and count how many values are between 0-999, how many between 1000-1999, and so on. if we count only 999 values
 * in a particular range, then we know that a missing int must be in that range.
 *
 * in the second pass, we'll actually look for which number in that rang is missing. we use the bit vectors approach
 * from the first part of this problem. we can ignore any number outside of this specific range.
 *
 * the question, now, is what is the appropriate block size? let's define some variables as follow:
 * 1) let rangeSize be the size of the ranges that each block in the first pass represent.
 * 2) let arraySize represent the number of blocks in the first pass. Note that arraySize =(2 power of 31/rangeSize)
 *    since there are 2 power of 31 non-negative integers.
 * we need to select a value for rangeSize such that the memory from the first pass(the array) and the second pass(the
 * bit vector)fit.
 *
 * First pass: the array
 * the array in the fist pass can fit in 10 MB, or roughly 2 power of 32 bytes, of memory. since each element in the array
 * is an int, and an int is 4 bytes, we can hold an array of at most about 2 power of 31 elements. so we can deduce the
 * following:
 *    arraySize = (2 power of 31)/rangeSie <= 2 power of 21
 *    rangeSize >= (2 power of 31)/(2 power of 21)
 *    rangeSize >= (2 power of 10)
 *
 * Second pass: the bit vector
 * we need to have enough space to store rangeSize bits. since we can fit 2 power of 23 bytes in memory, we can fit
 * 2 power of 26 bits in memory. therefore, we can conclude the following:
 *    (2 power of 10) <= rangeSize <= (2 power of 26)
 * this condition give us a good amount of "wiggle room", but the nearer to the middle that we pick, then less memory
 * will be used at any given time.
 */
public class QuestionB {

  public static int findOpenNumber(String filename) throws FileNotFoundException {

    int rangeSize = (1 << 20); // 2^20 bits (2^17 bytes)

		/* Get count of number of values within each block. */
    int[] blocks = getCountPerBlock(filename, rangeSize);

		/* Find a block with a missing value. */
    int blockIndex = findBlockWithMissing(blocks, rangeSize);
    if (blockIndex < 0) {
      return -1;
    }

		/* Create bit vector for items within this range. */
    byte[] bitVector = getBitVectorForRange(filename, blockIndex, rangeSize);

		/* Find a zero in the bit vector */
    int offset = findZero(bitVector);
    if (offset < 0) {
      return -1;
    }

		/* Compute missing value. */
    return blockIndex * rangeSize + offset;
  }

  /* Get count of items within each range. */
  public static int[] getCountPerBlock(String filename, int rangeSize) throws FileNotFoundException {
    int arraySize = Integer.MAX_VALUE / rangeSize + 1;
    int[] blocks = new int[arraySize];

    Scanner in = new Scanner(new FileReader(filename));
    while (in.hasNextInt()) {
      int value = in.nextInt();
      blocks[value / rangeSize]++;
    }
    in.close();
    return blocks;
  }

  /* Find a block whose count is low. */
  public static int findBlockWithMissing(int[] blocks, int rangeSize) {
    for (int i = 0; i < blocks.length; i++) {
      if (blocks[i] < rangeSize) {
        return i;
      }
    }
    return -1;
  }

  /* Create a bit vector for the values within a specific range. */
  public static byte[] getBitVectorForRange(String filename, int blockIndex, int rangeSize)
      throws FileNotFoundException {

    int startRange = blockIndex * rangeSize;
    int endRange = startRange + rangeSize;
    byte[] bitVector = new byte[rangeSize / Byte.SIZE];

    Scanner in = new Scanner(new FileReader(filename));
    while (in.hasNextInt()) {
      int value = in.nextInt();
      /* If the number is inside the block that's missing numbers, we record it */
      if (startRange <= value && value < endRange) {
        int offset = value - startRange;
        int mask = (1 << (offset % Byte.SIZE));
        bitVector[offset / Byte.SIZE] |= mask;
      }
    }
    in.close();
    return bitVector;
  }

  /* Find bit index that is 0 within byte. */
  public static int findZero(byte b) {
    for (int i = 0; i < Byte.SIZE; i++) {
      int mask = 1 << i;
      if ((b & mask) == 0) {
        return i;
      }
    }
    return -1;
  }

  /* Find a zero within the bit vector and return the index. */
  public static int findZero(byte[] bitVector) {
    for (int i = 0; i < bitVector.length; i++) {
      if (bitVector[i] != ~0) { // If not all 1s
        int bitIndex = findZero(bitVector[i]);
        return i * Byte.SIZE + bitIndex;
      }
    }
    return -1;
  }

  public static void generateFile(String filename, int max, int missing) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(filename);

    for (int i = 0; i < max && i >= 0; i++) {
      if (i != missing) {
        writer.println(i);
      }
      if (i % 10000 == 0) {
        System.out.println("Now at location: " + i);
      }
    }
    writer.flush();
    writer.close();
  }

  public static void main(String[] args) throws FileNotFoundException {
    String filename = "Ch 10. Scalability and Memory Limits/Q10_04_Missing_Int/input.txt";
    int max = 10000000;
    int missing = 1234325;
    System.out.println("Generating file...");
    generateFile(filename, max, missing);
    System.out.println("Generated file from 0 to " + max + " with " + missing + " missing.");
    System.out.println("Searching for missing number...");
    System.out.println("Missing value: " + findOpenNumber(filename));
  }

}
