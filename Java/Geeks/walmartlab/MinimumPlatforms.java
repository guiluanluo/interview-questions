package walmartlab;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/minimum-platforms/0
 *
 * Given arrival and departure times of all trains that reach a railway station, find the minimum number of platforms
 * required for the railway station so that no train waits.
 *
 * Input: The first line will contain a number T, the number of test cases. Each test case will contain an integer N,
 * the number of trains. Next two lines will consist of N space separated time intervals denoting arrival and departure
 * times respectively. NOTE: Time intervals are in the 24-hour format(hhmm), preceding zeros are insignificant. Consider
 * the example for better understanding of input.
 *
 * Output: For each test case, print the minimum number of platforms required in the newline.
 *
 * Constraints: 1<=T<=100 1<=N<=1000 1<=A[i]<=2359
 *
 * Example: INPUT: 1 6
 * 900  940 950  1100 1500 1800
 * 910 1200 1120 1130 1900 2000
 * OUTPUT: 3
 */
public class MinimumPlatforms {

  private static void solution(int[] starts, int[] ends) {
    Arrays.sort(starts);
    Arrays.sort(ends);

    int platNeeded = 0;
    int max = 0;

    for (int s = 0, e = 0; s < starts.length; ) {
      if (starts[s] < ends[e]) {
        platNeeded++;
        max = Math.max(max, platNeeded);
        s++;
      } else {
        platNeeded--;
        e++;
      }
    }

    System.out.println(max);
  }

  public static void main(String[] args) throws Exception {
    try (Scanner scanner = new Scanner(System.in)) {
      int cases = scanner.nextInt();

      for (int c = 0; c < cases; c++) {
        int size = scanner.nextInt();
        int[] start = new int[size];
        int[] end = new int[size];

        for (int i = 0; i < size; i++) {
          start[i] = scanner.nextInt();
        }
        for (int i = 0; i < size; i++) {
          end[i] = scanner.nextInt();

          if (end[i] < start[i]) {
            end[i] += 2400;
          }
        }

        solution(start, end);
      }
    }
  }
}
