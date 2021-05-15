package com.example.vaialipramim.dominios;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "taxa_entrega", nullable = false)
    private Double taxaEntrega;

    @Column(name = "nome_estabelecimento",nullable = false)
    private String estabelecimento;

    private String produtosIds;

    @Column(name = "valor_total_compras")
    private Double valorTotalCompras;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "foi_entregue")
    private Integer foiEntregue;

    @Column(name = "foi_aceito")
    private Integer foiAceito;

    @Column(name = "solicitante_id",nullable = false)
    private Integer solicitanteId;

    public Pedido() {

    }

    public Pedido(Integer idPedido, LocalDateTime dataHora, Double taxaEntrega,
                  String estabelecimento, String produtosIds, Integer solicitanteId,
                  Double valorTotalCompras) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
        this.taxaEntrega = taxaEntrega;
        this.estabelecimento = estabelecimento;
        this.produtosIds = produtosIds;
        this.valorTotalCompras = valorTotalCompras;
        this.solicitanteId = solicitanteId;
    }


    public Pedido(Integer idPedido, LocalDateTime dataHora, Double taxaEntrega,
                  String estabelecimento, String produtosIds, Double valorTotalCompras,
                  Integer postId, Integer foiEntregue, Integer foiAceito, Integer solicitanteId) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
        this.taxaEntrega = taxaEntrega;
        this.estabelecimento = estabelecimento;
        this.produtosIds = produtosIds;
        this.valorTotalCompras = valorTotalCompras;
        this.postId = postId;
        this.foiEntregue = foiEntregue;
        this.foiAceito = foiAceito;
        this.solicitanteId = solicitanteId;
    }

    public Pedido(Integer idPedido, LocalDateTime dataHora, Double taxaEntrega, String estabelecimento,
                  String produtosIds, Double valorTotalCompras, Integer postId, Integer solicitanteId) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
        this.taxaEntrega = taxaEntrega;
        this.estabelecimento = estabelecimento;
        this.produtosIds = produtosIds;
        this.valorTotalCompras = valorTotalCompras;
        this.postId = postId;
        this.solicitanteId = solicitanteId;
    }

    public Pedido(Integer postId) {
        this.postId = postId;
    }

    public Pedido(LocalDateTime dataHora, Double taxaEntrega,
                  String estabelecimento, String produtosIds, Integer postId,
                  Integer solicitanteId
                  ) {
        this.dataHora = dataHora;
        this.taxaEntrega = taxaEntrega;
        this.estabelecimento = estabelecimento;
        this.produtosIds = produtosIds;
        this.postId = postId;
        this.solicitanteId = solicitanteId;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(Double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getProdutosIds() {
        return produtosIds;
    }

    public void setProdutosIds(String produtosIds) {
        this.produtosIds = produtosIds;
    }

    public Double getValorTotalCompras() {
        return valorTotalCompras;
    }

    public void setValorTotalCompras(Double valorTotalCompras) {
        this.valorTotalCompras = valorTotalCompras;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Integer solicitanteId) {
        this.solicitanteId = solicitanteId;
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


    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", dataHora=" + dataHora +
                ", taxaEntrega=" + taxaEntrega +
                ", estabelecimento='" + estabelecimento + '\'' +
                ", produtosIds='" + produtosIds + '\'' +
                ", valorTotalCompras=" + valorTotalCompras +
                ", postId=" + postId +
                ", foiEntregue=" + foiEntregue +
                ", foiAceito=" + foiAceito +
                ", solicitanteId=" + solicitanteId +
                '}';
    }
}

