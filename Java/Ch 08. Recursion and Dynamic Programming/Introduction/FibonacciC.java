package Introduction;

/**
 * Bottom-up Dynamic Programing
 *
 * Thinks about doing the same things as the recursive memoized approach, but in reverse. first, we compute fib(1) and
 * fib(0), which are already known from the base cases. then we use those to fompute fib(2). then we use the prior
 * answers to compute fib(3), then fib(4), and so on
 */
public class FibonacciC {

  public static int fibonacci(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }

    int[] memo = new int[n];
    memo[0] = 0;
    memo[1] = 1;

    for (int i = 2; i < n; i++) {
      memo[i] = memo[i - 1] + memo[i - 2];
    }
    return memo[n - 1] + memo[n - 2];
  }


  public static void main(String[] args) {
    int max = 100; // Make this as big as you want! (Though you'll exceed the bounds of a long around 46)
    int trials = 10; // Run code multiple times to compute average time.
    double[] times = new double[max]; // Store times

    for (int j = 0; j < trials; j++) { // Run this 10 times to compute
      for (int i = 0; i < max; i++) {
        long start = System.currentTimeMillis();
        System.out.println("fibonacci(" + i + "): " + fibonacci(i));
        long end = System.currentTimeMillis();
        long time = end - start;
        times[i] += time;
      }
    }

    for (int j = 0; j < max; j++) {
      //System.out.println(j + ": " + times[j] / trials + "ms");
    }
  }

}
