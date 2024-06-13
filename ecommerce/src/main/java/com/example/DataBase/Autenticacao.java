package com.example.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class Autenticacao {
    
    // private String nome;
    // private String senha;

    // public Autenticacao(String nomeEx, String senhaEx){
    //     this.nome = nomeEx;
    //     this.senha = senhaEx;
    // }

    public static String authenticate() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o usuário: ");
        String nome = scanner.next();

        System.out.print("Insira a senha: ");
        String senha = scanner.next();

        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT USER(), CURRENT_USER()");
            if (rs.next()) {
                String usuarioAtual = rs.getString(1);
                if (usuarioAtual.equals(nome + "@" + "localhost")) {
                    System.out.println("Login feito com sucesso!");
                    return nome;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Falha no Login");
        return null;
    }
}
