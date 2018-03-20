package Q17_17_Multi_Search;

import java.util.ArrayList;

import CtCILibrary.HashMapList;

/**
 * Multi search: given a string b and an array of smaller strings T, design a method to search b for each small string
 * in T.
 *
 * Solution B using Trie:  called prefix tree (as they can be searched by prefixes), is a kind of search tree—an ordered
 * tree data structure that is used to store a dynamic set or associative array where the keys are usually strings.
 * Unlike a binary search tree, no node in the tree stores the key associated with that node; instead, its position in
 * the tree defines the key with which it is associated. All the descendants of a node have a common prefix of the
 * string associated with that node, and the root is associated with the empty string. Values are not necessarily
 * associated with every node. Rather, values tend only to be associated with leaves, and with some inner nodes that
 * correspond to keys of interest. For the space-optimized presentation of prefix tree, see compact prefix tree.
 */
public class QuestionA {

  public static HashMapList<String, Integer> searchAll(String big, String[] smalls) {
    HashMapList<String, Integer> lookup = new HashMapList<String, Integer>();
    for (String small : smalls) {
      ArrayList<Integer> locations = search(big, small);
      lookup.put(small, locations);
    }
    return lookup;
  }

  public static ArrayList<Integer> search(String big, String small) {
    ArrayList<Integer> locations = new ArrayList<Integer>();
    for (int i = 0; i < big.length() - small.length() + 1; i++) {
      if (isSubstringAtLocation(big, small, i)) {
        locations.add(i);
      }
    }
    return locations;
  }


  public static boolean isSubstringAtLocation(String big, String small, int offset) {
    for (int i = 0; i < small.length(); i++) {
      if (big.charAt(offset + i) != small.charAt(i)) {
        return false;
      }
    }
    return true;
  }


  public static void main(String[] args) {
    String big = "mississippi";
    String[] smalls = {"is", "ppi", "hi", "sis", "i", "mississippi"};
    HashMapList<String, Integer> locations = searchAll(big, smalls);
    System.out.println(locations.toString());
  }
}