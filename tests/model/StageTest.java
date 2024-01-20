package model;

import org.junit.Before;
import model.CardInfo;
import org.junit.Test;

import static org.junit.Assert.*;

//import java.util.EnumMap;
import java.util.EnumMap;
import java.util.Map;

/**
 * die Testklasse für Model.Stage
 * @author Tobias
 */
public class StageTest {

    /**
     * die Stage
     */
	private Stage stage;

    /**
     * die StageNR
     */
	private int stageNr;

	/**
	 *der BuildMarker
	 */
    private Card buildMarker;

    /**
     * der StageEffect
     */
    private Effect stageEffect;

    /**
     * die Kosten
     */
    private EnumMap<BuildResources, Integer> costs;


    /**
     * Erstellt vor jeder Klassemethode eine Testumgebung
     */
    @Before
    public void setUp(){

        costs = new EnumMap<BuildResources, Integer>(BuildResources.class);
        costs.put(BuildResources.BRICK,3);

        stageNr=1;

        stageEffect = new AddBuildResEffect(BuildResources.ORE,2);

        buildMarker = CardInfo.LUMBER_YARD.buildCard();

        stage = new Stage(costs,stageEffect,stageNr);
    	
    }

    /**
     * testet die Methode getStageNr
     */
    @Test
    public void testGetStageNr() {
    	assertEquals(stageNr,stage.getStageNr());
    }

    /**
     * testet die Methode getStageEffect
     */
    @Test
    public void testGetStageEffect() {
    	assertEquals(stageEffect,stage.getStageEffect());
    }

    /**
     * testet die Methode getCosts
     */
    @Test
    public void testGetCosts() {
    	assertEquals(costs,stage.getCosts());
    }

    /**
     * testet die Methode getBuildMarker
     */
    @Test
    public void testGetBuildMarker() {
    	assertNull(stage.getBuildMarker());
    	stage.setBuildMarker(buildMarker);
    	assertEquals(buildMarker,stage.getBuildMarker());
    }

    /**
     * testet die Methode setBuildMarker
     */
    @Test
    public void testSetBuildMarker() {
    	stage.setBuildMarker(buildMarker);
    	assertEquals(buildMarker,stage.getBuildMarker());
    }

    /**
     * Test clone.
     */
    @Test
    public void testClone(){
        Stage newStage = stage.clone();

        assertEquals(newStage.getBuildMarker(),stage.getBuildMarker());
        assertEquals(newStage.getCosts(),stage.getCosts());
        assertEquals(newStage.getStageEffect(),stage.getStageEffect());
        assertEquals(newStage.getStageNr(),stage.getStageNr());
        assertNotEquals(newStage,stage);
    }
    
    
    
}
