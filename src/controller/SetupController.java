package controller;

import application.Main;
import gui.view.BoardViewController;
import javafx.util.Pair;
import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Random;

/**
 * The type Setup controller.
 *
 * @author Tobias Jacob
 */
public class SetupController {

    /**
     * Datein mit Daten fürs Tunier
     */
    String tournamentFile = null;
    /**
     * Datein mit Daten über Karten
     */
    InputStream dataFile = null;
    /**
     * der MainController
     */
    private MainController mainController;

    /**
     * Instantiates a new Setup controller.
     *
     * @param mainController the main controller
     */
    public SetupController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Gibt eine Zeile aus
     * @param line auszugebende Zeile
     */
    private void out(String line){
        if(!BoardViewController.IGNOREKITURN)System.out.println("Setup Controller: "+line);
        if(Main.pWriter!=null)Main.pWriter.println("Setup Controller: "+line);
    }
    /**
     * gibt eine Teile Liste der liste zurück. neue Liste : list[start,end[
     * list bleibt unverändert
     * @param list überggebene Liste
     * @param start Start der iiste
     * @param end Ende der Liste
     * @param <T> Typ
     * @return die neue Liste
     */
    public static <T extends Object> LinkedList<T> sublist(LinkedList<T> list, int start, int end){
        LinkedList<T> result = new LinkedList<>();
        for(int index = start;index<end;index++){
            result.addLast(list.get(index));
        }
        return result;
    }



    /**
     * mischt die übergebene Liste
     *
     * @param cardList the card list
     * @return cardList gemischte Kartenlistet
     */
    public static <T extends Object> LinkedList<T> shuffle(LinkedList<T> cardList) {
        int deckSize = cardList.size();

        T temp;
        int rndLoc;
        Random rndGen = new Random(System.currentTimeMillis());
        for (int iteration = 0; iteration < 5; iteration++) {
            for (int index = 0; index < deckSize; index++) {
                rndLoc = rndGen.nextInt(deckSize);
                temp = cardList.get(index);
                cardList.set(index, cardList.get(rndLoc));
                cardList.set(rndLoc, temp);
            }
        }
        return cardList;
    }

