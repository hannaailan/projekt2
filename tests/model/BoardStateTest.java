package model;


import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Die Testklasse zu model.BoardState
 *
 * @author Tobias
 */
public class BoardStateTest {

    /**
     * The Board state.
     */
    BoardState boardState;
    /**
     * The Player list.
     */
    LinkedList<Wonder> playerList;
    /**
     * The Cardlist.
     */
    LinkedList<Card>[] cardlist;

    /**
     * Erstellt vor jeder Testmethode eine Testumgebung
     */
    @Before
    public void setUp() {
        playerList=new LinkedList<Wonder>();
        Wonder wonder = new Wonder(WonderInfo.ALEXANDRIA,BuildResources.BRICK);
        wonder.addStage(new Stage(null,null,1));
        wonder.addStage(new Stage(null,null,2));
        wonder.addStage(new Stage(null,null,3));
        playerList.add(wonder);
        cardlist=new LinkedList[3];
        for(int index=0;index<cardlist.length;index++)
            cardlist[index]= new LinkedList<>();

        boardState = new BoardState(playerList,cardlist);
        boardState.increaseAge();
    }

    /**
     * Testet getAge
     */
    @Test
    public void testGetAge() {

        assertEquals(1,boardState.getAge());
    }

    /**
     * Testet increaseAge
     */
    @Test
    public void testIncreaseAge() {
        assertEquals(1,boardState.getAge());
        boardState.increaseAge();
        assertEquals(2,boardState.getAge());
    }

    /**
     * Testet getDiscardStack
     */
    @Test
    public void testGetDiscardStack() {
        assertNotNull(boardState.getDiscardStack());
    }

    /**
     * Testet getCardList
     */
    @Test
    public void testGetCardList() {
        assertNotNull(boardState.getCardList());
    }

    /**
     * Testet getPlayerList
     */
    @Test
    public void testGetPlayerList() {
        assertNotNull(boardState.getPlayerList());
    }

    /**
     * Testet getFirstPlayer
     */
    @Test
    public void testGetFistPlayer() {
        assertNotNull(boardState.getFistPlayer());
    }

    /**
     * Testet getCurrentPlayer
     */
    @Test
    public void testGetCurrentPlayer() {
        assertNotNull(boardState.getFistPlayer());
    }

    /**
     * Testet setFirstPlayer
     */
    @Test
    public void testSetFirstPlayer() {
        boardState.setFirstPlayer(new Wonder(WonderInfo.GIZA,BuildResources.STONE));
        assertTrue(boardState.getFistPlayer().getWonderName().name().equals("GIZA"));

    }

    /**
     * Testet setCurrentPlayer
     */
    @Test
    public void testSetCurrentPlayer() {
        boardState.setCurrentPlayer(new Wonder(WonderInfo.GIZA,BuildResources.STONE));
        assertTrue(boardState.getCurrentPlayer().getWonderName().name().equals("GIZA"));
    }

    /**
     * Testet ActivateHAlikarnassos
     */
    @Test
    public void testActivateHalikarnassos() {
        assertFalse(boardState.isHalikarnassosThisTurn(false));
        boardState.activateHalikarnassos();
        assertTrue(boardState.isHalikarnassosThisTurn(false));
    }

    /**
     * Testet HalikarnassosThisTurn
     */
    @Test
    public void testIsHalikarnassosThisTurn() {
        assertFalse(boardState.isHalikarnassosThisTurn(false));
    }

    /**
     * Testet das Klonen
     */
    @Test
    public void testClone(){
        BoardState newBoardState = boardState.clone();
        assertEquals(boardState.isHalikarnassosThisTurn(false),newBoardState.isHalikarnassosThisTurn(false));
        assertEquals(boardState.getCurrentPlayer().getWonderName(),newBoardState.getCurrentPlayer().getWonderName());
        assertEquals(boardState.getAge(),newBoardState.getAge());
        assertEquals(boardState.getCardList().size(),newBoardState.getCardList().size());
        assertEquals(boardState.getFistPlayer().getWonderName(),newBoardState.getFistPlayer().getWonderName());
        assertEquals(boardState.getPlayerList().size(),newBoardState.getPlayerList().size());

    }
}