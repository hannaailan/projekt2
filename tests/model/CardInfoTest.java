package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The type Card info test.
 * @author Tobias
 */
public class CardInfoTest {

    /**
     * Build card test.
     */
    @Test
    public void buildCardTest() {
        for(CardInfo card : CardInfo.values())
            assertNotNull(card.buildCard());
    }
}