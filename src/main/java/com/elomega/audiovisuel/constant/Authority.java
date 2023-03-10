package com.elomega.audiovisuel.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:read" };
    public static final String[] ACTEUR_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] MAISON_DE_PRODUCTION_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] ADMIN_AUTHORITIES = { "user:read", "user:create", "user:update" };
}
