package com.example.vaialipramim.dominios;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity()
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false)
    private int idProduto;

    @Length(min = 2, max = 65)
    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @Length(min = 2, max = 25)
    @Column(name = "tipo_produto", nullable = false)
    private String tipoProduto;

    @Min(0)
    @Column( nullable = false)
    private double valor;

    @Length(min = 2, max = 25)
    @Column( nullable = false)
    private String marca;

    public Produto() {

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

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public void setAll(Produto novoProduto) {
        setNomeProduto(novoProduto.getNomeProduto());
        setTipoProduto(novoProduto.getTipoProduto());
        setValor(novoProduto.getValor());
        setMarca(novoProduto.getMarca());
    }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", tipoProduto='" + tipoProduto + '\'' +
                ", valor=" + valor +
                ", marca='" + marca + '\'' +
                '}';
    }
}
