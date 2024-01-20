package model;

import controller.WonderController;

import java.io.Serializable;
import java.util.EnumMap;

/**
 * The type Add build res effect.
 */
class AddBuildResEffect implements Effect, Serializable {

    /**
     * The Res.
     */
    BuildResources res;
    /**
     * The Anz.
     */
    int anz;

    /**
     * WC wird benötigt
     */
    WonderController wonderController = new WonderController(null);

    /**
     * Instantiates a new Add build res effect.
     *
     * @param res the res
     * @param anz the anz
     */
    public AddBuildResEffect(BuildResources res, int anz){
        this.res=res;
        this.anz=anz;
    }

    /**
     * Fügt einem Wunder Build Resourcen hinzu
     * @param wonder betroffenes Wunder
     */
    @Override
    public void effect(Wonder wonder) {
        EnumMap<BuildResources,Integer> newResource = new EnumMap<>(BuildResources.class);
        for (BuildResources buildResource : BuildResources.values()) {
            newResource.put(buildResource, 0);
        }
        newResource.put(res,1);
        for(int i = anz; i > 0; i --){
            wonder.setProduces(wonderController.integrateResource(wonder.getProduces(),newResource.clone()));
            wonder.setSells(wonderController.integrateResource(wonder.getSells(),newResource.clone()));
        }

    }
}
