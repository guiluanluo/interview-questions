package Q8_05_Recursive_Multiply;

/**
 * we could make it faster than Solution B. for even numbers, we just divide smaller by 2 and double the result of the
 * recursive call. on odd numbers, we do the same, but then we also add bigger to this result.
 *
 * in doing so, we have an unexpected "win". our minProduct() just recursive straight downwards, with increasingly small
 * numbers each time. it will never repeat the same call, so there's no need to cache any information.
 */
public class QuestionC {

  public static int counter = 0;

  public static int minProductHelper(int smaller, int bigger) {
    if (smaller == 0) {
      return 0;
    } else if (smaller == 1) {
      return bigger;
    }

    int s = smaller >> 1;
    int halfProd = minProductHelper(s, bigger);

    if (smaller % 2 == 0) {
      counter++;
      return halfProd + halfProd;
    } else {
      counter += 2;
      return halfProd + halfProd + bigger;
    }
  }


  public static int minProduct(int a, int b) {
    int bigger = a < b ? b : a;
    int smaller = a < b ? a : b;

    return minProductHelper(smaller, bigger);
  }

  public static void main(String[] args) {
    int a = 13494;
    int b = 22323;
    int product = a * b;
    int minProduct = minProduct(a, b);
    if (product == minProduct) {
      System.out.println("Success: " + a + " * " + b + " = " + product);
    } else {
      System.out.println("Failure: " + a + " * " + b + " = " + product + " instead of " + minProduct);
    }
    System.out.println("Adds: " + counter);
  }

}
