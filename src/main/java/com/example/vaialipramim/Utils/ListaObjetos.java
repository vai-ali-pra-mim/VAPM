package com.example.vaialipramim.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;

public class ListaObjetos<T>  {
    private T[] vetor;
    private int nroElem;

    public ListaObjetos(int tam) {
        vetor = (T[]) new Object[tam];
        nroElem = 0;
    }

    public ListaObjetos(){
        vetor = (T[]) new Object[1];
        
    }

    public T[] getVetor() {
        return vetor;
    }

    public void adiciona(T valor) {
        if (nroElem < vetor.length) {
            vetor[nroElem++] = valor;
        } else if (nroElem == vetor.length) {
            int novoTam = (vetor.length + 1);
            T[] novoVetor = (T[]) new Object[novoTam];
            for (int index = 0; index < vetor.length; index++) {
                novoVetor[index] = vetor[index];
            }

            vetor = novoVetor;
            vetor[nroElem] = valor;
            nroElem++;
        }
    }

    public void exibe() {
        System.out.println("\nExibindo elementos da lista:");
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
        System.out.println();
    }

    public int busca(T valor) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return false;
        } else {

            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            nroElem--;
            return true;
        }
    }

    public boolean removeElemento(T valor) {
        return removePeloIndice(busca(valor));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        } else {
            return vetor[indice];
        }
    }

    public void limpa() {
        nroElem = 0;
    }

    public boolean estaVazio() {
        return nroElem == 0;
    }

    @Override
    public String toString() {
        return "ListaObjetos{" +
                "vetor=" + Arrays.toString(vetor) +
                '}';
    }

    public int getNroElem() {
        return nroElem;
    }
}