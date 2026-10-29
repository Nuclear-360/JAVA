package br.com.nuclear360.controller;

import br.com.nuclear360.dto.PageResponse;
import br.com.nuclear360.dto.PerguntaResponse;
import br.com.nuclear360.dto.RespostaRequest;
import br.com.nuclear360.dto.RespostaResponse;
import br.com.nuclear360.mapper.RespostaMapper;
import br.com.nuclear360.model.Resposta;
import br.com.nuclear360.service.RespostaService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resposta")
@Validated
@Tag(name = "Resposta", description = "Endpoints para gerenciar respostas")
public class RespostaController {

    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService){
        this.respostaService = respostaService;
    }

    @GetMapping
    public PageResponse<RespostaResponse> getAll(@RequestParam(required = false) Long perguntaId,
                                                 @RequestParam(required = false, defaultValue = "0") Integer page,
                                                 @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        Page<Resposta> pageResult = perguntaId != null
                ? this.respostaService.findByPerguntaId(perguntaId,page,pageSize)
                : this.respostaService.findAll(page,pageSize);
        return PageResponse.from(pageResult.map(RespostaMapper::toResponse));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Deleta uma Resposta baseado no id enviado pela url")
    @ApiResponse(responseCode = "404", description = "Resposta com o id não encontra ")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id){
        this.respostaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Resposta atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespostaResponse.class)))
    @ApiResponse(responseCode = "404", description = "Resposta não encontrada",
            content = @Content)
    public ResponseEntity<RespostaResponse> updateResposta(@PathVariable @Positive Long id,
                                                           @RequestBody RespostaRequest respostaRequest){
        Resposta resposta = new Resposta();

        resposta.setConteudo(respostaRequest.conteudo());
        resposta.setExplicacao(respostaRequest.explicacao());
        resposta.setRespostaCerta(respostaRequest.respostaCerta());

        Resposta updated = this.respostaService.update(id,resposta);
        return ResponseEntity.ok(RespostaMapper.toResponse(updated));
    }
}
