package gui.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import application.Main;
import controller.BoardController;
import controller.WonderController;
import gui.aui.BoardAUI;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import model.BoardState;
import model.Card;
import model.Wonder;

/**
 * The type Board view controller.
 */
public class BoardViewController extends BorderPane implements BoardAUI {

    /**
     * #
     * falls true werden konsolen ausgaben minimiert und ki-züge dierekt bestätigt
     */
    public  static boolean IGNOREKITURN=false;
    @FXML
    private ResourceBundle resources;

    @FXML
    private ImageView imageViewAge;

    @FXML
    private CheckBox toggleKIignore;
    @FXML
    private URL location;

    @FXML
    private VBox vBoxLeft;

    @FXML
    private HBox hBoxMenu;

    @FXML
    private MenuItem menuButtonCancelGame;

    @FXML
    private MenuItem menuButtonShowTip;

    @FXML
    private MenuItem menuButtonRedo;

    @FXML
    private MenuItem menuButtonUndo;

    @FXML
    private MenuItem menuButtonSaveGame;

    @FXML
    private MenuItem menuItemShowDiscardStack;

    @FXML
    private VBox vBoxRight;

    @FXML
    private HBox hBoxBottom;

    @FXML
    private Label boardInfo;

    private Main main;
    private int width, height;
    private LinkedList<Wonder> wonders;
    private MediaPlayer a;

