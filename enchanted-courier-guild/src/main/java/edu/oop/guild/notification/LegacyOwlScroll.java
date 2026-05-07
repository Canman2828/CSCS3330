package edu.oop.guild.notification;

public class LegacyOwlScroll {
    public String dispatchScroll(String recipient, String inscription) {
        if (recipient == null || recipient.isBlank())
            throw new IllegalArgumentException("recipient must not be blank");
        if (inscription == null || inscription.isBlank())
            throw new IllegalArgumentException("inscription must not be blank");
        return "Owl scroll sent to " + recipient + ": " + inscription;
    }
}
