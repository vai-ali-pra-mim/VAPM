package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.FilaObj;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.Utils.PilhaObj;
import com.example.vaialipramim.dominios.Pedido;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.dominios.ProdutoQuantidade;
import com.example.vaialipramim.repositorios.PedidoRepository;
import com.example.vaialipramim.repositorios.ProdutoQuantidadeRepository;
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
            return ResponseEntity.ok(produtos.getVetor());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable int id) {
        Optional<Pedido> pedido = repository.findById(id);
        return ResponseEntity.of(pedido);
    }

    public void inserirProdutosQuantidades(String getProdutosIdsEQuantidades, Integer IdPedido){
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
        System.out.println(novoPedido);
        List<Produto> produtos = new ArrayList<>();

        inserirProdutosQuantidades(novoPedido.getProdutosIds(), novoPedido.getIdPedido());
        String[] produtosIdsEQuantidades =novoPedido.getProdutosIds().split(";");

        repository.save(novoPedido);
        return ResponseEntity.ok(produtos);
    }
}
