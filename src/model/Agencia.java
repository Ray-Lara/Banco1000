package src.model;

import java.util.ArrayList;

public class Agencia {
    private int numeroAgencia;
    private ArrayList<Conta> contas;

    public Agencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
        this.contas = new ArrayList<>();
    }

    public int getnumeroAgencia() {
        return numeroAgencia;
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public Conta buscarConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }
}
