package br.edu.ifpr.ApiSpringConsumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * Modelo que representa a localização recente do personagem da API de Rick and Morty.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Localizacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    public Localizacao() {}

    public Localizacao(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
