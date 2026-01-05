/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.irfin.cafe.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hansen, Irfin
 */
public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/cafe";
    private static final String USER = "cafe_dba";
    private static final String PASSWORD = "user123";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            System.out.println("Connected to DB.");
        }
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } 
            catch (SQLException e) {
                // log, do not swallow silently
                e.printStackTrace(System.out);
            }
        }
    }
}
