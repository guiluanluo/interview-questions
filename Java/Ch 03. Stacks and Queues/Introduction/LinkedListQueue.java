package Introduction;

import java.util.LinkedList;

/**
 * Queue is a FIFO (first in first out) data structure
 *
 * Implement Queue with java.util.LinkedList; FIFO
 */
public class LinkedListQueue<T> {

  LinkedList<T> li = new LinkedList();

  public void add(T data) {
    li.add(data);
  }

  public T remove() {
    return li.remove(0);
  }

  public T peek() {
    return li.get(0);
  }

  public void displayStack() {
    System.out.println("  " + li.toString());
  }

  public static void main(String[] args) {
    LinkedListQueue<Integer> st = new LinkedListQueue();

    st.add(50);
    st.add(70);
    st.add(190);
    st.displayStack();

    System.out.println("call remove():" + st.remove());
    st.displayStack();

    System.out.println("call peek():" + st.peek());
    st.displayStack();
  }
}
