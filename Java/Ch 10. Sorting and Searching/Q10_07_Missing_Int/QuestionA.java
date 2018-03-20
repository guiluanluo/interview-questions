package Q10_07_Missing_Int;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * Missing Int: given an input file with four billion non-negative integers, provide an algorithm to generate an integer
 * that is not contained in the file. assume you have 1 GB of memory available for this task.
 *
 * Follow up: what if you have only 10 MB of memory? assume that all the values are distinct and we now have no more
 * than one billion non-negative integer
 *
 * Solution: there are a total of 2 power 23, or 4 billion, distinct integers possible and 2 power of 31 non-negative
 * integers. therefore, we know the input file(assuming it is ints rather than longs) contains some duplicate.
 *
 * we have 1 GB of memory, or 8 billion bits. thus, with 8 billion bits, we can map all possible integers to a distinct
 * bit with the available memory. the logic as following:
 * 1) create a bit vector(BV) with 4 billion bits. recall that a bit vector is an array that compactly store boolean
 * values by using an array of ints ( or another data type). each int represents 32 boolean values.
 * 2) initialize BV with all 0s
 * 3) scan all numbers(num) from the file and call BV.set(num, 1)
 * 4) now scan again BV from the 0th index.
 * 5) return the first index which have a value of 0
 */

public class QuestionA {

  public static long numberOfInts = ((long) Integer.MAX_VALUE) + 1;
  public static byte[] bitfield = new byte[(int) (numberOfInts / 8)];

  public static void findOpenNumber() throws Exception {

    FileReader reader = null;
    Scanner in = null;
    try {
      //Lucy Note: this original file input.rxt contains 1 - 9999999, one number each line
      //Scanner in = new Scanner(new FileReader("Ch 10. Sorting and Searching/Q10_07_Missing_Int/input.txt"));
      String inputFile = "/Users/212391955/EWorkspace/Aviation/projects/CtCI-6th-Edition/Java/Ch 10. Sorting and Searching/Q10_07_Missing_Int/input.txt";
      reader = new FileReader(inputFile);
      in = new Scanner(reader);
      while (in.hasNextInt()) {
        int n = in.nextInt();
        /**
         *  Finds the corresponding number in the bitfield by using the OR operator to set the nth bit of a byte
         *  (e.g., 10 would correspond to bit 2 of index 1 in the byte array).
         */
        bitfield[n / 8] |= 1 << (n % 8);
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      if (reader != null) {
        reader.close();
      }
      if (in != null) {
        in.close();
      }
    }

    for (int i = 0; i < bitfield.length; i++) {
      for (int j = 0; j < 8; j++) {
        /* Retrieves the individual bits of each byte. When 0 bit is found, finds the corresponding value. */
        if ((bitfield[i] & (1 << j)) == 0) {
          System.out.println(i * 8 + j);
          return;
        }
      }
    }

  }

  public static void main(String[] args) throws Exception {
    findOpenNumber();
  }
}
