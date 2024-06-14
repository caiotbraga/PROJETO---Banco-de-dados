package com.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/empresa";

    public static Connection getConnection(String nome, String senha) throws SQLException {
        return DriverManager.getConnection(URL, nome, senha);
    }
}
