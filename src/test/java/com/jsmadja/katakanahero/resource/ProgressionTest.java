package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.domain.Katakana;
import com.jsmadja.katakanahero.domain.Player;
import com.jsmadja.katakanahero.domain.Progression;
import com.jsmadja.katakanahero.domain.Result;
import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ProgressionTest extends KatakanaITest {

    public static final String PLAYER_NAME = "test";

    @Test
    public void doit_acceder_a_la_progression_du_joueur() {
        Player player = new Player(PLAYER_NAME);
        preparePostRequest(player, "players").post(Katakana.class);

        Progression progression = prepareGetRequest("progressions").header("player", player).get(Progression.class);
        assertThat(progression.toString()).isEqualTo("Progression: 0/73 (0%)");
    }

    @After
    public void deleteAll() {
        deleteDatabaseOf(PLAYER_NAME);
    }

}
