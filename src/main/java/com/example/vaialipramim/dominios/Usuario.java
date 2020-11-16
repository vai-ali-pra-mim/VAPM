package com.example.vaialipramim.dominios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Set;
import java.util.UUID;
import java.time.LocalDate;

@Entity
public class Usuario {

    //-----Atributos Usuários-----
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario",nullable = false)
    private Integer idUsuario;

    @Length(min = 3, max = 60)
    @Column(name = "nome_completo",nullable = false)
    private String nomeCompleto;

    @Length(min = 11, max = 11)
    @Column(nullable = false)
    private String CPF;

    @Past
    @Column(name = "data_nascimento",nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    @Length(min = 8, max = 9)
    private String CEP;

    @Length(min = 7, max = 65)
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;

    @Length(min = 11, max = 11)
    @Column(nullable = false)
    private String telefone;

    @Column()
    private String coordenadas;

    @Column()
    private String RG;

    @Column()
    private Double saldo;

    @ManyToOne()
    private Cartao cartao;

    @ManyToOne
    private Post post;

    //-----Construtor para auxiliar nos cadastros na classe "controller"-----

    public Usuario() {

    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}