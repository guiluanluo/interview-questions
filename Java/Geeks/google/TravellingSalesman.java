package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/travelling-salesman-problem/0
 *
 * Given a matrix M of size N where M[i][j] denotes the cost of moving from city i to city j. Your task is to complete a
 * tour from the city 0 (0 based index) to all other cities such that you visit each city atmost once and then at the
 * end come back to city 0 in min1 cost.
 *
 * Input: The first line of input contains an integer T denoting the no of test cases. Then T test cases follow. Each
 * test case contains an integer N denoting the size of the matrix then in the next line are N*N space separated values
 * of the matrix M.
 *
 * Output: For each test case print the required result denoting the min1 cost of the tour in a new line.
 *
 * Constraints:
 * 1<=T<=15
 * 1<=N<=12
 * 1<=M[][]<=10000
 *
 * Example:
 * Input:
 * 2
 * 2
 * 0 111 112 0
 * 3
 * 0 1000 5000 5000 0 1000 1000  5000  0
 * Output:
 * 223
 * 3000
 */
public class TravellingSalesman {

  /**
   * Solution 1
   */
  public static long min1;

  public static void find_min_1(int[][] matrix, int n) {
    boolean[] visited = new boolean[n];
    visited[0] = true;
    find_min_1(matrix, n, 0, visited, matrix[0][0], 0); //start from node 0
  }

  private static void find_min_1(int[][] matrix, int n, int node, boolean[] visited, long dist, int count) {
    //node 0 -> node n-1 -> city 0 should only have n-1 steps
    if (count == n - 1) {
      //come back to node 0 distance: matrix[node][0]
      min1 = Math.min(min1, dist + matrix[node][0]);
      return;
    }

    //from city 0 to n-1
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        visited[i] = true;
        //node -> i distance: matrix[node][i]
        find_min_1(matrix, n, i, visited, dist + matrix[node][i], count + 1);
        visited[i] = false; //reused visited array!
      }
    }
  }

  /**
   * Solution 2
   */
  public static long min2;

  public static void find_min_2(int[][] matrix, int n) {
    int[] visited = new int[n];
    for (int i = 0; i < n; i++) {
      visited[i] = 0;
    }
    find_min_2(matrix, n, 0, visited, matrix[0][0]);

  }

  public static void find_min_2(int[][] matrix, int n, int node, int[] visited, long dist) {
    visited[node] = 1;
    int flag = 1;
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        //System.out.println("node:"+node+", i:"+i);
        //System.out.println("visited:");
        flag = 0;
        find_min_2(matrix, n, i, visited, dist + matrix[node][i]);
        visited[i] = 0;
        continue;
      }
    }
    //System.out.println("done");
    if (flag == 1 && min2 > dist + matrix[node][0]) {
      min2 = dist + matrix[node][0];
    }
    return;
  }

  /**
   * Solution 3
   */
  public static int result;

  public static int solve(int n, int[][] cost) {
    if (n <= 1) {
      return 0;
    }

    int minCost = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i != j) {
          minCost = Math.min(minCost, cost[i][j]);
        }
      }
    }

    result = Integer.MAX_VALUE;

    boolean[] free = new boolean[n];
    Arrays.fill(free, true);
    free[0] = false;

    List<Integer> path = new ArrayList<>();
    path.add(0);

    int currentCost = 0;
    trySolve(path, n, currentCost, minCost, cost, free);
    return result;
  }

  public static void trySolve(List<Integer> path, int n, int currentCost, int minCost, int[][] cost, boolean[] free) {
    int top = path.get(path.size() - 1);
    for (int i = 0; i < n; i++) {
      if (free[i] && currentCost + cost[top][i] + (n - path.size()) * minCost < result) {
        free[i] = false;
        path.add(i);
        currentCost += cost[top][i];
        if (path.size() == n) {
          if (currentCost + cost[i][0] < result) {
            result = currentCost + cost[i][0];
          }
        } else {
          trySolve(path, n, currentCost, minCost, cost, free);
        }
        free[i] = true;
        path.remove(path.size() - 1);
        currentCost -= cost[top][i];
      }
    }//end for i
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testCase = sc.nextInt();
    while (testCase-- > 0) {
      min1 = Long.MAX_VALUE;
      int n = sc.nextInt(); // takes in the input -> number of elements in the matrix

      int[][] matrix = new int[n][n];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          matrix[i][j] = sc.nextInt();
        }
      }

      find_min_1(matrix, n);
      System.out.println(min1);

      find_min_2(matrix, n);
      System.out.println(min2);


    }
  }
}
