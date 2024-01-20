package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The type Highscore test.
 *
 * @author Tobias
 */
public class HighscoreTest {


    /**
     * der hightscore
     */
    private Highscore highscore;

    /**
     * der name
     */
    private String name;

    /**
     * die punkte
     */
    private int points;

    /**
     * Die Position
     */
    private Integer position;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        name="name";
        points=1;
        position=4;
        highscore = new Highscore(name,points, position);
    }

    /**
     * Konstruktor test
     */
    @Test
    public void konstruktorTest(){
        assertNotNull(new Highscore(name,points, position));
    }

    /**
     * Gets name.
     */
    @Test
    public void getName() {
        assertEquals(name,highscore.getName());
    }

    /**
     * Sets name.
     */
    @Test
    public void setName() {
        name="NAME";
        highscore.setName(name);
        assertEquals(name,highscore.getName());
    }

    /**
     * Gets points.
     */
    @Test
    public void getPoints() {
        assertEquals(points,highscore.getPoints());
    }

    /**
     * Sets points.
     */
    @Test
    public void setPoints() {
        points=2;
        highscore.setPoints(points);
        assertEquals(points,highscore.getPoints());
    }

    /**
     * Test getPosition
     */
    @Test
    public void getPosition() {
        assertEquals(4, highscore.getPosition());
    }
}