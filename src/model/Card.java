package model;





import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Klasse Karte
 */
public class Card implements Serializable {

    /**
 	 * Name der Karte
 	 */
    private CardInfo name;

    /**
 	 * Kosten zum Bau der Karte
 	 */
    private EnumMap<BuildResources, Integer> costs;

    /**
 	 * Farbe der Karte
 	 */
    private CardColor color;

    /**
 	 * Gebäude welche durch die Karte kostenlos errichtet werden können
 	 */
    private CardInfo[] buildsFree;

    /**
 	 * Gebäude durch welche die Karte kostenlos errichtet werden kann
 	 */
    private CardInfo[] builtFreeBy;

    /**
 	 * Effekt der Karte
 	 */
    private Effect effect;

    /**
     * Bild der Karte
     */
    private String image;



    /**
     * Instanziiert eine neue Karte
     *
     * @param name        Der Kartenname
     * @param costs       Die Kartenkosten
     * @param color       Die Kartenfarbe
     * @param buildsFree  Feld der Karten die durch Karte frei errichtet werden können
     * @param builtFreeBy Feld der Karten durch die die Karte frei errichtet werden kann
     * @param cardEffect  Der Karteneffekt
     * @param image       Das Bild der Karte
     */
    public Card(CardInfo name, EnumMap<BuildResources, Integer> costs, CardColor color, CardInfo [] buildsFree, CardInfo [] builtFreeBy, Effect cardEffect, String image) {
        this.name = name;
        this.color = color;
        this.costs=costs;
        this.buildsFree = buildsFree;
        this.builtFreeBy = builtFreeBy;
        this.effect = cardEffect;
        this.image=image;
    }

    /**
     * Gibt den Kartennamen zurück
     *
     * @return Der Name
     */
    public CardInfo getName() {
        return name;
    }

    /**
     * Gibt Kosten zurück
     *
     * @return Die Kosten
     */
    public EnumMap<BuildResources, Integer> getCosts() {
        return costs;
    }

    /**
     * Gibt Kartenfarbe zurück
     *
     * @return Die Kartenfarbe
     */
    public CardColor getColor(){
        return color;
    }

    /**
     * Gibt Feld der durch Karte frei gebauten Karten zurück
     *
     * @return Das Feld
     */
    public CardInfo[] getBuildsFree(){
        return buildsFree;
    }

    /**
     * Gibt Feld der Karten die Karte frei bauen zurück
     *
     * @return Das Feld
     */
    public CardInfo[] getBuiltFreeBy() {
        return builtFreeBy;
    }

    /**
     * Gibt Effekt zurück
     *
     * @return Der Effekt
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Gibt das Bild zurück
     *
     * @return das Bild
     */
    public String getImage() {
        return image;
    }
}
