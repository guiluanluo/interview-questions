package Q15_07_FizzBuzz;

public class QuestionB {

  public static void main(String[] args) {
    int n = 50;
    Thread[] threads = {new FizzBuzzThread("thread1", true, true, n, "FizzBuzz"),
        new FizzBuzzThread("thread2",true, false, n, "Fizz"),
        new FizzBuzzThread("thread3",false, true, n, "Buzz"),
        new NumberThread("thread-number",false, false, n)};
    for (Thread thread : threads) {
      thread.start();
    }
  }

}
