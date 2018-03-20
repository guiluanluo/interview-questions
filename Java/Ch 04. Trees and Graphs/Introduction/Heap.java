package Introduction;


public class Heap<T extends Comparable<T>> {

  private T[] arr;
  private int size;

  public Heap(T[] baseArr) {
    this.arr = baseArr;
    size = arr.length - 1;
  }

  public void minHeapify(int i, int n) {
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    int smallest = i;
    if (l <= n && arr[l].compareTo(arr[smallest]) < 0) {
      smallest = l;
    }
    if (r <= n && arr[r].compareTo(arr[smallest]) < 0) {
      smallest = r;
    }

    if (smallest != i) {
      T temp = arr[i];
      arr[i] = arr[smallest];
      arr[smallest] = temp;
      minHeapify(smallest, n);
    }
  }

  public void buildMinHeap() {
    for (int i = size / 2; i >= 0; i--) {
      minHeapify(i, size);
    }
  }

  public void heapSortAscending() {
    buildMinHeap();
    int n = size;
    for (int i = n; i >= 1; i--) {
      T temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;
      n--;
      minHeapify(0, n);
    }
  }
}
