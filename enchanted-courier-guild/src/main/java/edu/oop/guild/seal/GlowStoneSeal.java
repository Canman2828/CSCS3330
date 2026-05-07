package edu.oop.guild.seal;

import java.util.Objects;

public class GlowStoneSeal implements PackageSeal {
    @Override
    public String apply(String label) {
        return "◆ " + Objects.requireNonNull(label) + " ◆";
    }

    @Override
    public int durability() {
        return 12;
    }
}
