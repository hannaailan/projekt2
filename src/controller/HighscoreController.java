package controller;

import gui.view.BoardViewController;
import model.Highscore;
//import model.Main;
import model.Wonder;
import gui.aui.BoardAUI;
import gui.aui.HighscoreAUI;
import application.Main;
import java.lang.UnsupportedOperationException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author Tobias
 */
public class HighscoreController {

    /**
 	 * der MainController
 	 */
    private MainController mainController;


	/**
	 * Erzeugt einen HighscoreController
	 * @param mainController übergebener Maincontroller
	 */
	public HighscoreController(MainController mainController) {
    	this.mainController=mainController;
    }

	/**
	 * Ausgabemethode
	 * @param line Auszugebende Zeile
	 */
	private void out(String line){
		if(!BoardViewController.IGNOREKITURN)System.out.println("Highscore Controller: "+line);
		if(Main.pWriter!=null)Main.pWriter.println("Highscore Controller: "+line);
	}

    /**
 	 *
 	 * adds Array to Hightscores
 	 * @param players
 	 *	 	 	Diese Exception wird geworfen, fallsdie Methode noch nicht implementiert ist. 
 	 */
    public void addToHighscores(LinkedList<Wonder> players){
    	for(Wonder wonder : players){
    		if(wonder.isDisabledFromHighscore())
    			continue;
    		out("Füge "+wonder.getPlayerName()+" den Highscores Hinzu");
    		int points = mainController.getBoardController().calculatePoints(wonder);
    		addHighscore(wonder.getPlayerName(),points,calculatePosition(points));
		}
    	int index = 0;
    	for(Highscore highscore : mainController.getMain().getHighscores())
    		highscore.setPosition(index++);
    	//highscoreAUI.refreshHighscore();
    }

    /**
 	 * @return Highscore[]
 	 */
    public LinkedList<Highscore> getHighscores() {
    	out("Stelle Highscores bereit");
    	return mainController.getMain().getHighscores();

    }

    /**
 	 * fügt der Highscore-Liste einen Highscore mit entsprechenden werten zu
 	 * @param name name
 	 * @param points punkte
 	 * @param position position
 	 *
 	 */
    private void addHighscore(String name, int points, int position) {
		mainController.getMain().getHighscores().add(position,new Highscore(name,points,position));
    }

    /**
 	 *
 	 * Bedingung: Liste ist aufsteigend Sortiert
 	 * @param points Wunder
 	 * @return int
 	 *
 	 */
    private int calculatePosition(int points) {
    	int result=0;
    	for(Highscore highscore : mainController.getMain().getHighscores())
    		if(highscore.getPoints()<points)
    			return result;
    		else
    			result++;
		return result;


	}
}
