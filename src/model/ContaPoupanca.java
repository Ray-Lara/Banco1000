package src.model;

public class ContaPoupanca extends Conta {
    private static final double RENDIMENTO_MENSAL = 0.005;

    public ContaPoupanca(Cliente cliente, Agencia agencia, double saldoInicial) {
        super(cliente, agencia,"Conta Poupança", saldoInicial);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Conta Poupança - Agência: " + getAgencia().getnumeroAgencia() + "Número: " + getNumConta() + " | Saldo: " + getSaldo());
        System.out.println("Rendimento mensal: " + (RENDIMENTO_MENSAL * 100) + "%");
    }

    public void aplicarRendimento() {
        depositar(getSaldo() * RENDIMENTO_MENSAL);
    }
}
