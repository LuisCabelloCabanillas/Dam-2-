package com.ejemplo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {

                // Insertar un alumno de ejemplo
                String insertSql = "INSERT INTO alumnos (nombre, edad) VALUES (?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, "Juan Pérez"); // Nombre del alumno
                    insertStmt.setInt(2, 20);              // Edad del alumno
                    int filasInsertadas = insertStmt.executeUpdate();
                    System.out.println("Filas insertadas: " + filasInsertadas);
                }

                // Buscar solo alumnos mayores de 20 años
                String buscarSql = "SELECT * FROM Alumnos WHERE edad > 20";
                try (PreparedStatement buscarStmt = conn.prepareStatement(buscarSql);
                     ResultSet rs = buscarStmt.executeQuery()) {

                    System.out.println("Alumnos mayores de 20 años:");
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        int edad = rs.getInt("edad");
                        System.out.println(id + " - " + nombre + " - " + edad);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
