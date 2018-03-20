package Q17_10_Majority_Element;

import java.util.Arrays;

/**
 * Majority element: a majority element is an element that makes up more than half of the items in an array. given a
 * positive integers array, find the majority element. if there is not majority element, return -1. do this in O(n) time
 * and O(1) space.
 *
 * input: 1 2 5 9 5 9 5 5 5     output: 5
 *
 * Solution: let's start off with an example: 3 1 7 1 3 7 3 7 1 7 7. one thing we can notice here is that if the
 * majority element(in this case 7) appears less often in the beginning, it must appear much more often toward the end.
 * that's a good observation to make!
 *
 * this interview question specifically requires us to do this in O(n) time and O(1) space. nonetheless, sometime it can
 * be useful to relax one of those requirements and develop an algorithm. let's try relaxing the time requirement but
 * stay from on the O(1) space requirement.
 *
 * Solution 1(slow): one simple way to do this is to just iterate through the array and check each element for whether
 * it's the majority element. this takes O(n*n) time and O(1) space.
 */
public class QuestionA {


  //note: this algorithm takes O(n logn(n)) + O(n) = O(n logn(n))
  public static int findMajorityElement_lucy(int[] array) {
    if (array.length == 0 || array.length < 3) {
      return -1;
    }

    Arrays.sort(array); //take O(n log(n)) runtime
    int majorCount = array.length % 2 == 0 ? array.length / 2 : array.length / 2 + 1;

    int prev = array[0];
    int count = 1;
    for (int i = 1; i < array.length; i++) {
      if (array[i] == prev) {
        count++;
      } else {
        prev = array[i];
        count = 1;
      }

      if (count >= majorCount) {
        return array[i];
      }
    }
    return -1;
  }

  // this algorithm takes O( n*n) runtime !
  public static int findMajorityElement(int[] array) {
    for (int x : array) {
      if (validate(array, x)) {
        return x;
      }
    }
    return -1;
  }

  public static boolean validate(int[] array, int majority) {
    int count = 0;
    for (int n : array) {
      if (n == majority) {
        count++;
      }
    }

    return count > array.length / 2;
  }


  public static void main(String[] args) {
    System.out.println("called findMajorityElement()....");
    int[] array = {0, 0, 1, 2, 2, 0, 1, 0, 1, 1, 1, 1, 1};
    System.out.println("array length:" + array.length + ", majorityElement:" + findMajorityElement(array));

    System.out.println();
    System.out.println("called findMajorityElement_lucy()....");
    int[] array1 = {0, 0, 1, 2, 2, 0, 1, 0, 1, 1, 1, 1, 1};
    System.out.println("array length:" + array1.length + ", majorityElement:" + findMajorityElement_lucy(array1));
  }

}
