package com.example.vaialipramim.repositorios;

import com.example.vaialipramim.dominios.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
