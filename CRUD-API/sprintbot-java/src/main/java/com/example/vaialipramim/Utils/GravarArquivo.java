package com.example.vaialipramim.Utils;

import com.example.vaialipramim.dominios.Produto;
import com.example.vaialipramim.visoes.UsuarioVisao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GravarArquivo<T> {
    public static void gravaRegistro(String nomeArq, UsuarioVisao usuario) {
        BufferedWriter saida = null;
        try {
            // o argumento true é para indicar que o arquivo não será sobrescrito e sim
            // gravado com append (no final do arquivo)
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(String.format("%-2s", "02"));
            saida.append(String.format("%-2s", usuario.getIdUsuario()));
            saida.append(String.format("%-7s","Usuario"));
            saida.append(String.format("%-50s",usuario.getNomeCompleto()));
            saida.append(String.format("%-11s",usuario.getCPF()));
            saida.append(String.format("%-12s",usuario.getDataNascimento()));
            saida.append(String.format("%-50s",usuario.getEmail()));
            saida.append(String.format("%-13s",usuario.getTelefone()));
            saida.append(String.format("%-8s",usuario.getCEP() + ""));
            saida.append(String.format("%-20s",usuario.getComplemento()));
            saida.append(String.format("%-8s",usuario.getSaldo()));
            saida.append(String.format("%-9s",usuario.getRG()));

            saida.append("\n");

            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }
    public static void gravaRegistro (String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            // o argumento true é para indicar que o arquivo não será sobrescrito e sim
            // gravado com append (no final do arquivo)
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");

            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

    public void gravaRegistro(String nomeArq, Produto registro) {
        BufferedWriter saida = null;
        try {
            // o argumento true é para indicar que o arquivo não será sobrescrito e sim
            // gravado com append (no final do arquivo)

            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {

            saida.append(registro.getIdProduto() + ";");
            saida.append(registro.getNomeProduto() + ";");
            saida.append(registro.getTipoProduto() + ";");
            saida.append(registro.getValor() + ";");
            saida.append(registro.getMarca() + ";");

            saida.append("\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

    public void gravaRegistro(String nomeArq) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append("\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

}
