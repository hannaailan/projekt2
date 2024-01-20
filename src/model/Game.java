package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Stack;

/**
 * The type Game.
 */
public class Game implements Serializable {

    /**
 	 * Das aktueller redo-Stack
 	 */
    private Stack<BoardState> redo;
    /**
 	 * Das gameHistory-Stack
 	 */
    private Stack<BoardState> gameHistory;


    /**
     * Instantiates a new Game.
     */
    public Game(/*BoardState boardState*/) {
    	gameHistory = new Stack<BoardState>();
    	redo = new Stack<BoardState>();

    	//addNewBoardState(boardState);
    }

    /**
     * Add new board state.
     *
     * @param boardState the board state
     */
    public void addNewBoardState(BoardState boardState){
        assert boardState!=null;
        gameHistory.push(boardState);
        if(!redo.empty())
            redo.clear();
    }

    /**
     * Gets game history.
     *
     * @return the game history
     */
    public Stack<BoardState> getGameHistory() {
        return gameHistory;
    }

    /**
     * Gets current board state.
     *
     * @return the current board state
     */
    public BoardState getCurrentBoardState() {
        return gameHistory.peek();
    }

    /**
     * Gets redo.
     *
     * @return the redo
     */
    public Stack<BoardState> getRedo() {
		return redo;
	}
}
