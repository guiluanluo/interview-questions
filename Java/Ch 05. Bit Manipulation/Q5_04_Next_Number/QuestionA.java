package Q5_04_Next_Number;

/**
 * Next number: given a positive integer, print the next smallest and the next largest number that have the same number
 * of 1 bits in their binary representation.
 *
 * hint 147: Get Next: start with a brute force solution for each
 *
 * hint 175: Get Next: picture a binary number - something with a bunch of 1 and 0s spread out number get bigger? in
 * what case will it get smaller?
 *
 * hint 242: Get Next: if you flip a 1 to 0 and a 0 to a 1, it will get bigger if the 0->1 bit is mor significant than
 * the 1->0 bit. how can you use this to create the next biggest number (with the same number of 1s).
 *
 * hint 312: Get Next: can you flip a 0 to a 1 to create the next biggest number?
 *
 * hint 339: Get Next: flipping a 0 to 1 will create a bigger number. the farther right the index is the smaller the
 * bigger number is. if we have a number like 1001, we want to flip the rightmost 0(to create 1011). but if we have a
 * number like 1010, we should not flip the rightmost 1.
 *
 * hint 358: Get Next: we should flip the rightmost non-trailing 0. the number 1010 would become 1110. once we have done
 * that, we need to flip a 1 to 0 to make the number as small as possible, but gigger than the original number(1010).
 * what do we do? how can we shrink the number?
 *
 * hint 375: Get Next: we can shrink the number by moving all the 1s to the right of the flipped bit as far right as
 * possible (removing a 1 in the process).
 *
 * hint 390: get previous: once you've solved Get Next, try to invert the logic for Get Previous
 *
 * Solution A: the brute force approach - count the number of 1s in n, and then increase(0r decrement) until you find a
 * number with the same number of 1s. easy - but not terribly interesting.
 */
public class QuestionA {

  public static int countOnes(int i) {
    int count = 0;
    while (i > 0) {
      if ((i & 1) == 1) {
        count++;
      }
      i = i >> 1;
    }
    return count;
  }

  public static int countZeros(int i) {
    return 32 - countOnes(i);
  }

  public static boolean hasValidNext(int i) {
    if (i == 0) {
      return false;
    }

    int count = 0;
    while ((i & 1) == 0) {
      i >>= 1;
      count++;
    }

    while ((i & 1) == 1) {
      i >>= 1;
      count++;
    }

    if (count == 31) {
      return false;
    }
    return true;
  }

  public static boolean hasValidPrev(int i) {
    while ((i & 1) == 1) {
      i >>= 1;
    }
    if (i == 0) {
      return false;
    }
    return true;
  }

  public static int getNextSlow(int i) {
    if (!hasValidNext(i)) {
      return -1;
    }

    int num_ones = countOnes(i);
    i++;
    while (countOnes(i) != num_ones) {
      i++;
    }
    return i;
  }

  public static int getPrevSlow(int i) {
    if (!hasValidPrev(i)) {
      return -1;
    }

    int num_ones = countOnes(i);
    i--;
    while (countOnes(i) != num_ones) {
      i--;
    }
    return i;
  }

  public static void main(String[] args) {
    int i = 13948;
    int p1 = getPrevSlow(i);
    int n1 = getNextSlow(i);

    Tester.binPrint(i);  //13948: 11 0110 0111 1100
    Tester.binPrint(p1); //13946: 11 0110 0111 1010
    Tester.binPrint(n1); //13967: 11 0110 1000 1111

    int i2 = 20;
    int p2 = getPrevSlow(i2);
    int n2 = getNextSlow(i2);

    Tester.binPrint(i2);  //20: 1 0100
    Tester.binPrint(p2); // 18: 1 0010
    Tester.binPrint(n2); // 24: 1 1000


  }
}
