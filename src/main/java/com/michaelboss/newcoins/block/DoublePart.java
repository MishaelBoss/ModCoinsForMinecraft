package com.michaelboss.newcoins.block;

import net.minecraft.util.StringRepresentable;

public enum DoublePart implements StringRepresentable {
    LEFT("left"),
    RIGHT("right");

    private final String name;

    DoublePart(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
