package br.edu.ifpr.ApiSpringConsumer.service;

import br.edu.ifpr.ApiSpringConsumer.dto.RickAndMortyCharacterDTO;
import br.edu.ifpr.ApiSpringConsumer.model.Pista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Serviço responsável por consumir a API pública do Rick and Morty
 * e converter os dados brutos recebidos em pistas textuais dinâmicas.
 */
@Service
public class RickAndMortyService {

    private static final String API_URL = "https://rickandmortyapi.com/api/character/{id}";
    private static final int TOTAL_PERSONAGENS = 826;

    private final RestTemplate restTemplate;
    private final Random random = new Random();

    @Autowired
    public RickAndMortyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Sorteia um personagem aleatório da Rick and Morty API.
     */
    public RickAndMortyCharacterDTO sortearPersonagem() {
        int idAleatorio = random.nextInt(TOTAL_PERSONAGENS) + 1;
        return restTemplate.getForObject(API_URL, RickAndMortyCharacterDTO.class, idAleatorio);
    }

    /**
     * Converte os atributos do personagem em uma lista progressiva de pistas textuais.
     */
    public List<Pista> gerarPistas(RickAndMortyCharacterDTO personagem) {
        List<Pista> pistas = new ArrayList<>();
        int numero = 1;

        // Pista 1: Status de vida
        String statusPt = "unknown".equalsIgnoreCase(personagem.getStatus()) ? "Desconhecido" :
                "Alive".equalsIgnoreCase(personagem.getStatus()) ? "Vivo(a)" : "Morto(a)";
        pistas.add(new Pista(numero++, "Status", "O personagem está atualmente " + statusPt + " (" + personagem.getStatus() + ")."));

        // Pista 2: Espécie
        pistas.add(new Pista(numero++, "Espécie", "Pertence à espécie: " + personagem.getSpecies() + "."));

        // Pista 3: Gênero
        String generoPt = "Male".equalsIgnoreCase(personagem.getGender()) ? "Masculino" :
                "Female".equalsIgnoreCase(personagem.getGender()) ? "Feminino" : personagem.getGender();
        pistas.add(new Pista(numero++, "Gênero", "Gênero do personagem: " + generoPt + "."));

        // Pista 4: Origem
        String origem = (personagem.getOrigin() != null && personagem.getOrigin().getName() != null)
                ? personagem.getOrigin().getName() : "Desconhecida";
        pistas.add(new Pista(numero++, "Origem", "Seu planeta ou dimensão de origem é: " + origem + "."));

        // Pista 5: Localização
        String localizacao = (personagem.getLocation() != null && personagem.getLocation().getName() != null)
                ? personagem.getLocation().getName() : "Desconhecida";
        pistas.add(new Pista(numero++, "Localização", "Foi visto(a) recentemente em: " + localizacao + "."));

        // Pista 6: Total de episódios
        int totalEpisodios = (personagem.getEpisode() != null) ? personagem.getEpisode().size() : 0;
        pistas.add(new Pista(numero++, "Aparições", "Aparece em " + totalEpisodios + " episódio(s) da série."));

        // Pista 7: Tipo (se houver) ou primeira letra
        if (personagem.getType() != null && !personagem.getType().trim().isEmpty()) {
            pistas.add(new Pista(numero++, "Subtipo", "Possui o subtipo especial: " + personagem.getType() + "."));
        } else {
            char primeiraLetra = personagem.getName().charAt(0);
            pistas.add(new Pista(numero++, "Letra Inicial", "A primeira letra do nome é '" + primeiraLetra + "'."));
        }

        // Pista 8: Quantidade de letras no nome
        int tamanhoNome = personagem.getName().replace(" ", "").length();
        pistas.add(new Pista(numero++, "Tamanho do Nome", "O nome possui " + tamanhoNome + " letras (sem contar espaços)."));

        return pistas;
    }
}
