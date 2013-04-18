package com.jsmadja.katakanahero.domain;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ProgressionTest {

    @Test
    public void doit_formatter_la_progression_10_pc() {
        Progression progression = new Progression(10, 100);
        assertThat(progression.toString()).isEqualTo("Progression: 10/100 (10%)");
    }

    @Test
    public void doit_formatter_la_progression_33_pc() {
        Progression progression = new Progression(33, 100);
        assertThat(progression.toString()).isEqualTo("Progression: 33/100 (33%)");
    }

}
