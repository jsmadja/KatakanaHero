package com.jsmadja.katakanahero.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;

import static com.jsmadja.katakanahero.domain.Result.KO;
import static com.jsmadja.katakanahero.domain.Result.OK;

public class GameSession {

    private Katakana currentKatakana;
    private Integer score = 0;
    private Result lastResult = OK;
    private final Player player;
    private final Statistics statistics = new Statistics();
    private Iterator<Katakana> katakanaGenerator;

    private static final Logger LOG = LoggerFactory.getLogger(GameSession.class);

    public GameSession(Player player) {
        this.player = player;
        loadPlayerHistory();
        savePlayerHistory();
        katakanaGenerator = statistics.getKatakanasWithMostUnknownFirst().iterator();
    }

    public Katakana next() {
        if (lastResult.isKO()) {
            return currentKatakana;
        }
        currentKatakana = katakanaGenerator.next();
        return currentKatakana;
    }

    public Result input(String answer) {
        if (currentKatakana.isEqualTo(answer)) {
            score++;
            lastResult = OK;
            statistics.good(currentKatakana);
            return OK;
        } else {
            statistics.bad(currentKatakana);
            lastResult = KO;
        }
        savePlayerHistory();
        return lastResult;
    }

    public Integer getScore() {
        return score;
    }

    public boolean isNotFinished() {
        return score < katakanaCount();
    }

    private int katakanaCount() {
        return Katakana.values().length;
    }

    public Progression getProgression() {
        return new Progression(score, katakanaCount());
    }

    public Statistics getStatistics() {
        return statistics;
    }

    private void loadPlayerHistory() {
        try {
            statistics.loadFrom(playerDatabase());
            LOG.debug(player + " database has been loaded successfully");
        } catch (IOException e) {
            LOG.info("Unable to load " + player + " database.");
        }
    }

    private void savePlayerHistory() {
        try {
            statistics.saveTo(playerDatabase());
            LOG.debug(player + " database has been saved successfully");
        } catch (IOException e) {
            LOG.error("Unable to save " + player + " database.");
        }
    }

    private String playerDatabase() {
        return "/tmp/katakanahero-" + player.getName() + ".db";
    }

}
