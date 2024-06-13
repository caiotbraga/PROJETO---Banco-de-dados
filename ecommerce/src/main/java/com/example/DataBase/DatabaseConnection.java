package com.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String USER = "admin";
    private static final String PASSWORD = "adm123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // public static Connection getConnection(String nome, String senha) throws SQLException {
    //     return DriverManager.getConnection(URL, nome, senha);
    // }
}
