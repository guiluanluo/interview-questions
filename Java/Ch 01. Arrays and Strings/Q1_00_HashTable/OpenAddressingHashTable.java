package Q1_00_HashTable;

/**
 * In an open addressing method all the elements occupy hash table array itself. There are no buckets – only a value or
 * null. That means that hash table can fill up and no further insertions are possible. However because it does not need
 * to keep additional pointers it’s more memory efficient so you can allocate a larger array which should result in less
 * collisions and possibly better performance. So how do we solve collision problem after all? When inserting an element
 * we probe hash table until we find a place for it. In other words we calculate hash then check if the slot is empty if
 * not calculate another hash and check that slot and continue until a place for the element is found. To do that we
 * extend hash function with additional parameter – probe index.
 */
public class OpenAddressingHashTable {

  private static int SIZE = 1000;

  private String[] hashtable = new String[SIZE];

  public void add(String value) {
    int probe = 0;
    do {
      int hash = hash(value, probe);
      if (hashtable[hash] == null) {
        hashtable[hash] = value;
        return;
      }
      probe++;
    } while (probe < SIZE);

    // hash table is full
    throw new RuntimeException("Hash table overflow");
  }

  public boolean contains(String value) {
    int probe = 0;
    do {
      int hash = hash(value, probe);
      if (hashtable[hash] == null) {
        return false;
      } else {
        if (hashtable[hash].equals(value)) {
          return true;
        }
        probe++;
      }

    } while (probe < SIZE);

    return false;
  }

  private int hash(String value, int probe) {
    int hash = value.hashCode() % SIZE;
    return (hash + probe) % SIZE;
  }
}