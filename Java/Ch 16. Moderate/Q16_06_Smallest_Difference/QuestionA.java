package Q16_06_Smallest_Difference;

/**
 * Smallest difference: given two arrays of integers, compute the pair of values(one value in each array) with the
 * smallest (non-negative) difference. return the difference.
 *
 * Example: input: {1,3,15,11,2}, {23,127,235,19,8}   output: 3. that is, the pair {11,8}
 *
 * hint 631: what if you sorted the arrays?
 *
 * hint 669: think about how you would merge two sorted arrays
 *
 * hint 678: image you had the two arrays sorted and you were walking through them. if the pointer in the first array
 * points to 3 and the pointer in the second array points to 19, what effect will moving the second pointer have on the
 * difference of the pair?
 */
public class QuestionA {


  public static int findSmallestDifference(int[] array1, int[] array2) {
    if (array1.length == 0 || array2.length == 0) {
      return -1;
    }

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < array1.length; i++) {
      for (int j = 0; j < array2.length; j++) {
        if (Math.abs(array1[i] - array2[j]) < min) {
          min = Math.abs(array1[i] - array2[j]);
        }
      }
    }
    return min;
  }

  public static void main(String[] args) {
    int[] array1 = {1, 3, 15, 11, 12, 2};
    int[] array2 = {23, 127, 234, 19, 10, 8};
    int difference = findSmallestDifference(array1, array2);
    System.out.println(difference);
  }

}
