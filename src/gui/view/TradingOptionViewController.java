package gui.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

/**
 * The type Trading option view controller.
 */
public class TradingOptionViewController extends AnchorPane {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView imageLeft;

    @FXML
    private Label labelLeft;


    @FXML
    private Label labelCostsLeft;

    @FXML
    private Button buttonChoose;

    @FXML
    private Label labelRight;

    @FXML
    private Label labelCostsRight;

    @FXML
    private ImageView imageRight;

    private CardOptionsViewController cardOptionsViewController;
    private Integer costsLeft,costsRight;
    private String nameLeft,nameRight, type;

    /**
     * Instantiates a new Trading option view controller.
     *  @param cardOptionsViewController the cardOptionsViewController
     * @param costsLeft           the costs left
     * @param costsRight          the costs right
     * @param nameLeft            the name left
     * @param nameRight           the name right
     * @param type  the type could be "buildCard" or "expandWonder"
     */
    public TradingOptionViewController(CardOptionsViewController cardOptionsViewController, int costsLeft, int costsRight, String nameLeft, String nameRight, String type) {
        this.cardOptionsViewController = cardOptionsViewController;
        this.costsLeft = costsLeft;
        this.costsRight = costsRight;
        this.nameLeft = nameLeft;
        this.nameRight = nameRight;
        this.type = type;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TradingOptionView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Choose.
     *
     * @param event the event
     */
    @FXML
    void choose(ActionEvent event) {
        switch (this.type) {
            case "buildCard": this.cardOptionsViewController.build(new Pair<>(this.costsLeft, this.costsRight)); break;
            case "expandWonder": this.cardOptionsViewController.expand(new Pair<>(this.costsLeft, this.costsRight)); break;
        }
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert imageLeft != null : "fx:id=\"imageLeft\" was not injected: check your FXML file 'TradingOption.fxml'.";
        assert labelLeft != null : "fx:id=\"labelLeft\" was not injected: check your FXML file 'TradingOption.fxml'.";
        assert buttonChoose != null : "fx:id=\"buttonChoose\" was not injected: check your FXML file 'TradingOption.fxml'.";
        assert labelRight != null : "fx:id=\"labelRight\" was not injected: check your FXML file 'TradingOption.fxml'.";
        assert imageRight != null : "fx:id=\"imageRight\" was not injected: check your FXML file 'TradingOption.fxml'.";
        if (costsLeft == 0 && costsRight == 0) {
            this.buttonChoose.setText("eigene Resourcen nutzen");
            this.hBox.getChildren().remove(0, 2);
            this.hBox.getChildren().remove(1, 3);
        } else {
            if (this.nameRight.equals("")) {
                this.buttonChoose.setText("Handeln");
                this.labelCostsLeft.setText(this.costsLeft.toString());
                this.labelLeft.setText(nameLeft);
                this.hBox.getChildren().remove(3, 5);
            } else {
                this.buttonChoose.setText("Handeln");
                this.labelCostsLeft.setText(this.costsLeft.toString());
                this.labelCostsRight.setText(this.costsRight.toString());
                this.labelLeft.setText(nameLeft);
                this.labelRight.setText(nameRight);
            }
        }
    }
}
