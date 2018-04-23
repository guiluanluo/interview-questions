package Q16_22_Langtons_Ant;

/**
 * Langton's ant: an ant is sitting on an infinite grid of white and black squares. initially, the grid is all white and
 * the ant faces right. at each step, it does the following: 1) at a white square, flip the color of the square, turn 90
 * degrees right(clockwise), and move forward on unit. 2) at a black square, flip the color of the square, turn 90
 * degrees left(counter-clockwise), and move forward one unit.
 *
 * write a program to simulate the first K moves that the ant makes and print the final board as a grid. note that you
 * are not provided with the data structure to represent the grid. this is something you must design yourself. the only
 * input to your method is K. you should print the final grid and return nothing. the method signature might be
 * something like void printMoves(int K).
 *
 * hint 473: the tricky bit is handling an infinite grid. what are your options?
 *
 * hint 480: option 1: do you actually need an infinite grid? read the problem again. do you know the max size of the
 * grid?
 *
 * hint 532: option 2: think about how an ArrayList works. can you use an ArrayList for this?
 *
 * hint 539: option 2: it's not impossible to use an ArrayList, but it would be tedious. perhaps it would be easier to
 * build your own, but specialized for matrices.
 *
 * hint 558: option 2: one approach is to just double the size of the array when the ant wanders to an edge. how will
 * you handle the ant wandering into negative coordinates, though? arrays can't have negative indices.
 *
 * hint 569: option 2: observe that nothing in the problem stipulates that the label for the coordinates must remain the
 * same. can you move the ant and all cells into positive coordinates? in other words, what would happen if, whenever
 * you needed to grow tht array in a negative direction, you relabeled all the indices such that they were still
 * positive?
 *
 * hint 598: option 3: another thin to think about is whether you even need a grid to implement this. what information
 * do you actually need int the problem?
 *
 * hint 615: option 3: all you actually need is some way of looking uo if a cell is white or black (and of course the
 * position of the ant). can you just keep a list of all the white cells?
 *
 * hint 626: option 3: you could consider keeping a hash set of all the white cells. how will you be able to print the
 * whole grid, though?
 */
public class Question {

  public static void main(String[] args) {
    Board board = new Board();
    Grid grid = new Grid();
    System.out.println(board.toString());
    for (int i = 0; i < 100; i++) {
      System.out.println("\n\n---- MOVE " + i + " ----");
      board.move();
      String bs = board.toString();
      System.out.println(bs);

      grid.move();
      String gs = grid.toString();
      System.out.println(gs);

      if (!bs.equals(gs)) {
        System.out.println("ERROR");
        break;
      }

    }
  }

}
