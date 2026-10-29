package br.com.nuclear360.dto;

import br.com.nuclear360.model.PerguntaNivelDificuldade;
import br.com.nuclear360.model.PerguntaStatus;
import br.com.nuclear360.model.PerguntaTipo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PerguntaResponseWithResposta(
        @Schema(description = "O id interno da Pergunta",
                example = "1")
        Long id,

        @Schema(description = "Enunciado da pergunta",
                example = "Qual é a capital de São Paulo?")
        String enunciado,

        @Schema(description = "Tipo da pergunta",
                example = "MULTIPLA_ESCOLHA",
                enumAsRef = true)
        PerguntaTipo tipo,


        @Schema(description = "O status atual da pergunta",
                example = "EM_ANALISE",
                defaultValue = "EM_ANALISE",
                enumAsRef = true)
        PerguntaStatus status,

        @Schema(description = "O nivel de dificuldade que a pergunta tera",
                example = "FACIL",
                required = true,
                enumAsRef = true)
        PerguntaNivelDificuldade nivelDificuldade,

        @Schema(description = "O valor dos pontos ganhos ao responder a pergunta corretamente",
                example = "1000")
        Integer valorPontos,

        @Schema(description = "O tempo que o usuario tem para responder em segundos",
                multipleOf = 30,
                example = "180")
        Integer tempoLimite,

        @Schema(description = "A ordem que a pergunta ira aparecer",
                example = "1")
        Integer ordem,

        @Schema(description = "As dicas que o sistema ira dar ao usuario quando ele estiver preso",
                example = "[O nome é similar ao do estado]")
        List<String> dicas,

        @Schema(description = "As repostas para a pergunta",
                example = "[São Paulo,Bahia,Rio de Janeiro]")
        List<RespostaResponse> respostas) {
}
