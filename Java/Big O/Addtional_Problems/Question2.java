package Addtional_Problems;

import java.util.Arrays;

/**
 * Given a small string s and a bigger string b, design a algorithm to find all permutations of the shorter string
 * within the longer one. Print the location of each permutation
 */
public class Question2 {

  public static void findPermutation(String s, String b) {

    int slen = s.length();
    String sortedS = sorted(s);

    int blen = b.length();

    for (int i = 0; i < blen; i++) {
      int endIndex = i + slen < blen ? (i + slen) : blen;
      String subStr = b.substring(i, endIndex);
      if (isEqual(sortedS, sorted(subStr))) {
        System.out.println("*" + subStr);
      }
    }
  }

  public static String sorted(String str) {
    char[] strArray = str.toCharArray();
    Arrays.sort(strArray);
    return new String(strArray);
  }

  public static boolean isEqual(String sorted1, String sorted2) {
    return sorted1.equals(sorted2);
  }


  public static void swapMinMax(int[] array) {
    int minIndex = 0, maxIndex = 0;

    for (int i = 1; i < array.length; i++) {
      if (array[i] < array[minIndex]) {
        minIndex = i;

      }

      if (array[i] > array[maxIndex]) {
        maxIndex = i;
      }
    }
    System.out.println("minIndex:" + minIndex + ", maxIndex:" + maxIndex);

    int max = array[maxIndex];
    array[maxIndex] = array[minIndex];
    array[minIndex] = max;

    for (int j = 0; j < array.length; j++) {
      System.out.println(array[j]);
    }
  }

  public static void main(String[] args) {

    findPermutation("abc", "dbcabceabcf");

    int[] array = {4, 5, 1, 2, 3};
    swapMinMax(array);
  }

}
