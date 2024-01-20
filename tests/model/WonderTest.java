package model;

import org.junit.Before;
import org.junit.Test;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Die Testklasse zu Model.Wonder
 */
public class WonderTest {
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
     * Erstellt vor jeder Testmethode eine Testumgebung
     *
     * @throws Exception mögliche Exeption beim setUp
     */
    @Before
    public void setUp() throws Exception {
        testWonder = new Wonder(WonderInfo.RHODES, BuildResources.ORE);
        testWonderLeft = new Wonder(WonderInfo.ALEXANDRIA, BuildResources.GLASS);
        testWonderRight = new Wonder(WonderInfo.EPHESUS, BuildResources.PAPYRUS);
        testSet = new HashSet<>();
        costsResources = new EnumMap<>(BuildResources.class);
        emptyResource = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()) {
            emptyResource.put(buildResource, 0);
            costsResources.put(buildResource,2);
        }
    }

    /**
     *  Hilfsmethode zum Erstellen einer testMap die für alle OtherResources auf 0 gesetzt ist
     *
     * @return testMap die TestMap
     */
    private EnumMap<OtherResources,Integer> otherResourcesTestMap(){
        EnumMap<OtherResources,Integer> testMap = new EnumMap<>(OtherResources.class);
        for (OtherResources oR : OtherResources.values()) {
            testMap.put(oR, 0);
        }
        return testMap;
    }

    /**
     * Testet den Konstruktor
     */
    @Test
    public void testConstructor() {
        //aktuell nicht möglich assertNull("Fehler Name Konstruktor",testWonder.getPlayerName());
        assertEquals("Fehler WunderName Konstruktor", "RHODES",testWonder.getWonderName().name());

        EnumMap<BuildResources, Integer> testResource = emptyResource.clone();
        testResource.put(BuildResources.ORE, 1);
        testSet.add(testResource);
        testSet.add(emptyResource);
        assertEquals("Fehler Produces Konstruktor",testSet,testWonder.getProduces());

        testSet = new HashSet<>();
        testSet.add(testResource);
        assertEquals("Fehler Sells Konstruktor",testSet,testWonder.getSells());

        assertEquals("Fehler CostsResourcesLeft Konstruktor",costsResources,testWonder.getCostsResourcesLeft());
        assertEquals("Fehler CostsResourcesRight Konstruktor",costsResources,testWonder.getCostsResourcesRight());

        assertEquals("Fehler afterGameEffects Konstruktor",1,testWonder.getAfterGameEffects().size());
        assertEquals("Fehler cardColors Konstruktor",7,testWonder.getCardColors().length);
        assertEquals("Fehler zeusEffectAvailable Konstruktor",3,testWonder.getZeusEffectAvailable().length);
        assertEquals("Fehler otherResources Konstruktor",otherResourcesTestMap(),testWonder.getOtherResources());
        assertEquals("Fehler stages Konstruktor",3,testWonder.getStages().length);

        assertNull("Fehler boardState Konstruktor",testWonder.getBoardState());
        assertEquals("Fehler builtCards Konstruktor",0,testWonder.getBuiltCards().size());
        assertEquals("Fehler playerHand Konstruktor",0,testWonder.getBuiltCards().size());
        assertEquals("Fehler neighbours Konstruktor",0,testWonder.getNeighbours().size());
    }

    /**
     * Testet add/get Tablets
     */
    @Test
    public void addStage() {
        for(Stage stage : testWonder.getStages()) {
            assertNull("Stages ist fälschlicherweise befüllt",stage);
        }
        testWonder.addStage(new Stage(null,null,1));
        testWonder.addStage(new Stage(null,null,2));
        testWonder.addStage(new Stage(null,null,3));
        for(Stage stage : testWonder.getStages()) {
            assertNotNull("Stages sollte Objekte enthalten",stage);
        }
    }

    /**
     * Testet add/get Tablets
     */
    @Test
    public void addGetCoins() {

        assertEquals("Die Map sollte 0 Coins enthalten",0, testWonder.getOtherResources().get(OtherResources.COIN).intValue());
        testWonder.addCoins(5);
        assertEquals("Die Map sollte 5 Coins enthalten",5, testWonder.getOtherResources().get(OtherResources.COIN).intValue());
        testWonder.addCoins(5);
        assertEquals("Die Map sollte 10 Coins enthalten",10, testWonder.getOtherResources().get(OtherResources.COIN).intValue());
        assertEquals("Die Map sollte 8 otherResources beinhalten",8, testWonder.getOtherResources().size());
        assertEquals("GetCoins ist fehlgeschlagen",10,testWonder.getCoins());
    }

    /**
     * Testet add/get Tablets
     */
    @Test
    public void addGetCMPlus() {


        assertEquals("Die Map sollte 0 CMPlus enthalten",0, testWonder.getOtherResources().get(OtherResources.CMPLUS).intValue());
        testWonder.addCMPlus(5);
        assertEquals("Die Map sollte 5 CMPlus enthalten",5, testWonder.getOtherResources().get(OtherResources.CMPLUS).intValue());
        testWonder.addCMPlus(5);
        assertEquals("Die Map sollte 10 CMPlus enthalten",10, testWonder.getOtherResources().get(OtherResources.CMPLUS).intValue());
        assertEquals("Die Map sollte 8 otherResources beinhalten",8, testWonder.getOtherResources().size());
        assertEquals("GetCMPlus ist fehlgeschlagen",10,testWonder.getCMPlus());
    }

    /**
     * Testet add/get Tablets
     */
    @Test
    public void addGetCMMinus() {

        assertEquals("Die Map sollte 0 CMMinus enthalten",0, testWonder.getOtherResources().get(OtherResources.CMMINUS).intValue());
        testWonder.addCMMinus(5);
        assertEquals("Die Map sollte 5 CMMinus enthalten",5, testWonder.getOtherResources().get(OtherResources.CMMINUS).intValue());
        testWonder.addCMMinus(5);
        assertEquals("Die Map sollte 10 CMMinus enthalten",10, testWonder.getOtherResources().get(OtherResources.CMMINUS).intValue());
        assertEquals("Die Map sollte 8 otherResources beinhalten",8, testWonder.getOtherResources().size());
        assertEquals("GetCMMinus ist fehlgeschlagen",10,testWonder.getCMMinus());
    }

    /**
     * Testet add/get Tablets
     */
    @Test
    public void addGetGears() {

        assertEquals("Die Map sollte 0 Gears enthalten",0, testWonder.getOtherResources().get(OtherResources.GEAR).intValue());
        testWonder.addGears(5);
        assertEquals("Die Map sollte 5 Gears enthalten",5, testWonder.getOtherResources().get(OtherResources.GEAR).intValue());
        testWonder.addGears(5);
        assertEquals("Die Map sollte 10 Gears enthalten",10, testWonder.getOtherResources().get(OtherResources.GEAR).intValue());
        assertEquals("Die Map sollte 8 otherResources beinhalten",8, testWonder.getOtherResources().size());
        assertEquals("GetGears ist fehlgeschlagen",10,testWonder.getGears());

    }

    /**
     * Testet add/get Tablets
     */
    @Test
    public void addGetTablets() {

        assertEquals("Die Map sollte 0 Tablets enthalten",0, testWonder.getOtherResources().get(OtherResources.TABLET).intValue());
        testWonder.addTablets(5);
        assertEquals("Die Map sollte 5 Tablets enthalten",5, testWonder.getOtherResources().get(OtherResources.TABLET).intValue());
        testWonder.addTablets(5);
        assertEquals("Die Map sollte 10 Tablets enthalten",10, testWonder.getOtherResources().get(OtherResources.TABLET).intValue());
        assertEquals("Die Map sollte 8 otherResources beinhalten",8, testWonder.getOtherResources().size());
        assertEquals("GetTablets ist fehlgeschlagen",10,testWonder.getTablets());
    }

    /**
     * Testet add/get Compasses
     */
    @Test
    public void addGetCompasses() {

        assertEquals("Die Map sollte 0 Compasses enthalten",0, testWonder.getOtherResources().get(OtherResources.COMPASS).intValue());
        testWonder.addCompasses(5);
        assertEquals("Die Map sollte 5 Compasses enthalten",5, testWonder.getOtherResources().get(OtherResources.COMPASS).intValue());
        testWonder.addCompasses(5);
        assertEquals("Die Map sollte 10 Compasses enthalten",10, testWonder.getOtherResources().get(OtherResources.COMPASS).intValue());
        assertEquals("Die Map sollte 8 otherResources beinhalten",8, testWonder.getOtherResources().size());
        assertEquals("GetCompasses ist fehlgeschlagen",10,testWonder.getCompasses());


    }

    /**
     * Testet add/get Shields
     */
    @Test
    public void addGetShields() {

        assertEquals("Die Map sollte 0 Shields enthalten",0, testWonder.getOtherResources().get(OtherResources.SHIELD).intValue());
        testWonder.addShields(5);

        assertEquals("Die Map sollte 5 Shields enthalten",5, testWonder.getOtherResources().get(OtherResources.SHIELD).intValue());
        testWonder.addShields(5);

        assertEquals("Die Map sollte 10 Shields enthalten",10, testWonder.getOtherResources().get(OtherResources.SHIELD).intValue());
        assertEquals("Die Map sollte 8 otherResources beinhalten",8, testWonder.getOtherResources().size());
        assertEquals("GetShields ist fehlgeschlagen",10,testWonder.getShields());
    }

    /**
     * Testet add/get VPs
     */
    @Test
    public void addGetVPs() {

        assertEquals("Die Map sollte 0 VPs enthalten",0, testWonder.getOtherResources().get(OtherResources.VP).intValue());
        testWonder.addVPs(5);
        assertEquals("Die Map sollte 5 VPs enthalten",5, testWonder.getOtherResources().get(OtherResources.VP).intValue());
        testWonder.addVPs(5);
        assertEquals("Die Map sollte 10 VPs enthalten",10, testWonder.getOtherResources().get(OtherResources.VP).intValue());
        assertEquals("Die Map sollte 8 otherResources beinhalten",8, testWonder.getOtherResources().size());
        assertEquals("GetVPs ist fehlgeschlagen",10,testWonder.getVPs());
    }

    /**
     * Testet add/get buildCard
     */
    @Test
    public void addGetBuildCard() {
        assertEquals(0,testWonder.getBuiltCards().size());
        for(int i = 0; i < 10; i++){
            testWonder.addBuildCard(new Card(null, null, null, null, null, null, null));
        }
        assertEquals("addBuildCard ist fehlgeschlagen",10,testWonder.getBuiltCards().size());
    }

    /**
     * Testet IncreaseCardColor
     */
    @Test
    public void increaseCardColor() {
        for(int amount : testWonder.getCardColors()){
            assertEquals(0,amount);
        }
        testWonder.increaseCardColor(CardColor.BLUE);
        testWonder.increaseCardColor(CardColor.BLUE);
        testWonder.increaseCardColor(CardColor.GREEN);
        assertEquals("IncreaseCardColor ist fehlgeschlagen",2,testWonder.getCardColors()[CardColor.BLUE.ordinal()]);
        assertEquals("IncreaseCardColor ist fehlgeschlagen",1,testWonder.getCardColors()[CardColor.GREEN.ordinal()]);
    }

    /**
     * Testet IncreaseCurrentStage
     */
    @Test
    public void increaseCurrentStage() {
        assertNotEquals(1,testWonder.getCurrentStage());
        testWonder.increaseCurrentStage();
        assertEquals(1,testWonder.getCurrentStage());
    }
    /**
     * Testet setAndGetPlayerName
     */
    @Test
    public void setAndGetPlayerName() {
        testWonder.setPlayerName("Test");
        assertEquals("Fehler set oder get PlayerName","Test",testWonder.getPlayerName().substring(0,4));
    }
    /**
     * Testet getWonderName
     */
    @Test
    public void getWonderName() {
        assertEquals("Fehler getWonderName","RHODES",testWonder.getWonderName().name());
    }
    /**
     * Testet setAndGetProduces
     */
    @Test
    public void setAndGetProduces() {
        testSet.add(emptyResource);
        testWonder.setProduces(testSet);
        testWonder.setProduces(testSet);
        assertEquals("Fehler set oder get Produces",testSet, testWonder.getProduces());
    }
    /**
     * Testet setAndGetSells
     */
    @Test
    public void setAndGetSells() {
        EnumMap<BuildResources, Integer> testResource = emptyResource.clone();
        testResource.put(BuildResources.ORE, 1);
        testSet.add(testResource);
        testWonder.setProduces(testSet);
        assertEquals("Fehler set oder get Sells",testSet, testWonder.getSells());
    }
    /**
     * Testet setAndGetCostsResourcesLeft
     */
    @Test
    public void setAndGetCostsResourcesLeft() {
        testWonder.setCostsResourcesLeft(emptyResource);
        assertEquals("Fehler set oder get CostsResourcesLeft",emptyResource,testWonder.getCostsResourcesLeft());
    }
    /**
     * Testet setAndGetCostsResourcesRight
     */
    @Test
    public void setAndGetCostsResourcesRight() {
        testWonder.setCostsResourcesRight(emptyResource);
        assertEquals("Fehler set oder get CostsResourcesRight",emptyResource,testWonder.getCostsResourcesRight());
    }
    /**
     * Testet getAfterGameEffects
     */
    @Test
    public void getAfterGameEffects() {
        LinkedList<AfterGameEffect> test = new LinkedList<>();

        assertNotEquals("Fehler getAfterGameEffect",test,testWonder.getAfterGameEffects());
    }
    /**
     * Testet getCardColors
     */
    @Test
    public void getCardColors() {
        int[]  test = new int[7];
        assertArrayEquals("Fehler get CardColors",test,testWonder.getCardColors());
    }
    /**
     * Testet getCurrentStage
     */
    @Test
    public void getCurrentStage() {
        assertEquals("Fehler getCurrentStage",0,testWonder.getCurrentStage());
    }
    /**
     * Testet isDisabledFromHighscore
     */
    @Test
    public void isDisabledFromHighscore() {
        assertFalse("Fehler isDisabledFromHighscore", testWonder.isDisabledFromHighscore());
    }
    /**
     * Testet getZeusEffectAvailable
     */
    @Test
    public void getZeusEffectAvailable() {
        boolean[] test = new boolean[3];
        assertArrayEquals("Fehler getZeusEffectAvailable",test,testWonder.getZeusEffectAvailable());
    }
    /**
     * Testet getOtherResources
     */
    @Test
    public void getOtherResources() {
        EnumMap<OtherResources, Integer> test = new EnumMap<>(OtherResources.class);
        for (OtherResources otherResource : OtherResources.values()){
            test.put(otherResource, 0);
        }
        assertEquals("Fehler getOtherResources",test,testWonder.getOtherResources());
    }
    /**
     * Testet getStages
     */
    @Test
    public void getStages() {
        Stage[] test = new Stage[3];
        assertArrayEquals("Fehler getStages",test,testWonder.getStages());
    }
    /**
     * Testet setAndGetBoardState
     */
    @Test
    public void setAndGetBoardState() {
        LinkedList<Wonder> playerList = new LinkedList<>();
        playerList.add(testWonder);
        LinkedList<Card>[] cardList= new LinkedList[3];
        for(int i=0;i<3; i++){
            cardList[i]= new LinkedList<>();
        }
        BoardState test = new BoardState(playerList, cardList);
        testWonder.setBoardState(test);
        assertEquals("Fehler setAndGetBoardState",test,testWonder.getBoardState());
    }
    /**
     * Testet getBuiltCards
     */
    @Test
    public void getBuiltCards() {
        LinkedList<Card> test = new LinkedList<>();
        assertEquals("Fehler getBuiltCards",test,testWonder.getBuiltCards());
    }
    /**
     * Testet setAndGetPlayerHand
     */
    @Test
    public void setAndGetPlayerHand() {
        LinkedList<Card> test = new LinkedList<>();
        testWonder.setPlayerHand(test);
        assertEquals("Fehler setAndGetPlayerHand", test,testWonder.getPlayerHand());
    }

    /**
     * Testet setAndGetNeighbours
     */
    @Test
    public void setAndGetNeighbours() {
        LinkedList<Wonder> test = new LinkedList<>();
        test.add(testWonderLeft);
        test.add(testWonderRight);
        testWonder.setNeighbours(test);
        assertEquals("Fehler setAndGetNeighbours",test,testWonder.getNeighbours());
    }

    /**
     * Testet setAndGetPlayerType
     */
    @Test
    public void setAndGetPlayerType(){
        testWonder.setPlayerType(3);
        assertEquals("Fehler setAndGetPlayerType",3, testWonder.getPlayerType());
    }

    /**
     * Testet enableZeus
     */
    @Test
    public void enableZeus(){
        boolean[] test = new boolean[3];
        for (int i=0; i<3; i++){
            test[i]=true;
        }
        testWonder.enableZeus();
        assertArrayEquals("Fehler enableZeus",test,testWonder.getZeusEffectAvailable());
    }
    /**
     * Testet disableZeus
     */
    @Test
    public void disableZeus() {
        boolean[] test = new boolean[3];
        testWonder.enableZeus();
        testWonder.disableZeus(3);
        testWonder.disableZeus(1);
        testWonder.disableZeus(2);
        assertArrayEquals("Fehler disableZeus",test,testWonder.getZeusEffectAvailable());
    }
    /**
     * Testet disableHighscore
     */
    @Test
    public void disableHighscore(){
        testWonder.disableHighscore();
        assertTrue("Fehler disableHighscore", testWonder.isDisabledFromHighscore());
    }
    /**
     * Testet addAfterGameEffect
     */
    @Test
    public void addAfterGameEffect(){
        LinkedList<AfterGameEffect> effects = new LinkedList<>();
        AfterGameEffect testEffect = new CalculateNewScienceEffect();
        effects.add(testEffect);
        //testWonder.addAfterGameEffect(testEffect);
        assertNotEquals("Fehler addAfterGameEffect",effects, testWonder.getAfterGameEffects());
    }
}