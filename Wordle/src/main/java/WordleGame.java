package main.java;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class WordleGame {
    private static final int MAX_TRIES = 6;
    public static final int WORD_LENGTH = 5;
    private String[] fileWords;
    private String secretWord;
    private int remainingAttempts;
    private String[] triesHistory;

    public WordleGame(String[] fileWords) {
        this.fileWords = fileWords;
        this.secretWord = selectRandomWord(fileWords).toUpperCase(); //selecciona una palabra aleatoria.
        this.remainingAttempts = MAX_TRIES;
        this.triesHistory = new String[MAX_TRIES];
    }

    public void start() { //lógica del juego
        Scanner scanner = new Scanner(System.in); // creo un menú de bienvenida al jugador.
        WordleFileManager.showWriteLog(WordleFeedBack.ANSI_PURPLE + "Bienvenidos al Wordle");
        WordleFileManager.showWriteLog("Dame tu nombre:");
        String userName = scanner.next();
        WordleFileManager.showWriteLog("Vamos a jugar " + userName);
        WordleFileManager.showWriteLog(WordleFeedBack.ANSI_CYAN + "#PalabraSecreta " + secretWord); // línea para facilitar el vídeo, se puede borrar o comentar.
        boolean win = false;
        while (remainingAttempts > 0) { // creo bucle del juego, va dejando constancia de los intentos que nos quedan.
            WordleFileManager.showWriteLog(WordleFeedBack.ANSI_RESET + "Tienes " + remainingAttempts + " intentos restantes");
            showTriesHistory();
            String userWord = getUserInput(scanner);
            triesHistory[MAX_TRIES - remainingAttempts] = userWord;
            WordleFileManager.showWriteLog("Validación: ");
            WordleFileManager.showWriteLog(WordleFeedBack.feedBackString(userWord, secretWord));
            if (Objects.equals(userWord, secretWord)) {
                win = true;
                break;
            } else {
                remainingAttempts--;
            }
        }

        if (win) { // si ganas se ejecuta esta parte.
            WordleFileManager.showWriteLog(WordleFeedBack.ANSI_CYAN + "Has Ganado" + WordleFeedBack.ANSI_RESET);
        } else { // si pierdes se ejecuta esta.
            WordleFileManager.showWriteLog(WordleFeedBack.ANSI_RED + "Has Perdido, la palabra secreta era: " + secretWord + WordleFeedBack.ANSI_RESET);
        }
    }

    private String selectRandomWord(String[] words) {
        Random random = new Random();
        int randomNumber = random.nextInt(fileWords.length); // Genera un número entre 0 (incluido) y 6 (excluido)
        return words[randomNumber];
    }

    private void showTriesHistory() {
        for (String historyLine : triesHistory) {
            if (historyLine != null) {
                System.out.println(historyLine);
            }
        }
    }

    private String getUserInput(Scanner scanner) { // recogemos las palabras del usuario
        boolean validWord = false;
        String userWord;
        do {
            WordleFileManager.showWriteLog("Introduce una palabra de " + WORD_LENGTH + " letras: "
                    .concat(WordleFeedBack.ANSI_GREEN));
            userWord = scanner.next();
            userWord = userWord.toUpperCase();
            System.out.print(WordleFeedBack.ANSI_RESET);
            if (userWord.length() > WORD_LENGTH) {
                WordleFileManager.showWriteLog(WordleFeedBack.ANSI_RED
                        .concat("La palabra " + userWord + " contiene mas de " + WORD_LENGTH + " letras")
                        .concat(WordleFeedBack.ANSI_RESET));
            } else {
                validWord = true;
            }
        } while (!validWord);
        return userWord;
    }
}
