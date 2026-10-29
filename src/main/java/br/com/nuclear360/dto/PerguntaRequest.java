package br.com.nuclear360.dto;

import br.com.nuclear360.model.PerguntaNivelDificuldade;
import br.com.nuclear360.model.PerguntaTipo;
import br.com.nuclear360.validation.MultipleOf;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Schema(description = "Dados para criar Perguntas")
public record PerguntaRequest(
        @Schema(hidden = true)
        Long id,

        @Schema(description = "Enunciado da pergunta",
                example = "Qual é a capital de São Paulo?",
                required = true)
        @NotNull(message = "enunciado é obrigatorio")
        @NotBlank(message = "enunciado não pode ser vazio")
        String enunciado,

        @Schema(description = "Tipo da pergunta",
                example = "MULTIPLA_ESCOLHA",
                required = true,
                enumAsRef = true)
        @NotNull(message = "tipo é obrigatorio")
        PerguntaTipo tipo,

        @Schema(description = "O nivel de dificuldade que a pergunta tera",
                example = "FACIL",
                required = true,
                enumAsRef = true)
        @NotNull(message = "nivel de dificuldade é obrigatorio")
        PerguntaNivelDificuldade nivelDificuldade,

        @Schema(description = "O valor dos pontos ganhos ao responder a pergunta corretamente",
                example = "1000",
                required = true)
        @Positive(message = "valor dos pontos tem que ser positivo")
        @NotNull(message = "valor dos pontos é obrigatorio")
        Integer valorPontos,

        @Schema(description = "O tempo que o usuario tem para responder em segundos",
                multipleOf = 30,
                example = "180",
                required = true)
        @Positive(message = "tempo limite tem que ser positivo")
        @MultipleOf(value = 30)
        @NotNull(message = "tempo limite é obrigatorio")
        Integer tempoLimite,

        @Schema(description = "As dicas que o sistema ira dar ao usuario quando ele estiver preso",
                example = "O nome é similar ao do estado",
                required = true)
        @NotNull(message = "dicas são obrigatorias")
        List<String> dicas,

        @Schema(description = "A ordem que a pergunta ira aparecer",
                example = "1",
                required = true)
        @Positive(message = "ordem deve ser positivo")
        @NotNull(message = "ordem é obrigatorio")
        Integer ordem
) {
}
