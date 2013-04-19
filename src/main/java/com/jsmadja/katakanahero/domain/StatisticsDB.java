package com.jsmadja.katakanahero.domain;

import com.google.common.base.Predicate;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Collections2.filter;

public class StatisticsDB {

    public static final Charset CHARSET = Charset.forName("UTF-8");

    private final Statistics statistics;

    public StatisticsDB(Statistics statistics) {
        this.statistics = statistics;
    }

    public void saveTo(String filename) throws IOException {
        StringBuilder save = new StringBuilder();
        insertBads(save);
        insertGoods(save);
        Files.write(save.toString(), new File(filename), CHARSET);
    }

    private void insertGoods(StringBuilder save) {
        save.append("# goods\n");
        for (Katakana katakana : Katakana.values()) {
            save.append(katakana.name()).append(" ").append(statistics.getGoodScore(katakana)).append("\n");
        }
    }

    private void insertBads(StringBuilder save) {
        save.append("# bads\n");
        for (Katakana katakana : Katakana.values()) {
            save.append(katakana.name()).append(" ").append(statistics.getBadScore(katakana)).append("\n");
        }
    }

    public void loadFrom(String filename) throws IOException {
        List<String> scores = Files.readLines(new File(filename), CHARSET);
        scores = new ArrayList<String>(filter(scores, new Predicate<String>() {
            @Override
            public boolean apply(String line) {
                return !line.startsWith("#");
            }
        }));
        loadBads(scores);
        loadGoods(scores);
    }

    private void loadGoods(List<String> scores) {
        for (int i = Katakana.count(); i < scores.size(); i++) {
            String[] split = scores.get(i).split(" ");
            Katakana katakana = Katakana.valueOf(split[0]);
            Integer score = Integer.valueOf(split[1]);
            statistics.good(katakana, score);
        }
    }

    private void loadBads(List<String> scores) {
        for (int i = 0; i < Katakana.count(); i++) {
            String[] split = scores.get(i).split(" ");
            Katakana katakana = Katakana.valueOf(split[0]);
            Integer score = Integer.valueOf(split[1]);
            statistics.bad(katakana, score);
        }
    }

}
