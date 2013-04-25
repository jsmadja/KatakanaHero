package com.jsmadja.katakanahero.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KatakanaHero {

    private static final KatakanaHero INSTANCE = new KatakanaHero();

    private Map<Player, GameSession> sessions = new HashMap<Player, GameSession>();

    public static void main(String[] args) {
        KatakanaHero katakanaHero = new KatakanaHero();
        katakanaHero.start();
    }

    public void start() {
        GameSession gameSession = new GameSession(new Player("julien"));
        while (gameSession.isNotFinished()) {
            guess(gameSession);
        }
    }

    private void guess(GameSession gameSession) {
        Katakana katakana = gameSession.next();
        System.err.println("\nGuess this: " + katakana);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String answer = scanner.nextLine();
            Result result = gameSession.input(answer);
            if (result.isOK()) {
                System.err.println("Yeah that's right");
            } else {
                System.err.println("Too bad, it's " + katakana.getSyllabe());
            }
            System.err.println(gameSession.getProgression());
        }
    }

    public static KatakanaHero getInstance() {
        return INSTANCE;
    }

    public GameSession getSessionOf(Player player) {
        GameSession gameSession = sessions.get(player);
        if (gameSession == null) {
            gameSession = new GameSession(player);
            sessions.put(player, gameSession);
        }
        return gameSession;
    }
}
