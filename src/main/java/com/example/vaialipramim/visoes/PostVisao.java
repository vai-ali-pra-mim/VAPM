package com.example.vaialipramim.visoes;

import com.example.vaialipramim.dominios.Pedido;
import com.example.vaialipramim.dominios.Post;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PostVisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false)
    private Integer idPost;

    @Length(min = 5)
    @Column(nullable = false)
    private String titulo;

    @Future
    @Column(name = "data_hora_realizacao", nullable = false)
    private LocalDateTime dataHoraDaRealizacao;

    @Length(min = 10)
    @Column(nullable = false)
    private String descricao;

    @Min(0)
    @Column(name = "taxa_entrega",nullable = false)
    private Double taxaDeEntrega;

    @Min(0)
    @Column(name = "limite_quantidade_item",nullable = false)
    private Integer limiteDeQuantidadeItens;

    @Length(min = 10)
    @Column(name = "local_tarefa",nullable = false)
    private String localDaTarefa;

    @Column(name = "tempo_estimado_realizacao",nullable = false)
    private LocalTime tempoEstimadoRealizacao;


    //-----Construtor-----

    public PostVisao() {

    }

    public PostVisao(Integer idPost, @Length(min = 5) String titulo, @Future LocalDateTime dataHoraDaRealizacao, @Length(min = 10) String descricao, @Min(0) Double taxaDeEntrega,
                @Min(0) Integer limiteDeQuantidadeItens, @Length(min = 10) String localDaTarefa, LocalTime tempoEstimadoRealizacao) {
        this.idPost = idPost;
        this.titulo = titulo;
        this.dataHoraDaRealizacao = dataHoraDaRealizacao;
        this.descricao = descricao;
        this.taxaDeEntrega = taxaDeEntrega;
        this.limiteDeQuantidadeItens = limiteDeQuantidadeItens;
        this.localDaTarefa = localDaTarefa;
        this.tempoEstimadoRealizacao = tempoEstimadoRealizacao;

    }

    //-----Getters-----

    public Integer getIdPost() {
        return idPost;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataHoraDaRealizacao() {
        return dataHoraDaRealizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getTaxaDeEntrega() {
        return taxaDeEntrega;
    }

    public Integer getLimiteDeQuantidadeItens() {
        return limiteDeQuantidadeItens;
    }

    public String getLocalDaTarefa() {
        return localDaTarefa;
    }

    public LocalTime getTempoEstimadoRealizacao() {
        return tempoEstimadoRealizacao;
    }

    //-----Setters-----

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDataHoraDaRealizacao(LocalDateTime dataHoraDaRealizacao) {
        this.dataHoraDaRealizacao = dataHoraDaRealizacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTaxaDeEntrega(Double taxaDeEntrega) {
        this.taxaDeEntrega = taxaDeEntrega;
    }

    public void setLimiteDeQuantidadeItens(Integer limiteDeQuantidadeItens) {
        this.limiteDeQuantidadeItens = limiteDeQuantidadeItens;
    }

    public void setLocalDaTarefa(String localDaTarefa) {
        this.localDaTarefa = localDaTarefa;
    }

    public void setTempoEstimadoRealizacao(LocalTime tempoEstimadoRealizacao) {
        this.tempoEstimadoRealizacao = tempoEstimadoRealizacao;
    }

    public void setAll(Post post){
        this.titulo = post.getTitulo();
        this.dataHoraDaRealizacao = post.getDataHoraDaRealizacao();
        this.descricao = post.getDescricao();
        this.taxaDeEntrega = post.getTaxaDeEntrega();
        this.limiteDeQuantidadeItens = post.getLimiteDeQuantidadeItens();
        this.localDaTarefa = post.getLocalDaTarefa();
        this.tempoEstimadoRealizacao = post.getTempoEstimadoRealizacao();
    }
}