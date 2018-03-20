package Q16_11_Diving_Board;

import java.util.HashSet;

/**
 * Diving board: you are building a diving board by placing a bunch of planks of wood end-to-end. there are two types of
 * planks, one of length shorter and one of length longer. you must use exactly k planks of wood. write a method to
 * generate all possible lengths for the diving board.
 *
 * hint 689: consider building a diving board. what are the choices you make?
 *
 * hint 699: consider a recursive solution
 *
 * hint 714: once you have a recursive solution, think about the runtime. can you make this faster?
 *
 * hint 721: consider memoization to optimize the runtime. think carefully about what exactly you cache. what is the
 * runtime? the runtime is closely related to the max size of the table
 *
 * hint 739: there's an alternate, clever(and very fast) solution. you can actually do this in linear time without
 * recursion. how?
 *
 * hint 746: think about it this way. you are picking K planks and there are two different types. all choices with 10 of
 * the first type and 4 of the second type will have the same sum. can you just iterate through all possible choices
 *
 * Solution: onw way to approach this is to think about the choices we make as we're building a diving board. this leads
 * us to a recursive algorithm
 *
 * Recursive solution: we can image ourselves building a diving board. we make K decisions, each time choose which plank
 * we will put on next. once we've put on K planks, we have a complete diving board and we can add this to the
 * list(assuming we have not seen this length before). we can follow this logic to write recursive code. note that we
 * don't need to track the sequence of planks. all we need to know is the current length and the number of planks
 * remaining.
 *
 * this algorithm takes O(2 power of k) time, since there are two choices at each recursive call and we recurse to a
 * depth of K.
 */
public class QuestionA {

  public static int counter = 0;

  public static HashSet<Integer> allLengths(int k, int shorter, int longer) {
    HashSet<Integer> lengths = new HashSet<Integer>();
    getAllLengths(k, 0, shorter, longer, lengths);
    return lengths;
  }

  public static void getAllLengths(int k, int total, int shorter, int longer, HashSet<Integer> lengths) {
    counter++;
    if (k == 0) {
      lengths.add(total);
      return;
    }
    getAllLengths(k - 1, total + shorter, shorter, longer, lengths);
    getAllLengths(k - 1, total + longer, shorter, longer, lengths);
  }

  public static void main(String[] args) {
    HashSet<Integer> lengths = allLengths(5, 1, 3);
    System.out.println(lengths.toString());

    System.out.println(counter);
  }

}
