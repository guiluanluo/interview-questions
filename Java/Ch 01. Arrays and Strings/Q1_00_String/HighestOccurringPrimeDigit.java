package Q1_00_String;

import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/find-the-highest-occurring-digit-in-prime-numbers-in-a-range/0
 */
public class HighestOccurringPrimeDigit {

  public static int highestOccurringPrimeDigit(int startNum, int endNum) {
    int[] digitCount = new int[10];
    for (int i = 0; i < digitCount.length; i++) {
      digitCount[i] = 0;
    }

    for (int num = startNum; num <= endNum; num++) {
      if (isPrime(num)) {
        int copyNum = num;
        while (copyNum > 0) {
          int d = copyNum % 10;
          digitCount[d]++;
          copyNum = copyNum / 10;
        }
      }
    }

    int maxCount = 0;
    int maxNum = 0;
    for (int j = 0; j < digitCount.length; j++) {
      if (digitCount[j] > maxCount) {
        maxCount = digitCount[j];
        maxNum = j;
      }
    }

    if (maxCount == 1) {
      for (int k = digitCount.length - 1; k > 0; k--) {
        if (digitCount[k] > 0) {
          maxNum = k;
          break;
        }
      }
    } else if (maxCount > 1) {
      return maxNum;
    } else {
      maxNum = -1;
    }
    return maxNum;
  }

  private static boolean isPrime(int n) {
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int count = 0;
    while (count < testCount) {
      String[] arrItems = scanner.nextLine().trim().split(" ");
      int first = Integer.parseInt(arrItems[0]);
      int second = Integer.parseInt(arrItems[1]);
      int result = highestOccurringPrimeDigit(first, second);
      System.out.println(result);
    }
  }
}
