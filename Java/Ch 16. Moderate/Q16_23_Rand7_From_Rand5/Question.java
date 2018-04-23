package Q16_23_Rand7_From_Rand5;

/*
 * Rand7 from Rand5: implement a method rand7() given rand5(). that is, given a method that generates a random integer
 * between 0 and 4(inclusive), write a method that generate a random integer between 0 and 6(inclusive).
 *
 * hints 504 be very careful that your solution actually return each value from 0 through 6 with equal probability.
 *
 * hint 573: start with a brute force solution. how many times does it call rand5() in the worst case?
 *
 * hint 636:  make a table indicating how each possible sequence of calls to rand5() would map to the result fo rand7().
 * for example, if you were implementing rand3() with (rand2() + rand2())%/3, then the table would look like the below.
 * analyze this table, what can it tell you?
 *  1st   2nd   result
 *  0     0     0
 *  0     1     1
 *  1     0     1
 *  1     1     2
 *
 * hint 667: looking at the table again, note that the number of rows will be 5 power of k, where k is the max number of
 * calls to rand5(). in order to make each value between 0 and 6 have equal probability, 1/7th of the rows must map to
 * 0, 1/7th to 1, and so on. is this possible?
 *
 * hint 696: it is not possible to divide 5 power of k evenly by 7. does this mean that you can't implement rand7() with
 * rand5()?
 *
 * hint 719:you can implement rand7() with rand5(), you just can't do it deterministically (such that you know it will
 * definitely terminate after a certain number of calls). given this, write a solution that works.
 *
 * Solution: it implement this function correctly, we must have each of value between 0-6 return with 1/7 probability
 * fixed number of calls: (rand5+rand5())/7, two calls, 5*5 probability, unfortunately, it does not generate the values
 * with equal probability.
 *
 * nodeterministic number of calls: we gererate the rang 0-24 by doing 5 * rand5() + rand5(). then we discard the values
 * between21-24 since they would otherwise make rand7() unfairly weighted toward 0 through 3. finally we mode by 7 to
 * give us the values in the range 0-6 with equal probability
 */
public class Question {

  public static int rand7() {
    while (true) {
      int num = 5 * rand5() + rand5();
      if (num < 21) {
        return num % 7;
      }
    }
  }

  public static int rand5() {
    return (int) (Math.random() * 100) % 5;
  }

  public static void main(String[] args) {
    /* Test: call rand7 many times and inspect the results. */
    int[] arr = new int[7];
    int test_size = 1000000;
    for (int k = 0; k < test_size; k++) {
      arr[rand7()]++;
    }

    for (int i = 0; i < 7; i++) {
      double percent = 100.0 * arr[i] / test_size;
      System.out.println(i + " appeared " + percent + "% of the time.");
    }
  }
}
