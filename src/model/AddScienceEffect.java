package model;

import java.io.Serializable;

/**
 * The type Add science effect.
 *
 * @author Tobias
 */
class AddScienceEffect implements Effect, Serializable {
    /**
     * der Effect
     *
     * @param wonder
     */
    @Override
    public void effect(Wonder wonder) {
        wonder.setNewScience(wonder.getNewScience()+1);
    }

}
