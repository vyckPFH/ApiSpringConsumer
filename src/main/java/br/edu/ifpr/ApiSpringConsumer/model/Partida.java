package br.edu.ifpr.ApiSpringConsumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entidade JPA que representa o registro de uma partida jogada ou cadastrada no sistema.
 * Atende aos requisitos da disciplina de POO:
 * - Atributos privados com getters e setters (Encapsulamento)
 * - Construtores padrão e parametrizados
 * - Anotações JPA para persistência em banco de dados
 */
@Entity
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePersonagem;
    private String imagemPersonagem;
    private String statusResultado; // "Vitória" ou "Derrota"
    private int tentativasUsadas;
    private int pistasUsadas;
    private String observacao;
    private LocalDateTime dataRegistro;

    public Partida() {
        this.dataRegistro = LocalDateTime.now();
    }

    public Partida(String nomePersonagem, String imagemPersonagem, String statusResultado, int tentativasUsadas, int pistasUsadas, String observacao) {
        this.nomePersonagem = nomePersonagem;
        this.imagemPersonagem = imagemPersonagem;
        this.statusResultado = statusResultado;
        this.tentativasUsadas = tentativasUsadas;
        this.pistasUsadas = pistasUsadas;
        this.observacao = observacao;
        this.dataRegistro = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getImagemPersonagem() {
        return imagemPersonagem;
    }

    public void setImagemPersonagem(String imagemPersonagem) {
        this.imagemPersonagem = imagemPersonagem;
    }

    public String getStatusResultado() {
        return statusResultado;
    }

    public void setStatusResultado(String statusResultado) {
        this.statusResultado = statusResultado;
    }

    public int getTentativasUsadas() {
        return tentativasUsadas;
    }

    public void setTentativasUsadas(int tentativasUsadas) {
        this.tentativasUsadas = tentativasUsadas;
    }

    public int getPistasUsadas() {
        return pistasUsadas;
    }

    public void setPistasUsadas(int pistasUsadas) {
        this.pistasUsadas = pistasUsadas;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getDataFormatada() {
        if (this.dataRegistro == null) {
            return "";
        }
        return this.dataRegistro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
