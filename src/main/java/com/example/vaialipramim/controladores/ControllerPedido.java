package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.FilaObj;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.Utils.PilhaObj;
import com.example.vaialipramim.dominios.Pedido;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.repositorios.PedidoRepository;
import com.example.vaialipramim.repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class ControllerPedido {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping()
    public ResponseEntity getTodos() {
        Adapter adapter = new Adapter<Produto>(repository.findAll());
        ListaObjetos produtos = adapter.getListaObjetos();

        if (produtos.estaVazio()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(produtos.getVetor());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable int id) {
        Optional<Pedido> pedido = repository.findById(id);

        return ResponseEntity.of(pedido);

    }

    @PostMapping()
    public ResponseEntity criarPedido(@RequestBody Pedido novoPedido) {
        double valorTotalCompras = 0;
        List<Produto> produtos = new ArrayList<>(20);

        String[] produtosIds =novoPedido.getProdutosIds().split(",");

        for (String id : produtosIds) {
            Produto produto = produtoRepository.findByIdProduto(Integer.parseInt(id));
            valorTotalCompras += produto.getValor();
            produtos.add(produto);
        }

        novoPedido.setValorTotalCompras(valorTotalCompras);
        repository.save(novoPedido);
        return ResponseEntity.ok(produtos);
    }
}
