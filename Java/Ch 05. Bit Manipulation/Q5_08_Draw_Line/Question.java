package Q5_08_Draw_Line;

/**
 * //TODO: too complicate, will revisit it later, Feb 04, 2018, LL
 *
 * Draw line: a monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored
 * in one byte. the screen has width w, where w is divisible by 8 (that is, no byte will be split across rows). the
 * height of the screen, of course, can be derived from the length of the array and the width. Implement a function that
 * draws a horizontal line from (x1, y) to (x2, y).
 *
 * This method signature should look something like:
 *
 * drawline(byte[] screen, int width, int x1, int x2, int y)
 *
 * hint 366: first try the naive approach. can you set a particular "pixel"?
 *
 * hint 381: when you are drawing a long line, you will have entire bytes that will become a sequence of 1s. can you set
 * this all at once?
 *
 * hint 391: does your code handle the case when x1 and x2 are in the same byte?
 *
 * A naive solution to the problem is straightforward: iterate in a for loop from x1 to x2, set each pixel along the
 * way. but that's hardly any fun, and neither efficient
 *
 * a better solution is to recognize that if x1 and x2 are far away form each other, several full bytes will be
 * contained between them. these fully bytes can be set one at a time by doing screen[byte_pos] = 0xFF. the residual
 * start and end of the line can be set using masks.
 */
public class Question {

  public static int computeByteNum(int width, int x, int y) {
    return (width * y + x) / 8;
  }

  public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
    int start_offset = x1 % 8;
    int first_full_byte = x1 / 8;
    if (start_offset != 0) {
      first_full_byte++;
    }

    int end_offset = x2 % 8;
    int last_full_byte = x2 / 8;
    if (end_offset != 7) {
      last_full_byte--;
    }

    // Set full bytes
    for (int b = first_full_byte; b <= last_full_byte; b++) {
      screen[(width / 8) * y + b] = (byte) 0xFF;
    }

    byte start_mask = (byte) (0xFF >> start_offset);
    byte end_mask = (byte) ~(0xFF >> (end_offset + 1));

    // Set start and end of line
    if ((x1 / 8) == (x2 / 8)) { // If x1 and x2 are in the same byte
      byte mask = (byte) (start_mask & end_mask);
      screen[(width / 8) * y + (x1 / 8)] |= mask;
    } else {
      if (start_offset != 0) {
        int byte_number = (width / 8) * y + first_full_byte - 1;
        screen[byte_number] |= start_mask;
      }
      if (end_offset != 7) {
        int byte_number = (width / 8) * y + last_full_byte + 1;
        screen[byte_number] |= end_mask;
      }
    }
  }

  public static void printByte(byte b) {
    for (int i = 7; i >= 0; i--) {
      char c = ((b >> i) & 1) == 1 ? '1' : '_';
      System.out.print(c);
    }
  }

  public static void printScreen(byte[] screen, int width) {
    int height = screen.length * 8 / width;
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c += 8) {
        byte b = screen[computeByteNum(width, c, r)];
        printByte(b);
      }
      System.out.println("");
    }
  }

  public static void main(String[] args) {
    int width = 8 * 1;
    int height = 1;
    for (int r = 0; r < height; r++) {
      for (int c1 = 0; c1 < width; c1++) {
        for (int c2 = c1; c2 < width; c2++) {
          byte[] screen = new byte[width * height / 8];

          System.out.println("row: " + r + ": " + c1 + " -> " + c2);
          drawLine(screen, width, c1, c2, r);
          printScreen(screen, width);
          System.out.println("\n\n");
        }
      }
    }
  }
}
