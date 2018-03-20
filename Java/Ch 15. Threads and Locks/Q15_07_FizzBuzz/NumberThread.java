package Q15_07_FizzBuzz;

public class NumberThread extends FizzBuzzThread {

  public NumberThread(String name, boolean div3, boolean div5, int max) {
    super(name, div3, div5, max, null);
  }

  public void print() {
    System.out.println(current);
  }
}
