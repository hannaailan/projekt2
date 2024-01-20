package gui.view;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Card;
import model.Wonder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * The type Card view controller.
 */
public class CardViewController extends BorderPane {

    @FXML
    private HBox hBoxTop;

    @FXML
    private HBox hBoxBottom;

    @FXML
    private Button buttonConfirm;

    private BoardViewController boardViewController;
    private Card selectedCard;
    private LinkedList<Card> cards;
    private Wonder wonder;
    private HashMap<String, Card> fileCardMap = new HashMap<>();

    /**
     * Instantiates a new Card view controller.
     * @param boardViewController the board view controller
     * @param cards               the given cards
     * @param wonder              the wonder
     */
    public CardViewController(BoardViewController boardViewController, LinkedList<Card> cards, Wonder wonder) {
        this.wonder = wonder;
        this.cards = cards;
        assert (this.cards.size() >= 2);
        assert (this.cards.size() <= 7);
        for (Card card : this.cards) {
            this.fileCardMap.put(card.getImage(), card);
        }
        this.boardViewController = boardViewController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Confirm.
     *
     * @param event the event
     */
    @FXML
    void confirm(ActionEvent event) {
        assert this.selectedCard != null;
        out(wonder.getPlayerName()+" wählt die Karte: "+ selectedCard.getName().name());
        this.boardViewController.confirmCard(this.selectedCard, this.wonder);
    }

    /**
     * Sets button visible.
     *
     * @param visibility the visibility
     */
    public void setButtonVisible(Boolean visibility) {
        this.buttonConfirm.setVisible(visibility);
    }

    /**
     * Remove effect from images.
     */
    public void removeEffectFromImages() {
        for(Node n : this.hBoxTop.getChildren()){
            n.setEffect(null);
        }
        for(Node n : this.hBoxBottom.getChildren()){
            n.setEffect(null);
        }
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert hBoxTop != null : "fx:id=\"hBoxTop\" was not injected: check your FXML file 'CardView.fxml'.";
        assert hBoxBottom != null : "fx:id=\"hBoxBottom\" was not injected: check your FXML file 'CardView.fxml'.";
        for (int i = 0; i < this.cards.size(); i++) {
            if (i <= 3) {
                ImageView imageView = createImageView(this.cards.get(i).getImage());
                imageView.setFitHeight(221);
                imageView.setFitWidth(142);
                this.hBoxTop.getChildren().add(imageView);
            } else {
                ImageView imageView = createImageView(this.cards.get(i).getImage());
                imageView.setFitHeight(221);
                imageView.setFitWidth(142);
                this.hBoxBottom.getChildren().add(imageView);
            }
        }
        out(wonder.getPlayerName()+" Wählt eine Karte von :");
        for(Card card : cards)
            out(card.getName().name());
    }

    /**
     * Creates a new ImageView with the given path
     *
     * @param file the file of the image
     * @return the imageView with the image
     */
    private ImageView createImageView(String file) {
        ImageView imageView = new ImageView(new Image(this.getClass().getResourceAsStream(file)));
        imageView.setFitHeight(250);
        imageView.setFitWidth(162.5);

        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    InnerShadow shadow = new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 10, 1, 0,0);
                    shadow.setHeight(10);
                    shadow.setWidth(10);
                    removeEffectFromImages();
                    setSelectedCard(getFileCardMap().get(file));
                    imageView.setEffect(shadow);
                    setButtonVisible(true);
                    event.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return imageView;
    }

    /**
     * Sets selected image.
     *
     * @param card the image
     */
    public void setSelectedCard(Card card) {
        this.selectedCard = card;
    }

    /**
     * Gets file card map.
     *
     * @return the file card map
     */
    public HashMap<String, Card> getFileCardMap() {
        return fileCardMap;
    }
    private void out(String line){
        if(!BoardViewController.IGNOREKITURN)System.out.println("BoardViewController: "+line);
        Main.pWriter.println("CardView Controller: "+line);
    }
}
