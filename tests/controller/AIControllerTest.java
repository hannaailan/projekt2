package controller;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * The AiControllerTest Class
 */
public class AIControllerTest {
    /**
     * MainController
     */
    private MainController mainController;

    /**
     * AIController
     */
    private AIController aiController;

    /**
     * BoardController
     */
    private BoardController boardController;

    /**
     * WonderController
     */
    private WonderController wonderController;

    /**
     * Wonder
     */
    private Wonder testWonder;

    /**
     * Wonder
     */
    private Wonder testWonderLeft;

    /**
     * Wonder
     */
    private Wonder testWonderRight;

    /**
     * BoardState
     */
    private BoardState testBoardState;

    /**
     * LinkedListWonder[]
     */
    private LinkedList<Wonder> testPlayers;

    /**
     * LinkedListCard[]
     */
    private LinkedList<Card> testPlayerHand;

    /**
     * LinkedListCard[]
     */
    private LinkedList<Card>[] testCardStacks;

    /**
     * Erstellt eine Testumgebung
     * @throws Exception Fehler beim Setup
     */
    @Before
    public void setUp() throws Exception {
        mainController = new MainController();
        aiController = mainController.getaIController();
        boardController = mainController.getBoardController();
        wonderController = mainController.getWonderController();
        testWonder = WonderInfo.RHODES.buildBoard();
        testWonderLeft = new Wonder(WonderInfo.ALEXANDRIA, BuildResources.GLASS);
        testWonderRight = new Wonder(WonderInfo.EPHESUS, BuildResources.PAPYRUS);
        testPlayers = new LinkedList<>();
        testPlayerHand = new LinkedList<>();
        testCardStacks = new LinkedList[3];
    }

    /**
     * testet playCard
     */
    //TODO Asserts anpassen
    @Test
    public void playCard() {
        testPlayers.add(testWonderLeft);
        testBoardState = new BoardState(testPlayers,null);
        testBoardState.increaseAge();
        testWonder.setBoardState(testBoardState);
        testWonder.setNeighbours(testPlayers);
//        for(int i = 0; i < 20; i++){
//            System.out.println("discard or build: " + aiController.playCard(testWonder,testBoardState,CardInfo.ALTAR.buildCard()));
//            System.out.println("discard: " + aiController.playCard(testWonder,testBoardState,CardInfo.PANTHEON.buildCard()));
//        }
        wonderController.createBuilding(CardInfo.SAWMILL.buildCard(),testWonder);
        wonderController.createBuilding(CardInfo.BRICKYARD.buildCard(), testWonder);
        wonderController.createBuilding(CardInfo.BRICKYARD.buildCard(), testWonder);
        wonderController.createBuilding(CardInfo.BRICKYARD.buildCard(), testWonder);
//        for(int i = 0; i < 20; i++){
//            //für sauberen Test Stages zurücksetzen
//            System.out.println("discard or build or expand: " + aiController.playCard(testWonder,testBoardState,CardInfo.ALTAR.buildCard()));
//            System.out.println("discard or expand: " + aiController.playCard(testWonder,testBoardState,CardInfo.PANTHEON.buildCard()));
//        }
    }

    /**
     * testet selectCard
     */
    //TODO Asserts anpassen
    @Test
    public void selectCard() {
        Card testCard1 = CardInfo.ACADEMY.buildCard();
        testPlayerHand.add(testCard1);
        Card testCard2 = CardInfo.ALTAR.buildCard();
        testPlayerHand.add(testCard2);
        Card testCard3 = CardInfo.APOTHECARY.buildCard();
        testPlayerHand.add(testCard3);
        Card testCard4 = CardInfo.AQUEDUCT.buildCard();
        testPlayerHand.add(testCard4);
        Card testCard5 = CardInfo.ARCHERY_RANGE.buildCard();
        testPlayerHand.add(testCard5);
        Card testCard6 = CardInfo.ARENA.buildCard();
        testPlayerHand.add(testCard6);
        Card testCard7 = CardInfo.ARSENAL.buildCard();
        testPlayerHand.add(testCard7);
        testWonder.setPlayerHand(testPlayerHand);
        HashSet<Card> testSet = new HashSet<>();

        for(int i = 0; i < 200; i++) {
            Card temp = aiController.selectCard(testWonder,testBoardState);
            assertNotNull("Fehler selectCard",temp);
            testSet.add(temp);
        }
        //Könnte in seltenen Fällen fehlschlagen, wenn eine Karte bei 200 durchläufen per Zufall nicht ein Mal gewählt wurde
        assertEquals("Fehler selectCard",7,testSet.size());
    }
}