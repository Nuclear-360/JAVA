package br.com.nuclear360.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enunciado;
    private PerguntaTipo tipo;
    private PerguntaStatus status;
    private String nivelDificuldade;
    private String explicacao;
    private Integer valorPontos;
    private Integer tempoLimite;

    @OneToMany(mappedBy = "pergunta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta>  respostas;
    private List<String> dicas;
    private Integer ordem;
    private boolean ativo;

    public void setStatus(PerguntaStatus status) {
        if(this.status == null && status != PerguntaStatus.EM_ANALISE) {
            //lançar erro
        }
        else if((this.status != PerguntaStatus.EM_ANALISE && status == PerguntaStatus.EM_ANALISE)
                || (this.status == PerguntaStatus.INDISPONIVEL && status == PerguntaStatus.DISPONIVEL)
        ||(this.status == PerguntaStatus.EM_ANALISE && status == PerguntaStatus.EM_REVISAO)){
            //lançar erro
        }
        else{
            this.status = status;
        }
    }
}