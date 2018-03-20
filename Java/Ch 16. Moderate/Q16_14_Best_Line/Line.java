package Q16_14_Best_Line;

public class Line {

  public static double epsilon = 0.5;
  public double slope;
  public double y_intercept;

  private boolean infinite_slope = false;

  public Line(GraphPoint p, GraphPoint q) {
    if (Math.abs(p.x - q.x) > epsilon) { // if x�s are different
      slope = (p.y - q.y) / (p.x - q.x); // compute slope
      y_intercept = p.y - slope * p.x; // y y_intercept from y=mx+b
    } else {
      infinite_slope = true;
      y_intercept = p.x; // x-y_intercept, since slope is infinite
    }
  }
  
  public static double floorToNearestEpsilon(double d) {
    int r = (int) (d / epsilon);
    return ((double) r) * epsilon;
  }

  public boolean isEquivalent(double a, double b) {
    return (Math.abs(a - b) < epsilon);
  }

  public boolean isEquivalent(Object o) {
    Line l = (Line) o;
    if (isEquivalent(l.slope, slope) && isEquivalent(l.y_intercept, y_intercept) && (infinite_slope
        == l.infinite_slope)) {
      return true;
    }
    return false;
  }


  public void Print() {
    System.out.println("y = " + slope + "x + " + y_intercept);
  }

}
