package Q1_00_HashTable;


import java.util.Iterator;
import java.util.LinkedList;

public class ArrayOfLinkedListHashTable {

  private static int SIZE = 1000;

  private LinkedList<String>[] hashtable = (LinkedList<String>[]) new LinkedList[SIZE];

  public void add(String value) {
    int hash = hash(value);
    if (hashtable[hash] == null) {
      hashtable[hash] = new LinkedList<>();
    }
    LinkedList<String> bucket = hashtable[hash];
    bucket.add(value);
  }

  public boolean contains(String value) {
    int hash = hash(value);
    LinkedList<String> bucket = hashtable[hash];
    if (bucket != null) {
      Iterator<String> it = bucket.iterator();
      while (it.hasNext()) {
        if (it.next().equals(value)) {
          return true;
        }
      }
    }
    // value not found
    return false;
  }

  // exactly the same as contains() just additionally remove value
  public boolean remove(String value) {
    int hash = hash(value);
    LinkedList<String> bucket = hashtable[hash];
    if (bucket != null) {
      Iterator<String> it = bucket.iterator();
      while (it.hasNext()) {
        if (it.next().equals(value)) {
          it.remove();
          return true;
        }
      }
    }
    // value not found
    return false;
  }

  private int hash(String value) {
    return value.hashCode() % hashtable.length;
  }
}
