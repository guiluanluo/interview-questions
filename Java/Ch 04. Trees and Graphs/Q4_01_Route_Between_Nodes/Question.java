package Q4_01_Route_Between_Nodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Route between nodes: given a directed graph, design an algorithm to find out whether there is a route between two
 * nodes.
 *
 * hint 127: two well-know algorithms can do this. what are the trade-off between them?
 *
 * Graph can be either directed or undirected. while directed edges are like a one-way street, undirected edges are like
 * two ways street.
 *
 * Solution: we could solve this by simple graph traversal, such BFS or DFS. we start from one of the two nodes. during
 * traversal, check is the other node is found. we should mark any node found in the course of the algorithm as "already
 * visited" to avoid cycles and repetition of the nodes.
 *
 * DFS is a bit simpler to implement it can be done with simple recursive, BFS can also to fin the shortest path.
 */
public class Question {

  public static void main(String a[]) {
    Graph g = createNewGraph();
    System.out.println("=== call breadthFirstSearch() ===");
    g.breadthFirstSearch();

    System.out.println("=== call depthFirstSearch() ===");
    g.depthFirstSearch();

    Node[] n = g.getNodes();
    Node start = n[3];
    Node end = n[5];
    System.out.println("search start=" + start.getVertex() + ", end=" + end.getVertex() + ": " + search(g, start, end));
    System.out.println("breadthFirstSearch start=" + start.getVertex() + ", end=" + end.getVertex()
        + ": " + breadthFirstSearch(g, start, end));

    System.out.println("bidirectionalSearch start=" + start.getVertex() + ", end=" + end.getVertex()
        + ": " + bidirectionalSearch(g, start, end));

  }

  public static Graph createNewGraph() {
    Graph g = new Graph();
    Node[] temp = new Node[6];

    temp[0] = new Node("a", 3);
    temp[1] = new Node("b", 0);
    temp[2] = new Node("c", 0);
    temp[3] = new Node("d", 1);
    temp[4] = new Node("e", 1);
    temp[5] = new Node("f", 0);

    temp[0].addAdjacent(temp[1]);
    temp[0].addAdjacent(temp[2]);
    temp[0].addAdjacent(temp[3]);
    temp[2].addAdjacent(temp[5]);
    temp[3].addAdjacent(temp[4]);
    temp[4].addAdjacent(temp[5]);
    for (int i = 0; i < 6; i++) {
      g.addNode(temp[i]);
    }
    return g;
  }

  public static boolean bidirectionalSearch(Graph g, Node start, Node end) {
    Queue<Node> queueStart = new LinkedList<>();
    Queue<Node> queueEnd = new LinkedList<>();
    List<String> startVisited = new ArrayList<>();
    List<String> endVisited = new ArrayList<>();

    //set the state of all nodes as unvisited
    for (Node u : g.getNodes()) {
      u.state = State.Unvisited;
    }

    start.state = State.Visiting;
    end.state = State.Visiting;
    queueStart.add(start);
    queueEnd.add(end);

    while (queueStart.peek() != null || queueEnd.peek() != null) {
      if (bidirectionalSearch(end, queueStart, startVisited, endVisited, true)) {
        return true;
      }
      if (bidirectionalSearch(start, queueEnd, startVisited, endVisited, false)) {
        return true;
      }
    }
    return false;

  }

  private static boolean bidirectionalSearch(Node checkNode, Queue<Node> queue, List<String> startVisited,
      List<String> endVisited, boolean isFromStart) {
    if (queue.peek() != null) {
      Node current = queue.poll();
      if (isFromStart) {
        startVisited.add(current.getVertex());
      } else {
        endVisited.add(current.getVertex());
      }

      List<String> checkVisited = isFromStart ? endVisited : startVisited;
      if (current.getAdjacent() != null) {
        for (Node node : current.getAdjacent()) {
          if (node.state == State.Unvisited) {
            if (node.getVertex() == checkNode.getVertex() || checkVisited.contains(node.getVertex())) {
              return true;
            }
            node.state = State.Visiting;
            queue.add(node);
          } else {
            if (checkVisited.contains(node.getVertex())) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  public static boolean breadthFirstSearch(Graph g, Node start, Node end) {
    Queue<Node> queue = new LinkedList<>();

    //set the state of all nodes as unvisited
    for (Node u : g.getNodes()) {
      u.state = State.Unvisited;
    }

    /*
     * when a node is added to queue, its status = Visiting.
     * after check whether its neighbors is the end node, set its status = VISITED
     */
    start.state = State.Visiting;
    queue.add(start);

    while (queue.peek() != null) {
      Node current = queue.poll();
      if (current.getAdjacent() != null) {
        for (Node node : current.getAdjacent()) {
          if (node.state == State.Unvisited) {
            if (node == end) {
              return true;
            } else {
              node.state = State.Visiting;
              queue.add(node);
            }
          }
        }
      }
      current.state = State.Visited;
    }

    return false;
  }


  public static boolean search(Graph g, Node start, Node end) {
    LinkedList<Node> q = new LinkedList<Node>();
    for (Node u : g.getNodes()) {
      u.state = State.Unvisited;
    }

    start.state = State.Visiting;
    q.add(start);

    Node u;
    while (!q.isEmpty()) {
      u = q.removeFirst();
      if (u != null) {
        for (Node v : u.getAdjacent()) {
          if (v.state == State.Unvisited) {
            if (v == end) {
              return true;
            } else {
              v.state = State.Visiting;
              q.add(v);
            }
          }
        }
        u.state = State.Visited;
      }
    }
    return false;
  }

  public enum State {
    Unvisited, Visited, Visiting;
  }
}
