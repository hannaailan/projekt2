package controller;

import application.Main;
import gui.aui.BoardAUI;
import gui.view.BoardViewController;
import model.*;

import java.util.Random;
import java.util.LinkedList;
import java.util.Stack;


/**
 * Klasee zun Verwalten vin Spieler,Karten
 *
 * @author Tobias Jacob
 */
public class BoardController {

    /**
 	 *  Maincontroller der alle anderen Controller kennt
 	 */
    private MainController mainController;


    /**
     * Konstruktor, welcher beim erstellen eines neuen Wunders den Maincontroller setzt.
     *
     * @param mainController übergebener Mainkontroller
     */
    public BoardController(MainController mainController) {
        this.mainController=mainController;
    }

    /**
     * Ausgabe einer Zeile
     * @param line auszugebende Zeile
     */
    private void out(String line){
        if(!BoardViewController.IGNOREKITURN)
            System.out.println("Board Controller: "+line);
        if(Main.pWriter!=null)Main.pWriter.println("Board Controller: "+line);
    }

    /**
     * geht eine Runde zurück
     * Spielern wird der Highscore verwehrt
     */
    public boolean undo() {
        Game game = mainController.getMain().getGame();
        int num = 1;
        if(game.getGameHistory().size() <= num) {
            out("undo fehlgeschlagen. Wir sind bereits am Spielanfang");
            return false;
        }
        game.getRedo().push(
                game.getGameHistory().pop());

        out("undo erfolgreich");
        for(Wonder player : game.getCurrentBoardState().getPlayerList())
            player.disableHighscore();
        return true;
    }

    /**
     * setzt den Dpielstatus auf einen neueren Stand zurück,
     *  falls möglich
     * sollte nach einem undo eine weitere Runde gespielt worden sein passiert hier nichts
     *
     */
    public boolean redo() {
        Game game = mainController.getMain().getGame();
        if(game.getRedo().empty()) {
            out("redo fehlgeschlagen");
            return false;
        }
        game.getGameHistory().push(
                game.getRedo().pop());
        out("redo erfolgreich");
        return true;
        //boardAUI.refreshBoard();
    }

    /**
     * Spielerhänder werden an den endsprechenden Nachbarn übergeben
     * Age 1,3 Rechtsrum , im Uhrzeigersinn
     * Age 2 Linkssrum
     */
    public void cycleCards()
    {
        Wonder firstPlayer = mainController.getMain().getGame().getCurrentBoardState().getFistPlayer();
        LinkedList<Card> saveHand = firstPlayer.getPlayerHand();
        Wonder player=firstPlayer;
        int num1 = 1;
        int num2 = 2;
        if(mainController.getMain().getGame().getCurrentBoardState().getAge() % num2 == num1) {
            out("Karten linksherum drehen");
            while (!player.getNeighbours().getLast().equals(firstPlayer)) { // Karten Linksherum

                player.setPlayerHand(player.getNeighbours().getLast().getPlayerHand());//erhalte Hand vom Rechten nachbar
                player = player.getNeighbours().getLast();
            }
        }
        else {
            out("Karten rechtsherum drehen");
            while (!player.getNeighbours().getFirst().equals(firstPlayer)) { // Karten Rechtsrum

                player.setPlayerHand(player.getNeighbours().getFirst().getPlayerHand());//erhalte Hand vom Linken nachbar
                player = player.getNeighbours().getFirst();
            }
        }
        player.setPlayerHand(saveHand);
        //startTurn();
    }

    /**
     * setzt neuen Startspieler
     * erhöht zeitalter
     * setzt neue Spielerkarten
     */
    public void startAge() {
        BoardState boardState = mainController.getMain().getGame().getCurrentBoardState();

        boardState.increaseAge();
        out("Neues Zeitalter: "+boardState.getAge());
        out("Startspieler: "+boardState.getFistPlayer().getPlayerName());

        LinkedList<Card> carddeck = boardState.getCardList();
        int index = 0;
        for(Wonder player : boardState.getPlayerList()){
            player.setPlayerHand( SetupController.sublist(carddeck,index*7,((index++)+1)*7));
        }
        boardState.getDiscardStack().addAll(SetupController.sublist(carddeck,index*7,carddeck.size()));
        out("start age konfiguration abgeschlossen");
    }


    /**
     * Nicht verwendete Karten werden auf den Ablagestapel gelegt
     * Die Hände aller Spieler werden geleert
     * Konflikte werden ausgetragen
     *  Siegpunkte werden dierekt vergeben
     * @return halikanossos möglich
     */
    public boolean endAge() {
        BoardState boardState = mainController.getMain().getGame().getCurrentBoardState();
        out("Beende Zeitalter: "+boardState.getAge());
        for (Wonder player: boardState.getPlayerList()) {
            boardState.getDiscardStack().add(player.getPlayerHand().getFirst());
            player.setPlayerHand(null);

            for(Wonder neighbour : player.getNeighbours()){
                if(neighbour.getShields()>player.getShields()){
                    out(player.getPlayerName()+","+player.getShields()+" greift "+neighbour.getPlayerName()+","+neighbour.getShields()+" an. Niederlage! (-1)");
                    player.addCMMinus(1);
                    player.addVPs(-1);
                }
                else if (neighbour.getShields()<player.getShields()){
                    out(player.getPlayerName()+","+player.getShields()+" greift "+neighbour.getPlayerName()+","+neighbour.getShields()+" an. Sieg! (+"+(boardState.getAge()*2-1)+")");
                    player.addCMPlus((boardState.getAge()*2)-1);
                    player.addVPs((boardState.getAge()*2)-1);
                }
                else
                    out(player.getPlayerName()+","+player.getShields()+" greift "+neighbour.getPlayerName()+","+neighbour.getShields()+" an. Unentschieden! (+0)");
            }
        }

        return boardState.isHalikarnassosThisTurn(true);
    }


