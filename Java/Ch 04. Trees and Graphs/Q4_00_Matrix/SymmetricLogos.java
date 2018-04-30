package Q4_00_Matrix;

/**
 * https://www.hackerearth.com/practice/data-structures/arrays/multi-dimensional/practice-problems/algorithm/roy-and-symmetric-logos-1/
 */


import java.util.Scanner;


public class SymmetricLogos {

  public static boolean isSymmetric(int[][] metrix, int n) {
    if (n == 1) {
      return false;
    }
    if (n == 2) {
      if (metrix[0][0] != metrix[0][1] || metrix[1][0] != metrix[1][1]) {
        return false;
      }
    }

    //check Y-axis
    for (int r = 0; r < n / 2; r++) {
      int cstartIdx = 0;
      int cendIdx = n - 1;
      while (cstartIdx < cendIdx) {
        if (metrix[r][cstartIdx] != metrix[r][cendIdx]) {
          return false;
        }
        cstartIdx++;
        cendIdx--;
      }
    }

    //check X-axis
    for (int c = 0; c < n / 2; c++) {
      int rstartIdx = 0;
      int rendIdx = n - 1;
      while (rstartIdx < rendIdx) {
        if (metrix[rstartIdx][c] != metrix[rendIdx][c]) {
          return false;
        }
        rstartIdx++;
        rendIdx--;
      }
    }
    return true;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String args[]) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

    // Write your code here
    int testCount = Integer.parseInt(scanner.nextLine().trim());
    int n = Integer.parseInt(scanner.nextLine().trim());
    int[][] metrix = new int[n][n];

    int r = 0;
    while (r < n) {
      String[] items = scanner.nextLine().trim().split(" ");
      for (int c = 0; c < n; c++) {
        metrix[r][c] = Integer.parseInt(items[c]);
      }
      r++;
    }

    boolean result = isSymmetric(metrix, n);
    System.out.println(result);
  }
}
