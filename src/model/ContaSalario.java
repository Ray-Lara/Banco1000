package src.model;

public class ContaSalario extends Conta {
    private static final int LIMITE_SAQUES = 3;
    private int saquesRealizados;

    public ContaSalario(Cliente cliente, Agencia agencia, double saldoInicial) {
        super(cliente, agencia, "Conta Salário", saldoInicial);
        this.saquesRealizados = 0;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Conta Salário - Agência: " + getAgencia().getnumeroAgencia() + "Número: " + getNumConta() + " | Saldo: " + getSaldo());
        System.out.println("Saques realizados: " + saquesRealizados + " de " + LIMITE_SAQUES);
    }

    @Override
    public void sacar(double valor) {
        if (saquesRealizados < LIMITE_SAQUES) {
            super.sacar(valor);
            saquesRealizados++;
        } else {
            System.out.println("Limite de saques atingido para essa conta.");
        }
    }
}
