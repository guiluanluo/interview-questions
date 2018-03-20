package Q1_06_String_Compression;

/*
 * instead, we can check in advance. this will be more optional in cases where we don't have a large number of repeating
 * characters. it will avoid us having to create a string that we never use. the downside of this is that it causes a
 * second loop through the characters and also adds nearly duplicate code.
 *
 * step 1: count the length of final compression string
 * step 2: if final compression string is greater that the original string, then return the original string
 * step 3: compressed the string using solution B with the StringBuilder
 */
public class QuestionC {

  public static String compress(String str) {

    int finalLength = countCompression(str);
    if (finalLength >= str.length()) {
      return str;
    }

    StringBuffer compressed = new StringBuffer(finalLength); // initialize capacity
    int countConsecutive = 0;

    for (int i = 0; i < str.length(); i++) {
      countConsecutive++;

			/* If next character is different than current, append this char to result.*/
      if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
        compressed.append(str.charAt(i));
        compressed.append(countConsecutive);
        countConsecutive = 0;
      }
    }
    return compressed.toString();
  }

  public static int countCompression(String str) {
    int compressedLength = 0;
    int countConsecutive = 0;

    for (int i = 0; i < str.length(); i++) {
      countConsecutive++;

			/* If next character is different than current, append this char to result.*/
      if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
        compressedLength += 1 + String.valueOf(countConsecutive).length();
        countConsecutive = 0;
      }
    }
    return compressedLength;
  }

  public static void main(String[] args) {
    String str = "aa";
    System.out.println(str);
    System.out.println(compress(str));
  }
}
