package model;

import java.io.Serializable;

/**
 * The type Add other res effect.
 * @author Tobias
 */
class AddOtherResEffect implements Effect , Serializable {

    /**
     * The Res.
     */
    OtherResources res;
    /**
     * The Anz.
     */
    int anz;

    /**
     * Instantiates a new Add other res effect.
     *
     * @param res the res
     * @param anz the anz
     */
    public AddOtherResEffect(OtherResources res , int anz){
    this.res=res;
    this.anz=anz;
    }

    /**
     *
     * @param wonder
     */
    @Override
    public void effect(Wonder wonder) {
        switch (res){
            case VP:        System.err.println("VP sollen anders übergeben werden");break;
            case COIN:      wonder.addCoins(anz);break;
            case GEAR:      wonder.addGears(anz);break;
            case COMPASS:   wonder.addCompasses(anz);break;
            case SHIELD:    wonder.addShields(anz);break;
            case TABLET:    wonder.addTablets(anz);break;
            case CMPLUS:    wonder.addCMPlus(anz);break;
            case CMMINUS:   wonder.addCMMinus(anz);break;
        }
    }
}
