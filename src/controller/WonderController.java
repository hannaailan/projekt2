package controller;

import javafx.util.Pair;
import model.*;

import java.io.Serializable;
import java.util.*;

/**
 * Klasse zum Verwalten von Wundern/Spieler
 * @author Daniel Nemitz, Malte Sichtermann
 */
public class WonderController implements Serializable {

    /**
     * Maincontroller der alle anderen Controller kennt
     */
    private MainController mainController;


    private final EnumMap<BuildResources, Integer> emptyResource;

    /**
     * Konstruktor, welcher beim erstellen eines neuen Wunders den Maincontroller setzt.
     * @param mainController übergebener Mainkontroller
     */
    public WonderController(MainController mainController) {
        this.mainController = mainController;
        this.emptyResource = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()) {
            emptyResource.put(buildResource, 0);
        }
    }

    /**
     * Testet ob eine Karte bereit Gebaut ist
     * @param card Karte die gebaut werden soll
     * @param wonder Spieler der Bauen will
     * @return boolean true wenn schon vorhanden, false sonst
     */
    public boolean isBuiltAlready(Card card, Wonder wonder){
        for(Card builtCard : wonder.getBuiltCards())
            if(builtCard.getName().equals(card.getName())){
                return true;
        }
        return false;
    }
    /**
     * Methode zum Abwerfen/Verkaufen einer Karte
     *    - Karte auf Ablage
     *    - 3 Münzen erhalten
     * @param selectedCard die Karte die zum abwerfen ausgewählt wurde
     * @param wonder der Spieler der die Karte abwirft
     */
    public void discardCard(Card selectedCard, Wonder wonder) {
        BoardState activeBoard = wonder.getBoardState();
        activeBoard.getDiscardStack().add(selectedCard);
        wonder.addCoins(3);
    }

    /**
     * Methode zum Ausbauen eines Wunders
     *     - increase currentStage im Wonder
     *     - Effekt ausführen
     *     - Karte als Marker an die Stage anlegen
     * @param selectedCard die Karte die als "Marker" an die Stage gelegt wird
     * @param wonder das Wunder das ausgebaut werden soll
     */
    public void increaseStage(Card selectedCard, Wonder wonder)  {
        int currentStageNumber = wonder.getCurrentStage();
        int age = this.mainController.getBoardController().getAge();
        Stage stage = wonder.getStages()[currentStageNumber];
        stage.setBuildMarker(selectedCard);
        stage.getStageEffect().effect(wonder);
        switch (age) {
            case 1: stage.setFile("/gui/image/age1_small.png"); break;
            case 2: stage.setFile("/gui/image/age2_small.png"); break;
            case 3: stage.setFile("/gui/image/age3_small.png"); break;
            default: System.err.println("WonderController: Falsches Zeitalter. Kein passendes Bild für die Stage"); break;
        }
        wonder.increaseCurrentStage();
    }

    /**
     * Methode zum Errichten eine Gebäudes
     *     - Array cardColors aktualisieren
     *     - Effekt ausführen
     *     - Karte in builtCards einfügen
     * @param selectedCard das Gebäude das errichtet werden soll
     * @param wonder der Spieler der dass Gebäude errichtet
     */
    public void createBuilding(Card selectedCard, Wonder wonder)  {
        assert selectedCard != null;
        assert selectedCard.getColor() != null;
        assert selectedCard.getEffect() != null;
        selectedCard.getEffect().effect(wonder);
        wonder.addBuildCard(selectedCard);
        if(selectedCard.getName() != null) {
            wonder.increaseCardColor(selectedCard.getColor());
            wonder.addCoins(-selectedCard.getName().buildCard().getCosts().get(BuildResources.COIN));
        }
    }

    /**
     * Methode zum Errichten eine Gebäudes
     *     - Array cardColors aktualisieren
     *     - Effekt ausführen
     *     - Karte in builtCards einfügen
     * @param selectedCard das Gebäude das errichtet werden soll
     * @param wonder der Spieler der dass Gebäude errichtet
     */
    public void createBuildingFree(Card selectedCard, Wonder wonder)  {
        assert selectedCard != null;
        assert selectedCard.getColor() != null;
        assert selectedCard.getEffect() != null;
        selectedCard.getEffect().effect(wonder);
        wonder.addBuildCard(selectedCard);
        if(selectedCard.getName() != null) {
            wonder.increaseCardColor(selectedCard.getColor());
        }
    }

    /**
     * Gibt für das angeforderte Wunder die Kartenliste zurück
     * @param player Spieler dessen Karten gefordert sind
     * @return Kartenliste Spielerhand
     */
    public LinkedList<Card> getHand(Wonder player){
    	return player.getPlayerHand();
	}

    /**
     * Führt den Handel einer gewählten Option aus und passt die Münzen der Spieler an
     * @param player Spieler der den Handel ausführt
     * @param selectedTrade Paar mit Münzwerten die an linken/rechten Nachbarn gegeben werden müssen
     */
    public void trade(Wonder player, Pair<Integer,Integer> selectedTrade){
        int costLeft = selectedTrade.getKey();
        int costRight = selectedTrade.getValue();
        player.addCoins(-(costLeft+costRight));
        player.getNeighbours().get(0).addCoins(costLeft);
        int plaNeigSize = player.getNeighbours().size();
        int num = 2;
        if ( plaNeigSize == num) {
            player.getNeighbours().get(1).addCoins(costRight);
        }
	}

    /**
     * prüft ob eingegebene Resourcren produziert oder gehandelt werden können
     *     - eigene Produktion
     *         - Ressourcen
     *     - Handel
     *         - Ressourcen
     *         - Münzen
     * @param buildCost Resourcenkosten
     * @param currentPlayer der aktuelle Spieler
     * @return boolean gibt Ja zurück wenn die Resourcen durch Produktion oder Handel (nich keine Münzprüfung) bezahlbar sind, sonst Nein
     *	 	 	Diese Exception wird geworfen, fallsdie Methode noch nicht implementiert ist.
     */
    public boolean isAffordable(EnumMap<BuildResources,Integer> buildCost, Wonder currentPlayer) {
        int coins = buildCost.get(BuildResources.COIN);
        buildCost.put(BuildResources.COIN, 0);
        if (currentPlayer.getCoins()<coins){
            return false;
        }
        if(currentPlayer.getProduces().contains(buildCost)){
            return true;
        }
        HashSet<EnumMap<BuildResources,Integer>> missingResources = new HashSet<>(missingResources(currentPlayer.getProduces(),buildCost));
        HashSet<EnumMap<BuildResources,Integer>> buyOptions = new HashSet<>(isTradable(missingResources,currentPlayer));
        int cheapestPrice;
        if(!buyOptions.isEmpty()){
            cheapestPrice = cheapestTrade(buyOptions,currentPlayer);
            return cheapestPrice <= currentPlayer.getCoins() && 0 != cheapestPrice;
        }
        return false;
    }


    /**
     * Berechnet noch fehlende Kombinationen an Resourcen
     * @param produces Resourcen die produziert werden
     * @param required benötigte Resourcen
     * @return all noch fehlende Kombinationen
     */
    public HashSet<EnumMap<BuildResources,Integer>> missingResources(HashSet<EnumMap<BuildResources,Integer>> produces, EnumMap<BuildResources,Integer> required ){
        HashSet<EnumMap<BuildResources,Integer>> missingResources = new HashSet<>();
        missingResources.add(required);
        for(EnumMap<BuildResources,Integer> element : produces){
            if(mapContainsMap(required,element)){
                missingResources.add(mapDifference(required,element));
            }
        }
        return missingResources;
    }
    /**
     * Prüft für Karten ob sie durch Zeus oder Techtree gratis errichtbar sind
     *     - Zeus
     *     - Techtree
     *     - Siehe Sequenzdiagramm
     * @param wonder aktueller Spieler
     * @param card die Karte die geprüft werden soll
     * @return boolean
     */
    public boolean isFree(Wonder wonder, Card card) {
        if(wonder.getBoardState() != null) {
            int activeAge = wonder.getBoardState().getAge();
            if (wonder.getZeusEffectAvailable()[activeAge - 1]) {//TODO -1 hinzugefügt überprüfen ob das funktioniert, da im 3. Zeitalter ist active Age 3
                return true;
            } else {
                LinkedList<Card> builtCards = wonder.getBuiltCards();
                for (CardInfo freeCard : card.getBuiltFreeBy()) {
                    for (Card playerCard : builtCards) {
                        if (playerCard.getName().equals(freeCard)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * berechnet bei eingabe von Baukosten und Spieler, alle möglichen Handelskosten
     * @param player der Spieler der etwas errichten will
     * @param cost die Baukosten
     * @return eine Liste mit Paaren aller möglichen Handelskosten
     */
    public LinkedList<Pair<Integer, Integer>> calculateAllResourceOptions(Wonder player, EnumMap<BuildResources, Integer> cost) {
        LinkedList<Pair<Integer, Integer>> resourceOptions = new LinkedList<>();
        HashSet<EnumMap<BuildResources, Integer>> potence = potenceMap(cost);
        HashSet<EnumMap<BuildResources, Integer>>  leftSells = new HashSet<>(player.getNeighbours().get(0).getSells());
        HashSet<EnumMap<BuildResources, Integer>> rightSells;
        EnumMap<BuildResources, Integer> difference;
        EnumMap<BuildResources, Integer> difference2;
        EnumMap<BuildResources, Integer> priceListLeft = player.getCostsResourcesLeft();
        EnumMap<BuildResources, Integer> priceListRight = player.getCostsResourcesRight();
        int playNSize = player.getNeighbours().size();
        int num = 2;
        if(playNSize == num) {
            rightSells = new HashSet<>(player.getNeighbours().get(1).getSells());
        }
        else{
            rightSells = new HashSet<>();
        }
        EnumMap<BuildResources, Integer> emptyResource = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()) {
            emptyResource.put(buildResource, 0);
        }
        leftSells.add(emptyResource.clone());
        rightSells.add(emptyResource.clone());
        int leftCost;
        int rightCost;

        for(EnumMap<BuildResources, Integer> elementLeft : leftSells){
            if(potence.contains(elementLeft)){
                difference = mapDifference(cost, elementLeft);
                for(EnumMap<BuildResources, Integer> elementRight : rightSells){
                    if(mapContainsMap(difference, elementRight)){
                        difference2 = mapDifference(difference, elementRight);
                        if(player.getProduces().contains(difference2)){
                            leftCost = calculateBuyCost(priceListLeft, elementLeft);
                            rightCost=calculateBuyCost(priceListRight, elementRight);
                            if(player.getCoins()>=(leftCost+rightCost)) {
                                resourceOptions.add(new Pair<>(leftCost, rightCost));
                            }
                        }
                    }
                }
            }
        }
        resourceOptions.sort(Comparator.comparing(Pair :: getValue));
        resourceOptions.sort(Comparator.comparing(Pair::getKey));
        //löscht doppelte Paare
        int index=1;
        while (index<resourceOptions.size()){
            if(resourceOptions.get(index-1).getKey()==resourceOptions.get(index).getKey() &
                    resourceOptions.get(index-1).getValue()==resourceOptions.get(index).getValue())
                resourceOptions.remove(index);
            else
                index++;
        }
        return resourceOptions;
    }

    private int calculateBuyCost (EnumMap<BuildResources, Integer> priceList, EnumMap<BuildResources, Integer> cost){
        int buyCost=0;
        for (BuildResources bR : BuildResources.values()){
            for (int i=cost.get(bR); i>0; i--){
                buyCost += priceList.get(bR);
            }
        }
        return buyCost;
    }

    /**
     * gibt die "Potenzmenge" zu einger gegebenen BuildResource Map
     * @param map die Map
     * @return "Potenzmenge"
     */
    private HashSet<EnumMap<BuildResources, Integer>> potenceMap(EnumMap<BuildResources, Integer> map){
        EnumMap<BuildResources, Integer> empty = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()){
            empty.put(buildResource, 0);
        }
        HashSet<EnumMap<BuildResources, Integer>> potence = new HashSet<>();
        for (BuildResources buildResource : BuildResources.values()){
            for(int i=map.get(buildResource); i>0; i--){
                EnumMap<BuildResources, Integer> add = empty.clone();
                add.put(buildResource, 1);
                potence = integrateResource(potence, add);
            }
        }
        potence.add(empty);
        return potence;
    }

    /**
     * Gibt bei eingabe aller möglicherweise gewünschten Kaufoptionen die dazu möglichen Handelsoptionen (noch keine Münzprüfung)
     *	   - Eingabe: mögliche Kaufoptionen (alle Kombinationen die wenn gekauft das Bauen erlauben)
     *     - Ausgabe: Teilmenge der Eingabe, die tatsächlich theoretisch kaufbar wäre (noch keine Preiskontrolle)
     * @param missingResources möglicherweise gewünschten Kaufoptionen
     * @param currentPlayer Spieler der am Zug ist
     * @return Set&#60;Collection&#60;BuildResources&#62;&#62; möglichen Handelsoptionen (noch keine Münzprüfung)
     *	 	 	Diese Exception wird geworfen, fallsdie Methode noch nicht implementiert ist.
     */
    public HashSet<EnumMap<BuildResources,Integer>> isTradable(HashSet<EnumMap<BuildResources,Integer>> missingResources, Wonder currentPlayer) {
        HashSet<EnumMap<BuildResources,Integer>> leftNeighbourSells;
        HashSet<EnumMap<BuildResources,Integer>> rightNeighbourSells;
        HashSet<EnumMap<BuildResources,Integer>> buyOptions = new HashSet<>();
        leftNeighbourSells = currentPlayer.getNeighbours().get(0).getSells();
        HashSet<EnumMap<BuildResources,Integer>> combinedSet = new HashSet<>(leftNeighbourSells);
        int cNS =currentPlayer.getNeighbours().size();
        int num = 2;
        if( cNS == num){
            rightNeighbourSells = currentPlayer.getNeighbours().get(1).getSells();
            combinedSet.addAll(rightNeighbourSells);
            for(EnumMap<BuildResources,Integer> element : leftNeighbourSells){
                combinedSet.addAll(integrateResource(rightNeighbourSells,element));
            }
        }
        for(EnumMap<BuildResources,Integer> element : missingResources){
            if(combinedSet.contains(element)) {
                buyOptions.add(element);
            }
        }
        return buyOptions;
    }

    /**
     * Berechnet aus eingegebenen Handelsoptionen den günstigsten Preis
     *     - Eingabe: echt mögliche Kaufoptionen
     *         - siehe isTradeable Ausgabe
     *     - Ausgabe: niedrigster Preis der für Handel bezahlt werden muss
     * @param tradeCombinations mögliche Handelsoptionen
     * @param currentPlayer Spieler der am Zug ist
     * @return int günstigster Preis
     *	 	 	Diese Exception wird geworfen, fallsdie Methode noch nicht implementiert ist.
     */
    public int cheapestTrade(HashSet<EnumMap<BuildResources,Integer>> tradeCombinations, Wonder currentPlayer) {

        int cheapestPrice = 0;
        Wonder leftNeighbour = currentPlayer.getNeighbours().get(0);
        HashSet<EnumMap<BuildResources,Integer>> rightNeighbourSells = new HashSet<>();
        int cNS =currentPlayer.getNeighbours().size();
        int num = 2;
        if(cNS == num){
            rightNeighbourSells = currentPlayer.getNeighbours().get(1).getSells();
        }
        for(EnumMap<BuildResources,Integer> element : tradeCombinations){
            int combinatedPrice = 0;
            EnumMap<BuildResources,Integer> tempMap = new EnumMap<>(BuildResources.class);
            for(BuildResources buildResource : BuildResources.values()){
                if(currentPlayer.getCostsResourcesLeft().get(buildResource).equals(currentPlayer.getCostsResourcesRight().get(buildResource))){
                    combinatedPrice += element.get(buildResource) * currentPlayer.getCostsResourcesLeft().get(buildResource);
                }else if(currentPlayer.getCostsResourcesLeft().get(buildResource) < currentPlayer.getCostsResourcesRight().get(buildResource)){
                    for(BuildResources buildResource2 : BuildResources.values()){
                        tempMap.put(buildResource2, 0);
                    }
                    tempMap.put(buildResource, element.get(buildResource));
                    for(int i = element.get(buildResource); i > 0; i--){
                        if(leftNeighbour.getSells().contains(tempMap)){
                            combinatedPrice += tempMap.get(buildResource) ;
                            break;
                        }else{
                            combinatedPrice += 2;
                            tempMap.put(buildResource,i - 1);
                        }
                    }
                }
                else if(currentPlayer.getCostsResourcesLeft().get(buildResource) > currentPlayer.getCostsResourcesRight().get(buildResource)){
                    for(BuildResources buildResource2 : BuildResources.values()){
                        tempMap.put(buildResource2, 0);
                    }
                    tempMap.put(buildResource, element.get(buildResource));
                    for(int i = element.get(buildResource); i > 0; i--){
                        if(rightNeighbourSells.contains(tempMap)){
                            combinatedPrice += tempMap.get(buildResource) ;
                            break;
                        }else{
                            combinatedPrice += 2;
                            tempMap.put(buildResource,i - 1);
                        }
                    }
                }
            }
            if(combinatedPrice < cheapestPrice || cheapestPrice == 0){
                cheapestPrice = combinatedPrice;
            }
        }
        return cheapestPrice;
    }

    /**
     * Überprüft für 2 übergebene Resourcenmaps ob die 1. Map eine "Obermenge" der 2. Map ist
     * @param set	die Map für die überprüft werden soll ob sie die andere Map enthält
     * @param subset die Map die in set hineinpassen soll
     * @return true wenn subset in set hinein passt, sonst false
     */

    private boolean mapContainsMap (EnumMap<BuildResources,Integer> set, EnumMap<BuildResources,Integer> subset){
        for(BuildResources buildResource : BuildResources.values()){
            int posA = set.get(buildResource);
            int posB = subset.get(buildResource);
            if (posA - posB <0){
                return false;
            }
        }
        return true;
    }
    /**
     * Subtrahiert die Values der 2. Map von denen der 1.
     * @param mapA	die Map von der subtrahiert wird
     * @param mapB die Map die subtrahiert wird
     * @return difference die neu Map
     */

    private EnumMap<BuildResources,Integer> mapDifference (EnumMap<BuildResources,Integer> mapA, EnumMap<BuildResources,Integer> mapB){
        EnumMap<BuildResources,Integer> difference = new EnumMap<>(BuildResources.class);
        for(BuildResources buildResource : BuildResources.values()){
            int posA = mapA.get(buildResource);
            int posB = mapB.get(buildResource);
            difference.put(buildResource, (posA - posB));
        }
        return difference;
    }
    /**
     * Addiert die Values der 2. Map von denen der 1.
     * @param mapA	1. Map die addiert wird
     * @param mapB 2. Map die addiert wird
     * @return sum die neu Map
     */

    private EnumMap<BuildResources,Integer> mapAddition(EnumMap<BuildResources,Integer> mapA, EnumMap<BuildResources,Integer> mapB){
        EnumMap<BuildResources,Integer> sum = new EnumMap<>(BuildResources.class);
        for(BuildResources buildResource : BuildResources.values()){
            int posA = mapA.get(buildResource);
            int posB = mapB.get(buildResource);
            sum.put(buildResource, (posA + posB));
        }
        return sum;
    }
    /**
     * baut die newResource in das übergebene oldResources set ein
     * @param oldSet	produces oder sells set in das integriert werden soll
     * @param newResource die Resource die eingebaut werden soll
     * @return newSet das neue set
     */
    public HashSet<EnumMap<BuildResources,Integer>> integrateResource(HashSet<EnumMap<BuildResources,Integer>> oldSet, EnumMap<BuildResources,Integer> newResource){
        HashSet<EnumMap<BuildResources,Integer>> newSet = new HashSet<>(oldSet);
        EnumMap<BuildResources, Integer> tempMap;
        if(newResource.equals(emptyResource)){
            newSet.add(newResource.clone());
        }
        for (BuildResources buildResource : BuildResources.values()) {
            int res = newResource.get(buildResource);
            int eins=1;
                if ( res == eins) {
                    tempMap= emptyResource.clone();
                    tempMap.put(buildResource, 1);
                    newSet.add(tempMap.clone());
                    for (EnumMap<BuildResources, Integer> element : oldSet) {
                        newSet.add(mapAddition(element, tempMap.clone()));
                    }
                }
            }
        return newSet;
    }

}

//	public static void main(String[] args) {
//		//WonderController Test
//		Wonder testWonder = new Wonder("Test", WonderInfo.ALEXANDRIA, BuildResources.BRICK);
//		Wonder testWonderLeft = new Wonder("TestLeft", WonderInfo.RHODES, BuildResources.ORE);
//		Wonder testWonderRight = new Wonder("TestRight", WonderInfo.BABYLON, BuildResources.ORE);
//		testWonder.getNeighbours().add(testWonderLeft);
//		testWonder.getNeighbours().add(testWonderRight);
//		System.out.println("Neighbour size: "+ testWonder.getNeighbours().size());
//		WonderController wonderController = new WonderController(null);
//
//		testWonder.getCostsResourcesRight().put(BuildResources.ORE, 1);
//
//		EnumMap<BuildResources, Integer> mapA = new EnumMap<>(BuildResources.class);
//		EnumMap<BuildResources, Integer> mapB = new EnumMap<>(BuildResources.class);
//		for(BuildResources buildResource : BuildResources.values()){
//			mapA.put(buildResource,0);
//			mapB.put(buildResource,0);
//		}
//		mapA.put(BuildResources.BRICK, 1);
//		mapB.put(BuildResources.BRICK, 1);
//		mapB.put(BuildResources.ORE, 2);
//
//		testWonder.addCoins(3);
//
//		System.out.println("isAffordable brick,ore, ore: "+ wonderController.isAffordable(mapB, testWonder));
//
//		//End WonderController Test
//		launch(args);
//	}



//	//WonderController Test
//	Wonder testWonder = new Wonder(WonderInfo.ALEXANDRIA, BuildResources.BRICK);
//	Wonder testWonderLeft = new Wonder(WonderInfo.RHODES, BuildResources.ORE);
////Wonder testWonderRight = new Wonder(WonderInfo.BABYLON, BuildResources.ORE);
//		testWonder.getNeighbours().add(testWonderLeft);
//				//testWonder.getNeighbours().add(testWonderRight);
//				WonderController wonderController = new WonderController(null);
//
//				testWonder.getCostsResourcesRight().put(BuildResources.ORE, 1);
//
//				EnumMap<BuildResources, Integer> mapA = new EnumMap<>(BuildResources.class);
//		EnumMap<BuildResources, Integer> mapB = new EnumMap<>(BuildResources.class);
//		EnumMap<BuildResources, Integer> mapCoins = new EnumMap<>(BuildResources.class);
//		for(BuildResources buildResource : BuildResources.values()){
//		mapA.put(buildResource,0);
//		mapB.put(buildResource,0);
//		mapCoins.put(buildResource,0);
//		}
//		mapA.put(BuildResources.WOOD, 1);
//		mapB.put(BuildResources.BRICK, 1);
//		mapB.put(BuildResources.ORE, 1);
//
//		// mapCoins.put(BuildResources.COIN, 1);
//		testWonder.addCoins(1);
//
//		HashSet<EnumMap<BuildResources, Integer>> testSet = new HashSet<>();
//		testSet.add(mapA);
//		System.out.println("cheapestTrade(HashSet<EnumMap<BuildResources,Integer>> tradeCombinations, Wonder currentPlayer) "+ wonderController.cheapestTrade( testSet, testWonder));
////End WonderController Test