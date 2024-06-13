package com.example.Cadastros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.DataBase.DatabaseConnection;

public class CadastroCliente {

    public static void adicionarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Digite o sexo (m/f/o): ");
        String sexo = scanner.next();
        System.out.print("Digite a idade: ");
        int idade = scanner.nextInt();
        System.out.print("Digite a data de nascimento (AAAA-MM-DD): ");
        String dataNascimento = scanner.next();

        String sql = "INSERT INTO cliente (nome, sexo, idade, nascimento) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, sexo);
            pstmt.setInt(3, idade);
            pstmt.setDate(4, java.sql.Date.valueOf(dataNascimento));
            pstmt.executeUpdate();
            System.out.println("Cliente adicionado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
