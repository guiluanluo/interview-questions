package Q17_13_ReSpace;

import java.util.HashSet;

import CtCILibrary.AssortedMethods;

/**
 * Re-space: on, no! you have accidentally removed all spaces, punctuation, and capitalization in a lengthy document. a
 * sentence link "I reset the computer. It still didn't boot!" became "iresetthecomputeritstilldidntboot", you will deal
 * with the punctuation and capitalization later; right now you need to re-insert the spaces. most of words are in a
 * dictionary but a few are not. given a dictionary(a list of strings) an the document(a string), design an algorithm to
 * unconcatenate the document in a way that minimizes the number of unrecognized characters.
 *
 * example: input: "jesslookedjustliketimherbrother" output: "jess looked just like tim her brother" (7 unrecognized
 * characters)
 *
 * Solution: some interview like to cut to the chase and give you the specific problems. other, though, like to give you
 * lot of unnecessary context, like this problem has. it's useful in such cases to boil down the problem to what it's
 * really all about.
 *
 * in this case, the problem is really about finding a way to break up a string into separate words such that as few
 * characters as possible are "left out" of the parsing. note that we do not attempt to "understand" the string. we
 * could just well parse "thisisawesome" to be "this is a we some" as we could "this is awesome".
 *
 * Solution A: Brute force: the key to this problem is finding a way to define the solution(that is, parsed string) in
 * terms of its subproblems. one way to do this is recursing through the string.
 *
 * the very first choice we make is where to insert the first space. after the first char?, second char?, third char?
 * let's image this in terms of a string like "thisismikesfavoritefood". what is the first space we insert? insert a
 * space after "t"->1 invalid char; "th"->2 invalid char; "thi"->3 invalid char; "this"->word, 0 invalid char;
 * "thisi"->5 invalid char, and so on
 *
 * after we choose the first space, we can recursively pick the second space, and then third space, and so on.
 */
public class QuestionA {

  public static String bestSplit_lucy(HashSet<String> dictionary, String sentence) {
    ParseResult result = new ParseResult(0, "");
    split_lucy(dictionary, sentence.toCharArray(), 0, "", result);
    return result == null ? null : result.parsed;
  }

  public static void split_lucy(HashSet<String> dictionary, char[] sentence, int startIndex, String prefix,
      ParseResult result) {
    if (startIndex >= sentence.length) {
      return;
    }

    if (prefix.length() >= sentence.length) {
      result.invalid = 0;
      result.parsed = "";
      return;
    }

    String previous = "";
    boolean found = false;
    for (int i = 0; i < sentence.length; i++) {
      prefix = prefix + sentence[i];
      if (dictionary.contains(prefix)) {
        found = true;
        previous = prefix;
      } else {
        if (found) {
          result.parsed = result.parsed + " " + previous;
          prefix = "" + sentence[i];
          previous = "";
        }
      }
    }

    result.parsed = result.parsed + " " + prefix;
    result.invalid++;
  }


  public static String bestSplit(HashSet<String> dictionary, String sentence) {
    ParseResult r = split(dictionary, sentence, 0);
    return r == null ? null : r.parsed;
  }

  public static ParseResult split(HashSet<String> dictionary, String sentence, int start) {
    if (start >= sentence.length()) {
      return new ParseResult(0, "");
    }

    int bestInvalid = Integer.MAX_VALUE;
    String bestParsing = null;

    String partial = "";
    int index = start;
    while (index < sentence.length()) {
      char c = sentence.charAt(index);
      partial += c;

      int invalid = dictionary.contains(partial) ? 0 : partial.length();
      if (invalid < bestInvalid) { // Short circuit

        /**
         * Recurse, putting a space after this character.
         * If this is better than the current best option, replace the best option.
         */
        ParseResult result = split(dictionary, sentence, index + 1);

        if (invalid + result.invalid < bestInvalid) {
          bestInvalid = invalid + result.invalid;
          bestParsing = partial + " " + result.parsed;
          if (bestInvalid == 0) {
            break;
          }
        }
      }

      index++;
    }

    return new ParseResult(bestInvalid, bestParsing);
  }


  public static String clean(String str) {
    char[] punctuation = {',', '"', '!', '.', '\'', '?', ','};
    for (char c : punctuation) {
      str = str.replace(c, ' ');
    }
    return str.replace(" ", "").toLowerCase();
  }

  public static void main(String[] args) {
    HashSet<String> dictionary = AssortedMethods.getWordListAsHashSet();
    String sentence = "As one of the top companies in the world, Google"; // will surely attract the attention of computer gurus. This does not, however, mean the company is for everyone.";
    sentence = clean(sentence);
    System.out.println(sentence);
    //Result v = parse(0, 0, new HashMap<Integer, Result>());
    //System.out.println(v.parsed);

    System.out.println("bestSplit_lucy:" + bestSplit_lucy(dictionary, sentence));

//    System.out.println(bestSplit(dictionary, sentence));
  }

}
