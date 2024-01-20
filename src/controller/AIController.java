package controller;

import javafx.util.Pair;
import model.*;
import application.Main;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Daniel Nemitz, Malte Sichtermann
 */
public class AIController {
	/**
	 * debug mode
	 */
	private final boolean debug=false;

	/**
	 * MainController
	 */
	private final MainController mainController;

    /**
 	 * BoardController
 	 */
    private final BoardController boardController;

    /**
 	 * WonderController
 	 */
    private final WonderController wonderController;


	/**
	 * Instanziiert AIController
	 * @param mainController übergebener MainController
	 */
	public AIController(MainController mainController) {
		this.mainController = mainController;
		this.boardController = this.mainController.getBoardController();
		this.wonderController = this.mainController.getWonderController();
    }

    /**
 	 * Spielt die KI Karte
	 *
 	 * @param board übergebener Boardstate
 	 * @param player übergebener Spieler
 	 * @param card übergebene Karte
	 *
	 * @return String Aktion
 	 */
    public String playCard(Wonder player, BoardState board, Card card) {
    	if(debug){
			System.out.println("playCard wird aufgerufen");
		}
    	String action = "discard";
		switch(player.getPlayerType()){
			case 1: action = playCardRandomly(player,card);
				break;
			case 2: action = playCardRandomly(player,card);
				break;
			case 3: int possible = possibleOptions(player,card);
				switch (possible) {
					// build only
					case 1:
						action = "build";
						break;
					//expand only
					case 2:
						action = "expand";
						break;
					//build or expand
					case 3:
						action = buildOrExpand(player, board, card);
						break;
					//free only
					case 4:
						action = "free";
						break;
					//free or build
					case 5:
						action = freeOrBuild(player, board, card);
						break;
					//free or expand
					case 6:
						action = freeOrExpand(player, board, card);
						break;
					//free or build or expand
					case 7:
						action = freeOrBuildOrExpand(player, board, card);
						break;

				}
					break;
			default:action = playCardRandomly(player,card);
				break;
		}
		return action;
    }

