package google;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/assignment-problem/0
 *
 * You are the head of a firm and you have to assign jobs to people. You have N persons working under you and you have N
 * jobs that are to be done by these persons. Each person has to do exactly one job and each job has to be done by
 * exactly one person. Each person has his own capability (in terms of time taken) to do any particular job. Your task
 * is to assign the jobs to the persons in such a way that the total time taken is minimum. A job can be assigned to
 * only one person and a person can do only one job.
 *
 * Input: The first line of input contains an integer T denoting the number of test cases. Then T test cases follow. The
 * first line of each test case contains an integer N, where N is the number of jobs and the number of persons under
 * you. The next line contains N2 positive integers. The first N of these integers denote the time taken by the first
 * person to do the N jobs, the next N integers denote the time taken by the second person to do the N jobs and so on
 * till the Nth person.
 *
 * Output: For each test case in a new line, print the time taken in the best possible assignment that you can do.
 *
 * Constrains: 1<=T<=100, 1<=N<=30, 1<=Time taken to do a job <=100
 *
 * Example: Input:
 * 2
 * 2
 * 3 5 10 1
 * 3
 * 2 1 2 9 8 1 1 1 1
 *
 * Output: 4 3
 *
 * Explanation: The first person takes times 3 and 5 for jobs 1 and 2 respectively. The second person takes times 10 and
 * 1 for jobs 1 and 2 respectively. We can see that the optimal assignment will be to give job 1 to person 1 and job 2
 * to person 2 for a total for 3 + 1 = 4.
 */
public class AssignmentProblem {

  private static int[] initArray(int n, int initVal) {
    int[] selectColumn = new int[n];
    for (int i = 0; i < selectColumn.length; i++) {
      selectColumn[i] = initVal;
    }
    return selectColumn;
  }

  private static int getSum(int[][] matrix, int[] selectColumn) {
    int sum = 0;
    for (int j = 0; j < selectColumn.length; j++) {
      sum += matrix[j][selectColumn[j]];
    }
    return sum;
  }

  private static List<Integer> getUsedColumns(int row, int[] selectColumn) {
    List<Integer> usedColumn = new ArrayList<>();
    for (int i = selectColumn.length - 1; i > row; i--) {
      if (selectColumn[i] != -1) {
        usedColumn.add(selectColumn[i]);
      }
    }
    return usedColumn;
  }

  public static int findMinTime(int[][] matrix, int n) {
    int[] selectColumn = initArray(n, -1);
    return findMinTime(matrix, n, n - 1, n - 1, selectColumn);
  }

  private static int findMinTime(int[][] matrix, int n, int row, int column, int[] selectColumn) {
    if (row < 0) {
      return getSum(matrix, selectColumn);
    }

    int minTime = Integer.MAX_VALUE;
    List<Integer> usedColumn = getUsedColumns(row, selectColumn);
    for (int c = column; c >= 0; c--) {
      if (!usedColumn.contains(c)) {
        selectColumn[row] = c;
        minTime = Math.min(findMinTime(matrix, n, row - 1, column, selectColumn), minTime);
      }
    }
    return minTime;
  }


  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      int n = Integer.parseInt(scanner.nextLine().trim());
      String[] input = scanner.nextLine().trim().split(" ");
      int[][] matrix = new int[n][n];

      int row;
      int column;
      for (int j = 0; j < n * n; j++) {
        row = j / n;
        column = j % n;
        matrix[row][column] = Integer.parseInt(input[j]);
      }
      int result = findMinTime(matrix, n);
      System.out.println(result);
      count++;
    }
  }
}