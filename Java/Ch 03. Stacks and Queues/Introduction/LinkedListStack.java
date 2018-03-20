package Introduction;

import java.util.LinkedList;

/**
 * Stack is a LIFO (last in first out) data structure.
 *
 * Implement Stack with java.util.LinkedList
 */
public class LinkedListStack<T> {

  LinkedList<T> li = new LinkedList();

  public void push(T data) {
    li.add(0, data);
  }

  public T pop() {
    return li.remove(0);
  }

  public T peek() {
    return li.get(0);
  }

  public boolean isEmpty() {
    return li.isEmpty();
  }

  public void displayStack() {
    System.out.println("  " + li.toString());
  }

  public static void main(String[] args) {
    LinkedListStack<Integer> st = new LinkedListStack();

    st.push(50);
    st.push(70);
    st.push(190);
    st.displayStack();
    st.pop();
    st.displayStack();

  }

}
