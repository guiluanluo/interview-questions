package Q10_11_Peaks_and_Valleys;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

/*
 * Peaks and valleys: in an array of integers, a "peak" is an element which is greater than or equal to the adjacent
 * integers and a "valley" is an element which is an element which is less than or equal to the adjacent integers. for
 * example, in the array {5,8,6,2,3,4,6}, {8,6} are peaks and {5,2} are valleys. given an array of integers, sort the
 * array into an alternating sequence of peaks and valleys.
 *
 * Example: input(5,3,1,2,3}   output: {5,1,3,2,3}
 *
 * hint 196:  imagine the array were sorted in ascending order. is there any way you could "fix it" to be sorted into
 * alternating peaks and valleys?
 *
 * hint 219: try walking through a sorted array. can you just swap elements until you have fixed the array?
 *
 * hint 231:not that if you ensure the peaks are in place, the valleys will be, too. therefore, your iteration to fin
 * the array an skip over every other element.
 *
 * hint 252: what happens if you put one pill from each bottle on the scale? what if you put two pills from each bottle
 * on the scale?
 *
 * hint 277: suppose you had a sequence of three elements{0,1,2}, in any order. write out all possible sequences for
 * those elements and how you can fix them to make 1 the peak
 *
 * hint 292: revisit the set of sequences for {0,1,2} that you just wrote out. image there are elements before the
 * leftmost element. are you sure that the way you swap the elements won't invalidate the previous pare of the array?
 *
 * hint 316: you should be able to design an O(n) algorithm
 *
 * Solution: since this problem asks us to sort the array in a particular way, one thing we can try is doing a normal
 * sort and then "fixing" the array into an alternation sequence of peaks and valleys.
 *
 * for example we have a sort array: {0,1,4,7,8,9}. how can we rearrange this into a proper alternating sequence of
 * peaks & valleys? let's walk through and try to do that:
 * 1) the 0 is ok;
 * 2) the 1 is at the wrong place, swap it with 0 or 4, let's swap it with 0 --> {1,0,4,7,8,9}
 * 3) the 4 is ok;
 * 4) the 7 is at the wrong place, swap it with 4 or 8, let's swap it with 4 --> {1,0,7,4,8,9}
 * 5) the 9 is at the wrong place, swap it with 8 --> {1,0,7,4,9,8}
 */
public class QuestionA {

  public static void sortValleyPeak(int[] array) {
    Arrays.sort(array);

    for (int i = 1; i < array.length; i += 2) {
      swap(array, i - 1, i);
    }
  }

  public static void swap(int[] array, int left, int right) {
    int temp = array[left];
    array[left] = array[right];
    array[right] = temp;
  }

  public static void main(String[] args) {
    int[] array = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
    System.out.println(AssortedMethods.arrayToString(array));

    sortValleyPeak(array);
    System.out.println(AssortedMethods.arrayToString(array));

    System.out.println(Tester.confirmValleyPeak(array));
  }

}
