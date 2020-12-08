package com.example.vaialipramim.Utils;

import com.example.vaialipramim.dominios.Usuario;

import javax.swing.text.Element;
import java.util.List;

public class Adapter<T> {

    private final ListaObjetos<T> listaObjetos;

    public Adapter(List list) {

        listaObjetos = new ListaObjetos<T>();
        for (Object elemento: list){
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
