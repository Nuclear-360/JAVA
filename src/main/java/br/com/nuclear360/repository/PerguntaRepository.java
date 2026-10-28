package br.com.nuclear360.repository;

import br.com.nuclear360.model.Pergunta;
import br.com.nuclear360.model.PerguntaStatus;
import br.com.nuclear360.model.PerguntaTipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    Page<Pergunta> findByStatus(PerguntaStatus status, Pageable pageable);

    Page<Pergunta> findByTipo(PerguntaTipo tipo, Pageable pageable);
}