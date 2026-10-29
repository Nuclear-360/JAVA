package br.com.nuclear360.mapper;

import br.com.nuclear360.dto.RespostaResponse;
import br.com.nuclear360.model.Resposta;

public final class RespostaMapper {

    private RespostaMapper(){}

    public static RespostaResponse toResponse(Resposta resposta){
        return new RespostaResponse(resposta.getId(), resposta.getPergunta().getId(), resposta.getConteudo(),
                resposta.isRespostaCerta(), resposta.getExplicacao());
    }
}
