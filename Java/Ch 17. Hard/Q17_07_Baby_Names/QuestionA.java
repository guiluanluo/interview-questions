package Q17_07_Baby_Names;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Baby names: each year, the government releases a list of the 10,000 most common baby names and their frequences (the
 * number of babies with that name). the only problem with this is that some names have multiple spellings. for example,
 * "john" and "jon" are essentially the same name but would be listed separately in the list. given two lists, one of
 * names/frequencies and the other of pairs of equivalent names, write an algorithm to print a new list of the true
 * frequency of each name. note that if John and Jon are synonyms, and Jon and Johnny are synonyms, then John and Johnny
 * are synonyms. (it is both transitive and symmetric). in the final list, any name can be used as the "real" name.
 *
 * Solution 1: let's assume our baby names list is given to us as a hash table(if not, it is easy to build one). we can
 * start reading pairs in from the synonyms list. as we read the pair(Janathan, John), we can merge the count for
 * Jonathan & John together. we'll nee to remember, though, that we saw this pair, because, int the future, we could
 * discover that Jonathan is equivalent to something else)
 *
 * we can think these names as "equivalance classes". when we find a pair(Jonathan, John), we put these in the same
 * set(or equivalence classes). each name maps to its equivalence class. all items in the set map to the same instance
 * of the set.
 */
public class QuestionA {

  public static HashMap<String, Integer> trulyMostPopular(HashMap<String, Integer> names, String[][] synonyms) {

    HashMap<String, NameSet> groups = constructGroups(names);

    mergeClasses(groups, synonyms);

    return convertToMap(groups);
  }

  /**
   * Read through (name, frequency) pairs and initialize a mapping
   * of names to NameSets (equivalence classes).
   */
  public static HashMap<String, NameSet> constructGroups(HashMap<String, Integer> names) {
    HashMap<String, NameSet> groups = new HashMap<String, NameSet>();
    for (Entry<String, Integer> entry : names.entrySet()) {
      String name = entry.getKey();
      int frequency = entry.getValue();
      NameSet group = new NameSet(name, frequency);
      groups.put(name, group);
    }
    return groups;
  }

  public static void mergeClasses(HashMap<String, NameSet> groups, String[][] synonyms) {

    for (String[] entry : synonyms) {
      String name1 = entry[0];
      String name2 = entry[1];

      NameSet set1 = groups.get(name1);
      NameSet set2 = groups.get(name2);

      if (set1 != set2) {
        /* Always merge the smaller set into the bigger one. */
        NameSet smaller = set2.size() < set1.size() ? set2 : set1;
        NameSet bigger = set2.size() < set1.size() ? set1 : set2;

        /* Merge lists */
        Set<String> otherNames = smaller.getNames();
        int frequency = smaller.getFrequency();
        bigger.copyNamesWithFrequency(otherNames, frequency);

			    /* Update mapping : similar name should have the same frequency*/
        for (String name : otherNames) {
          groups.put(name, bigger);
        }
      }
    }
  }

  public static HashMap<String, Integer> convertToMap(HashMap<String, NameSet> groups) {
    HashMap<String, Integer> list = new HashMap<String, Integer>();
    for (NameSet group : groups.values()) {
      list.put(group.getRootName(), group.getFrequency());
    }
    return list;
  }


  public static void main(String[] args) {
    HashMap<String, Integer> names = new HashMap<String, Integer>();

    names.put("John", 3);
    names.put("Jonathan", 4);
    names.put("Johnny", 5);
    names.put("Chris", 1);
    names.put("Kris", 3);
    names.put("Brian", 2);
    names.put("Bryan", 4);
    names.put("Carleton", 4);

    String[][] synonyms =
        {
            {"John", "Jonathan"},
            {"Jonathan", "Johnny"},
            {"Chris", "Kris"},
            {"Brian", "Bryan"}};

    HashMap<String, Integer> finalList = trulyMostPopular(names, synonyms);
    for (Entry<String, Integer> entry : finalList.entrySet()) {
      String name = entry.getKey();
      int frequency = entry.getValue();
      System.out.println(name + ": " + frequency);
    }
  }

}
