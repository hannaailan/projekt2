package gui.view;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 * The type New tournament view controller.
 */
public class NewTournamentViewController extends AnchorPane {
    @FXML
    private ImageView imageView;

    @FXML
    private Label labelKiStart;

    @FXML
    private TextField textFieldPlayerName1;

    @FXML
    private MenuButton menuButtonChooseType1;

    @FXML
    private MenuItem menuItemPlayer;

    @FXML
    private MenuItem menuItemKiEasy;

    @FXML
    private MenuItem menuItemKiMedium;

    @FXML
    private MenuItem menuItemKiHard;

    @FXML
    private TextField textFieldPlayerName2;

    @FXML
    private MenuButton menuButtonChooseType2;

    @FXML
    private MenuItem menuItemPlayer1;

    @FXML
    private MenuItem menuItemKiEasy1;

    @FXML
    private MenuItem menuItemKiMedium1;

    @FXML
    private MenuItem menuItemKiHard1;

    @FXML
    private Button buttonChooseCsv;

    @FXML
    private Label labelChoosedFile;

    @FXML
    private Button buttonStartGame;

    @FXML
    private Button buttonCancel;

    private Main main;
    private int width, height;
    private String selectedFile;
    private MediaPlayer a;

    /**
     * Instantiates a new New tournament view controller.
     *
     * @param main   the main
     * @param width  the width
     * @param height the height
     */
    public NewTournamentViewController(Main main, int width, int height) {
        this.width = width;
        this.height = height;
        this.main = main;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewTournamentView.fxml"));
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
     * Initialize.
     */
    @FXML
    void initialize() {
        this.imageView.setFitWidth(this.width);
        this.imageView.setFitHeight(this.height);
    }

    /**
     * Cancel.
     *
     * @param event the event
     */
    @FXML
    void cancel(ActionEvent event) {

        a.stop();
        this.main.loadStartView();
    }

    /**
     * Choose csv.
     *
     * @param event the event
     */
    @FXML
    void chooseCsv(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("CSV-Datei auswählen");
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(!userDirectory.canRead()) {
            userDirectory = new File("");//TODO test auf linux/mac
        }
        chooser.setInitialDirectory(userDirectory);
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        String selected = chooser.showOpenDialog(this.getScene().getWindow()).getAbsolutePath();
        if (selected != null) {
            this.selectedFile = selected;
            this.labelChoosedFile.setText(this.selectedFile.toString());
            if (!this.menuButtonChooseType1.getText().equals("Spielertyp") && !this.menuButtonChooseType2.getText().equals("Spielertyp")) {
                this.buttonStartGame.setDisable(false);
            }
        }
    }

    /**
     * Choose ki easy 1.
     *
     * @param event the event
     */
    @FXML
    void chooseKiEasy1(ActionEvent event) {
        this.menuButtonChooseType1.setText("KI einfach");
        if (!this.menuButtonChooseType2.getText().equals("Spielertyp") && this.selectedFile != null) {
            this.buttonStartGame.setDisable(false);
        }
    }

    /**
     * Choose ki easy 2.
     *
     * @param event the event
     */
    @FXML
    void chooseKiEasy2(ActionEvent event) {
        this.menuButtonChooseType2.setText("KI einfach");
        if (!this.menuButtonChooseType1.getText().equals("Spielertyp") && this.selectedFile != null) {
            this.buttonStartGame.setDisable(false);
        }
    }

    /**
     * Choose ki hard 1.
     *
     * @param event the event
     */
    @FXML
    void chooseKiHard1(ActionEvent event) {
        this.menuButtonChooseType1.setText("KI schwer");
        if (!this.menuButtonChooseType2.getText().equals("Spielertyp") && this.selectedFile != null) {
            this.buttonStartGame.setDisable(false);
        }
    }

    /**
     * Choose ki hard 2.
     *
     * @param event the event
     */
    @FXML
    void chooseKiHard2(ActionEvent event) {
        this.menuButtonChooseType2.setText("KI schwer");
        if (!this.menuButtonChooseType1.getText().equals("Spielertyp") && this.selectedFile != null) {
            this.buttonStartGame.setDisable(false);
        }
    }

    /**
     * Choose ki medium 1.
     *
     * @param event the event
     */
    @FXML
    void chooseKiMedium1(ActionEvent event) {
        this.menuButtonChooseType1.setText("KI mittel");
        if (!this.menuButtonChooseType2.getText().equals("Spielertyp") && this.selectedFile != null) {
            this.buttonStartGame.setDisable(false);
        }
    }

    /**
     * Choose ki medium 2.
     *
     * @param event the event
     */
    @FXML
    void chooseKiMedium2(ActionEvent event) {
        this.menuButtonChooseType2.setText("KI mittel");
        if (!this.menuButtonChooseType1.getText().equals("Spielertyp") && this.selectedFile != null) {
            this.buttonStartGame.setDisable(false);
        }
    }

    /**
     * Choose player 1.
     *
     * @param event the event
     */
    @FXML
    void choosePlayer1(ActionEvent event) {
        this.menuButtonChooseType1.setText("normaler Spieler");
        if (!this.menuButtonChooseType2.getText().equals("Spielertyp") && this.selectedFile != null) {
            this.buttonStartGame.setDisable(false);
        }
    }

    /**
     * Choose player 2.
     *
     * @param event the event
     */
    @FXML
    void choosePlayer2(ActionEvent event) {
        this.menuButtonChooseType2.setText("normaler Spieler");
        if (!this.menuButtonChooseType1.getText().equals("Spielertyp") && this.selectedFile != null) {
            this.buttonStartGame.setDisable(false);
        }
    }

    /**
     * Start game.
     *
     * @param event the event
     */
    @FXML
    void startGame(ActionEvent event) {
        Pair<String, Integer>[] arr = new Pair[2];
        switch (this.menuButtonChooseType1.getText()) {
            case "normaler Spieler":
                if (this.textFieldPlayerName1.getText().isBlank()) {
                    arr[0] = new Pair<>("name1", 0);
                } else {
                    arr[0] = new Pair<>(this.textFieldPlayerName1.getText(), 0);
                }
                break;
            case "KI einfach": arr[0] = new Pair<>("KI Gruppe4 einfach", 1); break;
            case "KI mittel": arr[0] = new Pair<>("KI Gruppe4 mittel", 2); break;
            case "KI schwer": arr[0] = new Pair<>("KI Gruppe4 schwer", 3); break;
        }

        switch (this.menuButtonChooseType2.getText()) {
            case "normaler Spieler":
                if (this.textFieldPlayerName2.getText().isBlank()) {
                    arr[1] = new Pair<>("name2", 0);
                } else {
                    arr[1] = new Pair<>(this.textFieldPlayerName2.getText(), 0);
                }
                break;
            case "KI einfach": arr[1] = new Pair<>("KI Gruppe4 einfach", 1); break;
            case "KI mittel": arr[1] = new Pair<>("KI Gruppe4 mittel", 2); break;
            case "KI schwer": arr[1] = new Pair<>("KI Gruppe4 schwer", 3); break;
        }

        a.stop();
        if (this.main.getMainController().getMain().getGame()!=null)
            this.main.getMainController().getiOController().saveGame();
        this.main.getMainController().getSetupController().startTournament(arr, this.selectedFile);
        this.main.loadBoardView();
    }
}
