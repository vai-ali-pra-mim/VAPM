package com.example.vaialipramim.Utils;

public class FilaObj<T>{

    //atributos
    int tamanho;
    private T[] fila;

    public int getTamanho() {
        return tamanho;
    }

    //constructor
    public FilaObj(int capacidade){
        tamanho=0;
        fila = (T[]) new Object[capacidade];
    }

    public T[] getFila() {
        return fila;
    }

    //métodos
    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == (fila.length);
    }

    public void insert(T info){
        if(!isFull())
            fila[tamanho++] = info;
    }

    public T peek(){
        return fila[0];
    }


    public T poll(){
        if(!isEmpty()){
            T retorno = fila[0];

            for (int index =0; index < tamanho; index++){
                fila[index] = fila[index +1];
            }

            tamanho--;
            return retorno;
        }
        return null;
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



