package com.example.vaialipramim.repositorios;

import com.example.vaialipramim.dominios.Pedido;
import com.example.vaialipramim.dominios.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("select new com.example.vaialipramim.dominios.Pedido(c.idPedido, c.dataHora, c.taxaEntrega, c.estabelecimento, c.produtosIds,c.valorTotalCompras,c.postId,c.solicitanteId ) from Pedido c where solicitante_id = :id")
    List<Pedido> findBySolicitanteId(@Param("id")int id);





}