	/**
	 * entscheided zwischen bauen oder erweitern
	 * @param player der aktuelle Spieler
	 * @param board das aktuelle Board
	 * @param card die aktuelle Karte
	 * @return die Aktion zum ausführen
	 */
	private String buildOrExpand(Wonder player, BoardState board, Card card){
		LinkedList<Card> cardList = new LinkedList<>();
		cardList.add(card);
		cardList.add(stageToCard(player.getStages()[player.getCurrentStage()]));
		Pair<LinkedList<Card>, LinkedList<Integer>> test = calculateOptionRanking(player,board, cardList);
		System.out.print("\u001B[31m");
		System.out.println("test.getKey().size(): "+ test.getKey().size());
		for(Card rankedCard : test.getKey()){
			System.out.println("best.getName(): "+ rankedCard.getName());
		}
		System.out.print("\u001B[0m");
		Card best = test.getKey().getFirst();

		if(best.getName() == null){
			System.out.print("\u001B[33m");
			System.out.println("expand ");
			System.out.print("\u001B[0m");
			return "expand";
		}
		System.out.println("BuildOrExpand build");
		return "build";
	}
	/**
	 * entscheidet zwischen frei bauen oder bauen
	 * @param player der aktuelle Spieler
	 * @param board das aktuelle Board
	 * @param card die aktuelle Karte
	 * @return die Aktion zum ausführen
	 */
	private String freeOrBuild(Wonder player, BoardState board, Card card){

		if(player.getZeusEffectAvailable()[board.getAge()-1]){
			if(selectTrade(player,card,wonderController.calculateAllResourceOptions(player,card.getCosts().clone())) == new Pair<>(0,0)){
				return "build";
			}else{
				out(player.getPlayerName() +" hat den Zeuseffekt verwendet");
				System.out.print("\u001B[33m");
				System.out.println("Zeus verwendet");
				System.out.println( "\u001B[0m");
				player.disableZeus(board.getAge());
				return "free";
			}
		}else {
			return "free";
		}
	}
	/**
	 * entscheidet zwischen frei bauen oder erweitern
	 * @param player der aktuelle Spieler
	 * @param board das aktuelle Board
	 * @param card die aktuelle Karte
	 * @return die Aktion zum ausführen
	 */
	private String freeOrExpand(Wonder player, BoardState board, Card card){
		if(player.getZeusEffectAvailable()[board.getAge()-1]){
			LinkedList<Card> cardList = new LinkedList<>();
			cardList.add(card);
			cardList.add(stageToCard(player.getStages()[player.getCurrentStage()]));
			Pair<LinkedList<Card>, LinkedList<Integer>> test = calculateOptionRanking(player,board, cardList);
			Card best = test.getKey().getFirst();
			if(best.getName() == null){
				return "expand";
			}

			out(player.getPlayerName() +" hat den Zeuseffekt verwendet");
			System.out.print("\u001B[33m");
			System.out.println("Zeus verwendet");
			System.out.println( "\u001B[0m");
			player.disableZeus(board.getAge());
			System.out.println("freeOrExpand free");
			return "free";
		}
		else{
			LinkedList<Card> cardList = new LinkedList<>();
			cardList.add(card);
			cardList.add(stageToCard(player.getStages()[player.getCurrentStage()]));
			Pair<LinkedList<Card>, LinkedList<Integer>> test = calculateOptionRanking(player,board, cardList);
			Card best = test.getKey().getFirst();
			if(best.getName() == null){
				return "expand";
			}
			System.out.println("freeOrExpand free 2");
			return "free";
		}
	}
	/**
	 * entscheidet zwischen frei bauen oder bauen oder erweitern
	 * @param player der aktuelle Spieler
	 * @param board das aktuelle Board
	 * @param card die aktuelle Karte
	 * @return die Aktion zum ausführen
	 */
	private String freeOrBuildOrExpand(Wonder player, BoardState board, Card card){
		System.out.println("freeOrBuildOrExpand");
		LinkedList<Card> cardList = new LinkedList<>();
		cardList.add(card);
		cardList.add(stageToCard(player.getStages()[player.getCurrentStage()]));
		Pair<LinkedList<Card>, LinkedList<Integer>> test = calculateOptionRanking(player,board, cardList);
		Card best = test.getKey().getFirst();
		if(best.getName() == null){
			System.out.println("freeOrBuildOrExpand expand");
			return "expand";
		}
		else {
			return freeOrBuild(player, board, card);
		}
	}

	/**
	 * Wählt die KI Karte
	 *
	 * @param player übergebener Spieler
	 * @param board übergenener BoardState
	 *
	 * @return gewählte Karte
	 */
	public Card selectCard(Wonder player, BoardState board) {
		if(debug){
			System.out.println("selectedCard wird aufgerufen");
		}
		Card selected;
		switch(player.getPlayerType()){
			case 1: selected = selectRandomCard(player, player.getPlayerHand());
					break;
			case 2: LinkedList<Card> playable = calculatePlayableCards(player,board);
					if(playable.isEmpty()){
						playable = player.getPlayerHand();
					}
					selected = selectRandomCard(player, playable);
					if (selected.getName()==null){
						playable.remove(selected);
						if(playable.isEmpty()){
							playable = player.getPlayerHand();
						}
						selected = selectRandomCard(player, playable);
					}
					break;
			case 3: playable = calculatePlayableCards(player,board);
					selected = chooseOptionByDifficulty(player,board,calculateOptionRanking(player,board, playable));
					break;
			default:selected = selectRandomCard(player, player.getPlayerHand());
					break;
		}
		if(selected.getName() != null) {
			return selected;
		}
		return null;
	}

	/**
	 * Wählt die KI Karte für Halikarnassos
	 *
	 * @param player übergebener Spieler
	 * @param board übergenener BoardState
	 *
	 * @return gewählte Karte
	 */
	public Card selectCardHarlikanassos(Wonder player, BoardState board) {
		if(debug){
			System.out.println("selectedCard wird aufgerufen");
		}
		Card selected;
		switch(player.getPlayerType()){

			case 1:
				selected = selectRandomCard(player, board.getDiscardStack());
				break;

			case 2:
				selected = selectRandomCard(player, board.getDiscardStack());
				break;
			case 3: selected = chooseOptionByDifficulty(player,board,calculateOptionRanking(player,board, boardController.getDiscardstack()));
				break;

			default:
				selected = selectRandomCard(player, board.getDiscardStack());
				break;
		}
		if(selected.getName() != null) {
			return selected;
		}
		return null;
	}

