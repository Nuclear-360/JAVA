package br.com.nuclear360.controller;

import br.com.nuclear360.dto.*;
import br.com.nuclear360.mapper.PerguntaMapper;
import br.com.nuclear360.mapper.RespostaMapper;
import br.com.nuclear360.model.Pergunta;
import br.com.nuclear360.model.PerguntaStatus;
import br.com.nuclear360.model.PerguntaTipo;
import br.com.nuclear360.model.Resposta;
import br.com.nuclear360.service.PerguntaService;
import br.com.nuclear360.service.RespostaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/pergunta")
@Validated
@Tag(name = "Pergunta", description = "Endpoints para gerenciar Perguntas")
public class PerguntaController {

    private final PerguntaService perguntaService;
    private final RespostaService respostaService;

    public PerguntaController(PerguntaService perguntaService, RespostaService respostaService){
        this.perguntaService = perguntaService;
        this.respostaService = respostaService;
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Pergunta criada com sucesso")
    public ResponseEntity<PerguntaResponse> createPergunta(@Valid @RequestBody PerguntaRequest request){
        Pergunta pergunta = new Pergunta();
        pergunta.setEnunciado(request.enunciado());
        pergunta.setTipo(request.tipo());
        pergunta.setNivelDificuldade(request.nivelDificuldade());
        pergunta.setValorPontos(request.valorPontos());
        pergunta.setTempoLimite(request.tempoLimite());
        pergunta.setDicas(request.dicas());
        pergunta.setOrdem(request.ordem());
        pergunta.setStatus(PerguntaStatus.EM_ANALISE);
        System.out.println(pergunta.getStatus());
        Pergunta salvo = this.perguntaService.create(pergunta);
        return ResponseEntity.created(URI.create("/api/pergunta/" + salvo.getId()))
                .body(PerguntaMapper.toResponse(salvo));
    }

    @GetMapping
    @ApiResponse(responseCode = "200",description = "Pagina de Pergunta")
    @Parameter(name = "status", description = "faz uma busca por status de pergunta")
    @Parameter(name = "tipo", description = "faz uma busca por tipo de pergunta")
    @Parameter(name = "page", description = "define a pagina para melhor visualização das perguntas")
    @Parameter(name = "sizePage", description = "define a quantidade de perguntas por pagina")
    public PageResponse<PerguntaResponse> findAllPergunta(@RequestParam(required = false) PerguntaStatus status,
                                                           @RequestParam(required = false) PerguntaTipo tipo,
                                                           @RequestParam(required = false,defaultValue = "0") Integer page,
                                                           @RequestParam(required = false,defaultValue = "10") Integer sizePage){

        Page<Pergunta> pageResult = status != null
                ? this.perguntaService.findByStatus(status,page,sizePage)
                : tipo != null
                ? this.perguntaService.findByTipo(tipo, page , sizePage)
                : this.perguntaService.findAll(page, sizePage);
        return PageResponse.from(pageResult.map(PerguntaMapper::toResponse));
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Pergunta atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PerguntaResponse.class)))
    @ApiResponse(responseCode = "404", description = "Pergunta não encontrada",
            content = @Content)
    public ResponseEntity<PerguntaResponse> updatePergunta(@PathVariable @Positive Long id,
            @Valid @RequestBody PerguntaRequest request){
        Pergunta pergunta = this.perguntaService.findById(id);
        pergunta.setEnunciado(request.enunciado());
        pergunta.setTipo(request.tipo());
        pergunta.setNivelDificuldade(request.nivelDificuldade());
        pergunta.setValorPontos(request.valorPontos());
        pergunta.setTempoLimite(request.tempoLimite());
        pergunta.setDicas(request.dicas());
        pergunta.setOrdem(request.ordem());

        Pergunta updated = this.perguntaService.update(id,pergunta);
        return ResponseEntity.ok(PerguntaMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Deleta uma Pergunta baseado no id enviado pela url")
    @ApiResponse(responseCode = "404", description = "Pergunta com o id não encontra")
    @Parameter(name = "id", description = "O id para deletar uma Pergunta")
    public ResponseEntity<Void> deletePergunta(@PathVariable @Positive Long id){
        this.perguntaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PerguntaResponse.class)))
    @ApiResponse(responseCode = "404", description = "Pergunta com o id não encontra",
                content = @Content)
    public ResponseEntity<PerguntaResponse> findByIdPergunta(@PathVariable @Positive Long id){
        Pergunta pergunta = this.perguntaService.findById(id);
        return ResponseEntity.ok(PerguntaMapper.toResponse(pergunta));
    }

    @GetMapping("/{perguntaId}/with-resposta")
    @ApiResponse(responseCode = "200", description = "Mostra uma pergunta com todas as resposta ligadas a ela",
                 content = @Content(mediaType = "application/json",
                         schema = @Schema(implementation = PerguntaResponseWithResposta.class)))
    @ApiResponse(responseCode = "404", description = "Pergunta com o id não encontra",
                 content = @Content)
    public ResponseEntity<PerguntaResponseWithResposta> findWithRespostaById(@PathVariable @Positive Long perguntaId){
        return ResponseEntity.ok(PerguntaMapper.toResponseWithRespostas(this.perguntaService.findWithRespostaById(perguntaId)));
    }

    @PostMapping("/{perguntaId}/resposta")
    @ApiResponse(responseCode = "201", description = "Resposta criada com sucesso")
    @ApiResponse(responseCode = "404", description = "Pergunta não encontrada", content = @Content)
    public ResponseEntity<RespostaResponse> createResposta(@PathVariable @Positive Long perguntaId,
                                                           @RequestBody RespostaRequest respostaRequest){
        Resposta resposta = new Resposta();

        resposta.setConteudo(respostaRequest.conteudo());
        resposta.setExplicacao(respostaRequest.explicacao());
        resposta.setRespostaCerta(respostaRequest.respostaCerta());
        resposta.setPergunta(this.perguntaService.findById(perguntaId));

        Resposta created = this.respostaService.create(resposta);
        return ResponseEntity.created(URI.create("/api/resposta/"+ created.getId()))
                .body(RespostaMapper.toResponse(created));
    }
}