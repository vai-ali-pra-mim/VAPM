package com.example.vaialipramim.repositorios;

import com.example.vaialipramim.dominios.ProdutoQuantidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoQuantidadeRepository extends JpaRepository<ProdutoQuantidade, Integer> {


}
