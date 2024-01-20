package controller;

import javafx.util.Pair;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * The type Board controller test.
 *
 * @author Tobias
 */
public class BoardControllerTest {

    /**
     * The Board controller.
     */
    BoardController boardController;
    /**
     * The Setup controller.
     */
    SetupController setupController;
    /**
     * The Main controller.
     */
    MainController mainController;
    /**
     * The Player list.
     */
    Pair<String,Integer>[] playerList;
    /**
     * The Game.
     */
    Game game;
    /**
     * The States.
     */
    BoardState[] states;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        mainController = new MainController();
        boardController = new BoardController(mainController);
        setupController = new SetupController(mainController);

        playerList = new Pair[4];
        for(int index = 0 ;index<playerList.length ; index++){
            playerList[index] = new Pair<>("S"+index,index);
        }

        setupController.startGame(playerList,false);

        game = mainController.getMain().getGame();

        states=new BoardState[3];
        states[0]= game.getCurrentBoardState();

        game.addNewBoardState(game.getCurrentBoardState().clone());

        states[1] = game.getCurrentBoardState();
    }

    /**
     * Undo.
     */
    @Test
    public void undo() {
        assertEquals(states[1],game.getCurrentBoardState());
        boardController.undo();
        assertEquals(states[0],game.getCurrentBoardState());
        for(Wonder player : states[0].getPlayerList()){
            assertTrue(player.isDisabledFromHighscore());
        }
    }

    /**
     * Redo.
     */
    @Test
    public void redo() {
        boardController.undo();
        assertEquals(states[0],game.getCurrentBoardState());
        boardController.redo();
        assertEquals(states[1],game.getCurrentBoardState());
        for(Wonder player : states[1].getPlayerList()){
            assertFalse(player.isDisabledFromHighscore());
        }
    }

    /**
     * Cycle cards.
     */
    @Test
    public void cycleCards() {
        boardController.cycleCards();//linksrum
        game.getCurrentBoardState().increaseAge();
        boardController.cycleCards();//rechtsrum
    }

    /**
     * Start age.
     */
    @Test
    public void startAge() {
        boardController.endAge();
        boardController.startAge();
        for(Wonder player : game.getCurrentBoardState().getPlayerList()){
            assertNotNull(player.getPlayerHand());
        }

    }

    /**
     * End age.
     */
    @Test
    public void endAge() {
        game.getCurrentBoardState().getPlayerList().getFirst().addShields(3);

        boardController.endAge();
        for(Wonder player : game.getCurrentBoardState().getPlayerList()){
            assertNull(player.getPlayerHand());
        }
    }

    /**
     * Calculate points.
     */
    @Test
    public void calculatePoints() {
        boardController.calculatePoints();
    }

    /**
     * Next player.
     */
    @Test
    public void nextPlayer() {
        Wonder player = game.getCurrentBoardState().getCurrentPlayer();
        boardController.nextPlayer();
        assertNotEquals(player,game.getCurrentBoardState().getCurrentPlayer());
    }

    /**
     * Start turn.
     */
    @Test
    public void startTurn() {
        boardController.startTurn();
        assertNotEquals(states[1],game.getCurrentBoardState());
    }

    /**
     * End game.
     */
    @Test
    public void endGame() {
        boardController.endGame();
    }

    /**
     * Gets player list.
     */
    @Test
    public void getPlayerList() {
        assertEquals(game.getCurrentBoardState().getPlayerList(),boardController.getPlayerList());
    }

    /**
     * Gets current player.
     */
    @Test
    public void getCurrentPlayer() {
        assertEquals(game.getCurrentBoardState().getCurrentPlayer(),boardController.getCurrentPlayer());
    }
}