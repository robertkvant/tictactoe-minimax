package nu.kvant.web;

import nu.kvant.tictactoe.BoardVerifier;
import nu.kvant.tictactoe.TicTacToeBoard;
import nu.kvant.tictactoe.TicTacToeBoardVerifier;
import nu.kvant.tictactoe.TicTacToePlayer;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tictactoe")
public class TicTacToeController {

    @PostMapping("/bestmove")
    public Map<String,Integer> nextMove(@RequestBody TicTacToeBoard board){
        BoardVerifier verifier = new TicTacToeBoardVerifier();
        TicTacToePlayer player = new TicTacToePlayer(verifier);
        return player.bestMove(board.getBoard());
    }

    @PostMapping("/winner")
    public Map<String,Integer> winner(@RequestBody TicTacToeBoard board){
        BoardVerifier verifier = new TicTacToeBoardVerifier();
        int winner = verifier.checkWinner(board.getBoard());
        Map<String,Integer> w = new HashMap<>();
        w.put("winner",winner);
        return w;
    }

    @PostMapping("/iscomplete")
    public Map<String,Boolean> iscomplete(@RequestBody TicTacToeBoard board){
        BoardVerifier verifier = new TicTacToeBoardVerifier();
        boolean complete = verifier.isComplete(board.getBoard());
        Map<String,Boolean> w = new HashMap<>();
        w.put("iscomplete",complete);
        return w;
    }

}
