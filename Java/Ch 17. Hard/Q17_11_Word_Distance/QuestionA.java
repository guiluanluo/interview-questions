package Q17_11_Word_Distance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CtCILibrary.AssortedMethods;

/**
 * Word distance: you have a large text file containing words. given any two words, find the shortest distance(in terms
 * of number of words) between them in the file. if the operation will be repeated many times for the same file(but
 * different pairs of words), can you optimize your solution?
 *
 * Solution: we will assume for this question that it doesn't matter whether word1 or word2 appears first. this is a
 * question you should ask your interviewer
 *
 * to solve this problem, we can traverse the file just one. we remember through out traversal where we've last seen
 * word1 and word2, storing the locations in location1 and location2. if the current locations are better than our best
 * known location, we update the vest location.
 */
public class QuestionA {

  public static int findClosest_lucy(String[] words, String word1, String word2) {
    Map<String, List<Integer>> wordIndexMap = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      List<Integer> indexList = wordIndexMap.get(word) != null ? wordIndexMap.get(word) : new ArrayList<>();
      indexList.add(i);
      wordIndexMap.put(word, indexList);
    }

    List<Integer> indexList1 = wordIndexMap.get(word1);
    List<Integer> indexList2 = wordIndexMap.get(word2);

    int closestDistance = Integer.MAX_VALUE;
    for (Integer index1 : indexList1) {
      for (Integer index2 : indexList2) {
        if (index2 < index1) {
          break;
        }

        int distance = index2 - index1;
        if (distance < closestDistance) {
          closestDistance = distance;
        }
      }
    }

    return closestDistance;
  }


  public static LocationPair findClosest(String[] words, String word1, String word2) {

    LocationPair best = new LocationPair(-1, -1);
    LocationPair current = new LocationPair(-1, -1);

    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      if (word.equals(word1)) {
        current.location1 = i;
        best.updateWithMin(current);
      } else if (word.equals(word2)) {
        current.location2 = i;
        best.updateWithMin(current);
      }
    }
    return best;
  }

  public static void main(String[] args) {
    String[] wordlist = AssortedMethods.getLongTextBlobAsStringList();
    String word1 = "river";
    String word2 = "life";
    LocationPair pair = findClosest(wordlist, word1, word2);
    System.out.println("Distance between <" + word1 + "> and <" + word2 + ">: " + pair.toString());

    System.out.println("*wordlist size:" + wordlist.length + ", Distance between <" + word1 + "> and <" + word2 + ">: "
        + findClosest_lucy(wordlist, word1, word2));

  }

}
