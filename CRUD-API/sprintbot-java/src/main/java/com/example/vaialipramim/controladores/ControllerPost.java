package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Post;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.repositorios.PostRepository;
import com.example.vaialipramim.visoes.PostVisao;
import com.example.vaialipramim.visoes.UsuarioVisao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")

public class ControllerPost {

    @Autowired
    private PostRepository repository;

    // Endpoint pra retornar todos os posts
    @GetMapping()
    public ResponseEntity getPosts() {
        Adapter adapter = new Adapter<Produto>(repository.findAllSimples());
        ListaObjetos posts = adapter.getListaObjetos();

        if(posts.estaVazio()){
            return ResponseEntity.status(204).build();
        }
        else {
            return ResponseEntity.ok(posts.getVetor());
        }
    }

    // Endpoint para buscar um post especifico
    @GetMapping("/{id}")
    public ResponseEntity getOnePost(@PathVariable @Valid Integer id) {
        Optional<Post> post = repository.findById(id);
        return ResponseEntity.of(post);

    }

    // Endpoint para cadastrar Post
    @PostMapping()
    public ResponseEntity cadastrarPost(@RequestBody @Valid Post novoPost) {

        repository.save(novoPost);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editar(@PathVariable Integer id, @RequestBody @Valid Post novoPost){
        Optional<Post> post = repository.findById(id);
        if(post.isPresent()){
            Post postProcurado = repository.getOne(id);
            postProcurado.setAll(novoPost);
            repository.save(postProcurado);
        }

        return ResponseEntity.ok().build();
    }

    //Endpoint para excluir um post
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {

        Optional<Post> post = repository.findById(id);
        if(post.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
