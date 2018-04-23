package Q4_00_Heap;

/*
 * https://algorithms.tutorialhorizon.com/binary-min-max-heap/
 *
 * A binary heap is a heap data structure created using a binary tree. Binary tree has two rules –
 * 1) Binary Heap has to be complete binary tree at all levels except the last level. This is called shape property.
 * 2) All nodes are either greater than equal to (Max-Heap) or less than equal to (Min-Heap) to each of its child nodes.
 * This is called heap property. Implementation:
 *
 * 1) Use array to store the data.
 * 2) Start storing from index 1, not 0.
 * 3) For any given node at position i:
 * 4) Its Left Child is at [2*i] if available.
 * 5) Its Right Child is at [2*i+1] if available.
 * 6) Its Parent Node is at [i/2] if available.
 */
public class MaxHeap {

  public int capacity;
  public int[] mH;
  public int currentSize;

  public MaxHeap(int capacity) {
    this.capacity = capacity;
    mH = new int[capacity + 1];
    currentSize = 0;
  }

  public void createHeap(int[] arrA) {
    if (arrA.length > 0) {
      for (int i = 0; i < arrA.length; i++) {
        insert(arrA[i]);
      }
    }
  }

  public void display() {
    for (int i = 1; i < mH.length; i++) {
      System.out.print(" " + mH[i]);
    }
    System.out.println("");
  }

  /*
   * Insert Operation:
   * 1) Add the element at the bottom leaf of the Heap.
   * 2) Perform the Bubble-Up operation.
   * 3) All Insert Operations must perform the bubble-up operation(it is also called as up-heap, percolate-up, sift-up,
   * trickle-up, heapify-up, or cascade-up)
   */
  public void insert(int x) {
    if (currentSize == capacity) {
      System.out.println("heap is full");
      return;
    }
    currentSize++;
    int idx = currentSize;
    mH[idx] = x;
    bubbleUp(idx);
  }

  /*
   * Bubble-up Operation:
   * 1) If inserted element is smaller than its parent node in case of Min-Heap OR greater than its parent node
   *  in case of Max-Heap, swap the element with its parent.
   * 2) Keep repeating the above step, if node reaches its correct position, STOP.
   */
  public void bubbleUp(int pos) {
    int parentIdx = pos / 2;
    int currentIdx = pos;
    while (currentIdx > 0 && mH[parentIdx] > mH[currentIdx]) {
      swap(currentIdx, parentIdx);
      currentIdx = parentIdx;
      parentIdx = parentIdx / 2;
    }
  }

  /*
   * Extract-Min OR Extract-Max Operation:
   * 1) Take out the element from the root.( it will be minimum in case of Min-Heap and maximum in case of Max-Heap).
   * 2) Take out the last element from the last level from the heap and replace the root with the element.
   * 3) Perform Sink-Down All
   * 4) delete operation must perform Sink-Down Operation ( also known as bubble-down, percolate-down, sift-down,
   *   trickle down, heapify-down, cascade-down).
   */
  public int extractMin() {
    int min = mH[1];
    mH[1] = mH[currentSize];
    mH[currentSize] = 0;
    sinkDown(1);
    currentSize--;
    return min;
  }

  /*
   * Sink-Down Operation:
   * 1) If replaced element is greater than any of its child node in case of Min-Heap OR smaller than any if its child node
   *  in case of Max-Heap, swap the element with its smallest child(Min-Heap) or with its greatest child(Max-Heap).
   * 2) Keep repeating the above step, if node reaches its correct position, STOP.
   */
  public void sinkDown(int k) {
    int smallest = k;
    int leftChildIdx = 2 * k;
    int rightChildIdx = 2 * k + 1;
    if (leftChildIdx < heapSize() && mH[smallest] > mH[leftChildIdx]) {
      smallest = leftChildIdx;
    }
    if (rightChildIdx < heapSize() && mH[smallest] > mH[rightChildIdx]) {
      smallest = rightChildIdx;
    }
    if (smallest != k) {

      swap(k, smallest);
      sinkDown(smallest);
    }
  }

  /**
   * Delete Operation:
   *
   * 1) Find the index for the element to be deleted.
   * 2) Take out the last element from the last level from the heap and replace the index with this element .
   * 3) Perform Sink-Down
   */
  public void delete(int x) {
    int idx = -1;
    for (int i = 0; i < currentSize; i++) {
      if (mH[i] == x) {
        idx = i;
        break;
      }
    }

    if (idx < 0) {
      System.out.println("Cannot find the index for element: " + x);
      return;
    }

    mH[idx] = mH[currentSize];
    currentSize--;

    sinkDown(mH[idx]);
  }

  public void swap(int a, int b) {
    int temp = mH[a];
    mH[a] = mH[b];
    mH[b] = temp;
  }

  public boolean isEmpty() {
    return currentSize == 0;
  }

  public int heapSize() {
    return currentSize;
  }

  public static void main(String args[]) {
    int arrA[] = {3, 2, 1, 7, 8, 4, 10, 16, 12};
    System.out.print("Original Array : ");
    for (int i = 0; i < arrA.length; i++) {
      System.out.print("  " + arrA[i]);
    }
    MaxHeap m = new MaxHeap(arrA.length);
    System.out.print("\nMin-Heap : ");
    m.createHeap(arrA);
    m.display();
    System.out.print("Extract Min :");
    for (int i = 0; i < arrA.length; i++) {
      System.out.print("  " + m.extractMin());
    }
  }
}


