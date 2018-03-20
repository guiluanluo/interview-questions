package Addtional_Problems;


public class Question {

  //code like this would take O(n) time and O(n) space
  //compute the product of a and b
  public static int product(int a, int b) {
    int sum = 0;
    for (int i = 0; i < b; i++) {
      sum += a;
    }
    return sum;
  }

  //compute a power b
  public static int power(int a, int b) {
    if (b < 0) {
      return 0; //error
    } else if (b == 0) {
      return 1;
    } else {
      return a * power(a, b - 1);
    }
  }


  public static void main(String[] args) {
    System.out.println("2*3 = " + product(2, 3));

    System.out.println("2 power 3 = " + power(2, 7));
  }

}
