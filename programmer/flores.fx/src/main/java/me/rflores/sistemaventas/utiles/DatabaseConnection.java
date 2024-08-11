package me.rflores.sistemaventas.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = Config.getProperty("mysql_conexion");
    private static final String USER = Config.getProperty("mysql_user");
    private static final String PASSWORD = Config.getProperty("mysql_password");

    private static Connection connection = null;

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion instanciada con exito");
            } catch (SQLException e) {
                System.out.println("Conexion fallida: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion cerrada con exito!");
            } catch (SQLException e) {
                System.out.println("Cierre de conexion fallida: " + e.getMessage());
            }
        }
    }
}