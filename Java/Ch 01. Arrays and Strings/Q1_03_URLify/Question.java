package Q1_03_URLify;

/*
 * URLify: write a method to replace all space in a string with '%20%'. you may assume that the string has sufficient
 * space at the end to hold the additional characters, and that you are given the "true" length of the string. (note: if
 * implementing in java, please use a character array so that you can perform this operation in place)
 *
 * Example
 * Input: "Mr John Smith    ", 13        output:"Mr%20John%20Smith"
 *
 * Hint 53: it's often easiest to modify strings by going from the end of the string to the beginning
 * hint 118: you might find you need to know the number of spaces. can you just count them?
 */

import CtCILibrary.AssortedMethods;

public class Question {

  // Assume string has sufficient free space at the end
  public static void replaceSpaces(char[] str, int trueLength) {
    int spaceCount = 0, index, i = 0;
    for (i = 0; i < trueLength; i++) {
      if (str[i] == ' ') {
        spaceCount++;
      }
    }

    index = trueLength + spaceCount * 2;
    if (trueLength < str.length) {
      str[trueLength] = '\0';
    }

    for (i = trueLength - 1; i >= 0; i--) {
      if (str[i] == ' ') {
        str[index - 1] = '0';
        str[index - 2] = '2';
        str[index - 3] = '%';
        index = index - 3;
      } else {
        str[index - 1] = str[i];
        index--;
      }
    }
  }

  public static int findLastCharacter(char[] str) {
    for (int i = str.length - 1; i >= 0; i--) {
      if (str[i] != ' ') {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    String str = "Mr John Smith    ";
    char[] arr = str.toCharArray();
    int trueLength = findLastCharacter(arr) + 1;
    replaceSpaces(arr, trueLength);
    System.out.println("\"" + AssortedMethods.charArrayToString(arr) + "\"");
  }
}
