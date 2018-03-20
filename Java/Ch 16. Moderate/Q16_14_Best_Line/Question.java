package Q16_14_Best_Line;

import java.util.ArrayList;
import java.util.Set;

import CtCILibrary.HashMapList;

/**
 * Best line: given a two-dimensional graph with points on it, find a line which passes the most number of points
 *
 * hint 490:  sometimes, a brute force is a pretty good solution. can you try all possible lines?
 *
 * hint 519: you cannot truly try all possible lines in the world - that's infinite. but you know that a "best" line
 * must intersect at least two points. can you connect each pair of points? can you check if each line is indeed the
 * best line?
 *
 * hint 528:  you should be able to get to an O(N*N) solution
 *
 * hint 562: have you tried using a hash table?
 *
 * Solution: we just "draw" an infinite line(that is, not a line segment) between every tow points and, using a hash
 * table, track which line is the most common. this will takeO(N*N) time, since there are N*N line segments.
 *
 * we will represent a line as a slop and y-intercept(as opposed to a pair of points), which allow us to easily check to
 * see if the line from (x1,y1) to (x2,y2) is equivalent to the line from (x3,y3) to (x4,y4).
 *
 * to find the most common line the, we just iterate through all lines segments, using a hash table to count the number
 * of times we've see each line. easy enough!
 *
 * however, there's one little complication. we are defining two lines to be equal if the lines have the same slop &
 * y-intercept. we are the, furthermore, hashing the lines based on these values( specifically, based on slop). the
 * problem is that floating point numbers cannot always be represented accurately in binary. we resolve this by checking
 * if two floating point numbers are within an epsilon value of each other.
 *
 * what does this mean for our hash table? it meas that two lines with "equal" slope may not be hashed to the same
 * value. to solve this, we will round the slope down to the next epsilon and use this flooredSlope as the hash key.
 * then, to retrieve all lines that are potentially equal, we will search the hash table at three spots: flooredSlope,
 * flooredSlop-epsilon, and flooredSlop+epsilon. that will ensure that we've checked out all lines that might be equal.
 *
 * we need to be careful about the calculation of slope of a line. the line might be completed vertical, which means
 * that it doesn't have a y-intercept and its slope is infinite. we can keep track of this in a separate
 * flag(infinite_slop). we need to check this condition in the equals method.
 */
public class Question {

  /* Find line that goes through most number of points. */
  public static Line findBestLine(GraphPoint[] points) {
    HashMapList<Double, Line> linesBySlope = getListOfLines(points);
    return getBestLine(linesBySlope);
  }

  /* Add each pair of points as a line to the list. */
  public static HashMapList<Double, Line> getListOfLines(GraphPoint[] points) {
    HashMapList<Double, Line> linesBySlope = new HashMapList<Double, Line>();
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        Line line = new Line(points[i], points[j]);
        double key = Line.floorToNearestEpsilon(line.slope);
        linesBySlope.put(key, line);
      }
    }
    return linesBySlope;
  }

  /* Return the line with the most equivalent other lines. */
  public static Line getBestLine(HashMapList<Double, Line> linesBySlope) {
    Line bestLine = null;
    int bestCount = 0;

    Set<Double> slopes = linesBySlope.keySet();
    for (double slope : slopes) {
      ArrayList<Line> lines = linesBySlope.get(slope);
      for (Line line : lines) {
        /* count lines that are equivalent to current line */
        int count = countEquivalentLines(linesBySlope, line);

				/* if better than current line, replace it */
        if (count > bestCount) {
          bestLine = line;
          bestCount = count;
          bestLine.Print();
          System.out.println(bestCount);
        }
      }
    }
    return bestLine;
  }

  /**
   * Check HashMap for lines that are equivalent. Note that we need to check one epsilon above and below the actual
   * slope since we're defining two lines as equivalent if they're within an epsilon of each other.
   */
  public static int countEquivalentLines(HashMapList<Double, Line> linesBySlope, Line line) {

    double key = Line.floorToNearestEpsilon(line.slope);

    int count = countEquivalentLines(linesBySlope.get(key), line);
    count += countEquivalentLines(linesBySlope.get(key - Line.epsilon), line);
    count += countEquivalentLines(linesBySlope.get(key + Line.epsilon), line);

    return count;
  }

  /* Count lines within an array of lines which are "equivalent" (slope and y-y_intercept are within an epsilon value) to a given line */
  public static int countEquivalentLines(ArrayList<Line> lines, Line line) {
    if (lines == null) {
      return 0;
    }

    int count = 0;
    for (Line parallelLine : lines) {
      if (parallelLine.isEquivalent(line)) {
        count++;
      }
    }
    return count;
  }


  public static GraphPoint[] createPoints() {
    int n_points = 100;
    System.out.println("Points on Graph\n***************");
    GraphPoint[] points = new GraphPoint[n_points - 1];
    for (int i = 0; i < n_points / 2; i++) {
      GraphPoint p = new GraphPoint(i, 2.3 * ((double) i) + 20.0);
      points[i] = p;
      System.out.println(p.toString());
    }

    for (int i = 0; i < n_points / 2 - 1; i++) {
      GraphPoint p = new GraphPoint(i, 3.0 * ((double) i) + 1.0);
      points[n_points / 2 + i] = p;
      System.out.println(p.toString());
    }
    System.out.println("****************\n");
    return points;
  }

  public static int validate(Line line, GraphPoint[] points) {
    int count = 0;
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        Line other = new Line(points[i], points[j]);
        if (line.isEquivalent(other)) {
          count++;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    GraphPoint[] points = createPoints();
    Line line = findBestLine(points);
    line.Print();
    System.out.println(validate(line, points));
  }
}
