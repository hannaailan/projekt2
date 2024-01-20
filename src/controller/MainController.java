package controller;

import model.*;

import java.lang.UnsupportedOperationException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Main controller.
 *
 * @author ich nicht
 */
public class MainController {



    /**
 	 * Das aktuelle main-Objekt
 	 */
    private Main main;

    /**
 	 * Der WonderController
 	 */
    private WonderController wonderController;

    /**
 	 * Der HighscoreController
 	 */
    private HighscoreController highscoreController;

    /**
 	 * Der SetupController
 	 */
    private SetupController setupController;

    /**
 	 * Der IOController
 	 */
    private IOController iOController;

    /**
 	 * Der AIController
 	 */
    private AIController aIController;

    /**
 	 * Der BoardController
 	 */
    private BoardController boardController;

	/**
	 * Der Konstruktur erzeugt alle anderen Controller und ein neues Main-Objekt
	 */
	public MainController() {
    	main = new Main();
    	wonderController = new WonderController(this);
    	highscoreController = new HighscoreController(this);
    	setupController = new SetupController(this);
    	iOController = new IOController(this);
    	boardController = new BoardController(this);
    	aIController = new AIController(this );

    }

	/**
	 * Gets wonder controller.
	 *
	 * @return the wonder controller
	 */
	public WonderController getWonderController() {
		return wonderController;
	}

	/**
	 * Gets highscore controller.
	 *
	 * @return the highscore controller
	 */
	public HighscoreController getHighscoreController() {
		return highscoreController;
	}

	/**
	 * Gets setup controller.
	 *
	 * @return the setup controller
	 */
	public SetupController getSetupController() {
		return setupController;
	}

	/**
	 * Gets o controller.
	 *
	 * @return the o controller
	 */
	public IOController getiOController() {
		return iOController;
	}

	/**
	 * Gets i controller.
	 *
	 * @return the i controller
	 */
	public AIController getaIController() {
		return aIController;
	}

	/**
	 * Gets board controller.
	 *
	 * @return the board controller
	 */
	public BoardController getBoardController() {
		return boardController;
	}

	/**
	 * Set main.
	 *
	 * @param main the main
	 */
	public void setMain(Main main){
        assert main!=null;
        this.main=main;
    }

	/**
	 * Get main main.
	 *
	 * @return the main
	 */
	public Main getMain(){
        return main;
    }


	/**
	 * Gibt die beste auszuwählende Karte aus
	 * @param boardState boardState des Spiels
	 * @param player Spieler
	 * @return
	 */
    public String showHint(BoardState boardState, Wonder player) {
        return "Beste Karte " + aIController.selectCard(player,boardState).getName().name();
    }

	/**
	 * @param boardState noch nicht benötigt
	 * @param player noch nicht benötigt
	 * @param card noch nicht benötigt
	 * @return GUTE!!1! Tipps
	 */
	public String showHint(BoardState boardState, Wonder player, Card card) {
		int random = ThreadLocalRandom.current().nextInt(0, 5);
		switch (random){
			//TODO weitere SINNVOLLE Tipps hinzufügen
			case 0: return "Wenn eine Karte verkauft wird bekommt man 3 Muenzen";
			case 1: return "Mit dem Zeus Effekt kannst du einmal pro Zeitalter eine Karte kostenlos bauen";
			case 2: return "3 gleiche Wissenschaftssymbole geben dir 9 Siegpunkte";
			case 3: return "Die Gilde der Wissenschaft gibt dir am Ende des Spiels das beste Symbol";
			case 4: return "Braune Karten geben Resourcen";
			default: return "404 Hint not found";
		}
	}

	/**
	 * gibt zurück ab es aktuelle ein Game gibt
	 * @return String
	 */
	public boolean hasGame() {
		return main.getGame()!=null;
	}

}
