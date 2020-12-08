package com.example.vaialipramim.repositorios;

import com.example.vaialipramim.dominios.Post;
import com.example.vaialipramim.visoes.PostVisao;
import com.example.vaialipramim.visoes.UsuarioVisao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select new com.example.vaialipramim.visoes.PostVisao(c.idPost, c.titulo, c.dataHoraRealizacao," +
            " c.descricao, c.taxaEntrega, c.limiteQuantidadeItens,c.limitePesoEntrega,  c.localTarefa," +
            " c.tempoEstimadoRealizacao, c.foiEntregue, c.foiAceito, c.usuarioId, c.solicitanteId) from Post c")
    List<PostVisao> findAllSimples();


}
