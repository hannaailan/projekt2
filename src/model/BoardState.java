package model;

import controller.SetupController;
import gui.view.BoardViewController;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * KLasse BoardState die Daten bereit, welche die Controller benötigen
 *
 * @author Mohamad Fistok , Tobias Jacob
 */
public class BoardState implements Serializable{

    /**
     * Speichert den aktuallen Zeitalter
     * 0 - bei initialisierung
     * 1 - 1 Zeitalter...
     */
    private int age=0;
    /**
     * Wonder des ersten Spielers
     */
    private Wonder firstPlayer;
    /**
     * Wonders des aktuallen Spielers
     */
    private Wonder currentPlayer;
    /**
     * default false,baut ein Spieler seinen Wunder aud,wird dann
     * auf true gesetzt.
     */
    private boolean halikarnassosThisTurn;
    /**
     *  Ablagestapel von Karten als Liste
     */
    private LinkedList<Card> discardStack;
    /**
     *  Spielern zum gehörigen Wonder als Liste
     */
    private LinkedList<Wonder> playerList;
    /**
     *  Aller Karte als Liste
     */
    private LinkedList<Card> [] cardList;

    /**
     * Konstruktor in Wonder Spielern und Karten gesetzt werden
     *
     * @param playerList übergebene PlayerList
     * @param cardList   übergebene CardList
     */
    public BoardState(LinkedList<Wonder> playerList, LinkedList<Card> [] cardList) {
        this.firstPlayer=playerList.getFirst();
        this.currentPlayer=firstPlayer;
        this.cardList=cardList;
        this.discardStack=new LinkedList<>();
        this.playerList=playerList;
    }

    /**
     * Gibt den Werte des Zeitalters zurück
     *
     * @return Age age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Set age.
     *
     * @param age the age
     */
    public void setAge(int age){this.age=age;}

    /**
     * erhöht der Zeitalter
     */
    public void increaseAge()
    {
        age++;
    }

    /**
     * Gibt Karten im Ablagestapel zurück
     *
     * @return DiscardStack LinkedList
     */
    public LinkedList<Card> getDiscardStack()
    {
        return discardStack;
    }

    /**
     * Gibt Karten zum gehörigen Zeitalter zurück
     *
     * @return CardList LinkedList
     */
    public LinkedList<Card> getCardList()
    {
        return cardList[age-1];
    }

    /**
     * Gibt Spielern zum gehörigen Wonder zurück
     *
     * @return PlayerList LinkedList
     */
    public LinkedList<Wonder> getPlayerList() {
        return playerList;
    }

    /**
     * Gibt der erste Spieler zurück
     *
     * @return firstPlayer Wonder
     */
    public Wonder getFistPlayer()
    {
        return firstPlayer;
    }

    /**
     * Gibt der aktualle Spieler zurück
     *
     * @return currentPlayer Wonder
     */
    public Wonder getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * setzt der erste Spieler
     *
     * @param pFirstPlayer the p first player
     */
    public void setFirstPlayer(Wonder pFirstPlayer)
    {
        this.firstPlayer=pFirstPlayer;
    }

    /**
     * setzt der Aktualle Spieler
     *
     * @param pCurrentPlayer Wonder
     */
    public void setCurrentPlayer(Wonder pCurrentPlayer)
    {
        this.currentPlayer=pCurrentPlayer;
        this.currentPlayer.setBoardState(this);
    }

    /**
     * setzt Halikarnassos auf true
     */
    public void activateHalikarnassos()
    {
        System.out.println("BoardState: Halikarnassos wurde auf true gesetzt");
        halikarnassosThisTurn=true;
    }

    /**
     * Gibt true zurück,wenn WeltWonder ausgebaut wurde
     *
     * @return halikarnassosThisTurn boolean
     */
    public boolean isHalikarnassosThisTurn(boolean daektivate)
    {
        Boolean temp = halikarnassosThisTurn;
        if (daektivate){
            halikarnassosThisTurn = false;
            if(!BoardViewController.IGNOREKITURN)
                System.out.println("BoardState: Halikarnassos wurde auf false gesetzt");
        }
        return temp;
    }


    /**
     *
     * @return ein Neues BoardGame
     */
    public BoardState clone(){

        LinkedList<Card>[] newCardList = new LinkedList[3];
        for(int index = 0; index < newCardList.length;index++)
            newCardList[index]=new LinkedList<>(cardList[index]);

        LinkedList<Wonder> newPlayerList = new LinkedList<>();
        for(Wonder player : playerList)
            newPlayerList.addLast(player.copy());



        BoardState newBoardState = new BoardState(newPlayerList,newCardList);

        if(halikarnassosThisTurn)
            newBoardState.activateHalikarnassos();

        for(Wonder player : newPlayerList)
            player.setBoardState(newBoardState);

        newBoardState.getDiscardStack().addAll(discardStack);
        newBoardState.setFirstPlayer(newPlayerList.get(playerList.indexOf(firstPlayer)));
        newBoardState.setCurrentPlayer(newPlayerList.get(playerList.indexOf(currentPlayer)));
        newBoardState.setAge(this.age);

        SetupController.setNeightbours(newPlayerList);

        return newBoardState;
    }

}
