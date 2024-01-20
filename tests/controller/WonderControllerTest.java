package controller;

import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Test des WunderControllers
 */
public class WonderControllerTest {
    /**
     * testWonder Hilfsattribut
     */
    Wonder testWonder;
    /**
     * testWonderLeft Hilfsattribut
     */
    Wonder testWonderLeft;
    /**
     * testWonderRight Hilfsattribut
     */
    Wonder testWonderRight;
    /**
     * testSet Hilfsattribut
     */
    HashSet<EnumMap<BuildResources,Integer>> testSet;
    /**
     * emptyResource Hilfsattribut
     */
    EnumMap<BuildResources, Integer> emptyResource;
    /**
     * costsResources Hilfsattribut
     */
    EnumMap<BuildResources,Integer> costsResources;
    /**
     * wonderController Hilfsattribut
     */
    WonderController wonderController;
    /**
     * mainController Hilfsattribut
     */
    MainController mainController;

    /**
     * Erstellen einer Testumgebung vor jedem Test
     *
     * @throws Exception Fehler beim SetUp
     */
    @Before
    public void setUp() throws Exception {
        mainController = new MainController();
        wonderController = mainController.getWonderController();
        testWonder = WonderInfo.RHODES.buildBoard();
        testWonderLeft = new Wonder(WonderInfo.ALEXANDRIA, BuildResources.GLASS);
        testWonderRight = new Wonder(WonderInfo.EPHESUS, BuildResources.PAPYRUS);
        testSet = new HashSet<>();
        costsResources = new EnumMap<>(BuildResources.class);
        emptyResource = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()) {
            emptyResource.put(buildResource, 0);
            costsResources.put(buildResource, 2);
        }
    }

    /**
     * testet die Methode discardCard
     */
    @Test
    public void discardCard() {
        LinkedList<Wonder> playerList = new LinkedList<>();
        playerList.add(testWonder);
        LinkedList<Card>[] cardList= new LinkedList[3];
        for(int i=0;i<3; i++){
            cardList[i]= new LinkedList<>();
        }
        Card testCard = new Card(null,null,null,null,null,null, null);
        BoardState testState = new BoardState(playerList, cardList);
        testWonder.setBoardState(testState);
        wonderController.discardCard(testCard, testWonder);
        assertEquals("Fehler discardCard",1,testState.getDiscardStack().size());
        assertEquals("Fehler discardCard",3,testWonder.getCoins());
    }

    /**
     * testet die Methode increaseStage
     */
    @Test
    public void increaseStage() {
        Pair<String, Integer>[] arr = new Pair[2];
        arr[0] = new Pair<>("testname1", 0);
        arr[1] = new Pair<>("testname2", 0);
        mainController.getSetupController().startGame(arr, false);
        mainController.getMain().getGame().getCurrentBoardState().setAge(-1);//TODO Test läuft so durch ist aber eig. nicht Sinn der Sache
        Card testCard = new Card(null,null,null,null,null,null, null);
        wonderController.increaseStage(testCard,testWonder);
        assertEquals("Fehler increaseStage",1,testWonder.getCurrentStage());
        assertEquals("Fehler increaseStage",testCard, testWonder.getStages()[0].getBuildMarker());
    }

    /**
     * testet die Methode createBuilding
     */
    @Test
    public void createBuilding() {

        Card testCard = CardInfo.LUMBER_YARD.buildCard();
        wonderController.createBuilding(testCard, testWonder);
        assertEquals("Fehler createBuilding",1, testWonder.getBuiltCards().size());
    }

    /**
     * testet die Methode trade
     */
    @Test
    public void trade() {
        LinkedList<Wonder> testList = new LinkedList<>();
        testList.add(testWonderLeft);
        testWonder.setNeighbours(testList);
        testWonder.addCoins(6);
        wonderController.trade(testWonder, new Pair<>(2,0));
        assertEquals("Fehler trade", 4, testWonder.getCoins());
        assertEquals("Fehler trade", 2, testWonderLeft.getCoins());
        testList.add(testWonderRight);
        testWonder.setNeighbours(testList);
        wonderController.trade(testWonder, new Pair<>(2,2));
        assertEquals("Fehler trade", 0, testWonder.getCoins());
        assertEquals("Fehler trade", 4, testWonderLeft.getCoins());
        assertEquals("Fehler trade", 2, testWonderRight.getCoins());

    }

    /**
     * testet die Methode isAffordable
     */
    @Test
    public void isAffordable() {
        EnumMap<BuildResources, Integer> testResource = emptyResource.clone();
        LinkedList<Wonder> testList = new LinkedList<>();
        testList.add(testWonderLeft);
        testList.add(testWonderRight);
        testWonder.setNeighbours(testList);
        testResource.put(BuildResources.COIN, 1);
        assertFalse("Fehler isAffordable", wonderController.isAffordable(testResource, testWonder));
        testWonder.addCoins(1);
        assertTrue("Fehler isAffordable", wonderController.isAffordable(testResource, testWonder));
        testResource.put(BuildResources.COIN, 0);
        testResource.put(BuildResources.ORE, 1);
        assertTrue("Fehler isAffordable", wonderController.isAffordable(testResource, testWonder));
        testResource.put(BuildResources.GLASS, 1);
        assertFalse("Fehler isAffordable", wonderController.isAffordable(testResource, testWonder));
        testWonder.addCoins(10);
        assertTrue("Fehler isAffordable", wonderController.isAffordable(testResource, testWonder));
    }

    /**
     * testet die Methode missingResources
     */
    @Test
    public void missingResources() {
        EnumMap<BuildResources, Integer> testResource = emptyResource.clone();
        HashSet<EnumMap<BuildResources, Integer>> testSet = new HashSet<>();
        testSet.add(emptyResource.clone());
        testResource.put(BuildResources.ORE, 1);
        testSet.add(testResource.clone());
        assertEquals("Fehler missingResources",testSet,wonderController.missingResources(testWonder.getProduces(), testResource));
        testResource.put(BuildResources.ORE, 2);
        testSet.remove(emptyResource);
        testSet.add(testResource.clone());
        assertEquals("Fehler missingResources",testSet,wonderController.missingResources(testWonder.getProduces(), testResource));
    }

    /**
     * testet die Methode
     */
    @Test
    public void isBuiltAlready(){
        Card aqueduct = CardInfo.AQUEDUCT.buildCard();
        assertFalse("Fehler1 isBuiltAlready",wonderController.isBuiltAlready(aqueduct,testWonder));
        testWonder.addBuildCard(aqueduct);
        assertTrue("Fehler2 isBuiltAlready",wonderController.isBuiltAlready(aqueduct,testWonder));
    }

    /**
     * testet die Methode isFree
     */
    @Test
    public void isFree() {
        LinkedList<Wonder> playerList = new LinkedList<>();
        playerList.add(testWonder);
        LinkedList<Card>[] cardList= new LinkedList[3];
        for(int i=0;i<3; i++){
            cardList[i]= new LinkedList<>();
        }
        Card testCard = CardInfo.BATHS.buildCard();
        BoardState testState = new BoardState(playerList, cardList);
        testState.increaseAge();
        testWonder.setBoardState(testState);

        assertFalse("Fehler isFree", wonderController.isFree(testWonder, testCard));
        Card secondCard = CardInfo.AQUEDUCT.buildCard();
        wonderController.createBuilding(testCard,testWonder);
        assertTrue("Fehler isFree",wonderController.isFree(testWonder, secondCard));
        testWonder.enableZeus();
        assertTrue("Fehler isFree",wonderController.isFree(testWonder, testCard));

    }

    /**
     * testet die Methode calculateAllResourceOptions
     */
    @Test
    public void calculateAllResourceOptions() {
        EnumMap<BuildResources, Integer> testCost = emptyResource.clone();
        testCost.put(BuildResources.ORE, 1);
        LinkedList<Wonder> testNeighbours = new LinkedList<>();
        testNeighbours.add(testWonderLeft);
        testNeighbours.add(testWonderRight);
        testWonder.setNeighbours(testNeighbours);
        LinkedList<Pair<Integer, Integer>> testPairs = new LinkedList<>();
        Pair<Integer, Integer> pair1 = new Pair<>(0,0);
        testPairs.add(pair1);
        assertEquals("Fehler calculateAllResourceOptions 1",testPairs ,wonderController.calculateAllResourceOptions(testWonder, testCost));
        testWonder.setProduces(wonderController.integrateResource(testWonder.getProduces(), testCost));
        testWonderLeft.setSells(wonderController.integrateResource(testWonderLeft.getSells(), testCost));
        testWonderRight.setSells(wonderController.integrateResource(testWonderRight.getSells(), testCost));
        testCost.put(BuildResources.ORE, 2);
        assertEquals("Fehler calculateAllResourceOptions 2", testPairs,wonderController.calculateAllResourceOptions(testWonder, testCost));
        testWonder.addCoins(8);
        pair1 = new Pair<>(0,2);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,0);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,2);
        testPairs.add(pair1);
        assertEquals("Fehler calculateAllResourceOptions 3", testPairs,wonderController.calculateAllResourceOptions(testWonder, testCost));
        HashSet<EnumMap<BuildResources, Integer>> manyOres = new HashSet<>();
        testCost.put(BuildResources.ORE, 1);
        manyOres = wonderController.integrateResource(manyOres, testCost);
        manyOres = wonderController.integrateResource(manyOres, testCost);
        manyOres = wonderController.integrateResource(manyOres, testCost);
        manyOres = wonderController.integrateResource(manyOres, testCost);
        manyOres = wonderController.integrateResource(manyOres, testCost);
        testWonderLeft.setSells(new HashSet<>(manyOres));
        testWonderRight.setSells(new HashSet<>(manyOres));
        manyOres = wonderController.integrateResource(manyOres, emptyResource);
        testWonder.setProduces(new HashSet<>(manyOres));
        testPairs = new LinkedList<>();
        pair1 = new Pair<>(0,0);
        testPairs.add(pair1);
        pair1 = new Pair<>(0,2);
        testPairs.add(pair1);
        pair1 = new Pair<>(0,4);
        testPairs.add(pair1);
        pair1 = new Pair<>(0,6);
        testPairs.add(pair1);
        pair1 = new Pair<>(0,8);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,0);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,2);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,4);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,6);
        testPairs.add(pair1);
        pair1 = new Pair<>(4,0);
        testPairs.add(pair1);
        pair1 = new Pair<>(4,2);
        testPairs.add(pair1);
        pair1 = new Pair<>(4,4);
        testPairs.add(pair1);
        pair1 = new Pair<>(6,0);
        testPairs.add(pair1);
        pair1 = new Pair<>(6,2);
        testPairs.add(pair1);
        pair1 = new Pair<>(8,0);
        testPairs.add(pair1);
        testCost.put(BuildResources.ORE, 5);
        assertEquals("Fehler calculateAllResourceOptions 4", testPairs,wonderController.calculateAllResourceOptions(testWonder, testCost));
        testPairs = new LinkedList<>();
        pair1 = new Pair<>(0,0);
        testPairs.add(pair1);
        pair1 = new Pair<>(0,2);
        testPairs.add(pair1);
        pair1 = new Pair<>(0,4);
        testPairs.add(pair1);
        pair1 = new Pair<>(0,6);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,0);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,2);
        testPairs.add(pair1);
        pair1 = new Pair<>(2,4);
        testPairs.add(pair1);
        pair1 = new Pair<>(4,0);
        testPairs.add(pair1);
        pair1 = new Pair<>(4,2);
        testPairs.add(pair1);
        pair1 = new Pair<>(6,0);
        testPairs.add(pair1);
        testCost.put(BuildResources.ORE, 3);
        assertEquals("Fehler calculateAllResourceOptions 4", testPairs,wonderController.calculateAllResourceOptions(testWonder, testCost));
    }

    /**
     * testet die Methode integrateResource
     */
    @Test
    public void integrateResource() {
        HashSet<EnumMap<BuildResources,Integer>> controlSet = new HashSet<>();
        HashSet<EnumMap<BuildResources,Integer>> testSet = new HashSet<>();
        EnumMap<BuildResources, Integer> testResource = emptyResource.clone();
        testResource.put(BuildResources.ORE, 1);
        testResource.put(BuildResources.WOOD, 1);
        testSet = wonderController.integrateResource(testSet, emptyResource);
        controlSet.add(emptyResource.clone());
        assertEquals("Fehler integrateResource",controlSet,testSet);
        testSet = wonderController.integrateResource(testSet, testResource);
        testResource.put(BuildResources.ORE, 1);
        testResource.put(BuildResources.WOOD, 0);
        controlSet.add(testResource.clone());
        testResource.put(BuildResources.ORE, 0);
        testResource.put(BuildResources.WOOD, 1);
        controlSet.add(testResource.clone());
        assertEquals("Fehler integrateResource",controlSet,testSet);
        testResource.put(BuildResources.ORE, 1);
        testResource.put(BuildResources.WOOD, 1);
        testSet = wonderController.integrateResource(testSet, testResource);
        controlSet.add(testResource.clone());
        testResource.put(BuildResources.ORE, 0);
        testResource.put(BuildResources.WOOD, 2);
        controlSet.add(testResource.clone());
        testResource.put(BuildResources.ORE, 2);
        testResource.put(BuildResources.WOOD, 0);
        controlSet.add(testResource.clone());
        assertEquals("Fehler integrateResource",controlSet,testSet);
    }
    /**
     * testet die Methode getHand
     */
    @Test
    public void getHand() {
        assertEquals("Fehler integrateResource", testWonder.getPlayerHand(),wonderController.getHand(testWonder));

    }
}