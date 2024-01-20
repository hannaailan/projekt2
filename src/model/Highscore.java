package model;

import java.io.Serializable;

/**
 * The type Highscore.
 * @author Tobias
 */
public class Highscore implements Serializable {

    /**
 	 * der Name
 	 */
    private String name;

    /**
 	 * die Punkte
 	 */
    private int points;

    /**
     * die Position
     */
    private int position;

    /**
     * Instantiates a new Highscore.
     *
     * @param name     the name
     * @param points   the points
     * @param position the position
     */
    public Highscore(String name, int points, int position) {
        this.name=name;
        this.points=points;
        this.position=position;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets points.
     *
     * @param points the points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * sets position.
     *
     * @param position die zu setzende Position
     */
    public void setPosition(int position) {
        this.position=position;
    }
}
