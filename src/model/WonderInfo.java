package model;
import java.io.*;
import java.net.URISyntaxException;
import java.util.EnumMap;

/**
 * The enum Wonder info.
 * @author Tobias
 */
public enum WonderInfo {


    /**
     * The RHODES.
     */
    RHODES {
        /**
         * erstellt ein neues Wunder des ntsprechenden Typs
         * @return das Wunder
         */
        public Wonder buildBoard() {
            Wonder wonder = new Wonder(RHODES,BuildResources.ORE);
            wonder.setImage("/gui/image/backgrounds/board0.jpg");

            EnumMap<BuildResources,Integer> empty = new EnumMap<>(BuildResources.class);
            for(BuildResources buildResource : BuildResources.values()){
                empty.put(buildResource,0);
            }
            EnumMap<BuildResources,Integer> temp1 = empty.clone();
            temp1.put(BuildResources.WOOD,2);
            wonder.addStage(new Stage(temp1,new AddVpwithColor(CardColor.BROWN,3),1));

            EnumMap<BuildResources,Integer> temp2 = empty.clone();
            temp2.put(BuildResources.BRICK,3);
            wonder.addStage(new Stage(temp2,new AddOtherResEffect(OtherResources.SHIELD,2),2));

            EnumMap<BuildResources,Integer> temp3 = empty.clone();
            temp3.put(BuildResources.ORE,4);
            wonder.addStage(new Stage(temp3,new AddVpwithColor(CardColor.BROWN,7),3));

            return wonder;
        }
    },

    /**
     * The ALEXANDRIA.
     */
    ALEXANDRIA {
        /**
         * erstellt ein neues Wunder des ntsprechenden Typs
         * @return das Wunder
         */
        public Wonder buildBoard() {
            Wonder wonder = new Wonder(ALEXANDRIA,BuildResources.GLASS);
            wonder.setImage("/gui/image/backgrounds/board1.jpg");

            EnumMap<BuildResources,Integer> empty = new EnumMap<>(BuildResources.class);
            for(BuildResources buildResource : BuildResources.values()){
                empty.put(buildResource,0);
            }
            EnumMap<BuildResources,Integer> temp1 = empty.clone();
            temp1.put(BuildResources.STONE,2);
            wonder.addStage(new Stage(temp1,new AddVpwithColor(CardColor.BROWN,3),1));

            EnumMap<BuildResources,Integer> temp2 = empty.clone();
            temp2.put(BuildResources.ORE,2);
            wonder.addStage(new Stage(temp2,new AddOrResEffect(false,BuildResources.BRICK,BuildResources.STONE,BuildResources.ORE,BuildResources.WOOD),2));

            EnumMap<BuildResources,Integer> temp3 = empty.clone();
            temp3.put(BuildResources.GLASS,2);
            wonder.addStage(new Stage(temp3,new AddVpwithColor(CardColor.BROWN,7),3));

            return wonder;
        }
    },

    /**
     * The EPHESUS.
     */
    EPHESUS {
        /**
         * erstellt ein neues Wunder des ntsprechenden Typs
         * @return das Wunder
         */
        public Wonder buildBoard() {
            Wonder wonder = new Wonder(EPHESUS,BuildResources.PAPYRUS);
            wonder.setImage("/gui/image/backgrounds/board2.jpg");

            EnumMap<BuildResources,Integer> empty = new EnumMap<>(BuildResources.class);
            for(BuildResources buildResource : BuildResources.values()){
                empty.put(buildResource,0);
            }
            EnumMap<BuildResources,Integer> temp1 = empty.clone();
            temp1.put(BuildResources.STONE,2);
            wonder.addStage(new Stage(temp1,new AddVpwithColor(CardColor.BROWN,3),1));

            EnumMap<BuildResources,Integer> temp2 = empty.clone();
            temp2.put(BuildResources.WOOD,2);
            wonder.addStage(new Stage(temp2,new AddOtherResEffect(OtherResources.COIN,9),2));

            EnumMap<BuildResources,Integer> temp3 = empty.clone();
            temp3.put(BuildResources.PAPYRUS,2);
            wonder.addStage(new Stage(temp3,new AddVpwithColor(CardColor.BROWN,7),3));

            return wonder;
        }
    },

    /**
     * The BABYLON.
     */
    BABYLON{
        /**
         * erstellt ein neues Wunder des ntsprechenden Typs
         * @return das Wunder
         */
        public Wonder buildBoard() {
            Wonder wonder = new Wonder(BABYLON, BuildResources.BRICK);
            wonder.setImage("/gui/image/backgrounds/board3.jpg");

            EnumMap<BuildResources,Integer> empty = new EnumMap<>(BuildResources.class);
            for(BuildResources buildResource : BuildResources.values()){
                empty.put(buildResource,0);
            }
            EnumMap<BuildResources,Integer> temp1 = empty.clone();
            temp1.put(BuildResources.BRICK, 2);
            wonder.addStage(new Stage(temp1,new AddVpwithColor(CardColor.BROWN,3),1));

            EnumMap<BuildResources,Integer> temp2 = empty.clone();
            temp2.put(BuildResources.WOOD, 3);
            wonder.addStage(new Stage(temp2,new AddScienceEffect(),2));

            EnumMap<BuildResources,Integer> temp3 = empty.clone();
            temp3.put(BuildResources.BRICK, 4);
            wonder.addStage(new Stage(temp3,new AddVpwithColor(CardColor.BROWN,7),3));

            return wonder;
        }
    },

