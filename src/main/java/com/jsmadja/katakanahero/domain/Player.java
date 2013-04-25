package com.jsmadja.katakanahero.domain;

public class Player {

    private String playerName;

    Player() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerName.equals(player.playerName);

    }

    @Override
    public int hashCode() {
        return playerName.hashCode();
    }
}
