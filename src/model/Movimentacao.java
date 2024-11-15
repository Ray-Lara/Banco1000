package src.model;

import java.util.Date;

public class Movimentacao {

    private Date data;
    private String tipo;
    private Cliente cliente;
    private String descricao;
    private double valor;

    public Movimentacao(String tipo, Cliente cliente, String descricao, double valor) {
        this.data = new Date();
        this.tipo = tipo;
        this.cliente = cliente;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Data: " + data + ", Tipo: " + tipo + ", Cliente: " + cliente.getNome() + ", Descrição: " + descricao + ", Valor: R$" + valor;
    }
}
