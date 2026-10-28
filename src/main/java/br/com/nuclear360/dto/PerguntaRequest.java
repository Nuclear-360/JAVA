package br.com.nuclear360.dto;

import br.com.nuclear360.model.PerguntaTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Schema(description = "Dados para criar Perguntas")
public record PerguntaRequest(
        @Schema(hidden = true)
        Long id,

        @Schema(description = "enunciado da pergunta",
                example = "",
                required = true)
        @NotNull(message = "enunciado é obrigatorio")
        @NotBlank(message = "enunciado não pode ser vazio")
        String enunciado,

        @NotNull(message = "tipo é obrigatorio")
        PerguntaTipo tipo,

        @NotBlank(message = "nivel de dificuldade não pode ser vazio")
        String nivelDificuldade,

        @NotNull(message = "explicacao é obrigatoria")
        @NotBlank(message = "explicacao não pode ser vazio")
        String explicacao,

        @Positive(message = "valor dos pontos tem que ser positivo")
        @NotNull(message = "valor dos pontos é obrigatorio")
        Integer valorPontos,

        @Positive(message = "tempo limite tem que ser positivo")
        //muliplo de 30
        @NotNull(message = "tempo limite é obrigatorio")
        Integer tempoLimite,

        @NotNull(message = "dicas são obrigatorias")
        List<String> dicas,

        @Positive(message = "ordem deve ser positivo")
        @NotNull(message = "ordem é obrigatorio")
        Integer ordem
) {
}
