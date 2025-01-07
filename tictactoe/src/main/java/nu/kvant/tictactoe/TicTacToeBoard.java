package nu.kvant.tictactoe;

public class TicTacToeBoard {
    private int[][] board;

    public TicTacToeBoard(int[][] board){
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
