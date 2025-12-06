package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordleFileManager {

    private static final String WORDS_FILE_PATH = "./src/main/resources/words.txt"; // archivo constante donde sacamos las palabras del juego.
    private static final String GAME_RESULT_FILE_PATH = "./src/main/resources/game_log.txt"; // aquí vamos escribiendo el log del juego.

    public static String[] loadWords() { // con este metodo cargamos la palabra aleatoria.
        List<String> lines = new ArrayList<>();
        File file = new File(WORDS_FILE_PATH);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                lines.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el fichero: " + e);
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e2) {
                System.out.println("Error cerrando el fichero: " + e2);
            }
        }
        return lines.toArray(new String[0]);
    }
    //
    public static void showWriteLog(String content) { // escribimos el log.
        try (FileWriter writer = new FileWriter(GAME_RESULT_FILE_PATH, true)) {
            System.out.println(content);
            writer.write(content + "\n");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al escribir en el fichero: " + e.getMessage());
        }
    }
}
