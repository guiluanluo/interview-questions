package Q7_12_Hash_Table;

/*
 * Hash table: design and implement a hash table which uses chaining(linked list) to handle collisions.
 *
 * hint 287: in order to handle collisions, the hash table should be an array of linked lists.
 * hint 307: think carefully about what information the linked list node need to contain.
 *
 * Lucy Notes:
 *
 * Collision: When a hash function maps two different keys to the same table address, a collision is said to occur.
 *
 * Chaining: In chaining we use array indexes to store the values. If hash code of second value also points to the same
 * index then we replace that index value with an linked list and all values pointing to that index are stored in the
 * linked list and actual array index points to the head of the the linked list. But if there is only one hash code
 * pointing to an index of array then the value is directly stored in that index. Same logic is applied while retrieving
 * the values. This is used in Java HashMap/Hashtable to avoid collisions.
 *
 * How do HashTables deal with collisions? Java uses both option 1 and 2 in its hash table implementations:
 *
 * Option 1: By having each bucket contain a linked list of elements that are hashed to that bucket. This is why a bad
 * hash function can make lookups in hash tables very slow.
 *
 * Option 2: If the hash table entries are all full then the hash table can increase the number of buckets that it has
 * and then redistribute all the elements in the table. The hash function returns an integer and the hash table has to
 * take the result of the hash function and mod it against the size of the table that way it can be sure it will get to
 * bucket. So by increasing the size, it will rehash and run the modulo calculations which if you are lucky might send
 * the objects to different buckets.
 */
public class Question {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Dummy bob = new Dummy("Bob", 20);
    Dummy jim = new Dummy("Jim", 25);
    Dummy alex = new Dummy("Alex", 30);
    Dummy tim = new Dummy("Tim", 35);
    Dummy maxwell = new Dummy("Maxwell", 40);
    Dummy john = new Dummy("John", 45);
    Dummy julie = new Dummy("Julie", 50);
    Dummy christy = new Dummy("Christy", 55);
    Dummy tim2 = new Dummy("Tim", 100); // This should replace the first "tim"

    Dummy[] dummies = {bob, jim, alex, tim, maxwell, john, julie, christy, tim2};

		/* Test: Insert Elements. */
    Hasher<String, Dummy> hash = new Hasher<String, Dummy>(3);
    for (Dummy d : dummies) {
      System.out.println(hash.put(d.getName(), d));
    }

    hash.printTable();

		/* Test: Recall */
    for (Dummy d : dummies) {
      String name = d.getName();
      Dummy dummy = hash.get(name);
      if (dummy == null) {
        System.out.println("Dummy named " + name + ": null");
      } else {
        System.out.println("Dummy named " + name + ": " + dummy.toString());
      }
      Dummy d2 = hash.remove(name);
      if (d2 == null) {
        System.out.println("Dummy removed named " + name + ": " + "null");
      } else {
        System.out.println("Dummy removed named " + name + ": " + d2.toString());
      }
    }
  }

}
