package edu.oop.guild.model;

import java.util.Objects;

public class DeliveryRequest {
    private final PackageType packageType;
    private final int weightKg;
    private final int distanceLeagues;
    private final RealmType destinationRealm;
    private final boolean fragile;

    public DeliveryRequest(PackageType packageType, int weightKg, int distanceLeagues,
                           RealmType destinationRealm, boolean fragile) {
        this.packageType = Objects.requireNonNull(packageType);
        if (weightKg <= 0) throw new IllegalArgumentException("weightKg must be positive");
        if (distanceLeagues <= 0) throw new IllegalArgumentException("distanceLeagues must be positive");
        this.destinationRealm = Objects.requireNonNull(destinationRealm);
        this.weightKg = weightKg;
        this.distanceLeagues = distanceLeagues;
        this.fragile = fragile;
    }

    public PackageType getPackageType() { return packageType; }
    public int getWeightKg() { return weightKg; }
    public int getDistanceLeagues() { return distanceLeagues; }
    public RealmType getDestinationRealm() { return destinationRealm; }
    public boolean isFragile() { return fragile; }
}
