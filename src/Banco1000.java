package src;

import src.model.Cliente;
import src.model.Conta;
import src.model.Movimentacao;
import src.model.Transferencia;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco1000 {

    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static Cliente usuarioLogado = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Banco1000 =====");
            System.out.println("1. Cadastro de Usuário");
            System.out.println("2. Criar Conta");
            System.out.println("3. Login");
            System.out.println("4. Realizar Operações Bancárias");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    criarConta(scanner);
                    break;
                case 3:
                    login(scanner);
                    break;
                case 4:
                    if (usuarioLogado != null) {
                        realizarOperacoes(scanner);
                    } else {
                        System.out.println("Você precisa fazer login primeiro!");
                    }
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.println("===== Cadastro de Usuário =====");
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do usuário: ");

        String cpf;
        while (true) {
            System.out.print("Digite o CPF do usuário (11 caracteres): ");
            cpf = scanner.nextLine();

            if (cpf.length() != 11) {
                System.out.println("Erro: O CPF deve conter exatamente 11 caracteres. Tente novamente.");
                continue;
            }

            boolean cpfJaCadastrado = false;
            for (Cliente cliente : clientes) {
                if (cliente.getCpf().equals(cpf)) {
                    System.out.println("Erro: Já existe um usuário cadastrado com este CPF!");
                    cpfJaCadastrado = true;
                    break;
                }
            }

            if (cpfJaCadastrado) {
                continue;
            }

            break;
        }

        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, senha);
        clientes.add(cliente);
    }

    private static void criarConta(Scanner scanner) {
        if (usuarioLogado == null) {
            System.out.println("Você precisa fazer login para criar uma conta!");
            return;
        }

        System.out.println("===== Criar Conta =====");
        System.out.print("Digite o tipo de conta (corrente/poupanca): ");
        String tipo = scanner.nextLine();
        System.out.print("Digite o saldo inicial: ");
        double saldo = scanner.nextDouble();


        Conta conta = new Conta(usuarioLogado, tipo, saldo);
        usuarioLogado.adicionarConta(conta);
        System.out.println("Conta criada com sucesso! Número da conta: " + conta.getNumConta());
    }


    private static void login(Scanner scanner) {
        System.out.println("===== Login =====");
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();


        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf) && cliente.getSenha().equals(senha)) {
                usuarioLogado = cliente;
                System.out.println("Login realizado com sucesso!");
                return;
            }
        }

        System.out.println("Credenciais inválidas!");
    }

    private static void realizarOperacoes(Scanner scanner) {
        while (true) {
            System.out.println("===== Operações Bancárias =====");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Ver Movimentações");
            System.out.println("6. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    consultarSaldo(scanner);
                    break;
                case 2:
                    depositar(scanner);
                    break;
                case 3:
                    sacar(scanner);
                    break;
                case 4:
                    transferir(scanner);
                    break;
                case 5:
                    verMovimentacoes(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void consultarSaldo(Scanner scanner) {
        System.out.println("===== Consultar Saldo =====");
        for (Conta conta : usuarioLogado.getContas()) {
            System.out.println(conta);
        }
    }

    private static void depositar(Scanner scanner) {
        System.out.println("===== Depósito =====");
        System.out.print("Digite o número da conta: ");
        int numConta = scanner.nextInt();
        System.out.print("Digite o valor a ser depositado: ");
        double valor = scanner.nextDouble();


        for (Conta conta : usuarioLogado.getContas()) {
            if (conta.getNumConta() == numConta) {
                conta.depositar(valor);
                System.out.println("Depósito realizado com sucesso!");
                return;
            }
        }

        System.out.println("Conta não encontrada!");
    }

    private static void sacar(Scanner scanner) {
        System.out.println("===== Saque =====");
        System.out.print("Digite o número da conta: ");
        int numConta = scanner.nextInt();
        System.out.print("Digite o valor a ser sacado: ");
        double valor = scanner.nextDouble();


        for (Conta conta : usuarioLogado.getContas()) {
            if (conta.getNumConta() == numConta) {
                conta.sacar(valor);
                System.out.println("Saque realizado com sucesso!");
                return;
            }
        }

        System.out.println("Conta não encontrada!");
    }

    private static void transferir(Scanner scanner) {
        System.out.println("===== Transferência =====");
        System.out.print("Digite o número da conta de origem: ");
        int numContaOrigem = scanner.nextInt();
        System.out.print("Digite o número da conta de destino: ");
        int numContaDestino = scanner.nextInt();
        System.out.print("Digite o valor a ser transferido: ");
        double valor = scanner.nextDouble();

        Conta contaOrigem = null, contaDestino = null;

        for (Conta conta : usuarioLogado.getContas()) {
            if (conta.getNumConta() == numContaOrigem) {
                contaOrigem = conta;
            }
            if (conta.getNumConta() == numContaDestino) {
                contaDestino = conta;
            }
        }

        if (contaOrigem != null && contaDestino != null) {

            Transferencia transferencia = new Transferencia(contaOrigem, contaDestino, valor);
            if (transferencia.realizarTransferencia()) {
                System.out.println("Transferência realizada com sucesso!");
            }
        } else {
            System.out.println("Uma ou ambas as contas não foram encontradas!");
        }
    }

    private static void verMovimentacoes(Scanner scanner) {
        System.out.println("===== Movimentações =====");
        System.out.print("Digite o número da conta: ");
        int numConta = scanner.nextInt();

        for (Conta conta : usuarioLogado.getContas()) {
            if (conta.getNumConta() == numConta) {
                conta.exibirMovimentacoes();
                return;
            }
        }

        System.out.println("Conta não encontrada!");
    }
}
