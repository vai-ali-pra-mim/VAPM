package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Pedido;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.dominios.ProdutoQuantidade;
import com.example.vaialipramim.repositorios.PedidoRepository;
import com.example.vaialipramim.repositorios.ProdutoQuantidadeRepository;
import com.example.vaialipramim.repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/pedidos")
public class ControllerPedido {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ProdutoQuantidadeRepository produtoQuantidadeRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping()
    public ResponseEntity getTodos() {
        Adapter adapter = new Adapter<Produto>(repository.findAll());
        ListaObjetos produtos = adapter.getListaObjetos();

        if (produtos.estaVazio())
            return ResponseEntity.noContent().build();

        return ok(produtos.getVetor());
    }

    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable int id) {
        Optional<Pedido> pedido = repository.findById(id);

        return ResponseEntity.of(pedido);
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity getPedidosPorUsuarioId(@PathVariable int id) {
        List<Pedido> pedidos;
        pedidos = repository.findBySolicitanteId(id);

        if (pedidos.isEmpty())
            return ResponseEntity.noContent().build();

        return ok(pedidos);
    }

    //Trazer todos os pedidos solicitados em um post
    @GetMapping("posts/{idPost}")
    public ResponseEntity getPedidosPorPostId(@PathVariable int idPost) {
        List<Pedido> pedidos;
        pedidos = repository.findPedidosByPostId(idPost);
        System.out.println("qtd todos pedidos :" + pedidos.size());
        if (pedidos.isEmpty())
            return ResponseEntity.noContent().build();

        return ok(pedidos);
    }

    //Trazer todos os pedidos solicitados em aberto em um post
    @GetMapping("posts/{idPost}/abertos")
    public ResponseEntity getPedidosAbertosPorPostId(@PathVariable int idPost) {
        List<Pedido> pedidos;
        pedidos = repository.findPedidosAbertosByPostId(idPost);

        System.out.println("qtd pedidos abertos: " + pedidos.size());
        if (pedidos.isEmpty())
            return ResponseEntity.noContent().build();

        return ok(pedidos);
    }

    //Trazer todos os pedidos solicitados de um post com status aceitos
    @GetMapping("posts/{idPost}/aceitos")
    public ResponseEntity getPedidosAceitosPorPostId(@PathVariable int idPost) {
        List<Pedido> pedidos;
        pedidos = repository.findPedidosAceitosByPostId(idPost);

        System.out.println("qtd pedidos aceitos: " + pedidos.size());
        if (pedidos.isEmpty())
            return ResponseEntity.noContent().build();

        return ok(pedidos);
    }

    //Trazer todos os pedidos solicitados de um post com status aceitos
    @GetMapping("posts/{idPost}/recusados")
    public ResponseEntity getPedidosRecusadosPorPostId(@PathVariable int idPost) {
        List<Pedido> pedidos;
        pedidos = repository.findPedidosRecusadosByPostId(idPost);

        System.out.println("qtd pedidos recusados: " + pedidos.size());
        if (pedidos.isEmpty())
            return ResponseEntity.noContent().build();

        return ok(pedidos);
    }


    public void inserirProdutosQuantidades(String getProdutosIdsEQuantidades, Integer IdPedido) {
        String[] produtosIdsEQuantidades = getProdutosIdsEQuantidades.split(";");

        for (String produtoIdEQuantidade : produtosIdsEQuantidades) {
            String[] produtoIdEQuantidadeString = produtoIdEQuantidade.split("-");

            ProdutoQuantidade produtoQuantidade = new ProdutoQuantidade(Integer.parseInt(produtoIdEQuantidadeString[0]),
                    Integer.parseInt(produtoIdEQuantidadeString[1]), IdPedido);

            produtoQuantidadeRepository.save(produtoQuantidade);
        }
    }

    @PostMapping()
    public ResponseEntity criarPedido(@RequestBody Pedido novoPedido) {
        Pedido pedido = new Pedido();

        novoPedido.setIdPedido(pedido.getIdPedido());
        repository.save(novoPedido);
        inserirProdutosQuantidades(novoPedido.getProdutosIds(), novoPedido.getIdPedido());

        return ResponseEntity.created(null).build();
    }

    @PatchMapping("{idPost}/aceitar")
    public ResponseEntity aceitarPedido(@PathVariable int idPost) {
        try {
            Optional<Pedido> pedido = repository.findById(idPost);
            pedido.get().setFoiAceito(1);
            repository.save(pedido.get());

            return ok().build();
        } catch (Exception exception) {
            System.out.println(exception);

            return ok().build();
        }
    }

    @PatchMapping("{idPost}/rejeitar")
    public ResponseEntity rejeitarPedido(@PathVariable int idPost) {
        Optional<Pedido> pedido = repository.findById(idPost);
        pedido.get().setFoiAceito(0);
        repository.save(pedido.get());

        return ok().build();
    }
}