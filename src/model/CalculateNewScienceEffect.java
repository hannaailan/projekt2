package model;


import java.io.Serializable;

/**
 * The type Calculate new science effect.
 */
public class CalculateNewScienceEffect implements Serializable,AfterGameEffect {
    @Override
    public void afterGameEffect(Wonder wonder) {
        int num = 2;
        if(wonder.getNewScience()==0)
            return;
        else if(wonder.getNewScience()==num){
            /*
            Strategie:
            falls 2 0 0 und Permutationen
             oder 1 0 0 und Permutationen
             oder 2 1 1 und Permutationen
             -> Set vervollständigen
             */
            int comp = wonder.getCompasses();
            int tabl = wonder.getTablets();
            int gear = wonder.getGears();
            if(comp<3 & tabl < comp & gear < comp & tabl == gear){
                wonder.addCompasses(1);
            } else if(tabl<3 & tabl > comp & gear < tabl & comp == gear){
                wonder.addTablets(1);
            } else if(gear<3 & tabl < gear & gear > comp & tabl == comp){
                wonder.addGears(1);
            } else
                distributeOneScience(wonder);

        }
        distributeOneScience(wonder);

    }
    private void distributeOneScience(Wonder wonder){
        /*
        Strategie:
        1. falls eine ForschungsRes >=3 -> auf den höhsten
        2. sonst Set vervollständigen
        3. sonst auf den höhsten
         */
        int comp = wonder.getCompasses();
        int tabl = wonder.getTablets();
        int gear = wonder.getGears();

        if (comp >= 3 | tabl >= 3 | gear >= 3) {
            if (comp > tabl & comp > gear)
                wonder.addCompasses(1);
            else if (tabl > comp & tabl > gear)
                wonder.addTablets(1);
            else
                wonder.addGears(1);
            return;
        }

        if (comp < tabl & comp < gear)
            wonder.addCompasses(1);
        else if (tabl < comp & tabl < gear)
            wonder.addTablets(1);
        else if (gear < comp & gear < tabl)
            wonder.addGears(1);
        else {

            if (comp > tabl & comp > gear)
                wonder.addCompasses(1);
            else if (tabl > comp & tabl > gear)
                wonder.addTablets(1);
            else
                wonder.addGears(1);
        }

    }
}
