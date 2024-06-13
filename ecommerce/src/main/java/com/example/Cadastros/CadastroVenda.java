package com.example.Cadastros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.DataBase.DatabaseConnection;

public class CadastroVenda {
    public static void realizarVenda() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do vendedor: ");
        int idVendedor = scanner.nextInt();
        System.out.print("Digite o ID do cliente: ");
        int idCliente = scanner.nextInt();
        System.out.print("Digite o ID do produto: ");
        int idProduto = scanner.nextInt();
        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();

        String sql = "CALL realizar_venda(?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idVendedor);
            pstmt.setInt(2, idCliente);
            pstmt.setInt(3, idProduto);
            pstmt.setInt(4, quantidade);
            pstmt.executeUpdate();
            System.out.println("Venda realizada com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
