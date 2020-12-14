package com.example.vaialipramim.controladores;

import com.example.vaialipramim.Utils.FilaObj;
import com.example.vaialipramim.Utils.PilhaObj;
import com.example.vaialipramim.dominios.Cartao;
import com.example.vaialipramim.dominios.Usuario;
import com.example.vaialipramim.repositorios.CartaoRepository;
import com.example.vaialipramim.repositorios.UsuarioRepository;
import com.example.vaialipramim.servicos.GravarUsuarioEmArquivoServico;
import com.example.vaialipramim.servicos.RealizarMatchingEntreUsuariosServico;
import com.example.vaialipramim.visoes.UsuarioLoginVisao;
import com.example.vaialipramim.visoes.UsuarioVisao;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@EnableScheduling()
@CrossOrigin()
@RestController
@RequestMapping("/usuarios")
public class ControllerUsuario {

    private FilaObj<Usuario> usuariosFilaObj = new FilaObj<>(100);

    @Scheduled(fixedRate = 1500)
    public void inserirNoBanco(){
        if(!usuariosFilaObj.isEmpty()){
            Usuario usuario = usuariosFilaObj.poll();
            repository.save(usuario);
            System.out.println(usuario + "Inserido");
        }
    }

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private CartaoRepository cartaoRepository;

    //Traz todos os usuarios do banco
    @GetMapping
    public ResponseEntity getTodos() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok().body(usuarios);
        }
    }
    //Traz uma versão resumida de todos os usuarios do banco
    @GetMapping("/visao")
    public ResponseEntity getTodosVisao() {
        List<UsuarioVisao> usuarios = repository.findAllSimples();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok().body(usuarios);
        }
    }

    //Traz do banco todos os entregadores proximos a posicao do solicitante
    @GetMapping("/entregadores/{posicaoSolicitante}")
    public ResponseEntity getEntregadores(@PathVariable String posicaoSolicitante) {
        RealizarMatchingEntreUsuariosServico realizarMatching = new RealizarMatchingEntreUsuariosServico(repository, posicaoSolicitante);
        return realizarMatching.execute();
    }

    @GetMapping("/{id}")
    //Traz do banco um usuario especifico
    public ResponseEntity getId(@PathVariable int id) {
        Optional<Usuario> usuario = repository.findById(id);
        return ResponseEntity.of(usuario);
    }

    @GetMapping("/download")
    //Faz download de um arquivo txt contendo todos os usuarios do banco
    public ResponseEntity getTodos(HttpServletResponse response) throws IOException {
        GravarUsuarioEmArquivoServico gravarUsuarioEmArquivoServico = new GravarUsuarioEmArquivoServico(repository, response);

        return ResponseEntity.ok(gravarUsuarioEmArquivoServico.execute());
    }

    @PostMapping("/login")
    //Traz do banco um usuario que tenha o email e senha passado no corpo da requisicao
    public ResponseEntity login(@RequestBody Usuario usuario) {
        UsuarioVisao usuarioEncontrado = repository.findByEmailESenha(usuario.getEmail(), usuario.getSenha());

        if (usuarioEncontrado == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuarioEncontrado);
    }

    @PostMapping
    //Cria um usuario no banco
    public ResponseEntity criaUsuario(@RequestBody @Valid Usuario usuario) {
        try {
            repository.save(usuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/senha")
    //altera a senha banco um usuario especifico
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
    //altera o endereço banco um usuario especifico
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
    //altera o telefone banco um usuario especifico
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



    @GetMapping("/saldo/{id}")
    //traz o saldo em conta de um usuario especifico do banco
    public ResponseEntity getSaldo(@PathVariable int id) {
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get().getSaldo());
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{id}/depositar/{valor}")
    //deposita valor no saldo da conta de um usuario especifico do banco
    public ResponseEntity depositarCredito(@PathVariable int id, @PathVariable Double valor) {
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isPresent()) {
            usuario.get().depositarSaldo(valor);
            repository.save(usuario.get());
            return ResponseEntity.ok(usuario.get().getSaldo());
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{id}/sacar/{valor}")
    //saca valor no saldo da conta de um usuario especifico do banco
    public ResponseEntity sacarCredito(@PathVariable int id, @PathVariable Double valor) {
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isPresent()) {
            usuario.get().sacarSaldo(valor);
            repository.save(usuario.get());
            return ResponseEntity.ok(usuario.get().getSaldo());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/arquivo")
    public ResponseEntity subirArquivo(@RequestParam("arquivo") MultipartFile arquivo) throws IOException{
        if (arquivo.isEmpty()){
            return badRequest().body("Arquivo não enviado!");
        }

        System.out.println("Recebendo um arquivo do tipo" + arquivo.getContentType());

        String conteudo = new String(arquivo.getBytes(), "UTF-8");
        String[] guardar = conteudo.split("\n");

        for (int i = 1; i < (guardar.length - 1) ; i++) {

            String nomeCompleto = guardar[i].substring(02,61).trim();
            String CPF = guardar[i].substring(62,73);
            LocalDate dataNascimento =LocalDate.parse(guardar[i].substring(73,83).trim()) ;
            String email = guardar[i].substring(83,147).trim();
            String telefone = guardar[i].substring(148,158).trim();
            String CEP = guardar[i].substring(159,167).trim();
            String complemento = guardar[i].substring(168,186).trim();
            Double saldo =Double.parseDouble(guardar[i].substring(188,195).trim()) ;
            String RG = guardar[i].substring(195,204).trim();
            String pontoReferencia = guardar[i].substring(204,263).trim();
            String senha = guardar[i].substring(264,278).trim();
            String coordenadas = guardar[i].substring(279,308).trim();
            String fotoRG = guardar[i].substring(309,313).trim();
            String fotoPerfil = guardar[i].substring(313,317).trim();
            Integer ehConsumidor = Integer.parseInt(guardar[i].substring(317,318).trim());
            Integer idCartao = Integer.parseInt(guardar[i].substring(319,320).trim());

            Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

            Usuario usuario = new Usuario(nomeCompleto,CPF,dataNascimento,email,telefone,CEP,complemento,saldo,
                    RG,pontoReferencia,senha,coordenadas,fotoRG,fotoPerfil,ehConsumidor,cartao.get());

            usuariosFilaObj.insert(usuario);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    //Deleta do banco um usuario especifico
    public ResponseEntity deleteId(@PathVariable int id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}