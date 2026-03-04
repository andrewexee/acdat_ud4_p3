package org.andrew;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.andrew.db.SingletonConnection;

import org.andrew.model.Libro;
import org.andrew.model.Pedido;
import org.bson.Document;

import java.util.Scanner;

/**
 * @author Andrés Iglesias Camacho
 * @date 30.01.2026
 */
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        try {
            // Conectamos con la DB
            MongoDatabase db = SingletonConnection.getCliente().getDatabase("Biblioteca");

            System.out.println("""
                ==============================
                        APP PRINCIPAL         
                    GESTIÓN DE BIBLIOTECA
                ==============================""");

            do {
                menuPrincipal();
                System.out.print("Elige: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        crear(db);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("""
                                ==============================
                                           NOS VEMOS
                                ==============================
                                """);
                        break;
                    default:
                }
            } while (opcion != 5);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo vacío para mostrar el menú de opciones
     */
    public static void menuPrincipal() {
        System.out.println("""
                -----------------
                1. Crear
                2. Insertar
                3. Actualizar
                4. Borrar
                5. Salir
                -----------------
                """);
    }

    public static void crear(MongoDatabase db) {
        Scanner selector = new Scanner(System.in);
        String titulo, autor;
        double precio;

        String titulo2;
        int cantidad;
        double total;

        System.out.println("""
                -_-_-_-_-_-_-_-_-_-
                1. Alta Libros
                2. Crear Pedido
                -_-_-_-_-_-_-_-_-_-""");

        int op = selector.nextInt();
        selector.nextLine();

        switch (op) {
            case 1:
                MongoCollection<Document> colLibros = db.getCollection("Libros");
                System.out.println("Inserta (Titulo, autor, precio):");
                titulo = selector.nextLine();
                autor = selector.nextLine();
                precio = selector.nextDouble();

                Libro libro = new Libro(titulo, autor, precio);

                if (colLibros.insertOne(libro.generarLibro()).wasAcknowledged()) {
                    System.out.println("\nNuevo LIBRO insertado correctamente");
                }
                break;
            case 2:
                MongoCollection<Document> colPedidos = db.getCollection("Pedidos");
                System.out.println("Inserta (Titulo, cantidad, total):");
                titulo2 = selector.nextLine();
                cantidad = selector.nextInt();
                total = selector.nextDouble();

                Pedido pedido = new Pedido(titulo2, cantidad, total);

                if (colPedidos.insertOne(pedido.generarPedido()).wasAcknowledged()) {
                    System.out.println("\nNuevo PEDIDO insertado correctamente");
                }
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}


