package br.edu.ifpr.ApiSpringConsumer.model;

/**
 * Classe de modelo para representar uma pista do jogo.
 */
public class Pista {
    private int numero;
    private String tipo;
    private String descricao;

    public Pista() {}

    public Pista(int numero, String tipo, String descricao) {
        this.numero = numero;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
