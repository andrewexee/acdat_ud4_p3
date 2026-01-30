package org.andrew;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.andrew.db.SingletonConnection;

import org.bson.Document;

/**
 * @author Andrés Iglesias Camacho
 * @date 30.01.2026
 */
public class Main {
    static void main() {
        try {
            // Escribe tu código bro...
            MongoDatabase db = SingletonConnection.getCliente().getDatabase("Instituto");

            MongoCollection<Document> collection = db.getCollection("Alumno");

            //Consulta previa a la inserción de un nuevo documento
            if (collection.countDocuments() == 0) {
                System.out.println("No se encuentran documentos en la BBDD");
            } else {
                for (Document encontrado : collection.find()) {
                    System.out.println(encontrado.toJson());
                }
            }

            /**
             * Sacar la creacion de documentos fuera del main
             */

            //Creación del documento
            Document doc = new Document("titulo", "Ballena99708")
                    .append("anyo", 2026)
                    .append("completado", true);

            System.out.println("\nInsertando nuevo documento");

            //Insertamos el documento en la colección
            if (collection.insertOne(doc).wasAcknowledged()) {
                System.out.println("\nNuevo documento insertado correctamente");
            }

            /**
             * Consultar los documentos dentro de la Collection
             */

            //Consulta básica
            System.out.println("Documentos encontrados en la BBDD");
            for (Document encontrado : collection.find()) {
                System.out.println(encontrado.toJson());
            }
        } catch(Exception e) {
            // Trata tu excepción
            System.err.println(e.getMessage());
        }
    }

    /**
     * Esto que es¿?
     */
    public static void miau() {
        System.out.println("""
                -----------------
                1. Broderk
                2. Broderk?
                3. Broderk!
                -----------------
                """);
    }
}


