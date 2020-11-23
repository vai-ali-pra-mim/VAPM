package com.example.vaialipramim.Utils;

public class FilaObj<T>{

    //atributos
    int tamanho;
    private T[] fila;


    //constructor
    public FilaObj(int capacidade){
        tamanho=0;
        fila =(T[]) new String[capacidade];
    }


    //métodos
    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        //  return tamanho == fila.length;
        if (tamanho == (fila.length)) {
            return true;
        }
        return false;
    }

    public T insert(T info){
        if (tamanho == 0) {
        }
        fila[tamanho++]=info;
        return info;
    }

    public T peek(){
        return fila[0];
    }


    public T poll(T info){
        if(isEmpty()){
            return null;
        }
        for(int i = 0; i < tamanho; i++){
            fila[i]= fila[i+1];
        }
        tamanho--;
        return info;

    }


    public void exibe() {
        if(isEmpty()) {
            System.out.println("Fila vazia");
        }
        else {
            System.out.println("Exibindo elementos da Fila: \n");
            for(int i = 0; i <= tamanho -1 ; i++) {
                System.out.println(fila[i]);
            }
        }

    }

}



