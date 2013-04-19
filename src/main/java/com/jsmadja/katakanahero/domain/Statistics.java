package com.jsmadja.katakanahero.domain;

import java.io.IOException;
import java.util.*;

public class Statistics {

    private Map<Katakana, Integer> goods = new HashMap<Katakana, Integer>();
    private Map<Katakana, Integer> bads = new HashMap<Katakana, Integer>();
    private StatisticsDB statisticsDB = new StatisticsDB(this);

    public void good(Katakana katakana) {
        incrementScoreOf(katakana, goods);
    }

    public void bad(Katakana katakana) {
        incrementScoreOf(katakana, bads);
    }

    public Integer getGoodScore(Katakana katakana) {
        return getScoreOf(katakana, goods);
    }

    public Integer getBadScore(Katakana katakana) {
        return getScoreOf(katakana, bads);
    }

    private void incrementScoreOf(Katakana katakana, Map<Katakana, Integer> scores) {
        Integer score = scores.get(katakana);
        if (score == null) {
            scores.put(katakana, 1);
        } else {
            scores.put(katakana, score + 1);
        }
    }

    private int getScoreOf(Katakana katakana, Map<Katakana, Integer> scores) {
        Integer score = scores.get(katakana);
        if (score == null) {
            return 0;
        }
        return score;
    }

    public List<Katakana> getKatakanasWithMostUnknownFirst() {
        List<Katakana> katakanas = new ArrayList<Katakana>(Katakana.randomList());
        Collections.sort(katakanas, sortByBadScore);
        return katakanas;
    }

    private final Comparator<Katakana> sortByBadScore = new Comparator<Katakana>() {
        @Override
        public int compare(Katakana k1, Katakana k2) {
            return getBadScore(k2).compareTo(getBadScore(k1));
        }
    };

    public void good(Katakana katakana, Integer score) {
        goods.put(katakana, score);
    }

    public void bad(Katakana katakana, Integer score) {
        bads.put(katakana, score);
    }

    public void saveTo(String filename) throws IOException {
        statisticsDB.saveTo(filename);
    }

    public void loadFrom(String filename) throws IOException {
        statisticsDB.loadFrom(filename);
    }
}
