package com.example.vaialipramim.servicos;

import com.example.vaialipramim.Utils.CalcularDistancia;
import com.example.vaialipramim.Utils.Coordenadas;
import com.example.vaialipramim.dominios.Usuario;
import com.example.vaialipramim.repositorios.UsuarioRepository;
import com.example.vaialipramim.visoes.UsuarioVisao;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class RealizarMatchingEntreUsuariosServico {

    UsuarioRepository repository;
    String posicaoSolicitante;

    public RealizarMatchingEntreUsuariosServico(UsuarioRepository repository, String posicaoSolicitante) {
        this.repository = repository;
        this.posicaoSolicitante = posicaoSolicitante;
    }

    public ResponseEntity execute() {
        List<UsuarioVisao> usuarios = repository.findAllSimples();
        String[] stringPosicaoSolicitante = posicaoSolicitante.split(",");
        Coordenadas coordenadasSolicitante = new Coordenadas(Double.parseDouble(stringPosicaoSolicitante[0]), Double.parseDouble(stringPosicaoSolicitante[1]));

        if (usuarios.isEmpty()) {

            return ResponseEntity.noContent().build();
        } else {
            List<UsuarioVisao> usuariosDentroDoRaioDistancia = new ArrayList<>();

            for (UsuarioVisao usuario : usuarios) {

                String[] stringCoordenadasEntregador = usuario.getCoordenadas().split(", ");
                Coordenadas CoordenadasEntregador = new Coordenadas(Double.parseDouble(stringCoordenadasEntregador[0]), Double.parseDouble(stringCoordenadasEntregador[1]));
                var c = CalcularDistancia.distanciaEmKMEntreCoordenadas(coordenadasSolicitante, CoordenadasEntregador);
                if (c <= 0.500 && c != 0.0)
                    usuariosDentroDoRaioDistancia.add(usuario);
            }

            if (usuariosDentroDoRaioDistancia.isEmpty())
                return ResponseEntity.noContent().build();
            else
                return ResponseEntity.ok().body(usuariosDentroDoRaioDistancia);
        }
    }

}
