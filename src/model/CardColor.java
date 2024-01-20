package model;

/**
 * The enum Card color.
 */
public enum CardColor {

    /**
     * Brown card color.
     */
    BROWN("brown",0),
    /**
     * Grey card color.
     */
    GREY("grey",1),
    /**
     * Blue card color.
     */
    BLUE("blue",2),
    /**
     * Green card color.
     */
    GREEN("green",3),
    /**
     * Yellow card color.
     */
    YELLOW("yellow",4),
    /**
     * Red card color.
     */
    RED("red",5),
    /**
     * Violet card color.
     */
    VIOLET("violet",6);


    private String color;


    private int number;

    /**
     * der Konstruktor
     * @param color
     * @param number
     */
    CardColor(String color,int number){
        this.color=color;
        this.number=number;
    }

    /**
     * Get color string.
     *
     * @return the string
     */
    String getColor(){return color;}

    /**
     * Get nr int.
     *
     * @return the int
     */
    int getNr(){return number;}
}
