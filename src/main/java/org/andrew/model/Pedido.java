package org.andrew.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

/**
 * @author Andrés Iglesias Camacho
 * @date 04.03.2026
 */
public class Pedido {
    private MongoCollection<Document> db; // Referencia a la colección de pedidos en la base de datos

    private String nLibro;
    private int cantidad;
    private double total;

    private static int idActual; // Variable estática para llevar el seguimiento del último ID asignado

    public Pedido(MongoCollection<Document> db, String nLibro, int cantidad, double total) {
        this.db = db;
        idActual = obtenerUltimoID(); // Obtenemos el último ID asignado al crear un nuevo pedido
        ++idActual; // Incrementamos el ID para el nuevo pedido
        this.nLibro = nLibro;
        this.cantidad = cantidad;
        this.total = total;
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
     * Genera un documento de pedido con los datos actuales del objeto.
     * @return Documento que representa el pedido listo para ser insertado en la base de datos.
     */
    public Document generarPedido() {
        return new Document("ID", idActual)
                .append("Libro", this.nLibro)
                .append("Cantidad", this.cantidad)
                .append("Total", this.total);
    }
}
