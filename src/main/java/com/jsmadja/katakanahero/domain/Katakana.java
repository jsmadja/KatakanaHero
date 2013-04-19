package com.jsmadja.katakanahero.domain;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public enum Katakana {

    ア("a"), イ("i"), ウ("u"), エ("e"), オ("o"),
    カ("ka"), キ("ki"), ク("ku"), ケ("ke"), コ("ko"),
    サ("sa"), シ("shi"), ス("su"), セ("se"), ソ("so"),
    タ("ta"), チ("chi"), ツ("tsu"), テ("te"), ト("to"),
    ナ("na"), ニ("ni"), ヌ("nu"), ネ("ne"), ノ("no"),
    ハ("ha"), ヒ("hi"), フ("fu"), ヘ("he"), ホ("ho"),
    マ("ma"), ミ("mi"), ム("mu"), メ("me"), モ("mo"),
    ヤ("ya"), ユ("yu"), ヨ("yo"),
    ラ("ra"), リ("ri"), ル("ru"), レ("re"), ロ("ro"),
    ワ("wa"), ヰ("wi"), ヱ("we"), ヲ("wo"),
    ン("n"),
    ガ("ga"), ギ("gi"), グ("gu"), ゲ("ge"), ゴ("go"),
    ザ("za"), ジ("ji"), ズ("zu"), ゼ("ze"), ゾ("zo"),
    ダ("da"), ヂ("ji"), ヅ("zu"), デ("de"), ド("do"),
    バ("ba"), ビ("bi"), ブ("bu"), ベ("be"), ボ("bo"),
    パ("pa"), ピ("pi"), プ("pu"), ペ("pe"), ポ("po");

    private final String syllabe;

    private Katakana(String syllabe) {
        this.syllabe = syllabe;
    }

    public String getSyllabe() {
        return syllabe;
    }

    public boolean isEqualTo(String s) {
        return syllabe.equalsIgnoreCase(s);
    }

    public static int count() {
        return Katakana.values().length;
    }

    public static Collection<Katakana> randomList() {
        Set<Katakana> katakanas = new HashSet<Katakana>();
        while (katakanas.size() < count()) {
            katakanas.add(values()[RandomUtils.nextInt(count())]);
        }
        return katakanas;
    }
}
