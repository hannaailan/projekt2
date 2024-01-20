package gui.view;

import java.io.File;
import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;

/**
 * The type New game view controller.
 */
public class NewGameViewController extends AnchorPane {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageNewGame;

    @FXML
    private Label labelAddPlayer;

    @FXML
    private Button buttonAdd;

    @FXML
    private TextField textFieldPlayername;

    @FXML
    private MenuButton menuButtonChooseType;

    @FXML
    private MenuItem menuItemKiEasy;

    @FXML
    private MenuItem menuItemKiMedium;

    @FXML
    private MenuItem menuItemKiHard;

    @FXML
    private MenuItem menuItemPlayer;

    @FXML
    private Label labelName1;

    @FXML
    private Label labelTyp1;

    @FXML
    private Label labelName2;

    @FXML
    private Label labelTyp2;

    @FXML
    private Label labelName3;

    @FXML
    private Label labelTyp3;

    @FXML
    private CheckBox rand;

    @FXML
    private Label labelName4;

    @FXML
    private Label labelTyp4;

    @FXML
    private Label labelName5;

    @FXML
    private Label labelTyp5;

    @FXML
    private Label labelName6;

    @FXML
    private Label labelTyp6;

    @FXML
    private Label labelName7;

    @FXML
    private Label labelTyp7;

    @FXML
    private Button buttonStartGame;

    @FXML
    private Button buttonRemovePlayer;

    @FXML
    private Button buttonCancel;

    private Main main;
    private int width, height;
    private ArrayList<Pair<Label, Label>> playerLabels;

    /**
     * Pair<Spielername::String, KITyp::Integer>, 0 = Menschlich, 1 = KI Easy, 2 = KI Medium, 3 = KI Hard
     */
    private LinkedList<Pair<String, Integer>> players;
    private MediaPlayer a;