    /**
 	 * Berechnet Liste sortiert je nach Zeitalter:
	 * 	1. Zeitalter - Resourcen bevorzugen
	 * 	2.-3. Zeitalter - Resourcen/ nach den Karten die im qktuellen Zug die meisten VP geben
 	 *
 	 * @param boardState übergebener Boardstate
 	 * @param player übergebener Spieler
 	 * @return CardList
 	 */
    private Pair<LinkedList<Card>,LinkedList<Integer>> calculateOptionRanking(Wonder player, BoardState boardState, LinkedList<Card> playable) {
		if(debug){
			System.out.println("calculateOptionRanking wird aufgerufen");
		}

        LinkedList<Card> sortedByVP = new LinkedList<>();
        LinkedList<Integer> actualVP =  new LinkedList<>();
		if(debug){
			System.out.println("calculateOptionRanking pre first if");
		}
    	if(playable.size() > 0){
			if(debug){
				System.out.println("calculateOptionRanking first if");
			}
			for (Card card : playable){
				if(debug){
					System.out.println("for card: "+ card + " playable: "+ playable);
					System.out.println("calculateOptionRanking for");
				}
				Wonder copyOld = player.copy();
				Wonder copyNew = player.copy();
				int vpAdvantage;
				if(card.getName()!=null) {
					if (card.getName().equals(CardInfo.FORUM)) {
						vpAdvantage = 100;
					}else if (card.getColor().equals(CardColor.BROWN)) {
						vpAdvantage = 5;
					} else if (card.getColor().equals(CardColor.GREY)) {
						vpAdvantage = 3;
					} else if (card.getColor().equals(CardColor.RED)) {
						wonderController.createBuilding(card, copyNew);
						testAfterGameEffects(copyOld);
						testAfterGameEffects(copyNew);
						vpAdvantage = boardController.calculatePoints(copyNew) - boardController.calculatePoints(copyOld) + advantageByConflicts(copyOld, copyNew);
					} else {
						wonderController.createBuilding(card, copyNew);
						testAfterGameEffects(copyOld);
						testAfterGameEffects(copyNew);
						vpAdvantage = boardController.calculatePoints(copyNew) - boardController.calculatePoints(copyOld);
					}
				}else {
					if(copyOld.getWonderName().name() == "HALICARNASSUS" && copyOld.getCurrentStage() == 1){
						
						Pair<LinkedList<Card>, LinkedList<Integer>> ranking = calculateOptionRanking(player, boardState, boardState.getDiscardStack());
						vpAdvantage = ranking.getValue().getFirst();

					}else {
						wonderController.createBuilding(card, copyNew);
						testAfterGameEffects(copyOld);
						testAfterGameEffects(copyNew);
						vpAdvantage = boardController.calculatePoints(copyNew) - boardController.calculatePoints(copyOld);
						//Stages besser bewerten

						vpAdvantage +=1;
					}
				}
				if(actualVP.size() == 0){
					actualVP.add(vpAdvantage);
					sortedByVP.add(card);
				}else{
					for(int i = 0; i < actualVP.size(); i++) {

						if(debug){
							System.out.println("calculateOptionRanking for 2");
							System.out.println("if1 index: "+ i +" actualVP size: "+actualVP.size() +" sortedByVP size: "+ sortedByVP.size());
						}
						if(actualVP.get(i) < vpAdvantage){
							if(debug) {
								System.out.println("if2 card: " + card + "index: " + i + " actualVP size: " + actualVP.size() + " sortedByVP size: " + sortedByVP.size());
							}
							actualVP.add(i, vpAdvantage);
							sortedByVP.add(i,card);
							break;
						}else if(i == actualVP.size()-1){
							if(debug) {
								System.out.println("else if card: " + card + "index: " + i + " actualVP size: " + actualVP.size() + " sortedByVP size: " + sortedByVP.size());
							}
							actualVP.addLast(vpAdvantage);
							sortedByVP.add(i,card);
							break;
						}
					}
				}

			}
			if(debug){
				System.out.println("calculateOptionRanking return pair");
			}
			return new Pair<>(sortedByVP,actualVP);
		}
		if(debug){
			System.out.println("calculateOptionRanking return null");
		}
    	return null;
    }

