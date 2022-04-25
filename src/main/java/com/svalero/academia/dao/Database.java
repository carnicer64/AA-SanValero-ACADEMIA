package com.svalero.academia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.svalero.academia.util.Constants.*;

public class Database {

    private Connection connection;

    public Connection getConnection() {
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            System.out.println("Conectado!");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No se ha podido cargar el driver de conexión. Verifique que los drivers están disponibles");
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos. Comprueba que los datos son correctos y que el servidor se ha iniciado");
            sqle.printStackTrace();
        }

        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos. Comprueba que los datos son correctos y que el servidor se ha iniciado");
            sqle.printStackTrace();
        }
    }
}
