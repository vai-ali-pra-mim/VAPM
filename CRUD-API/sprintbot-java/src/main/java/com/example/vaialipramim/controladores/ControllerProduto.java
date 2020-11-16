package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ControllerProduto {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity getTodos() {
        Adapter adapter =  new Adapter<Produto>(repository.findAll());
        ListaObjetos produtos =  adapter.getListaObjetos();

        if (produtos.estaVazio()) {
            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.ok(produtos.getVetor());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getUm(@PathVariable int id) {
        Optional<Produto> produto = repository.findById(id);

        return ResponseEntity.of(produto);
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid Produto novoProduto) {
        repository.save(novoProduto);

        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editar(@PathVariable int id, @RequestBody @Valid Produto novoProduto) {
        boolean existsProduto = repository.existsById(id);
        if(existsProduto){
            var produtos = repository.findAll();

            Produto produto = new Produto();
            for (var index = 0; index < produtos.size(); index++){
                if(produtos.get(index).getIdProduto() == id){
                    produto = produtos.get(index);
                    break;
                }
            }

            produto.setNomeProduto(novoProduto.getNomeProduto());
            produto.setValor(novoProduto.getValor());
            produto.setMarca(novoProduto.getMarca());
            produto.setTipoProduto(novoProduto.getTipoProduto());

            repository.save(produto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id) {

        Optional<Produto> produto = repository.findById(id);
        if(produto.isPresent()){
            repository.deleteProduto(id);
            repository.deleteById(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.notFound().build();
    }
}
