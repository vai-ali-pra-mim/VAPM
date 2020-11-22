package com.example.vaialipramim.repositorios;

import com.example.vaialipramim.Utils.ListaObjetos;
import com.example.vaialipramim.dominios.Usuario;
import com.example.vaialipramim.visoes.UsuarioLoginVisao;
import com.example.vaialipramim.visoes.UsuarioVisao;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("select new com.example.vaialipramim.visoes.UsuarioVisao(c.idUsuario, c.nomeCompleto, c.CPF, c.dataNascimento, c.complemento, c.telefone, c.CEP, c.email, c.senha, c.coordenadas, c.RG, c.saldo, c.post) from Usuario c")
    List<UsuarioVisao> findAllSimples();

    @Query("select new com.example.vaialipramim.visoes.UsuarioVisao(c.idUsuario, c.nomeCompleto, c.CPF, c.dataNascimento, c.complemento, c.telefone, c.CEP, c.email, c.senha, c.coordenadas, c.RG, c.saldo, c.post) from Usuario c where id_usuario = :id")
    UsuarioVisao findByIdUsuarioVisao(@Param("id") int id);

    @Query("select new com.example.vaialipramim.visoes.UsuarioLoginVisao(c.idUsuario, c.nomeCompleto, c.CPF, c.dataNascimento, c.complemento, c.telefone, c.CEP, c.email, c.senha, c.coordenadas, c.RG, c.saldo) from Usuario c where email = :email and senha = :senha")
    UsuarioLoginVisao findByEmailESenha(@Param("email") String email, @Param("senha") String senha);
}
