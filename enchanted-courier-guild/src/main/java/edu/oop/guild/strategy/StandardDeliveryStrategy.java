package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;
import java.util.Objects;

public class StandardDeliveryStrategy implements DeliveryCostStrategy {
    @Override
    public int estimateCoins(DeliveryRequest request) {
        Objects.requireNonNull(request);
        int base = request.getWeightKg() + 2 * request.getDistanceLeagues() + 10;
        return base + (request.isFragile() ? 5 : 0);
    }
}
