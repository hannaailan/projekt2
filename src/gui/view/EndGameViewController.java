package gui.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Highscore;
import model.Wonder;

/**
 * The type End game view controller.
 */
public class EndGameViewController extends AnchorPane {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonEnd;

    @FXML
    private TableView<Highscore> tableViewHighscores;

    @FXML
    private TableColumn<Highscore, Integer> tableColumnPosition;

    @FXML
    private TableColumn<Highscore, String> tableColumnName;

    @FXML
    private TableColumn<Highscore, Integer> tableColumnPoints;

    @FXML
    private GridPane gridPaneScores;

    @FXML
    private ImageView imageBackground;

    private MediaPlayer a;
    private Main main;
    private int width, height;
    private LinkedList<Wonder> wonders;
    private ObservableList<Highscore> highscoreObservableList;

    /**
     * Instantiates a new End game view controller.
     *
     * @param main    the main
     * @param width   the width
     * @param height  the height
     * @param wonders the wonders
     */
    public EndGameViewController(Main main, int width, int height, LinkedList<Wonder> wonders) {
        assert wonders.size() >= 2;
        this.width = width;
        this.height = height;
        this.main = main;
        this.wonders = wonders;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EndGameView.fxml"));
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

    /**
     * Sorgt für geortnete Konsolenausgabe
     */
    private void out(String line){
        System.out.println("EndGameViewController: "+line);
    }

    /**
     * End.
     *
     * @param event the event
     */
    @FXML
    void end(ActionEvent event) {
        a.stop();

        this.main.loadStartView();
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert imageBackground != null : "fx:id=\"imageBackground\" was not injected: check your FXML file 'EndGameView.fxml'.";
        assert tableViewHighscores != null : "fx:id=\"tableViewHighscores\" was not injected: check your FXML file 'EndGameView.fxml'.";
        assert tableColumnPosition != null : "fx:id=\"tableColumnPosition\" was not injected: check your FXML file 'EndGameView.fxml'.";
        assert tableColumnName != null : "fx:id=\"tableColumnName\" was not injected: check your FXML file 'EndGameView.fxml'.";
        assert tableColumnPoints != null : "fx:id=\"tableColumnPoints\" was not injected: check your FXML file 'EndGameView.fxml'.";
        assert gridPaneScores != null : "fx:id=\"gridPaneScores\" was not injected: check your FXML file 'EndGameView.fxml'.";
        assert buttonEnd != null : "fx:id=\"buttonEnd\" was not injected: check your FXML file 'EndGameView.fxml'.";
        this.imageBackground.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/backgrounds/wallpapper_1920x1080_artemis.jpg")));
        this.imageBackground.setFitWidth(this.width);
        this.imageBackground.setFitHeight(this.height);
        this.imageBackground.setOpacity(0.5);

        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setMinWidth(USE_PREF_SIZE);
        constraints.setPrefWidth(120);
        constraints.setMaxWidth(USE_COMPUTED_SIZE);
        LinkedList<int[]> points = main.getMainController().getBoardController().calculatePoints();
        out("Punktestand wird bereitgestellt");
        for (int i = 0; i < this.wonders.size(); i++) {
            this.gridPaneScores.add(createNewLabel(this.wonders.get(i).getPlayerName()), i + 1, 0);
            this.gridPaneScores.add(createNewLabel(Integer.toString(points.get(i)[0]) ), i + 1, 1);
            this.gridPaneScores.add(createNewLabel(Integer.toString((points.get(i)[1]/3))), i + 1, 2);
            this.gridPaneScores.add(createNewLabel(Integer.toString(points.get(i)[2]) ), i + 1, 3);
            this.gridPaneScores.add(createNewLabel(Integer.toString(points.get(i)[3]) ), i + 1, 4);
            this.gridPaneScores.add(createNewLabel(Integer.toString(points.get(i)[4]) ), i + 1, 5);
            this.gridPaneScores.add(createNewLabel(Integer.toString(points.get(i)[5]) ), i + 1, 6);
            this.gridPaneScores.add(createNewLabel(Integer.toString(points.get(i)[6]) ), i + 1, 7);
            this.gridPaneScores.add(createNewLabel(Integer.toString(points.get(i)[7]) ), i + 1, 8);

            this.gridPaneScores.getColumnConstraints().add(i+1, constraints);
        }

        LinkedList<Highscore> highscores = main.getMainController().getHighscoreController().getHighscores();
        this.tableColumnName.setCellValueFactory(new PropertyValueFactory<Highscore, String>("name"));
        this.tableColumnPoints.setCellValueFactory(new PropertyValueFactory<Highscore, Integer>("points"));
        this.tableColumnPosition.setCellValueFactory(new PropertyValueFactory<Highscore, Integer>("position"));
        this.highscoreObservableList = FXCollections.observableList(highscores);

        this.tableViewHighscores.setItems(this.highscoreObservableList);
    }

    /**
     * Create new label label.
     *
     * @param text the text
     * @return the label
     */
    public Label createNewLabel(String text) {
        Label label = new Label(text);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(20));
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);
        return label;
    }
}
