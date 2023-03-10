package com.elomega.audiovisuel.enumeration;

import static com.elomega.audiovisuel.constant.Authority.*;

public enum Role {
    USER(USER_AUTHORITIES),
    ACTEUR(ACTEUR_AUTHORITIES),
    MAISON_DE_PRODUCTION(MAISON_DE_PRODUCTION_AUTHORITIES),
    ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
