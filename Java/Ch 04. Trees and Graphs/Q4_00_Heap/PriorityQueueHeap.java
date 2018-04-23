package Q4_00_Heap;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueHeap {

  public static void main(String[] args) {
    //create a new object and add a custom comparator that provides the "MinHeap" behaviour.
    PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
    //add elements
    minheap.addAll(Arrays.asList(new Integer[]{8, 9, 1, 2, 3, 4, 5,}));
    System.out.println("Minheap---------------------");
    System.out.println(Arrays.toString(minheap.toArray()));
    for (Iterator iterator = minheap.iterator(); iterator.hasNext(); ) {
      System.out.println("Min : " + minheap.element());
      System.out.println("Removing " + minheap.element());
      minheap.remove();
    }


    //create a new object and add a custom comparator that provides the "MaxHeap" behaviour.
    PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
    System.out.println("Maxheap---------------------");
    //add elements
    maxheap.addAll(Arrays.asList(new Integer[]{8, 9, 1, 2, 3, 4, 5,}));
    System.out.println(Arrays.toString(maxheap.toArray()));
    for (Iterator iterator = maxheap.iterator(); iterator.hasNext(); ) {
      System.out.println("Max : " + maxheap.element());
      System.out.println("Removing " + maxheap.element());
      maxheap.remove();
    }
  }
}
