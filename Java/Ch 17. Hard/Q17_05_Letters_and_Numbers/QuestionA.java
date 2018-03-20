package Q17_05_Letters_and_Numbers;

/**
 * Letters and numbers: given an array filled with letters and numbers, find the longest subarray with an equal number
 * of letters and numbers.
 *
 * hint 484: it doesn't really matter which letter or number it is. you can simplify this problem to just having an
 * array of As and Bs. you would then be looking for the longest subarray with an equal number of As and Bs
 *
 * hint 514: start with a brute force solution
 *
 * hint 618: what if you just started from the beginning, counting the number of As and the number of Bs you have seen
 * so far? (try making a table of the array and the number of As and Bs thus far)
 *
 * hint 670: when the above tables have equal values for the number of As and Bs, the entire subarray(starting from
 * index 0) has an equal number of As and Bs. how could you use this table to find qualifying subarrays that don't start
 * at index 0?
 *
 * hint 712: suppose, in this table, index i has count (A, 0->1) =3 and count(B, 0-1)=7. this means that there are four
 * more Bs than As. if you find a later spot j with the same difference (count(B, 0->jk) - count(A, 0->j), then this
 * indicates a subarray with an equal number of As and Bs.
 *
 * Solution: in the introduction, we discussed the importance of creating a really good, general-purpose example. that's
 * absolutely true. it's also important, though, to understand what matters.
 *
 * in this case, we just want an equal number of letters and numbers. all letters are treated identically and all
 * numbers are treated identically.
 *
 * Brute Force: let's start with the obvious solution. just go through all subarrayss, count the number of As and Bs,
 * and find the longest on that is equal. we can make one small optimization to this. we can start with the longest
 * subarray and, as soon as we find one which fits this equality condition, return it! the algorithm is O(N*N).
 */
public class QuestionA {

  public static char[] extractSubarray(char[] array, int start, int end) {
    if (start > end) {
      return null;
    }

    char[] subarray = new char[end - start + 1];
    for (int i = start; i <= end; i++) {
      subarray[i - start] = array[i];
    }
    return subarray;
  }

  public static boolean hasEqualLettersNumbers(char[] array, int start, int end) {
    int counter = 0;
    for (int i = start; i <= end; i++) {
      if (Character.isLetter(array[i])) {
        counter++;
      } else if (Character.isDigit(array[i])) {
        counter--;
      }
    }
    return counter == 0;
  }

  /**
   * return te largest subarray with equal number of 0s and 1s. look at each subarray, starting from the longest. as
   * soon as we find one that's equal, we return
   */
  public static char[] findLongestSubarray(char[] array) {
    System.out.println("array length: " + array.length);
    for (int len = array.length; len > 1; len--) {

      for (int i = 0; i <= array.length - len; i++) {

        System.out.println("len:" + len + ",i:" + i
            + ", call hasEqualLettersNumbers(), start=" + i + ", end:" + (i + len - 1));

        if (hasEqualLettersNumbers(array, i, i + len - 1)) {
          return extractSubarray(array, i, i + len - 1);
        }
      }
    }
    return null;
  }


  public static void main(String[] args) {
    char b = '1';
    char a = 'a';
    char[] array = {a, b, a, b, a, b, b, b, b, b, a, a, a, a, a, b, a, b, a, b, b, a, a, a, a, a, a, a};

    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println("\n");
    char[] max = findLongestSubarray(array);
    System.out.println(max.length);
    for (int i = 0; i < max.length; i++) {
      System.out.print(max[i] + " ");
    }
    System.out.println("\nIs Valid? " + hasEqualLettersNumbers(max, 0, max.length - 1));
  }

}
