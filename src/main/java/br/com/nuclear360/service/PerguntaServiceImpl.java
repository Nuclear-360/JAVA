package br.com.nuclear360.service;

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
        return perguntaRepository.save(pergunta);
    }

    @Override
    public Pergunta update(Long id, Pergunta update) {
        Optional<Pergunta> existing = this.perguntaRepository.findById(id);
        if(!existing.isPresent()){
            //lançar erro
        }
        update.setId(id);
        return this.perguntaRepository.save(update);
    }

    @Override
    public void delete(Long id) {
        Optional<Pergunta> existing = this.perguntaRepository.findById(id);
        if(!existing.isPresent()){
            //lançar erro
        }
        this.perguntaRepository.delete(existing.get());
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
        Optional<Pergunta> existing = this.perguntaRepository.findById(id);
        if(!existing.isPresent()){
            //lançar erro
        }
        return existing.get();
    }
}