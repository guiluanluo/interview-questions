package Q9_05_Cache;

import java.util.Arrays;

public class Node {

  public Node prev;
  public Node next;

  public String[] results;
  public String query;

  public Node(String q, String[] res) {
    results = res;
    query = q;
  }

  public String printForward() {
    return (query + "/" + Arrays.asList(results))
        + (next == null ? "" : (" --> " + next.printForward()));
  }

}
