import { useState } from 'react';
import { Cell } from './Cell.js'
import './styles.css'

export function App() {
    const COMPUTER = 1
    const PLAYER = 2

    const [board, setBoard] = useState([
      [0,0,0],
      [0,0,0],
      [0,0,0]
    ])
    
    const [winner, setWinner] = useState(0)
    const [isComplete, setIsComplete] = useState(false)
    const [isPlayersTurn, setIsPlayersTurn] = useState(true)

    async function fetchData(url,callback) {
      try {
        const response = await fetch(url, {
          headers: {
            "Content-Type": "application/json",
          },
          method: "POST",
          body: JSON.stringify({ board: board })
        });
        if (!response.ok) {
          throw new Error(`Response status: ${response.status}`);
        }
    
        const json = await response.json();
        callback(json)
      } catch (error) {
        console.error(error.message);
      }
    }

    function checkWinner(){
      fetchData("/tictactoe/winner",function(json){
        setWinner(json.winner)
      })
    }

    function boardIsComplete(){
      fetchData("/tictactoe/iscomplete",function(json){
        setIsComplete(json.isComplete)
      })
    }

    function updateBoard(x,y){
      if (board[x][y] === 0 && x >= 0 && y >= 0){
          const b = [...board];
          b[x][y] = isPlayersTurn ? PLAYER : COMPUTER
          setBoard(b)
          checkWinner()
          boardIsComplete()
          setIsPlayersTurn(!isPlayersTurn)
      }
    }

    if (!isPlayersTurn){
      fetchData("/tictactoe/bestmove",function(json){
        if (json.x >= 0 && json.y >= 0){
          updateBoard(json.x,json.y)
        }
      })
    }
    
    return (
      <>
        <div className="TicTacToeBoard">
        {
          board.map((x,x_i) => 
            x.map((y,y_i) => 
               <Cell x_index={x_i} y_index={y_i} 
                value={y} key={x_i.toString() + y_i.toString()}
                callback={updateBoard}
               />
            ))
        }
      </div>
      <p>{ winner == 1 ? "Winner is COMPUTER" : 
            winner == 2 ? "Winner is PLAYER" : 
            isComplete ? "It's a draw" : ""}</p>
      </>
    );
}