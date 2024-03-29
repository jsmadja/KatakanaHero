package com.jsmadja.katakanahero.domain;

import static java.lang.String.format;

public class Progression {

    private int currentScore;
    private int maxScore;

    Progression() {}

    public Progression(int currentScore, int maxScore) {
        this.currentScore = currentScore;
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return format("Progression: %d/%d (%d%%)", currentScore, maxScore, pourcentage());
    }

    private int pourcentage() {
        return (int) ((currentScore / (double) maxScore) * 100);
    }
}
