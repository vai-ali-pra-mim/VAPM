package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.CalcularDistancia;
import com.example.vaialipramim.Utils.Coordenadas;
import com.example.vaialipramim.dominios.Usuario;
import com.example.vaialipramim.repositorios.UsuarioRepository;
import com.example.vaialipramim.visoes.UsuarioVisao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
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
        var usuarios = repository.findAllSimples();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok().body(usuarios);
        }
    }

    @GetMapping("/entregadores/{posicaoSolicitante}")
    public ResponseEntity getEntregadores(@PathVariable String posicaoSolicitante) {
        List<UsuarioVisao> usuarios = repository.findAllSimples();
        String[] stringPosicaoSolicitante = posicaoSolicitante.split(",");
        Coordenadas coordinadasSolicitante = new Coordenadas(Double.parseDouble(stringPosicaoSolicitante[0]), Double.parseDouble(stringPosicaoSolicitante[1]));

        if (usuarios.isEmpty()) {

            return ResponseEntity.noContent().build();
        } else {
            List<UsuarioVisao> usuariosDentroDoRaioDistancia = new ArrayList<>();

            for (UsuarioVisao usuario : usuarios) {

                String[] stringCoordenadasEntregador = usuario.getCoordenadas().split(", ");
                Coordenadas CoordenadasEntregador = new Coordenadas(Double.parseDouble(stringCoordenadasEntregador[0]), Double.parseDouble(stringCoordenadasEntregador[1]));

                if ( CalcularDistancia.distanciaEmKMEntreCoordenadas(coordinadasSolicitante,CoordenadasEntregador) < 0.500)
                    usuariosDentroDoRaioDistancia.add(usuario);
            }

            if (usuariosDentroDoRaioDistancia.isEmpty())
                return ResponseEntity.noContent().build();
            else
                return ResponseEntity.ok().body(usuariosDentroDoRaioDistancia);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getId(@PathVariable int id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(repository.findByIdUsuarioVisao(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Usuario usuario) {
        var todos = repository.findAllSimples();
        for (UsuarioVisao usuarioVisao : todos) {
            if (usuarioVisao.getEmail().equals(usuario.getEmail()) && usuarioVisao.getSenha().equals(usuario.getSenha())) {
                return ResponseEntity.ok(usuarioVisao);
            }
        }
        return ResponseEntity.notFound().build();
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
        repository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity alteraSenha(@PathVariable int id, @RequestBody @Valid Usuario novaSenha) {
        boolean existsUsuario = repository.existsById(id);
        if (existsUsuario) {
            var usuarios = repository.findAll();

            Usuario usuario = new Usuario();
            for (var index = 0; index < usuarios.size(); index++) {
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
            var usuarios = repository.findAll();

            Usuario usuario = new Usuario();
            for (var index = 0; index < usuarios.size(); index++) {
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
            var usuarios = repository.findAll();

            Usuario usuario = new Usuario();
            for (var index = 0; index < usuarios.size(); index++) {
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





}


