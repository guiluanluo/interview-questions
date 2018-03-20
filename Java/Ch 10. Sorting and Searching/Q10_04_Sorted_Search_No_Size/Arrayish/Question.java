package Q10_04_Sorted_Search_No_Size.Arrayish;

/**
 * Sorted search, not size: you are given an array-like data structure Listy which lacks a size method. it does,
 * however, have an elementAt(i) method that returns the element at index i in O(1) time. if i is beyond the bounds of
 * the data structure, it returns -1. (for this reason, the data structure only supports positive integers). given a
 * Listy which contains sorted, positive integers, find the index at which an element x occurs multiple time, you may
 * return any index.
 *
 * hint 320: think about how binary search works. what will be the issue with just implementing binary search?
 *
 * hint 337: binary search requires comparing an element to the midpoint. getting the midpoint requres knowing the
 * length. we don't know the length. can we find it?
 *
 * hint 348: we can find the length by using an exponential backoff. fist check index 2, then 4, then 8, then 16, and so
 * on. what will be the runtime of this algorithm?
 */
public class Question {

  public static int binarySearch(Listy list, int value, int low, int high) {
    int mid;

    while (low <= high) {

      mid = (low + high) / 2;
      int middle = list.elementAt(mid);

      if (middle > value || middle == -1) {
        high = mid - 1;
      } else if (middle < value) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static int search(Listy list, int value) {
    int index = 1;
    while (list.elementAt(index) != -1 && list.elementAt(index) < value) {
      index *= 2;
    }
    System.out.println("value: " + value + ", index:" + index);
    return binarySearch(list, value, index / 2, index);
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18};
    Listy list = new Listy(array);

    System.out.println(search(list, 5));

    for (int a : array) {
      System.out.println(search(list, a));
    }
    System.out.println(search(list, 15));
  }

}
