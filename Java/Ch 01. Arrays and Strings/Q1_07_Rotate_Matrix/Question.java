package Q1_07_Rotate_Matrix;

import CtCILibrary.AssortedMethods;

/*
 * Rotate Matrix: given an image represented by a an NxN matrix, where each pixel in the image is 4 bytes, write a
 * method to rotate the image by 90 degrees. can you do this in places?
 *
 * hint 51: try thinking about it layer by layer. can you rotate a specific layer?
 * hint 100: rotating a specific layer would just mean swapping the values in four layers. if you wer asked
 * to swap the values in two layers, could you do this? can you then extend it to four layers?
 *
 * because we are rotating the matrix by 90 degrees, the easiest way to do this is to implement the rotation in layer.
 * we perform a circular to rotation on each layer: moving the top edge to the right edge, the right edge to the bottom
 * edge, the bottom edge to the left edge, and the left edge to the top edge
 *
 * implement the swap index by index. in this case, we do the following
 * for i= 0 to n
 *   temp= top[i]
 *   top[i] = left[i]
 *   left[i]=bottom[i]
 *   bottom[i]=right[i]
 *   right[i]=tem;
 * we perform such a swap on each layer (touch two layers), starting from the outermost layer and working our way inwards
 * (alternatively, we could start from the inner layer and work outwards)
 * this algorithm is O(N*N), which is the best we can do since any algorithm must touch all N*N elements
 */
public class Question {

  public static boolean rotate(int[][] matrix) {

    if (matrix.length == 0 || matrix.length != matrix[0].length) {
      return false; // Not a square
    }

    int n = matrix.length;

    //4x4 is 2 layers, 3x3 is 1 layer, draw a circle --> one layer
    for (int layer = 0; layer < n / 2; layer++) {

      int first = layer;
      int last = n - 1 - layer;
      System.out.println("** layer:" + layer + ", first:" + first + ", last:" + last);

      for (int i = first; i < last; i++) {
        int offset = i - first;
        int top = matrix[first][i]; // save top

        System.out.println("  i:" + i + ", offset:" + offset);
        System.out.println("  BEFORE change"
            + ", top matrix[" + first + "][" + i + "]:" + matrix[first][i]
            + ", left  matrix[" + (last - offset) + "][" + first + "]:" + matrix[last - offset][first]
            + ", bottom matrix[" + last + "][" + (last - offset) + "]:" + matrix[last][last - offset]
            + ", right matrix[" + i + "][" + last + "]: " + matrix[i][last]);

        //top: matrix[first][i]
        //left: matrix[last - offset][first]
        //right: matrix[i][last]
        //bottom: matrix[last][last - offset]

        // left -> top
        matrix[first][i] = matrix[last - offset][first];

        // bottom -> left
        matrix[last - offset][first] = matrix[last][last - offset];

        // right -> bottom
        matrix[last][last - offset] = matrix[i][last];

        // top -> right
        matrix[i][last] = top; // right <- saved top

        System.out.println("  AFTER change"
            + ", top matrix[" + first + "][" + i + "]:" + matrix[first][i]
            + ", left  matrix[" + (last - offset) + "][" + first + "]:" + matrix[last - offset][first]
            + ", bottom matrix[" + last + "][" + (last - offset) + "]:" + matrix[last][last - offset]
            + ", right matrix[" + i + "][" + last + "]: " + matrix[i][last]);
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[][] matrix = AssortedMethods.randomMatrix(4, 4, 0, 9);
    AssortedMethods.printMatrix(matrix);
    rotate(matrix);
    System.out.println();
    AssortedMethods.printMatrix(matrix);
  }
}
