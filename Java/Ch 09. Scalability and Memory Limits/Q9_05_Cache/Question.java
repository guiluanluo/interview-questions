package Q9_05_Cache;

/**
 * Cache: imagine a web sever for a simplified search engine. this system has 100 machines to response to search
 * queries, which may then call out using processSearch(string query) to another cluster of machines to actually get the
 * result. the machine which responds to a given query is chosed at random, so you cannot gurantee that the same machine
 * will always response to the same request. the method processSearch() is very expensive. design a caching mechanism to
 * cache the results of the most recent queries. be sure to explain how you would update the cache when data change.
 */
public class Question {

  public static String[] generateResults(int i) {
    String[] results = {"resultA" + i, "resultB" + i, "resultC" + i};
    return results;
  }

  public static void main(String[] args) {
    Cache cache = new Cache();
    for (int i = 0; i < 20; i++) {
      String query = "query" + i;
      cache.insertResults(query, generateResults(i));
      if (i == 9 || i == 16 || i == 19) {
        cache.getResults("query" + 2);
        cache.getResults("query" + 6);
        cache.getResults("query" + 9);
      }
    }

    System.out.println("===print cache: size=" + cache.size + ", " + cache.head.printForward());

    for (int i = 0; i < 30; i++) {
      String query = "query" + i;
      String[] results = cache.getResults(query);
      System.out.print(query + ": ");
      if (results == null) {
        System.out.print("null");
      } else {
        for (String s : results) {
          System.out.print(s + ", ");
        }
      }
      System.out.println("");
    }
  }
}
