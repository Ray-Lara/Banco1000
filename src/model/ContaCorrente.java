package src.model;

public class ContaCorrente extends Conta {
    private static final double TAXA_MANUTENCAO = 20.0;

    public ContaCorrente(Cliente cliente, Agencia agencia, double saldoInicial) {
        super(cliente, agencia, "Conta Corrente", saldoInicial);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Conta Corrente - Agência: " + getAgencia().getnumeroAgencia() + "Número: " + getNumConta() + " | Saldo: " + getSaldo());
        System.out.println("Taxa de manutenção mensal: R$" + TAXA_MANUTENCAO);
    }

    public void aplicarTaxaManutencao() {
        depositar(-TAXA_MANUTENCAO);
    }
}
