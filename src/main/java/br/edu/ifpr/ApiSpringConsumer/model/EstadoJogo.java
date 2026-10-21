package br.edu.ifpr.ApiSpringConsumer.model;

import java.text.Normalizer; /*
  é uma classe do Java que fornece métodos para normalizar strings, ou seja,
  transformar caracteres acentuados em suas formas básicas (por exemplo, "é" em "e") e remover diacríticos.
  Isso é útil para comparar strings de forma consistente, independentemente de acentos ou outros sinais diacríticos.
*/
import java.util.ArrayList;
import java.util.List;


/**
 * estado atual do jogo rodando na RAM * 
 */
public class EstadoJogo  { 

    private Personagem personagem; // O personagem atual que o usuário deve adivinhar (JSON retornado pela API)
    private List<Pista> todasPistas = new ArrayList<>(); // pedaços de json que se tornam pistas do personagem
    private List<Pista> pistasReveladas = new ArrayList<>(); // pistas que o usuário já usou
    private int indicePistaAtual = 0; // qtd de pistas que o usuário já usou
    private int tentativasRestantes = 5; // qtd de tentativas restantes para o usu a adivinhar
    private int totalTentativas = 5; // qtd de tentativas limite que o usuário pode tentar
    private List<String> palpitesErrados = new ArrayList<>(); // palpites que o usuário já usou
    private String status = "JOGANDO"; // JOGANDO, VITORIA, DERROTA (o status do jogo)
    private String mensagemResultado = ""; // ganhou ou perdeu? 

    public EstadoJogo() {}
//iniciar um jogo
    public EstadoJogo(Personagem personagem, List<Pista> todasPistas) { 
        this.personagem = personagem;
        this.todasPistas = todasPistas;
        this.tentativasRestantes = 5;
        this.totalTentativas = 5;
        this.indicePistaAtual = 0;
        this.status = "JOGANDO";
        if (todasPistas != null && !todasPistas.isEmpty()) {
            this.pistasReveladas.add(todasPistas.get(0));
        }
    }
// se tiver uma pista revelada, retorna uma
    public Pista getPistaAtual() {
        if (pistasReveladas.isEmpty()) {
            return null;
        }
        return pistasReveladas.get(pistasReveladas.size() - 1); // "retorna a última pos de pistas reveladas"
    }
// verifica se tem mais pistas para revelar
    public boolean temMaisPistas() {
        return indicePistaAtual + 1 < todasPistas.size();
    }
// revela a próxima pistado vetor de pistas, se tiver mais pistas
    public void revelarProximaPista() {
        if (temMaisPistas()) {
            indicePistaAtual++;
            pistasReveladas.add(todasPistas.get(indicePistaAtual));
        }
    }
// ve se palpite == personagem
    public boolean validarPalpite(String palpite) {
        if (palpite == null || personagem == null || personagem.getName() == null) {
            return false;
        }

        String palpiteNormalizado = normalizar(palpite);
        String nomeNormalizado = normalizar(personagem.getName());

        boolean acertou = palpiteNormalizado.equals(nomeNormalizado);

        if (acertou) {
            this.status = "VITORIA";
            this.mensagemResultado = "Boa NERDOLA!";
        } else {
            this.tentativasRestantes--;
            this.palpitesErrados.add(palpite.trim());

            // Revela automaticamente a próxima pista se ainda houver
            if (temMaisPistas()) {
                revelarProximaPista();
            }

            if (this.tentativasRestantes <= 0) {
                this.status = "DERROTA";
                this.mensagemResultado = "you suck";
            }
        }
        return acertou;
    }

    public void desistir() {
        this.status = "DERROTA";
        this.mensagemResultado = "you suck";
    }
// pega a entrada do usuario e tira tds os caracteres especiais e deixa tudo minusculo
    private String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .replaceAll("[^a-z0-9]", "");
    }

    // Getters e Setters
    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public List<Pista> getTodasPistas() {
        return todasPistas;
    }

    public void setTodasPistas(List<Pista> todasPistas) {
        this.todasPistas = todasPistas;
    }

    public List<Pista> getPistasReveladas() {
        return pistasReveladas;
    }

    public void setPistasReveladas(List<Pista> pistasReveladas) {
        this.pistasReveladas = pistasReveladas;
    }

    public int getIndicePistaAtual() {
        return indicePistaAtual;
    }

    public void setIndicePistaAtual(int indicePistaAtual) {
        this.indicePistaAtual = indicePistaAtual;
    }

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    public void setTentativasRestantes(int tentativasRestantes) {
        this.tentativasRestantes = tentativasRestantes;
    }

    public int getTotalTentativas() {
        return totalTentativas;
    }

    public void setTotalTentativas(int totalTentativas) {
        this.totalTentativas = totalTentativas;
    }

    public List<String> getPalpitesErrados() {
        return palpitesErrados;
    }

    public void setPalpitesErrados(List<String> palpitesErrados) {
        this.palpitesErrados = palpitesErrados;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagemResultado() {
        return mensagemResultado;
    }

    public void setMensagemResultado(String mensagemResultado) {
        this.mensagemResultado = mensagemResultado;
    }
}
