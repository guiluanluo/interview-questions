package Q7_09_Circular_Array;

import java.util.Iterator;

/**
 * one way to implement the CircularArray class is to actually is to actually shift the elements each time we call
 * rotate(int shiftRight). doing this is, of course, not very efficient. instead, we ca just create a member variable
 * head which points to what should be conceptually viewed as the start of circular array. rather than shift around the
 * elements in the array, we just increment head by shiftRight.
 *
 * There are a number of things here which are easy to make mistakes on, such as:
 *
 * in java, we can not create an array of the generic type. instead, we must either case the array or define items to be
 * type List<T>. for simplicity, we have done the former.
 *
 * the % operator will return a nagive value when we do nagValue%posVal. for example -8%3 is -2. this is different from
 * how mathematicians would define the modulus function. we must add items.length to a negative index to get the correct
 * positive result.
 *
 * we need to be sure to consistently convert the raw index to the rotated index. for this reason, we have implemented a
 * covert() that is used by other methods. even the rotate() uses convert(), this is a good example of code reuse.
 */
public class CircularArray<T> implements Iterable<T> {

  private T[] items;
  private int head = 0;

  public CircularArray(int size) {
    items = (T[]) new Object[size];
  }

  private int convert(int index) {
    if (index < 0) {
      index += items.length;
    }
    return (head + index) % items.length;
  }

  public void rotate(int shiftRight) {
    head = convert(shiftRight);
  }

  public T get(int i) {
    if (i < 0 || i >= items.length) {
      throw new java.lang.IndexOutOfBoundsException("Index " + i + " is out of bounds");
    }
    return items[convert(i)];
  }

  public void set(int i, T item) {
    items[convert(i)] = item; //TODO: this is overwrite the value?? -LL
  }

  public Iterator<T> iterator() {
    return new CircularArrayIterator();
  }

  private class CircularArrayIterator implements Iterator<T> {

    private int _current = -1;

    public CircularArrayIterator() {
    }

    @Override
    public boolean hasNext() {
      return _current < items.length - 1;
    }

    @Override
    public T next() {
      _current++;
      return (T) items[convert(_current)];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove is not supported by CircularArray");
    }
  }

  public void print() {
    StringBuilder builder = new StringBuilder();
    builder.append("CircularArray: { head:" + head).append(", items[");
    Iterator<T> iterator = iterator();
    while (iterator.hasNext()) {
      builder.append(iterator.next() + ",");
    }
    builder.append("] ");

    builder.append("  storage item:[");
    for (int i = 0; i < items.length; i++) {
      builder.append((i == 0 ? "" : ",") + items[i]);
    }
    builder.append("] }");

    System.out.println(builder.toString());

  }
}
