package Introduction;

import java.util.Arrays;

/**
 * Stack is a LIFO (last in first out) data structure.
 */
public class ArrayStack<E> {

  private E[] arr = null;
  private int capacity;
  private int top = -1;
  private int size = 0;

  @SuppressWarnings("unchecked")
  public ArrayStack(int capacity) {
    this.capacity = capacity;
    this.arr = (E[]) new Object[capacity];
  }

  public E pop() {
    if (this.size == 0) {
      return null;
    }

    this.size--;
    E result = this.arr[top];
    this.arr[top] = null;//prevent memory leaking
    this.top--;

    return result;
  }

  public boolean push(E e) {
    if (!isFull()) {
//      return false;
      grow();
    }

    this.size++;
    this.arr[++top] = e;
    return true;
  }

  public boolean isFull() {
    if (this.size == this.capacity) {
      return false;
    }
    return true;
  }

  /**
   * The maximum size of array to allocate. Some VMs reserve some header words in an array. Attempts to allocate larger
   * arrays may result in OutOfMemoryError: Requested array size exceeds VM limit
   */
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  private void grow() {
    // overflow-conscious code
    int newCapacity = capacity * 2;
    if (newCapacity - MAX_ARRAY_SIZE > 0) {
      throw new OutOfMemoryError();
    }

    arr = Arrays.copyOf(arr, newCapacity);
    capacity = newCapacity;
  }

  public String toString() {
    if (this.size == 0) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.size; i++) {
      sb.append(this.arr[i] + ", ");
    }

    sb.setLength(sb.length() - 2);

    sb.append("    size:" + size + ", top:" + top + ", capacity:" + capacity);

    return sb.toString();
  }

  public static void main(String[] args) {

    ArrayStack<Integer> stack = new ArrayStack<Integer>(4);
    stack.push(1);
    stack.push(2);
    stack.push(3);
    System.out.println(stack);

    stack.push(4);
    stack.push(5);
    stack.push(6);
    System.out.println("called pushed 4,5,6: " + stack);

    stack.pop();
    System.out.println("called pop: " + stack);

    stack.push(11);
    stack.push(12);
    System.out.println("pushed 11, 12: " + stack);

    stack.pop();
    System.out.println("called pop: " + stack);
  }
}
