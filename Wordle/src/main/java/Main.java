package main.java;

public class Main {

    public static void main(String[] args) {
        // Cargar palabras fichero
        String[] fileWords = WordleFileManager.loadWords();
        WordleGame wordleGame = new WordleGame(fileWords);
        wordleGame.start();
    }
}