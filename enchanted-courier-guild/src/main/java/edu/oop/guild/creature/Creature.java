package edu.oop.guild.creature;

import edu.oop.guild.model.DeliveryRequest;
import edu.oop.guild.model.RealmType;
import java.util.Objects;

public abstract class Creature {
    public abstract String name();
    public abstract int carryingCapacityKg();
    public abstract RealmType nativeRealm();

    public boolean canCarry(DeliveryRequest request) {
        Objects.requireNonNull(request);
        return request.getWeightKg() <= carryingCapacityKg() && request.getDestinationRealm() == nativeRealm();
    }
}
