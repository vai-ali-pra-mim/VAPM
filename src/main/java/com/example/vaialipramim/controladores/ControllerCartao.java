package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.Adapter;
import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Cartao;
import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.repositorios.CartaoRepository;
import com.example.vaialipramim.visoes.CartaoVisao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class ControllerCartao {

    @Autowired
    private CartaoRepository repository;

    @GetMapping
    public ResponseEntity getTodos(){
        Adapter adapter = new Adapter<Produto>(repository.findAllSimples());
        ListaObjetos cartoes = adapter.getListaObjetos();

        if(cartoes.estaVazio()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(cartoes.getVetor());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable int id){
        Optional<CartaoVisao> cartao = Optional.ofNullable(repository.findByIdCartaoVisao(id));
        if (cartao.isPresent()){
            return ResponseEntity.ok(cartao);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteId(@PathVariable int id){
        Optional<Cartao> cartao = repository.findById(id);
        if (cartao.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity criaCartao(@RequestBody @Valid Cartao cartao){
        repository.save(cartao);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity alteraCartao(@PathVariable int id, @RequestBody @Valid Cartao novoCartao){
        boolean existsCartao = repository.existsById(id);
        if (existsCartao){
            List<Cartao> cartoes = repository.findAll();

            Cartao cartao = new Cartao();
            for (int index = 0; index < cartoes.size(); index++){
                if(cartoes.get(index).getIdCartao() == id){
                    cartao = cartoes.get(index);
                    break;
                }
            }

            cartao.setNomeTitular(novoCartao.getNomeTitular());
            cartao.setBandeira(novoCartao.getBandeira());
            cartao.setCPF(novoCartao.getCPF());
            cartao.setNumeroCartao(novoCartao.getNumeroCartao());
            cartao.setCVV(novoCartao.getCVV());
            cartao.setDataValidade(novoCartao.getDataValidade());
            cartao.setTipo(novoCartao.getTipo());

            repository.save(cartao);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
