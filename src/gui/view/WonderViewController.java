package gui.view;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.*;

/**
 * The type Wonder view controller.
 */
public class WonderViewController extends AnchorPane {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageWonder;

    @FXML // fx:id="brownCards"
    private ImageView brownCards; // Value injected by FXMLLoader

    @FXML // fx:id="greyCards"
    private ImageView greyCards; // Value injected by FXMLLoader

    @FXML // fx:id="yellowCards"
    private ImageView yellowCards; // Value injected by FXMLLoader

    @FXML // fx:id="violettCards"
    private ImageView violettCards; // Value injected by FXMLLoader

    @FXML // fx:id="greenCards"
    private ImageView greenCards; // Value injected by FXMLLoader

    @FXML
    private ImageView imageViewStage1;

    @FXML
    private ImageView imageViewStage2;

    @FXML
    private ImageView imageViewStage3;

    @FXML
    private Button labelBrown;

    @FXML
    private Label labelGrey;

    @FXML
    private Label labelYellow;

    @FXML
    private Label labelGreen;

    @FXML
    private Label labelViolett;


    @FXML // fx:id="hand"
    private ImageView hand; // Value injected by FXMLLoader

    @FXML // fx:id="wonderResources"
    private ImageView wonderResources; // Value injected by FXMLLoader

    @FXML // fx:id="conflictPoints"
    private ImageView conflictPoints; // Value injected by FXMLLoader

    @FXML // fx:id="mp"
    private ImageView mp; // Value injected by FXMLLoader

    @FXML // fx:id="vp"
    private ImageView vp; // Value injected by FXMLLoader

    @FXML // fx:id="coin"
    private ImageView coin; // Value injected by FXMLLoader

    @FXML
    private String imagePath;

    @FXML
    private Label labelCoins;

    @FXML
    private Label labelConflictPoints;

    @FXML
    private Label labelPlayerName;

    @FXML
    private Label labelProfanbau;

    @FXML
    private ListView<ImageView> liste;

    @FXML
    private Label labelBrick;
    private int bricks;

    @FXML
    private Label labelOre;
    private int ores;
    @FXML
    private Label labelStone;
    private int stones;
    @FXML
    private Label labelWood;
    private int woods;
    @FXML
    private Label labelTextil;
    private int textils;
    @FXML
    private Label labelGlass;
    private int glasses;
    @FXML
    private Label labelPapyrus;
    private int papyrus;
    @FXML
    private Label labelConflict;
    @FXML
    private ImageView imageBrick;

    @FXML
    private ImageView imageOre;

    @FXML
    private ImageView imageStone;

    @FXML
    private ImageView imageWood;

    @FXML
    private ImageView imageTextil;

    @FXML
    private ImageView imageGlass;

    @FXML
    private ImageView imagePapyrus;

    @FXML
    private Label labelOr;

    @FXML
    private ImageView imageOrWB;
    private int OrWB;

    @FXML
    private ImageView imageOrSB;
    private int OrSB;
    @FXML
    private ImageView imageOrBO;
    private int OrBo;
    @FXML
    private ImageView imageOrSW;
    private int OrSW;
    @FXML
    private ImageView imageOrWO;
    private int OrWO;
    @FXML
    private ImageView imageOrSO;
    private int OrSO;
    @FXML
    private Label labelOrWB;

    @FXML
    private Label labelOrSW;

    @FXML
    private Label labelOrSO;

    @FXML
    private Label labelOrSB;

    @FXML
    private Label labelOrWO;

    @FXML
    private Label labelOrBO;

    @FXML
    private ImageView allBrown;

    @FXML
    private ImageView allRest;


    private Wonder wonder;
    private BoardViewController boardViewController;


    private LinkedList<Card> brownBuild= new LinkedList<>();
    private LinkedList<Card> greyBuild= new LinkedList<>();
    private LinkedList<Card> yellowBuild= new LinkedList<>();
    private LinkedList<Card> greenBuild= new LinkedList<>();
    private LinkedList<Card> violettBuild= new LinkedList<>();
    private LinkedList<Card> blueBuild= new LinkedList<>();
    private LinkedList<Card> redBuild= new LinkedList<>();

