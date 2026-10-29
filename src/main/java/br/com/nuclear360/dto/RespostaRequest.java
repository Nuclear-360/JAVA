package br.com.nuclear360.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para criar Resposta")
public record RespostaRequest(
        @Schema(hidden = true)
        Long id,

        @Schema(description = "O conteudo da resposta",
                example = "São Paulo",
                required = true)
        @NotNull(message = "o conteudo é obrigatorio")
        @NotBlank(message = "o conteudo não pode ser vazio")
        String conteudo,

        @Schema(description = "Se é a resposta correta ou não",
                example = "true",
                required = true)
        @NotNull(message = "a resposta certa é obrigatorio")
        boolean respostaCerta,

        @Schema(description = "Explica o porque a resposta esta certa ou errada",
                example = "Porque a cidade de São Paulo deu origem ao nome do estado, e não o contrário.",
                required = true)
        @NotNull(message = "explicação é obrigatoria")
        @NotBlank(message = "explicação não pde ser vazia")
        String explicacao) {
}
