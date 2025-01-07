package nu.kvant.tictactoe;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TicTacToeBoardVerifier implements BoardVerifier {

    @Override
    public boolean isComplete(int[][] board) {
        for (int[] r : board) {
            if (Arrays.stream(r).anyMatch(e -> e == 0)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValid(int[][] board){
        return isValidContent(board) && hasValidDimensions(board);
    }

    @Override
    public boolean isValidContent(int[][] board){
        for (int[] r: board){
            if (Arrays.stream(r).anyMatch(e ->
                    !(e == 0 || e == 1 || e == 2))){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasValidDimensions(int[][] board){
        return Arrays.stream(board).allMatch(r ->
                r.length == board.length);
    }

    @Override
    public int checkWinner(int[][] board){
        int rowWinner = checkRowWinner(board);
        int colWinner = checkColumnWinner(board);
        int diagonalWinner = checkDiagonalWinner(board);

        return rowWinner != 0 ? rowWinner :
                colWinner != 0 ? colWinner :
                        diagonalWinner;

    }

    @Override
    public int checkRowWinner(int[][] board){
        for (int[] r: board){
            if (r[0] != 0){
                if (Arrays.stream(r).allMatch(e ->
                        e == r[0])){
                    return r[0];
                }
            }
        }
        return 0;
    }

    @Override
    public int checkDiagonalWinner(int[][] board){

        if (board[0][0] != 0){
            if (IntStream.range(1,board.length).allMatch(i ->
                    board[i][i] == board[0][0])){
                return board[0][0];
            }
        }

        if (board[board.length - 1][0] != 0){
            if (IntStream.range(0,board.length).allMatch(i ->
                    board[board.length - 1 - i][i] == board[board.length - 1][0])){
                return board[board.length - 1][0];
            }
        }

        return 0;
    }

    @Override
    public int checkColumnWinner(int[][] board){
        for (int col = 0; col < board[0].length; col++){
            final int c = col;
            int value = board[0][col];
            if (value != 0) {
                if (IntStream.range(0, board.length).allMatch(i ->
                        board[i][c] == value)) {
                    return value;
                }
            }
        }
        return 0;
    }

}
