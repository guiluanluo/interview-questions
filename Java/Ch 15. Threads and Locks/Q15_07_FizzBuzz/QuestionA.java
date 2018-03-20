package Q15_07_FizzBuzz;

/**
 * FizzBuzz: in the classic problem FixxBuzz, you are told to print the numbers from 1 to n. however, when the number is
 * divisible by 3, print "Fizz". when it is divisible by 5, print "Buzz". when it is divisible by 3 and 5, print
 * "FizzBuzz". in this problem, you are asked to do this in a multithreaded way. implement a multithreaded version of
 * FizzBuzz with four threads. on thread checks for divisibility of 3 and prints "Fizz". another thread is responsible
 * for divisibility of 5 and prints "Buzz". a third thread is responsible for divisibility of 3 and 5 and print
 * "FizzBuzz". a four thread does the numbers.
 *
 * Solution A: single thread version
 *
 * Solution B: multiple threads version
 *
 * Solution C: multiple threads with Java 8 version
 */

public class QuestionA {

  public static void fizzbuzz(int n) {
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        System.out.println("FizzBuzz");
      } else if (i % 3 == 0) {
        System.out.println("Fizz");
      } else if (i % 5 == 0) {
        System.out.println("Buzz");
      } else {
        System.out.println(i);
      }
    }
  }

  public static void main(String[] args) {
    fizzbuzz(50);
  }

}
