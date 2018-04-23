package Q16_04_Tic_Tac_Win;

/**
 * Tic Tac Win: design an algorithm to figure out if someone has won a game of tic-tac-toe
 *
 * Note: The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the
 * game.
 *
 * hint 709: if you were calling hasWon() multiple times, how might your solution change?
 *
 * hint 731: if you were designing this for an NxN board, how might your solution changes?
 *
 * Solution C is only for 3x3 board. it is not easy to scale to NxN board
 *
 * Solution D is for NxN board
 */
public class QuestionA {

  public static int convertBoardToInt(Piece[][] board) {
    int sum = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        int value = board[i][j].ordinal();
        sum = sum * 3 + value;
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    Piece[][] board = {
        {Piece.Empty, Piece.Empty, Piece.Empty},
        {Piece.Empty, Piece.Empty, Piece.Empty},
        {Piece.Blue, Piece.Blue, Piece.Blue}};

    int v = convertBoardToInt(board);
    System.out.println(v);
  }

}
