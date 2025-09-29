package com.ejemplo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Escuela";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion establecida");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " +  e.getMessage());
        }
        return conn;
    }

    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexion cerrada correctamente");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: ");
                e.printStackTrace();
            }
        }
    }
}