package controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import model.Main;


/**
 * The type Main controller test.
 */
public class MainControllerTest {
		
		private MainController mc;
		private Main m;


	/**
	 * setUp der Testumgebung vor dem Testen aller Methoden
	 *
	 * @throws Exception the exception
	 */
	@Before
			public void setUp() throws Exception {
				mc = new MainController();
				m = new Main();
			}

	/**
	 * Testet den MainController-Konstruktor.
	 */
	@Test
			public void test() {
				assertNotNull(mc.getaIController() );
				assertNotNull(mc.getBoardController());
				assertNotNull(mc.getHighscoreController());
				assertNotNull(mc.getiOController());		
				assertNotNull(mc.getSetupController());
				assertNotNull(mc.getWonderController());
				assertNotNull(mc.getMain());
			}
			
		

	}



