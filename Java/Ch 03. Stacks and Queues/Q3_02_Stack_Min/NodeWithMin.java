package Q3_02_Stack_Min;

class NodeWithMin {

  public int value;
  public int min;

  public NodeWithMin(int v, int min) {
    value = v;
    this.min = min;
  }

  public String toString() {
    return "NodeWithMin{ value:" + value + ", min1:" + min + " }";
  }
}
