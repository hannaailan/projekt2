package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * The type Game test.
 */
public class GameTest {
		
	private Game g;


	/**
	 * Erzeugt vor jeder Test-Methode eine neue Testumgebung.
	 *
	 * @throws Exception Mögliche Exceptions beim setUp.
	 */
	@Before
	    public void setUp() throws Exception {
	 		g = new Game();
	 	}

	/**
	 * Test Konstruktor.
	 */
	@Test
	 	public void test() {
	 		assertNotNull(g.getGameHistory());
			assertNotNull(g.getRedo());
			
	 		
	 	}
			

	 	

}
