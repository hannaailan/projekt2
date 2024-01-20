package model;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

/**
 * KLasse Wunder stellt die Daten bereit, welche die Controller benötigen
 *
 * @author Daniel Nemitz, Malte Sichtermann
 */
public class Wonder implements Serializable {

    /**
 	 * Name des zum Wunder gehörigen Spielers
 	 */
    private String playerName;

    /**
 	 * Name des Wunders
 	 */
    private WonderInfo wonderName;

    /**
     * ColorVP
     */
    private EnumMap<CardColor,Integer> colorVP;


    private int newScience=0;
    /**
     * Version control id
     */
    private int generation=0;

    /**
 	 * Ressourcen, die der Spieler für sich produziert (inklusive Gelb, Wunderstufen)
 	 */
    private HashSet<EnumMap<BuildResources,Integer>> produces;

    /**
 	 * Ressourcen, die der Spieler zum Handeln bereitstellt (nur Braun, Grau)
 	 */
    private HashSet<EnumMap<BuildResources,Integer>> sells;

    /**
 	 * Die Preise der einzelnen Ressourcen beim rechten Nachbarn (2 default, 1 wenn Marktplatz o.ä. gebaut)
 	 */
    private EnumMap<BuildResources, Integer> costsResourcesRight;

    /**
 	 * Die Preise der einzelnen Ressourcen beim linken Nachbarn (2 default, 1 wenn Marktplatz o.ä. gebaut)
 	 */
    private EnumMap<BuildResources, Integer> costsResourcesLeft;

    /**
 	 * Liste an Effekten, welche nach dem Spiel noch ausgeübt werden müssen (z.B. Lila/Gelbe Karten)
 	 */
    private LinkedList<AfterGameEffect> afterGameEffects;

    /**
 	 * Feld mit 7 Einträgen. In jedem Eintrag wird die Anzahl der von der zugehörigen Farbe gebauten Gebäude gespeichert
 	 */
    private int[] cardColors;

    /**
 	 * Speichert die aktuelle Wunderausbaustufe als Ganzzahl
 	 */
    private int currentStage;

    /**
 	 * Default false, lässt sich ein Spieler einen Tipp anzeigen oder führt undo/redo aus wird auf false gesetzt
 	 */
    private boolean isDisabledFromHighscore;

    /**
 	 * Default false an allen Stellen, baut ein Spieler mit dem Wunder Olympia die 2 Stufe aus, werden alle auf true
     * gesetzt. Nimmt er seine Gratisoption in Anspruch, so das Feld an der Stelle (age - 1) auf false gesetzt
 	 */
    private boolean[] zeusEffectAvailable;

    /**
 	 * Ressourcen wie VPs, Münzen etc, die keine Bauressourcen sind Ressourcen sind.
 	 */
    private EnumMap<OtherResources, Integer> otherResources;

    /**
     * Liste der Ausbaustufen des Wunders
     */
    private Stage[] stages;

    /**
     * BoardState der dem Wunder bekannt ist
     */
    private BoardState boardState;

    /**
     * Liste der errichteten Gebäude
     */
    private LinkedList<Card> builtCards;

    /**
     * Liste mit Handkarten des Spielers
     */
    private LinkedList<Card> playerHand;

    /**
     * Liste der Nachbarn des Spielers, Index 0 = Links, Index 1 = Rechts
     */
    private LinkedList<Wonder> neighbours;

    /** Der Spielertyp: 0 Human, 1 KI leicht, 2 KI mittel, &#62;=3 KI schwer*/
    private int playerType;
    /**
     * Bild des Wunders
     */
    private String image;
    /**
     * Ausgewählte Karte des Spielers
     */
    private Card selctedCard;

    /**
     * Konstruktor in dem Spielername, Wunder und die vom Wunder standardmäßig produzierte Ressource gesetzt werden
     *
     * @param wonderName übergebener Wundername
     * @param resource   übergebene Ressource
     */
    public Wonder(WonderInfo wonderName, BuildResources resource) {

        this.wonderName = wonderName;

        this.produces = new HashSet<>();
        this.sells = new HashSet<>();

        EnumMap<BuildResources, Integer> startResource = new EnumMap<>(BuildResources.class);
        EnumMap<BuildResources, Integer> empty = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()) {
            startResource.put(buildResource, 0);
            empty.put(buildResource, 0);
        }
        startResource.put(resource, 1);
        this.produces.add(empty);
        this.produces.add(startResource);
        this.sells.add(startResource);

        this.otherResources = new EnumMap<>(OtherResources.class);
        for (OtherResources oR : OtherResources.values()) {
            otherResources.put(oR, 0);
        }

