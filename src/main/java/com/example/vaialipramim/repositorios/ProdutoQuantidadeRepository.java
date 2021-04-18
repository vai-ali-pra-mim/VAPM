package com.example.vaialipramim.repositorios;

import com.example.vaialipramim.dominios.Cartao;
import com.example.vaialipramim.dominios.ProdutoQuantidade;
import com.example.vaialipramim.visoes.CartaoVisao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoQuantidadeRepository extends JpaRepository<ProdutoQuantidade, Integer> {

   // @Query("select new com.example.vaialipramim.visoes.CartaoVisao(c.idCartao, c.nomeTitular, c.numeroCartao, c.tipo, c.bandeira, c.CVV, c.CPF, c.dataValidade) from Cartao c")
   // List<CartaoVisao> findAllSimples();

   // @Query("select new com.example.vaialipramim.visoes.CartaoVisao(c.idCartao, c.nomeTitular, c.numeroCartao, c.tipo, c.bandeira, c.CVV, c.CPF, c.dataValidade) from Cartao c where id_cartao = :id")
    //CartaoVisao findByIdCartaoVisao(@Param("id")int id);
}
