package org.andrew.model;

import org.bson.Document;

/**
 * @author Andrés Iglesias Camacho
 * @date 04.03.2026
 */
public class Libro {
    private static int id;
    private String titulo;
    private String autor;
    private double precio;

    public Libro(String titulo, String autor, double precio) {
        ++id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    public Document generarLibro() {
        return new Document("ID", id)
                .append("Titulo", this.titulo)
                .append("Autor", this.autor)
                .append("Precio", this.precio);
    }
}
