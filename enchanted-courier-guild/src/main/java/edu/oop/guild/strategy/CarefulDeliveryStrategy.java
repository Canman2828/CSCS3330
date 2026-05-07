package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;
import java.util.Objects;

public class CarefulDeliveryStrategy implements DeliveryCostStrategy {
    @Override
    public int estimateCoins(DeliveryRequest request) {
        Objects.requireNonNull(request);
        int base = 7 * request.getWeightKg() * request.getDistanceLeagues();
        return base + request.getPackageType().carefulSurcharge();
    }
}
