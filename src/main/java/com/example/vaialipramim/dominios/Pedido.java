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

    @Column(name = "produtos_ids")
    private String produtosIds;

    @Column(name = "valor_total_compras")
    private Double valorTotalCompras;

    public Pedido() {

    }

    public Pedido(Integer idPedido, LocalDateTime dataHora, Double taxaEntrega,
                  String estabelecimento, String produtosIds,
                  Double valorTotalCompras) {
        this.idPedido = idPedido;
        this.dataHora = dataHora;
        this.taxaEntrega = taxaEntrega;
        this.estabelecimento = estabelecimento;
        this.produtosIds = produtosIds;
        this.valorTotalCompras = valorTotalCompras;
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

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", dataHora=" + dataHora +
                ", taxaEntrega=" + taxaEntrega +
                ", estabelecimento='" + estabelecimento + '\'' +
                ", produtosIds='" + produtosIds + '\'' +
                ", valorTotalCompras=" + valorTotalCompras +
                '}';
    }
}