    private void testAfterGameEffects (Wonder player){
    	for(AfterGameEffect effect : player.getAfterGameEffects()){
    		effect.afterGameEffect(player);
		}
	}

	/**
	 * Gibt zurück wie viel mehr/weniger Siegpunkte durch Militärkartenbau erzielt werden können
	 * @param original Wunder vor ausbau der Karte
	 * @param modified Wunder nach Ausbau der Karte
	 * @return differenz zwischen alten und neuen VP aus Militär
	 */
	private int advantageByConflicts(Wonder original, Wonder modified){
		if(debug){
			System.out.println("advantageByConflicts wird aufgerufen");
		}
    	int combatVPOriginal = 0;
    	int combatVPModified = 0;
    	int ageCombatVP = mainController.getMain().getGame().getCurrentBoardState().getAge() * 2 - 1;
		//TODO if(original.getNeighbours().size() == 1): return - Wert verdoppeln,
		// Grundsätzlich + 1 ausgeben
		// Wenn Schilde eh schon viel zu viel, dann nicht
    	for (int i = 0; i < original.getNeighbours().size(); i++) {
				if (original.getNeighbours().get(i).getShields() > original.getShields()) {
					combatVPOriginal -= 1;
				}
				if (modified.getNeighbours().get(i).getShields() > modified.getShields()) {
					combatVPModified -= 1;
				}
				if (original.getNeighbours().get(i).getShields() < original.getShields()) {
					combatVPOriginal += ageCombatVP;
				}
				if (modified.getNeighbours().get(i).getShields() < modified.getShields()) {
					combatVPModified += ageCombatVP;
				}
			}
    	return combatVPModified - combatVPOriginal;
	}

    /**
 	 * Wählt Option aus Liste Nach KI Schwierigkeit (1 Leicht, 2 Mittel, 3 Schwer)
 	 *
	 * @param player übergebener Spieler
	 * @param boardState übergebener Boardstate
	 * @param options übergebene Liste
 	 * @return int
 	 */
    private Card chooseOptionByDifficulty(Wonder player, BoardState boardState, Pair<LinkedList<Card>,LinkedList<Integer>> options) {
		if(debug){
			System.out.println("chooseOptionByDifficulty wird aufgerufen");
		}
    	int indexOfOption = 0;
    	if(options != null) {
			int size = options.getKey().size();
			switch (player.getPlayerType()) {
				case 1: if (size >= 2) {
							indexOfOption = ThreadLocalRandom.current().nextInt(1, size);
						} else indexOfOption = 0;
						break;
				case 2: if (size >= 3) {
							indexOfOption = ThreadLocalRandom.current().nextInt(0, 100);
							if (indexOfOption < 25){indexOfOption = 0;}
							else if (indexOfOption < 75){indexOfOption = 1;}
							else{indexOfOption = 2;}
						} else indexOfOption = ThreadLocalRandom.current().nextInt(0, size);
						break;
				case 3: indexOfOption = 0;
						break;
				default:break;
			}
			if(options.getKey().get(indexOfOption).getName() != null) {
				return options.getKey().get(indexOfOption);
			}else{
				return selectCardToDiscard(player.getPlayerHand(),boardState, player);
			}
		}else{
    		return selectCardToDiscard(player.getPlayerHand(),boardState, player);
		}
    }

	/**
	 * Wählt geschickt eine Karte zum Wegwerfen aus, priorisiert darauf dem Gegner zu schaden
	 * @param cards Karten des Spielers
	 * @param boardState Aktueller Status des Boards
	 * @param player Aktueller Spieler
	 * @return Kartenliste
	 */
    private Card selectCardToDiscard(LinkedList<Card> cards, BoardState boardState, Wonder player){
		if(debug){
			System.out.println("selectCardToDiscard wird aufgerufen");
		}
    	LinkedList<Card> options = new LinkedList<>();
    	for(Card card : cards) {
			if(freeForOthers(card,boardState,player)){
				options.add(card);
			}
		}
    	if(options.size() > 0) {
			return options.get(cardWithHighestImpact(options,boardState,player));
		}
    	else {
			return cards.get((cardWithHighestImpact(options, boardState, player)));
		}
	}

