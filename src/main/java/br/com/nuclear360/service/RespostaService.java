package br.com.nuclear360.service;

import br.com.nuclear360.model.Resposta;
import org.springframework.data.domain.Page;

public interface RespostaService {

    Resposta create(Resposta resposta);

    Page<Resposta> findAll(Integer page, Integer size);

    Resposta update(Long id, Resposta resposta);

    void delete(Long id);

    Page<Resposta> findByPerguntaId(Long perguntaId, Integer page, Integer size);

    Resposta findById(Long id);
}
