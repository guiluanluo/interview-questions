package Q1_01_Is_Unique;

/**
 * we can reduce our space usage by a factor of eight by using a bit vector. in the below code, we assume that the
 * string only uses the lower case letters (a-z, total 26). This will allow us to use just a sing int.
 *
 * idea: 26 letters represents  26 bits: a = 00, b=01, c=10, d=11
 */
public class QuestionB {

  /* Assumes only letters a through z. */
  public static boolean isUniqueChars(String str) {
    //TODO: the length of input str could >26, don't need this check -LL
    if (str.length() > 26) { // Only 26 characters
      return false;
    }

    System.out.println("** int value of a:" + (int) 'a');

    int checker = 0;
    for (int i = 0; i < str.length(); i++) {
      int val = str.charAt(i) - 'a';

      System.out.println("checker:" + checker + ",  " + str.charAt(i) + ", after-a, val:" + val
          + ", 1 << val:" + (int) (1 << val) + ", (checker & (1 << val)): " + (checker & (1 << val)));

      if ((checker & (1 << val)) > 0) {
        return false;
      }

      //add the letter bits to checker
      checker |= (1 << val);
    }
    return true;
  }

  public static void bitshift_lucy() {
    //Some of the most basic operations on bits is shifting in the form of a shift left and a shift right
    //0001<<4 : 10000 = 16, 0010<<4: 0001 0000 = 32
    System.out.println("1 << 4(the integer 1 is signed left shift by 4 position):" + (int) (1 << 4));
    System.out.println("2 << 4 (the integer 2 is signed left shift by 4 position):" + (int) (2 << 4));

    //"unsigned shift right" operator: >>> for always shifting in a "0" regardless of the sign.
    //0001 0000 >>> 4: 0000 0010 = 2
    System.out.println("32 >>> 4 (the integer 32 is unsignedleft shift by 4 position):" + (int) (32 >>> 4));
  }

  public static void main(String[] args) {
   // bitshift_lucy();

    String[] words = {"adcade", "heleo", "apple", "kite", "padle"};
    for (String word : words) {
      System.out.println(word + ": " + isUniqueChars(word) + "\n");
    }

//    String word_65 = "abcde111112222233333444445555566666777778888899999000001111122222";
//    String[] word_130 = {word_65 + word_65};
//    for (String word : word_130) {
//      System.out.println(word + ": " + isUniqueChars(word) + "\n");
//    }
  }

}