    /**
     * The OYLMPIA:
     */
    OLYMPIA{
        /**
         * erstellt ein neues Wunder des ntsprechenden Typs
         * @return das Wunder
         */
        public Wonder buildBoard() {
            Wonder wonder = new Wonder(OLYMPIA, BuildResources.WOOD);
            wonder.setImage("/gui/image/backgrounds/board4.jpg");

            EnumMap<BuildResources,Integer> empty = new EnumMap<>(BuildResources.class);
            for(BuildResources buildResource : BuildResources.values()){
                empty.put(buildResource,0);
            }
            EnumMap<BuildResources,Integer> temp1 = empty.clone();
            temp1.put(BuildResources.WOOD, 2);
            wonder.addStage(new Stage(temp1,new AddVpwithColor(CardColor.BROWN,3),1));

            EnumMap<BuildResources,Integer> temp2 = empty.clone();
            temp2.put(BuildResources.STONE, 2);
            wonder.addStage(new Stage(temp2,new AddZeusEffect(),2));

            EnumMap<BuildResources,Integer> temp3 = empty.clone();
            temp3.put(BuildResources.ORE, 2);
            wonder.addStage(new Stage(temp3,new AddVpwithColor(CardColor.BROWN,7),3));

            return wonder;
        }
    },

    /**
     * The HALICARNASSUS.
     */
    HALICARNASSUS{
        /**
         * erstellt ein neues Wunder des ntsprechenden Typs
         * @return das Wunder
         */
        public Wonder buildBoard() {
            Wonder wonder = new Wonder(HALICARNASSUS, BuildResources.TEXTILE);
            wonder.setImage("/gui/image/backgrounds/board5.jpg");

            EnumMap<BuildResources,Integer> empty = new EnumMap<>(BuildResources.class);
            for(BuildResources buildResource : BuildResources.values()){
                empty.put(buildResource,0);
            }
            EnumMap<BuildResources,Integer> temp1 = empty.clone();
            temp1.put(BuildResources.BRICK, 2);
            wonder.addStage(new Stage(temp1,new AddVpwithColor(CardColor.BROWN,3),1));

            EnumMap<BuildResources,Integer> temp2 = empty.clone();
            temp2.put(BuildResources.ORE, 3);
            wonder.addStage(new Stage(temp2,new AddHalicanassosEffect(),2));

            EnumMap<BuildResources,Integer> temp3 = empty.clone();
            temp3.put(BuildResources.TEXTILE, 2);
            wonder.addStage(new Stage(temp3,new AddVpwithColor(CardColor.BROWN,7),3));

            return wonder;
        }
    },

    /**
     * The GIZA.
     */
    GIZA{
        /**
         * erstellt ein neues Wunder des ntsprechenden Typs
         * @return das Wunder
         */
        public Wonder buildBoard() {
            Wonder wonder = new Wonder(GIZA, BuildResources.STONE);
            wonder.setImage("/gui/image/backgrounds/board6.jpg");

            EnumMap<BuildResources,Integer> empty = new EnumMap<>(BuildResources.class);
            for(BuildResources buildResource : BuildResources.values()){
                empty.put(buildResource,0);
            }
            EnumMap<BuildResources,Integer> temp1 = empty.clone();
            temp1.put(BuildResources.STONE, 2);
            wonder.addStage(new Stage(temp1,new AddVpwithColor(CardColor.BROWN,3),1));

            EnumMap<BuildResources,Integer> temp2 = empty.clone();
            temp2.put(BuildResources.WOOD, 3);
            wonder.addStage(new Stage(temp2,new AddVpwithColor(CardColor.BROWN,5),2));

            EnumMap<BuildResources,Integer> temp3 = empty.clone();
            temp3.put(BuildResources.STONE, 4);
            wonder.addStage(new Stage(temp3,new AddVpwithColor(CardColor.BROWN,7),3));

            return wonder;
        }
    };

    /**
     * Build board wonder.
     *
     * @return the wonder
     */
    public abstract Wonder buildBoard();

    /**
     * add Halicanassos
     */
    private class AddHalicanassosEffect implements  Effect, Serializable{

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            wonder.getBoardState().activateHalikarnassos();
        }
    }

    /**
     * add Zeus
     */
    private class AddZeusEffect implements  Effect, Serializable {

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            wonder.enableZeus();
        }
    }
}
