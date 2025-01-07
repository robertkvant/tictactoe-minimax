package nu.kvant.tictactoe;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeBoardVerifierTest {

    @Test
    void isComplete() {
        int[][] arr = {{1,2,1},{2,1,1},{2,1,2}};
        TicTacToeBoardVerifier verifier = new TicTacToeBoardVerifier();
        assertTrue(verifier.isComplete(arr));
        arr[0][0] = 0;
        assertFalse(verifier.isComplete(arr));
    }

    @Test
    void isValidContent() {
        int[][] arr = {{1,2,0},{0,1,1},{2,1,2}};
        TicTacToeBoardVerifier verifier = new TicTacToeBoardVerifier();
        assertTrue(verifier.isValidContent(arr));
        arr[0][0] = -1;
        assertFalse(verifier.isValidContent(arr));
    }

    @Test
    void hasValidDimensions() {
        int[][] arr1 = {{1,2,0},{0,1,1},{2,1,2}};
        TicTacToeBoardVerifier verifier = new TicTacToeBoardVerifier();
        assertTrue(verifier.hasValidDimensions(arr1));

        int[][] arr2 = {{1,2,0},{0,1,1,2},{2,1,2}};
        assertFalse(verifier.hasValidDimensions(arr2));
    }

    @Test
    void checkRowWinner() {
        int[][] arr1 = {{1,2,2},{2,2,2},{2,1,2}};
        TicTacToeBoardVerifier verifier = new TicTacToeBoardVerifier();
        assertEquals(2,verifier.checkRowWinner(arr1));

        int[][] arr2 = {{1,2,0},{0,1,1},{2,1,2}};
        assertEquals(0,verifier.checkRowWinner(arr2));

        int[][] arr3 = {{1,1,1},{2,0,2},{2,1,2}};
        assertEquals(1,verifier.checkRowWinner(arr3));

        int[][] arr4 = {{2,1,2},{1,2,1},{1,1,1}};
        assertEquals(1,verifier.checkRowWinner(arr4));
    }

    @Test
    void checkColumnWinner() {
        int[][] arr1 = {
                {1,2,2},
                {1,1,1},
                {1,1,2}};

        TicTacToeBoardVerifier verifier = new TicTacToeBoardVerifier();
        assertEquals(1,verifier.checkColumnWinner(arr1));

        int[][] arr2 = {
                {1,2,2},
                {0,1,2},
                {2,1,2}};

        assertEquals(2,verifier.checkColumnWinner(arr2));

        int[][] arr3 = {
                {1,1,2},
                {0,1,1},
                {2,1,2}};

        assertEquals(1,verifier.checkColumnWinner(arr3));

        int[][] arr4 = {
                {1,1,2},
                {0,0,1},
                {2,1,2}};

        assertEquals(0,verifier.checkColumnWinner(arr4));
    }


    @Test
    void checkDiagonalWinner() {
        int[][] arr1 = {
                {1,2,2},
                {1,0,2},
                {2,1,1}};

        TicTacToeBoardVerifier verifier = new TicTacToeBoardVerifier();
        assertEquals(0,verifier.checkDiagonalWinner(arr1));

        int[][] arr2 = {
                {1,2,2},
                {1,1,2},
                {2,1,1}};

        assertEquals(1,verifier.checkDiagonalWinner(arr2));

        int[][] arr3 = {
                {1,2,2},
                {1,2,2},
                {2,1,1}};

        assertEquals(2,verifier.checkDiagonalWinner(arr3));

    }

    @Test
    void checkWinner(){
        int[][] arr1 = {
                {1,2,2},
                {1,0,2},
                {2,1,1}};

        TicTacToeBoardVerifier verifier = new TicTacToeBoardVerifier();
        assertEquals(0,verifier.checkWinner(arr1));

        int[][] arr2 = {
                {1,2,2},
                {1,1,2},
                {2,1,1}};

        assertEquals(1,verifier.checkWinner(arr2));

        int[][] arr3 = {
                {1,2,2},
                {1,2,2},
                {2,1,1}};

        assertEquals(2,verifier.checkWinner(arr3));

        int[][] arr4 = {
                {2,2,0},
                {2,1,0},
                {2,1,0}};

        assertEquals(2,verifier.checkWinner(arr4));

        int[][] arr5 = {{1,2,2},{1,1,1},{2,1,2}};
        assertEquals(1,verifier.checkWinner(arr5));
    }

    @Test
    void bestMove(){
        int[][] b1 = {
                {1,1,2},
                {1,2,0},
                {0,2,2}
        };

        BoardVerifier verifier = new TicTacToeBoardVerifier();
        TicTacToePlayer player = new TicTacToePlayer(verifier);

        Map<String,Integer> c = player.bestMove(b1);
        assertEquals(2,c.get("x"));
        assertEquals(0,c.get("y"));


        int[][] b2 = {
                {2,0,2},
                {0,1,0},
                {0,0,0}
        };

        Map<String,Integer> c2 = player.bestMove(b2);
        assertEquals(0,c2.get("x"));
        assertEquals(1,c2.get("y"));


        int[][] b3 = {
                {1,0,2},
                {2,1,0},
                {1,2,2}
        };

        Map<String,Integer> c3 = player.bestMove(b3);
        assertEquals(1,c3.get("x"));
        assertEquals(2,c3.get("y"));

    }
}