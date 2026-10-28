package br.com.nuclear360.service;

import br.com.nuclear360.exceptions.ResourceNotFoundException;
import br.com.nuclear360.model.Pergunta;
import br.com.nuclear360.model.PerguntaStatus;
import br.com.nuclear360.model.PerguntaTipo;
import br.com.nuclear360.repository.PerguntaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerguntaServiceImpl implements  PerguntaService {

    private final PerguntaRepository perguntaRepository;

    public PerguntaServiceImpl(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    @Override
    public Pergunta create(Pergunta pergunta) {
        pergunta.setId(null);
        return perguntaRepository.save(pergunta);
    }

    @Override
    public Pergunta update(Long id, Pergunta update) {
        findById(id);
        update.setId(id);
        return this.perguntaRepository.save(update);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        this.perguntaRepository.delete(findById(id));
    }


    @Override
    public Page<Pergunta> findAll(Integer page, Integer size) {
        return this.perguntaRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    @Override
    public Page<Pergunta> findByStatus(PerguntaStatus status, Integer page, Integer size) {
        return this.perguntaRepository.findByStatus(status, Pageable.ofSize(size).withPage(page));
    }

    @Override
    public Page<Pergunta> findByTipo(PerguntaTipo tipo, Integer page, Integer size) {
        return this.perguntaRepository.findByTipo(tipo, Pageable.ofSize(size).withPage(page));
    }

    @Override
    public Pergunta findById(Long id) {
        return this.perguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("pergunta", id));
    }

    @Override
    public Pergunta findWithRespostaById(Long id) {
        return this.perguntaRepository.findWithRespostaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("pergunta", id));
    }
}