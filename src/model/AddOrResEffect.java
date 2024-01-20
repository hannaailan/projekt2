package model;

import controller.WonderController;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashSet;

/**
 * Die Klasse addOrEffect die das hinzufügen von OrResources vewaltet
 */
class AddOrResEffect implements Effect, Serializable {

    /**
     * Zwei oder mehr resourcen die zu einer OrResource gehören
     */
    BuildResources[] res;

    /**
     * true: Resource ist Handelbar (Braune/Graue Karten), false: resource ist nicht Handelbar (Gelbe Karten/ Stages)
     */
    boolean tradable;

    /**
     * WonderController zum benutzen der Methoden
     */
    WonderController wonderController = new WonderController(null);

    /**
     * Konstruktor des AddOrResEffect, setzt die parameter die zur ausführung benötigt werden
     * @param tradable true: Resource ist Handelbar (Braune/Graue Karten), false: resource ist nicht Handelbar (Gelbe Karten, Stages)
     * @param res Zwei oder mehr resourcen, die unabhängig voneinander in die Datenstruktur eingefügt werden
     */
    public AddOrResEffect(boolean tradable, BuildResources... res){
        this.tradable=tradable;
        this.res=res;
    }

    /**
     * Fügt alle Resourcen  so in die Datenstruktur ein, dass sie sich gegenseitig ausschließen (XOR)
     * @param wonder Spieler welcher die Resourcen bekommt
     */
    @Override
    public void effect(Wonder wonder) {
        EnumMap<BuildResources,Integer> emptyResource = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()) {
            emptyResource.put(buildResource, 0);
        }
        final HashSet<EnumMap<BuildResources,Integer>> oldProduces = new  HashSet<>(wonder.getProduces());
        final HashSet<EnumMap<BuildResources,Integer>> oldSells = new HashSet<>(wonder.getSells());
        for(BuildResources resources : res){
            EnumMap<BuildResources,Integer> newResource = new EnumMap<>(emptyResource.clone());
            newResource.put(resources,1);
            wonder.addProduces(wonderController.integrateResource(oldProduces,newResource.clone()));
            if(tradable){
                wonder.addSells(wonderController.integrateResource(oldSells,newResource.clone()));
            }
        }
    }
}
