package br.com.nuclear360.dto;

public record RespostaResponse(Long id,Long perguntaId,String conteudo,boolean respostaCerta,String explicacao) {
}
