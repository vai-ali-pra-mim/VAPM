package com.example.vaialipramim.dominios;

import javax.persistence.*;

@Entity
public class ProdutoQuantidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto_quantidade", nullable = false)
    private Integer idProdutoQuantidade;

    @Column(name = "produto_id", nullable = false)
    private Integer produtoId;

    @Column(name = "quantidade_produto",nullable = false)
    private Integer quantidadeProduto;

    @Column(name = "pedido_id",nullable = false)
    private Integer pedido_id;


    public ProdutoQuantidade(Integer produtoId, Integer quantidadeProduto, Integer pedido_id) {
        this.produtoId = produtoId;
        this.quantidadeProduto = quantidadeProduto;
        this.pedido_id = pedido_id;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Integer getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Integer pedido_id) {
        this.pedido_id = pedido_id;
    }

    @Override
    public String toString() {
        return "ProdutoQuantidade{" +
                "idProdutoQuantidade=" + idProdutoQuantidade +
                ", produtoId=" + produtoId +
                ", quantidadeProduto=" + quantidadeProduto +
                ", pedido_id=" + pedido_id +
                '}';
    }
}
