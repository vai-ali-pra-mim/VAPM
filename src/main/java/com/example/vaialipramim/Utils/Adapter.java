package com.example.vaialipramim.Utils;

import java.util.List;

public class Adapter<T> {

    private final ListaObjetos<T> listaObjetos;

    public Adapter(List list) {

        listaObjetos = new ListaObjetos<T>();
        for (var elemento: list){
            listaObjetos.adiciona((T) elemento);
        }
    }

    public ListaObjetos<T> getListaObjetos() {
        return listaObjetos;
    }

    @Override
    public String toString() {
        return "Adapter{" +
                "listaObjetos=" + listaObjetos +
                '}';
    }
}
