package Comando;

import Expressao.*;
import Variavel.Variaveis;

public class ComandoEndF extends Comando {

    int linha;
    int linhaFor;
    char variavel;
    String operador;

    public ComandoEndF(int lin, int linW, char var, String op) {
        this.linha = lin;
        this.linhaFor = linW;
        this.variavel = var;
        this.operador = op;
    }

    public int executa() {
        int posicao = (int)variavel - 97;
        if(this.operador.equals("to")){
            Variaveis.var[posicao] += 1;
        }
        else{
            Variaveis.var[posicao] -= 1;
        }
        return this.linhaFor;
    }
}
