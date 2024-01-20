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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.Card;
import model.Wonder;

/**
 * The type Card options view controller.
 */
public class CardOptionsViewController extends AnchorPane {
    @FXML
    private ResourceBundle resources;

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

    /**
     * Instantiates a new Card options view controller.
     *
     * @param boardViewController the board view controller
     * @param card                the card
     * @param wonder              the wonder from the card Optiones
     */
    public CardOptionsViewController(BoardViewController boardViewController, Card card, Wonder wonder) {
        assert boardViewController != null;
        assert card != null;
        assert wonder != null;
        this.boardViewController = boardViewController;
        this.wonderController = this.boardViewController.getMain().getMainController().getWonderController();
        this.card = card;
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
        assert vBox != null : "fx:id=\"hBox\" was not injected: check your FXML file 'CardOptionsView.fxml'.";
        assert imageCard != null : "fx:id=\"imageCard\" was not injected: check your FXML file 'CardOptionsView.fxml'.";
        this.imageCard.setImage(new Image(this.getClass().getResourceAsStream(this.card.getImage())));
        this.vBox.getChildren().clear();


        Button sell = new Button("Verkaufen");
        sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sellCard();
            }
        });

        Button buildBuilding = new Button("Gebaeude bauen");
        buildBuilding.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buildBuilding();
            }
        });

        Button expandWonder = new Button("Wunder ausbauen");
        expandWonder.setDisable(true);
        expandWonder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                expandWonder();
            }
        });

        this.vBox.getChildren().add(sell);
        this.vBox.getChildren().add(buildBuilding);
        this.vBox.getChildren().add(expandWonder);

        if (!this.wonderController.isAffordable(this.card.getCosts(), this.wonder)) {//Karte nicht finanzierbar
            buildBuilding.setDisable(true);
        }

        if(this.wonderController.isBuiltAlready(this.card, this.wonder)) {//Karte wurde bereits gebaut
            buildBuilding.setDisable(true);
        }

        if (this.wonderController.isFree(this.wonder, this.card)) {//Karte kann durch Techtree oder Zeus Effekt gebaut werden
            buildBuilding.setDisable(false);
        }

        int currentStage = this.wonder.getCurrentStage();
        if (currentStage < 3 && this.wonderController.isAffordable(this.wonder.getStages()[currentStage].getCosts(), this.wonder)) {//Stage nicht finanzierbar
            expandWonder.setDisable(false);
        }
    }

    @FXML
    void back(ActionEvent event) {
        initialize();
        this.buttonBack.setVisible(false);
    }

    /**
     * Sell card.
     */
    public void sellCard() {
        //Fixed indem im BoardState beim Aufruf von setCurrentPlayer der Boardstate gesetzt wird, da sonst nullPointerException
        //im WonderController geworfen wurde
        out(wonder.getPlayerName()+" verkauft die Karte");
        this.wonderController.discardCard(this.card, this.wonder);
        this.boardViewController.removeConfirmedCard(this.wonder);
    }

    /**
     * Build building.
     */
    public void buildBuilding() {
        out(wonder.getPlayerName()+" baut die Karte");
        String nameLeft = "";
        String nameRight = "";
        if (this.wonder.getNeighbours().size() == 2) {
            nameLeft = this.wonder.getNeighbours().get(0).getPlayerName();
            nameRight = this.wonder.getNeighbours().get(1).getPlayerName();
        } else {
            nameLeft = this.wonder.getNeighbours().get(0).getPlayerName();
        }
        this.vBox.getChildren().clear();

        if (this.wonderController.isFree(this.wonder, this.card)) {
            int currentAge = this.boardViewController.getMain().getMainController().getBoardController().getAge();
            if (this.wonder.getZeusEffectAvailable()[currentAge-1]) {
                Button zeus = new Button("Zeus Effekt nutzen");
                zeus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        useZeusEffectOnCard();
                    }
                });
                this.vBox.getChildren().add(zeus);
            } else {
                Button freeBuild = new Button("Freies bauen nutzen");
                freeBuild.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        useFreeBuildOnCard();
                    }
                });
                this.vBox.getChildren().add(freeBuild);
            }
        }

        LinkedList<Pair<Integer, Integer>> options = this.wonderController.calculateAllResourceOptions(this.wonder, this.card.getCosts());
        for (Pair<Integer, Integer> pair : options) {
            this.vBox.getChildren().add(new TradingOptionViewController(this, pair.getKey(), pair.getValue(), nameLeft, nameRight, "buildCard"));
        }
        this.buttonBack.setVisible(true);
    }

    private void out(String line){
        if(!BoardViewController.IGNOREKITURN)System.out.println("BoardViewController: "+line);
        Main.pWriter.println("CardOptionView Controller: "+line);
    }

    private void useFreeBuildOnCard() {
        out(wonder.getPlayerName()+" konnte die karte umsonst bauen");
        this.wonderController.createBuilding(this.card, this.wonder);
        this.boardViewController.removeConfirmedCard(this.wonder);
    }

    /**
     * Expand wonder.
     */
    public void expandWonder() {
        out(wonder.getPlayerName()+" erweitert sein Wunder");
        String nameLeft = "";
        String nameRight = "";
        if (this.wonder.getNeighbours().size() == 2) {
            nameLeft = this.wonder.getNeighbours().get(0).getPlayerName();
            nameRight = this.wonder.getNeighbours().get(1).getPlayerName();
        } else {
            nameLeft = this.wonder.getNeighbours().get(0).getPlayerName();
        }
        this.vBox.getChildren().clear();

        int currentAge = this.boardViewController.getMain().getMainController().getBoardController().getAge();
        if (this.wonder.getZeusEffectAvailable()[currentAge-1]) {
            Button zeus = new Button("Zeus Effekt nutzen");
            zeus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    useZeusEffectOnWonder();
                }
            });
            this.vBox.getChildren().add(zeus);
        }

        int currentStage = this.wonder.getCurrentStage();
        LinkedList<Pair<Integer, Integer>> options = this.wonderController.calculateAllResourceOptions(this.wonder, this.wonder.getStages()[currentStage].getCosts());
        for (Pair<Integer, Integer> pair : options) {
            this.vBox.getChildren().add(new TradingOptionViewController(this, pair.getKey(), pair.getValue(), nameLeft, nameRight, "expandWonder"));
        }
        this.buttonBack.setVisible(true);
    }

    private void useZeusEffectOnCard() {
        out(wonder.getPlayerName()+" nutze den Zeuseffect");
        int currentAge = this.boardViewController.getMain().getMainController().getBoardController().getAge();

        this.wonderController.createBuildingFree(this.card, this.wonder);
        this.wonder.disableZeus(currentAge);
        this.boardViewController.removeConfirmedCard(this.wonder);
    }

    private void useZeusEffectOnWonder() {
        out(wonder.getPlayerName()+" nutze den Zeuseffect");
        int currentAge = this.boardViewController.getMain().getMainController().getBoardController().getAge();

        this.wonderController.increaseStage(this.card, this.wonder);
        this.wonder.disableZeus(currentAge);
        this.boardViewController.removeConfirmedCard(this.wonder);

    }

    /**
     * Builds the current card in the current Wonder
     *
     * @param trade the trade
     */
    public void build(Pair<Integer, Integer> trade) {
        out(wonder.getPlayerName()+" handelte "+trade.toString());
        try {
            this.wonderController.trade(this.wonder, trade);
            this.wonderController.createBuilding(this.card, this.wonder);
            this.boardViewController.removeConfirmedCard(this.wonder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Expands the current Wonder with the current Card
     *
     * @param trade the trade
     */
    public void expand(Pair<Integer, Integer> trade) {
        out(wonder.getPlayerName()+" handelte "+trade.toString());
        try {
            this.wonderController.trade(this.wonder, trade);
            this.wonderController.increaseStage(this.card, this.wonder);
            this.boardViewController.removeConfirmedCard(this.wonder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets board view controller.
     *
     * @return the board view controller
     */
    public BoardViewController getBoardViewController() {
        return boardViewController;
    }
}
