package com.example.vaialipramim.visoes;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

public class ProdutoVisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false)
    private int idProduto;

    @Length(min = 2, max = 25)
    @Column(name = "nome_produto")
    private String nomeProduto;

    @Length(min = 2, max = 25)
    @Column(name = "tipo_produto")
    private String tipoProduto;

    @Min(0)
    private double valor;

    @Length(min = 2, max = 25)
    private String marca;

    public ProdutoVisao(int idProduto, @Length(min = 2, max = 25) String nomeProduto, @Length(min = 2, max = 25) String tipoProduto, @Min(0) double valor, @Length(min = 2, max = 25) String marca) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.tipoProduto = tipoProduto;
        this.valor = valor;
        this.marca = marca;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
