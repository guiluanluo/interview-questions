package Q17_13_ReSpace;

import java.util.HashSet;

import CtCILibrary.AssortedMethods;

public class QuestionB {

  public static String bestSplit(HashSet<String> dictionary, String sentence) {
    ParseResult[] memo = new ParseResult[sentence.length()];
    ParseResult r = split(dictionary, sentence, 0, memo);
    return r == null ? null : r.parsed;
  }

  public static ParseResult split(HashSet<String> dictionary, String sentence, int start, ParseResult[] memo) {
    if (start >= sentence.length()) {
      return new ParseResult(0, "");
    }

    if (memo[start] != null) {
      return memo[start];
    }

    int bestInvalid = Integer.MAX_VALUE;
    String bestParsing = null;

    String partial = "";
    int index = start;
    while (index < sentence.length()) {
      char c = sentence.charAt(index);
      partial += c;
      int invalid = dictionary.contains(partial) ? 0 : partial.length();
      System.out.println("**index:" + index + ", partial:" + partial
          + ", invalid:" + invalid + ", bestInvalid:" + bestInvalid);

      if (invalid < bestInvalid) { // Short circuit
        System.out.println(" ==if, invalid:" + invalid + ", bestInvalid:" + bestInvalid
            + ", call split() start=" + (index + 1) + ", meno:" + printParseResults(memo));
        /**
         * Recurse, putting a space after this character. If this is better than the current best option,
         * replace the best option.
         */
        ParseResult result = split(dictionary, sentence, index + 1, memo);
        System.out.println(" ==result:" + result.invalid + ", " + result.parsed + "; invalid:" + invalid);
        if (invalid + result.invalid < bestInvalid) {
          bestInvalid = invalid + result.invalid;
          bestParsing = partial + " " + result.parsed;
          if (bestInvalid == 0) {
            System.out.println(
                "     bestInvalid:" + bestInvalid + ", (invalid:" + invalid + ", result.invalid:" + result.invalid);
            break; // Short circuit
          }
        }
      }

      index++;
    }
    memo[start] = new ParseResult(bestInvalid, bestParsing);
    return memo[start];
  }

  public static String clean(String str) {
    char[] punctuation = {',', '"', '!', '.', '\'', '?', ','};
    for (char c : punctuation) {
      str = str.replace(c, ' ');
    }
    return str.replace(" ", "").toLowerCase();
  }

  public static String printParseResults(ParseResult[] results) {
    if (results == null || results.length == 0) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    builder.append("[");
    for (int i = 0; i < results.length; i++) {
      ParseResult result = results[i];
      if (result != null) {
        builder.append(i + ":{").append(result.invalid).append(",").append(result.parsed).append("}  ");
      }
    }
    builder.append("]");
    return builder.toString();
  }

  public static void main(String[] args) {
    HashSet<String> dictionary = AssortedMethods.getWordListAsHashSet();
    String sentence = "As one of the topk companies in the world"; //, Google will surely attract the attention of computer gurus. This does not, however, mean the company is for everyone.";
    sentence = clean(sentence);
    System.out.println(sentence);
    //Result v = parse(0, 0, new HashMap<Integer, Result>());
    //System.out.println(v.parsed);
    System.out.println(bestSplit(dictionary, sentence));
  }

}
