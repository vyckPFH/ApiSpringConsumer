package br.edu.ifpr.ApiSpringConsumer.repository;

import br.edu.ifpr.ApiSpringConsumer.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface Repository para persistência da entidade Partida via Spring Data JPA.
 */
@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findAllByOrderByDataRegistroDesc();
}
