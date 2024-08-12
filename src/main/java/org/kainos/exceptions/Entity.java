package org.kainos.exceptions;

public enum Entity {
    BOOK("Book");
    private final String entity;
    Entity(final String entity) {
        this.entity = entity;
    }
    public String getEntity() {
        return this.entity;
    }
}
