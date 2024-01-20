package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The type Wonder info test.
 *
 * @author Tobias
 */
public class WonderInfoTest {


    /**
     * Tested Build Board
     */
    @Test
    public void buildBoardTest() {
        for(WonderInfo wonder : WonderInfo.values())
            assertNotNull(wonder.buildBoard());
    }
}