    /**
     * Instantiates a new New game view controller.
     *
     * @param main   the main
     * @param width  the width
     * @param height the height
     */
    public NewGameViewController(Main main, int width, int height) {
        this.playerLabels = new ArrayList<Pair<Label, Label>>();
        this.players = new LinkedList<Pair<String, Integer>>();

        this.width = width;
        this.height = height;
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGameView.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            Media media = new Media(this.getClass().getResource("/gui/image/zwischen.mp3").toURI().toString());
            a = new MediaPlayer(media);
            a.setVolume(0.3);
            a.setAutoPlay(true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            this.setWidth(this.width);
            this.setHeight(this.height);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new player
     *
     * @param event the event
     */
    @FXML
    void add(ActionEvent event) {
            String name, typeName;
            int typeInt;
            typeName = this.menuButtonChooseType.getText();
            switch (typeName) {
                case "normaler Spieler":
                    typeInt = 0;
                    break;
                case "KI einfach":
                    typeInt = 1;
                    break;
                case "KI mittel":
                    typeInt = 2;
                    break;
                case "KI schwer":
                    typeInt = 3;
                    break;
                default:
                    typeInt = -1;
                    break;
            }
            if (typeInt == 0 && this.textFieldPlayername.getText().isBlank()) {
                Integer pos = this.players.size()+1;
                name = "name".concat(pos.toString());
                this.players.add(new Pair<>(name, typeInt));
            } else if (typeInt == 0){
                name = this.textFieldPlayername.getText();
                this.players.add(new Pair<>(name, typeInt));
            } else if (typeInt == 1) {
                Integer pos = this.players.size()+1;
                name = "";
                this.players.add(new Pair<>("KI einfach".concat(pos.toString()), typeInt));
            } else if (typeInt == 2) {
                Integer pos = this.players.size()+1;
                name = "";
                this.players.add(new Pair<>("KI mittel".concat(pos.toString()), typeInt));
            } else {
                Integer pos = this.players.size()+1;
                name = "";
                this.players.add(new Pair<>("KI schwer".concat(pos.toString()), typeInt));
            }
            showPlayerAt(this.players.size(), name, typeName);

            if (this.players.size() == 7) {
                this.buttonAdd.setDisable(true);
            }

            this.buttonRemovePlayer.setDisable(false);
            if (players.size() >= 2) {
                this.buttonStartGame.setDisable(false);
            }
            this.textFieldPlayername.clear();
    }

    /**
     * Cancel.
     *
     * @param event the event
     */
    @FXML
    void cancel(ActionEvent event) {

        a.stop();this.main.loadStartView();
    }

    /**
     * Choose ki easy.
     *
     * @param event the event
     */
    @FXML
    void chooseKiEasy(ActionEvent event) {
        this.menuButtonChooseType.setText("KI einfach");
        if (this.players.size() < 7) {
            this.buttonAdd.setDisable(false);
        }
    }

    /**
     * Choose ki medium.
     *
     * @param event the event
     */
    @FXML
    void chooseKiMedium(ActionEvent event) {
        this.menuButtonChooseType.setText("KI mittel");
        if (this.players.size() < 7) {
            this.buttonAdd.setDisable(false);
        }
    }

    /**
     * Choose ki hard.
     *
     * @param event the event
     */
    @FXML
    void chooseKiHard(ActionEvent event) {
        this.menuButtonChooseType.setText("KI schwer");
        if (this.players.size() < 7) {
            this.buttonAdd.setDisable(false);
        }
    }

    /**
     * Choose player.
     *
     * @param event the event
     */
    @FXML
    void choosePlayer(ActionEvent event) {
        this.menuButtonChooseType.setText("normaler Spieler");
        if (this.players.size() < 7) {
            this.buttonAdd.setDisable(false);
        }
    }

    /**
     * Remove player.
     *
     * @param event the event
     */
    @FXML
    void removePlayer(ActionEvent event) {
        removePlayerAt(this.players.size());
        this.players.removeLast();
        if (this.players.size() < 2) {
            this.buttonStartGame.setDisable(true);
        }
        if (this.players.size() == 0) {
            this.buttonRemovePlayer.setDisable(true);
        }
        this.buttonAdd.setDisable(false);
    }

    /**
     * Starts the game.
     *
     * @param event the event
     */
    @FXML
    void startGame(ActionEvent event) {
        if (players.size() >= 2) {
            Pair<String,Integer>[] arr = new Pair[this.players.size()];
            for(int i=0; i<arr.length; i++) {
                arr[i] = this.players.get(i);
            }
            try {
                if (this.main.getMainController().getMain().getGame()!=null)
                    this.main.getMainController().getiOController().saveGame();
                System.out.println("Random ist aktiviert? " + rand.isSelected());
                a.stop();
                this.main.getMainController().getSetupController().startGame(arr,rand.isSelected());
                this.main.loadBoardView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Shows the player at the given position
     *
     * @param i the position
     * @param name the name ofe the player
     * @param type the type of the player
     */
    private void showPlayerAt(Integer i, String name, String type) {
        Pair<Label, Label> pair = this.playerLabels.get(i-1);
        pair.getKey().setText(name);
        pair.getKey().setVisible(true);
        pair.getValue().setText(type);
        pair.getValue().setVisible(true);
    }

    /**
     * Removes the player at the given position
     *
     * @param i the position
     */
    private void removePlayerAt(Integer i) {
        Pair<Label, Label> pair = this.playerLabels.get(i-1);
        pair.getKey().setText("name".concat(i.toString()));
        pair.getKey().setVisible(false);
        pair.getValue().setText("typ".concat(i.toString()));
        pair.getValue().setVisible(false);
    }

    /**
     * Sets up the labels for the players
     */
    private void setupPlayerLabels() {
        this.playerLabels.add(new Pair(this.labelName1, this.labelTyp1));
        this.playerLabels.add(new Pair(this.labelName2, this.labelTyp2));
        this.playerLabels.add(new Pair(this.labelName3, this.labelTyp3));
        this.playerLabels.add(new Pair(this.labelName4, this.labelTyp4));
        this.playerLabels.add(new Pair(this.labelName5, this.labelTyp5));
        this.playerLabels.add(new Pair(this.labelName6, this.labelTyp6));
        this.playerLabels.add(new Pair(this.labelName7, this.labelTyp7));
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert imageNewGame != null : "fx:id=\"imageNewGame\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelAddPlayer != null : "fx:id=\"labelAddPlayer\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert buttonAdd != null : "fx:id=\"buttonAdd\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert textFieldPlayername != null : "fx:id=\"textFieldPlayername\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert menuButtonChooseType != null : "fx:id=\"menuButtonChooseType\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert menuItemKiEasy != null : "fx:id=\"menuItemKiEasy\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert menuItemPlayer != null : "fx:id=\"menuItemPlayer\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelName1 != null : "fx:id=\"labelName1\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelTyp1 != null : "fx:id=\"labelTyp1\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelName2 != null : "fx:id=\"labelName2\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelTyp2 != null : "fx:id=\"labelTyp2\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelName3 != null : "fx:id=\"labelName3\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelTyp3 != null : "fx:id=\"labelTyp3\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelName4 != null : "fx:id=\"labelName4\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelTyp4 != null : "fx:id=\"labelTyp4\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelName5 != null : "fx:id=\"labelName5\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelTyp5 != null : "fx:id=\"labelTyp5\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelName6 != null : "fx:id=\"labelName6\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelTyp6 != null : "fx:id=\"labelTyp6\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelName7 != null : "fx:id=\"labelName7\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert labelTyp7 != null : "fx:id=\"labelTyp7\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert buttonStartGame != null : "fx:id=\"buttonStartGame\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert buttonRemovePlayer != null : "fx:id=\"buttonRemovePlayer\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'NewGameView.fxml'.";
        this.imageNewGame.setFitWidth(this.width);
        this.imageNewGame.setFitHeight(this.height);
        setupPlayerLabels();
    }
}