    /**
     * Calculate points int [ ].
     * 0-Militär
     * 1-Münzen
     * 2-Wunderstages
     * 3-Profanbauten
     * 4-Forschungssymbole
     * 5-Handelskarten
     * 6-Gilden
     * 7-Gesammt
     *
     *mach mal nur nach end Age
     * @return Array mit details jewils Spieler (Coins,Schilde,...)
     */
    public LinkedList<int[]> calculatePoints()
    {
        BoardState boardState = mainController.getMain().getGame().getCurrentBoardState();
        LinkedList<int[]> result = new LinkedList<>();

        for( Wonder player : boardState.getPlayerList()){
            int[] vparr = new int[8];
            result.addLast(vparr);
            vparr[0]=player.getCMPlus()-player.getCMMinus();
            vparr[1]=player.getCoins();
            vparr[2]=player.getColorVP().get(CardColor.BROWN);
            vparr[3]=player.getColorVP().get(CardColor.BLUE);
            vparr[4]=calculateScience(player);
            vparr[5]=player.getColorVP().get(CardColor.YELLOW);
            vparr[6]=player.getColorVP().get(CardColor.VIOLET);
            vparr[7]=calculatePoints(player);
        }
        return result;
    }

    /**
     *
     * @param wonder
     * @return
     */
    private int calculateScience(Wonder wonder){
        int points=0;
        points+=(int) Math.pow(wonder.getCompasses(),2);
        points+=(int) Math.pow(wonder.getTablets(),2);
        points+=(int) Math.pow(wonder.getGears(),2);
        points+=Math.min(wonder.getGears(),Math.min(wonder.getTablets(),wonder.getCompasses()))*7;

        return points;
    }

    /**
     *
     * @param wonder
     * @return
     */
    public int calculatePoints(Wonder wonder){
        int points = wonder.getVPs();
        points+=wonder.getCoins()/3;

        points+=calculateScience(wonder);

        return points;
    }

    /**
     * Setzt den neuen Aktuellen Spieler auf den rechten Nachbar des Aktuellen Spielers
     * verläuft also immer im Uhrzeigersinn(UZ)
     */
    public void nextPlayer(){
        BoardState boardState = mainController.getMain().getGame().getCurrentBoardState();
        boardState.setCurrentPlayer(boardState.getCurrentPlayer().getNeighbours().getFirst());
        out("Neuer aktueller Spieler: "+boardState.getCurrentPlayer().getPlayerName());
    }


    /**
     * erstellt ein neues BoardState bsierent auf dem Aktuellen BoardState
     * der neue BoardState wird zum aktuellen BoardState
     */
    public void startTurn(){

        Game game = mainController.getMain().getGame();
        game.addNewBoardState(game.getCurrentBoardState().clone());
        out("Gespeichert!");
    }

    /**
     * starte eine neue Runde indem der Startspielermarker an die rechte person vom aktuellen Spieler gereicht wird
     * und setzt den aktiven Spieler auf den Startspieler
     */
    public void endTurn(){
        BoardState boardState = mainController.getMain().getGame().getCurrentBoardState();

        boardState.setFirstPlayer(boardState.getFistPlayer().getNeighbours().getFirst());
        boardState.setCurrentPlayer(boardState.getFistPlayer());
    }

    /**
     * Beendet ein Spiel
     * aktiviert alle AfterGameEffecte aller Wonder/Spieler
     *
     */
    public void endGame(){
        out("Beende Spiel");
        BoardState boardState = mainController.getMain().getGame().getCurrentBoardState();
        for(Wonder player : boardState.getPlayerList())
            for(AfterGameEffect effect : player.getAfterGameEffects())
                effect.afterGameEffect(player);

        mainController.getHighscoreController().addToHighscores(boardState.getPlayerList());
        mainController.getMain().getGame().getCurrentBoardState().increaseAge();
    }

    /**
     * Get player list linked list.
     * @return the linked list
     */
    public LinkedList<Wonder> getPlayerList(){
        return  mainController.getMain().getGame().getCurrentBoardState().getPlayerList();
    }

    /**
     * gibt den aktuellen Spieler zurück
     * @return der spieler
     */
    public  Wonder getCurrentPlayer() {
        return mainController.getMain().getGame().getCurrentBoardState().getCurrentPlayer();
    }

    /**
     * gibt das aktuelle Zeitalter des aktuellen Spiels zurück
     * @return das Zeitalte
     */
    public int getAge() {
        return mainController.getMain().getGame().getCurrentBoardState().getAge();
    }

    /**
     * Gibt den ersten Spieler zurück
     * @return Wonder firstPlayer
     */
    public Wonder getFirstPlayer() {
        return mainController.getMain().getGame().getCurrentBoardState().getFistPlayer();
    }

    /**
     * gets the discardstack
     * @return the discardStack
     */
    public LinkedList<Card> getDiscardstack(){
        return mainController.getMain().getGame().getCurrentBoardState().getDiscardStack();
    }
}
