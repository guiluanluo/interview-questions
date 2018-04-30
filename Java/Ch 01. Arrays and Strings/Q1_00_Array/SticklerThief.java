package Q1_00_Array;

import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/stickler-theif/0
 */
public class SticklerThief {

  public static int findMaxSum(int[] arr) {
    int n = arr.length;
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return arr[0];
    } else if (n == 2) {
      return Math.max(arr[0], arr[1]);
    } else {
      int[] dp = new int[n];
      dp[0] = arr[0];
      dp[1] = Math.max(arr[0], arr[1]);
      for (int i = 2; i < dp.length; i++) {
        dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
        System.out.println("dp[" + i+ "]:" + dp[i]);
      }
      return dp[n - 1];
    }
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      int arrCount = Integer.parseInt(scanner.nextLine().trim());
      int[] arr = new int[arrCount];

      String[] arrItems = scanner.nextLine().split(" ");
      for (int arrItr = 0; arrItr < arrCount; arrItr++) {
        int arrItem = Integer.parseInt(arrItems[arrItr].trim());
        arr[arrItr] = arrItem;
      }

      int result = findMaxSum(arr);
      System.out.println(result);

      count++;
    }
  }
}
