package gui.view;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The type Start view controller.
 */
public class StartViewController extends AnchorPane {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageStartView;

    @FXML
    private Button buttonNewGame;

    @FXML
    private Button buttonLoadGame;

    @FXML
    private Button buttonShowHighscores;

    @FXML
    private Button buttonContinueGame;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonStartAiTournament;

    @FXML
    private HBox hbox;


    private Main main;
    private int width;
    private int height;
    private MediaPlayer a;

    /**
     * Instantiates a new Start view controller.
     *
     * @param main   the main
     * @param width  the width
     * @param height the height
     */
    public StartViewController(Main main, int width, int height) {
        this.width = width;
        this.height = height;
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartView.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            Media media = new Media(this.getClass().getResource("/gui/image/anfang.mp3").toURI().toString());
            a = new MediaPlayer(media);
            a.play();
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
     * Exit.
     *
     * @param event the event
     */
    @FXML
    void exit(ActionEvent event) {
        this.main.closeStage();
    }

    /**
     * Continue game.
     *
     * @param event the event
     */
    @FXML
    void continueGame(ActionEvent event) {
        try {
            a.stop();
            this.main.loadBoardView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Start ai tournament.
     *
     * @param event the event
     */
    @FXML
    void startAiTournament(ActionEvent event) {
        a.stop();
    this.main.loadNewTournamentView();
    }

    /**
     * Load game.
     *
     * @param event the event
     */
    @FXML
    void loadGame(ActionEvent event) {
        try {
            a.stop();
            this.main.loadLoadGameView();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * New game.
     *
     * @param event the event
     */
    @FXML
    void newGame(ActionEvent event) {

        a.stop();
        this.main.loadNewGameView();
    }

    /**
     * Show highscores.
     *
     * @param event the event
     */
    @FXML
    void showHighscores(ActionEvent event) {

        a.stop();
        main.loadHighscoreView();
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert imageStartView != null : "fx:id=\"imageStartView\" was not injected: check your FXML file 'StartView.fxml'.";
        assert buttonNewGame != null : "fx:id=\"buttonNewGame\" was not injected: check your FXML file 'StartView.fxml'.";
        assert buttonContinueGame != null : "fx:id=\"buttonContinueGame\" was not injected: check your FXML file 'StartView.fxml'.";
        assert buttonLoadGame != null : "fx:id=\"buttonLoadGame\" was not injected: check your FXML file 'StartView.fxml'.";
        assert buttonShowHighscores != null : "fx:id=\"buttonShowHighscores\" was not injected: check your FXML file 'StartView.fxml'.";
        assert buttonExit != null : "fx:id=\"buttonExit\" was not injected: check your FXML file 'StartView.fxml'.";
        this.imageStartView.setFitWidth(this.width);
        this.imageStartView.setFitHeight(this.height);
        if (this.main.getMainController().getMain().getGame() != null) {
            this.buttonContinueGame.setDisable(false);
        }
        if (this.width != 1920) {
            this.hbox.setSpacing(50);
        }

    }
}

