package Introduction;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
 * Queue is a FIFO (first in first out) data structure
 *
 * Type Parameter Naming Conventions for generic type in java:
 *
 * By convention, type parameter names are single, uppercase letters. This stands in sharp contrast to the variable
 * naming conventions that you already know about, and with good reason: Without this convention, it would be difficult
 * to tell the difference between a type variable and an ordinary class or interface name.
 *
 * The most commonly used type parameter names are:
 *    E - Element (used extensively by the Java Collections Framework)
 *    K - Key
 *    N - Number
 *    T - Type
 *    V - Value
 *    S,U,V etc. - 2nd, 3rd, 4th types
 */
public class ArrayQueue<E> {

  E[] arr;
  int head = -1;
  int tail = -1;
  int size;

  public ArrayQueue(Class<E> c, int size) {
    E[] newInstance = (E[]) Array.newInstance(c, size);
    this.arr = newInstance;
    this.size = 0;
  }

  //add to head, keep increase head index..
  boolean add(E e) {
    if (size == arr.length) {
      return false;
    }

    head = (head + 1) % arr.length;
    arr[head] = e;
    size++;

    if (tail == -1) {
      tail = head;
    }

    return true;
  }

  //remove from tail...
  boolean remove() {
    if (size == 0) {
      return false;
    }

    E result = arr[tail];
    arr[tail] = null; //prevent memory leaking
    size--;
    tail = (tail + 1) % arr.length;

    if (size == 0) {
      head = -1;
      tail = -1;
    }

    return true;
  }

  E peek() {
    if (size == 0) {
      return null;
    }

    return arr[tail];
  }

  public int size() {
    return this.size;
  }

  public String toString() {
    return Arrays.toString(this.arr);
  }

  public static void main(String[] args) {
    ArrayQueue<Integer> q = new ArrayQueue<Integer>(Integer.class, 6);
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    q.add(5);
    System.out.println(q + " head:" + q.head + ", tail:" + q.tail);

    System.out.println("called remove()...");
    q.remove();
    System.out.println(q + " head:" + q.head + ", tail:" + q.tail);

    System.out.println("called add()..");
    q.add(6);
    q.add(7);
    System.out.println(q + " head:" + q.head + ", tail:" + q.tail);
  }
}


