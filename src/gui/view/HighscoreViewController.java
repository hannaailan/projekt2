package gui.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Main;
import gui.aui.HighscoreAUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Highscore;

/**
 * The type Highscore view controller.
 */
public class HighscoreViewController extends AnchorPane implements HighscoreAUI {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageHighscores;

    @FXML
    private TableView<Highscore> tableHighscores;

    @FXML
    private TableColumn<Highscore, Integer> tableColumnPosition;

    @FXML
    private TableColumn<Highscore, String> tableColumnName;

    @FXML
    private TableColumn<Highscore, Integer> tableColumnPoints;

    @FXML
    private Button buttonBack;
    private MediaPlayer a;

    private Main main;
    private int width, height;
    private ObservableList<Highscore> highscoreObservableList;

    /**
     * Instantiates a new Highscore view controller.
     *
     * @param main   the main
     * @param width  the width
     * @param height the height
     */
    public HighscoreViewController(Main main, int width, int height) {
        this.width = width;
        this.height = height;
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HighscoreView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            Media media = new Media(this.getClass().getResource("/gui/image/zwischen.mp3").toURI().toString());
            a = new MediaPlayer(media);
            a.setVolume(0.4);
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

    @Override
    public void refreshHighscore() {
        this.tableHighscores.refresh();
    }

    /**
     * Back.
     *
     * @param event the event
     */
    @FXML
    void back(ActionEvent event) {

        a.stop();main.loadStartView();
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert tableHighscores != null : "fx:id=\"tableHighscores\" was not injected: check your FXML file 'HighscoreView.fxml'.";
        assert buttonBack != null : "fx:id=\"buttonBack\" was not injected: check your FXML file 'HighscoreView.fxml'.";
        this.imageHighscores.setFitWidth(this.width);
        this.imageHighscores.setFitHeight(this.height);
        initializeTableViewHighscores();
        this.tableHighscores.setItems(this.highscoreObservableList);
    }

    /**
     * Initializes the tableView
     */
    private void initializeTableViewHighscores() {
        LinkedList<Highscore> highscores = this.main.getMainController().getMain().getHighscores();
        this.tableColumnName.setCellValueFactory(new PropertyValueFactory<Highscore, String>("name"));
        this.tableColumnPoints.setCellValueFactory(new PropertyValueFactory<Highscore, Integer>("points"));
        this.tableColumnPosition.setCellValueFactory(new PropertyValueFactory<Highscore, Integer>("position"));
        this.highscoreObservableList = FXCollections.observableList(highscores);
    }
}
