package Q4_00_Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A graph is simply a collection of nodes with edges between (some of) them.
 *
 * Graphs can be either directed or undirected. While directed edges are like a one-way street, undirected edges are
 * like a two-way street.
 *
 * The graph might consist of multiple isolated sub- graphs. if there is a path between every pair of vertices, it is
 * calls a "connected graph"
 *
 * The graph can also have cycles(or not). and "acyclic graph" is one without cycles
 */
public class Graph<T> {

  boolean isDirected = false;
  private List<Edge<T>> allEdges;
  private Map<Long, Vertex<T>> allVertex; //keyed by the Id of Vertex, value is Vertex object

  public Graph(boolean isDirected) {
    allEdges = new ArrayList<Edge<T>>();
    allVertex = new HashMap<Long, Vertex<T>>();
    this.isDirected = isDirected;
  }

  public void addEdge(long id1, long id2) {
    addEdge(id1, id2, 0);
  }

  //This works only for directed graph because for undirected graph we can end up
  //adding edges two times to allEdges
  public void addVertex(Vertex<T> vertex) {
    if (allVertex.containsKey(vertex.getId())) {
      return;
    }

    allVertex.put(vertex.getId(), vertex);
    for (Edge<T> edge : vertex.getEdges()) {
      allEdges.add(edge);
    }
  }

  public Vertex<T> addSingleVertex(long id) {
    if (allVertex.containsKey(id)) {
      return allVertex.get(id);
    }

    Vertex<T> v = new Vertex<T>(id);
    allVertex.put(id, v);
    return v;
  }

  public Vertex<T> getVertex(long id) {
    return allVertex.get(id);
  }

  public void addEdge(long id1, long id2, int weight) {
    Vertex<T> vertex1;
    if (allVertex.containsKey(id1)) {
      vertex1 = allVertex.get(id1);
    } else {
      vertex1 = new Vertex<T>(id1);
      allVertex.put(id1, vertex1);
    }

    Vertex<T> vertex2;
    if (allVertex.containsKey(id2)) {
      vertex2 = allVertex.get(id2);
    } else {
      vertex2 = new Vertex<T>(id2);
      allVertex.put(id2, vertex2);
    }

    Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
    allEdges.add(edge);

    vertex1.addAdjacentVertex(edge, vertex2);
    if (!isDirected) {
      vertex2.addAdjacentVertex(edge, vertex1);
    }
  }

  public Edge<T> getEdge(long id1, long id2) {
    for (Edge<T> e : allEdges) {
      if (e.getVertex1().getId() == id1 && e.getVertex2().getId() == id2) {
        return e;
      }
    }
    return null;
  }

  public List<Edge<T>> getAllEdges() {
    return allEdges;
  }

  public Collection<Vertex<T>> getAllVertex() {
    return allVertex.values();
  }

  public void setDataForVertex(long id, T data) {
    if (allVertex.containsKey(id)) {
      Vertex<T> vertex = allVertex.get(id);
      vertex.setData(data);
    }
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    for (Edge<T> edge : getAllEdges()) {
      buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
      buffer.append("\n");
    }
    return buffer.toString();
  }
}



