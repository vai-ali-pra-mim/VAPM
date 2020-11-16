package com.example.vaialipramim.Utils;

public class PilhaObj <T>{

    int topo;
    private T[] pilha;


    public PilhaObj(int capacidade) {
        topo = -1;
        pilha = (T[]) new Object[capacidade];
    }


    public boolean isEmpty(){
        if (topo == -1) {
            System.out.println("A pilha está vazia");
            return true;
        }
        return false;
    }

    public boolean isFull(){
        if (topo == pilha.length -1 ) {
            System.out.println("A pilha está cheia");
            return true;
        }
        return false;
    }

    public T push(T info){
        if (topo == -1) {
        }
        pilha[++topo]=info;
        return info;
    }


    public T pop(){
        if(isEmpty()){
            return null;
        }
        return pilha[topo--];
    }

    public T peek(){
        if (isEmpty() ) {
            return null;
        }
        return pilha[topo];
    }

    public void exibe() {
        System.out.println("\nExibindo elementos da pilha:");
        if(isEmpty()){
            System.out.println("Pilha vazia");
        }
        for (int i= 0; i <= topo; i++) {
            System.out.println(pilha[i]);
        }

    }

    public PilhaObj<T> multiPop (int n){
        PilhaObj<T> aux = new PilhaObj<T>(n);
        if(n > topo + 1){
            return null;
        }
        for(int i =0; i < n; i++){
            aux.push(this.pop());
        }

        return (PilhaObj<T>) aux;
    }


    public void multiPush(PilhaObj<T> aux){
        if(!isFull()){
            for(int i =0; i < aux.tamPilha()+1 ;i++){
                this.push(aux.pop());
            }
        }
    }

    public int tamPilha(){
        return topo +1;
    }


}



