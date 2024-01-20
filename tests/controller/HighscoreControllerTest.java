package controller;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * The type Highscore controller test.
 *
 * @author Tobias
 */
public class HighscoreControllerTest {

    private HighscoreController highscoreController;
    private MainController mainController;
    private Main main;
    private LinkedList<Wonder> wonders;

    /**
     * Sets up
     */
    @Before
    public void setUp()  {
        mainController = new MainController();
        highscoreController = new HighscoreController(mainController);
        main = new Main();
        mainController.setMain(main);
        wonders = new LinkedList<Wonder>();
        wonders.add(new Wonder(WonderInfo.ALEXANDRIA, BuildResources.BRICK));
        wonders.add(new Wonder(WonderInfo.ALEXANDRIA, BuildResources.BRICK));
        wonders.add(new Wonder(WonderInfo.ALEXANDRIA, BuildResources.BRICK));
        wonders.add(new Wonder(WonderInfo.ALEXANDRIA, BuildResources.BRICK));

        int index=0;
        for(Wonder wonder : wonders){

            wonder.setPlayerName(""+index);
            wonder.getOtherResources().put(OtherResources.VP,index++);
        }
    }

    /**
     * Add to highscores.
     */
    @Test
    public void addToHighscores() {
        highscoreController.addToHighscores(wonders);
        assertTrue(main.getHighscores().size()==wonders.size());
        int index=wonders.size()-1;
        for(Highscore highscore : main.getHighscores()){
            assertTrue(highscore.getPoints()==index);
            assertTrue(highscore.getPosition()==wonders.size()-1-index);
            index--;
        }
    }

    /**
     * Gets highscores.
     */
    @Test
    public void getHighscores() {
        highscoreController.addToHighscores(wonders);
        assertNotNull(highscoreController.getHighscores());
    }
}