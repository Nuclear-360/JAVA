package br.com.nuclear360.service;

import br.com.nuclear360.exceptions.ResourceNotFoundException;
import br.com.nuclear360.model.Resposta;
import br.com.nuclear360.repository.RespostaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RespostaServiceImpl implements RespostaService{

    private final RespostaRepository respostaRepository;

    public RespostaServiceImpl(RespostaRepository  respostaRepository){
        this.respostaRepository = respostaRepository;
    }

    @Override
    public Resposta create(Resposta resposta) {
        return this.respostaRepository.save(resposta);
    }

    @Override
    public Page<Resposta> findAll(Integer page, Integer size) {
        return this.respostaRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    @Override
    public Resposta update(Long id, Resposta resposta) {
        Resposta updated = findById(id);
        updated.setId(id);
        return this.respostaRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        this.respostaRepository.delete(findById(id));
    }

    @Override
    public Page<Resposta> findByPerguntaId(Long perguntaId, Integer page, Integer size) {
        return this.respostaRepository.findByPerguntaId(perguntaId, Pageable.ofSize(size).withPage(page));
    }

    @Override
    public Resposta findById(Long id){
        return this.respostaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("resposta",id));
    }
}
