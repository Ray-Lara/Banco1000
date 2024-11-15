package src.model;

import java.util.Date;

public class Transferencia {

    private Conta contaOrigem;
    private Conta contaDestino;
    private double valor;
    private Date data;

    public Transferencia(Conta contaOrigem, Conta contaDestino, double valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = new Date();
    }

    public boolean realizarTransferencia() {
        if (valor > 0 && contaOrigem.getSaldo() >= valor) {
            contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);

            Movimentacao movimentacaoOrigem = new Movimentacao("Transferência", contaOrigem.getCliente(),
                    "Transferência realizada para conta " + contaDestino.getNumConta(), valor);
            Movimentacao movimentacaoDestino = new Movimentacao("Transferência", contaDestino.getCliente(),
                    "Transferência recebida de conta " + contaOrigem.getNumConta(), valor);
            contaOrigem.adicionarMovimentacao(movimentacaoOrigem);
            contaDestino.adicionarMovimentacao(movimentacaoDestino);
            return true;
        } else {
            System.out.println("Transferência inválida! Verifique o saldo ou o valor da transferência.");
            return false;
        }
    }

    public Date getData() {
        return data;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public double getValor() {
        return valor;
    }

}
