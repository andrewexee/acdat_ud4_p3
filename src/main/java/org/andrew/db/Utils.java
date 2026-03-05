package org.andrew.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

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

    /**
     * Formatea un documento de libro para su visualización.
     * @param doc Documento que representa un libro
     * @return String formateada con los detalles del libro
     */
    public static String formatearL(Document doc) {
        return String.format("ID: %d, Titulo: %s, Autor: %s, Precio: %.2f€",
                doc.getInteger("ID"),
                doc.getString("Titulo"),
                doc.getString("Autor"),
                doc.getDouble("Precio"));
    }

    /**
     * Formatea un documento de pedido para su visualización.
     * @param doc Documento que representa un pedido
     * @return String formateada con los detalles del pedido
     */
    public static String formatearP(Document doc) {
        return String.format("ID: %d, Libro: %s, Cantidad: %s, Total: %.2f€",
                doc.getInteger("ID"),
                doc.getString("Libro"),
                doc.getInteger("Cantidad"),
                doc.getDouble("Total"));
    }
}
