package Q8_04_Power_Set;

import java.util.ArrayList;

/*
 * Power set: write a method to return all subsets of a set.
 *
 * hint 273: how can you build all subsets of {a,b,c} from the subsets of {a,b}
 * hint 290: anything that is a subset of {a,b} is also a subset of {a,b,c}. which sets are subsets of {a,b,c} but not {a,b}
 * hint 338: subsets that contain c will be subsets {a,b,c} but not {a,b}. can you build these subsets from the subsets of {a,b}
 * hint 354: you can build the remaining subsets by adding c to all the subsets of {a,b}
 * hint 373: you can also do this by mapping each subset to a binary number. the ith bit could represent a "boolean" flag for whether an element is in the set.
 *
 * Solution 1: recursion. this problem is a good candidate for the Base Case and Build approach. image that we are
 * trying to find all subsets of a set link S ={a1, a2,..., an} are also call ed the Power set , P{a1, a2,..., an} or
 * just P(n).
 * Base Case: n=0   there is just one subset of the empty set: {}
 * Case: n=1        there are 2 subsets of the set {a1}: {},{a1}
 * Case: n=2        there are 4 subset of the set {a1,a2}: {},{a1},{a2}, {a1,a2}
 * Case: n=3        there are 8 subset of the set {a1,a2,a3}: {},{a1},{a2}, {a1,a2},{a3},{a1,a3},{a2,a3}, {a1,a2,a3}
 *
 * we want to find a way of generating the solution for n=3 based on the prior solutions. the different between these
 * solutions is that P(2) is missing the all the subsets containing a3. how we can use P(2) to create P(3)? we can simple
 * clone the subset of P(2) and add a3 to them!!
 *
 * How this recursive work?
 * getSubsets(0)
 *   getSubsets(1)
 *     getSubsets(2)
 *        getSubsets(3)
 *            return {}
 *     return 2 + subsets(3) -> {},{2}
 *   return 1 + subsets(2) -> {1},{1,2}, {},{2}
 * return 0 + subsets(1) -> {0,1},{0,1,2},{0},{1,2}, {1},{1,2}, {},{2}
 *
 */
public class QuestionA {

  public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
    System.out.println("Call getSubsets(set: " + set + ", index: " + (index) + ")");

    ArrayList<ArrayList<Integer>> allsubsets;

    if (set.size() == index) { // Base case - add empty set: reach the last element!!
      allsubsets = new ArrayList<ArrayList<Integer>>();
      allsubsets.add(new ArrayList<Integer>());
      System.out.println("set.size() == " + index + ", add empty set {}");

    } else {
      //System.out.println("Call getSubsets(set: " + set + ", index + 1: " + (index + 1) + ")");
      allsubsets = getSubsets(set, index + 1);
      System.out.println("before add item, allsubsets of getSubsets(set, " + (index + 1) + "): " + allsubsets);

      //to create P(index+1), we can simple clone the subset of P(index) and add a3 to them!!
      int item = set.get(index);
      System.out.println("item: " + item + ", index:" + index + ", set:" + set);
      ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
      for (ArrayList<Integer> subset : allsubsets) {
        ArrayList<Integer> newsubset = new ArrayList<Integer>();
        newsubset.addAll(subset);
        newsubset.add(item);
        moresubsets.add(newsubset);
      }
      allsubsets.addAll(moresubsets);
    }

    System.out.println("returned allsubsets:" + allsubsets + "\n");
    return allsubsets;
  }

  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < 3; i++) {
      list.add(i);
    }

    //start from index = 0
    ArrayList<ArrayList<Integer>> subsets = getSubsets(list, 0);
    System.out.println(subsets.toString());
  }

}
