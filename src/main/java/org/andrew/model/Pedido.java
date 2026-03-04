package org.andrew.model;

import org.bson.Document;

/**
 * @author Andrés Iglesias Camacho
 * @date 04.03.2026
 */
public class Pedido {
    private String nLibro;
    private int cantidad;
    private double total;

    public Pedido(String nLibro, int cantidad, double total) {
        this.nLibro = nLibro;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Document generarPedido() {
        return new Document("Libro", this.nLibro)
                .append("Cantidad", this.cantidad)
                .append("Total", this.total);
    }
}
