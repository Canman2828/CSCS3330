package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;
import java.util.Objects;

public class ExpressDeliveryStrategy implements DeliveryCostStrategy {
    @Override
    public int estimateCoins(DeliveryRequest request) {
        Objects.requireNonNull(request);
        int base = 2 * request.getWeightKg() + 4 * request.getDistanceLeagues() + 25;
        return base + (request.isFragile() ? 10 : 0);
    }
}
