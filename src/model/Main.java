package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;


/**
 * The type Main.
 */
public class Main implements Serializable {

    /**
 	 * Das game-Objekt
     * ! ist anfänglich null !
 	 */
    private Game game;

    /**
 	 * Das highscore- Objekt
 	 */
    private LinkedList<Highscore> highscore;

    /**
     * Der Konstruktur
     */
    public Main() {
    	highscore = new LinkedList<Highscore>();
    }

    /**
     * Set game.
     *
     * @param game the game
     */
    public void setGame(Game game){
        this.game = game;
    }

    /**
     * Get game game.
     *
     * @return the game
     */
    public Game getGame(){
        return game;
    }

    /**
     * Gets highscores.
     *
     * @return the highscores
     */
    public LinkedList<Highscore> getHighscores() {
		return highscore;
	}

}
