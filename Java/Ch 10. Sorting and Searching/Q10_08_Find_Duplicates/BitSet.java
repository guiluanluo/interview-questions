package Q10_08_Find_Duplicates;

/*
 * Lucy Notes: The prefix 0x indicates hexadecimal and 0b indicates binary:
 *
 * int decVal = 26; //The number 26, in decimal
 * int hexVal = 0x1a; //The number 26, in hexadecimal.   0x1a = 1 1010
 * int binVal = 0b11010; //The number 26, in binary
 *
 * Hexadecimal (or "hex" for short) is a numbering system which works similarly to our regular decimal system, but where
 * a single digit can take a value of 0-15 rather than 0-9. The extra digits are represented by the letters A-F
 */
class BitSet {

  int[] bitset;

  public BitSet(int size) {
    bitset = new int[(size >> 5) + 1]; // divide by 32
  }

  boolean get(int pos) {
    int wordNumber = (pos >> 5); // divide by 32
    int bitNumber = (pos & 0x1F); // mod 32   lucy - 0x1F is 31
    return (bitset[wordNumber] & (1 << bitNumber)) != 0;
  }

  void set(int pos) {
    int wordNumber = (pos >> 5); // divide by 32
    int bitNumber = (pos & 0x1F); // mod 32
    bitset[wordNumber] |= 1 << bitNumber;
  }

  int getBitSetSize() {
    return bitset.length;
  }
}
