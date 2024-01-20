package gui.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class KICardViewController extends BorderPane {

    @FXML
    private HBox hBoxTop;

    @FXML
    private HBox hBoxBottom;

    @FXML
    public Button buttonConfirm;

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
    public KICardViewController(BoardViewController boardViewController, LinkedList<Card> cards,Card selectedCard, Wonder wonder) {
        this.wonder = wonder;
        this.cards = cards;
        this.selectedCard=selectedCard;
        assert (this.cards.size() >= 2);
        assert (this.cards.size() <= 7);
        for (Card card : this.cards) {
            this.fileCardMap.put(card.getImage(), card);
        }
        this.boardViewController = boardViewController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("KICardView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setButtonVisible(true);
    }

    /**
     * Confirm.
     *
     * @param event the event
     */
    @FXML
    void confirm(ActionEvent event) {
        assert this.selectedCard != null;
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
                ImageView imageView = createImageView(this.cards.get(i));
                imageView.setFitHeight(221);
                imageView.setFitWidth(142);
                this.hBoxTop.getChildren().add(imageView);
            } else {
                ImageView imageView = createImageView(this.cards.get(i));
                imageView.setFitHeight(221);
                imageView.setFitWidth(142);
                this.hBoxBottom.getChildren().add(imageView);
            }
        }

    }

    /**
     * Creates a new ImageView with the given path
     *
     * @param curCard the file of the image
     * @return the imageView with the image
     */
    private ImageView createImageView(Card curCard) {
        Image image = new Image(this.getClass().getResourceAsStream(curCard.getImage()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(250);
        imageView.setFitWidth(162.5);
        if(curCard.equals(selectedCard)) {
            try {
                InnerShadow shadow = new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 10, 1, 0,0);
                shadow.setHeight(10);
                shadow.setWidth(10);
                removeEffectFromImages();
                imageView.setEffect(shadow);
                setButtonVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return imageView;
    }



}
