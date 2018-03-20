package Q1_04_Palindrome_Permutation;

/*
 * Palindrome Permutation: given a string, write a function to check if it is a permutation of palindrome. A palindrome
 * is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. the
 * palindrome does not need to be limited to just dictionary words.
 *
 * Example:  input: Tact Coa    Output: true(permutation: "taco cat", "atco cta", etc)
 * Hint 106: you don't have to - and should not -- generate all permutations. This would be very ineffective.
 * hint 121: what characteristics would a string that is a permutation of a palindrome have?
 * hint 134: have you tried a hash table? you should be abel to get this down to O(N) time.
 * hint 136: can you reduce the space usage by using a bit vector?
 *
 * Lucy note: a palindrome is a string that is the same forwards and backwards. Therefore, to decide if a string is a
 * permutation of a palindrome, we need to know if it can be written such that its the same forwards and backwards. what
 * does it take to be able to write a set of characters the same way forwards and backwards? we need to have even number
 * of almost all characters except the middle character
 *
 * for example: we know "tactcoapapa" is a permutation of palindrome because it has 2 T, 4 A, 2 C, 2 P, and 1 O. that O
 * would be the center of all possible palindromes
 *
 * Solution A: we use a hash table to count how many times each character appears. then, we iterate through the hash
 * table and ensure that no more than one character has an odd count. this algorithm takes O(N) time, where N is the
 * length of the string
 */
public class QuestionA {

  public static boolean checkMaxOneOdd(int[] table) {
    boolean foundOdd = false;
    for (int count : table) {
      if (count % 2 == 1) {
        if (foundOdd) {
          return false;
        }
        foundOdd = true;
      }
    }
    return true;
  }

  public static boolean isPermutationOfPalindrome(String phrase) {
    int[] table = Common.buildCharFrequencyTable(phrase);
    return checkMaxOneOdd(table);
  }

  public static void main(String[] args) {
    String pali = "Rats live on no evil star";
    System.out.println(isPermutationOfPalindrome(pali));
  }


}
