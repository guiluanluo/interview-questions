package Q4_00_Graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://www.geeksforgeeks.org/graph-coloring-applications/ http://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
 *
 * Basic Greedy Coloring Algorithm: 1. Color first vertex with first color.
 *
 * 2. Do following for remaining V-1 vertices: Consider the currently picked vertex and color it with the lowest
 * numbered color that has not been used on any previously colored vertices adjacent to it. If all previously used
 * colors appear on vertices adjacent to v, assign a new color to it.
 *
 * The basic algorithm never uses more than d+1 colors where d is the maximum degree of a vertex in the given graph.
 *
 * Time Complexity: O(V^2 + E) in worst case.
 */
public class GraphColoring {

  public void greedyColoring() {

    Graph<Integer> graph = buildGraph(false);

    //sort all vertex by degree
    Set<Vertex<Integer>> sortedSet = new TreeSet<Vertex<Integer>>(new Comparator<Vertex<Integer>>() {
      @Override
      public int compare(Vertex<Integer> o1, Vertex<Integer> o2) {
        return (o1.getDegree() <= o2.getDegree()) ? 1 : -1; //in descending order
      }
    });
    for (Vertex<Integer> v : graph.getAllVertex()) {
      sortedSet.add(v);
    }

    Map<Long, String> assignedColor = new HashMap<Long, String>();
    Map<Long, String> finalAssignedColor = new HashMap<Long, String>();
    Map<String, Boolean> colorsUsed = buildColorUsedMap();

    Set<Vertex<Integer>> visitedSet = new HashSet<Vertex<Integer>>();
    while (sortedSet.size() != visitedSet.size()) {
      String color = null;
      for (Vertex<Integer> v : sortedSet) {
        if (visitedSet.contains(v)) {
          continue;
        }
        boolean allUncolored = allAdjacentUnColored(v.getAdjacentVertexes(), assignedColor);
        if (allUncolored) {
          color = getUnusedColor(colorsUsed);
          assignedColor.put(v.getId(), color);
          visitedSet.add(v);
          finalAssignedColor.put(v.getId(), color);
        }
      }

      colorsUsed.remove(color);
      assignedColor.clear();
    }

    System.out.println(finalAssignedColor);
  }


  private String getUnusedColor(Map<String, Boolean> colorsUsed) {
    for (String color : colorsUsed.keySet()) {
      if (colorsUsed.get(color).equals(false)) {
        return color;
      }
    }
    throw new RuntimeException();
  }

  private boolean allAdjacentUnColored(Collection<Vertex<Integer>> vertexes, Map<Long, String> colorsAssigned) {
    for (Vertex<Integer> vertex : vertexes) {
      if (colorsAssigned.containsKey(vertex.getId())) {
        return false;
      }
    }
    return true;
  }

  private Map<String, Boolean> buildColorUsedMap() {
    Map<String, Boolean> colorsUsed = new TreeMap<String, Boolean>();
    colorsUsed.put("Green", false);
    colorsUsed.put("Blue", false);
    colorsUsed.put("Red", false);
    colorsUsed.put("Yellow", false);
    colorsUsed.put("Orange", false);
    colorsUsed.put("BLACK", false);
    colorsUsed.put("WHITE", false);
    return colorsUsed;
  }

  private Graph<Integer> buildGraph(boolean isDirected) {
    Graph<Integer> graph = new Graph<Integer>(isDirected);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(1, 4);
    graph.addEdge(4, 6);
    graph.addEdge(1, 7);
    graph.addEdge(1, 8);
    graph.addEdge(2, 9);
    graph.addEdge(1, 3);
    graph.addEdge(3, 4);
    graph.addEdge(2, 4);
    graph.addEdge(3, 7);
    graph.addEdge(2, 7);
    return graph;
  }

  public static void main(String args[]) {
    GraphColoring graphColoring = new GraphColoring();
    graphColoring.greedyColoring();
  }
}

