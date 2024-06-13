package com.example.Cadastros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.DataBase.DatabaseConnection;

public class CadastroProduto {
    
    public static void adicionarProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.next();
        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite a descrição: ");
        String descricao = scanner.next();
        System.out.print("Digite o preço: ");
        double preco = scanner.nextDouble();

        String sql = "INSERT INTO produto (nome, quantidade, descricao, valor) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, quantidade);
            pstmt.setString(3, descricao);
            pstmt.setDouble(4, preco);
            pstmt.executeUpdate();
            System.out.println("Produto adicionado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}