package Q16_12_XML_Encoding;

import java.util.ArrayList;

public class Element {

  public ArrayList<Attribute> attributes;
  public ArrayList<Element> children;
  public String name;
  public String value;

  public Element(String name) {
    this.name = name;
    this.attributes = new ArrayList<Attribute>();
    this.children = new ArrayList<Element>();
  }

  public Element(String name, String value) {
    this.name = name;
    this.value = value;
    this.attributes = new ArrayList<Attribute>();
    this.children = new ArrayList<Element>();
  }

  public String getNameCode() {
    if (name == "family") {
      return "1";
    } else if (name == "person") {
      return "2";
    } else if (name == "firstName") {
      return "3";
    } else if (name == "lastName") {
      return "4";
    } else if (name == "state") {
      return "5";
    }
    return "--";
  }

  public void insert(Attribute attribute) {
    attributes.add(attribute);
  }

  public void insert(Element child) {
    children.add(child);
  }
}
