package com.example.Registros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import com.example.DataBase.Autenticacao;
import com.example.DataBase.DatabaseConnection;

import java.sql.Statement;

public class Registros {
    public static void buscarRegistros() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Buscar Produtos: ");
    System.out.print("Digite o nome do produto ou deixe em branco para listar todos: ");
    String nomeProduto = scanner.nextLine().trim();

    try (Connection connection = DatabaseConnection.getConnection(Autenticacao.nome, Autenticacao.senha);
         Statement stmt = connection.createStatement()) {

        String sql;
        if (nomeProduto.isEmpty()) {
            sql = "SELECT * FROM produto";
        } 
        else {
            sql = "SELECT * FROM produto WHERE nome LIKE '%" + nomeProduto + "%'";
        }

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            int quantidade = rs.getInt("quantidade");
            String descricao = rs.getString("descricao");
            double valor = rs.getDouble("valor");

            System.out.printf("ID: %d, Nome: %s, Quantidade: %d, Descrição: %s, Valor: %.2f\n",
                    id, nome, quantidade, descricao, valor);
        }

    } catch (SQLException e) {
        e.printStackTrace();

    }
}


public static void editarRegistros() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Editar Produtos:");
    System.out.print("Digite o ID do produto que deseja editar: ");
    int idProduto = scanner.nextInt();
    scanner.nextLine(); 

    try (Connection connection = DatabaseConnection.getConnection(Autenticacao.nome, Autenticacao.senha);
         PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM produto WHERE id = ?")) {

        pstmt.setInt(1, idProduto);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                
                String nomeAtual = rs.getString("nome");
                int quantidadeAtual = rs.getInt("quantidade");
                String descricaoAtual = rs.getString("descricao");
                double valorAtual = rs.getDouble("valor");

                System.out.printf("Nome atual: %s, Quantidade atual: %d, Descrição atual: %s, Valor atual: %.2f\n",
                        nomeAtual, quantidadeAtual, descricaoAtual, valorAtual);

                System.out.print("Novo nome (ou deixe em branco para manter o atual): ");
                String novoNome = scanner.nextLine().trim();
                System.out.print("Nova quantidade (ou -1 para manter a atual): ");
                int novaQuantidade = scanner.nextInt();

                scanner.nextLine(); 

                System.out.print("Nova descrição (ou deixe em branco para manter a atual): ");
                String novaDescricao = scanner.nextLine().trim();
                System.out.print("Novo valor (ou -1 para manter o atual): ");
                double novoValor = scanner.nextDouble();

                
                StringBuilder sql = new StringBuilder("UPDATE produto SET ");
                boolean alteracoesFeitas = false;

                if (!novoNome.isEmpty()) {
                    sql.append("nome = ?, ");
                    alteracoesFeitas = true;
                }
                if (novaQuantidade != -1) {
                    sql.append("quantidade = ?, ");
                    alteracoesFeitas = true;
                }
                if (!novaDescricao.isEmpty()) {
                    sql.append("descricao = ?, ");
                    alteracoesFeitas = true;
                }
                if (novoValor != -1) {
                    sql.append("valor = ?, ");
                    alteracoesFeitas = true;
                }

                if (!alteracoesFeitas) {
                    System.out.println("Nenhuma alteração feita. Retornando...");
                    return;
                }

                
                sql.delete(sql.length() - 2, sql.length());
                sql.append(" WHERE id = ?");
                pstmt.clearParameters(); 
                pstmt.setString(1, novoNome);
                pstmt.setInt(2, novaQuantidade);
                pstmt.setString(3, novaDescricao);
                pstmt.setDouble(4, novoValor);
                pstmt.setInt(5, idProduto);

                
                int linhasAfetadas = pstmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Produto atualizado com sucesso.");
                } 
                else {
                    System.out.println("Falha ao atualizar o produto.");
                }

            } 
            else {
                System.out.println("Produto não encontrado.");
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void excluirRegistros() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Excluir Produtos:");
    System.out.print("Digite o ID do produto que deseja excluir: ");
    int idProduto = scanner.nextInt();

    try (Connection connection = DatabaseConnection.getConnection(Autenticacao.nome, Autenticacao.senha);
         PreparedStatement pstmt = connection.prepareStatement("DELETE FROM produto WHERE id = ?")) {

        pstmt.setInt(1, idProduto);

        int linhasAfetadas = pstmt.executeUpdate();
        if (linhasAfetadas > 0) {
            System.out.println("Produto excluído com sucesso.");

        } 
        else {
            System.out.println("Produto não encontrado ou falha ao excluir.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void listarVendas() {
    String sql = "SELECT * FROM venda";

    try (Connection connection = DatabaseConnection.getConnection(Autenticacao.nome, Autenticacao.senha);
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            int idVenda = rs.getInt("id");
            int idVendedor = rs.getInt("id_vendedor");
            int idCliente = rs.getInt("id_cliente");
            Date dataVenda = rs.getDate("data");
            double valor = rs.getDouble("valor");

            System.out.printf("ID da Venda: %d, Vendedor: %d, Cliente: %d, Data: %s\n",
                    idVenda, idVendedor, idCliente, dataVenda.toString(), valor);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
