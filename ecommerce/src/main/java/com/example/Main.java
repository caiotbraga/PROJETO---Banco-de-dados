package com.example;

import java.util.Scanner;

import com.example.Cadastros.CadastroCliente;
import com.example.Cadastros.CadastroProduto;
import com.example.Cadastros.CadastroVenda;
import com.example.DataBase.Autenticacao;

public class Main {
    public static void main(String[] args) {

        String cargoUsuario = Autenticacao.authenticate();

        if (cargoUsuario == null) {
            System.out.println("Saindo...");
            return;
        }

        switch (cargoUsuario) {
            case "admin":
                menuADM();
                break;
            case "gerente":
                menugGerente();
                break;
            case "funcionario":
                menuFuncionario();
                break;
            default:
                System.out.println("Cargo desconhecido. Saindo...");
        }
    }

    private static void menuADM() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Admin Menu:");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Adicionar Cliente");
            System.out.println("3. Fazer venda");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    CadastroProduto.adicionarProduto();
                    break;
                case 2:
                    CadastroCliente.adicionarCliente();
                    break;
                case 3:
                    CadastroVenda.realizarVenda();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção invalida. Tente novamente.");
            }
        }
    }

    private static void menugGerente() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu do Gerente:");
            System.out.println("1. Pesquisar Registros");
            System.out.println("2. Editar Registros");
            System.out.println("3. Deletar Registros");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            switch (choice) {
                // Adicione métodos para as opções do gerente
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    

    private static void menuFuncionario() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Funcionario:");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Adicionar Funcionario");
            System.out.println("3. Ver vendas");
            System.out.println("4. Exit");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CadastroProduto.adicionarProduto();
                    break;
                case 2:
                    CadastroCliente.adicionarCliente();
                    break;
                case 3:
                    // Implement method to view sales records
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
