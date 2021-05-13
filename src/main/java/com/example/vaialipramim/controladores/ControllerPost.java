package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Pedido;
import com.example.vaialipramim.dominios.Post;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.repositorios.PedidoRepository;
import com.example.vaialipramim.repositorios.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/posts")

public class ControllerPost {

    @Autowired
    private PostRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    // Endpoint pra retornar todos os posts
    @GetMapping()
    public ResponseEntity getPosts() {
        Adapter adapter = new Adapter<Produto>(repository.findAll());
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
                Integer c = posts.get(i).getEntregadorId();
                if (posts.get(i).getEntregadorId() == id) {
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
                    if (posts.get(i).getEntregadorId() == idUsuarios.get(index)) {
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

        List<Pedido> pedidos = pedidoRepository.findBySolicitanteId(id);
        List<Post> postSolicitadosConsumidor = new ArrayList<>();

        for (Pedido pedido : pedidos) {
           Optional<Post> post = repository.findById(pedido.getPostId());
           if(post.isPresent())
               postSolicitadosConsumidor.add(post.get());
        }

        if (postSolicitadosConsumidor.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(postSolicitadosConsumidor);
    }

 //   @GetMapping("status/{id}")
//    public ResponseEntity statusEspera(@PathVariable Integer id) {
//        Optional<Post> post = repository.findById(id);
//        if (post.isPresent()) {
//            if (post.get().getEstaEmEspera().equals(1))
//                return ResponseEntity.ok().build();
//            else
//                return ResponseEntity.status(406).build();
//
//        }
//        return ResponseEntity.notFound().build();
   // }

    // Endpoint para cadastrar Post
    @PostMapping()
    public ResponseEntity cadastrarPost(@RequestBody @Valid Post novoPost) {
       try{
           System.out.println(novoPost);
           repository.save(novoPost);

           return ResponseEntity.ok().build();
       }
       catch(Exception ex){
           System.out.println(ex);
            return ResponseEntity.status(500).build();
        }

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

    @PutMapping("/status-entregue/{id}/{cliente}")
    // Endpoint para mudar status de entregue de um post especifico
    public ResponseEntity editarStatusEntregue(@PathVariable Integer id, @PathVariable String cliente) {
        Optional<Post> post = repository.findById(id);
        if (post.isPresent()) {
            Post postProcurado = post.get();
            Integer statusAtual;
            if (postProcurado.getFoiEntregue() == 0 && cliente.equals("consumidor"))
                statusAtual = 1;
            else if (postProcurado.getFoiEntregue() == 1 && cliente.equals("entregador"))
                statusAtual = 0;
            else
                return ResponseEntity.badRequest().build();

            postProcurado.setFoiEntregue(statusAtual);
            repository.save(postProcurado);
            return ResponseEntity.ok(post.get().getFoiEntregue());
        }

        return ResponseEntity.of(post);
    }

//    @PutMapping("/status-espera/{id}/{cliente}")
//    // Endpoint para mudar status de espera de um post especifico
//    public ResponseEntity editarStatusEspera(@PathVariable Integer id, @PathVariable String cliente) {
//        Optional<Post> post = repository.findById(id);
//
//        if (post.isPresent()) {
//            Post postProcurado = post.get();
//            Integer statusAtual;
//            if (postProcurado.getEstaEmEspera() == 0 && cliente.equals("consumidor"))
//                statusAtual = 1;
//            else if (postProcurado.getEstaEmEspera() == 1 && cliente.equals("entregador"))
//                statusAtual = 0;
//            else
//                return ResponseEntity.badRequest().build();
//
//            postProcurado.setEstaEmEspera(statusAtual);
//            repository.save(postProcurado);
//            return ResponseEntity.ok(post.get().getEstaEmEspera());
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @PutMapping("/status-aceite/{id}/{solicitanteId}/{cliente}")
//    // Endpoint para mudar status de aceite de um post especifico
//    public ResponseEntity editarStatusAceito(@PathVariable Integer id, @PathVariable Integer solicitanteId,
//                                             @PathVariable String cliente) {
//        Optional<Post> post = repository.findById(id);
//        if (post.isPresent()) {
//            Post postProcurado = post.get();
//            Integer statusAtual;
//            if (postProcurado.getFoiAceito() == 0 && cliente.equals("consumidor"))
//                statusAtual = 1;
//            else if (postProcurado.getFoiAceito() == 1 && cliente.equals("entregador"))
//                statusAtual = 0;
//            else
//                return ResponseEntity.badRequest().build();
//
//            postProcurado.setFoiAceito(statusAtual);
//            if (solicitanteId.equals(9999))
//                postProcurado.setSolicitanteId(null);
//            else
//                postProcurado.setSolicitanteId(solicitanteId);
//            repository.save(postProcurado);
//            return ResponseEntity.ok(post.get().getFoiAceito());
//        }
//
//        return ResponseEntity.of(post);
//    }

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