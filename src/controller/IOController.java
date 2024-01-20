package controller;

import model.Game;
import model.Main;

import java.io.*;
import java.util.LinkedList;

/**
 * @author Tobias
 */
public class IOController {

    /**
 	 * der MainController
 	 */
    private MainController mainController;

    /**
     * Savedatei für die Main
     */
    private static final String MAIN_SAVE_FILE = "save.data";

    /**
     * Prefix für die Gamesave Dateien
     */
    private static final String GAMESAVE_PREFIX = "game";

    /**
     * Sofix für die Gamesave Dateien
     */
    private static final String GAMESAVE_SUFIX = ".game";

    /**
     * Konstruktor halt, hier passiet icht viel
     * @param mainController übergebener Maincontroer
     */
    public IOController(MainController mainController) {
        this.mainController=mainController;
    }

    /**
     * Hifsmethode mit Ausgabe
     * @param line auszugebende Zeile
     */
    private void out(String line){
        System.out.println("IO Controller: "+line);
    }
    /**
 	 * läd den gesammten programmstatus
     * also die main sammt Highscore sowie ewentuel dem aktuellen Game
 	 */
    public void loadMain() {
        if (!new File(MAIN_SAVE_FILE).exists()) {//TODO this.getClass().getResourceAsStream()
            return;
        }
        out("Lade Main");
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File(MAIN_SAVE_FILE)));

            Main main = (Main) stream.readObject();
            mainController.setMain(main);
            stream.close();
        } catch (Exception e) {
            System.err.println("Ladefehler bei der Main");
            e.printStackTrace();
        }

    }

    /**
 	 *
 	 * Speichert den gesammten programmstatus ab
     * also die main sammt Highscore sowie eventuel einem aktuellen Game
 	 */
    public void saveMain() {
        out("Speichere Main");
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File(MAIN_SAVE_FILE)));
            stream.writeObject(mainController.getMain());
            stream.close();
        }  catch (IOException e) {
            System.err.println("Speicherfehler in der Main");
            e.printStackTrace();
        }
    }

    /**
 	 * läd das Spiel in der File
     * läd lediglich das Game, eine Main mus vorhanden sein
     * @param gamefile die zu ladende Datei
 	 */
    public void loadGame(File gamefile) {
        if (!gamefile.exists()) {
            return;
        }
        out("Lade Game: "+gamefile.toString());
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(gamefile));
            Game game = (Game) stream.readObject();
            mainController.getMain().setGame(game);
            stream.close();
        } catch (Exception e) {
            System.err.println("Ladefehler bei Game: "+ gamefile.toString());
            e.printStackTrace();
        }
    }

    /**
     * speichert das Aktuelle Game in seperater File ab
 	 */
    public void saveGame() {
        out("Speichere aktuelles Game");
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(getNewGameSave()));
            stream.writeObject(mainController.getMain().getGame());
            stream.close();
        }  catch (IOException e) {
            System.err.println("Speicherfehler beim Game");
            e.printStackTrace();
        }
    }

    /**
     *
     * gibt eine List bereits exitierender GameSaves zurück
     * wird, glaub ich, nie verwendet
     * @return eine Liste aller Gamefiles
     */
    public static LinkedList<File> getGameFiles() {
        int filenr = 0;
        LinkedList<File> result = new LinkedList<>();
        File file;
        while((file = new File(GAMESAVE_PREFIX+(filenr++)+GAMESAVE_SUFIX)).exists())
            result.add(file);

        return result;
    }

    /**
     * gibt einen Dateipfad in dem ein neues Game abgespeichert werden kann
     * @return der Dateipfad
     */
    private File getNewGameSave(){
        int filenr = 0;

        File file;
        while((file = new File(GAMESAVE_PREFIX+(filenr++)+GAMESAVE_SUFIX)).exists()) {}

        return file;
    }


}
