package Q17_12_BiNode;

public class BiNode {

  public BiNode node1;
  public BiNode node2;
  public int data;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BiNode biNode = (BiNode) o;
    if (data == biNode.data) {
      return false;
    }

    if (node1 != null ? !node1.equals(biNode.node1) : biNode.node1 != null) {
      return false;
    }
    return node2 != null ? node2.equals(biNode.node2) : biNode.node2 == null;
  }

  @Override
  public int hashCode() {
    int result = node1 != null ? node1.data : 0;
    result = 31 * result + (node2 != null ? node2.data : 0);
    result = 31 * result + data;
    return result;
  }

  public BiNode(int d) {
    data = d;
  }
}
