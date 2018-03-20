package Introduction;

/**
 * there are many ways you might divide a problem into sub-problem. three of the most common approaches to develop an
 * algorithm are "bottom-up, top-down, and half-half"
 *
 * Bottom-up approach is often the most intuitive. we start with knowing how to solve the problem for a simple case,
 * like a list with only one element. then we figure out how to solve the problem for two elements, then for three
 * elements, and so on. the key here is to think about how you can build the solution for one case off of the previous
 * case(or multiple previous cases).
 *
 * Top-down approach can be more complex since it's less concrete. but sometimes, it is the best way to think about the
 * problem: divide the problem for case N into sub-problems.
 *
 * Half-half is often divide the data set in half
 *
 * Solution A takes roughly O(log N) runtime
 */
public class FibonacciA {

  public static int fibonacci(int i) {
    System.out.println("call fibonacci(" + i + ")");
    if (i == 0) {
      return 0;
    }

    if (i == 1) {
      return 1;
    }

    return fibonacci(i - 1) + fibonacci(i - 2);
  }

  public static void main(String[] args) {
    int max = 5; // WARNING: If you make this above 40ish, your computer may serious slow down.
    int trials = 1; // Run code multiple times to compute average time.
    double[] times = new double[max]; // Store times

    for (int j = 0; j < trials; j++) { // Run this 10 times to compute
      for (int i = 0; i < max; i++) {
        long start = System.currentTimeMillis();
        fibonacci(i);
        long end = System.currentTimeMillis();
        long time = end - start;
        times[i] += time;
      }
    }

    for (int j = 0; j < max; j++) {
      System.out.println(j + ": " + times[j] / trials + "ms");
    }
  }

  public static void main0(String[] args) {
    int max = 35; // WARNING: If you make this above 40ish, your computer may serious slow down.
    int trials = 10; // Run code multiple times to compute average time.
    double[] times = new double[max]; // Store times

    for (int j = 0; j < trials; j++) { // Run this 10 times to compute
      for (int i = 0; i < max; i++) {
        long start = System.currentTimeMillis();
        fibonacci(i);
        long end = System.currentTimeMillis();
        long time = end - start;
        times[i] += time;
      }
    }

    for (int j = 0; j < max; j++) {
      System.out.println(j + ": " + times[j] / trials + "ms");
    }
  }

}
