package com.example.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class Autenticacao {

    public static String nome;
    public static String senha;

    // public Autenticacao(String nomeEx, String senhaEx){
    // this.nome = nomeEx;
    // this.senha = senhaEx;
    // }

        public static boolean autenticar() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o nome de usuário: ");
            nome = scanner.next();
            System.out.print("Digite a senha: ");
            senha = scanner.next();

            try (Connection connection = DatabaseConnection.getConnection(nome, senha)) {
                System.out.println("Login bem-sucedido.");
                return true;
            } catch (SQLException e) {
                System.out.println("Falha no login: " + e.getMessage());
                return false;
            }
        }
}
