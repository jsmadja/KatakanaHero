package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.domain.Katakana;
import com.jsmadja.katakanahero.domain.Player;
import com.jsmadja.katakanahero.domain.Result;
import org.junit.After;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class AnswersTest extends KatakanaITest {

    public static final String PLAYER_NAME = "test";

    @Test
    public void doit_repondre_correctement_a_la_question() {
        Player player = new Player(PLAYER_NAME);
        Katakana katakana = preparePostRequest(player, "players").post(Katakana.class);

        String answer = katakana.getSyllabe();
        Result result = preparePostRequest(answer, "answers").header("player", player).post(Result.class);
        assertThat(result.isOK()).isTrue();
    }

    @Test
    public void ne_doit_pas_repondre_correctement_a_la_question() {
        Player player = new Player(PLAYER_NAME);
        preparePostRequest(player, "players").post(Katakana.class);

        String answer = "wrong_answer";
        Result result = preparePostRequest(answer, "answers").header("player", player).post(Result.class);
        assertThat(result.isKO()).isTrue();
    }

    @After
    public void deleteAll() {
        deleteDatabaseOf(PLAYER_NAME);
    }

}
