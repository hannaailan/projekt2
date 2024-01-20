package model;

import org.junit.Before;
import org.junit.Test;

import java.util.EnumMap;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * AddOrResEffect Testklasse
 */
public class AddOrResEffectTest {

    private Wonder testWonder;

    private AddOrResEffect addOrResEffect;

    /**
     * testSet Hilfsattribut
     */
    HashSet<EnumMap<BuildResources,Integer>> testSet;
    /**
     * emptyResource Hilfsattribut
     */
    EnumMap<BuildResources, Integer> emptyResource;

    /**
     * Erstellen einer Testumgebung vor den Methodentests
     *
     * @throws Exception mögliche Exeptions beim SetUp
     */
    @Before
    public void setUp() throws Exception {
        addOrResEffect = new AddOrResEffect(true,BuildResources.BRICK,BuildResources.WOOD);
        testWonder = new Wonder(WonderInfo.RHODES, BuildResources.ORE);
        testSet = new HashSet<>();
        emptyResource = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()) {
            emptyResource.put(buildResource, 0);
        }
    }

    /**
     * Testet den Konstruktor in AddOrResEffect
     */
    @Test
    public void constructorTest(){

        assertTrue("Fehler tradable constructorTest()",addOrResEffect.tradable);
        assertEquals("Fehler res constructorTest()",BuildResources.BRICK,addOrResEffect.res[0]);
        assertEquals("Fehler res constructorTest()",BuildResources.WOOD,addOrResEffect.res[1]);
    }

    /**
     * Testet die Methode effect() in AddOrResEffect
     */
    @Test
    public void effect() {
        testSet = new HashSet<>();
        EnumMap<BuildResources, Integer> testResource1 = emptyResource.clone();
        EnumMap<BuildResources, Integer> testResource2 = emptyResource.clone();
        EnumMap<BuildResources, Integer> testResource3 = emptyResource.clone();
        EnumMap<BuildResources, Integer> testResource4 = emptyResource.clone();
        EnumMap<BuildResources, Integer> testResource5 = emptyResource.clone();


        testResource1.put(BuildResources.BRICK,1);
        testSet.add(testResource1.clone());
        testResource2.put(BuildResources.WOOD,1);
        testSet.add(testResource2.clone());
        testResource3.put(BuildResources.ORE,1);
        testSet.add(testResource3.clone());
        testResource4.put(BuildResources.WOOD,1);
        testResource4.put(BuildResources.ORE,1);
        testSet.add(testResource4.clone());
        testResource5.put(BuildResources.BRICK,1);
        testResource5.put(BuildResources.ORE,1);
        testSet.add(testResource5.clone());
        addOrResEffect.effect(testWonder);
        assertEquals(testSet,testWonder.getSells());
        testSet.add(emptyResource.clone());
        assertEquals(testSet,testWonder.getProduces());

    }
}