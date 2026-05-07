package edu.oop.guild.model;

public enum PackageType {
    FOOD("Snack crate", 0),
    POTION("Potion case", 5),
    ARTIFACT("Ancient artifact", 17);

    private final String label;
    private final int carefulSurcharge;

    PackageType(String label, int carefulSurcharge) {
        this.label = label;
        this.carefulSurcharge = carefulSurcharge;
    }

    public String label() {
        return label;
    }

    public int carefulSurcharge() {
        return carefulSurcharge;
    }
}
