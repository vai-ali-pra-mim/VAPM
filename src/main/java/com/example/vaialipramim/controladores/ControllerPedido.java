package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Pedido;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.repositorios.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class ControllerPedido {

    @Autowired
    private PedidoRepository repository;

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
        Optional<Pedido> numPedido = repository.findById(id);
        if (numPedido.isPresent()) {
            return ResponseEntity.ok(numPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity criarPedido(@RequestBody @Valid Pedido novoPedido) {
        double valorTotalCompras = 0;
        for (Produto produto: novoPedido.getProdutosDoPedido()){
            valorTotalCompras += produto.getValor();
        }
        novoPedido.setValorTotalCompras(valorTotalCompras);
        repository.save(novoPedido);
        return ResponseEntity.ok().build();
    }
}
