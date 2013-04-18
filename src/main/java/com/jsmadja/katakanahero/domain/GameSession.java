package com.jsmadja.katakanahero.domain;

import com.google.common.collect.Iterables;

import java.util.Arrays;
import java.util.Iterator;

import static com.jsmadja.katakanahero.domain.Result.KO;
import static com.jsmadja.katakanahero.domain.Result.OK;

public class GameSession {

    private Katakana currentKatakana;
    private Integer score = 0;
    private Result lastResult = OK;
    private Iterator<Katakana> katakanaGenerator = Iterables.cycle(Arrays.asList(Katakana.values())).iterator();

    public Katakana next() {
        if(lastResult.isKO()) {
            return currentKatakana;
        }
        currentKatakana = katakanaGenerator.next();
        return currentKatakana;
    }

    public Result input(String answer) {
        if(currentKatakana.isEqualTo(answer)) {
            score++;
            lastResult = OK;
            return OK;
        } else {
            lastResult = KO;
        }
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
}
