package com.example.vaialipramim.dominios;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class Post {

    //-----Atributos-----
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false)
    private Integer idPost;

    @Length(min = 5)
    @Column(nullable = false)
    private String titulo;

    @Future
    @Column(name = "data_hora_realizacao", nullable = false)
    private LocalDateTime dataHoraRealizacao;

    @Length(min = 10)
    @Column(nullable = false)
    private String descricao;

    @Min(0)
    @Column(name = "taxa_entrega",nullable = false)
    private Double taxaEntrega;

    @Min(0)
    @Column(name = "limite_quantidade_item",nullable = false)
    private Integer limiteQuantidadeItens;

    @Length(min = 10)
    @Column(name = "local_tarefa",nullable = false)
    private String localTarefa;

    @Column(name = "tempo_estimado_realizacao",nullable = false)
    private LocalTime tempoEstimadoRealizacao;

    @ManyToOne
    private Pedido pedido;

    //-----Construtor-----

    public Post() {

    }


    //-----Getters-----

    public Integer getIdPost() {
        return idPost;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataHoraDaRealizacao() {
        return dataHoraRealizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getTaxaDeEntrega() {
        return taxaEntrega;
    }

    public Integer getLimiteDeQuantidadeItens() {
        return limiteQuantidadeItens;
    }

    public String getLocalDaTarefa() {
        return localTarefa;
    }

    public LocalTime getTempoEstimadoRealizacao() {
        return tempoEstimadoRealizacao;
    }

    public Pedido getPedido() {
        return pedido;
    }
    //-----Setters-----

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDataHoraDaRealizacao(LocalDateTime dataHoraDaRealizacao) {
        this.dataHoraRealizacao = dataHoraDaRealizacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTaxaDeEntrega(Double taxaDeEntrega) {
        this.taxaEntrega = taxaDeEntrega;
    }

    public void setLimiteDeQuantidadeItens(Integer limiteDeQuantidadeItens) {
        this.limiteQuantidadeItens = limiteDeQuantidadeItens;
    }

    public void setLocalDaTarefa(String localDaTarefa) {
        this.localTarefa = localDaTarefa;
    }

    public void setTempoEstimadoRealizacao(LocalTime tempoEstimadoRealizacao) {
        this.tempoEstimadoRealizacao = tempoEstimadoRealizacao;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setAll(Post post){
        this.titulo = post.getTitulo();
        this.dataHoraRealizacao = post.getDataHoraDaRealizacao();
        this.descricao = post.getDescricao();
        this.taxaEntrega = post.getTaxaDeEntrega();
        this.limiteQuantidadeItens = post.getLimiteDeQuantidadeItens();
        this.localTarefa = post.getLocalDaTarefa();
        this.tempoEstimadoRealizacao = post.getTempoEstimadoRealizacao();
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost='" + idPost + '\'' +
                ", titulo='" + titulo + '\'' +
                ", dataHoraDaRealizacao=" + dataHoraRealizacao +
                ", descricao='" + descricao + '\'' +
                ", taxaDeEntrega=" + taxaEntrega +
                ", limiteDeQuantidadeItens=" + limiteQuantidadeItens +
                ", localDaTarefa='" + localTarefa + '\'' +
                ", tempoEstimadoRealizacao=" + tempoEstimadoRealizacao +
                '}';
    }


//-----Fim da classe-----
}
