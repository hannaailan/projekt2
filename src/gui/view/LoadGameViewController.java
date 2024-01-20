package gui.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * The type Load game view controller.
 */
public class LoadGameViewController extends AnchorPane {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageLoadGame;

    @FXML
    private HBox hbox;

    @FXML
    private ComboBox<?> comboBoxChooseSavegame;

    @FXML
    private Button buttonLoadSavegame;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonChooseSavegame;

    @FXML
    private Text textChoosedSavegame;

    private Main main;
    private int width, height;
    private File selectedFile;
    private MediaPlayer a;
    /**
     * Instantiates a new Load game view controller.
     *
     * @param main   the main
     * @param width  the width
     * @param height the height
     */
    public LoadGameViewController(Main main, int width, int height) {
        this.width = width;
        this.height = height;
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoadGameView.fxml"));
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
     * Cancel.
     *
     * @param event the event
     */
    @FXML
    void cancel(ActionEvent event) {

        a.stop();this.main.loadStartView();
    }

    /**
     * Choose savegame.
     *
     * @param event the event
     */
    @FXML
    void chooseSavegame(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Gamesave-Datei auswählen");
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(!userDirectory.canRead()) {
            userDirectory = new File("");//TODO test auf linux/mac
        }
        chooser.setInitialDirectory(userDirectory);
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save Files", "*.game"));
        File selcted = chooser.showOpenDialog(this.getScene().getWindow());
        if (selcted != null) {
            this.selectedFile = selcted;
            this.textChoosedSavegame.setText(this.selectedFile.toString());
            this.buttonLoadSavegame.setDisable(false);
        }
    }

    /**
     * Load savegame.
     *
     * @param event the event
     */
    @FXML
    void loadSavegame(ActionEvent event) {

        a.stop();
        this.main.getMainController().getiOController().loadGame(this.selectedFile);
        this.main.loadBoardView();
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert imageLoadGame != null : "fx:id=\"imageLoadGame\" was not injected: check your FXML file 'LoadGameView.fxml'.";
        assert hbox != null : "fx:id=\"hbox\" was not injected: check your FXML file 'LoadGameView.fxml'.";
        assert buttonChooseSavegame != null : "fx:id=\"buttonChooseSavegame\" was not injected: check your FXML file 'LoadGameView.fxml'.";
        assert textChoosedSavegame != null : "fx:id=\"textChoosedSavegame\" was not injected: check your FXML file 'LoadGameView.fxml'.";
        assert buttonLoadSavegame != null : "fx:id=\"buttonLoadSavegame\" was not injected: check your FXML file 'LoadGameView.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'LoadGameView.fxml'.";
        this.imageLoadGame.setFitWidth(this.width);
        this.imageLoadGame.setFitHeight(this.height);
        this.imageLoadGame.setOpacity(0.5);
    }
}

