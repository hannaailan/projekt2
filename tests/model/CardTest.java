package model;

import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.EnumMap;

/**
 * testet die klasse model.card
 *
 */
public class CardTest {
	/**
	 * die karte 
	 */private Card card;
	/**
	 * die name 
	 */
	private CardInfo name;
	/**
	 * die kosten
	 */
	private EnumMap<BuildResources, Integer> costs;
	/**
	 * die farbe
	 */
	private CardColor color;
	/**
	 * die buildsfree
	 */
	private CardInfo[] buildsFree;
	/**
	 * die buildFreeBy
	 */
	private CardInfo[] buildFreeBy;
	/**
	 * der effect
	 */
	private Effect effect;
	/**
	 * das bild
	 */
	private String image;
	
	/**
     * Erstellt vor jeder Klassemethode eine Testumgebung
     */
	@Before
	public void setUp(){
		
	    name=CardInfo.LUMBER_YARD;
		
		costs = new EnumMap<BuildResources, Integer>(BuildResources.class);
		
		color = CardColor.GREY ;
				
	    effect = new AddBuildResEffect(BuildResources.ORE,2);
	    
	    buildsFree = new CardInfo[]{};
	    
	    buildFreeBy= new CardInfo[]{};
	    
	    card= new Card(name,costs,color,buildsFree,buildFreeBy,effect,image);
	}
	/**
     * testet die Methode getName
     */
	@Test
	public void getName() {
		assertEquals(name,card.getName() );
	}
	/**
     * testet die Methode getCosts
     */
	@Test
	public void getCosts() {
		assertEquals(costs,card.getCosts());
	}
	/**
     * testet die Methode getColor
     */
	@Test
	public void getColor() {
		assertEquals(color,card.getColor());
	}
	/**
     * testet die Methode getBuildsFree
     */
	@SuppressWarnings("deprecation")
	@Test
	public void getBuildsFree() {
		assertEquals(buildsFree,card.getBuildsFree());
	}
	/**
     * testet die Methode getBuildFreeBy
     */
	@SuppressWarnings("deprecation")
	@Test
	public void getBuildFreeBy() {
		assertEquals(buildFreeBy,card.getBuiltFreeBy());
	}
	/**
     * testet die Methode getEffect
     */
	@Test
	public void getEffect() {
		assertEquals(effect,card.getEffect());
	}
	/**
     * testet die Methode getImage
     */
	@Test
	public void getImage() {
		assertEquals(image,card.getImage());
	}


}
