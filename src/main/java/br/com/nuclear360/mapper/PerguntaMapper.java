package br.com.nuclear360.mapper;

import br.com.nuclear360.dto.PerguntaResponse;
import br.com.nuclear360.model.Pergunta;

public final class PerguntaMapper {

    private PerguntaMapper(){}

    public static PerguntaResponse toResponse(Pergunta pergunta){
        return new PerguntaResponse(pergunta.getId(), pergunta.getEnunciado(),pergunta.getTipo(),
                pergunta.getStatus(), pergunta.getNivelDificuldade(), pergunta.getExplicacao(), pergunta.getValorPontos(),
                pergunta.getTempoLimite(), pergunta.getOrdem(), pergunta.getDicas());
    }
}
