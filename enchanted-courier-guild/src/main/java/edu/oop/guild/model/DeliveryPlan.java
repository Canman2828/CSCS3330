package edu.oop.guild.model;

import edu.oop.guild.creature.Creature;
import java.util.Objects;

public class DeliveryPlan {
    private final DeliveryRequest request;
    private final Creature courier;
    private final String sealedLabel;
    private final int priceInCoins;

    public DeliveryPlan(DeliveryRequest request, Creature courier, String sealedLabel, int priceInCoins) {
        this.request = Objects.requireNonNull(request);
        this.courier = Objects.requireNonNull(courier);
        this.sealedLabel = Objects.requireNonNull(sealedLabel);
        if (priceInCoins < 0) throw new IllegalArgumentException("priceInCoins cannot be negative");
        this.priceInCoins = priceInCoins;
    }

    public DeliveryRequest getRequest() { return request; }
    public Creature getCourier() { return courier; }
    public String getSealedLabel() { return sealedLabel; }
    public int getPriceInCoins() { return priceInCoins; }

    public String summary() {
        return courier.name() + " delivers " + sealedLabel + " for " + priceInCoins + " coins";
    }
}
