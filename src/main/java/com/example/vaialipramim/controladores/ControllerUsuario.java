package com.example.vaialipramim.controladores;

import com.example.vaialipramim.dominios.Usuario;
import com.example.vaialipramim.repositorios.UsuarioRepository;
import com.example.vaialipramim.servicos.GravarUsuarioEmArquivoServico;
import com.example.vaialipramim.servicos.RealizarMatchingEntreUsuariosServico;
import com.example.vaialipramim.visoes.UsuarioLoginVisao;
import com.example.vaialipramim.visoes.UsuarioVisao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/usuarios")
public class ControllerUsuario {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity getTodos() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok().body(usuarios);
        }
    }

    @GetMapping("/visao")
    public ResponseEntity getTodosVisao() {
        List<UsuarioVisao> usuarios = repository.findAllSimples();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok().body(usuarios);
        }

    }

    @GetMapping("/entregadores/{posicaoSolicitante}")
    public ResponseEntity getEntregadores(@PathVariable String posicaoSolicitante) {
        RealizarMatchingEntreUsuariosServico realizarMatching = new RealizarMatchingEntreUsuariosServico(repository, posicaoSolicitante);
        return  realizarMatching.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable int id) {
        Optional<Usuario> usuario = repository.findById(id);
       return ResponseEntity.of(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Usuario usuario) {
        UsuarioLoginVisao usuarioEncontrado = repository.findByEmailESenha(usuario.getEmail(), usuario.getSenha());

        if(usuarioEncontrado == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuarioEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteId(@PathVariable int id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity criaUsuario(@RequestBody @Valid Usuario usuario) {
        try{
            repository.save(usuario);
            return ResponseEntity.ok().build();
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity alteraSenha(@PathVariable int id, @RequestBody @Valid Usuario novaSenha) {
        boolean existsUsuario = repository.existsById(id);
        if (existsUsuario) {
            List<Usuario> usuarios = repository.findAll();

            Usuario usuario = new Usuario();
            for (int index = 0; index < usuarios.size(); index++) {
                if (usuarios.get(index).getIdUsuario() == id) {
                    usuario = usuarios.get(index);
                    break;
                }
            }

            usuario.setSenha(novaSenha.getSenha());
            repository.save(usuario);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/endereco")
    public ResponseEntity alteraEndereco(@PathVariable int id, @RequestBody @Valid Usuario novoEndereco) {
        boolean existsUsuario = repository.existsById(id);
        if (existsUsuario) {
            List<Usuario> usuarios = repository.findAll();

            Usuario usuario = new Usuario();
            for (int index = 0; index < usuarios.size(); index++) {
                if (usuarios.get(index).getIdUsuario() == id) {
                    usuario = usuarios.get(index);
                    break;
                }
            }

            usuario.setCEP(novoEndereco.getCEP());
            usuario.setComplemento(novoEndereco.getComplemento());
            repository.save(usuario);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/telefone")
    public ResponseEntity alteraTelefone(@PathVariable int id, @RequestBody @Valid Usuario novoTelefone) {
        boolean existsUsuario = repository.existsById(id);
        if (existsUsuario) {
            List<Usuario> usuarios = repository.findAll();

            Usuario usuario = new Usuario();
            for (int index = 0; index < usuarios.size(); index++) {
                if (usuarios.get(index).getIdUsuario() == id) {
                    usuario = usuarios.get(index);
                    break;
                }
            }

            usuario.setTelefone(novoTelefone.getTelefone());
            repository.save(usuario);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/download")
    public ResponseEntity getTodos(HttpServletResponse response) throws IOException {
        GravarUsuarioEmArquivoServico gravarUsuarioEmArquivoServico = new GravarUsuarioEmArquivoServico(repository, response);

        return ResponseEntity.ok(gravarUsuarioEmArquivoServico.execute());
    }

    @GetMapping("/saldo/{id}")
    public ResponseEntity getSaldo(@PathVariable int id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()){
            List<Usuario> todos = repository.findAll();
            for (Usuario usuarioCadastrado: todos){
                if(usuarioCadastrado.getIdUsuario().equals(id)){
                    return ResponseEntity.ok(usuarioCadastrado.getSaldo());
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{id}/depositar/{valor}")
    public ResponseEntity depositarCredito(@PathVariable int id, @PathVariable  Double valor){

         Optional<Usuario> usuario = repository.findById(1);
         if(usuario.isPresent()){
             List<Usuario> todos = repository.findAll();
             for (Usuario usuarioAtual: todos){
                 if(usuarioAtual.getIdUsuario() == id){
                     usuarioAtual.depositarSaldo(valor);
                     repository.save(usuarioAtual);
                     return  ResponseEntity.ok(usuarioAtual.getSaldo());
                 }
             }
         }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{id}/sacar/{valor}")
    public ResponseEntity sacarCredito(@PathVariable int id, @PathVariable Double valor){

        Optional<Usuario> usuario = repository.findById(1);
        if(usuario.isPresent()){
            List<Usuario> todos = repository.findAll();
            for (Usuario usuarioAtual: todos){
                if(usuarioAtual.getIdUsuario() == id){
                    usuarioAtual.sacarSaldo(valor);
                    repository.save(usuarioAtual);
                    return  ResponseEntity.ok(usuarioAtual.getSaldo());
                }
            }
        }

        return ResponseEntity.notFound().build();
    }
}