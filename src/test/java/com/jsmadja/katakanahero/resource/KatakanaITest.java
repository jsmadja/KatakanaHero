package com.jsmadja.katakanahero.resource;

import com.jsmadja.katakanahero.EmbeddedServer;
import com.jsmadja.katakanahero.client.KatakanaHeroClient;
import com.jsmadja.katakanahero.domain.Katakana;
import com.jsmadja.katakanahero.domain.Player;
import com.jsmadja.katakanahero.domain.Progression;
import com.jsmadja.katakanahero.domain.Result;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class KatakanaITest {

    @ClassRule
    public static EmbeddedServer server = EmbeddedServer.create();

    private KatakanaHeroClient client = new KatakanaHeroClient(server);

    public static final String PLAYER_NAME = "test";

    @Test
    public void doit_acceder_a_la_progression_du_joueur() {
        Player player = client.createNewPlayer(PLAYER_NAME);
        Progression progression = client.getProgressionOf(player);
        assertThat(progression.toString()).isEqualTo("Progression: 0/73 (0%)");
    }

    @Test
    public void doit_repondre_correctement_a_la_question() {
        Player player = client.createNewPlayer(PLAYER_NAME);
        Katakana katakana = client.getCurrentKatakana(player);
        Result result = client.answer(player, katakana.getSyllabe());
        assertThat(result.isOK()).isTrue();
    }

    @Test
    public void ne_doit_pas_repondre_correctement_a_la_question() {
        Player player = client.createNewPlayer(PLAYER_NAME);
        Result result = client.answer(player, "wrong_answer");
        assertThat(result.isKO()).isTrue();
    }

    @After
    public void deleteAll() {
        for (Player player : client.getPlayers()) {
            client.deleteDatabaseOf(player);
        }
    }

}
