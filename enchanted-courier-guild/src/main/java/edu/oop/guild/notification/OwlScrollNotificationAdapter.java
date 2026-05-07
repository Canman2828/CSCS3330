package edu.oop.guild.notification;

import edu.oop.guild.model.DeliveryPlan;
import java.util.Objects;

public class OwlScrollNotificationAdapter implements NotificationChannel {
    private final LegacyOwlScroll owlScroll;

    public OwlScrollNotificationAdapter(LegacyOwlScroll owlScroll) {
        this.owlScroll = Objects.requireNonNull(owlScroll);
    }

    @Override
    public String send(DeliveryPlan plan) {
        Objects.requireNonNull(plan);
        String recipient = plan.getRequest().getDestinationRealm().displayName();
        return owlScroll.dispatchScroll(recipient, plan.summary());
    }
}
