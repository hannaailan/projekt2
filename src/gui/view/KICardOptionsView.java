package gui.view;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.Main;
import controller.WonderController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Pair;
import model.Card;
import model.Wonder;

/**
 * The type Card options view controller.
 */
public class KICardOptionsView extends AnchorPane {
    @FXML
    private ResourceBundle resources;

    /**
     * The Confirm.
     */
    public Button confirm;

    @FXML
    private URL location;

    @FXML
    private ImageView imageCard;

    @FXML
    private VBox vBox;

    @FXML
    private Button buttonBack;

    private Card card;
    private BoardViewController boardViewController;
    private Wonder wonder;
    private WonderController wonderController;
    private  String coise;

    /**
     * Instantiates a new Card options view controller.
     *
     * @param boardViewController the board view controller
     * @param card                the card
     * @param wonder              the wonder from the card Optiones
     * @param coise               the coise
     */
    public KICardOptionsView(BoardViewController boardViewController, Card card, Wonder wonder,String coise) {
        assert boardViewController != null;
        assert card != null;
        assert wonder != null;
        this.boardViewController = boardViewController;
        this.wonderController = this.boardViewController.getMain().getMainController().getWonderController();
        this.card = card;
        this.coise= coise;
        this.wonder = wonder;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardOptionsView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
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
        assert vBox != null : "fx:id=\"hBox\" was not injected: check your FXML file 'KICardOptionsView.fxml'.";
        assert imageCard != null : "fx:id=\"imageCard\" was not injected: check your FXML file 'KICardOptionsView.fxml'.";
        this.imageCard.setImage(new Image(this.getClass().getResourceAsStream(this.card.getImage())));
        this.vBox.getChildren().clear();

        Pair<Integer,Integer> trading ;
        switch (coise) {
            case "build":
                 trading=boardViewController.getMain().getMainController().getaIController().selectTrade(this.wonder, this.card,
                        boardViewController.getMain().getMainController().getWonderController().
                                calculateAllResourceOptions(wonder, card.getCosts()));
                break;
            case "expand":
                trading = boardViewController.getMain().getMainController().getaIController().selectTrade(this.wonder, this.card,
                        boardViewController.getMain().getMainController().getWonderController().
                                calculateAllResourceOptions(wonder, wonder.getStages()[wonder.getCurrentStage()].getCosts()));
                break;
                default:
                trading=null;
        }
        confirm = new Button("ok");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(BoardViewController.IGNOREKITURN)
                    return;
                confirm();

            }
        });

        Label coise = new Label(this.coise);
        coise.setFont(new Font(20));
        coise.setStyle("-fx-text-fill: white;");

        if(trading!=null) {
            Label trade = new Label(trading.getKey()+" <- Geld -> "+trading.getValue());
            trade.setFont(new Font(20));
            trade.setStyle("-fx-text-fill: white;");
            this.vBox.getChildren().add(trade);
        }



        this.vBox.getChildren().add(coise);
        this.vBox.getChildren().add(confirm);



    }

    public void confirm(){
        switch (coise) {
            case "build":
                build(boardViewController.getMain().getMainController().getaIController().selectTrade(getWonder(), getCard(),
                        boardViewController.getMain().getMainController().getWonderController().
                                calculateAllResourceOptions(wonder, card.getCosts())));
                break;
            case "expand":
                expand(boardViewController.getMain().getMainController().getaIController().selectTrade(getWonder(), getCard(),
                        boardViewController.getMain().getMainController().getWonderController().
                                calculateAllResourceOptions(wonder, wonder.getStages()[wonder.getCurrentStage()].getCosts())));
                break;
            case "free":
                buildFree();
                break;
            case "discard":
                sellCard();
                break;
            default:
                System.out.println("Ich kann die KI nicht verstehen");
        }
    }
    /**
     * Back.
     *
     * @param event the event
     */
    @FXML
    void back(ActionEvent event) {
        //initialize();
        this.buttonBack.setVisible(false);
    }

    /**
     * Sell card.
     */
    public void sellCard() {
        //Fixed indem im BoardState beim Aufruf von setCurrentPlayer der Boardstate gesetzt wird, da sonst nullPointerException
        //im WonderController geworfen wurde
        this.wonderController.discardCard(this.card, this.wonder);
        this.boardViewController.removeConfirmedCard(this.wonder);
    }

    /**
     * Build building.
     */
    public void buildBuilding() {

    }

    /**
     * Expand wonder.
     */
    public void expandWonder() {

    }


    /**
     * Builds the current card in the current Wonder
     *
     * @param trade the trade
     */
    public void build(Pair<Integer, Integer> trade) {
        out(wonder.getPlayerName()+" benutzte den Trade "+trade.toString());
        try {
            this.wonderController.trade(this.wonder, trade);
            this.wonderController.createBuilding(this.card, this.wonder);

            this.boardViewController.removeConfirmedCard(this.wonder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Build free.
     */
    public void buildFree(){
        this.wonderController.createBuildingFree(this.card,this.wonder);
        this.boardViewController.removeConfirmedCard(this.wonder);
    }

    /**
     * Expands the current Wonder with the current Card
     *
     * @param trade the trade
     */
    public void expand(Pair<Integer, Integer> trade) {
        out(wonder.getPlayerName()+" benutzte den Trade "+trade.toString());
        try {
            this.wonderController.trade(this.wonder, trade);
            this.wonderController.increaseStage(this.card, this.wonder);
            this.boardViewController.removeConfirmedCard(this.wonder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Sorgt für geortnete Konsolenausgabe
     */
    private void out(String line){
        if(!BoardViewController.IGNOREKITURN)System.out.println("BoardViewController: "+line);
        Main.pWriter.println("KICardOption Controller: "+line);
    }

    /**
     * Gets board view controller.
     *
     * @return the board view controller
     */
    public BoardViewController getBoardViewController() {
        return boardViewController;
    }

    /**
     * Gets wonder
     *
     * @return wonder wonder
     */
    public Wonder getWonder() {
        return wonder;
    }

    /**
     * Gets card
     *
     * @return card card
     */
    public Card getCard() {
        return card;
    }
}
