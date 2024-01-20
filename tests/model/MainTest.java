package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * The type Main test.
 */
public class MainTest {

	private Main main;
	private Game game;

	/**
	 * Erzeugt vor jeder Test-Methode eine neue Testumgebung.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		 	this.main = new Main();
	 		this.game = new Game();
			main.setGame(game);
	 	}

	/**
	 * Test Konstruktor.
	 */
	@Test
	public void test() {
		assertNotNull(main.getGame());
		assertNotNull(main.getHighscores());
	}

	/**
	 * setGame
	 */
	@Test
	public void testSetGame() {
		main.setGame(game);
		assertEquals(game,main.getGame());
		
	}

	/**
	 * Test get game.
	 */
	@Test
	public void testGetGame() {
		assertEquals(game, main.getGame());
	}

	/**
	 * Test get highscore.
	 */
	@Test
	public void testGetHighscore() {
		assertNotNull(main.getHighscores());
	}
}
