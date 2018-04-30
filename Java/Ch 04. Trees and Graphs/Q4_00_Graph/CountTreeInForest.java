package Q4_00_Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/count-number-trees-forest/
 *
 * Given n nodes of a forest (collection of trees), find the number of trees in the forest.
 *
 * Approach :
 * 1. Apply DFS on every node.
 * 2. Increment count by one if every connected node is visited from one source.
 * 3. Again perform DFS traversal if some nodes yet not visited.
 * 4. Count will give the number of trees in forest.
 */
public class CountTreeInForest {


  public int countTrees(Graph<Integer> graph) {
    Set<Long> visited = new HashSet<Long>();
    int treeCount = 0;
    for (Vertex<Integer> vertex : graph.getAllVertex()) {
      if (!visited.contains(vertex.getId())) {
        DFSUtil(vertex, visited);
        treeCount++;
      }
    }
    return treeCount;
  }

  private void DFSUtil(Vertex<Integer> v, Set<Long> visited) {
    visited.add(v.getId());
    System.out.print(v.getId() + " ");
    for (Vertex<Integer> vertex : v.getAdjacentVertexes()) {
      if (!visited.contains(vertex.getId())) {
        DFSUtil(vertex, visited);
      }
    }
  }

  public static void main(String args[]) {
    Graph<Integer> graph = new Graph<>(true);
    graph.addEdge(1, 3);
    graph.addEdge(1, 2);
    graph.addEdge(3, 4);
    graph.addEdge(5, 6);
    graph.addEdge(8, 11);

    CountTreeInForest countTreeInForest = new CountTreeInForest();
    int numberOfTree = countTreeInForest.countTrees(graph);
    System.out.println("\nnumberOfTree:" + numberOfTree);

  }
}