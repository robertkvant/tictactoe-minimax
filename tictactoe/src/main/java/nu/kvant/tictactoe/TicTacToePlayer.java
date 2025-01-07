package nu.kvant.tictactoe;
import java.util.HashMap;
import java.util.Map;

public class TicTacToePlayer {

    private final static int EMPTY = 0;
    private final static int COMPUTER = 1;
    private final static int PLAYER = 2;
    private final BoardVerifier verifier;

    public TicTacToePlayer(BoardVerifier verifier){
        this.verifier = verifier;
    }

    public Map<String,Integer> bestMove(int[][] board){
        int bestMove = Integer.MIN_VALUE;
        Map<String,Integer> coordinates = new HashMap<>();
        coordinates.put("x",-1);
        coordinates.put("y",-1);

        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if (board[row][col] == EMPTY){
                    board[row][col] = COMPUTER;
                    int result = miniMax(board,false);
                    board[row][col] = EMPTY;
                    if (bestMove < result){
                        bestMove = result;
                        coordinates.put("x",row);
                        coordinates.put("y",col);
                    }
                }
            }
        }
        return coordinates;
    }

    private int miniMax(int[][] board, boolean maximizing){
        int winner = verifier.checkWinner(board);
        if (winner != EMPTY){
            return winner == COMPUTER ? 1 : -1;
         }

        if (verifier.isComplete(board)){
            return 0;
        }

        int value = maximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if (board[row][col] == EMPTY){
                    board[row][col] = maximizing ? COMPUTER : PLAYER;
                    int childValue = miniMax(board,!maximizing);
                    board[row][col] = EMPTY;
                    value = maximizing ? Integer.max(value,childValue) :
                            Integer.min(value,childValue);
                }
            }
        }
        return value;
    }
}
