package br.com.nuclear360.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para criar Resposta")
public record RespostaRequest(
        @Schema(hidden = true)
        Long id,
        @NotNull(message = "o conteudo é obrigatorio")
        @NotBlank(message = "o conteudo não pode ser vazio")
        String conteudo,
        @NotNull(message = "a resposta certa é obrigatorio")
        boolean respostaCerta,
        @NotNull(message = "explicação é obrigatoria")
        @NotBlank(message = "explicação não pde ser vazia")
        String explicacao) {
}
