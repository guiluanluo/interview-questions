package Q10_02_Group_Anagrams;

/**
 * Group Anagrams: write a method to sort an array of strings so that all the anagrams are next to each other.
 *
 * hint 177: how do you check if two words are anagrams of each other? think about what the definition of "anagram" is.
 * explain it in your own words.
 *
 * hint 182: two words are anagrams if they contain the same characters but in different orders. how can you put
 * characters in order?
 *
 * hint 263: can you leverage a standard sorting algorithm?
 *
 * hint 342: do you even need to truly "sort"? or is just reorganizing the list sufficient?
 *
 * Solution: anagrams are words that have the same characters, but in different orders. what is the easiest way of
 * checking if two words are anagrams? we could count the occurences of the distinct characters in each string and
 * return true if they match. OR we could just sort the string. after all, two words which are anagrams will look the
 * same once they are sorted.
 */

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

public class Question {

  public static void main(String[] args) {
    String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
    System.out.println(AssortedMethods.stringArrayToString(array));

    Arrays.sort(array, new AnagramComparator());
    System.out.println(AssortedMethods.stringArrayToString(array));
  }
}