	/**
	 * Berechnet die Karte, welche beim Gegner den größten vorteiligen Effekt auf das Spiel hat
	 * @param options Liste welche infrage kommende Karten beinhaltet
	 * @param boardState Aktueller Status des Boards
	 * @param wonder Aktueller Spieler
	 * @return int
	 */
	private int cardWithHighestImpact(LinkedList<Card> options, BoardState boardState, Wonder wonder){
		if(debug){
			System.out.println("cardWithHighestImpact wird aufgerufen");
		}
		int index = 0;
		int highestVP = 0;
    	for(int i = 0; i < options.size(); i++) {
    		for(Wonder player : boardState.getPlayerList()){
				if(wonder != player){
					Wonder copy = player.copy();
					wonderController.createBuilding(options.get(i),copy);
					int vpGain = boardController.calculatePoints(copy) - boardController.calculatePoints(player);
					if(vpGain > highestVP){
						highestVP = vpGain;
						index = i;
					}
				}
			}
		}
		return index;
	}

	/**
	 * Berechnet ob eine Karte von Gegnern kostenlos (durch Effekte oder eigene Resourcen) errichtet werden kann
	 * @param fromHand fragliche Karte
	 * @param boardState Aktueller Status des Boards
	 * @param wonder Aktueller Spieler
	 * @return true falls ja, false sonst
	 */
	private boolean freeForOthers(Card fromHand, BoardState boardState,Wonder wonder){
		if(debug){
			System.out.println("freeForOthers wird aufgerufen");
		}
		boolean freeForOthers = false;
		EnumMap<BuildResources,Integer> costs = fromHand.getCosts().clone();
		for (Wonder player : boardState.getPlayerList()) {
			if((wonder != player) && (wonderController.isFree(player,fromHand) || player.getProduces().contains(costs))){
				freeForOthers = true;
				break;
			}
		}
		return freeForOthers;
	}

	/**
	 * Wandelt eine Stage in eine Dummy Karte um
	 * @param stage umzuwandelnde Stage
	 * @return Dummy Karte
	 */
	private Card stageToCard(Stage stage){
		if(debug){
			System.out.println("stageToCard wird aufgerufen");
		}
		Card card = new Card(null, stage.getCosts().clone(), null,null,null,stage.getStageEffect(),null);
		if(debug) {
			if (card.getName() == null) {
				System.out.println("OOPS");
			} else {
				System.out.println("if ist ok");
			}
			System.out.println("stageToCard: " + card);
		}
		return card;
	}

    /**
 	 * Berechnet tatsächlich Spielbare Karten des Spielers (abgesehen von Discard)
 	 *
	 * @param player übergebener Spieler
	 * @param boardState übergebener Boardstate
 	 * @return List_Card_
 	 *
 	 */
    private LinkedList<Card> calculatePlayableCards(Wonder player, BoardState boardState) {
		if(debug){
			System.out.println("calculatePlayableCards wird aufgerufen");
		}
        LinkedList<Card> playable = new LinkedList<>();
        Card stage = stageToCard(player.getStages()[player.getCurrentStage()]);
		if(wonderController.isAffordable(stage.getCosts(), player)){
			playable.add(stage);
		}
        for (Card card : player.getPlayerHand()){
        	if(wonderController.isFree(player,card) || wonderController.isAffordable(card.getCosts(), player)){
        		if(!wonderController.isBuiltAlready(card, player)){
					playable.add(card);
				}
			}
		}
		if(debug){
			System.out.println("calculatePlayableCards returned");
		}
    	return playable;
    }

    /**
 	 * Zufällige Karte wählen
	 *
	 * @param player übergebener Spieler
	 * @return Karte
 	 */
    private Card selectRandomCard(Wonder player, LinkedList<Card> cards) {
		if(debug){
			System.out.println("selectRandomCard wird aufgerufen");
		}
        int size = cards.size();
        int random;
        if (size > 0) {
			random = ThreadLocalRandom.current().nextInt(0, size);
			return cards.get(random);
		} else {
        	throw new IllegalArgumentException("Liste hat keine Elemente");
		}
    }