    private LinkedList<ImageView> brownView= new LinkedList<>();
    private LinkedList<ImageView> greyView= new LinkedList<>();
    private LinkedList<ImageView> yellowView= new LinkedList<>();
    private LinkedList<ImageView> greenView= new LinkedList<>();
    private LinkedList<ImageView> violettView= new LinkedList<>();
    private LinkedList<ImageView> blueView = new LinkedList<>();
    private LinkedList<ImageView> redView = new LinkedList<>();
    private boolean or = false;

    /**
     * Instantiates a new Wonder view controller.
     * @param boardView the board view
     * @param wonder the wonder
     * @param width
     * @param height
     */
    public WonderViewController(BoardViewController boardView, Wonder wonder, int width, int height) {
        this.boardViewController = boardView;
        this.wonder = wonder;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WonderView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void fillLists() {
        LinkedList<Card> tmp = wonder.getBuiltCards();
        for(Card a : tmp){
            if (a.getColor() == CardColor.RED){
                redBuild.add(a);
            }if (a.getColor() == CardColor.BLUE){
                blueBuild.add(a);
            }if (a.getColor() == CardColor.BROWN){
                brownBuild.add(a);
            } if (a.getColor() == CardColor.GREY){
                greyBuild.add(a);
            }   if (a.getColor() == CardColor.YELLOW){
                yellowBuild.add(a);
            }if (a.getColor() == CardColor.GREEN){
                greenBuild.add(a);
            } if (a.getColor() == CardColor.VIOLET){
                violettBuild.add(a);
            }
        }

    }

    void sortRes(Card a){

        if(a.getName() == CardInfo.LUMBER_YARD){
            woods++;
        }
        if(a.getName() == CardInfo.SAWMILL){
            woods++;
            woods++;
        }if(a.getName() == CardInfo.ORE_VEIN){
            ores++;
        }
        if(a.getName() == CardInfo.FOUNDRY){
            ores++;
            ores++;
        }if(a.getName() == CardInfo.CLAY_POOL){
            bricks++;
        }
        if(a.getName() == CardInfo.BRICKYARD){
            bricks++;
            bricks++;

        }if(a.getName() == CardInfo.STONE_PIT){
            stones++;

        }
        if(a.getName() == CardInfo.QUARRY){
            stones++;
            stones++;

        }if(a.getName() == CardInfo.TIMBER_YARD){
            OrSW++;
        }
        if(a.getName() == CardInfo.CLAY_PIT){
            OrBo++;

        }if(a.getName() == CardInfo.EXCAVATION){
            OrSB++;
        }
        if(a.getName() == CardInfo.FOREST_CAVE){
            OrWO++;
        }if(a.getName() == CardInfo.TREE_FARM){
            OrWB++;
        }
        if(a.getName() == CardInfo.MINE){
            OrSO++;
        }
        if(a.getName() == CardInfo.LOOM){
            textils++;
        }
        if(a.getName() == CardInfo.GLASSWORKS){
            glasses++;
        }
        if(a.getName() == CardInfo.PRESS){
            papyrus++;
        }
        if (a.getName() == CardInfo.CARAVANSERY){
            allBrown.setVisible(true);
        }
        if (a.getName() == CardInfo.FORUM){
            allRest.setVisible(true);
        }
    }

    void setRes() {
        woods = 0;
        ores = 0;
        bricks = 0;
        stones = 0;
        textils = 0;
        papyrus = 0;
        glasses = 0;
        OrSW  = 0;
        OrSO = 0;
        OrWB = 0;
        OrWO = 0;
        OrSB = 0;
        OrBo = 0;

        for (Card a : brownBuild) {
            sortRes(a);
        }
        for (Card b : greyBuild){
            sortRes(b);
        }
        for(Card c : yellowBuild){
            sortRes(c);
        }
        labelWood.setText(woods+"");
        labelOre.setText(ores+"");
        labelBrick.setText(bricks+"");
        labelStone.setText(stones+"");
        labelTextil.setText(textils +"");
        labelGlass.setText(glasses +"");
        labelPapyrus.setText(papyrus+"");
        labelOrSW.setText(OrSW +"");
        labelOrBO.setText(OrBo +"");
        labelOrSB.setText(OrSB +"");
        labelOrWO.setText(OrWO +"");
        labelOrWB.setText(OrWB +"");
        labelOrSO.setText(OrSO +"");
        methodeConflict();

    }


    @FXML
    void methodOr() {
        if (!or){
            labelBrick.setVisible(false);
            labelOre.setVisible(false);
            labelStone.setVisible(false);
            labelWood.setVisible(false);
            labelTextil.setVisible(false);
            labelGlass.setVisible(false);
            labelPapyrus.setVisible(false);
            imageBrick.setVisible(false);
            imageOre.setVisible(false);
            imageStone.setVisible(false);
            imageWood.setVisible(false);
            imageTextil.setVisible(false);
            imageGlass.setVisible(false);
            imagePapyrus.setVisible(false);

            imageOrWB.setVisible(true);
            imageOrSB.setVisible(true);
            imageOrBO.setVisible(true);
            imageOrSW.setVisible(true);
            imageOrWO.setVisible(true);
            imageOrSO.setVisible(true);
            labelOrWB.setVisible(true);
            labelOrSW.setVisible(true);
            labelOrSO.setVisible(true);
            labelOrSB.setVisible(true);
            labelOrWO.setVisible(true);
            labelOrBO.setVisible(true);

            labelOr.setText("->Res");
            or = true;

        }else {
            labelBrick.setVisible(true);
            labelOre.setVisible(true);
            labelStone.setVisible(true);
            labelWood.setVisible(true);
            labelTextil.setVisible(true);
            labelGlass.setVisible(true);
            labelPapyrus.setVisible(true);
            imageBrick.setVisible(true);
            imageOre.setVisible(true);
            imageStone.setVisible(true);
            imageWood.setVisible(true);
            imageTextil.setVisible(true);
            imageGlass.setVisible(true);
            imagePapyrus.setVisible(true);

            imageOrWB.setVisible(false);
            imageOrSB.setVisible(false);
            imageOrBO.setVisible(false);
            imageOrSW.setVisible(false);
            imageOrWO.setVisible(false);
            imageOrSO.setVisible(false);
            labelOrWB.setVisible(false);
            labelOrSW.setVisible(false);
            labelOrSO.setVisible(false);
            labelOrSB.setVisible(false);
            labelOrWO.setVisible(false);
            labelOrBO.setVisible(false);

            labelOr.setText("->OrRes");
            or = false;

        }
    }

    @FXML
    void methodeUno() {
        Card select = wonder.getSelctedCard();
        liste.getItems().clear();
        if (select != null) {
            ImageView imageView = new ImageView(new Image(this.getClass().getResourceAsStream(select.getImage())));
            imageView.setFitHeight(150.0);
            imageView.setFitWidth(98.0);
            liste.getItems().addAll(imageView);
        }
        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }
    }
    @FXML
    void methodeHand() {
        liste.getItems().clear();
        LinkedList<Card> playerHand = wonder.getPlayerHand();
        LinkedList<ImageView> images = new LinkedList<>();
        ImageView imageView = new ImageView();
        for (Card m : playerHand) {
            imageView = new ImageView(new Image(this.getClass().getResourceAsStream(m.getImage())));
            imageView.setFitHeight(150.0);
            imageView.setFitWidth(98.0);
            images.add(imageView);
        }
        ObservableList<ImageView> items = FXCollections.observableList(images);
        liste.getItems().addAll(items);


        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }

    }
    @FXML
     void methodeConflict() {
        labelConflict.setVisible(true);
        int a = wonder.getCMPlus() - wonder.getCMMinus();
        labelConflict.setText("" + a);
    }


    @FXML
    void methodeClickMP() {
        redView.clear();
        for (Card a : redBuild){
            ImageView imageView = new ImageView();
            imageView = new ImageView(new Image(this.getClass().getResourceAsStream(a.getImage())));
            imageView.setFitHeight(160);
            imageView.setFitWidth(100);
            redView.add(imageView);
        }
        liste.getItems().clear();
        liste.getItems().addAll(redView);
        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }
    }

    @FXML
    void methodeClickVP() {
        blueView.clear();
        for (Card a : blueBuild){
            ImageView imageView = new ImageView();
            imageView = new ImageView(new Image(this.getClass().getResourceAsStream(a.getImage())));
            imageView.setFitHeight(160);
            imageView.setFitWidth(100);
            blueView.add(imageView);
        }

        liste.getItems().clear();
        liste.getItems().addAll(blueView);
        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }
    }


    @FXML
    void methodeClickBrown() {
        brownView.clear();
        for (Card a : brownBuild){
            ImageView imageView = new ImageView();
            imageView = new ImageView(new Image(this.getClass().getResourceAsStream(a.getImage())));
            imageView.setFitHeight(160);
            imageView.setFitWidth(100);
           brownView.add(imageView);
        }

        liste.getItems().clear();
        liste.getItems().addAll(brownView);
        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }

    }

    @FXML
    void methodeClickGrey() {
        greyView.clear();
        for (Card a : greyBuild){
            ImageView imageView = new ImageView();
            imageView = new ImageView(new Image(this.getClass().getResourceAsStream(a.getImage())));
            imageView.setFitHeight(160);
            imageView.setFitWidth(100);
            greyView.add(imageView);
        }

        liste.getItems().clear();
        liste.getItems().addAll(greyView);
        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }

    }

    @FXML
    void methodeClickYellow() {
        yellowView.clear();
        for (Card a : yellowBuild){
            ImageView imageView = new ImageView();
            imageView = new ImageView(new Image(this.getClass().getResourceAsStream(a.getImage())));
            imageView.setFitHeight(160);
            imageView.setFitWidth(100);
            yellowView.add(imageView);
        }

        liste.getItems().clear();
        liste.getItems().addAll(yellowView);
        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }

    }

    @FXML
    void methodeClickGreen() {
        greenView.clear();
        for (Card a : greenBuild){
            ImageView imageView = new ImageView();
            imageView = new ImageView(new Image(this.getClass().getResourceAsStream(a.getImage())));
            imageView.setFitHeight(160);
            imageView.setFitWidth(100);
            greenView.add(imageView);
        }

        liste.getItems().clear();
        liste.getItems().addAll(greenView);
        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }

    }

    @FXML
    void methodeClickViolett() {
        violettView.clear();
        for (Card a : violettBuild){
            ImageView imageView = new ImageView();
            imageView = new ImageView(new Image(this.getClass().getResourceAsStream(a.getImage())));
            imageView.setFitHeight(160);
            imageView.setFitWidth(100);
            violettView.add(imageView);
        }

        liste.getItems().clear();
        liste.getItems().addAll(violettView);
        if (liste.isVisible()) {
            liste.setVisible(false);
        }else {
            liste.setVisible(true);
        }

    }


    @FXML
    void stage1Clicked(MouseEvent event) {
        if (this.imageViewStage1.getImage() != null) {
            Stage[] stages = this.wonder.getStages();
            ImageView imageView = new ImageView(new Image(this.getClass().getResourceAsStream(stages[0].getBuildMarker().getImage())));
            imageView.setFitHeight(150.0);
            imageView.setFitWidth(98.0);
            LinkedList<ImageView> images = new LinkedList<>();
            images.add(imageView);
            ObservableList<ImageView> items = FXCollections.observableList(images);
            liste.getItems().clear();
            liste.setItems(items);
            if (liste.isVisible()) {
                liste.setVisible(false);
            }else {
                liste.setVisible(true);
            }
        }
    }

    @FXML
    void stage2Clicked(MouseEvent event) {
        if (this.imageViewStage2.getImage() != null) {
            Stage[] stages = this.wonder.getStages();
            ImageView imageView = new ImageView(new Image(this.getClass().getResourceAsStream(stages[1].getBuildMarker().getImage())));
            imageView.setFitHeight(150.0);
            imageView.setFitWidth(98.0);
            LinkedList<ImageView> images = new LinkedList<>();
            images.add(imageView);
            ObservableList<ImageView> items = FXCollections.observableList(images);
            liste.getItems().clear();
            liste.setItems(items);
            if (liste.isVisible()) {
                liste.setVisible(false);
            }else {
                liste.setVisible(true);
            }
        }
    }

    @FXML
    void stage3Clicked(MouseEvent event) {
        if (this.imageViewStage3.getImage() != null) {
            Stage[] stages = this.wonder.getStages();
            ImageView imageView = new ImageView(new Image(this.getClass().getResourceAsStream(stages[2].getBuildMarker().getImage())));
            imageView.setFitHeight(150.0);
            imageView.setFitWidth(98.0);
            LinkedList<ImageView> images = new LinkedList<>();
            images.add(imageView);
            ObservableList<ImageView> items = FXCollections.observableList(images);
            liste.getItems().clear();
            liste.setItems(items);
            if (liste.isVisible()) {
                liste.setVisible(false);
            }else {
                liste.setVisible(true);
            }
        }
    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        this.imageWonder.setImage(new Image(this.getClass().getResourceAsStream(this.wonder.getImage())));
        assert imageWonder != null : "fx:id=\"imageWonder\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert brownCards != null : "fx:id=\"brownCards\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert greyCards != null : "fx:id=\"greyCards\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert yellowCards != null : "fx:id=\"yellowCards\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert violettCards != null : "fx:id=\"violettCards\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert greenCards != null : "fx:id=\"greenCards\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert hand != null : "fx:id=\"hand\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert wonderResources != null : "fx:id=\"wonderResources\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert conflictPoints != null : "fx:id=\"conflictPoints\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert mp != null : "fx:id=\"mp\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert vp != null : "fx:id=\"vp\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert coin != null : "fx:id=\"coin\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert labelBrown != null : "fx:id=\"labelBrown\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert labelGrey != null : "fx:id=\"labelGrey\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert labelYellow != null : "fx:id=\"labelYellow\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert labelGreen != null : "fx:id=\"labelGreen\" was not injected: check your FXML file 'WonderView.fxml'.";
        assert labelViolett != null : "fx:id=\"labelViolett\" was not injected: check your FXML file 'WonderView.fxml'.";
        liste.setVisible(false);

        fillLists();
        setRes();

        switch (this.wonder.getWonderName().toString()) {
            case "RHODES": this.wonderResources.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/minicards/rs2.png"))); break;
            case "ALEXANDRIA": this.wonderResources.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/minicards/rs6.png"))); break;
            case "EPHESUS": this.wonderResources.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/minicards/rs7.png"))); break;
            case "BABYLON": this.wonderResources.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/minicards/rs1.png"))); break;
            case "OLYMPIA": this.wonderResources.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/minicards/rs4.png"))); break;
            case "HALICARNASSUS": this.wonderResources.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/minicards/rs5.png"))); break;
            case "GIZA": this.wonderResources.setImage(new Image(this.getClass().getResourceAsStream("/gui/image/minicards/rs3.png"))); break;
        }

        boolean isCurrentPlayer = this.boardViewController.getMain().getMainController().getBoardController().getCurrentPlayer().getWonderName().equals(this.wonder.getWonderName());
        if (isCurrentPlayer) {
            InnerShadow shadow = new InnerShadow(BlurType.THREE_PASS_BOX, Color.GOLD, 10, 1, 0,0);
            shadow.setHeight(15);
            shadow.setWidth(15);
            this.imageWonder.setEffect(shadow);
        }

        Stage[] stages = this.wonder.getStages();
        int currentStage = this.wonder.getCurrentStage();
        for (int i = 0; i < currentStage; i++) {
            switch (i) {
                case 0: this.imageViewStage1.setImage(new Image(this.getClass().getResourceAsStream(stages[0].getFile()))); break;
                case 1: this.imageViewStage2.setImage(new Image(this.getClass().getResourceAsStream(stages[1].getFile()))); break;
                case 2: this.imageViewStage3.setImage(new Image(this.getClass().getResourceAsStream(stages[2].getFile()))); break;
                default: throw new IndexOutOfBoundsException("WonderViewController: Index für die Stage ist größer 2");
            }
        }
        this.labelCoins.setText(Integer.toString(this.wonder.getCoins()));
        this.labelPlayerName.setText(this.wonder.getPlayerName());

    }
}

