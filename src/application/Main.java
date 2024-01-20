package application;

import controller.MainController;
import gui.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Paint;
import javafx.util.Pair;
import model.Wonder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;


public class Main extends Application {
	private Stage primaryStage;
	private BorderPane borderPane = new BorderPane();
	private int width, height;
	private MainController mainController;
	public static PrintWriter pWriter;
	private static String protokollPath="protokoll.txt";

	@Override
	public void start(Stage primaryStage) {
			this.primaryStage = primaryStage;
			this.borderPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), CornerRadii.EMPTY, Insets.EMPTY)));
			Scene scene = new Scene(this.borderPane);
			this.primaryStage.setScene(scene);
			this.primaryStage.setFullScreen(true);
			this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
			this.primaryStage.show();

			int width = (int) primaryStage.getWidth();
			int height = (int) primaryStage.getHeight();
			if (height <= 1080 && width <= 1920) {
				this.height = height;
				this.width = width;
			} else {
				this.height = 1080;
				this.width = 1920;
			}

			this.mainController = new MainController();
			this.mainController.getiOController().loadMain();

			System.out.println("width: " + this.width);
			System.out.println("height: " + this.height);
			loadStartView();
			try {
				pWriter = new PrintWriter(new FileWriter(protokollPath, true), true);
			} catch (IOException e){
				if (pWriter != null){
					pWriter.flush();
					pWriter.close();
				}
				e.printStackTrace();
			}
	}

	public void loadStartView() {
		StartViewController startViewController = new StartViewController(this, this.width, this.height);
		this.borderPane.setCenter(startViewController);
	}

	public void loadHighscoreView() {
		HighscoreViewController highscoreViewController = new HighscoreViewController(this, this.width, this.height);
		this.borderPane.setCenter(highscoreViewController);
	}

	public void loadLoadGameView() {
		LoadGameViewController loadGameViewController = new LoadGameViewController(this, this.width, this.height);
		this.borderPane.setCenter(loadGameViewController);
	}


	public void loadNewGameView() {
		NewGameViewController newGameViewController = new NewGameViewController(this, this.width, this.height);
		this.borderPane.setCenter(newGameViewController);
	}

	public void loadBoardView() {
		BoardViewController boardViewController = new BoardViewController(this, this.width, this.height);
		this.borderPane.setCenter(boardViewController);
	}

	public void loadEndGameView() {
		LinkedList<Wonder> players = this.mainController.getBoardController().getPlayerList();
		EndGameViewController endGameViewController = new EndGameViewController(this, this.width, this.height, players);
		this.borderPane.setCenter(endGameViewController);
	}

	public void loadNewTournamentView() {
		NewTournamentViewController newTournamentViewController = new NewTournamentViewController(this, this.width, this.height);
		this.borderPane.setCenter(newTournamentViewController);
	}

	public void closeStage() {
		this.primaryStage.close();
	}

	@Override
	public void stop(){
		System.out.println("Application Main: Stage is closing");
		if (pWriter != null){
			pWriter.flush();
			pWriter.close();
		}
		this.mainController.getiOController().saveMain();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public MainController getMainController() {
		return mainController;
	}
}
