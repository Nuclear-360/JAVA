package br.com.nuclear360.dto;

import br.com.nuclear360.model.PerguntaStatus;
import br.com.nuclear360.model.PerguntaTipo;

import java.util.List;

public record PerguntaResponseWithResposta(Long id, String enunciado, PerguntaTipo tipo, PerguntaStatus status,
                                           String nivelDificuldade, String explicacao, Integer valorPontos,
                                           Integer tempoLimite, Integer ordem, List<String> dicas,List<RespostaResponse> respostas) {
}
