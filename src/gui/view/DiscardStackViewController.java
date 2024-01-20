package gui.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Card;
import model.Wonder;

/**
 * The type Discard stack view controller.
 */
public class DiscardStackViewController extends AnchorPane {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<ImageView> listViewCards;

    @FXML
    private HBox hBox;

    @FXML
    private Label labelPlayerName;

    private BoardViewController boardViewController;
    private LinkedList<Card> cards;
    private boolean halikarnassos;
    private Card selectedCard;
    private HashMap<ImageView, Card> imageCardMap = new HashMap<>();
    private Button buttonBuild;
    private Wonder wonder;
    private String contextInfo;

    /**
     * Instantiates a new Discard stack view controller.
     *
     * @param boardViewController the board view controller
     * @param cards               the cards
     * @param halikarnassos       the halikarnassos
     * @param wonder              the wonder
     * @param contxtInfo          the contxt info
     */
    public DiscardStackViewController(BoardViewController boardViewController, LinkedList<Card> cards, boolean halikarnassos, Wonder wonder, String contxtInfo) {
        assert boardViewController != null;
        assert cards != null;
        this.boardViewController = boardViewController;
        this.cards = cards;
        this.halikarnassos = halikarnassos;
        this.wonder = wonder;
        this.contextInfo = contxtInfo;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DiscardStackView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Hide.
     */
    public void hide() {
        this.boardViewController.refreshBoard();
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert listViewCards != null : "fx:id=\"listViewCards\" was not injected: check your FXML file 'DiscardStackView.fxml'.";

        LinkedList<ImageView> images = new LinkedList<>();
        if (!halikarnassos) {
            for (Card card : cards) {
                images.add(new ImageView(new Image(this.getClass().getResourceAsStream(card.getImage()))));
            }
            ObservableList<ImageView> items = FXCollections.observableList(images);
            listViewCards.setItems(items);

            Button buttonHide = new Button("Ausblenden");
            buttonHide.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    hide();
                }
            });
            this.hBox.getChildren().add(buttonHide);

            this.labelPlayerName.setVisible(false);
        } else {
            this.labelPlayerName.setText(this.wonder.getPlayerName() + " kann seinen Halikarnassos Effekt nutzen");


            for (Card card : cards) {
                ImageView imageView = new ImageView(new Image(this.getClass().getResourceAsStream(card.getImage())));
                this.imageCardMap.put(imageView, card);
                images.add(imageView);
            }

            ObservableList<ImageView> items = FXCollections.observableList(images);
            listViewCards.setItems(items);
            listViewCards.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageView>() {
                @Override
                public void changed(ObservableValue<? extends ImageView> observable, ImageView oldImage, ImageView newImage) {
                    InnerShadow shadow = new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 10, 1, 0,0);
                    shadow.setHeight(10);
                    shadow.setWidth(10);
                    removeEffectFromImages();
                    setSelectedCard(getImageCardMap().get(newImage));
                    newImage.setEffect(shadow);
                    setButtonDisable(false);
                }
            });

            Button buttonCancel = new Button("Keine Karte bauen");
            buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    cancel();
                }
            });
            Button buttonBuild = new Button("Markierte Karte bauen");
            buttonBuild.setDisable(true);
            buttonBuild.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    build();
                }
            });
            this.buttonBuild = buttonBuild;

            this.hBox.getChildren().add(buttonBuild);
            this.hBox.getChildren().add(buttonCancel);
        }
    }

    /**
     * Cancel.
     */
    public void cancel() {
        out(wonder.getPlayerName()+" hat keine Karte gewählt");
        switch (contextInfo) {
            case "endAge": this.boardViewController.endAge(); break;
            case "endRound": this.boardViewController.endRound(); break;
            case "nextPlayer": this.boardViewController.nextPlayer(); break;
        }
    }

    /**
     * Build.
     */
    public void build() {
        assert wonder != null;
        assert selectedCard != null;
        out(wonder.getPlayerName()+" wählte die Karte: "+selectedCard.getName().name());
        this.boardViewController.getMain().getMainController().getWonderController().createBuilding(selectedCard, wonder);
        this.boardViewController.getMain().getMainController().getBoardController().getDiscardstack().remove(selectedCard);
        switch (contextInfo) {
            case "endAge": this.boardViewController.endAge(); break;
            case "endRound": this.boardViewController.endRound(); break;
            case "nextPlayer": this.boardViewController.nextPlayer(); break;
        }
    }

    /**
     * Remove effect from images.
     */
    public void removeEffectFromImages() {
        for(Node n : this.listViewCards.getItems()){
            n.setEffect(null);
        }
    }

    /**
     * Sets selected image.
     *
     * @param card the image
     */
    public void setSelectedCard(Card card) {
        assert card != null;
        this.selectedCard = card;
    }

    /**
     * Gets file card map.
     *
     * @return the file card map
     */
    public HashMap<ImageView, Card> getImageCardMap() {
        return imageCardMap;
    }

    /**
     * Sets button visible.
     *
     * @param visibility the visibility
     */
    public void setButtonDisable(Boolean visibility) {
        assert this.buttonBuild != null;
        this.buttonBuild.setDisable(visibility);
    }
    private void out(String line){
        if(!BoardViewController.IGNOREKITURN)System.out.println("BoardViewController: "+line);
        Main.pWriter.println("DiscardStackView Controller: "+line);
    }
}
