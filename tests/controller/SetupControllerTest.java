package controller;


import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * The type Setup controller test.
 *
 * @author Tobias
 */
public class SetupControllerTest {

    private SetupController setupC;
    private MainController mainC;

    /**
     * Setup.
     */
    @Before
    public void setup(){
        mainC = new MainController();
        setupC = new SetupController(mainC);
    }

    /**
     * Konstruktor test.
     */
    @Test
    public void konstruktorTest(){
        assertNotNull(new SetupController(mainC));
    }

    /**
     * Shuffle Test.
     */
    @Test
    public void shuffleTest() {
        LinkedList<Integer> testlist = new LinkedList<>();
        for(int index=0;index<100;index++)
            testlist.add(index);
        assertTrue(testlist.size()==100);
        assertTrue(SetupController.shuffle(testlist).size()==100);
    }


    /**
     * Start tournament Test.
     */
    @Test
    public void startTournamentTest() {

        Pair<String,Integer>[] playername = new Pair[2];
        playername[0]= new Pair<>("S1",1);
        playername[1]= new Pair<>("S2",1);
        String file = "src/gui/saveddata/ki-turnier.csv";
        setupC.startTournament(playername, file);
    }

    /**
     * Start game Test.
     */
    @Test
    public void startGameTest() {
        Pair<String,Integer>[] playername = new Pair[4];
        playername[0]= new Pair<>("S1",0);
        playername[1]= new Pair<>("S2",0);
        playername[2]= new Pair<>("S3",0);
        playername[3]= new Pair<>("S4",0);
        setupC.startGame(playername,false);
    }
}
