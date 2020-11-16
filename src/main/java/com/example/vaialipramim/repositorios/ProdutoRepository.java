package com.example.vaialipramim.repositorios;


import com.example.vaialipramim.dominios.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from PEDIDO_TEM_PRODUTO where id_produto = :id",nativeQuery = true)
    void deleteProduto(@Param("id")int id);

    Produto findByIdProduto(Integer index);
}
