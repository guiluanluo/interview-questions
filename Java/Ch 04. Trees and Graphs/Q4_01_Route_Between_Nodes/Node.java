package Q4_01_Route_Between_Nodes;

import java.util.Arrays;

class Node {

  private Node adjacent[];
  public int adjacentCount;

  private String vertex;  //顶点
  public Question.State state;

  public Node(String vertex, int adjacentLength) {
    this.vertex = vertex;
    adjacentCount = 0;
    adjacent = new Node[adjacentLength];
  }

  public void addAdjacent(Node node) {
    if (adjacentCount < adjacent.length) {
      this.adjacent[adjacentCount] = node;
      adjacentCount++;
    } else {
      System.out.print("No more adjacent can be added. we double size of adjacent..");
      doubleScale();
      this.adjacent[adjacentCount] = node;
      adjacentCount++;
    }
  }

  private void doubleScale() {
    int newSize = 2 * (adjacentCount == 0 ? 1 : adjacentCount);
    if (newSize > Integer.MAX_VALUE) {
      throw new OutOfMemoryError("OutOfMemory");
    }

    Node[] newAdjacent = Arrays.copyOf(adjacent, newSize);
    this.adjacent = newAdjacent;
  }

  public Node[] getAdjacent() {
    return adjacent;
  }

  public String getVertex() {
    return vertex;
  }

  public String getAdjacentVertexes() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < adjacentCount; i++) {
      builder.append(adjacent[i].getVertex()).append(" ");
    }
    return builder.toString();
  }
}
