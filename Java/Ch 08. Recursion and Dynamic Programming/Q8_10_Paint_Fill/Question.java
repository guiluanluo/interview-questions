package Q8_10_Paint_Fill;

/**
 * Paint fill: implement the "paint fill" function that one might see on many image editing programs. that is, given a
 * screen (represented by a two-dimensional array of colors), a point, and a new color, fill in the surrounding area
 * until the color changes from the original color.
 *
 * hint 364: think about this as a graph
 *
 * hint 382: you can implement this using depth-first search(or breadth-first search). each adjacent pixel of the
 * "right" color is a connected edge.
 *
 * Solution: first, let's visualize how this method works. when we call paintFill("click" paint fill in the image
 * editing application) on, say, a green pixel, we want to "bleed" outwards. pixel by pixel, we expand outwards by
 * calling paintFill() on the surrounding pixel. when we hit a pixel that is not green, we stop.
 */
public class Question {

  public enum Color {
    Black, White, Red, Yellow, Green
  }

  public static String PrintColor(Color c) {
    switch (c) {
      case Black:
        return "B";
      case White:
        return "W";
      case Red:
        return "R";
      case Yellow:
        return "Y";
      case Green:
        return "G";
    }
    return "X";
  }

  public static void PrintScreen(Color[][] screen) {
    for (int r = 0; r < screen.length; r++) {
      for (int c = 0; c < screen[0].length; c++) {
        System.out.print(PrintColor(screen[r][c]));
      }
      System.out.println();
    }
  }

  public static int randomInt(int n) {
    return (int) (Math.random() * n);
  }

  public static boolean PaintFill(Color[][] screen, int r, int c, Color originalColor, Color newColor) {

    if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length) {
      return false;
    }

    if (screen[r][c] == originalColor) {
      screen[r][c] = newColor;
      PaintFill(screen, r - 1, c, originalColor, newColor); // up
      PaintFill(screen, r + 1, c, originalColor, newColor); // down
      PaintFill(screen, r, c - 1, originalColor, newColor); // left
      PaintFill(screen, r, c + 1, originalColor, newColor); // right
    }

    return true;
  }

  public static boolean PaintFill(Color[][] screen, int r, int c, Color newColor) {
    if (screen[r][c] == newColor) {
      return false;
    }

    System.out.println("originalColor:" + screen[r][c] + ", newColor:" + newColor);
    return PaintFill(screen, r, c, screen[r][c], newColor);
  }

  public static void main(String[] args) {
    int N = 10;
    Color[][] screen = new Color[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        screen[i][j] = Color.Black;
      }
    }

    for (int i = 0; i < 100; i++) {
      screen[randomInt(N)][randomInt(N)] = Color.Green;
    }

    System.out.println("original screen:");
    PrintScreen(screen);
    System.out.println("call PaintFill(screen, 2, 2, Color.White)");
    PaintFill(screen, 2, 2, Color.White);
    System.out.println("after called PaintFill(screen, 2, 2, Color.White), the screen is:");
    PrintScreen(screen);
  }

}
