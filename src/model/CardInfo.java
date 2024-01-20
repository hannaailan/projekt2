package model;


import java.io.File;
import java.io.Serializable;
import java.util.EnumMap;

/**
 * The enum Card info.
 * @author Tobias , Nenard
 */
public enum CardInfo {



    // age 3 violet cards


    /**
     * The Workers guild.
     */
    WORKERS_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE,2);
            cost.put(BuildResources.BRICK, 1);
            cost.put(BuildResources.STONE,1);
            cost.put(BuildResources.WOOD,1);
            return new Card(WORKERS_GUILD ,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{},new AddVPByColorEffect(CardColor.BROWN,1),"/gui/image/card/workersguild.png");
        }
    },
    /**
     * The Craftsmen guild.
     */
    CRAFTSMEN_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE,2);
            cost.put(BuildResources.STONE,2);
            return new Card(CRAFTSMEN_GUILD,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{},new AddVPByColorEffect(CardColor.GREY,2),"/gui/image/card/craftsmensguild.png");
        }
    },
    /**
     * The Traders guild.
     */
    TRADERS_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.PAPYRUS,1);
            return new Card(TRADERS_GUILD,cost,CardColor.VIOLET, new CardInfo[]{},new CardInfo[]{},new AddVPByColorEffect(CardColor.YELLOW,1),"/gui/image/card/tradersguild.png");
        }
    },
    /**
     * The Philosophers guild.
     */
    PHILOSOPHERS_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK,3);
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.PAPYRUS,1);
            return new Card(PHILOSOPHERS_GUILD,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{},new AddVPByColorEffect(CardColor.GREEN,1),"/gui/image/card/philosophersguild.png");
        }
    },
    /**
     * The Spies guild.
     */
    SPIES_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK,3);
            cost.put(BuildResources.GLASS,1);
            return new Card(SPIES_GUILD,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{},new AddVPByColorEffect(CardColor.RED,1),"/gui/image/card/spiesguild.png");
        }
    },
    /**
     * The Strategists guild.
     */
    STRATEGISTS_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE,2);
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.STONE,1);
            return new Card(STRATEGISTS_GUILD ,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{},new AddVPByNeightborDefeatEffect(),"/gui/image/card/strategistsguild.png");
        }
    },
    /**
     * The Shipowners guild.
     */
    SHIPOWNERS_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.WOOD,3);
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.PAPYRUS,1);
            return new Card(SHIPOWNERS_GUILD ,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{},new AddVPThreeColorEffect(),"/gui/image/card/shipownersguild.png");
        }
    },
    /**
     * The Scientists guild.
     */
    SCIENTISTS_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.WOOD,2);
            cost.put(BuildResources.ORE,2);
            cost.put(BuildResources.PAPYRUS,1);
            return new Card(SCIENTISTS_GUILD,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{}, new AddScienceEffect(),"/gui/image/card/scientistsguild.png");
        }
    },
    /**
     * The Magistrates guild.
     */
    MAGISTRATES_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.WOOD,3);
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.STONE,1);
            return new Card(MAGISTRATES_GUILD,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{},new AddVPByColorEffect(CardColor.BLUE,1),"/gui/image/card/magistratesguild.png");
        }
    },
    /**
     * The Builder guild.
     */
    BUILDERS_GUILD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.STONE,2);
            cost.put(BuildResources.BRICK,2);
            return new Card(BUILDERS_GUILD,cost,CardColor.VIOLET,new CardInfo[]{},new CardInfo[]{},new AddVPByStageEffect(),"/gui/image/card/buildersguild.png");
        }
    },

    // age 1 brown cards

    /**
     * The Lumber yard.
     */
    LUMBER_YARD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(LUMBER_YARD,
                            cost,
                            CardColor.BROWN,
                            new CardInfo[]{},
                            new CardInfo[]{},
                            new AddBuildResEffect(BuildResources.WOOD, 1),
                            "/gui/image/card/lumberyard.png");
        }
    },
    /**
     * The Stone pit.
     */
    STONE_PIT{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(STONE_PIT,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.STONE, 1),"/gui/image/card/stonepit.png");
        }
    },
    /**
     * The Clay pool.
     */
    CLAY_POOL{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(CLAY_POOL,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.BRICK, 1),"/gui/image/card/claypool.png");
        }
    },
    /**
     * The Ore vein.
     */
    ORE_VEIN{
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(ORE_VEIN,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.ORE, 1),"/gui/image/card/orevein.png");
        }
    },
    /**
     * The Tree farm.
     */
    TREE_FARM{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(TREE_FARM,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddOrResEffect(true, BuildResources.WOOD, BuildResources.BRICK),"/gui/image/card/treefarm.png");
        }
    },
    /**
     * The Excavation.
     */
    EXCAVATION{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(EXCAVATION,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddOrResEffect(true, BuildResources.STONE, BuildResources.BRICK),"/gui/image/card/excavation.png");
        }
    },
    /**
     * The Clay pit.
     */
    CLAY_PIT{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(CLAY_PIT,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddOrResEffect(true, BuildResources.ORE, BuildResources.BRICK),"/gui/image/card/claypit.png");
        }
    },
    /**
     * The Timber yard.
     */
    TIMBER_YARD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(TIMBER_YARD,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddOrResEffect(true, BuildResources.STONE, BuildResources.WOOD),"/gui/image/card/timberyard.png");
        }
    },
    /**
     * The Forest cave.
     */
    FOREST_CAVE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(FOREST_CAVE,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddOrResEffect(true, BuildResources.ORE, BuildResources.WOOD),"/gui/image/card/forestcave.png");
        }
    },
    /**
     * The Mine.
     */
    MINE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(MINE,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddOrResEffect(true, BuildResources.STONE, BuildResources.ORE),"/gui/image/card/mine.png");
        }
    },

    // age 2 brown cards

    /**
     * The Saw mill.
     */
    SAWMILL{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(SAWMILL,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.WOOD, 2),"/gui/image/card/sawmill.png");
        }
    },
    /**
     * The Quarry.
     */
    QUARRY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(QUARRY,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.STONE, 2),"/gui/image/card/quarry.png");
        }
    },
    /**
     * The Brick yard.
     */
    BRICKYARD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(BRICKYARD,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.BRICK, 2),"/gui/image/card/brickyard.png");
        }
    },
    /**
     * The Foundry.
     */
    FOUNDRY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.COIN, 1);
            return new Card(FOUNDRY,cost,CardColor.BROWN,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.ORE, 2),"/gui/image/card/foundry.png");
        }
    },

    // age 1 / 2 gray cards

    /**
     * The Loom.
     */
    LOOM{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(LOOM,cost,CardColor.GREY,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.TEXTILE, 1),"/gui/image/card/loom.png");
        }
    },
    /**
     * The Glass works.
     */
    GLASSWORKS{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(GLASSWORKS,cost,CardColor.GREY,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.GLASS, 1),"/gui/image/card/glassworks.png");
        }
    },
    /**
     * The Press.
     */
    PRESS{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(PRESS,cost,CardColor.GREY,new CardInfo[]{},new CardInfo[]{},new AddBuildResEffect(BuildResources.PAPYRUS, 1),"/gui/image/card/press.png");
        }
    },

    // age 1 red cards

    /**
     * The Stockade.
     */
    STOCKADE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.WOOD, 1);
            return new Card(STOCKADE,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{},new AddOtherResEffect(OtherResources.SHIELD, 1),"/gui/image/card/stockade.png");
        }
    },
    /**
     * The Barracks.
     */
    BARRACKS{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE, 1);
            return new Card(BARRACKS,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{},new AddOtherResEffect(OtherResources.SHIELD, 1),"/gui/image/card/barracks.png");
        }
    },
    /**
     * The Guard tower.
     */
    GUARD_TOWER{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK, 1);
            return new Card(GUARD_TOWER,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{},new AddOtherResEffect(OtherResources.SHIELD, 1),"/gui/image/card/guardtower.png");
        }
    },

    // age 2 red cards

    /**
     * The Walls.
     */
    WALLS{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.STONE, 3);
            return new Card(WALLS,cost,CardColor.RED,new CardInfo[]{FORTIFICATIONS},new CardInfo[]{},new AddOtherResEffect(OtherResources.SHIELD, 2),"/gui/image/card/walls.png");
        }
    },
    /**
     * The Training ground.
     */
    TRAINING_GROUND{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE, 2);
            cost.put(BuildResources.WOOD,1);
            return new Card(TRAINING_GROUND,cost,CardColor.RED,new CardInfo[]{CIRCUS},new CardInfo[]{},new AddOtherResEffect(OtherResources.SHIELD, 2),"/gui/image/card/trainingground.png");
        }
    },
    /**
     * The Stables.
     */
    STABLES{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK,1);
            cost.put(BuildResources.ORE, 1);
            cost.put(BuildResources.WOOD,1);
            return new Card(STABLES,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{APOTHECARY},new AddOtherResEffect(OtherResources.SHIELD, 2),"/gui/image/card/stables.png");
        }
    },
    /**
     * The Archery range.
     */
    ARCHERY_RANGE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE, 1);
            cost.put(BuildResources.WOOD,2);
            return new Card(ARCHERY_RANGE,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{WORKSHOP},new AddOtherResEffect(OtherResources.SHIELD, 2),"/gui/image/card/archeryrange.png");
        }
    },

    // age 3 red cards

    /**
     * The Fortification.
     */
    FORTIFICATIONS{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE, 3);
            cost.put(BuildResources.STONE,1);
            return new Card(FORTIFICATIONS,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{WALLS},new AddOtherResEffect(OtherResources.SHIELD, 3),"/gui/image/card/fortifications.png");
        }
    },
    /**
     * The Circus.
     */
    CIRCUS{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE, 1);
            cost.put(BuildResources.STONE,3);
            return new Card(CIRCUS,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{TRAINING_GROUND},new AddOtherResEffect(OtherResources.SHIELD, 3),"/gui/image/card/circus.png");
        }
    },
    /**
     * The Arsenal.
     */
    ARSENAL{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE, 1);
            cost.put(BuildResources.WOOD,2);
            cost.put(BuildResources.TEXTILE,1);
            return new Card(ARSENAL,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{},new AddOtherResEffect(OtherResources.SHIELD, 3),"/gui/image/card/arsenal.png");
        }
    },
    /**
     * The Siege workshop.
     */
    SIEGE_WORKSHOP{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK, 3);
            cost.put(BuildResources.WOOD,1);
            return new Card(SIEGE_WORKSHOP,cost,CardColor.RED,new CardInfo[]{},new CardInfo[]{LABORATORY},new AddOtherResEffect(OtherResources.SHIELD, 3),"/gui/image/card/siegeworkshop.png");
        }
    },

    // age 1 blue cards

    /**
     * The Pawnshop.
     */
    PAWNSHOP{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(PAWNSHOP,cost,CardColor.BLUE,new CardInfo[]{},new CardInfo[]{},new AddVpwithColor(CardColor.BLUE,3),"/gui/image/card/pawnshop.png");
        }
    },
    /**
     * The Baths.
     */
    BATHS{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.STONE,1);
            return new Card(BATHS,cost,CardColor.BLUE,new CardInfo[]{AQUEDUCT},new CardInfo[]{},new AddVpwithColor(CardColor.BLUE,3),"/gui/image/card/baths.png");
        }
    },
    /**
     * The Altar.
     */
    ALTAR{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(ALTAR,cost,CardColor.BLUE,new CardInfo[]{TEMPLE},new CardInfo[]{},new AddVpwithColor(CardColor.BLUE,2),"/gui/image/card/altar.png");
        }
    },
    /**
     * The Theater.
     */
    THEATER{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(THEATER,cost,CardColor.BLUE,new CardInfo[]{STATUE},new CardInfo[]{},new AddVpwithColor(CardColor.BLUE,2),"/gui/image/card/theater.png");
        }
    },

    // age 2 blue cards

    /**
     * The Aqueduct.
     */
    AQUEDUCT{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.STONE,3);
            return new Card(AQUEDUCT,cost,CardColor.BLUE,new CardInfo[]{},new CardInfo[]{BATHS},new AddVpwithColor(CardColor.BLUE,5),"/gui/image/card/aqueduct.png");
        }
    },
    /**
     * The Temple.
     */
    TEMPLE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.BRICK,1);
            cost.put(BuildResources.WOOD,1);
            return new Card(TEMPLE,cost,CardColor.BLUE,new CardInfo[]{PANTHEON},new CardInfo[]{ALTAR},new AddVpwithColor(CardColor.BLUE,3),"/gui/image/card/temple.png");
        }
    },
    /**
     * The Statue.
     */
    STATUE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE,2);
            cost.put(BuildResources.WOOD,1);
            return new Card(STATUE,cost,CardColor.BLUE,new CardInfo[]{GARDENS},new CardInfo[]{THEATER},new AddVpwithColor(CardColor.BLUE,4),"/gui/image/card/statue.png");
        }
    },
    /**
     * The Courthouse.
     */
    COURTHOUSE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK,2);
            cost.put(BuildResources.TEXTILE,1);
            return new Card(COURTHOUSE,cost,CardColor.BLUE,new CardInfo[]{},new CardInfo[]{SCRIPTORIUM},new AddVpwithColor(CardColor.BLUE,4),"/gui/image/card/courthouse.png");
        }
    },

    //age 3 blue cards

    /**
     * The Pantheon.
     */
    PANTHEON{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK,2);
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.ORE,1);
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.PAPYRUS,1);
            return new Card(PANTHEON,cost,CardColor.BLUE,new CardInfo[]{},new CardInfo[]{TEMPLE},new AddVpwithColor(CardColor.BLUE,7),"/gui/image/card/pantheon.png");
        }
    },
    /**
     * The Gardens.
     */
    GARDENS{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK,2);
            cost.put(BuildResources.WOOD,1);
            return new Card(GARDENS,cost,CardColor.BLUE,new CardInfo[]{},new CardInfo[]{STATUE},new AddVpwithColor(CardColor.BLUE,5),"/gui/image/card/gardens.png");
        }
    },
    /**
     * The Town hall.
     */
    TOWN_HALL{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.STONE,2);
            cost.put(BuildResources.ORE,1);
            cost.put(BuildResources.GLASS,1);
            return new Card(TOWN_HALL,cost,CardColor.BLUE,new CardInfo[]{},new CardInfo[]{},new AddVpwithColor(CardColor.BLUE,6),"/gui/image/card/townhall.png");
        }
    },
    /**
     * The Palace.
     */
    PALACE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.STONE,1);
            cost.put(BuildResources.WOOD,1);
            cost.put(BuildResources.BRICK,1);
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.ORE,1);
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.PAPYRUS,1);
            return new Card(PALACE,cost,CardColor.BLUE,new CardInfo[]{},new CardInfo[]{},new AddVpwithColor(CardColor.BLUE,8),"/gui/image/card/palace.png");
        }
    },
    /**
     * The Senate.
     */
    SENATE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.STONE,1);
            cost.put(BuildResources.WOOD,2);
            cost.put(BuildResources.ORE,1);
            return new Card(SENATE,cost,CardColor.BLUE,new CardInfo[]{},new CardInfo[]{LIBRARY},new AddVpwithColor(CardColor.BLUE,6),"/gui/image/card/senate.png");
        }
    },

    //age 1 yellow cards

    /**
     * The Tavern.
     */
    TAVERN{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(TAVERN,cost,CardColor.YELLOW,new CardInfo[]{},new CardInfo[]{},new AddOtherResEffect(OtherResources.COIN, 5),"/gui/image/card/tavern.png");
        }
    },
    /**
     * The East trading post.
     */
    EAST_TRADING_POST{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(EAST_TRADING_POST,cost,CardColor.YELLOW,new CardInfo[]{FORUM},new CardInfo[]{},new AddDiscountEffect(0),"/gui/image/card/easttradingpost.png");
        }
    },
    /**
     * The West trading post.
     */
    WEST_TRADING_POST{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(WEST_TRADING_POST,cost,CardColor.YELLOW,new CardInfo[]{FORUM},new CardInfo[]{},new AddDiscountEffect(1),"/gui/image/card/westtradingpost.png");
        }
    },
    /**
     * The Marketplace.
     */
    MARKETPLACE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(MARKETPLACE,cost,CardColor.YELLOW,new CardInfo[]{CARAVANSERY},new CardInfo[]{},new AddDiscountEffect(2),"/gui/image/card/marketplace.png");
        }
    },

    // age 2 yellow cards

    /**
     * The Forum.
     */
    FORUM{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.BRICK,2);
            return new Card(FORUM,cost,CardColor.YELLOW,new CardInfo[]{HAVEN},new CardInfo[]{EAST_TRADING_POST, WEST_TRADING_POST},new AddOrResEffect(false, BuildResources.GLASS, BuildResources.PAPYRUS, BuildResources.TEXTILE),"/gui/image/card/forum.png");
        }
    },
    /**
     * The Caravansery.
     */
    CARAVANSERY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.WOOD,2);
            return new Card(CARAVANSERY,cost,CardColor.YELLOW,new CardInfo[]{LIGHTHOUSE},new CardInfo[]{MARKETPLACE},new AddOrResEffect(false,BuildResources.BRICK, BuildResources.ORE, BuildResources.STONE, BuildResources.WOOD),"/gui/image/card/caravansery.png");
        }
    },
    /**
     * The Vineyard.
     */
    VINEYARD{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(VINEYARD,cost,CardColor.YELLOW,new CardInfo[]{},new CardInfo[]{},new AddCoinByColorEffect(CardColor.BROWN, 1),"/gui/image/card/vineyard.png");
        }
    },
    /**
     * The Bazar.
     */
    BAZAR{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            return new Card(BAZAR,cost,CardColor.YELLOW,new CardInfo[]{},new CardInfo[]{},new AddCoinByColorEffect(CardColor.GREY, 2),"/gui/image/card/bazar.png");
        }
    },

    // age 3 yellow cards

    /**
     * The Haven.
     */
    HAVEN{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.WOOD,1);
            cost.put(BuildResources.ORE,1);
            cost.put(BuildResources.TEXTILE,1);
            return new Card(HAVEN,cost,CardColor.YELLOW,new CardInfo[]{},new CardInfo[]{FORUM},new AddVPAndCoinByColorEffect(CardColor.BROWN, 1),"/gui/image/card/haven.png");
        }
    },
    /**
     * The Lighthouse.
     */
    LIGHTHOUSE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.STONE,1);
            return new Card(LIGHTHOUSE,cost,CardColor.YELLOW,new CardInfo[]{},new CardInfo[]{CARAVANSERY},new AddVPAndCoinByColorEffect(CardColor.YELLOW, 1),"/gui/image/card/lighthouse.png");
        }
    },
    /**
     * The Chamber of commerce.
     */
    CHAMBER_OF_COMMERCE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.PAPYRUS,1);
            cost.put(BuildResources.BRICK,2);
            return new Card(CHAMBER_OF_COMMERCE,cost,CardColor.YELLOW,new CardInfo[]{},new CardInfo[]{},new AddVPAndCoinByColorEffect(CardColor.GREY, 2),"/gui/image/card/chamberofcommerce.png");
        }
    },
    /**
     * The Arena.
     */
    ARENA{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.ORE,1);
            cost.put(BuildResources.STONE,2);
            return new Card(ARENA,cost,CardColor.YELLOW,new CardInfo[]{},new CardInfo[]{DISPENSARY},new AddVPAndCoinByStageEffect(),"/gui/image/card/arena.png");
        }
    },

    // age 1 green cards

    /**
     * The Apothecary.
     */
    APOTHECARY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.TEXTILE,1);
            return new Card(APOTHECARY,cost,CardColor.GREEN,new CardInfo[]{STABLES, DISPENSARY},new CardInfo[]{},new AddOtherResEffect(OtherResources.COMPASS, 1),"/gui/image/card/apothecary.png");
        }
    },
    /**
     * The Workshop.
     */
    WORKSHOP{
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.GLASS,1);
            return new Card(WORKSHOP,cost,CardColor.GREEN,new CardInfo[]{ARCHERY_RANGE, LABORATORY},new CardInfo[]{},new AddOtherResEffect(OtherResources.GEAR, 1),"/gui/image/card/workshop.png");
        }
    },
    /**
     * The Scriptorium.
     */
    SCRIPTORIUM{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.PAPYRUS,1);
            return new Card(SCRIPTORIUM,cost,CardColor.GREEN,new CardInfo[]{COURTHOUSE, LIBRARY},new CardInfo[]{},new AddOtherResEffect(OtherResources.TABLET, 1),"/gui/image/card/scriptorium.png");
        }
    },

    // age 2 green cards

    /**
     * The Dispensary.
     */
    DISPENSARY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.ORE,2);
            return new Card(DISPENSARY,cost,CardColor.GREEN,new CardInfo[]{ARENA, LODGE},new CardInfo[]{APOTHECARY},new AddOtherResEffect(OtherResources.COMPASS, 1),"/gui/image/card/dispensary.png");
        }
    },
    /**
     * The Laboratory.
     */
    LABORATORY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.PAPYRUS,1);
            cost.put(BuildResources.BRICK,2);
            return new Card(LABORATORY,cost,CardColor.GREEN,new CardInfo[]{SIEGE_WORKSHOP, OBSERVATORY},new CardInfo[]{WORKSHOP},new AddOtherResEffect(OtherResources.GEAR, 1),"/gui/image/card/laboratory.png");
        }
    },
    /**
     * The Library.
     */
    LIBRARY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.STONE,2);
            return new Card(LIBRARY,cost,CardColor.GREEN,new CardInfo[]{UNIVERSITY, SENATE},new CardInfo[]{SCRIPTORIUM},new AddOtherResEffect(OtherResources.TABLET, 1),"/gui/image/card/library.png");
        }
    },
    /**
     * The School.
     */
    SCHOOL{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.PAPYRUS,1);
            cost.put(BuildResources.WOOD,1);
            return new Card(SCHOOL,cost,CardColor.GREEN,new CardInfo[]{ACADEMY, STUDY},new CardInfo[]{},new AddOtherResEffect(OtherResources.TABLET, 1),"/gui/image/card/school.png");
        }
    },

    // age 3 green cards

    /**
     * The Lodge.
     */
    LODGE{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.PAPYRUS,1);
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.BRICK,2);
            return new Card(LODGE,cost,CardColor.GREEN,new CardInfo[]{},new CardInfo[]{DISPENSARY},new AddOtherResEffect(OtherResources.COMPASS, 1),"/gui/image/card/lodge.png");
        }
    },
    /**
     * The Observatory.
     */
    OBSERVATORY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.TEXTILE,1);
            cost.put(BuildResources.ORE,2);
            return new Card(OBSERVATORY,cost,CardColor.GREEN,new CardInfo[]{},new CardInfo[]{LABORATORY},new AddOtherResEffect(OtherResources.GEAR, 1),"/gui/image/card/observatory.png");
        }
    },
    /**
     * The University.
     */
    UNIVERSITY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.PAPYRUS,1);
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.WOOD,2);
            return new Card(UNIVERSITY,cost,CardColor.GREEN,new CardInfo[]{},new CardInfo[]{LIBRARY},new AddOtherResEffect(OtherResources.TABLET, 1),"/gui/image/card/university.png");
        }
    },
    /**
     * The Academy.
     */
    ACADEMY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.GLASS,1);
            cost.put(BuildResources.STONE,3);
            return new Card(ACADEMY,cost,CardColor.GREEN,new CardInfo[]{},new CardInfo[]{SCHOOL},new AddOtherResEffect(OtherResources.COMPASS, 1),"/gui/image/card/academy.png");
        }
    },
    /**
     * The Study.
     */
    STUDY{
        /**
         * Baut eine neue Kate das entsprechenden Typs
         * @return die Karte
         */
        @Override
        public Card buildCard() {
            EnumMap<BuildResources, Integer> cost = new EnumMap<>(BuildResources.class);
            for (BuildResources buildResource : BuildResources.values()) {
                cost.put(buildResource, 0);
            }
            cost.put(BuildResources.WOOD,1);
            cost.put(BuildResources.PAPYRUS,1);
            cost.put(BuildResources.TEXTILE,1);
            return new Card(STUDY,cost,CardColor.GREEN,new CardInfo[]{},new CardInfo[]{SCHOOL},new AddOtherResEffect(OtherResources.GEAR, 1),"/gui/image/card/study.png");
        }
    };

    /**
     * Build card card.
     *
     * @return the card
     */
    public abstract Card buildCard();


    /**
     * add VP By Color
     */
    private class AddVPByColorEffect implements Effect, AfterGameEffect,Serializable{

        /**
         * The Color.
         */
        CardColor color;
        /**
         * The Anz.
         */
        int anz;

        /**
         * Instantiates a new Add vp by color effect.
         *
         * @param color the color
         * @param anz   the anz
         */
        AddVPByColorEffect(CardColor color, int anz){
            this.color=color;
            this.anz=anz;
        }

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            wonder.getAfterGameEffects().add(this);
        }

        @Override
        public void afterGameEffect(Wonder wonder) {
            int cardanz=0;
            for(Wonder wonderindex : wonder.getNeighbours()){
                cardanz+=wonderindex.getCardColors()[color.ordinal()];
            }
            wonder.addVPs(cardanz*anz);
            wonder.addColorVP(CardColor.VIOLET,cardanz*anz);
        }
    }

    /**
     * add VP By Neightbor Defeat
     */
    private class AddVPByNeightborDefeatEffect implements Effect, AfterGameEffect,Serializable{

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
           wonder.getAfterGameEffects().add(this);
        }

        @Override
        public void afterGameEffect(Wonder wonder) {
            int defanz=0;
            for(Wonder wonderindex : wonder.getNeighbours()){
                defanz+=wonderindex.getCMMinus();
            }
            wonder.addVPs(defanz);
            wonder.addColorVP(CardColor.VIOLET,defanz);
        }
    }

    /**
     * add VP Three Color
     */
    private class AddVPThreeColorEffect implements Effect, AfterGameEffect,Serializable{

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            wonder.getAfterGameEffects().add(this);
        }

        @Override
        public void afterGameEffect(Wonder wonder) {
            int anz = wonder.getCardColors()[CardColor.BROWN.ordinal()]
                    +wonder.getCardColors()[CardColor.GREY.ordinal()]
                    +wonder.getCardColors()[CardColor.VIOLET.ordinal()];
            wonder.addVPs(anz);
            wonder.addColorVP(CardColor.VIOLET,anz);

        }
    }

    /**
     * add VP By Stage
     */
    private class AddVPByStageEffect implements Effect, AfterGameEffect,Serializable{

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            wonder.getAfterGameEffects().add(this);
        }


        @Override
        public void afterGameEffect(Wonder wonder) {
            int stageanz=0;
            for(Wonder wonderindex : wonder.getNeighbours()){
                stageanz+=wonderindex.getCurrentStage();
            }
            wonder.addVPs(stageanz+wonder.getCurrentStage());
            wonder.addColorVP(CardColor.VIOLET,stageanz+wonder.getCurrentStage());
        }
    }

    /**
     * add Discount
     */
    private class AddDiscountEffect implements Effect, Serializable{

        /**
         * 0=Right Raw
         * 1=Left Raw
         * 2=Both Manuf
         */
        int type;

        /**
         * Instantiates a new Add discount effect.
         *
         * @param type the type
         */
        public AddDiscountEffect(int type){
            this.type=type;
        }

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            int nill=0;int eins=1; int zwei=2;
            if(type==nill){
                wonder.getCostsResourcesRight().put(BuildResources.BRICK,1);
                wonder.getCostsResourcesRight().put(BuildResources.ORE,1);
                wonder.getCostsResourcesRight().put(BuildResources.STONE,1);
                wonder.getCostsResourcesRight().put(BuildResources.WOOD,1);
                int asd = wonder.getNeighbours().size();
                if (asd == eins){
                    wonder.getCostsResourcesLeft().put(BuildResources.BRICK,1);
                    wonder.getCostsResourcesLeft().put(BuildResources.ORE,1);
                    wonder.getCostsResourcesLeft().put(BuildResources.STONE,1);
                    wonder.getCostsResourcesLeft().put(BuildResources.WOOD,1);
                }
            }
            else if(type==eins){
                wonder.getCostsResourcesLeft().put(BuildResources.BRICK,1);
                wonder.getCostsResourcesLeft().put(BuildResources.ORE,1);
                wonder.getCostsResourcesLeft().put(BuildResources.STONE,1);
                wonder.getCostsResourcesLeft().put(BuildResources.WOOD,1);
            }
            else  if(type==zwei){
                wonder.getCostsResourcesRight().put(BuildResources.PAPYRUS,1);
                wonder.getCostsResourcesRight().put(BuildResources.GLASS,1);
                wonder.getCostsResourcesRight().put(BuildResources.TEXTILE,1);
                wonder.getCostsResourcesLeft().put(BuildResources.PAPYRUS,1);
                wonder.getCostsResourcesLeft().put(BuildResources.GLASS,1);
                wonder.getCostsResourcesLeft().put(BuildResources.TEXTILE,1);
            }


        }

    }

    /**
     * add Coin By Color
     */
    private class AddCoinByColorEffect implements Effect, Serializable{

        /**
         * The Color.
         */
        CardColor color;
        /**
         * The Anz.
         */
        int anz;

        /**
         * Instantiates a new Add coin by color effect.
         *
         * @param color the color
         * @param anz   the anz
         */
        public AddCoinByColorEffect(CardColor color, int anz){
            this.color=color;
            this.anz=anz;
        }

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            int cardanz=0;
            for(Wonder wonderindex : wonder.getNeighbours()){
                cardanz+=wonderindex.getCardColors()[color.ordinal()];
            }
            wonder.addCoins((cardanz+wonder.getCardColors()[color.ordinal()])*anz);
        }


    }

    /**
     * add VP And Coin By Color
     */
    private class AddVPAndCoinByColorEffect implements Effect, AfterGameEffect, Serializable{

        /**
         * The Color.
         */
        CardColor color;
        /**
         * The Anz.
         */
        int anz;

        /**
         * Instantiates a new Add vp and coin by color effect.
         *
         * @param color the color
         * @param anz   the anz
         */
        public AddVPAndCoinByColorEffect(CardColor color, int anz){
            this.color=color;
            this.anz=anz;
        }

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            int mehr=(wonder.getCardColors()[color.ordinal()]);
            if(color==CardColor.YELLOW)
                mehr+=1;
            wonder.addCoins((mehr*anz));
            wonder.getAfterGameEffects().add(this);
        }

        /**
         * der after Game Effekt
         * @param wonder the wonder
         */
        @Override
        public void afterGameEffect(Wonder wonder) {

            int mehr=(wonder.getCardColors()[color.ordinal()]);
            if(color==CardColor.YELLOW)
                mehr+=1;
            wonder.addVPs((mehr*anz));
            wonder.addColorVP(CardColor.YELLOW,(mehr*anz));

        }
    }

    /**
     * add VP And Coin By Stage
     */
    private class AddVPAndCoinByStageEffect implements Effect, AfterGameEffect, Serializable {

        /**
         * der Effekt
         * @param wonder the wonder
         */
        @Override
        public void effect(Wonder wonder) {
            wonder.addCoins(3*wonder.getCurrentStage());
            wonder.getAfterGameEffects().add(this);
        }

        /**
         * der after Game Effekt
         * @param wonder the wonder
         */
        @Override
        public void afterGameEffect(Wonder wonder) {
            wonder.addVPs(wonder.getCurrentStage());
            wonder.addColorVP(CardColor.YELLOW,wonder.getCurrentStage());
        }
    }
}
