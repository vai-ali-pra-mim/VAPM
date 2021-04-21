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

    @Min(0)
    @Column(name = "limite_peso_entrega",nullable = false)
    private double limitePesoEntrega;

    @Length(min = 4)
    @Column(name = "local_tarefa",nullable = false)
    private String localTarefa;

    @Column(name = "tempo_estimado_realizacao",nullable = false)
    private LocalTime tempoEstimadoRealizacao;

    @Column(name = "foi_entregue")
    private Integer foiEntregue;

    @Column(name = "entregador_id")
    private Integer entregadorId;


    //-----Construtor-----

    public Post() {

    }

    public Post(@Length(min = 5) String titulo,String dataHoraRealizacao, @Length(min = 10) String descricao,
                 @Min(0) Double taxaEntrega, @Min(1) Integer limiteQuantidadeItens, @Min(0) double limitePesoEntrega,
                 @Length(min = 5) String localTarefa, String tempoEstimadoRealizacao)
                  {
        this.titulo = titulo;
        this.dataHoraRealizacao = LocalDateTime.parse(dataHoraRealizacao);
        this.descricao = descricao;
        this.taxaEntrega = taxaEntrega;
        this.limiteQuantidadeItens = limiteQuantidadeItens;
        this.limitePesoEntrega = limitePesoEntrega;
        this.localTarefa = localTarefa;
        this.tempoEstimadoRealizacao = LocalTime.parse(tempoEstimadoRealizacao);
        this.foiEntregue = 0;
        this.entregadorId = null;
    }

    //-----Getters-----


    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataHoraRealizacao() {
        return dataHoraRealizacao;
    }

    public void setDataHoraRealizacao(LocalDateTime dataHoraRealizacao) {
        this.dataHoraRealizacao = dataHoraRealizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(Double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public Integer getLimiteQuantidadeItens() {
        return limiteQuantidadeItens;
    }

    public void setLimiteQuantidadeItens(Integer limiteQuantidadeItens) {
        this.limiteQuantidadeItens = limiteQuantidadeItens;
    }

    public double getLimitePesoEntrega() {
        return limitePesoEntrega;
    }

    public void setLimitePesoEntrega(double limitePesoEntrega) {
        this.limitePesoEntrega = limitePesoEntrega;
    }

    public String getLocalTarefa() {
        return localTarefa;
    }

    public void setLocalTarefa(String localTarefa) {
        this.localTarefa = localTarefa;
    }

    public LocalTime getTempoEstimadoRealizacao() {
        return tempoEstimadoRealizacao;
    }

    public void setTempoEstimadoRealizacao(LocalTime tempoEstimadoRealizacao) {
        this.tempoEstimadoRealizacao = tempoEstimadoRealizacao;
    }

    public Integer getFoiEntregue() {
        return foiEntregue;
    }

    public void setFoiEntregue(Integer foiEntregue) {
        this.foiEntregue = foiEntregue;
    }

    public Integer getEntregadorId() {
        return entregadorId;
    }

    public void setEntregadorId(Integer entregadorId) {
        this.entregadorId = entregadorId;
    }

    public void setAll(Post post){
        this.titulo = post.getTitulo();
        this.dataHoraRealizacao = post.getDataHoraRealizacao();
        this.descricao = post.getDescricao();
        this.taxaEntrega = post.getTaxaEntrega();
        this.limiteQuantidadeItens = post.getLimiteQuantidadeItens();
        this.localTarefa = post.getLocalTarefa();
        this.tempoEstimadoRealizacao = post.getTempoEstimadoRealizacao();
        this.foiEntregue = post.getFoiEntregue();
        this.entregadorId = post.getEntregadorId();
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost=" + idPost +
                ", titulo='" + titulo + '\'' +
                ", dataHoraRealizacao=" + dataHoraRealizacao +
                ", descricao='" + descricao + '\'' +
                ", taxaEntrega=" + taxaEntrega +
                ", limiteQuantidadeItens=" + limiteQuantidadeItens +
                ", limitePesoEntrega=" + limitePesoEntrega +
                ", localTarefa='" + localTarefa + '\'' +
                ", tempoEstimadoRealizacao=" + tempoEstimadoRealizacao +
                ", foiEntregue=" + foiEntregue +
                ", usuarioId=" + entregadorId +
                '}';
    }

    //-----Fim da classe-----
}
