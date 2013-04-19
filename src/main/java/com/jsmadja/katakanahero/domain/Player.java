package com.jsmadja.katakanahero.domain;

public class Player {
    private final String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getName() {
        return playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