    /**
     * Instantiates a new Board view controller.
     *
     * @param main   the main
     * @param width  the width
     * @param height the height
     */
    public BoardViewController(Main main, int width, int height) {
        IGNOREKITURN=false;
        this.width = width;
        this.height = height;
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardView.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            Media media = new Media(this.getClass().getResource("/gui/image/bass.mp3").toURI().toString());
            a = new MediaPlayer(media);
            a.setVolume(0.4);
            a.setAutoPlay(true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        a.setOnEndOfMedia(new Runnable() {
            public void run() {
                a.seek(javafx.util.Duration.ZERO);
            }
        });
        a.play();

        try {
            this.setWidth(this.width);
            this.setHeight(this.height);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshBoard() {
        this.wonders = this.main.getMainController().getBoardController().getPlayerList();

        int age = this.main.getMainController().getBoardController().getAge();
        switch (age) {
            case 1: this.imageViewAge.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/build1.png"))); break;
            case 2: this.imageViewAge.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/build2.png"))); break;
            case 3: case 4: this.imageViewAge.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/build3.png"))); break;
            default: out("Falsches Zeitalter. Zeitalter: " + age); break;
        }

        if(age<4)
            boardInfo.setText("F\u00fcr mehr Infos, die Fl\u00e4chen auf den Wunder anklicken");
        else
            boardInfo.setText("Spiel wurde bereits beendet. Zur\u00fcck mit Spiel abbrechen");
        this.imageViewAge.setFitWidth(50);
        this.imageViewAge.setFitHeight(50);

        this.vBoxLeft.getChildren().clear();
        this.vBoxLeft.getChildren().clear();
        this.hBoxBottom.getChildren().clear();
        this.hBoxBottom.getChildren().clear();
        this.hBoxBottom.getChildren().clear();
        this.vBoxRight.getChildren().clear();
        this.vBoxRight.getChildren().clear();
        this.hBoxMenu.getChildren().clear();

        WonderViewController wonderViewController0;
        WonderViewController wonderViewController1;
        WonderViewController wonderViewController2;
        WonderViewController wonderViewController3;
        WonderViewController wonderViewController4;
        WonderViewController wonderViewController5;
        WonderViewController wonderViewController6;

        toggleKIignore.setSelected(IGNOREKITURN);

        int amountPlayers = this.wonders.size();

        switch (amountPlayers) {
            case 2:
                wonderViewController0 = new WonderViewController(this, this.wonders.get(0), this.width, this.height);
                wonderViewController1 = new WonderViewController(this, this.wonders.get(1), this.width, this.height);
                this.vBoxLeft.getChildren().add(wonderViewController0);
                this.vBoxLeft.setAlignment(Pos.CENTER);
                this.vBoxRight.getChildren().add(wonderViewController1);
                this.vBoxRight.setAlignment(Pos.CENTER);
                break;
            case 3:
                wonderViewController0 = new WonderViewController(this, this.wonders.get(0), this.width, this.height);
                wonderViewController1 = new WonderViewController(this, this.wonders.get(1), this.width, this.height);
                wonderViewController2 = new WonderViewController(this, this.wonders.get(2), this.width, this.height);
                this.vBoxLeft.getChildren().add(wonderViewController0);
                this.vBoxLeft.setAlignment(Pos.CENTER);
                this.hBoxBottom.getChildren().add(wonderViewController2);
                this.vBoxRight.getChildren().add(wonderViewController1);
                this.vBoxRight.setAlignment(Pos.CENTER);
                break;
            case 4:
                wonderViewController0 = new WonderViewController(this, this.wonders.get(0), this.width, this.height);
                wonderViewController1 = new WonderViewController(this, this.wonders.get(1), this.width, this.height);
                wonderViewController2 = new WonderViewController(this, this.wonders.get(2), this.width, this.height);
                wonderViewController3 = new WonderViewController(this, this.wonders.get(3), this.width, this.height);
                this.vBoxLeft.getChildren().add(wonderViewController0);
                this.vBoxLeft.getChildren().add(wonderViewController3);
                this.vBoxLeft.setAlignment(Pos.CENTER);
                this.vBoxRight.getChildren().add(wonderViewController1);
                this.vBoxRight.getChildren().add(wonderViewController2);
                this.vBoxRight.setAlignment(Pos.CENTER);
                break;
            case 5:
                wonderViewController0 = new WonderViewController(this, this.wonders.get(0), this.width, this.height);
                wonderViewController1 = new WonderViewController(this, this.wonders.get(1), this.width, this.height);
                wonderViewController2 = new WonderViewController(this, this.wonders.get(2), this.width, this.height);
                wonderViewController3 = new WonderViewController(this, this.wonders.get(3), this.width, this.height);
                wonderViewController4 = new WonderViewController(this, this.wonders.get(4), this.width, this.height);
                this.vBoxLeft.getChildren().add(wonderViewController0);
                this.vBoxLeft.getChildren().add(wonderViewController4);
                this.hBoxBottom.getChildren().add(wonderViewController3);
                this.vBoxRight.getChildren().add(wonderViewController1);
                this.vBoxRight.getChildren().add(wonderViewController2);
                break;
            case 6:
                wonderViewController0 = new WonderViewController(this, this.wonders.get(0), this.width, this.height);
                wonderViewController1 = new WonderViewController(this, this.wonders.get(1), this.width, this.height);
                wonderViewController2 = new WonderViewController(this, this.wonders.get(2), this.width, this.height);
                wonderViewController3 = new WonderViewController(this, this.wonders.get(3), this.width, this.height);
                wonderViewController4 = new WonderViewController(this, this.wonders.get(4), this.width, this.height);
                wonderViewController5 = new WonderViewController(this, this.wonders.get(5), this.width, this.height);
                this.vBoxLeft.getChildren().add(wonderViewController0);
                this.vBoxLeft.getChildren().add(wonderViewController5);
                this.hBoxBottom.getChildren().add(wonderViewController4);
                this.hBoxBottom.getChildren().add(wonderViewController3);
                this.vBoxRight.getChildren().add(wonderViewController1);
                this.vBoxRight.getChildren().add(wonderViewController2);
                break;
            case 7:
                wonderViewController0 = new WonderViewController(this, this.wonders.get(0), this.width, this.height);
                wonderViewController1 = new WonderViewController(this, this.wonders.get(1), this.width, this.height);
                wonderViewController2 = new WonderViewController(this, this.wonders.get(2), this.width, this.height);
                wonderViewController3 = new WonderViewController(this, this.wonders.get(3), this.width, this.height);
                wonderViewController4 = new WonderViewController(this, this.wonders.get(4), this.width, this.height);
                wonderViewController5 = new WonderViewController(this, this.wonders.get(5), this.width, this.height);
                wonderViewController6 = new WonderViewController(this, this.wonders.get(6), this.width, this.height);
                this.vBoxLeft.getChildren().add(wonderViewController0);
                this.vBoxLeft.getChildren().add(wonderViewController6);
                this.hBoxBottom.getChildren().add(wonderViewController5);
                this.hBoxBottom.getChildren().add(wonderViewController4);
                this.hBoxBottom.getChildren().add(wonderViewController3);
                this.vBoxRight.getChildren().add(wonderViewController1);
                this.vBoxRight.getChildren().add(wonderViewController2);
                break;
            default: throw new IllegalArgumentException("Anzahl Spieler ist nicht korrekt. Anzahl Spieler: " + amountPlayers);
        }

        if (age == 4) {
            return;
        }
        Wonder currentPlayer = this.main.getMainController().getBoardController().getCurrentPlayer();
        if (currentPlayer.getSelctedCard() == null) {
            if(currentPlayer.getPlayerType()>0){
                kichoseCard(currentPlayer);
            }
            else {

                CardViewController cardViewController = new CardViewController(this, currentPlayer.getPlayerHand(), currentPlayer);
                this.setCenter(cardViewController);
            }

        } else {
            if(currentPlayer.getPlayerType()>0){
                kiuseCard(currentPlayer);
            }
            else {

                CardOptionsViewController cardOptionsViewController = new CardOptionsViewController(this, currentPlayer.getSelctedCard(), currentPlayer);
                this.setCenter(cardOptionsViewController);

            }

        }
    }

    /**
     * Cancel game.
     *
     * @param event the event
     */
    @FXML
    void cancelGame(ActionEvent event) {

        a.stop();this.main.loadEndGameView();
    }


    /**
     * Redo.
     *
     * @param event the event
     */
    @FXML
    void redo(ActionEvent event) {
        if(this.main.getMainController().getBoardController().redo()) {
            wonders = this.main.getMainController().getBoardController().getPlayerList();
            refreshBoard();
            boardInfo.setText("redo erfolgreich");
        }
        else
            boardInfo.setText("weiter nach vorne gehts nicht");
    }

    /**
     * Undo.
     *
     * @param event the event
     */
    @FXML
    void undo(ActionEvent event) {
        if(this.main.getMainController().getBoardController().undo()){
            wonders = this.main.getMainController().getBoardController().getPlayerList();
            refreshBoard();
            boardInfo.setText("undo erfolgreich");
        }
        else
            boardInfo.setText("Du bist dereits am Spielanfang");
    }

    /**
     * Save game.
     *
     * @param event the event
     */
    @FXML
    void saveGame(ActionEvent event) {
        boardInfo.setText("Spiel gespeichert! Spiel wurde da abgelegt wo auch die .jar-Datei ist");
        this.main.getMainController().getiOController().saveGame();
    }

    /**
     * Show tip.
     *
     * @param event the event
     */
    @FXML
    void showTip(ActionEvent event) {

        BoardState currentBS = main.getMainController().getMain().getGame().getCurrentBoardState();
        Wonder activePlayer = main.getMainController().getBoardController().getCurrentPlayer();
        activePlayer.disableHighscore();
        Card selectedCard = activePlayer.getSelctedCard();
        if (selectedCard != null) {
            String text = this.main.getMainController().showHint(currentBS, activePlayer, selectedCard);
            Label label = new Label(text);
            label.setFont(new Font(20));
            label.setStyle("-fx-text-fill: white;");
            this.hBoxMenu.getChildren().clear();
            this.hBoxMenu.getChildren().add(label);
        } else {
            String text = this.main.getMainController().showHint(currentBS, activePlayer);
            Label label = new Label(text);
            label.setFont(new Font(20));
            label.setStyle("-fx-text-fill: white;");
            this.hBoxMenu.getChildren().clear();
            this.hBoxMenu.getChildren().add(label);
        }
    }

    /**
     * Show Discard Stack
     *
     * @param event the event
     */
    @FXML
    void showDiscardStack(ActionEvent event) {
        DiscardStackViewController discardStackViewController =
                new DiscardStackViewController(this, this.getMain().getMainController().getBoardController().getDiscardstack(), false, null, "");
        this.setCenter(discardStackViewController);
    }

    /**
     * Load card options view.
     *
     * @param card   the image of the card
     * @param wonder the wonder from the card Optiones
     */
    public void loadCardOptionsView(Card card, Wonder wonder) {
        CardOptionsViewController cardOptionsViewController = new CardOptionsViewController(this, card, wonder);
        assert cardOptionsViewController != null;
        this.setCenter(cardOptionsViewController);
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert vBoxLeft != null : "fx:id=\"vBoxLeft\" was not injected: check your FXML file 'BoardView.fxml'.";
        assert menuButtonCancelGame != null : "fx:id=\"menuButtonCancelGame\" was not injected: check your FXML file 'BoardView.fxml'.";
        assert vBoxRight != null : "fx:id=\"vBoxRight\" was not injected: check your FXML file 'BoardView.fxml'.";
        assert hBoxBottom != null : "fx:id=\"hBoxBottom\" was not injected: check your FXML file 'BoardView.fxml'.";
        refreshBoard();
    }

    /**
     * Next player.
     */
    public void nextPlayer() {
        this.main.getMainController().getBoardController().nextPlayer();
        refreshBoard();
    }

    /**
     * Entfernt die Karte aus der Playerhand vom Wonder und merkt sich die Karte als bestätigt
     *
     * @param card   the card
     * @param wonder the wonder
     */
    public void confirmCard(Card card, Wonder wonder) {
        BoardController boardController = getMain().getMainController().getBoardController();
        assert card != null;
        //out( wonder.getPlayerName() + " wählt " + card.getName().toString()+"-Karte");
        int index = wonders.indexOf(wonder);
        if(wonder.equals(boardController.getFirstPlayer())){
            boardController.startTurn();
            wonders = boardController.getPlayerList();
        }
        wonder = wonders.get(index);
        wonder.setSelctedCard(card);
        wonder.getPlayerHand().remove(card);
        nextPlayer();
    }

    /**
     * Remove confirmed card.
     *
     * @param wonder the wonder
     */
    public void removeConfirmedCard(Wonder wonder) {
        BoardController boardController = this.getMain().getMainController().getBoardController();
        Wonder lastPlayer = boardController.getFirstPlayer().getNeighbours().getLast();
        out(wonder.getPlayerName()+" benutzte "+wonder.getSelctedCard().getName().name()+"-Karte");
        wonder.setSelctedCard(null);
        if (lastPlayer == wonder) {
            if (wonder.getPlayerHand().size() == 1) {
                out("Überprüfe Halikarnassos bei endAge");
                if (this.main.getMainController().getMain().getGame().getCurrentBoardState().isHalikarnassosThisTurn(true)) {
                    doHalicanassos("endAge");
                } else {
                    endAge();
                }
            } else {
                out("Überprüfe Halikarnassos bei endRound");
                if (this.main.getMainController().getMain().getGame().getCurrentBoardState().isHalikarnassosThisTurn(true)) {
                    doHalicanassos("endRound");
                } else {
                    endRound();
                }
            }
        } else {
            nextPlayer();
        }
    }

    /**
     * End round.
     */
    public void endRound() {
        BoardController boardController = this.main.getMainController().getBoardController();
        boardController.cycleCards();
        boardController.endTurn();
        refreshBoard();
    }

    /**
     * End age.
     */
    public void endAge() {
        BoardController boardController = this.main.getMainController().getBoardController();
        boardController.endAge();
        if (boardController.getAge() < 3) {
            boardController.endTurn();
            boardController.startAge();
            refreshBoard();
        } else {
            boardController.endGame();
            this.main.loadEndGameView();

        }
    }

    private void doHalicanassos(String type){
        Wonder player = getPlayerWithHalikarnassos();
        out(player.getPlayerName()+" kann den Halikanossoseffect nutzen");
        out(player.getPlayerName()+" kann aus folgenden Karten wählen:");
        for(Card card : this.getMain().getMainController().getBoardController().getDiscardstack())
            out(card.getName().name());
        if(player.getPlayerType()>0){
            LocalDateTime start = LocalDateTime.now();
            Card selectedCard = main.getMainController().getaIController().selectCardHarlikanassos(player,main.getMainController().getMain().getGame().getCurrentBoardState());
            Duration timeNeeded = Duration.between(start,LocalDateTime.now());
            out(player.getPlayerName()+" benötigte "+timeNeeded.getSeconds()+ "s Zeit zur Kartenwahl");
            out(player.getPlayerName()+" wählt die Karte:" +selectedCard.getName().name());
            KIDiscardStackViewController kiDiscardStackViewController = new KIDiscardStackViewController(this, this.getMain().getMainController().getBoardController().getDiscardstack(), true, player, type,selectedCard);
            this.setCenter(kiDiscardStackViewController);
            if(IGNOREKITURN) {
                Service<Void> service = new Service<Void>() {
                    @Override
                    protected Task<Void> createTask() {
                        return new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                //Background work
                                final CountDownLatch latch = new CountDownLatch(1);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            kiDiscardStackViewController.confirm.fire();
                                        } finally {
                                            latch.countDown();
                                        }
                                    }
                                });
                                latch.await();
                                //Keep with the background work
                                return null;
                            }
                        };
                    }
                };
                service.start();
            }
        }
        else {
            DiscardStackViewController discardStackViewController =
                    new DiscardStackViewController(this, this.getMain().getMainController().getBoardController().getDiscardstack(), true, player, type);
            assert discardStackViewController != null;
            this.setCenter(discardStackViewController);
        }
    }



    private Wonder getPlayerWithHalikarnassos() {
        for (Wonder wonder : this.wonders){
            if (wonder.getWonderName().toString().equals("HALICARNASSUS")){
                return wonder;
            }
        }
        return null;
    }

    private  void kichoseCard(Wonder currentPlayer){
        out(currentPlayer.getPlayerName()+" wählt eine Karte");
        LocalDateTime start = LocalDateTime.now();
        Card selectedCard = main.getMainController().getaIController().selectCard(currentPlayer,main.getMainController().getMain().getGame().getCurrentBoardState());
        Duration timeNeeded = Duration.between(start,LocalDateTime.now());
        out(currentPlayer.getPlayerName()+" benötigte "+timeNeeded.getSeconds()+ "s Zeit zur Kartenwahl");
        out(currentPlayer.getPlayerName()+" wählt die Karte: "+selectedCard.getName().name());
        KICardViewController kiCardViewController = new KICardViewController(this,currentPlayer.getPlayerHand(),selectedCard,currentPlayer);
        this.setCenter(kiCardViewController);
        if(IGNOREKITURN) {
            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            //Background work
                            final CountDownLatch latch = new CountDownLatch(1);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        kiCardViewController.buttonConfirm.fire();
                                    }finally{
                                        latch.countDown();
                                    }
                                }
                            });
                            latch.await();
                            //Keep with the background work
                            return null;
                        }
                    };
                }
            };
            service.start();
        }

    }

    private void kiuseCard(Wonder currentPlayer){
        out(currentPlayer.getPlayerName()+" benutzt die Karte: "+currentPlayer.getSelctedCard().getName().name());
        LocalDateTime start = LocalDateTime.now();
        String selectedOption = main.getMainController().getaIController().playCard(currentPlayer, main.getMainController().getMain().getGame().getCurrentBoardState(),currentPlayer.getSelctedCard());
        Duration timeNeeded = Duration.between(start,LocalDateTime.now());
        out(currentPlayer.getPlayerName()+" benötigte "+timeNeeded.getSeconds()+ "s Zeit zur Kartennutzung");
        out(currentPlayer.getPlayerName()+" wählt "+selectedOption);
        WonderController wonderController = main.getMainController().getWonderController();
        KICardOptionsView kiCardOptionsView = new KICardOptionsView(this, currentPlayer.getSelctedCard(), currentPlayer,selectedOption);
        this.setCenter(kiCardOptionsView);
        if(IGNOREKITURN) {
            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            //Background work
                            final CountDownLatch latch = new CountDownLatch(1);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        kiCardOptionsView.confirm();
                                    }finally{
                                        latch.countDown();
                                    }
                                }
                            });
                            latch.await();
                            //Keep with the background work
                            return null;
                        }
                    };
                }
            };
            service.start();
        }
    }

    /**
     * Toggle ingnore ki turn.
     */
    public void toggleIngnoreKiTurn(){

        IGNOREKITURN=!IGNOREKITURN;
    }
    /**
     * Sorgt für geortnete Konsolenausgabe
     */
    private void out(String line){
        if(!IGNOREKITURN)System.out.println("BoardViewController: "+line);
        Main.pWriter.println("BoardView Controller: "+line);
    }

    //private void alwasOut(String line){System.out.println("BoardViewController: "+line);}

    /**
     * Get main.
     *
     * @return the main
     */
    public Main getMain(){ return this.main; }
}

