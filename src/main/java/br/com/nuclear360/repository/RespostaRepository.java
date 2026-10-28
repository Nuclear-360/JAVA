package br.com.nuclear360.repository;

import br.com.nuclear360.model.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Page<Resposta> findByPerguntaId(Long perguntaId, Pageable pageable);
}
