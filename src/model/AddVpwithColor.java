package model;

import java.io.Serializable;

/**
 * The type Add vpwith color.
 */
public class AddVpwithColor implements Effect, Serializable {
    /**
     * The Card color.
     */
    CardColor cardColor;
    /**
     * The Anz.
     */
    int anz;

    /**
     * Instantiates a new Add vpwith color.
     *
     * @param cardColor the card color
     * @param anz       the anz
     */
    public AddVpwithColor(CardColor cardColor,int anz){
        this.cardColor=cardColor;
        this.anz=anz;
    }

    /**
     *
     * @param wonder the wonder
     */
    @Override
    public void effect(Wonder wonder) {
        wonder.addVPs(anz);
        wonder.addColorVP(cardColor,anz);
    }
}
