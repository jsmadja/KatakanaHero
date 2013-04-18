package com.jsmadja.katakanahero.domain;

import java.util.Scanner;

public class KatakanaHero {

    public static void main(String[] args) {
        KatakanaHero katakanaHero = new KatakanaHero();
        katakanaHero.start();
    }

    public void start() {
        GameSession gameSession = new GameSession();
        while(gameSession.isNotFinished()) {
            guess(gameSession);
        }
    }

    private void guess(GameSession gameSession) {
        Katakana katakana = gameSession.next();
        System.err.println("\nGuess this: "+katakana);
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()) {
            String answer = scanner.nextLine();
            Result result = gameSession.input(answer);
            if(result.isOK()) {
                System.err.println("Yeah that's right");
            } else {
                System.err.println("Too bad, it's "+katakana.getSyllabe());
            }
            System.err.println(gameSession.getProgression());
        }
    }

}
