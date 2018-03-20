package Q16_12_XML_Encoding;

/*
 * XML encoding: since XML is very verbose, you are given a way of encoding it where each tag gets mapped to a
 * pre-defined integer value. the language/grammar is as follows:
 *
 * Element --> Tag Attributes END Children END
 * Attribute --> Tag Value
 * END --> 0
 * Tag --> some predefined mapping to int
 * Value --> string value
 *
 * for example, the following XML might be converted into the compressed string below(assuming a mapping of
 * family->1, person->2, firstName->3, lastName->4, state->5)
 * <family lastName="McDowell" state="CA">
 *   <person firstName="Gayle">Some Message</person>
 * </family>
 * becomes: 1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0
 *
 * write code to print the encoded version of an XML element (passed in Element and Attribute objects).
 *
 * Hint 465: consider a recursive or tree-like approach
 *
 * Solution: since we know the element will be passed in as an Element and Attribute, our code is reasonably simple.
 * we can implement this by applying a tree-like approach.
 *
 * we repeatedly call encode() on parts of the XML structure, handling the code in slightly different ways depending on
 * the type of the XML element.
 *
 */

public class QuestionOO {

  public static void encode(String value, StringBuilder sb) {
    value = value.replace("0", "\\0");
    sb.append(value);
    sb.append(" ");
  }

  public static void encodeEnd(StringBuilder sb) {
    sb.append("0");
    sb.append(" ");
  }

  public static void encode(Attribute attr, StringBuilder sb) {
    encode(attr.getTagCode(), sb);
    encode(attr.value, sb);
  }

  public static void encode(Element root, StringBuilder sb) {
    encode(root.getNameCode(), sb);
    for (Attribute a : root.attributes) {
      encode(a, sb);
    }
    encodeEnd(sb);

    if (root.value != null && root.value != "") {
      encode(root.value, sb);
    } else {
      for (Element e : root.children) {
        encode(e, sb);
      }
    }

    encodeEnd(sb);
  }

  public static String encodeToString(Element root) {
    StringBuilder sb = new StringBuilder();
    encode(root, sb);
    return sb.toString();
  }

  public static void main(String args[]) {
    Element root = new Element("family");
    Attribute a1 = new Attribute("lastName", "mcdowell");
    Attribute a2 = new Attribute("state", "CA");
    root.insert(a1);
    root.insert(a2);

    Element child = new Element("person", "Some Message");
    Attribute a3 = new Attribute("firstName", "Gayle");
    child.insert(a3);

    root.insert(child);

    String s = encodeToString(root);
    System.out.println(s);
  }
}
