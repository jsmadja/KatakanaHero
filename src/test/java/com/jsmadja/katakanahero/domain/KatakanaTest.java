package com.jsmadja.katakanahero.domain;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class KatakanaTest {

    @Test
    public void doit_retourner_la_syllabe_d_un_katakana() {
        assertThat(Katakana.カ.getSyllabe()).isEqualTo("ka");
    }

}
