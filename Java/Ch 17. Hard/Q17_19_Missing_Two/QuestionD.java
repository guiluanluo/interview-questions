package Q17_19_Missing_Two;

public class QuestionD {

  public static int[] missingTwo(int[] array) {
    int size = array.length;
    int n = array[size - 1];
    int addTotalSum = n * (n + 1) / 2;
    int addSum = addSum(array);

    int twoSum = addTotalSum - addSum;
    int missOne = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] != i + 1) {
        missOne = array[i] - 1;
        break;
      }
    }
    int missTwo = twoSum - missOne;

    int[] miss = new int[2];
    miss[0] = missOne;
    miss[1] = missTwo;
    return miss;
  }


  public static int addSum(int[] array) {
    int sum = 0;
    for (int a : array) {
      sum += a;
    }
    return sum;
  }


  public static void main(String[] args) {
    int max = 100;
    for (int x = 1; x < max; x++) {
      for (int y = 1; y < max; y++) {
        if (x != y) {
          int len = max - 2;
          int count = 0;
          int[] array = new int[len];
          for (int i = 1; i <= max; i++) {
            if (i != x && i != y) {
              array[count] = i;
              count++;
            }
          }

          int[] solution = missingTwo(array);

          if ((solution[0] == x && solution[1] == y) ||
              (solution[1] == x && solution[0] == y)) {
            System.out.println("Success: " + solution[0] + ", " + solution[1]);
          } else {
            System.out.println("Error: " + x + ", " + y + " | " + solution[0] + ", " + solution[1]);
          }
        }
      }
    }
  }

}
