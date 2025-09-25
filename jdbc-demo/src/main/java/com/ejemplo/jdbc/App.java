package com.ejemplo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if  (conn != null) {
                String sql = "SELECT * FROM Alumnos";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                String sqlInsert = "INSERT INTO Alumnos VALUES (?, ?, ?)";


                while (rs.next()){
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    System.out.println(id + " - " + nombre + " - " + edad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
