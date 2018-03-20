package Q16_25_LRU_Cache;

/**
 * LRU cache: design and build a "least recently used" cache, which evicts the least recently used item. the cache
 * should map from keys to values (allowing you to insert and retrieve a value associated with a particular key) and be
 * initialized with a max size. when it is full, it should evict the least recently used item.
 *
 * hint 523: first scope the problem. what are the feature you would want/
 *
 * hint 629: what data structure would be most useful for the lookup? what data structure would be most useful to know
 * and maintain the order of items?
 *
 * hint 693: both a hash table and a doubly linked list would be useful. can you combine the two?
 */
public class Question {

  /**
   * @param args
   */
  public static void main(String[] args) {
    int cache_size = 5;
    Cache cache = new Cache(cache_size);

    cache.setKeyValue(1, "1");
    System.out.println(cache.getCacheAsString());
    cache.setKeyValue(2, "2");
    System.out.println(cache.getCacheAsString());
    cache.setKeyValue(3, "3");
    System.out.println(cache.getCacheAsString());
    cache.getValue(1);
    System.out.println(cache.getCacheAsString());
    cache.setKeyValue(4, "4");
    System.out.println(cache.getCacheAsString());
    cache.getValue(2);
    System.out.println(cache.getCacheAsString());
    cache.setKeyValue(5, "5");
    System.out.println(cache.getCacheAsString());
    cache.getValue(5);
    System.out.println(cache.getCacheAsString());
    cache.setKeyValue(6, "6");
    System.out.println(cache.getCacheAsString());
    cache.getValue(1);
    System.out.println(cache.getCacheAsString());
    cache.setKeyValue(5, "5a");
    System.out.println(cache.getCacheAsString());
    cache.getValue(3);
    System.out.println(cache.getCacheAsString());
    // 6->5->2->4->1
  }

}
