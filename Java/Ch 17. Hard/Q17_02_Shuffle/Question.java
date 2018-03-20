package Q17_02_Shuffle;

import CtCILibrary.AssortedMethods;

/**
 * IMPORTANT!!!
 *
 * Shuffle: write a method to shuffle a deck of cards. it must be a perfect shuffle -- on other words, each of the 52!
 * permutation of the deck has to be equally likely. assume that you are given a random number generator with is
 * perfect.
 *
 * hint: 482: try approaching this problem recursively
 *
 * hint 578: suppose you had a method shuffle() that worked on decks up to n-1 elements. could you use this method to
 * implement a new shuffle() element that works on decks up to n elements?
 *
 * hint 633: you could build this algorithm recursively by swapping the nth element for any of elements before it. what
 * would this look like iteratively?
 *
 * Solution: this is a very know interview question!!! and a well know algorithm. if you are not one fo the lucy few to
 * already know this algorithm, then read on.
 *
 * Let's image our n-element array. suppose it looks like this: [1][2][3][4][5]. using our Base Case Build approach, we
 * can ask this question: suppose we had a method shuffle(...) that worked on n-1 element. could we use this to shuffle
 * n elements? sure, in fact, that's quite easy. we would first shuffle the first n-1 element. then, we would take the
 * nth element and randomly swap it with an element in the array. that's it!!
 *
 * what would this algorithm look like iteratively? let's think about it. all it does is moving through the array and,
 * for each element i, swapping array[i] with a random element between 0-i, inclusive.
 */
public class Question {

  /* Random number between lower and higher, inclusive */
  public static int rand(int lower, int higher) {
    return lower + (int) (Math.random() * (higher - lower + 1));
  }

  public static int[] shuffleArrayRecursively(int[] cards, int i) {
    if (i == 0) {
      return cards;
    }

		/* shuffle elements 0 through index - 1 */
    shuffleArrayRecursively(cards, i - 1); // shuffle earlier part
    int k = rand(0, i); //pick random index to swap with

		/* Swap element k and index */
    int temp = cards[k];
    cards[k] = cards[i];
    cards[i] = temp;

		/* Return shuffled array */
    return cards;
  }

  public static void shuffleArrayIteratively(int[] cards) {
    for (int i = 0; i < cards.length; i++) {
      int k = rand(0, i);
      int temp = cards[k];
      cards[k] = cards[i];
      cards[i] = temp;
    }
  }

  public static void main(String[] args) {
    System.out.println("called shuffleArrayIteratively()....");
    int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println(AssortedMethods.arrayToString(cards));
    shuffleArrayIteratively(cards);
    System.out.println(AssortedMethods.arrayToString(cards));

    System.out.println("called shuffleArrayRecursively()....");
    int[] cards2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println(AssortedMethods.arrayToString(cards2));
    shuffleArrayRecursively(cards2, 9);
    System.out.println(AssortedMethods.arrayToString(cards2));
  }

}
