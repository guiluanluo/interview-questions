package Q16_13_Bisect_Squares;

/**
 * TODO: can square uses 4 points to represent it? the Square.java is not easy understand, revisit it later, 02/21/2018
 *
 * Lucy key point: find the centers of two squares and draw a line joining them
 *
 * Bisect squares: given two squares on a two-dimensional plane, find a line that would cut these two squares in half.
 * assume that the top and the bottom sides of the square run parallel to the x-axis.
 *
 * hint 467: draw a square and a bunch of lines that cut it in half. where are those lines located?
 *
 * hint 478: any straight line that cuts a square in half goes through the center of the square. how then can you find a
 * line that cuts two squares in half?
 *
 * hint 527: to cut two squares in half, a line must go through the middle of both squares
 *
 * hint 559: given a line (slope and y-y_intercept), can you find where it intersects another line?
 *
 * Solution: before we start, we should think about what exactly this problem means by a "line". is a line defined by a
 * slope and a y-y_intercept? or by any two points on the line? or, should the line be really a line segment, which starts
 * and ends at the edges of the squares?
 *
 * we will assume, since it makes the problem a bit more interesting, that we mean the third option: that the line
 * should end at the edges of the squares. in an interview situation, you should discuss this with your interviewer.
 *
 * the line that cuts two squares in half must connect the two middles. we can easily calculate the slope, knowing the
 * slop=(y1-y1)/(x2-x1). once we calculate the slope using the two middles, we can use the same equation to calculate
 * the start and end points of the line segment.
 *
 * in the below code, we will assume the origin(0,0) is in the upper left-hand corner.
 *
 * Note: the main goal of this problem is to see how careful you are about coding. it's easy to glance over the special
 * cases(e.g., the two squares having the same middle). you should make a list of these special cases before you start
 * the problem and make sure to handle them appropriately. this is a question that require careful and thorough
 * testing.
 */
public class Question {

  public static int randomInt(int n) {
    return (int) (Math.random() * n);
  }

  public static void printLine(Line l) {
    System.out.println(l.start.x + "\t" + l.start.y);
    System.out.println(l.end.x + "\t" + l.end.y);
  }

  public static void printSquare(Square s) {
    System.out.println(s.left + "\t" + s.top + "\t" + s.size);
  }

  public static boolean isApproxEqual(double d1, double d2) {
    double epsilon = .001;
    if (Math.abs(d1 - d2) < epsilon) {
      return true;
    }
    return false;
  }

  public static boolean isApproxEqual(Point p1, Point p2) {
    return isApproxEqual(p1.x, p2.x) && isApproxEqual(p1.y, p2.y);
  }

  public static boolean doTest(Square s1, Square s2, Point start, Point end) {
    Line line = s1.cut(s2);
    boolean r = (isApproxEqual(line.start, start) && isApproxEqual(line.end, end)) || (isApproxEqual(line.start, end)
        && isApproxEqual(line.end, start));
    if (!r) {
      printSquare(s1);
      printSquare(s2);
      printLine(line);
      System.out.println(start.toString());
      System.out.println(end.toString());
      System.out.println();
      return r;
    }
    return r;
  }

  public static boolean doTestFull(Square s1, Square s2, Point start, Point end) {
    return doTest(s1, s2, start, end) && doTest(s2, s1, start, end);
  }

  public static void doTests() {
    // Equal
    doTestFull(new Square(1, 1, 5), new Square(1, 1, 5), new Point(3.5, 1), new Point(3.5, 6));

    // Concentric
    doTestFull(new Square(1, 1, 5), new Square(2, 2, 3), new Point(3.5, 1), new Point(3.5, 6));

    // Partially overlapping -- side by side
    doTestFull(new Square(10, 10, 10), new Square(8, 10, 10), new Point(8, 15), new Point(20, 15));

    // Partially overlapping -- corners
    doTestFull(new Square(10, 10, 10), new Square(8, 7, 7), new Point(8.777777, 7), new Point(18.8888888, 20));

    // Partially overlapping -- on top of each other
    doTestFull(new Square(10, 10, 10), new Square(8, 7, 15), new Point(8, 22), new Point(23, 7));

    // Not overlapping -- side by side
    doTestFull(new Square(10, 10, 10), new Square(19, 25, 4), new Point(12.5, 10), new Point(22, 29));

    // Not overlapping -- on top of each other
    doTestFull(new Square(10, 10, 10), new Square(4, 4, 3), new Point(4, 4), new Point(20, 20));

    // Contained
    doTestFull(new Square(10, 10, 10), new Square(12, 14, 3), new Point(10, 16.66666), new Point(20, 13.333));
  }

  public static void main(String[] args) {
    /* For an easy way to test these, open up Square Cut Tester.xlsx
     * in the Chapter 7, Problem 5 folder. Copy and paste the exact
		 * output from below into the file (including all tabs).
		 */
    doTests();
  }

}
