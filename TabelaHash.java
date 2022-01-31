package app;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class TabelaHash {

    private List<ArrayList<Integer>> enderecos = new ArrayList<ArrayList<Integer>>();

    public List<ArrayList<Integer>> getEnderecos() {
        return this.enderecos;
    }

    public void setEnderecos(List<ArrayList<Integer>> enderecos) {
        this.enderecos = enderecos;
    }


    public TabelaHash(int M){
        if(M > 0){
            for(int j = 0; j < M; j++){
                ArrayList<Integer> lista = new ArrayList<Integer>();
                this.enderecos.add(lista);
            }
        }
           
    }

    public int hash(int valor) throws ArithmeticException{
        int endereco = -1;
        try{
            endereco = valor % this.enderecos.size(); //  h(k) = k mod M
            return endereco; 
        }catch(ArithmeticException e){
            throw new ArithmeticException();
        }   
    }

    public void inserir(int valor) throws ArithmeticException{
        int adress;
        try{
            adress = this.hash(valor);  //calcula o endereço da tabela onde a chave será inserida
            this.enderecos.get(adress).add(valor);//insere o valor na lista encadeada referente ao endereço calculado
        }catch(ArithmeticException e){
            throw new ArithmeticException();
        }    
    }

    public int buscar(int valor) throws Exception{
        int adress;  
        int elem;  //auxiliar para receber o próximo elemento da lista
        List<Integer> aux; //cria uma lista auxiliar para ser percorrida
        Iterator<Integer> i;
        
        try{
            adress = this.hash(valor); //calcula o endereço do valor na tabela
            aux = this.enderecos.get(adress);  ////cria uma lista auxiliar para ser percorrida
            i = aux.iterator(); 

            while(i.hasNext()){
                elem = (int) i.next();  //recebe o próximo elemento da lista
                if(elem == valor)
                    return aux.indexOf(elem);  //retorna a posição do elemento na lista
            }
            return -1;
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }catch(ArithmeticException er){
            throw new ArithmeticException();
        }catch(ConcurrentModificationException ex){
            throw new ConcurrentModificationException();
        }catch(NoSuchElementException err){
            return -1;
        }
    }

    public void remover(int valor) throws Exception {
        int adress, pos;
        try{
            adress = this.hash(valor); //endereco do valor na tabela
            pos = this.buscar(valor); //posicao do valor na lista encadeada
            if(pos != -1){
                this.enderecos.get(adress).remove(pos);
            }
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }catch(ArithmeticException e){
            throw new IndexOutOfBoundsException();
        }catch(ConcurrentModificationException ex){
            throw new ConcurrentModificationException();
        }
    }

    
    public void imprime(){
        int elem;
        List<Integer> aux;
        Iterator<Integer> i;

        for(int j = 0; j < enderecos.size(); j++){  
           System.out.println("Endereço " + j);

            aux = this.enderecos.get(j); 
            i = aux.iterator(); 

            while(i.hasNext()){
                elem = (int) i.next();
                System.out.println(elem);
                
            }
            System.out.println();
        }
    }

}