        colorVP = new EnumMap<CardColor, Integer>(CardColor.class);
        for(CardColor cardColor : CardColor.values()){
            colorVP.put(cardColor,0);
        }

        this.costsResourcesRight = new EnumMap<>(BuildResources.class);
        this.costsResourcesLeft = new EnumMap<>(BuildResources.class);
        for(BuildResources buildResource : BuildResources.values()){
            costsResourcesRight.put(buildResource,2);
            costsResourcesLeft.put(buildResource,2);
        }
        this.afterGameEffects = new LinkedList<>();
        this.afterGameEffects.add(new CalculateNewScienceEffect());

        this.cardColors = new int[7];
        this.zeusEffectAvailable = new boolean[3];
        this.stages = new Stage[3];
        this.boardState = null;
        this.builtCards = new LinkedList<>();
        this.playerHand = new LinkedList<>();
        this.neighbours = new LinkedList<>();

        this.image=null;
    }

    /**
     * Sets generation.
     *
     * @param generation the generation
     */
    public void setGeneration(int generation) {
        this.generation = generation;
    }

    /**
     * Gets generation.
     *
     * @return the generation
     */
    public int getGeneration() {
        return generation;
    }

    /**
     * Funktion zum setzen des Bildes des Wunders
     *
     * @param image das Bild das gesetzt werden soll
     */
    public void setImage(String image){
        this.image=image;
    }

    /**
     * Funktion zum getten des Bildes des Wunders
     *
     * @return File Bilddatei
     */
    public String getImage(){
        return this.image;
    }

    /**
     * Fügt dem Wunder eine Stage hinzu
     *
     * @param stage hinzuzufügende Stage
     */
    public void addStage(Stage stage){
        stages[stage.getStageNr() - 1] = stage;
    }

    /**
     * Fügt dem Spieler eine übergebene Anzahl Münzen hinzu/Zieht sie ab (negative Werte)
     *
     * @param amount Anzahl Münzen die hinzugefügt/entfernt werden soll
     */
    public void addCoins(int amount) {
        int newCoinCount = otherResources.get(OtherResources.COIN) + amount;
        otherResources.replace(OtherResources.COIN,newCoinCount);
    }

    /**
     * Fügt dem Spieler eine übergebene Anzahl an Konflikt Siegpunkten hinzu
     *
     * @param amount Anzahl Konflikt Siegpunkte die hinzugefügt werden soll
     */
    public void addCMPlus(int amount){
        int newCMPlusCount = otherResources.get(OtherResources.CMPLUS) + amount;
        otherResources.replace(OtherResources.CMPLUS,newCMPlusCount);
    }

    /**
     * Fügt dem Spieler eine übergebene Anzahl an Konflikt Niederlagenpunkten hinzu
     *
     * @param amount Anzahl Konflikt Niederlagenpunkte die hinzugefügt werden soll
     */
    public void addCMMinus(int amount){
        int newCMMinusCount = otherResources.get(OtherResources.CMMINUS) + amount;
        otherResources.replace(OtherResources.CMMINUS,newCMMinusCount);
    }

    /**
     * Fügt dem Spieler eine übergebene Anzahl Zahnräder hinzu
     *
     * @param amount Anzahl Zahnräder die hinzugefügt werden soll
     */
    public void addGears(int amount) {
        int newGearCount = otherResources.get(OtherResources.GEAR) + amount;
        otherResources.replace(OtherResources.GEAR,newGearCount);
    }

    /**
     * Fügt dem Spieler eine übergebene Anzahl Tafeln hinzu
     *
     * @param amount Anzahl Tafeln die hinzugefügt/entfernt werden soll
     */
    public void addTablets(int amount) {
        int newTabletCount = otherResources.get(OtherResources.TABLET) + amount;
        otherResources.replace(OtherResources.TABLET,newTabletCount);
    }

    /**
     * Fügt dem Spieler eine übergebene Anzahl Kompasse hinzu/Zieht sie ab (negative Werte)
     *
     * @param amount Anzahl Kompasse die hinzugefügt werden soll
     */
    public void addCompasses(int amount) {
        int newCompassCount = otherResources.get(OtherResources.COMPASS) + amount;
        otherResources.replace(OtherResources.COMPASS,newCompassCount);
    }

    /**
     * Fügt dem Spieler eine übergebene Anzahl Schilde hinzu
     *
     * @param amount Anzahl Schilde die hinzugefügt werden soll
     */
    public void addShields(int amount) {
        int newShieldCount = otherResources.get(OtherResources.SHIELD) + amount;
        otherResources.replace(OtherResources.SHIELD, newShieldCount);
    }

    /**
     * Fügt dem Spieler eine übergebene Anzahl Victory Points hinzu
     *
     * @param amount Anzahl Victory Points die hinzugefügt werden soll
     */
    public void addVPs(int amount) {
        int newVPCount = otherResources.get(OtherResources.VP) + amount;
        otherResources.replace(OtherResources.VP, newVPCount);
    }

    /**
     * Fügt die übergebene Karte in die Liste builtCards
     *
     * @param card Karten die hinzugefügt werden soll
     */
    public void addBuildCard(Card card){
        builtCards.add(card);
    }

    /**
     * Add color vp.
     *
     * @param cardColor the card color
     * @param anz       the anz
     */
    public void addColorVP(CardColor cardColor,int anz){
        colorVP.put(cardColor,colorVP.get(cardColor)+anz);
    }

    /**
     * Erhöt die Anzahlz der übergebenen Kartenfarbe in CardColors[]
     *
     * @param color Kartenfarbe die hinzugefügt werden soll
     */
    public void increaseCardColor(CardColor color){
        cardColors[color.ordinal()]++;
    }

    /**
     * Erhöt currentStage um 1
     */
    public void increaseCurrentStage (){currentStage++;}

    /**
     * Gibt die Anzahl der Münzen eines Spielers zurück
     *
     * @return Münzen (Ganzzahl)
     */
    public int getCoins(){
        return otherResources.get(OtherResources.COIN);
    }

    /**
     * Gibt die Anzahl der CMPlus eines Spielers zurück
     *
     * @return CMPlus (Ganzzahl)
     */
    public int getCMPlus(){
        return otherResources.get(OtherResources.CMPLUS);
    }

    /**
     * Gibt die Anzahl der CMMINUS eines Spielers zurück
     *
     * @return CMMINUS (Ganzzahl)
     */
    public int getCMMinus(){
        return otherResources.get(OtherResources.CMMINUS);
    }

    /**
     * Gibt die Anzahl der Zähnräder eines Spielers zurück
     *
     * @return Zähnräder (Ganzzahl)
     */
    public int getGears(){
        return otherResources.get(OtherResources.GEAR);
    }

    /**
     * Gibt die Anzahl der Münzen eines Spielers zurück
     *
     * @return Münzen (Ganzzahl)
     */
    public int getTablets(){
        return otherResources.get(OtherResources.TABLET);
    }

    /**
     * Gibt die Anzahl der Kompasse eines Spielers zurück
     *
     * @return Kompasse (Ganzzahl)
     */
    public int getCompasses(){
        return otherResources.get(OtherResources.COMPASS);
    }

    /**
     * Gibt die Anzahl der Schilde eines Spielers zurück
     *
     * @return Schilde (Ganzzahl)
     */
    public int getShields(){
        return otherResources.get(OtherResources.SHIELD);
    }

    /**
     * Gibt die Anzahl der Victory Points eines Spielers zurück
     *
     * @return Victory Points (Ganzzahl)
     */
    public int getVPs(){
        return otherResources.get(OtherResources.VP);
    }

    /**
     * Gibt den Namen eines Spieler zurück
     *
     * @return playerName String
     */
    public String getPlayerName(){
        return playerName;// generation entfernt return playerName+generation;
    }

    /**
     * Gibt den Wundernamen eines Spieler zurück
     *
     * @return wonderName String
     */
    public WonderInfo getWonderName(){
        return wonderName;
    }

    /**
     * Gibt die Menge der produzierten Güter eines Spielers zurück
     *
     * @return produces Menge
     */
    public HashSet<EnumMap<BuildResources, Integer>> getProduces(){
        return produces;
    }

    /**
     * Setzt den Spielernamen
     *
     * @param playername zu setzender Name
     */
    public void setPlayerName(String playername){
        this.playerName=playername;
    }

    /**
     * Gibt die Menge der gehandelten Güter eines Spielers zurück
     *
     * @return sells Menge
     */
    public HashSet<EnumMap<BuildResources, Integer>> getSells(){
        return sells;
    }


    /**
     * Gibt Map der Kosten für Ressourcen des linken Nachbarns
     *
     * @return costRessourcesLeft map
     */
    public EnumMap<BuildResources, Integer> getCostsResourcesLeft(){
        return costsResourcesLeft;
    }

    /**
     * Gibt Map der Kosten für Ressourcen des rechten Nachbarns
     *
     * @return costsRessourcesRight map
     */
    public EnumMap<BuildResources, Integer> getCostsResourcesRight(){
        return costsResourcesRight;
    }

    /**
     * Gets color vp.
     *
     * @return the color vp
     */
    public EnumMap<CardColor,Integer> getColorVP() {return colorVP;}

    /**
     * Gibt Lite der Effekte welche nach dem Spiel ausgeführt werden müssen zurück
     *
     * @return afterGameEffects map
     */
    public LinkedList<AfterGameEffect> getAfterGameEffects(){
        return afterGameEffects;
    }

    /**
     * Gibt Feld mit Anzahlen der Kartenfarben zurück
     *
     * @return cardColors Array
     */
    public int[] getCardColors(){
        return cardColors;
    }

    /**
     * Gibt die aktuelle Ausbaustufe des Wunders zurück
     *
     * @return currentStage Ganzzahl
     */
    public int getCurrentStage(){
        return currentStage;
    }

    /**
     * Gibt zurück ob Spieler für HighScores berechtigt ist
     *
     * @return isDisabledFromHighscore boolean
     */
    public boolean isDisabledFromHighscore(){
        return isDisabledFromHighscore;
    }

    /**
     * Gibt Feld zurück, in Welchem Zeitalter noch Zeuseffect vorhanden ist
     *
     * @return zeusEffectAvailable Array
     */
    public boolean[] getZeusEffectAvailable(){
        return zeusEffectAvailable;
    }

    /**
     * Gibt Map der Ressourcen die keine Ressourcen sind zurück
     *
     * @return otherResources Map
     */
    public EnumMap<OtherResources, Integer> getOtherResources(){
        return otherResources;
    }

    /**
     * Gibt die Stages des Wunders zurück
     *
     * @return stages Array
     */
    public Stage[] getStages(){
        return stages;
    }

    /**
     * Setzen des BoardState Attributs
     *
     * @param boardState BoardState
     */
    public void setBoardState(BoardState boardState) {
        this.boardState = boardState;
    }

    /**
     * Gibt den BoardState zurück
     *
     * @return boardState BoardState
     */
    public BoardState getBoardState() {
        return boardState;
    }

    /**
     * Git die errichteten Karten des Spielers zurück
     *
     * @return builtCards Liste
     */
    public LinkedList<Card> getBuiltCards() {
        return builtCards;
    }

    /**
     * Git Karten Auf der Hand des Spielers zurück
     *
     * @return playerHand Liste
     */
    public LinkedList<Card> getPlayerHand() {
        return playerHand;
    }

    /**
     * Git die errichteten Karten des Spielers zurück
     *
     * @param playerHand Liste der Handkarten
     */
    public void setPlayerHand(LinkedList<Card> playerHand) {

        this.playerHand = playerHand;
    }

    /**
     * gibt Liste der Nachbarn zurück
     *
     * @return neighbours Liste
     */
    public LinkedList<Wonder> getNeighbours() {
        return neighbours;
    }

    /**
     * Setzt Nachbarn des Spielers
     *
     * @param neighbours Nachbarn
     */
    public void setNeighbours(LinkedList<Wonder> neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * Setzt die Menge der produzierten Ressourcen
     *
     * @param produces zu setztende Liste
     */
    public void setProduces(HashSet<EnumMap<BuildResources, Integer>> produces) {
        this.produces = produces;
    }

    /**
     * Setzt die zum Verkauf bereitgestellten Ressourcen
     *
     * @param sells zu setzende Liste
     */
    public void setSells(HashSet<EnumMap<BuildResources, Integer>> sells) {
        this.sells = sells;
    }


    /**
     * Ressourcenkosten des Linken Nachbarns werden gesetzt
     *
     * @param costsResourcesLeft zu setzende Kosten
     */
    public void setCostsResourcesLeft(EnumMap<BuildResources, Integer> costsResourcesLeft) {
        this.costsResourcesLeft = costsResourcesLeft;
    }

    /**
     * Ressourcenkosten des Linken Nachbarns werden gesetzt
     *
     * @param costsResourcesRight zu setzende Kosten
     */
    public void setCostsResourcesRight(EnumMap<BuildResources, Integer> costsResourcesRight) {
        this.costsResourcesRight = costsResourcesRight;
    }

    /**
     * Den Spielertyp abragen
     *
     * @return 0 = Human, 1 = KI leicht, 2 = KI mittel, größer als 3 = KI schwer
     */
    public int getPlayerType(){
        return playerType;
    }

    /**
     * Den Spielertyp setzen
     *
     * @param type 0 = Human, 1 = KI leicht, 2 = KI mittel, größer als 3 = KI schwer
     */
    public void setPlayerType(int type){
        playerType = type;
    }

    /**
     * Macht den zeus Effekt für alle Zeitalter verfügbar
     */
    public void enableZeus(){
        for(int i=0; i<3; i++){
            zeusEffectAvailable[i]=true;
        }
    }

    /**
     * Setzt den Zeus Effekt für ein Zeitalter auf benutzt
     *
     * @param age das Zeitalter für dass der Effekt disabled wird
     */
    public void disableZeus(int age){
        zeusEffectAvailable[age-1]=false;
    }

    /**
     * nimmt den Spieler aus dem Highscore heraus
     */
    public void disableHighscore(){
        isDisabledFromHighscore = true;
    }

    /**
     * Fügt einen AftergameEffect hinzu, welcher nach dem Spiel ausgeführt werden muss
     *
     * @param afterGameEffect Aftergameeffect
     */
    public void addAfterGameEffect (AfterGameEffect afterGameEffect){
        afterGameEffects.add(afterGameEffect);
    }

    /**
     * Fügt alle Elemente aus ProducesNew in Produces vom Wunder hinzu, wenn sie nicht bereits vorhanden sind
     *
     * @param producesNew Set wie produces plus neue Resource
     */
    public void addProduces(HashSet<EnumMap<BuildResources,Integer>> producesNew){
        this.produces.addAll(producesNew);
    }

    /**
     * Fügt alle Elemente aus SellsNew in Sells vom Wunder hinzu, wenn sie nicht bereits vorhanden sind
     *
     * @param sellsNew Set wie sells plus neue Resource
     */
    public void addSells(HashSet<EnumMap<BuildResources,Integer>> sellsNew){
        this.sells.addAll(sellsNew);
    }

     /**
     * Klont die Stage
     * @param stages Stages
     * @return Stage Clone
     */
    private Stage[] cloneStages(Stage[] stages){
        Stage[] clone = new Stage[stages.length];
        for(int i = 0; i < stages.length; i++){
            clone[i] = stages[i].clone();
        }
        return clone;
    }


    /**
     * Erstellt einen Deep Clone des Wunders
     *
     * @return Wunder wonder
     */
    public Wonder copy(){
        Wonder clone = wonderName.buildBoard();
        clone.playerName = this.playerName;
        HashSet<EnumMap<BuildResources,Integer>> producesClone = new HashSet<>();
        for(EnumMap<BuildResources,Integer> element : this.produces){
            producesClone.add(element.clone());
        }
        clone.produces = producesClone;
        HashSet<EnumMap<BuildResources,Integer>> sellsClone = new HashSet<>();
        for(EnumMap<BuildResources,Integer> element : this.sells){
            sellsClone.add(element.clone());
        }
        clone.sells = sellsClone;
        clone.costsResourcesLeft = this.costsResourcesLeft.clone();
        clone.costsResourcesRight = this.costsResourcesRight.clone();
        clone.afterGameEffects = new LinkedList<AfterGameEffect>(this.afterGameEffects);
        clone.cardColors = Arrays.stream(this.cardColors).toArray();
        clone.currentStage = this.currentStage;
        clone.isDisabledFromHighscore = this.isDisabledFromHighscore();
        clone.zeusEffectAvailable = this.zeusEffectAvailable.clone();
        clone.otherResources = this.otherResources.clone();
        clone.stages = cloneStages(this.stages);
        clone.boardState = this.boardState;
        clone.builtCards = new LinkedList<>(this.builtCards);
        clone.playerHand = new LinkedList<>(this.playerHand);
        clone.playerType = this.playerType;
        clone.neighbours = this.neighbours;
        clone.setColorVP(new EnumMap<CardColor, Integer>(this.colorVP));
        clone.setSelctedCard(this.selctedCard);
        clone.setGeneration(generation+1);
        clone.setNewScience(this.newScience);
        //boardState und neighbours müssen beim Aufruf zusätzlich gesetzt werden
        return clone;
    }

    /**
     * Sets the colorVP
     *
     * @param map the new map
     */
    public void setColorVP(EnumMap<CardColor, Integer> map) {
        colorVP = map;
    }

    /**
     * gets the selected Card
     *
     * @return the card
     */
    public Card getSelctedCard() {
        return selctedCard;
    }

    /**
     * sets the selected Card
     *
     * @param selctedCard the new Card
     */
    public void setSelctedCard(Card selctedCard) {
        this.selctedCard = selctedCard;
    }

    /**
     * Gets new science.
     *
     * @return the new science
     */
    public int getNewScience() {
        return newScience;
    }

    /**
     * Sets new science.
     *
     * @param newScience the new science
     */
    public void setNewScience(int newScience) {
        this.newScience = newScience;
    }
}
