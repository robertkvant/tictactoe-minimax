package nu.kvant.tictactoe;

public interface BoardVerifier {
    boolean isComplete(int[][] board);

    boolean isValid(int[][] board);

    boolean isValidContent(int[][] board);

    boolean hasValidDimensions(int[][] board);

    int checkWinner(int[][] board);

    int checkRowWinner(int[][] board);

    int checkDiagonalWinner(int[][] board);

    int checkColumnWinner(int[][] board);
}
