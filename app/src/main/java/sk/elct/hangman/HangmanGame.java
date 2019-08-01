package sk.elct.hangman;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HangmanGame implements Serializable {
    // na zaciatku mam 6 pokusov
    public static final int DEFAULT_ATTEMPTS_LEFT = 6;

    // znak este neuhadnuteho pismena
    private static final char UNGUESSED_CHAR = '_';

    // predefinovane slova na hadanie
    private List<String> words = Arrays.asList(
            "hangman", "game", "hippopotamus",
            "troll", "anteater", "banana"
    );

    // slovo ktore treba uhadnut
    private String challengeWord;

    // aktualny stav hry - odhalene a skryte znaky
    private StringBuilder guessedCharacters;

    // zostavajuci pocet pokusov
    private int attemptsLeft = DEFAULT_ATTEMPTS_LEFT;

    private Random random;

    protected HangmanGame(Random random) {
        this.random = random;

        initializeRandomWord();
        initializeUnguessedWord();
    }

    public HangmanGame() {
        this(new Random());
    }

    private void initializeRandomWord() {
        int randomIndex = random.nextInt(words.size());

        challengeWord = words.get(randomIndex);
    }

    private void initializeUnguessedWord() {
        guessedCharacters = new StringBuilder(challengeWord.length());
        for (int i = 0; i < challengeWord.length(); i++) {
            guessedCharacters.append(UNGUESSED_CHAR);
        }
    }

    public boolean guess(char character) {
        if (attemptsLeft <= 0) {
            throw new IllegalStateException("No more guessing attempts left");
        }

        boolean letterIsFound = false;
        for (int i = 0; i < challengeWord.length(); i++) {
            if (challengeWord.charAt(i) == character) {
                letterIsFound = true;
                guessedCharacters.setCharAt(i, character);
            }
        }
        if (!letterIsFound) {
            attemptsLeft--;
        }
        return letterIsFound;
    }

    public boolean isWon() {
        return guessedCharacters.toString().equals(challengeWord);
    }

    public CharSequence getGuessedCharacters() {
        return guessedCharacters;
    }

    public String getChallengeWord() {
        return challengeWord;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

}