package org.andrew.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

/**
 * @author Andrés Iglesias Camacho
 * @date 04.03.2026
 */
public class Libro {
    private MongoCollection<Document> db; // Referencia a la colección de libros en la base de datos

    private String titulo;
    private String autor;
    private double precio;

    private static int idActual; // Variable estática para llevar el seguimiento del último ID asignado

    public Libro(MongoCollection<Document> db, String titulo, String autor, double precio) {
        this.db = db;
        idActual = obtenerUltimoID(); // Obtenemos el último ID asignado al crear un nuevo libro
        ++idActual; // Incrementamos el ID para el nuevo libro
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    /**
     * Obtiene el último ID asignado en la colección y lo devuelve.
     * @return El último ID asignado, o 0 si no hay documentos en la colección.
     */
    public int obtenerUltimoID(){
        Document ultimoDoc = db
                .find()
                .sort(Sorts.descending("ID"))
                .limit(1)
                .first();

        if (ultimoDoc != null) {
            return ultimoDoc.getInteger("ID");
        }

        return 0; // Si no hay documentos, empezamos desde 0
    }

    /**
     * Genera un documento de libro con los datos actuales del objeto.
     * @return Documento que representa el libro listo para ser insertado en la base de datos.
     */
    public Document generarLibro() {
        return new Document("ID", idActual)
                .append("Titulo", this.titulo)
                .append("Autor", this.autor)
                .append("Precio", this.precio);
    }
}
