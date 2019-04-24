package Comando;

import Expressao.*;
import Variavel.*;

public class ComandoFor extends Comando implements Condicao {

    int linha;
    int linhaEnd;
    char variavel;
    int valor;
    String operador;
    boolean inicio = true;
    Expressao exp1;

    public ComandoFor(int lin, char variavel, int valor, String tipo, Expressao raizArvoreExpressao) {
        this.linha = lin;
        this.variavel = variavel;
        this.valor = valor;
        this.operador = tipo;
        this.exp1 = raizArvoreExpressao;
    }

    public void setLinha(int lin) {
        this.linha = lin;
    }

    public void setLinhaEnd(int lin) {
        this.linhaEnd = lin;
    }
    
    public char getVar(){
        return this.variavel;
    }
    
    public String getOperador(){
        return this.operador;
    }
    
    public int executa() {
        int posicao = (int)variavel - 97;
        double limite = exp1.avalia();
        if(inicio==true){
            Variaveis.var[posicao] = this.valor;
            this.inicio = false;
        }
        if(this.operador.equals("to")){
            if(Variaveis.var[posicao] > limite){
                this.inicio = true;
                return this.linhaEnd + 1;
            }
            else{
                return this.linha + 1;
            }
        }
        else{
            if(Variaveis.var[posicao] < limite){
                this.inicio = true;
                return this.linhaEnd + 1;
            }
            else{
                return this.linha + 1;
            }
        }
    }
}
