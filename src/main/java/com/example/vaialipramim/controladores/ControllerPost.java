package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Post;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.dominios.Usuario;
import com.example.vaialipramim.repositorios.PostRepository;
import com.example.vaialipramim.visoes.PostVisao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin()
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

        if (posts.estaVazio()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok(posts.getVetor());
        }
    }

    // Endpoint para buscar um post especifico
    @GetMapping("/{id}")
    public ResponseEntity getOnePost(@PathVariable @Valid Integer id) {
        Optional<Post> post = repository.findById(id);

        return ResponseEntity.of(post);
    }

    // Endpoint para retornar todos posts de um usuário
    @GetMapping("/usuario/{id}")
    public ResponseEntity retornaPosts(@PathVariable int id) {
        List<Post> posts = repository.findAll();
        List<Post> postsUsr = new ArrayList<>(100);
        if (!posts.isEmpty()) {
            for (int i = 0; i < posts.size(); i++) {
                Integer c = posts.get(i).getUsuarioId();
                if (posts.get(i).getUsuarioId() == id) {
                    postsUsr.add(posts.get(i));
                }
            }
            if (!postsUsr.isEmpty()) {
                return ResponseEntity.ok(postsUsr);
            }
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Endpoint para retornar todos posts de um array de usuarios
    @GetMapping("/usuarios/{arrayIds}")
    public ResponseEntity retornaPosts(@PathVariable String arrayIds) {

        String[] idUsuariosString = arrayIds.split(",");

        List<Integer> idUsuarios = new ArrayList<>();
        for (String id : idUsuariosString)
            idUsuarios.add(Integer.parseInt(id));

        List<Post> posts = repository.findAll();
        List<Post> postsUsr = new ArrayList<>(100);

        if (!posts.isEmpty()) {
            for (int index = 0; index < idUsuarios.size(); index++) {
                for (int i = 0; i < posts.size(); i++) {
                    if (posts.get(i).getUsuarioId() == idUsuarios.get(index)) {
                        postsUsr.add(posts.get(i));
                    }
                }
            }

            if (!postsUsr.isEmpty()) {
                return ResponseEntity.ok(postsUsr);
            }
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Endpoint para retornar todos posts solicitados por um consumidor
    @GetMapping("/consumidor/{id}")
    public ResponseEntity retornaPostsConsumidor(@PathVariable int id) {
        List<PostVisao> posts = repository.findAllSimples();
        List<PostVisao> postsSolicitadosConsumidor = new ArrayList<>();

        for (PostVisao postVisao : posts) {
            if (postVisao.getSolicitanteId() != null && postVisao.getSolicitanteId().equals(id)) {
                postsSolicitadosConsumidor.add(postVisao);
            }
        }

        if (postsSolicitadosConsumidor.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(postsSolicitadosConsumidor);

    }

    // Endpoint para cadastrar Post
    @PostMapping()
    public ResponseEntity cadastrarPost(@RequestBody @Valid Post novoPost) {

        repository.save(novoPost);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    // Endpoint para editar um post especifico
    public ResponseEntity editar(@PathVariable Integer id, @RequestBody @Valid Post novoPost) {
        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            Post postProcurado = repository.getOne(id);
            postProcurado.setAll(novoPost);
            repository.save(postProcurado);
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/status/{id}")
    // Endpoint para mudar status de entegue de um post especifico
    public ResponseEntity editarStatusEntregue(@PathVariable Integer id) {
        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            Post postProcurado = post.get();
            Integer statusAtual;
            if (postProcurado.getFoiEntregue() == 0)
                statusAtual = 1;
            else
                statusAtual = 0;

            postProcurado.setFoiEntregue(statusAtual);
            repository.save(postProcurado);
        }

        return ResponseEntity.of(post);
    }

    @PutMapping("/status/{id}/{solicitanteId}")
    // Endpoint para mudar status de entegue de um post especifico
    public ResponseEntity editarStatusAceito(@PathVariable Integer id,@PathVariable Integer solicitanteId) {
        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            Post postProcurado = post.get();
            Integer statusAtual;
            if (postProcurado.getFoiAceito() == 0)
                statusAtual = 1;
            else
                statusAtual = 0;

            postProcurado.setFoiAceito(statusAtual);
            postProcurado.setSolicitanteId(solicitanteId);
            repository.save(postProcurado);
        }

        return ResponseEntity.of(post);
    }


    //Endpoint para excluir um post
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {

        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}