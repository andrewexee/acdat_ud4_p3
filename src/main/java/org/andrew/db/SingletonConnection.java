package org.andrew.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.Map;

/**
 * @author Andrés Iglesias Camacho
 * @date 30.01.2026
 */
public class SingletonConnection {

    private static MongoClient cliente;

    private SingletonConnection() {
        // Seco
    }

    public static synchronized MongoClient getCliente() {
        Map<String, String> env = Utils.getEnv();

        try {
            if (cliente == null) {
                cliente = MongoClients.create(env.get("URI"));

                System.out.println("Conexion exitosa con el cluster: " + env.get("URI"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return cliente;
    }
}
