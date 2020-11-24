package com.example.vaialipramim.visoes;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class UsuarioLoginVisao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Length(min = 3, max = 60)
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Length(min = 11, max = 11)
    @Column()
    private String CPF;

    @Past
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column()
    private String complemento;

    @Length(min = 11, max = 11)
    @Column()
    private String telefone;

    @Column()
    @Length(min = 8, max = 9)
    private String CEP;

    @Column()
    private String email;

    @Column()
    private String senha;

    @Column(name = "ponto_referencia")
    private String pontoReferencia;

    @Column()
    private String coordenadas;

    @Column()
    private String RG;

    @Column()
    private Double saldo;

    public UsuarioLoginVisao(Integer idUsuario, @Length(min = 3, max = 60) String nomeCompleto, @Length(min = 11, max = 11) String CPF, @Past LocalDate dataNascimento,
                             String complemento, @Length(min = 11, max = 11) String telefone, @Length(min = 8, max = 9) String CEP, String email, String senha,
                             String coordenadas, String RG, Double saldo) {
        this.idUsuario = idUsuario;
        this.nomeCompleto = nomeCompleto;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.complemento = complemento;
        this.telefone = telefone;
        this.CEP = CEP;
        this.email = email;
        this.senha = senha;
        this.coordenadas = coordenadas;
        this.RG = RG;
        this.saldo = saldo;
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

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
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
}
