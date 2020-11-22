package com.example.vaialipramim.dominios;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToMany
    @JoinTable(
            name = "pedido_tem_produto",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private Set<Produto> produtosDoPedido;

    @Column(name = "valor_total_compras")
    private Double valorTotalCompras;



    public Pedido() {

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

    public Set<Produto> getProdutosDoPedido() {
        return produtosDoPedido;
    }

    public void setProdutosDoPedido(Set<Produto> produtosDoPedido) {
        this.produtosDoPedido = produtosDoPedido;
    }

    public Double getValorTotalCompras() {
        return valorTotalCompras;
    }

    public void setValorTotalCompras(Double valorTotalEntrega) {
        this.valorTotalCompras = valorTotalEntrega;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", dataHora='" + dataHora + '\'' +
                ", taxaEntrega=" + taxaEntrega +
                ", estabelecimento='" + estabelecimento + '\'' +
                ", produtosDoPedido=" + produtosDoPedido +
                ", valorTotalEntrega=" + valorTotalCompras +
                '}';
    }
}

