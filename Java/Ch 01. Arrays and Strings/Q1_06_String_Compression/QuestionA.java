package Q1_06_String_Compression;

import java.util.Hashtable;

/*
 * String Compression: implement a method to perform basic string compression using the counts of repeated characters.
 * for example, the string aabcccccaa would become a2b1c5a3. if the "compressed" string would not become smaller that
 * the original string, your method should return the original string. you can assume the string has only uppercase and
 * lowercase letters(a-z)
 *
 * hint 92: do the easy thing first. compress the string, then compare the lengths
 * hint 110: be careful that you are not repeatedly concatenating string together. this can be very inefficient.
 *
 * Solution A: we iterate through the string, copying characters to a new string and counting the repeats. at each
 * iteration. check if the current character is the same as the next character. if not, add its compressed version to
 * the result. the runtime is O(p + k*k), where p is the size of the original string and k is the number of character
 * sequences.
 *
 * For example, if the string is aabccdeeaa, then there are six character sequences. it is slow because
 * string concatenation operation in O(n*n) time. we could fix this by using StringBuilder ==> Solution B
 *
 * Both solution A , B create the compressed string first and then return the shorter of the input string
 * and the compressed string.
 */
public class QuestionA {

  public static String compress_lucy1(String str) {
    Hashtable<Character, Integer> hashTable = new Hashtable();
    StringBuilder uniqueBuilder = new StringBuilder();
    char[] strArray = str.toCharArray();
    for (int i = 0; i < strArray.length; i++) {
      Integer value = hashTable.get(strArray[i]);
      if (value == null) {
        hashTable.put(strArray[i], 1);
        uniqueBuilder.append(strArray[i]);
      } else {
        hashTable.put(strArray[i], value + 1);
      }
    }

    StringBuilder compressBuilder = new StringBuilder();
    for (Character character : uniqueBuilder.toString().toCharArray()) {
      compressBuilder.append(character).append(hashTable.get(character));
    }

    String compressedStr = compressBuilder.toString();
    return compressedStr.length() > str.length() ? str : compressedStr;
  }

  public static String compress_lucy2(String str) {

    StringBuilder compressBuilder = new StringBuilder();
    char[] strArray = str.toCharArray();

    int counter = 1;
    Character previous = strArray[0];
    for (int i = 1; i < strArray.length; i++) {
      Character current = strArray[i];
      if (current != previous) {
        compressBuilder.append(previous).append(counter);
        previous = current;
        counter = 1;
      } else {
        counter++;
      }
    }

    if (counter > 1) {
      compressBuilder.append(previous).append(counter);
    }

    String compressedStr = compressBuilder.toString();
    return compressedStr.length() > str.length() ? str : compressedStr;
  }

  public static String compressBad(String str) {
    String compressedString = "";
    int countConsecutive = 0;

    for (int i = 0; i < str.length(); i++) {
      countConsecutive++;

			/* If next character is different than current, append this char to result.*/
      if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
        compressedString += "" + str.charAt(i) + countConsecutive;
        countConsecutive = 0;
      }
    }
    return compressedString.length() < str.length() ? compressedString : str;
  }

  public static void main(String[] args) {
    String str = "aa";
    System.out.println(str);
    System.out.println(compressBad(str));

    System.out.println("lucy1:" + compress_lucy1(str));
    System.out.println("lucy2:" + compress_lucy2(str));

    str = "aaacdd";
    System.out.println(str);
    System.out.println("lucy1:" + compress_lucy1(str));
    System.out.println("lucy2:" + compress_lucy2(str));

    str = "abcd";
    System.out.println(str);
    System.out.println("lucy1:" + compress_lucy1(str));
    System.out.println("lucy2:" + compress_lucy2(str));
  }
}
