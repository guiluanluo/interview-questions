package Q17_19_Missing_Two;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Missing two: you are given an array with all the numbers from 1 to N appearing exactly once, except for one number
 * that is missing. how can you find the missing number in O(n) time and O(1) space? what if there were two numbers
 * missing?
 *
 * Solution A - missing one: 1) using multiple - 1*2*3*4*5=120, 1*2*4*5=40; 120/40=3
 *
 * Solution B: x+y=sum; x*y=product --> x(sum-x)=product --> x*x - x*sum + product =0  to find x, will know y since
 * y=sum-x
 */
public class QuestionA {

  public static int missingOne_add(int[] array) {
    int max = 0;
    int total = 0;
    for (int i : array) {
      total += i;
      if (i > max) {
        max = i;
      }
    }

    int actTotal = max * (max + 1) / 2;

    return actTotal - total;
  }

  public static int missingOne(int[] array) {
    BigInteger fullProduct = productToN(array.length + 1);

    BigInteger actualProduct = new BigInteger("1");
    for (int i = 0; i < array.length; i++) {
      BigInteger value = new BigInteger(array[i] + "");
      actualProduct = actualProduct.multiply(value);
    }

    BigInteger missingNumber = fullProduct.divide(actualProduct);
    return Integer.parseInt(missingNumber.toString());
  }

  public static BigInteger productToN(int n) {
    BigInteger fullProduct = new BigInteger("1");
    for (int i = 2; i <= n; i++) {
      fullProduct = fullProduct.multiply(new BigInteger(i + ""));
    }
    return fullProduct;
  }


  public static void main(String[] args) {
    int max = 100;
    int x = 8;
    int len = max - 1;
    int count = 0;
    int[] array = new int[len];
    for (int i = 1; i <= max; i++) {
      if (i != x) {
        array[count] = i;
        count++;
      }
    }
    System.out.println("missing:" + x);
    System.out.println("array:" + Arrays.toString(array));
    System.out.println("solution(*):" + missingOne(array));
    System.out.println("solution(+):" + missingOne_add(array));
  }

}
