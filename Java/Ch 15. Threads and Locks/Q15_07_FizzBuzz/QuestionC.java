package Q15_07_FizzBuzz;

public class QuestionC {

  public static void main(String[] args) {
    int n = 50;
    Thread[] threads = {new FBThread("thread1", i -> i % 3 == 0 && i % 5 == 0, i -> "FizzBuzz", n),
        new FBThread("thread2", i -> i % 3 == 0 && i % 5 != 0, i -> "Fizz", n),
        new FBThread("thread3", i -> i % 3 != 0 && i % 5 == 0, i -> "Buzz", n),
        new FBThread("thread4", i -> i % 3 != 0 && i % 5 != 0, i -> Integer.toString(i), n)};
    for (Thread thread : threads) {
      thread.start();
    }
  }

}
