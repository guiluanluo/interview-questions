package Q17_21_Volume_of_Histogram;

/*
 * Volume of histogram: imagine a histogram (bar graph). design an algorithm to compute the volume of water it could
 * hold if someone poured water across the top. you can assume that each histogram bar has width 1.
 *
 * example: input: {0,0,4,0,0,6,0,0,3,0,5,0,1,0,0,0}  black bars are the histogram. gray is water output: 26
 *
 * this is difficult problem! so let's come up with a good example to help us resolve it. the tallest bar forms a
 * barrier for water on its left and right. but the volume of water is actually controller by the next highest bar on
 * the left and right. what about the rest? we have essentially two subgraphs, one on the left and one on the right. to
 * find the volume there, we repeat a very similar process:
 *
 * 1) find the max. (actually, this is given to us. the highest on the left subgraph is the right border(6) and the
 *    highest on the right subgraph is the left boarder(5).)
 * 2) find the second tallest in each subgraph. in the left subgraph, this is 4. in the right subgraph, this is 3.
 * 3) compute the volume between the tallest and the second tallest.
 * 4) recurse on the edge of the graph
 *
 */
public class QuestionA {


  public static int computeHistogramVolume(int[] histogram) {
    int start = 0;
    int end = histogram.length - 1;

    int max = findIndexOfMax(histogram, start, end);

    int leftVolume = subgraphVolume(histogram, start, max, true);
    int rightVolume = subgraphVolume(histogram, max, end, false);

    return leftVolume + rightVolume;
  }


  public static int findIndexOfMax(int[] histogram, int start, int end) {
    int indexOfMax = start;
    for (int i = start + 1; i <= end; i++) {
      if (histogram[i] > histogram[indexOfMax]) {
        indexOfMax = i;
      }
    }
    return indexOfMax;
  }

  /**
   * compute the volume of a subgraph of the histogram. one max is at either start or end(depending on isLeft). find
   * second tallest, then compute volume between tallest and second tallest. then compute volume of subgraph.
   */
  public static int subgraphVolume(int[] histogram, int start, int end, boolean isLeft) {
    if (start >= end) {
      return 0;
    }

    int sum = 0;
    if (isLeft) {
      int max = findIndexOfMax(histogram, start, end - 1);
      sum += borderedVolume(histogram, max, end);
      sum += subgraphVolume(histogram, start, max, isLeft);
    } else {
      int max = findIndexOfMax(histogram, start + 1, end);
      sum += borderedVolume(histogram, start, max);
      sum += subgraphVolume(histogram, max, end, isLeft);
    }

    return sum;
  }

  /**
   * compute volume between start and end. assumes that tallest bar is at start and second tallest is at end.
   */
  public static int borderedVolume(int[] histogram, int start, int end) {
    if (start >= end) {
      return 0;
    }

    int min = Math.min(histogram[start], histogram[end]);
    int sum = 0;
    for (int i = start + 1; i < end; i++) {
      sum += min - histogram[i];
    }
    return sum;
  }

  public static void main(String[] args) {
    int[] histogram = {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 8, 0, 2, 0, 5, 2, 0, 3, 0, 0};
    int result = computeHistogramVolume(histogram);
    System.out.println(result);
  }

}
