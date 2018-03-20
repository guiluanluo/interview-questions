package Q16_02_Word_Frequencies;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

/**
 * Word frequencies: design a method to find the frequency of occurrences of any given word in a book. what if we were
 * running this algorithm multiple times?
 *
 * hint 488: think about what the best conceivable runtime is for this problem. if your solution matches the best
 * conceivable runtime, then you probably can't do any better
 *
 * hint 535: can you use a hash table to optimize the repeated case?
 */
public class QuestionA {

  public static int getFrequency(String[] book, String word) {
    word = word.trim().toLowerCase();
    int count = 0;
    for (String w : book) {
      if (w.trim().toLowerCase().equals(word)) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    String[] wordlist = AssortedMethods.getLongTextBlobAsStringList();
    System.out.println("wordlist:" + Arrays.asList(wordlist));

    String[] words = {"the", "Lara", "and", "outcropping", "career", "it"};
    for (String word : words) {
      System.out.println(word + ": " + getFrequency(wordlist, word));
    }
  }

}
