package com.jsmadja.katakanahero.domain;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class GameSessionTest {

    private GameSession gameSession = new GameSession();

    @Test
    public void doit_renvoyer_une_question_katakana() {
        Katakana katakana = gameSession.next();

        assertThat(katakana).isNotNull();
    }

    @Test
    public void doit_renvoyer_OK_si_la_reponse_est_bonne() {
        Katakana katakana = gameSession.next();

        Result result = gameSession.input(katakana.getSyllabe());

        assertThat(result).isEqualTo(Result.OK);
    }

    @Test
    public void doit_renvoyer_KO_si_la_reponse_est_mauvaise() {
        gameSession.next();

        Result result = gameSession.input("");

        assertThat(result).isEqualTo(Result.KO);
    }

    @Test
    public void doit_incrementer_le_score_si_la_response_est_bonne() {
        assertThat(gameSession.getScore()).isEqualTo(0);

        Katakana katakana = gameSession.next();
        gameSession.input(katakana.getSyllabe());

        assertThat(gameSession.getScore()).isEqualTo(1);
    }

    @Test
    public void ne_doit_pas_incrementer_le_score_si_la_response_est_mauvaise() {
        assertThat(gameSession.getScore()).isEqualTo(0);

        gameSession.next();
        gameSession.input("");

        assertThat(gameSession.getScore()).isEqualTo(0);
    }

    @Test
    public void doit_renvoyer_le_meme_katakana_si_la_reponse_etait_mauvaise() {
        Katakana katakana1 = gameSession.next();
        gameSession.input("");
        Katakana katakana2 = gameSession.next();
        assertThat(katakana1).isEqualTo(katakana2);
    }

    @Test
    public void ne_doit_pas_renvoyer_le_meme_katakana_si_la_reponse_etait_bonne() {
        Katakana katakana1 = gameSession.next();
        gameSession.input(katakana1.getSyllabe());

        Katakana katakana2 = gameSession.next();
        assertThat(katakana1).isNotEqualTo(katakana2);

        gameSession.input(katakana2.getSyllabe());
        Katakana katakana3 = gameSession.next();
        assertThat(katakana2).isNotEqualTo(katakana3);
    }

    @Test
    public void doit_detecter_la_fin_de_la_session_quand_tous_les_katakanas_ont_ete_trouves() {
        for (int i = 0; i < Katakana.values().length; i++) {
            assertThat(gameSession.isNotFinished()).isTrue();
            gameSession.input(gameSession.next().getSyllabe());
        }
        assertThat(gameSession.isNotFinished()).isFalse();
    }

}
