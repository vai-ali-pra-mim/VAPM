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

@Entity
public class PostVisao {

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

    @Column(name = "foi_aceito")
    private Integer foiAceito;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "solicitante_id")
    private Integer solicitanteId;

    @ManyToOne
    private Pedido pedido;

    //-----Construtor-----

    public PostVisao() {

    }

    public PostVisao(Integer idPost, @Length(min = 5) String titulo, LocalDateTime dataHoraRealizacao, @Length(min = 10) String descricao,
                     @Min(0) Double taxaEntrega, @Min(0) Integer limiteQuantidadeItens, @Min(0) double limitePesoEntrega,
                     @Length(min = 4) String localTarefa, LocalTime tempoEstimadoRealizacao, Integer foiEntregue,
                     Integer foiAceito, Integer usuarioId, Integer solicitanteId
                     ) {
        this.idPost = idPost;
        this.titulo = titulo;
        this.dataHoraRealizacao = dataHoraRealizacao;
        this.descricao = descricao;
        this.taxaEntrega = taxaEntrega;
        this.limiteQuantidadeItens = limiteQuantidadeItens;
        this.limitePesoEntrega = limitePesoEntrega;
        this.localTarefa = localTarefa;
        this.tempoEstimadoRealizacao = tempoEstimadoRealizacao;
        this.foiEntregue = foiEntregue;
        this.foiAceito = foiAceito;
        this.usuarioId = usuarioId;
        this.solicitanteId = solicitanteId;
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

    public Integer getFoiEntregue() {
        return foiEntregue;
    }

    public void setFoiEntregue(Integer foiEntregue) {
        this.foiEntregue = foiEntregue;
    }

    public Integer getFoiAceito() {
        return foiAceito;
    }

    public void setFoiAceito(Integer foiAceito) {
        this.foiAceito = foiAceito;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalTime getTempoEstimadoRealizacao() {
        return tempoEstimadoRealizacao;
    }

    public void setTempoEstimadoRealizacao(LocalTime tempoEstimadoRealizacao) {
        this.tempoEstimadoRealizacao = tempoEstimadoRealizacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Integer solicitanteId) {
        this.solicitanteId = solicitanteId;
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
                ", foiAceito=" + foiAceito +
                ", usuarioId=" + usuarioId +
                ", solicitanteId=" + solicitanteId +
                ", pedido=" + pedido +
                '}';
    }

//-----Fim da classe-----
}