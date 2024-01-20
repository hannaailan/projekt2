package controller;

import javafx.util.Pair;
import model.Game;
import model.Highscore;
import model.Main;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * The type Io controller test.
 *
 * @author Tobias
 */
public class IOControllerTest {
    /**
     * The Io controller.
     */
    IOController ioController;
    /**
     * The Main c.
     */
    MainController mainC;

    /**
     *
     */
    Pair<String,Integer>[] playerList;

    /**
     *
     */
    SetupController setupController;

    /**
     * Setup.
     */
    @Before
    public void setup(){
        mainC=new MainController();
        ioController=new IOController(mainC);
        setupController = new SetupController(mainC);

        playerList = new Pair[4];
        for(int index = 0 ;index<playerList.length ; index++){
            playerList[index] = new Pair<>("S"+index,index);
        }

        setupController.startGame(playerList,false);
    }

    /**
     * Konstruktor test.
     */
    @Test
    public void KonstruktorTest(){
        assertNotNull(new IOController(mainC));
    }


    /**
     * test the saving and loading  function of a main class
     */
    @Test
    public void saveAndLoadMainTest() {
        ioController.saveMain();
        mainC.setMain(new Main());
        assertFalse(mainC.hasGame());
        ioController.loadMain();

        assertTrue(mainC.getMain().getGame().getCurrentBoardState().getPlayerList().size()==4);
    }

    /**
     * tests loading and saving of a game.
     */
    @Test
    public void loadAndSaveGameTest() {
        ioController.loadGame(new File("doesNotExists.dne"));
        assertTrue(mainC.hasGame());
        ioController.saveGame();
        mainC.getMain().setGame(null);
        assertNull(mainC.getMain().getGame());
        assertTrue(IOController.getGameFiles().size()>0);
        ioController.loadGame(IOController.getGameFiles().getLast());
        assertNotNull(mainC.getMain().getGame());
    }


    /**
     * Import csv.
     */
    @Test
    public void importCSV() {
    }

    /**
     * Main save found.
     */
    @Test
    public void mainSaveFound() {
    }

    /**
     * Gets game files.
     */
    @Test
    public void getGameFilesTest() {
    }


}
