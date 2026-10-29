package br.com.nuclear360.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RespostaResponse(
        @Schema(description = "Id da pergunta",
                example = "1")
        Long id,

        @Schema(description = "O id interno da Pergunta",
                example = "1")
        Long perguntaId,

        @Schema(description = "O conteudo da resposta",
                example = "São Paulo")
        String conteudo,

        @Schema(description = "Se é a resposta correta ou não",
                example = "true")
        boolean respostaCerta,

        @Schema(description = "Explica o porque a resposta esta certa ou errada",
                example = "Porque a cidade de São Paulo deu origem ao nome do estado, e não o contrário.")
        String explicacao) {
}
