package Q8_12_Eight_Queens;

import java.util.ArrayList;

/**
 * Eight queen: write an algorithm to print all ways of arranging eight queens on an 8x8 chess board so that none of
 * them share the same row, column, or diagonal. in this case, "diagonal" means all diagonals, not just the two that
 * bisect the board.
 *
 * hint 308: we know that each row must have a queen. can you try all possibilities?
 *
 * hint  350: each row must have queen. start with the last row. there are eight different columns on which you can put
 * a queen. can you try each of these?
 *
 * hint 371: break this down into smaller subproblems. the queen at row 8 must be at column 1,2,3,4,5,6,7 or 8. can you
 * print all ways of placing eight queens where a queen is at row 8 and column 3? you then need to check all the ways of
 * placing a queen on row 7.
 *
 * Solution: picture the queen is placed at last row 8. on which cell in row 8 is this queen? there are eight
 * possibilities, one for each column.
 */
public class Question {

  public static int GRID_SIZE = 8;

  /* Check if (row1, column1) is a valid spot for a queen by checking if there
   * is a queen in the same column or diagonal. We don't need to check it for queens
   * in the same row because the calling placeQueen only attempts to place one queen at
   * a time. We know this row is empty.
   */
  public static boolean checkValid(Integer[] columns, int row1, int column1) {

    for (int row2 = 0; row2 < row1; row2++) {
      int column2 = columns[row2];
      /* Check if (row2, column2) invalidates (row1, column1) as a queen spot. */

			/* Check if rows have a queen in the same column */
      if (column1 == column2) {
        return false;
      }

			/* Check diagonals: if the distance between the columns equals the distance
       * between the rows, then they’re in the same diagonal. */
      int columnDistance = Math.abs(column2 - column1);
      int rowDistance = row1 - row2; // row1 > row2, so no need to use absolute value
      if (columnDistance == rowDistance) {
        return false;
      }
    }
    return true;
  }

  public static void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> results) {
    if (row == GRID_SIZE) { // Found valid placement
      results.add(columns.clone());
    } else {
      for (int col = 0; col < GRID_SIZE; col++) {
        if (checkValid(columns, row, col)) {
          columns[row] = col;  // Place queen
          placeQueens(row + 1, columns, results);
        }
      }
    }
  }

  public static void clear(Integer[] columns) {
    for (int i = 0; i < GRID_SIZE; i++) {
      columns[i] = -1;
    }
  }

  public static void printBoard(Integer[] columns) {
    drawLine();
    for (int i = 0; i < GRID_SIZE; i++) {
      System.out.print("|");
      for (int j = 0; j < GRID_SIZE; j++) {
        if (columns[i] == j) {
          System.out.print("Q|");
        } else {
          System.out.print(" |");
        }
      }
      System.out.print("\n");
      drawLine();
    }
    System.out.println("");
  }

  private static void drawLine() {
    StringBuilder line = new StringBuilder();
    for (int i = 0; i < GRID_SIZE * 2 + 1; i++) {
      line.append('-');
    }
    System.out.println(line.toString());
  }


  public static void printBoards(ArrayList<Integer[]> boards) {
    for (int i = 0; i < boards.size(); i++) {
      Integer[] board = boards.get(i);
      printBoard(board);
    }
  }

  public static void main(String[] args) {
    ArrayList<Integer[]> results = new ArrayList<Integer[]>();
    Integer[] columns = new Integer[GRID_SIZE];
    clear(columns);
    placeQueens(0, columns, results);
    printBoards(results);
    System.out.println(results.size());
  }
}
