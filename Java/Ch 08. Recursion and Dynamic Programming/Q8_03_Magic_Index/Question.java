package Q8_03_Magic_Index;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

/*
 * Magic index: a magic index in an array A[0...n-1] is defined to be an index such that A[i]=i. given a stored array of
 * distinct integers, write a method to find a magic index, if one exists, in array A.
 *
 * Follow up: what if the values are not distinct?
 *
 * hint 170: start with a brute force algorithm
 * hint 204:your brute force algorithm probably ran in O(N) time. if you are trying to beat that runtime, what runtime
 * do you think you will got to? what sorts of algorithms have that runtime?
 *
 * hint 240: can you solve this problem in O(log N) time?
 * hint 286: binary search has a runtime of O(log N). can you apply a form of binary search to the problem?
 * hint 340: given a specific index and value, can you identify if the magic index would be before or after it?
 *
 * Solution: in binary search, we find an element K by comparing it to the middle element X, and determine if K would
 * land on the left or the right side of X
 */
public class Question {

  public static int magicSlow(int[] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == i) {
        return i;
      }
    }
    return -1;
  }

  public static int magicFast(int[] array, int start, int end) {
    if (end < start) {
      return -1;
    }

    int mid = (start + end) / 2;
    if (array[mid] == mid) {
      return mid;
    } else if (array[mid] > mid) {
      return magicFast(array, start, mid - 1);  //left side
    } else {
      return magicFast(array, mid + 1, end);  //right side
    }
  }

  public static int magicFast(int[] array) {
    return magicFast(array, 0, array.length - 1);
  }

  /* Creates an array that is distinct and sorted */
  public static int[] getDistinctSortedArray(int size) {
    int[] array = AssortedMethods.randomArray(size, -1 * size, size);
    Arrays.sort(array);
    for (int i = 1; i < array.length; i++) {
      if (array[i] == array[i - 1]) {
        array[i]++;
      } else if (array[i] < array[i - 1]) {
        array[i] = array[i - 1] + 1;
      }
    }
    return array;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 1000; i++) {
      int size = AssortedMethods.randomIntInRange(5, 20);
      int[] array = getDistinctSortedArray(size);
      int v2 = magicFast(array);
      if (v2 == -1 && magicSlow(array) != -1) {
        int v1 = magicSlow(array);
        System.out.println("Incorrect value: index = -1, actual = " + v1 + " " + i);
        System.out.println(AssortedMethods.arrayToString(array));
        break;
      } else if (v2 > -1 && array[v2] != v2) {
        System.out.println("Incorrect values: index= " + v2 + ", value " + array[v2]);
        System.out.println(AssortedMethods.arrayToString(array));
        break;
      }
    }
  }

}
