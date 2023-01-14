package com.elomega.audiovisuel.enumeration;

public enum Pouvoir {
    VOLER("VOLER"),
    LIRE_DANS_LES_PENSEES("LIRE_DANS_LES_PENSEES"),
    CHANGER_DASPECT("CHANGER_DASPPECT"),
    AVOIR_UNE_FORCE_SURHUMAINE("AVOIR_UNE_FORCE_SURHUMAINE"),
    SE_TELEPORTER("SE_TELEPORTER");
    private String pouvoir;
    Pouvoir(String pouvoir) {
        this.pouvoir = pouvoir;
    }
}
