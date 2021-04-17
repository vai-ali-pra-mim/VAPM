package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.GravarArquivo;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/nomes")
    public ResponseEntity getTodosNomes() {

        List<Produto> produtos = repository.findAll();

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<String> nomesProdutos = new ArrayList<String>();

            for (int index =0; index < produtos.size(); index++){
                nomesProdutos.add(produtos.get(index).getNomeProduto());
            }

            return ResponseEntity.ok(nomesProdutos);
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
            List<Produto> produtos = repository.findAll();

            Produto produto = new Produto();
            for (int index = 0; index < produtos.size(); index++){
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

    @GetMapping("/download")
    public ResponseEntity criarArquivoTxt(HttpServletResponse response) throws IOException {
        Adapter<Produto> adapter = new Adapter<Produto>(repository.findAll());
        ListaObjetos<Produto> produtos = adapter.getListaObjetos();

        if (produtos.estaVazio()) {
            return ResponseEntity.noContent().build();
        } else {
            GravarArquivo<Produto> gravaArquivo = new GravarArquivo<>();

            for (int index = 0; index < produtos.getNroElem(); index++) {
                gravaArquivo.gravaRegistro("src/main/resources/static/produtos.csv", produtos.getElemento(index));
            }

            gravaArquivo.gravaRegistro("src/main/resources/static/produtos.csv");

            File file = new File("src/main/resources/static/produtos.csv");
            response.setHeader("Content-Disposition", String.format("attachment; filename=produtos.csv"));

            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            }
            return ResponseEntity.ok().build();
        }
    }
}
