package main.java;


public class WordleFeedBack {
    private static final int WORD_LENGTH = 5;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static String applyColor(String letter, String color) { // metodo para aplicar color.
        return color + letter;
    }

    public static String feedBackString(String guess, String secretWord) { // metodo para comparar la palabra del jugador con la palabra secreta.
        StringBuilder feedback = new StringBuilder();
        for (int indexChar = 0; indexChar < WordleGame.WORD_LENGTH; indexChar++) {
            char guessChar = guess.charAt(indexChar);
            char secretWordChar = secretWord.charAt(indexChar);
            if (guessChar == secretWordChar) {
                feedback.append(applyColor(String.valueOf(guessChar), ANSI_GREEN));
            } else if (secretWord.contains(String.valueOf(guessChar))) {
                feedback.append(applyColor(String.valueOf(guessChar), ANSI_YELLOW));
            } else {
                feedback.append(applyColor(String.valueOf(guessChar), ANSI_RESET));
            }
        }
        return feedback.toString();
    }

}
