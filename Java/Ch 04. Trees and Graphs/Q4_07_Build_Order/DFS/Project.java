package Q4_07_Build_Order.DFS;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {

  public enum State {COMPLETE, PARTIAL, BLANK}

  private String name;
  private State state = State.BLANK;

  private ArrayList<Project> children = new ArrayList<Project>();
  
  //key is name of project, and value is the project
  private HashMap<String, Project> map = new HashMap<String, Project>();


  public Project(String n) {
    name = n;
  }

  public String getName() {
    return name;
  }

  public void addNeighbor(Project node) {
    if (!map.containsKey(node.getName())) {
      children.add(node);
      map.put(node.getName(), node);
    }
  }

  public State getState() {
    return state;
  }

  public void setState(State st) {
    state = st;
  }

  public ArrayList<Project> getChildren() {
    return children;
  }
}
