package Q1_04_Palindrome_Permutation;

/**
 * We can not optimize th big O time here since any algorithm will always have to look through the entire string.
 * however we can make some smaller incremental improvements. because this is a relatively simple problem, it can be
 * worthwhile to discuss some small optimizations or at lease some tweaks.
 *
 * instead of checking the number of odd count a the end, we can check as we go along. then, as soon as we get to the
 * end, we have our answer.
 */
public class QuestionB {

  public static boolean isPermutationOfPalindrome(String phrase) {
    int countOdd = 0;

    int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

    for (char c : phrase.toCharArray()) {
      int x = Common.getCharNumber(c);
      if (x != -1) {
        table[x]++;

        if (table[x] % 2 == 1) {
          countOdd++;
        } else {
          countOdd--;
        }
      }
    }
    return countOdd <= 1;
  }

  public static void main(String[] args) {
    String pali = "Ratzs live on no evil starz";
    System.out.println(isPermutationOfPalindrome(pali));
    String pali2 = "Zeus was deified, saw Suez";
    System.out.println(isPermutationOfPalindrome(pali2));
  }


}
