package me.rflores.sistemaventas.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = Config.getProperty("mysql_conexion");
    private static final String DB_USER = Config.getProperty("mysql_user");
    private static final String DB_PASSWORD = Config.getProperty("mysql_password");

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
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