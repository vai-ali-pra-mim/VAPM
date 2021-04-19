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

import java.util.List;
import java.util.Optional;

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

        if (produtos.estaVazio()) {
            return ResponseEntity.noContent().build();
        } else {
            return ok(produtos.getVetor());
        }
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
}
