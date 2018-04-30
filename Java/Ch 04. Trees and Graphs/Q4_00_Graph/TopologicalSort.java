package Q4_00_Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Topological Sorting is mainly used for scheduling jobs from the given dependencies among jobs.
 *
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed
 * edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not
 * a DAG.
 *
 * Given a directed acyclic graph, do a topological sort on this graph.
 *
 * Do DFS by keeping visited. Put the vertex which are completely explored into a stack. Pop from stack to get sorted
 * order. Space and time complexity is O(n).
 */
public class TopologicalSort<T> {

  /**
   * Main method to be invoked to do topological sorting.
   */
  public Deque<Vertex<T>> topSort(Graph<T> graph) {

//    Stack<Vertex<T>>  stack = new LinkedList<>();
    Deque<Vertex<T>> stack = new ArrayDeque<>();
    Set<Vertex<T>> visited = new HashSet<>();

    for (Vertex<T> vertex : graph.getAllVertex()) {
      if (visited.contains(vertex)) {
        continue;
      }
      topSortUtil(vertex, stack, visited);
    }
    return stack;
  }

  private void topSortUtil(Vertex<T> vertex, Deque<Vertex<T>> stack, Set<Vertex<T>> visited) {

    visited.add(vertex);

    for (Vertex<T> childVertex : vertex.getAdjacentVertexes()) {
      if (visited.contains(childVertex)) {
        continue;
      }
      topSortUtil(childVertex, stack, visited);
    }
    stack.offerFirst(vertex); //add vertex to stack
  }

  /**
   * Find the longest path in a directed acyclic graph
   */
  public void findLongestPathInDirectedAcyclicGraph(Graph<Integer> graph) {

    //Topological Sort the graph
    TopologicalSort<Integer> sort = new TopologicalSort<Integer>();
    Deque<Vertex<Integer>> result = sort.topSort(graph);
    Vertex<Integer>[] resultArr = result.toArray(new Vertex[result.size()]);

    //find the longest path of this graph
    Map<Vertex<Integer>, List> vertexPathMap = new HashMap();

    for (int i = 0; i < resultArr.length; i++) {
      Vertex<Integer> v = resultArr[i];
      List<Vertex<Integer>> values = vertexPathMap.get(v);
      if (values == null) {
        values = new ArrayList<>();
      }
      values.add(v);
      vertexPathMap.put(v, values);

      int idx = findNextVertexIdx(resultArr, 1, v.getAdjacentVertexes());
      while (idx > 0 && idx < resultArr.length) {
        values.add(resultArr[idx]);
        idx = findNextVertexIdx(resultArr, 1, resultArr[idx].getAdjacentVertexes());
      }
    }

    List<Vertex<Integer>> foundLongest = vertexPathMap.get(resultArr[0]);
    for (int j = 1; j < resultArr.length; j++) {
      if (vertexPathMap.get(resultArr[j]).size() > foundLongest.size()) {
        foundLongest = vertexPathMap.get(resultArr[j]);
      }
    }

    StringBuilder build = new StringBuilder();
    for (Vertex<Integer> v : foundLongest) {
      build.append(v.getId()).append(" --> ");
    }
    System.out.println("Found longest path:" + build.toString());
  }

  private int findNextVertexIdx(Vertex<Integer>[] result, int startIdx, List<Vertex<Integer>> adjacentVertex) {
    for (int i = startIdx; i < result.length; i++) {
      if (adjacentVertex.contains(result[i])) {
        return i;
      }
    }
    return -1;
  }


  public static void main(String args[]) {
    Graph<Integer> graph = new Graph<>(true);
    graph.addEdge(1, 3);
    graph.addEdge(1, 2);
    graph.addEdge(3, 4);
    graph.addEdge(5, 6);
    graph.addEdge(6, 3);
    graph.addEdge(3, 8);
    graph.addEdge(8, 11);

    TopologicalSort<Integer> sort = new TopologicalSort<Integer>();
    Deque<Vertex<Integer>> result = sort.topSort(graph);

    StringBuilder builder = new StringBuilder();
    while (!result.isEmpty()) {
      builder.append(result.poll()).append(" ");
    }
    System.out.println(builder.toString());

    sort.findLongestPathInDirectedAcyclicGraph(graph);
  }
}
