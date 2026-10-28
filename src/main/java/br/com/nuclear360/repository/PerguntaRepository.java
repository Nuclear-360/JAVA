package br.com.nuclear360.repository;

import br.com.nuclear360.model.Pergunta;
import br.com.nuclear360.model.PerguntaStatus;
import br.com.nuclear360.model.PerguntaTipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    Page<Pergunta> findByStatus(PerguntaStatus status, Pageable pageable);

    Page<Pergunta> findByTipo(PerguntaTipo tipo, Pageable pageable);

    @EntityGraph(attributePaths = "respostas")
    Optional<Pergunta> findWithRespostaById(Long id);
}