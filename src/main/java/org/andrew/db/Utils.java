package org.andrew.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrés Iglesias Camacho
 * @date 30.01.2026
 */
public class Utils {
    // Constructor privado para evitar instanciación
    private Utils() {
    }

    /**
     * Lee un archivo .env y devuelve un Map con sus valores.
     *
     * @return {@code Map<String, String>} con las variables cargadas
     */
    public static Map<String, String> getEnv() {

        Map<String, String> envVars = new HashMap<>();

        try (InputStream input = Utils.class.getClassLoader().getResourceAsStream(".env")) {

            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String linea;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                // Ignorar líneas vacías o comentarios
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                // Formato clave=valor
                int idx = linea.indexOf('=');
                if (idx == -1) {
                    continue; // línea inválida
                }

                String[] trozos = linea.split("=");

                envVars.put(trozos[0], trozos[1]);
            }
        } catch (IOException e) {
            System.err.println("Error. " + e.getMessage());
        }

        return envVars;
    }
}
