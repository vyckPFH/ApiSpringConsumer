package br.edu.ifpr.ApiSpringConsumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

/**
 * Modelo de domínio que representa o Personagem retornado diretamente
 * pela API pública do Rick and Morty, sem necessidade de DTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Personagem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origem origin;
    private Localizacao location;
    private String image;
    private List<String> episode;

    public Personagem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Origem getOrigin() {
        return origin;
    }

    public void setOrigin(Origem origin) {
        this.origin = origin;
    }

    public Localizacao getLocation() {
        return location;
    }

    public void setLocation(Localizacao location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(List<String> episode) {
        this.episode = episode;
    }
}
