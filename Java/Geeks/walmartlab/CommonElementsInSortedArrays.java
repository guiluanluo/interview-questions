package walmartlab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/find-common-elements-three-sorted-arrays/
 *
 * Given three arrays sorted in non-decreasing order, print all common elements in these arrays.
 *
 * Examples:
 *
 * ar1[] = {1, 5, 10, 20, 40, 80}
 * ar2[] = {6, 7, 20, 80, 100}
 * ar3[] = {3, 4, 15, 20, 30, 70, 80, 120}
 * Output: 20, 80
 *
 * ar1[] = {1, 5, 5}
 * ar2[] = {3, 4, 5, 5, 10}
 * ar3[] = {5, 5, 10, 20}
 * Output: 5, 5
 */
public class CommonElementsInSortedArrays {

  void findCommon(int ar1[], int ar2[], int ar3[]) {
    // Initialize starting indexes for ar1[], ar2[] and ar3[]
    int i = 0, j = 0, k = 0;

    // Iterate through three arrays while all arrays have elements
    while (i < ar1.length && j < ar2.length && k < ar3.length) {
      // If x = y and y = z, print any of them and move ahead, in all arrays
      if (ar1[i] == ar2[j] && ar2[j] == ar3[k]) {
        System.out.print(ar1[i] + " ");
        i++;
        j++;
        k++;
      } else if (ar1[i] < ar2[j]) { // x < y
        i++;
      } else if (ar2[j] < ar3[k]) {  // y < z
        j++;
      } else { // We reach here when x > y and z < y, i.e., z is smallest
        k++;
      }
    }
  }

  public static List<Integer> findCommonElements(int[] arr1, int[] arr2, int[] arr3) {
    Map<Integer, Integer> numberCountMap = new HashMap<>();
    for (int a1 : arr1) {
      Integer count = numberCountMap.get(a1);
      count = count == null ? 1 : count + 1;
      numberCountMap.put(a1, count);
    }
    for (int a2 : arr2) {
      Integer count = numberCountMap.get(a2);
      count = count == null ? 1 : count + 1;
      numberCountMap.put(a2, count);
    }
    for (int a3 : arr3) {
      Integer count = numberCountMap.get(a3);
      count = count == null ? 1 : count + 1;
      numberCountMap.put(a3, count);
    }

    List<Integer> commonElements = new ArrayList();
    Set<Integer> keys = numberCountMap.keySet();
    for (Integer key : keys) {
      if (numberCountMap.get(key) == 3) {
        commonElements.add(key);
      }
    }
    return commonElements;
  }


  public static void main(String[] args) {

  }

}
