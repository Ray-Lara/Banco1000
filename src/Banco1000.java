package src;

import src.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco1000 {

    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static Cliente usuarioLogado = null;
    private static ArrayList<Agencia> agencias = new ArrayList<>();

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
        System.out.print("Digite o número da agência: ");
        int numAgencia = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Informe o número correspondente ao tipo da conta: (1 - corrente/ 2 -poupanca/ 3 - salário): ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        Agencia agencia = Banco1000.buscarAgencia(numAgencia);
        if (agencia == null) {
            agencia = new Agencia(numAgencia);
            Banco1000.adicionarAgencia(agencia);
        }

        Conta conta = null;
        double saldo = 0.00;

        switch (escolha) {
            case 1 :
                System.out.print("Digite o saldo inicial: ");
                saldo = scanner.nextDouble();
                conta = new ContaCorrente(usuarioLogado, agencia, saldo);
                break;

            case 2 :

                System.out.print("Digite o saldo inicial: ");
                saldo = scanner.nextDouble();
                conta = new ContaPoupanca(usuarioLogado, agencia, saldo);
                break;

            case 3 :

                System.out.print("Digite o saldo inicial: ");
                saldo = scanner.nextDouble();
                conta = new ContaSalario(usuarioLogado, agencia, saldo);
                break;
        }

        if (conta != null) {
            usuarioLogado.adicionarConta(conta);

            agencia.adicionarConta(conta);
            System.out.println("Conta criada com sucesso! Número da conta: " + conta.getNumConta());
        }

    }

    public static Agencia buscarAgencia(int numAgencia) {
        for (Agencia agencia : agencias) {
            if (agencia.getnumeroAgencia() == numAgencia) {
                return agencia;
            }
        }
        return null;
    }

    public static void adicionarAgencia(Agencia agencia) {
        agencias.add(agencia);
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
            System.out.println("1. Consultar Conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Ver Movimentações");
            System.out.println("6. Listar Contas");
            System.out.println("7. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    consultarConta(scanner);
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
                    listarContas(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void consultarConta(Scanner scanner) {
        System.out.println("===== Consultar Conta =====");
        System.out.print("Digite o número da agência: ");
        int numAgencia = scanner.nextInt();
        System.out.print("Digite o número da conta: ");
        int numConta = scanner.nextInt();

        boolean contaEncontrada = false;


        for (Conta conta : usuarioLogado.getContas()) {
            if (conta.getNumConta() == numConta && conta.getAgencia().getnumeroAgencia() == numAgencia) {
                conta.exibirDetalhes();
                contaEncontrada = true;
                break;
            }
        }

        if (!contaEncontrada) {
            System.out.println("Conta não encontrada para o número da conta e agência informados.");
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
    private static void listarContas(Scanner scanner) {
        System.out.println("===== Contas cadastradas: =====");

        boolean encontrouConta = false;

        for (Agencia agencia : agencias) {
            for (Conta conta : agencia.getContas()) {
                if (conta.getCliente().equals(usuarioLogado)) {
                    System.out.println("Agência: " + agencia.getnumeroAgencia() + " - Número da conta: " + conta.getNumConta());
                    encontrouConta = true;
                }
            }
        }

        if (!encontrouConta) {
            System.out.println("Nenhuma conta encontrada para o usuário " + usuarioLogado.getNome() + ".");
        }
    }
}
