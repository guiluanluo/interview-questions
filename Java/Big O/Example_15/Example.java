package Example_15;

/**
 * print all Fibonacci numbers from 0 to n
 */
public class Example {

  public static void allFib(int n) {
    for (int i = 0; i < n; i++) {
      System.out.println(i + ":" + fib(i));
    }
  }

  public static int fib(int n) {
    if (n <= 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }

    return fib(n - 1) + fib(n - 2);
  }


  public static void allFib_store(int n) {
    int[] memo = new int[n + 1];
    for (int i = 0; i < n; i++) {
      System.out.println(i + ":" + fib_store(i, memo));
    }
  }

  public static int fib_store(int n, int[] memo) {
    if (n <= 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    } else if (memo[n] > 0) {
      return memo[n];
    }

    memo[n] = fib_store(n - 1, memo) + fib_store(n - 2, memo);
    return memo[n];
  }

  public static void main(String[] args) {

    allFib(5);

    System.out.println("============\n");

    allFib_store(5);
  }


}