    /**
     * ließt Karten aus einer CSV datei ein
     *
     * @return List_Card_[]
     * Diese Exception wird geworfen, fallsdie Methode noch nicht implementiert ist.
     */
    private LinkedList<Card>[] createCardWithCSV() {

        out("Lade Karten aus "+tournamentFile);
        LinkedList<Card>[] decks = new LinkedList[3];
        decks[0] = new LinkedList<>();
        decks[1] = new LinkedList<>();
        decks[2] = new LinkedList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(tournamentFile));
            String zeile = bufferedReader.readLine();
            assert zeile.equals("age,card");
            while ((zeile = bufferedReader.readLine()) != null) {
                char chr = zeile.toCharArray()[0];
                char nill = '0';
                if ( chr == nill ) continue;
                String card = zeile.substring(2).toUpperCase();
                out("Neue Karte "+ card + " in Zeitalter "+ zeile.split(",",2)[0]);
                decks[Integer.parseInt(zeile.split(",",2)[0])-1].add(CardInfo.valueOf(card).buildCard());
            }
        } catch (FileNotFoundException e) {
            System.out.println("file " + tournamentFile.toString() + " not found");
        } catch (IOException e) {
            System.out.println("Lesefehler");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("fehlerhafter Dateiinhalt");
        }
        return decks;
    }

    /**
     * Erstellt die drei Karten stapel anhand der Spieler zahl
     *
     * @param playerAnz Spieleranzahl
     * @return List_Card_[]
     */
    private LinkedList<Card>[] createCard(int playerAnz) {
        dataFile = this.getClass().getResourceAsStream("/gui/saveddata/CardData.csv");
        out("Lade Karten aus "+ dataFile);
        int planz = playerAnz;
        int num = 2;
        if (planz == num) playerAnz = 3;
        assert playerAnz > 2;
        assert playerAnz < 8;
        LinkedList<Card>[] decks = new LinkedList[3];
        decks[0] = new LinkedList<>();
        decks[1] = new LinkedList<>();
        decks[2] = new LinkedList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataFile, StandardCharsets.UTF_8));
            String zeile = bufferedReader.readLine();
            assert zeile.equals("cardname,3,4,5,6,7,age");

            while (!(zeile = bufferedReader.readLine()).equals("END_OF_NORMAL_CARDS")) {

                String[] data = zeile.split(",");
                assert data.length == 7;

                for (int i = playerAnz - 2; i > 0; i--)
                    if (data[i].equals("1")) {
                        out("Neue Karte "+ data[0] + " in Zeitalter "+ data[6]);
                        decks[Integer.parseInt(data[6]) - 1].add(CardInfo.valueOf(data[0]).buildCard());
                    }
            }

            assert zeile.equals("END_OF_NORMAL_CARDS");
            LinkedList<Card> guildcards = new LinkedList<>();
            while ((zeile = bufferedReader.readLine()) != null && !zeile.equals(""))
                guildcards.add(CardInfo.valueOf(zeile).buildCard());

            assert guildcards.size() == 10;
            shuffle(guildcards);
            decks[2].addAll(guildcards.subList(0, playerAnz + 2));

        } catch (FileNotFoundException e) {
            System.out.println("file " + dataFile.toString() + " not found");
        } catch (IOException e) {
            System.out.println("Lesefehler");
        } catch (IllegalArgumentException e) {
            System.out.println("fehlerhafter Dateiinhalt");
        }

        for (LinkedList<Card> deck : decks)
            shuffle(deck);

        return decks;
    }

    /**
     * Erstellt wunder anhand einer CSV datei
     *
     * @return Wonder[]
     */
    private LinkedList<Wonder> createWonderWithCSV(){
        out("Lade Wunder aus "+ tournamentFile);
        LinkedList<Wonder> list = new LinkedList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(tournamentFile));
            String zeile = bufferedReader.readLine();
            assert zeile.equals("age,card");
            while ((zeile = bufferedReader.readLine()) != null && zeile != "" && zeile.toCharArray()[0] == '0') {
                String wonder = zeile.substring(2).toUpperCase();
                out("Neues Wunder "+wonder);
                list.add(WonderInfo.valueOf(wonder).buildBoard());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Lesefehler");
        } catch (IllegalArgumentException e) {
            System.out.println("fehlerhafter Dateiinhalt");
        }

        return list;

    }

    /**
     * erstellt Wunder anhand der Spieler anzahl
     *
     * @param playerCount spieleranzahl
     * @return Wonder[]
     */
    private LinkedList<Wonder> createWonder(int playerCount) {
        out("Lade "+ playerCount + " Wunder");
        if (playerCount > 7 | playerCount < 2)
            throw new IllegalArgumentException();
        LinkedList<Wonder> list = new LinkedList<>();
        for (WonderInfo wonderbuilder : WonderInfo.values()) {
            list.add(wonderbuilder.buildBoard());
        }
        shuffle(list);
        return sublist(list,0,playerCount);
    }

    /**
     * bennent die übergebenen Wunder
     * und definiert ihren Typ
     */
    private void nameWonders(LinkedList<Wonder> wonders, Pair<String, Integer>[] playerList) {
        assert wonders.size() == playerList.length;
        for (int index = 0; index < wonders.size(); index++) {
            wonders.get(index).setPlayerName(playerList[index].getKey());
            wonders.get(index).setPlayerType(playerList[index].getValue());
            wonders.get(index).addCoins(3);
        }
    }

    /**
     * setzt für jedes wunden den nachbarn
     * @param wonders Neue Wunderliste
     */
    public static void setNeightbours(LinkedList<Wonder> wonders){
        LinkedList<Wonder> temp;
        int size = wonders.size();
        int num = 2;
        if(size==num){
            temp = new LinkedList<>();
            temp.add(wonders.get(1));
            wonders.get(0).setNeighbours(temp);
            temp = new LinkedList<>();
            temp.add(wonders.get(0));
            wonders.get(1).setNeighbours(temp);
            return;
        }
        temp = new LinkedList<>();
        temp.addLast(wonders.get((1)%wonders.size()));//rechter
        temp.add(wonders.getLast());//linker
        wonders.getFirst().setNeighbours(temp);
        for(int index=1;index<wonders.size();index++){
            temp = new LinkedList<>();
            temp.addLast(wonders.get((index+1)%wonders.size()));//rechter
            temp.add(wonders.get((index-1)));//linker
            wonders.get(index).setNeighbours(temp);
        }

    }

    /**
     * Startet das Ki Tunier
     *
     * @param playerList the player list
     * @param selectedCsv the selected csv
     */
    public void startTournament(Pair<String, Integer>[] playerList, String selectedCsv) {
        tournamentFile = selectedCsv;
        out("Erstelle Tunier");
        LinkedList<Card>[] decks = createCardWithCSV();
        LinkedList<Wonder> wonders = createWonderWithCSV();
        nameWonders(wonders, playerList);
        initiateGame(decks,wonders);
    }

    /**
     * Startet ein normales Spiel
     *
     * @param playerList the player list
     */
    public void startGame(Pair<String, Integer>[] playerList,boolean randomize) {
        out("Erstelle Spiel");
        Pair<String, Integer>[] pair = playerList;
        LinkedList<Card>[] decks = createCard(pair.length);
        LinkedList<Wonder> wonders = createWonder(playerList.length);
        nameWonders(wonders, pair);
        if(randomize) {
            shuffle(wonders);
        }
        initiateGame(decks,wonders);
    }

    private void initiateGame(LinkedList<Card>[] decks,LinkedList<Wonder> wonders){

        setNeightbours(wonders);

        out("Zusammenfassung :");
        out("Spieler : "+wonders.size());
        for(Wonder wonder : wonders)
            out(wonder.getPlayerName()+"\t "+wonder.getWonderName()+"\t "+wonder.getPlayerType());

        out("Kartenstapel :");
        for(LinkedList<Card> list : decks)
            out(list.size()+ " Karten");

        Game game = new Game();
        BoardState boardState = new BoardState(wonders,decks);
        game.addNewBoardState(boardState);

        mainController.getMain().setGame(game);
        mainController.getBoardController().startAge();
    }
}
