package src.model;

import java.util.ArrayList;

public abstract class Conta {
    private Cliente cliente;
    private int numeroConta;
    private String tipo;
    private double saldo;
    private Agencia agencia;
    private ArrayList<Movimentacao> movimentacoes;

    public Conta(Cliente cliente, Agencia agencia, String tipo, double saldo) {
        this.cliente = cliente;
        this.tipo = tipo;
        this.saldo = saldo;
        this.numeroConta = gerarNumeroConta();
        this.agencia = agencia;
        this.movimentacoes = new ArrayList<>();
    }

    public abstract void exibirDetalhes();

    public void adicionarMovimentacao(Movimentacao movimentacao) {
        this.movimentacoes.add(movimentacao);
    }

    private int gerarNumeroConta() {
        return (int) (Math.random() * 1000);
    }

    public int getNumConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            Movimentacao movimentacao = new Movimentacao("Depósito", cliente, "Depósito realizado", valor);
            movimentacoes.add(movimentacao);
        }
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            Movimentacao movimentacao = new Movimentacao("Saque", cliente, "Saque realizado", valor);
            movimentacoes.add(movimentacao);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void exibirMovimentacoes() {
        System.out.println("===== Movimentações =====");
        for (Movimentacao mov : movimentacoes) {
            System.out.println(mov);
        }
    }

    @Override
    public String toString() {
        return "Conta número: " + numeroConta + ", Tipo: " + tipo + ", Saldo: R$" + saldo;
    }
}
