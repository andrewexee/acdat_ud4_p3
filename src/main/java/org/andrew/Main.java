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
    public static final Scanner scanner = new Scanner(System.in);
    static void main() {
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
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        crear(db);
                        break;
                    case 2:
                        consultar(db);
                        break;
                    case 3:
                        consultarID(db);
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
        System.out.print("""
                -----------------
                1. Insertar
                2. Consultar
                3. Buscar
                4. Actualizar
                5. Salir
                -----------------
                """);
    }

    /**
     * Metodo para insertar Libros o Pedidos en sus respectivas colecciones
     * @param db DB donde se generarán las colecciones
     */
    public static void crear(MongoDatabase db) {
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

        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                MongoCollection<Document> colLibros = db.getCollection("Libros");
                System.out.println("Inserta (Titulo, autor, precio):");
                titulo = scanner.nextLine();
                autor = scanner.nextLine();
                precio = scanner.nextDouble();

                Libro libro = new Libro(titulo, autor, precio);

                if (colLibros.insertOne(libro.generarLibro()).wasAcknowledged()) {
                    System.out.println("\nNuevo LIBRO insertado correctamente");
                }
                break;
            case 2:
                MongoCollection<Document> colPedidos = db.getCollection("Pedidos");
                System.out.println("Inserta (Titulo, cantidad, total):");
                titulo2 = scanner.nextLine();
                cantidad = scanner.nextInt();
                total = scanner.nextDouble();

                Pedido pedido = new Pedido(titulo2, cantidad, total);

                if (colPedidos.insertOne(pedido.generarPedido()).wasAcknowledged()) {
                    System.out.println("\nNuevo PEDIDO insertado correctamente");
                }
                break;
            default:
                System.err.println("Opcion no valida");
        }
    }

    public static void consultar(MongoDatabase db) {

        System.out.println("""
                -_-_-_-_-_-_-_-_-_-
                1. Consultar Libros
                2. Consultar Pedidos
                -_-_-_-_-_-_-_-_-_-""");

        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                for (Document doc : db.getCollection("Libros").find()) {
                    System.out.println(doc.toJson());
                }
                break;
            case 2:
                for (Document doc : db.getCollection("Pedidos").find()) {
                    System.out.println(doc.toJson());
                }
                break;
            default:
                System.err.println("Opcion no valida");
        }
    }

    public static void consultarID(MongoDatabase db) {

        System.out.println("""
                -_-_-_-_-_-_-_-_-_-
                1. Buscar Libro
                2. Buscar Pedido
                -_-_-_-_-_-_-_-_-_-""");
        int op = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introduce el Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                for (Document doc : db.getCollection("Libros").find()) {
                    if (doc.get("ID").equals(id)) {
                        System.out.println(doc.toJson());
                    }
                }
                break;
            case 2:

                break;
            default:
                System.err.println("Opcion no valida");
        }
    }
}