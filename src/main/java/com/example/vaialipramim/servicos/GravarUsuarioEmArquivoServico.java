package com.example.vaialipramim.servicos;

import com.example.vaialipramim.Utils.GravarArquivo;
import com.example.vaialipramim.repositorios.UsuarioRepository;
import com.example.vaialipramim.visoes.UsuarioVisao;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GravarUsuarioEmArquivoServico {

    UsuarioRepository repository;
    HttpServletResponse response;
    public GravarUsuarioEmArquivoServico(UsuarioRepository repository, HttpServletResponse response) {
        this.repository =  repository;
        this.response = response;
    }

    public ResponseEntity execute() throws IOException {
        String header = "";
        String corpo = "";
        String trailer = "";

        // Monta o registro header
        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        header += "00";
        header += "txt";
        header += "AZURE";
        header += formatter.format(dataDeHoje);
        header += "01";
        GravarArquivo.gravaRegistro("src/main/resources/static\\usuarios.txt", header);

        List<UsuarioVisao> lista = repository.findAllSimples();
        for (int i = 0; i < lista.size() ; i++) {
            GravarArquivo.gravaRegistro("src/main/resources/static\\usuarios.txt",
                    lista.get(i));
        }

        trailer += "01";
        trailer +=String.format("%06d",lista.size());
        GravarArquivo.gravaRegistro("src/main/resources/static\\usuarios.txt", trailer);

        File file = new File("src/main/resources/static/usuarios.txt");
        response.setHeader("Content-Disposition", String.format("attachment; filename=usuarios.txt"));

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
        return ResponseEntity.ok().build();
    }

}
