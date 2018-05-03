package google;

/**
 * https://practice.geeksforgeeks.org/problems/the-painters-partition-problem/0
 *
 * Dilpreet wants to paint his dog- Buzo's home that has n boards with different lengths[A1, A2,..., An]. He hired k
 * painters for this work and each painter takes 1 unit time to paint 1 unit of the board.The problem is to find the
 * minimum time to get this job done under the constraints that any painter will only paint continuous sections of
 * boards, say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.
 *
 * Input: The first line consists of a single integer T, the number of test cases. For each test case, the first line
 * contains an integer k denoting the number of painters and integer n denoting the number of boards. Next line contains
 * n- space separated integers denoting the size of boards.
 *
 * Output: For each test case, the output is an integer displaying the minimum time for painting that house.
 *
 * Constraints: 1<=T<=100 1<=k<=30 1<=n<=50 1<=A[i]<=500
 *
 * Example: Input: 2 2 4 10 10 10 10 2 4 10 20 30 40 Output: 20 60
 *
 * Explanation: 1) Here we can divide the boards into 2 equal sized partitions, so each painter gets 20 units of the
 * board and the total time taken is 20. 2) Here we can divide first 3 boards for one painter and the last board for the
 * second painter.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PainterPartition {

  private static Map<String, Integer> sumMap;
  private static Map<String, Integer> partitionMap;

  public static int sum(int[] boards, int start, int end) {
    String key = start + "_" + end;
    if (sumMap.containsKey(key)) {
      return sumMap.get(key);
    }

    int result;
    if (start == end) {
      result = boards[start];
    } else if (start > end) {
      result = 0;
    } else {
      int medium = (start + end) / 2;
      result = sum(boards, start, medium) + sum(boards, medium + 1, end);
    }
    sumMap.put(key, result);
    return result;
  }

  public static int partition(int[] boards, int n, int k) {
    String key = n + "_" + k;
    if (partitionMap.containsKey(key)) {
      return partitionMap.get(key);
    }

    int result;
    if (k == 1) {
      result = sum(boards, 0, n - 1);
    } else if (n == 1) {
      result = boards[0];
    } else {
      int best = Integer.MAX_VALUE;
      for (int i = 1; i <= n; i++) {
        int partitionResult = partition(boards, i, k - 1); //boards[i, n] partitioned by k-1 painters
        int sum = sum(boards, i, n - 1); //sum of board from index [i, n-1]
        int maxResult = Math.max(partitionResult, sum);
        best = Math.min(best, maxResult);
      }
      result = best;
    }

    partitionMap.put(key, result);
    return result;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      sumMap = new HashMap<String, Integer>();
      partitionMap = new HashMap<String, Integer>();

      String[] input1 = scanner.nextLine().trim().split(" ");
      int k = Integer.parseInt(input1[0]);
      int n = Integer.parseInt(input1[1]);

      int[] boards = new int[n];
      String[] input2 = scanner.nextLine().trim().split(" ");
      for (int i = 0; i < n; i++) {
        boards[i] = Integer.parseInt(input2[i]);
      }

      int result = partition(boards, n, k);
      System.out.println("partition(): " + result);
      count++;
    }
  }
}