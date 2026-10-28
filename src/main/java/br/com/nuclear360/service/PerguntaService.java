package br.com.nuclear360.service;

import br.com.nuclear360.model.Pergunta;
import br.com.nuclear360.model.PerguntaStatus;
import br.com.nuclear360.model.PerguntaTipo;
import org.springframework.data.domain.Page;

public interface PerguntaService {

    Pergunta create(Pergunta pergunta);

    Pergunta update(Long id, Pergunta update);

    void delete(Long id);

    Page<Pergunta> findAll(Integer page, Integer size);

    Page<Pergunta> findByStatus(PerguntaStatus status, Integer page, Integer size);

    Page<Pergunta> findByTipo(PerguntaTipo tipo, Integer page, Integer size);

    Pergunta findById(Long id);
}