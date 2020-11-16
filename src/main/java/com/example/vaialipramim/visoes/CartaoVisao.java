package com.example.vaialipramim.visoes;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CartaoVisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao", nullable = false)
    private Integer idCartao;

    @Length(min = 3, max = 60)
    @Column(name = "nome_titular")
    private String nomeTitular;

    @Length(min = 16, max = 16)
    @Column(name = "numero_cartao")
    private String numeroCartao;

    @Column(name = "tipo_cartao")
    private String tipo;

    @Column
    private String bandeira;

    @Length(min = 3, max = 4)
    @Column
    private String CVV;

    @Length(min = 11, max = 11)
    @Column
    private String CPF;

    @Length(min = 5, max = 5)
    @Column(name = "data_validade")
    private String dataValidade;

    public CartaoVisao(Integer idCartao, @Length(min = 3, max = 60) String nomeTitular, @Length(min = 16, max = 16) String numeroCartao, String tipo,
                       String bandeira, @Length(min = 3, max = 4) String CVV, @Length(min = 11, max = 11) String CPF, @Length(min = 5, max = 5) String dataValidade) {
        this.idCartao = idCartao;
        this.nomeTitular = nomeTitular;
        this.numeroCartao = numeroCartao;
        this.tipo = tipo;
        this.bandeira = bandeira;
        this.CVV = CVV;
        this.CPF = CPF;
        this.dataValidade = dataValidade;
    }

    public Integer getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Integer idCartao) {
        this.idCartao = idCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return "CartaoVisao{" +
                "idCartao=" + idCartao +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", bandeira='" + bandeira + '\'' +
                ", CVV='" + CVV + '\'' +
                ", CPF='" + CPF + '\'' +
                ", dataValidade='" + dataValidade + '\'' +
                '}';
    }
}