	/**
	 * Gibt den günstigsten Handel zurück
	 * @param options Liste der Handelsoptionen
	 * @return Günstigstes Element der Liste
	 */
	public Pair<Integer,Integer> selectTrade(Wonder player, Card selectedCard, LinkedList<Pair<Integer,Integer>> options){
		if(debug){
			System.out.println("selectTrade wird aufgerufen");
			System.out.println("options: "+ options);
		}
		Pair<Integer,Integer> cheapest;
		if(wonderController.isFree(player, selectedCard)){
			cheapest = new Pair<>(0,0);
		}
		else {
			cheapest = options.getFirst();
			for (Pair<Integer, Integer> element : options) {
				if ((cheapest.getKey() + cheapest.getValue()) > (element.getKey() + element.getValue())) {
					if (debug) {
						System.out.println("if cheapest: " + cheapest + " element: " + element);
					}
					cheapest = element;
				}
			}
		}
		if(debug){
			System.out.println("cheapest: "+ cheapest);
		}

		return cheapest;
	}

	/**
	 * Zufällige Aktion mit gewählter Karte ausführen
	 *
	 * @param player übergebener Spieler
	 * @param card übergebene Karte
	 * @return String der Aktion die ausgeführt wurde (discard,build oder expand)
	 */
	//TODO: free einbauen
	private String playCardRandomly(Wonder player, Card card) {
		if(debug){
			System.out.println("playCardRandomly wird aufgerufen");
		}
		int possible = possibleOptions(player,card);
		int random;
		switch (possible){
			case 1: random = ThreadLocalRandom.current().nextInt(1, 2);
					if (random == 0){
						return "discard";
					}
					else{
						return "build";
					}

			case 2: random = ThreadLocalRandom.current().nextInt(1, 2);
					if (random == 0){
						return "discard";
					}
					else{
						return "expand";
					}


			case 3: random = ThreadLocalRandom.current().nextInt(0, 2);
				switch (random){
						case 1: return "build";
						case 2: return "expand";
						default:return "discard";
					}

			case 4: player.disableZeus(boardController.getAge());
					return "free";

			case 5: random = ThreadLocalRandom.current().nextInt(0, 2);
				switch (random) {

					case 1: player.disableZeus(boardController.getAge());
						    return "free";
					case 2:
						return "build";
					default:
						return "discard";
				}
			case 6:random = ThreadLocalRandom.current().nextInt(0, 2);
				switch (random){

					case 1: player.disableZeus(boardController.getAge());
							return "free";
					case 2: return "stage";
					default:return "discard";
				}
			case 7:random = ThreadLocalRandom.current().nextInt(0, 2);
				switch (random){
					case 1: return "build";
					case 2: return "stage";
					default:return "discard";
				}
			default: return "discard";
		}

    }

	/**
	 * (Verkaufen immer möglich) 0 = nur Verkauf, 1 = Karte bauen, 2 = Stage ausbauen, 3 = Karte und Stage möglich, 4 = free, 5= free+bauen, 6=free +stage, 7=free+bauen+stage
	 *
	 * @param player übergebener Spieler
	 * @param card übergebene Karte
	 * @return possibleOptions, entsprechend befüllte Ganzzahl
	 */
	private int possibleOptions(Wonder player, Card card){
		if(debug){
			System.out.println("possibleOptions wird aufgerufen");
		}
		int possibleOptions = 0;
		if (wonderController.isAffordable(card.getCosts(),player)) {
			possibleOptions +=1;
		}
		if(wonderController.isFree(player, card)){
			possibleOptions +=4;
		}
		int currentStage = player.getCurrentStage();
		if (currentStage < 2 && wonderController.isAffordable(player.getStages()[currentStage].getCosts(), player)) {
			possibleOptions += 2;
		}
    	return possibleOptions;
	}

	/**
	 * in die log schreiben
	 * @param line auszugebener string
	 */
	private void out(String line){

		Main.pWriter.println("BoardView Controller: "+line);
	}
}
