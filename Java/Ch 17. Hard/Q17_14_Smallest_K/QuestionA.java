package Q17_14_Smallest_K;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

/*
 * Smallest K: design an algorithm to find the smallest K elements in an array.
 *
 * Solution: there are number of ways to approach this problem. we will go through three of them: sorting, max heap, and
 * selection rank.
 *
 * 1) Sorting: sort the array, then copy the first K elements
 *
 * 2) Max heap: first create a max heap(largest element at the top) for the fist million numbers. then we traverse
 * through list. on each element, if it's smaller that the root, we insert it into the heap and delete the largest
 * element(which will be the root). the algorithm is O(n log(m)) time, where m is the number of values we are looking for.
 *
 * Java uses the PriorityQueue class to offer heap-like functionality. be default, it operates as a min heap with the
 * smallest element on the top. to switch it to the biggest element on the top, we pass in a different comparator.
 * PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new MaxHeapComparator());
 *
 * 3) selection rank algorithm(if elements are unique): Selection Rank is a well known algorithm in computer science to
 * find the ith smallest(or largest) element in an array in linear time.
 *
 * if the elements are unique, you can find the ith smallest element in expected O(n) time. the basic algorithm operates
 * like this:
 * a) pick a random element in the array and use it as a "pivot". partition elements around the pivot, keeping track of
 * the number of elements on the left side of the partition.
 * b) if there are exactly i elements on the left, then you just return the biggest element on the left
 * c) if the left side is bigger than i, repeat the algorithm on just the left part of the array
 * d) if the left size is smaller than i, repeat the algorithm on the right, but look for the element with rank
 * i-leftSize.
 *
 * once you have found the ith smallest element, you know that all elements smaller than is will be to the left of
 * this(since you've partitioned the array accordingly). you can now just return the first i elements.
 *
 * 4) selection rank algorithm(if elements are not unique): the major change that to be be made is to the partition().
 *  when we partition the array around a pivot element, we now partition it into three chunks: less than pivot, equal to
 *  pivot, and greater than pivot.
 *
 *  this requires minor tweaks to rank as well. we now compare the size of left and middle partitions to rank
 */
public class QuestionA {

  public static int[] smallestK_lucy(int[] array, int k) {
    if (k <= 0 || k > array.length) {
      throw new IllegalArgumentException();
    }

    Arrays.sort(array);
    return Arrays.copyOf(array, k);
  }

  public static int[] smallestK(int[] array, int k) {
    if (k <= 0 || k > array.length) {
      throw new IllegalArgumentException();
    }

		/* Sort array. */
    Arrays.sort(array);

		/* Copy first k elements. */
    int[] smallest = new int[k];
    for (int i = 0; i < k; i++) {
      smallest[i] = array[i];
    }
    return smallest;
  }

  public static void main(String[] args) {
    int[] array = {1, 5, 2, 9, 1, 11, 6, 13, 15};
    int[] smallest = smallestK(array, 3);
    System.out.println("smallestK():" + AssortedMethods.arrayToString(smallest));

    smallest = smallestK_lucy(array, 3);
    System.out.println(AssortedMethods.arrayToString(smallest));
  }

}
