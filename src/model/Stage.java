package model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.EnumMap;

/**
 * Die Klasse Stage
 */
public class Stage implements Serializable {


    private EnumMap<BuildResources, Integer> costs;


    private int stageNr;


    private Card buildMarker;


    private Effect stageEffect;

    private String image;

    /**
     * Instanziiert eine neue Stage mit Kosten, effect und nr (entspricht der Position auf dem Wonder)
     *
     * @param costs       the costs
     * @param stageEffect the stage effect
     * @param stageNr     the stage nr
     */
    public Stage(EnumMap<BuildResources, Integer> costs, Effect stageEffect, int stageNr) {
        this.costs = costs;
        this.stageEffect = stageEffect;
        this.stageNr = stageNr;
    }

    /**
     * Gibt die StageNr zurück
     *
     * @return StageNr Ganzzahl
     */
    public int getStageNr() {
        return stageNr;
    }

    /**
     * Gibt den Stageeffekt zurück
     *
     * @return stageEffect Effekt
     */
    public Effect getStageEffect(){
        return stageEffect;
    }

    /**
     * Gibt eine Map der Ausbaukosten zurück
     *
     * @return costs Ausbaukosten
     */
    public EnumMap<BuildResources, Integer> getCosts(){
        return costs;
    }

    /**
     * Gibt die Karte die zum Ausbau dieser Stage genutzt wurde zurück
     *
     * @return buildMarker Karte
     */
    public Card getBuildMarker(){
        return buildMarker;
    }

    /**
     * Setzt den BuildMarker
     *
     * @param buildMarker Karte die zum Ausbau der Stage genutzt wurde
     */
    public void setBuildMarker(Card buildMarker) {
        this.buildMarker = buildMarker;
    }

    /**
     *
     * @return neue Stage
     */
    public Stage clone(){
        Stage newStage = new Stage(getCosts(),getStageEffect(),getStageNr());
        newStage.setBuildMarker(getBuildMarker());
        newStage.setFile(getFile());
        return newStage;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getFile() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setFile(String image) {
        this.image = image;
    }
}
