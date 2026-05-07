package edu.oop.guild.model;

public enum RealmType {
    SKY("Sky Kingdom"),
    UNDERGROUND("Underground Market");

    private final String displayName;

    RealmType(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
