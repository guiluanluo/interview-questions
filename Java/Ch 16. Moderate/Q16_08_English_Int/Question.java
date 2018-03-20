package Q16_08_English_Int;

import java.util.LinkedList;

import CtCILibrary.AssortedMethods;

/**
 * English int: given any integer, print an english phrase that describes the integer(eg, "one thousand, two hundred
 * thirty four").
 *
 * hint 501: try thinking about the number in terms of chunks of three digits
 *
 * hint 587: have you considered negative number? does your solution work for values like 100,030,000?
 *
 * hint 687: consider dividing up a number into sequences of three digits
 *
 * Solution: this is not an especially challenging problem, but it is a somewhat tedious one. the key is to be organized
 * in how you approach the problem - and to make user you have good test cases
 *
 * we can think about converting a number link 19,323,984 as converting each of three 3-digit segments of the number,
 * and inserting "thousands" and "millions" in between as appropriate. that is:
 * convert(19,323,984) = convert(19) +" millions " + convert(323) + " thousands " + convert(984)
 */
public class Question {

  public static String[] smalls = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
      "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
  public static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
  public static String hundred = "Hundred";
  public static String[] bigs = {"", "Thousand", "Million", "Billion"};
  public static String negative = "Negative";

  public static String convert(int num) {
    System.out.println("convert(" + num + ")");

    if (num == 0) {
      return smalls[0];
    } else if (num < 0) {
      return negative + " " + convert(-1 * num);
    }

    LinkedList<String> parts = new LinkedList<String>();
    int chunkCount = 0;

    while (num > 0) {
      if (num % 1000 != 0) {
        String chunk = convertChunk(num % 1000) + " " + bigs[chunkCount];
        System.out.println(num % 1000 + ", chunk: " + chunk);
        parts.addFirst(chunk);
      }
      num /= 1000; // shift chunk
      chunkCount++;
    }

    String returnedValue = listToString(parts);
    System.out.println("returned " + returnedValue);
    return returnedValue;
  }

  /* Convert a linked list of strings to a string, dividing it up with spaces. */
  public static String listToString(LinkedList<String> parts) {
    StringBuilder sb = new StringBuilder();
    while (parts.size() > 1) {
      sb.append(parts.pop());
      sb.append(" ");
    }
    sb.append(parts.pop());
    return sb.toString();
  }

  public static String convertChunk(int number) {
    LinkedList<String> parts = new LinkedList<String>();

		/* Convert hundreds place */
    if (number >= 100) {
      parts.addLast(smalls[number / 100]);
      parts.addLast(hundred);
      number %= 100;
    }

		/* Convert tens place */
    if (number >= 10 && number <= 19) {
      parts.addLast(smalls[number]);
    } else if (number >= 20) {
      parts.addLast(tens[number / 10]);
      number %= 10;
    }

		/* Convert ones place */
    if (number >= 1 && number <= 9) {
      parts.addLast(smalls[number]);
    }

    return listToString(parts);
  }

  public static void main(String[] args) {

    /* numbers between 100000 and 1000000 */
    for (int i = 0; i < 8; i++) {
      int value = (int) (-1 * Math.pow(10, i));
      String s = convert(value);
      System.out.println(value + ": " + s + "\n");
    }

		/* numbers between 0 and 100 */
    for (int i = 0; i < 10; i++) {
      int value = AssortedMethods.randomIntInRange(0, 100);
      String s = convert(value);
      System.out.println(value + ": " + s + "\n");
    }

		/* numbers between 100 and 1000 */
    for (int i = 0; i < 10; i++) {
      int value = AssortedMethods.randomIntInRange(100, 1000);
      String s = convert(value);
      System.out.println(value + ": " + s + "\n");
    }

		/* numbers between 1000 and 100000 */
    for (int i = 0; i < 10; i++) {
      int value = AssortedMethods.randomIntInRange(1000, 100000);
      String s = convert(value);
      System.out.println(value + ": " + s + "\n");
    }

		
		/* numbers between 100000 and 100000000 */
    for (int i = 0; i < 10; i++) {
      int value = AssortedMethods.randomIntInRange(100000, 100000000);
      String s = convert(value);
      System.out.println(value + ": " + s + "\n");
    }

		/* numbers between 100000000 and 1000000000 */
    for (int i = 0; i < 10; i++) {
      int value = AssortedMethods.randomIntInRange(100000000, 1000000000);
      String s = convert(value);
      System.out.println(value + ": " + s + "\n");
    }
                
		/* numbers between 1000000000 and Integer.MAX_VALUE */
    for (int i = 0; i < 10; i++) {
      int value = AssortedMethods.randomIntInRange(1000000000, Integer.MAX_VALUE);
      String s = convert(value);
      System.out.println(value + ": " + s + "\n");
    }
  }
}
