package Q4_01_Route_Between_Nodes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import Q4_01_Route_Between_Nodes.Question.State;

/**
 * we use Graph class here since we can not necessarily reach all the nodes from a simple node.
 *
 * Depth-First-Search(DFS): visit a node A and then iterate through each of A's neighbors. when visiting a node B that
 * is a neighbor of A, we will visit all B's neighbor before going on A's other neighbors. that is, a exhaustive
 * searches B's branch before any of its other neighbors. NOTE: pre-order and other forms of tree traversal are a form
 * of DFS.
 *
 * Breadth-First-Search(BFS): node A visits each of A's neighbors before visiting any of their neighbors. you can think
 * this is searching level by level out from A. an iterative solution involving a Queue usually work best!
 */
public class Graph {

  public static int MAX_VERTICES = 6;
  public int count;

  private Node[] vertices;

  public Graph() {
    vertices = new Node[MAX_VERTICES];
    count = 0;
  }

  public void addNode(Node node) {
    if (count < vertices.length) {
      vertices[count] = node;
      count++;
    } else {
      System.out.print("Graph full, increase size...");
      doubleScale();
      vertices[count] = node;
      count++;
    }
  }

  private void doubleScale() {
    int newSize = 2 * MAX_VERTICES;
    if (newSize > Integer.MAX_VALUE) {
      throw new OutOfMemoryError("OutOfMemory");
    }

    Node[] newVertices = Arrays.copyOf(vertices, newSize);
    vertices = newVertices;
    MAX_VERTICES = newSize;
  }

  public Node[] getNodes() {
    return vertices;
  }

  private void resetAll2State(State state) {
    for (Node node : getNodes()) {
      node.state = state;
    }
  }

  public void depthFirstSearch() {
    resetAll2State(State.Unvisited);
    for (Node node : getNodes()) {
      depthFirstSearch(node);
    }
  }

  public void depthFirstSearch(Node node) {
    if (node == null || node.state == State.Visited) {
      return;
    }

    System.out.println(node.getVertex() + " ");
    node.state = State.Visited;

    if (node.getAdjacent() != null) {
      for (Node adj : node.getAdjacent()) {
        depthFirstSearch(adj);
      }
    }
  }


  public void breadthFirstSearch() {
    resetAll2State(State.Unvisited);

    Queue<Node> queue = new LinkedList<>();
    getNodes()[0].state = State.Visiting;
    queue.add(getNodes()[0]);

     /*
     * when a node is added to queue, its status = Visiting.
     * after check whether its neighbors is the end node, set its status = VISITED
     */
    while (queue.peek() != null) {
      Node current = queue.poll();
      if (current.getAdjacent() != null) {
        for (Node node : current.getAdjacent()) {
          if (node != null && node.state == State.Unvisited) {
            node.state = State.Visiting;
            queue.add(node);
          }
        }
      }
      System.out.println(current.getVertex() + " ");
      current.state = State.Visited;
    }
  }

}

