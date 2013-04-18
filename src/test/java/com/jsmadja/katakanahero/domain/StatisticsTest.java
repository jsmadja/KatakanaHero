package com.jsmadja.katakanahero.domain;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.jsmadja.katakanahero.domain.Katakana.ア;
import static com.jsmadja.katakanahero.domain.Katakana.イ;
import static com.jsmadja.katakanahero.domain.Katakana.カ;
import static org.fest.assertions.Assertions.assertThat;

public class StatisticsTest {

    private Statistics statistics = new Statistics();

    @Test
    public void doit_incrementer_les_katakanas_trouves_correctement() {
        assertThat(statistics.getGoodScore(カ)).isEqualTo(0);
        statistics.good(カ);
        assertThat(statistics.getGoodScore(カ)).isEqualTo(1);
        statistics.good(カ);
        assertThat(statistics.getGoodScore(カ)).isEqualTo(2);
    }

    @Test
    public void doit_incrementer_les_katakanas_non_trouves() {
        assertThat(statistics.getBadScore(カ)).isEqualTo(0);
        statistics.bad(カ);
        assertThat(statistics.getBadScore(カ)).isEqualTo(1);
        statistics.bad(カ);
        assertThat(statistics.getBadScore(カ)).isEqualTo(2);
    }

    @Test
    public void doit_trier_les_katakanas_avec_les_moins_trouves_en_premiers() {
        statistics.bad(カ);
        statistics.bad(ア);
        statistics.bad(イ);
        statistics.bad(ア);
        statistics.bad(カ);
        statistics.bad(カ);

        List<Katakana> katakanas = statistics.getKatakanasWithMostUnknownFirst();
        assertThat(katakanas).hasSize(Katakana.count());
        assertThat(katakanas.get(0)).isEqualTo(カ);
        assertThat(katakanas.get(1)).isEqualTo(ア);
        assertThat(katakanas.get(2)).isEqualTo(イ);
    }

    @Test
    public void doit_charger_les_statistiques_depuis_un_fichier() throws IOException {
        statistics.bad(カ);
        statistics.good(ア);
        statistics.good(ア);

        String filename = "katakanahero.db";
        statistics.saveTo(filename);

        statistics = new Statistics();
        statistics.loadFrom(filename);

        assertThat(statistics.getBadScore(カ)).isEqualTo(1);
        assertThat(statistics.getGoodScore(ア)).isEqualTo(2);
    }

}
