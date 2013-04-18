package com.jsmadja.katakanahero.domain;

public enum Result {

    OK,KO;

    public boolean isKO() {
        return this == KO;
    }

    public boolean isOK() {
        return !isKO();
    }
}
